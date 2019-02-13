package frc.robot;

import java.util.*;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import frc2019grip.*;

import org.opencv.core.*;
import org.opencv.imgproc.*;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Rect;

/** ss, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveTrain driveTrain;
  public static HatchPlacer hatchPlacer;
  public static Intake intake;
  public static LiftSystem liftSystem;
  public static RaiseTheRobot raiseTheRobot;
  public static Command teleopDrive;
  public static Command operateIntake;
  public static Command liftToSpecificHeight;
  public static int driveMode; 
  public static OI oi;

  private static final int IMG_WIDTH = 640;
	private static final int IMG_HEIGHT = 480;
	
  private VisionThread visionThread;
  private final Object imgLock = new Object();
  public static long rectArea;
  public static long rectArea2;

  static Compressor compressor = new Compressor(0);
  static UsbCamera cam;

  //Command m_autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    driveTrain = new DriveTrain();
    hatchPlacer = new HatchPlacer();
    intake = new Intake();
    raiseTheRobot = new RaiseTheRobot();
    operateIntake = new OperateIntake();
    teleopDrive = new TeleopDrive();
    liftSystem = new LiftSystem();

    cam = CameraServer.getInstance().startAutomaticCapture();
    CvSink cvSink = CameraServer.getInstance().getVideo();
    CvSource outputStream = CameraServer.getInstance().putVideo("Detected", IMG_WIDTH, IMG_HEIGHT);
    cam.setResolution(IMG_WIDTH, IMG_HEIGHT);
    cam.setBrightness(50);
    Mat source = new Mat();
    Mat output = new Mat();
    visionThread = new VisionThread(cam, new GripPipeline(), pipeline -> {
      cvSink.grabFrame(source);
      Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2RGB);
      ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
      if (!contours.isEmpty()) {
          //SmartDashboard.putNumber("Contour Area", (r.width * r.height));
          for(int i = 0; i < contours.size(); i++){
            Rect r = Imgproc.boundingRect(contours.get(i));
            Imgproc.rectangle(output,
              new Point(r.x, r.y),
              new Point(r.x + r.width, r.y + r.height),
              new Scalar(0, 0, 255),
              5
            );
          }
          synchronized (imgLock) {
            Rect r2 = Imgproc.boundingRect(contours.get(0));
            rectArea = r2.width * r2.height;
            SmartDashboard.putNumber("rectArea 1", rectArea);

            if(contours.size()>1) {
              Rect r3 = Imgproc.boundingRect(contours.get(1));
              rectArea2 = r3.width * r3.height;
              SmartDashboard.putNumber("rectArea 2", rectArea2);
            }
          }
      }
      outputStream.putFrame(output);
    });
    visionThread.start();


    compressor.setClosedLoopControl(true);
    driveMode = 2;// 1 for tank drive, 2 for arcade drive.
    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // For Xbox controllers
    if(oi.mainController.getYButtonPressed()){
      ActivateHatchPlacer a = new ActivateHatchPlacer();
      a.start();
    }
    if(oi.mainController.getYButtonReleased()){
      RetractHatchPlacer r = new RetractHatchPlacer();
      r.start();
    }
    // For joysticks
    oi.hatchButton.whenPressed(new ActivateHatchPlacer());
    oi.hatchButton.whenReleased(new RetractHatchPlacer());
  }


  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    //if (m_autonomousCommand != null) {
      //m_autonomousCommand.start();
    //}
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //if (m_autonomousCommand != null) {
      //m_autonomousCommand.cancel();
    //}
    teleopDrive.start();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}

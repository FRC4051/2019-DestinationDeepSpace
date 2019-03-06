package frc.robot;

import java.util.*;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import frc.robot.subsystems.*;

import com.ctre.phoenix.motorcontrol.*;
/** ss, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static XboxController mainController = new XboxController(0);

  public static int heightID;
  public static int height = 0;
  public static boolean hatchPlacerExtended = false;
  
  private static boolean usingLegacyLift = false;// false: switch between all heights. true: switch between either hatch heights or ball heights. New lift is untested.
  //public static boolean autoPilotArm = false;
  //static boolean heightIncReq = false;
  //static boolean heightDecReq = false;
  
  public static final int[] heights = {
    0/*idle*/, 1500/*hatch*/, 7500/*ball*/, 10500/*hatch*/, 16500/*ball*/, 19500/*hatch*/, 25400/*ball*/,
  };

  private static Compressor compressor = new Compressor(0);
  //Command m_autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    heightID = 0;
    SmartDashboard.putNumber("HeightID", heightID);

    configLiftMotor();

    compressor.setClosedLoopControl(true);
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
    // Drive
    if(mainController.getXButton()){
      double rectArea1 = (double) SmartDashboard.getNumber("rectArea 1", 1);
      double rectArea2 = (double) SmartDashboard.getNumber("rectArea 2", 1);
      DriveTrain.seekTarget(rectArea1, rectArea2);
    }
    else
      DriveTrain.drive();
    // Hatch placer
    if(mainController.getYButtonPressed()){
      if(hatchPlacerExtended) {
        HatchPlacer.retract();
        hatchPlacerExtended = false;
      }else{
        HatchPlacer.extend();
        hatchPlacerExtended = true;
      }
    }
    // Lift to specific height
    // if(oi.mainController.getPOV(0) == 0 && heightID < 6){
    //   heightIncReq = true;
    // } else if(heightIncReq){
    //   heightID++;
    //   LiftSystem.liftMotor.set(ControlMode.MotionMagic, heights[heightID]);
    //   heightIncReq = false;
    //   autoPilotArm = true;
    // }else if(oi.mainController.getPOV(0) == 180 && heightID > 0){
    //   heightDecReq = true;
    // }else if(heightDecReq){
    //   heightID--;
    //   LiftSystem.liftMotor.set(ControlMode.MotionMagic, heights[heightID]);
    //   heightDecReq = false;
    //   autoPilotArm = true;
    // } else // Normal lift
    // if(oi.mainController.getBumper(Hand.kLeft)){
    //   liftSystem.moveDown();
    //   autoPilotArm = false;
    // }else if(oi.mainController.getBumper(Hand.kRight)){
    //   autoPilotArm = false;
    //   liftSystem.moveUp();
    // }else if (!autoPilotArm){
    //   liftSystem.reset();
    // }

    // Specific lift v2.0
    if(usingLegacyLift){
      if(mainController.getBumperPressed(Hand.kRight)){
        if(heightID < heights.length - 1)  {
          heightID++;
        }
      }else if(mainController.getBumperPressed(Hand.kLeft)){
        if(heightID > 0) {
          heightID--;
        }
      }
    }else{
      //ball
      if(mainController.getBumperPressed(Hand.kRight)){
        if(heightID < heights.length - 1)  {
          heightID += 2;
        }else{
          heightID = 0;
        }
      }else 
      //hatch
      if(mainController.getBumperPressed(Hand.kLeft)){
        if(heightID == 0)
          heightID++;
        else if(heightID < heights.length - 1)  {
          heightID += 2;
        } else if(heightID == heights.length){
          heightID = 0;
        }
      }
    }
    LiftSystem.liftMotor.set(ControlMode.MotionMagic, heights[heightID]);
    // Normal lift
    if (mainController.getPOV() == 180) {
      LiftSystem.liftMotor.set(ControlMode.PercentOutput, -1);  
    }
    if (mainController.getPOV() == 0) {
      LiftSystem.liftMotor.set(ControlMode.PercentOutput, 1);  
    }
    if(LiftSystem.liftSensors.isRevLimitSwitchClosed()){
      LiftSystem.liftSensors.setQuadraturePosition(0, 500);
    }
    // Intake
    if(mainController.getTriggerAxis(Hand.kLeft) > 0.3){
      Intake.pullInBall();
    }else if(mainController.getTriggerAxis(Hand.kRight) > 0.3){
      Intake.yeetOutBall();
    }else{
      Intake.setIdle();
    }
    // Display on lift motor position & velocity on dashboard.
    SmartDashboard.putNumber("Arm Encoder Position", LiftSystem.liftSensors.getQuadraturePosition());
    SmartDashboard.putNumber("Arm Encoder Velocity", LiftSystem.liftSensors.getQuadratureVelocity());
    SmartDashboard.putBoolean("Upper Arm Limit Switch", LiftSystem.liftSensors.isFwdLimitSwitchClosed());
    SmartDashboard.putBoolean("Lower Arm Limit Switch", LiftSystem.liftSensors.isRevLimitSwitchClosed());
    SmartDashboard.putNumber("HeightID", heightID);
  }

  private void configLiftMotor(){
    LiftSystem.liftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    LiftSystem.liftMotor.configNominalOutputForward(0, 30);
    LiftSystem.liftMotor.configNominalOutputReverse(0, 30);
    LiftSystem.liftMotor.configPeakOutputForward(1, 30);
    LiftSystem.liftMotor.configPeakOutputReverse(-1, 30);
    LiftSystem.liftMotor.configMotionCruiseVelocity(15000, 30);
    LiftSystem.liftMotor.configMotionAcceleration(6000, 30);
    LiftSystem.liftMotor.selectProfileSlot(0, 0);
    LiftSystem.liftMotor.config_kF(0, 0.2, 30);
    LiftSystem.liftMotor.config_kP(0, 0.2, 30);
    LiftSystem.liftMotor.config_kI(0, 0.0, 30);
    LiftSystem.liftMotor.config_kD(0, 0.0, 30);
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

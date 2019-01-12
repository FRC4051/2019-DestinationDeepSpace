package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.OI;

/**
 * Drive train subsystem for the robot.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  SpeedController leftDriveMotor = RobotMap.driveMotor1;
  SpeedController rightDriveMotor = RobotMap.driveMotor2;
  DifferentialDrive driveControl = new DifferentialDrive(leftDriveMotor, rightDriveMotor); 

  public void tankDrive(){
    driveControl.tankDrive(OI.leftStick.getY(), OI.rightStick.getY()); 
  }

  public void arcadeDrive(){
    driveControl.arcadeDrive(OI.leftStick.getY(), OI.leftStick.getX());
  }

  @Override
  public void initDefaultCommand() {
    // Default command for the drive train.
    //setDefaultCommand(new TeleopDrive());
  }
}

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
import edu.wpi.first. wpilibj.GenericHID.Hand;

/**
 * Drive train subsystem for the robot.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  static VictorSPX leftDriveMotor = RobotMap.driveMotor1;
  static VictorSPX rightDriveMotor = RobotMap.driveMotor2;

  public static void drive(){
    // Tank drive
    leftDriveMotor.set(ControlMode.PercentOutput, Robot.mainController.getY(Hand.kRight) * 1);
    rightDriveMotor.set(ControlMode.PercentOutput, Robot.mainController.getY(Hand.kLeft) * -1);
    // Double-joystick arcade drive
    //leftDriveMotor.set(ControlMode.PercentOutput, Robot.mainController.getY(Hand.kLeft) - Robot.mainController.getY(Hand.kRight));
    //rightDriveMotor.set(ControlMode.PercentOutput, Robot.mainController.getY(Hand.kRight) - Robot.mainController.getY(Hand.kLeft));
    // Single-joystick arcade drive
    //leftDriveMotor.set(ControlMode.PercentOutput, (Robot.mainController.getY(Hand.kLeft) - Robot.mainController.getX(Hand.kLeft))/2);
    //rightDriveMotor.set(ControlMode.PercentOutput, (Robot.mainController.getY(Hand.kLeft) + Robot.mainController.getX(Hand.kLeft))/2);
  }

  @Override
  public void initDefaultCommand() {
    // Default command for the drive train.
    //setDefaultCommand(new TeleopDrive());
  }
}

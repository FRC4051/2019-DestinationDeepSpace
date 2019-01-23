package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Drive train subsystem for the robot.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  SpeedController leftDriveMotor = RobotMap.driveMotor1;
  SpeedController rightDriveMotor = RobotMap.driveMotor2;
  public DifferentialDrive driveControl = new DifferentialDrive(leftDriveMotor, rightDriveMotor); 

  public void enableTankDrive(){
    //driveControl.tankDrive(Robot.oi.leftStick.getY(), Robot.oi.rightStick.getY()); 
  }

  public void enableArcadeDrive(){
    if(Robot.oi.mainController.getAButton()){
      double cumulativeLightValue = 0;
      if(!Robot.digitalLS1.get()) cumulativeLightValue += 0.5;
      if(!Robot.digitalLS2.get()) cumulativeLightValue += 0.5;
      if(!Robot.digitalLS3.get()) cumulativeLightValue += 0;
      if(!Robot.digitalLS4.get()) cumulativeLightValue += -0.5;
      if(!Robot.digitalLS5.get()) cumulativeLightValue += -0.5;
      if(!Robot.digitalLS1.get()) System.out.println("Over here!");
      if(!Robot.digitalLS2.get()) System.out.println("Over here!");
      if(!Robot.digitalLS3.get()) System.out.println("Over here!");
      if(!Robot.digitalLS4.get()) System.out.println("Over here!");
      if(!Robot.digitalLS5.get()) System.out.println("Over here!");

      driveControl.arcadeDrive(-0.5, cumulativeLightValue);
      System.out.println(cumulativeLightValue);
    }else{
      driveControl.arcadeDrive(Robot.oi.mainController.getY(Hand.kLeft)*0.5, Robot.oi.mainController.getX(Hand.kRight)*-0.5);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Default command for the drive train.
    //setDefaultCommand(new TeleopDrive());
  }
}

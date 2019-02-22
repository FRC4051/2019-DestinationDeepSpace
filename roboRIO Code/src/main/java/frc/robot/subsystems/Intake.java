/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  static TalonSRX leftIntakeMotor = RobotMap.intakeMotor1;
  static TalonSRX rightIntakeMotor = RobotMap.intakeMotor2;

  //sets motors to direction -1 (going in) and pulls in the ball for a set amount of time
  public static void pullInBall(){
    // Pull in ball. 
    leftIntakeMotor.set(ControlMode.PercentOutput, -.7);
    rightIntakeMotor.set(ControlMode.PercentOutput, .7);
  }

  public static void yeetOutBall(){
    // Launch out ball.
    leftIntakeMotor.set(ControlMode.PercentOutput, 1);
    rightIntakeMotor.set(ControlMode.PercentOutput, -1);
  }

  public static void setIdle(){
    // Idle.
    leftIntakeMotor.set(ControlMode.PercentOutput, 0);
    rightIntakeMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

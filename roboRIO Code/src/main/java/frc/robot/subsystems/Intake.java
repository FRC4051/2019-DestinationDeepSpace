/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  static SpeedController leftIntakeMotor = RobotMap.intakeMotor1;
  static SpeedController rightIntakeMotor = RobotMap.intakeMotor2;

  //sets motors to direction -1 (going in) and pulls in the ball for a set amount of time
  public void pullInBall(){
    // Pull in ball. 
    leftIntakeMotor.set(0.45);
    rightIntakeMotor.set(-0.45);
  }

  public void yeetOutBall(){
    // Launch out ball.
    leftIntakeMotor.set(-1.0);
    rightIntakeMotor.set(1.0);
  }

  public void setIdle(){
    // Idle.
    leftIntakeMotor.set(0);
    rightIntakeMotor.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

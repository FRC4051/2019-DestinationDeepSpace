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
  static long startingTime = 0;
  static long currentTime = 0;

  //sets motors to direction -1 (going in) and pulls in the ball for a set amount of time
  public static void pullInBall(){
    // if(startingTime == 0) {
    //   startingTime = System.currentTimeMillis();
    // }
    // currentTime = System.currentTimeMillis() - startingTime;
    // double desiredPercentOutput = 0.8;
    // if(currentTime < 500) {
    //   double deltaTime = currentTime / 500.0; 
    //   desiredPercentOutput = 1.0 - (deltaTime * 0.2);
    // }
    // Pull in ball. 
    leftIntakeMotor.set(ControlMode.PercentOutput, 1/*desiredPercentOutput*/);
    rightIntakeMotor.set(ControlMode.PercentOutput, -1/*desiredPercentOutput*/);    
  }

  public static void yeetOutBall(){
    if(startingTime == 0) {
      startingTime = System.currentTimeMillis();
    }
    currentTime = System.currentTimeMillis() - startingTime;
    double desiredPercentOutput = 0.5;
    if(currentTime < 500) {
      double deltaTime = currentTime / 500.0;
      desiredPercentOutput = 1.0 - (deltaTime * 0.5);
    }
    // Launch out ball.
    leftIntakeMotor.set(ControlMode.PercentOutput, -desiredPercentOutput);
    rightIntakeMotor.set(ControlMode.PercentOutput, desiredPercentOutput);    
  }

  public static void setIdle(){
    // Idle.
    leftIntakeMotor.set(ControlMode.PercentOutput, 0);
    rightIntakeMotor.set(ControlMode.PercentOutput, 0);
    startingTime = 0;
    currentTime = 0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

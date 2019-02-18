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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;
/**
 * Add your docs here.
 */
public class LiftSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static TalonSRX liftMotor = RobotMap.liftMotor;
  public static SensorCollection liftSensors = liftMotor.getSensorCollection();


  public LiftSystem(){
    // Encoder.
    liftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    // Motion magic.
    liftMotor.configNominalOutputForward(0, 30);
		liftMotor.configNominalOutputReverse(0, 30);
		liftMotor.configPeakOutputForward(1, 30);
    liftMotor.configPeakOutputReverse(-1, 30);
    liftMotor.configMotionCruiseVelocity(15000, 30);
    liftMotor.configMotionAcceleration(6000, 30);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void moveUp() {
    //will move giraffe up
    if(liftSensors.getQuadraturePosition() > 15000) liftMotor.set(ControlMode.PercentOutput, 0.5);
    else liftMotor.set(ControlMode.PercentOutput, 1);
  }

  public void moveDown() {
    //will move giraffe down
    if(liftSensors.getQuadraturePosition() > 15000) liftMotor.set(ControlMode.PercentOutput, -0.5);
    else liftMotor.set(ControlMode.PercentOutput, -1);
  }
  public void reset(){
    liftMotor.set(ControlMode.PercentOutput, 0);
  }
  public void motionMagicMove(int pos){
    liftMotor.set(ControlMode.MotionMagic, pos);
  }
}

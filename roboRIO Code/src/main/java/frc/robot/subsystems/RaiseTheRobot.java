package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

//raise the roof
public class RaiseTheRobot extends Subsystem{

    static DoubleSolenoid frontRightSolenoid = RobotMap.solenoid2;
    static DoubleSolenoid frontLeftSolenoid = RobotMap.solenoid4;
    static DoubleSolenoid backSolenoid = RobotMap.solenoid3;

    public static void raiseRobotFront(){
        frontRightSolenoid.set(DoubleSolenoid.Value.kForward);
        frontLeftSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public static void raiseRobotBack(){
        backSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public static void lowerRobotFront(){
        frontRightSolenoid.set(DoubleSolenoid.Value.kReverse);
        frontLeftSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public static void lowerRobotBack(){
        backSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void initDefaultCommand() {
    // Default command for the drive train.
  }
}
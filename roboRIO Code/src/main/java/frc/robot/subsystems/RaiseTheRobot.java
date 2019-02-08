package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

//raise the roof
public class RaiseTheRobot extends Subsystem{

    static DoubleSolenoid frontRightSolenoid = RobotMap.solenoid2;
    static DoubleSolenoid frontLeftSolenoid = RobotMap.solenoid3;
    static DoubleSolenoid backRightSolenoid = RobotMap.solenoid4;
    static DoubleSolenoid backLeftSolenoid = RobotMap.solenoid5;

    public void raiseRobotFront(){
        frontRightSolenoid.set(DoubleSolenoid.Value.kForward);
        frontLeftSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void raiseRobotBack(){
        backRightSolenoid.set(DoubleSolenoid.Value.kForward);
        backLeftSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void lowerRobotFront(){
        frontRightSolenoid.set(DoubleSolenoid.Value.kReverse);
        frontLeftSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void lowerRobotBack(){
        backRightSolenoid.set(DoubleSolenoid.Value.kReverse);
        backLeftSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void initDefaultCommand() {
    // Default command for the drive train.
    //setDefaultCommand(new TeleopDrive());
  }
}
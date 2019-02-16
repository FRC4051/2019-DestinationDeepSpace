package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class HatchPlacer extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //example boi
  static DoubleSolenoid linearSolenoid = RobotMap.solenoid1;

  //all below example bois
  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }

  public void extend(){
    linearSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void retract(){
    linearSolenoid.set(DoubleSolenoid.Value.kForward);
  }
}

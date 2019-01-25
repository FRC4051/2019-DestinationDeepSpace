package frc.robot;

import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static Talon driveMotor1 = new Talon(0);
  public static Talon driveMotor2 = new Talon(1);
  public static Talon liftMotor = new Talon(2);
  public static DoubleSolenoid solenoid1 = new DoubleSolenoid(1,2);
}

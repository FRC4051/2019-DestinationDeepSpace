package frc.robot;

import edu.wpi.first.wpilibj.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static TalonSRX intakeMotor1 = new TalonSRX(1);
  public static TalonSRX liftMotor = new TalonSRX(2);
  public static TalonSRX driveMotor1 = new TalonSRX(3);
  public static TalonSRX driveMotor2 = new TalonSRX(4);
  public static TalonSRX intakeMotor2 = new TalonSRX(6);
  public static DoubleSolenoid solenoid1 = new DoubleSolenoid(0,1);
  // public static DoubleSolenoid solenoid2 = new DoubleSolenoid(2,3);
  // public static DoubleSolenoid solenoid3 = new DoubleSolenoid(4,5);
  // public static DoubleSolenoid solenoid4 = new DoubleSolenoid(6,7);
  // public static DoubleSolenoid solenoid5 = new DoubleSolenoid(8,9);
}


package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.*;

public class LiftToSpecificHeight extends Command {

  static int heightID;
  static int height;
  // static Encoder liftEncoder;

  public LiftToSpecificHeight(int _heightID) {
    heightID = _heightID;
    Robot.liftSystem._LiftMotor;
    //liftEncoder = new Encoder(/*Encoder Port*/);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    switch (heightID) {
      case 0:
        height = 1000;
        break;
      case 1:
        height = 2000;
        break;
      case 2:
        height = 3000;
        break;
      case 3:
        height = 4000;
        break;
      case 4:
        height = 5000;
        break;
      case 5:
        height = 6000;
        break;
      case 6:
        height = 7000;
        break;
      default:
        // If this happens then the command is referencing a height that doesn't exist.
        break;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (/*Encoder reads that the lift is at the right height.*/false);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}

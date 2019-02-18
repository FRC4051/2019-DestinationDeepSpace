
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.*;
import frc.robot.subsystems.LiftSystem;

public class LiftToSpecificHeight extends Command {

  static int heightID;
  static int height;

  public LiftToSpecificHeight(int _heightID) {
    heightID = _heightID;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    switch (heightID) {
      case 0:
        height = 0;
        break;
      case 1:
        height = 4000;
        break;
      case 2:
        height = 8000;
        break;
      case 3:
        height = 12000;
        break;
      case 4:
        height = 16000;
        break;
      case 5:
        height = 20000;
        break;
      case 6:
        height = 24000;
        break;
      default:
        // If this happens then the command is referencing a height that doesn't exist.
        break;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(height > LiftSystem.liftSensors.getQuadraturePosition()){
      Robot.liftSystem.moveUp();
    }else if(height < LiftSystem.liftSensors.getQuadraturePosition()){
      Robot.liftSystem.moveDown();
    }else{
      Robot.liftSystem.reset();
    }
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

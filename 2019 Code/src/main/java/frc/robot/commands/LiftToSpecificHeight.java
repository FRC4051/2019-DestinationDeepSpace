
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class LiftToSpecificHeight extends Command {

  // static int heightID;
  // static Encoder liftEncoder;

  public LiftToSpecificHeight(/*int _heightID*/) {
  
  //  requires(/*Robot.liftSystem*/);
  //  heightID = _heightID;
  //  liftEncoder = new Encoder(/* *//);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    /*switch (heightID) {
      case 0:
       
        break;
      case 1:
        
        break;
      case 2:
        
        break;
      case 3:
        
        break;
      case 4:
       
        break;
      case 5:
       
        break;
      case 6:
       
        break;
      default:

        break;
    }*/
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
  }
}

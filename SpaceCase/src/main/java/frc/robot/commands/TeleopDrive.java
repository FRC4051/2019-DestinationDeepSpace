package frc.robot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command; 
import frc.robot.Robot;

public class TeleopDrive extends Command {
  
  public TeleopDrive() {
    requires(Robot.driveTrain);
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch (Robot.driveMode) {
      case 1:
        Robot.driveTrain.tankDrive();
        break;
      case 2:
        Robot.driveTrain.arcadeDrive();
        break;
      default:
        break;
      }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  // Unused.
  @Override
  protected boolean isFinished() {
    return false;
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

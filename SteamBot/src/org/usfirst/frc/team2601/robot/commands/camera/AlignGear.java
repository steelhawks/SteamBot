package org.usfirst.frc.team2601.robot.commands.camera;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AlignGear extends Command {

    public AlignGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.camera.align = false;
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.camera.getGearPosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.camera.align;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

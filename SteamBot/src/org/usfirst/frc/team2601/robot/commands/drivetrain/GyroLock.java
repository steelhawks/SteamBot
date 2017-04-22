package org.usfirst.frc.team2601.robot.commands.drivetrain;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroLock extends Command {

	Constants constants = Constants.getInstance();
	
	double angle = 0;
	
    public GyroLock(double setAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	angle = setAngle;
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
		constants.gyroTurnRight = false;

    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.gyroLock(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return constants.gyroTurnRight;

    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team2601.robot.commands.drivetrain;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncGyroPleaseSlow extends Command {

	Constants constants = Constants.getInstance();
	
	double leftDistance = 0;
	double rightDistance = 0;
	int i;
	
    public EncGyroPleaseSlow(double leftDist, double rightDist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	leftDistance = leftDist;
    	rightDistance = rightDist;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	constants.ultraBool = false;
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.gyro.zeroYaw();
    	Robot.drivetrain.leftEnc.reset();
    	Robot.drivetrain.rightEnc.reset();
    	i = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.EncGyroForwardSlow(leftDistance, rightDistance);
    	i++;
    	System.out.println(i);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gear.isPeg();
    	//return constants.ultraBool || i > 150;
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

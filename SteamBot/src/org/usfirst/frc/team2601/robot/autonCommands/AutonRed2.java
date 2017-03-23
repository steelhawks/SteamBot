package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonForwardUltra;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForwardUltra;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ReverseDirection;
import org.usfirst.frc.team2601.robot.commands.gear.GearPanel;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeed;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeedAuton;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonRed2 extends CommandGroup {

    public AutonRed2() {
    	
    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new AutonGyroForwardUltra(2.5));// <-- this is commented out due to inconsistency
    	//addSequential(new EncoderForward(3100));
    	//addSequential(new AutonForwardUltra(2.5));
    	//addSequential(new EncoderForward(2100));
    	//addSequential(new AlignGear());
    	//addSequential(new EncoderForward(200));
    	Robot.drivetrain.stopMotors();
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(1.0));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(1.0));
    	addSequential(new EncoderBackward(1600));
    	addSequential(new GearPiston());//swapped
    	addSequential(new PushGear());
    	addSequential(new GyroTurnRight(50)); //don't need to get balls yet- testing gear only
    	//addSequential(new EncoderForward(8000));
    	//addSequential(new GyroTurnLeft(60));
    	//addSequential(new EncoderBackward(1500));
    	//addSequential(new TimeDelay(0.75));    	
    	//addSequential(new AutonGyroForwardUltra(9.5));
    	/*addSequential(new EncoderBackward(1500));
    	addSequential(new GyroTurnRight(85));
    	addSequential(new EncoderForward(3600));
    	addSequential(new GyroTurnRight(105));*/
    	//addSequential(new EncoderBackward(500));
    	Robot.drivetrain.stopMotors();
    	addSequential(new ReverseDirection());
    	//addSequential(new ShootPIDAuton(4.0));
    	
    }
}

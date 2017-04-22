package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForwardUltra;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseBack;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
import org.usfirst.frc.team2601.robot.commands.gear.GearPanel;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonRed1 extends CommandGroup {

    public AutonRed1() {
    	
    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(4208,4208));//10foot 6in
    	addSequential(new Shift());
    	addSequential(new GyroTurnRight(56));//45
    	//addSequential(new Shift());
    	//addSequential(new EncGyroPlease(3601,3480));//3601,3480
    	//addSequential(new EncGyroPlease(2900,2780));//new
    	addSequential(new TimeDelay(0.5));
    	addSequential(new AlignGear());//new*/
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(601,600));//new
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());
    	addSequential(new EncGyroPleaseBack(1500,1900));    
    }
}

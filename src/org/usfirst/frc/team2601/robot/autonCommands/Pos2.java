package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
import org.usfirst.frc.team2601.robot.commands.WaitTime;
//import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseBack;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseSlow;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ReverseDirection;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos2 extends CommandGroup {

    public Pos2() {
    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	Robot.drivetrain.leftEnc.reset();
    	Robot.drivetrain.rightEnc.reset();
    	//addParallel(new ShootPIDAuton(0.1));
    	addParallel(new Shift());
    	addSequential(new TimeDelay(0.5));
    	//addSequential(new EncGyroPlease(3201,3080));//3601,3480
    	//addSequential(new EncGyroPlease(2900,2780));//when using low gear
    	addSequential(new EncGyroPlease(2500,2380));//ALPHAAAAAAAAAAAAAAAAAA
    	//addSequential(new EncGyroPlease(2200,2200));//Beta
    	addSequential(new Shift());
    	addSequential(new TimeDelay(0.5));
    	//addSequential(new AlignGear());//new*/
    	addSequential(new Shift());
    	//addSequential(new AlignGear());//new
    	//addSequential(new EncGyroPlease(401,400));//new
    	addSequential(new EncGyroPleaseSlow(701,700));
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());
    	addSequential(new EncGyroPleaseBack(1500,1900));
    	addSequential(new GearPiston());//swapped
    	//addSequential(new PushGear());
    	Robot.drivetrain.stopMotors();
    	addSequential(new ReverseDirection());
    }
}

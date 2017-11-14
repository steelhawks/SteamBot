package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
import org.usfirst.frc.team2601.robot.commands.WaitTime;
//import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseBack;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseSlow;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos1 extends CommandGroup {

    public Pos1() {
    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	Robot.drivetrain.leftEnc.reset();
    	Robot.drivetrain.rightEnc.reset();
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(4008,4008));//10 ft 6 //4008
    	addSequential(new Shift());
    	Robot.drivetrain.gyro.reset();
    	//addSequential(new TimeDelay(0.25));
    	addSequential(new GyroTurnRight(56));//45
    	//addSequential(new EncGyroPlease(3601,3480));//3601,3480
    	//addSequential(new EncGyroPlease(2900,2780));//new
    	addSequential(new TimeDelay(0.5));
    	//addSequential(new AlignGear());//new*/
    	addSequential(new Shift());
    	//addParallel(new WaitTime(1.0));
    	addSequential(new EncGyroPleaseSlow(701,700));
    	//addSequential(new EncGyroPlease(701,700));//new
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());
    	//addSequential(new EncGyroPleaseBack(1025, 1025));
    	addParallel(new Shift());
    	addSequential(new EncGyroPleaseBack(615, 495));//25inches
    	addSequential(new GearPiston());
    }
}

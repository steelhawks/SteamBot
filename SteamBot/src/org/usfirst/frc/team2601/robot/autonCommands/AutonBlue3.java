package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseBack;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
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
public class AutonBlue3 extends CommandGroup {

    public AutonBlue3() {

    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(3704,3584));//10 ft 6
    	addSequential(new Shift());
    	addSequential(new GyroTurnRight(59.79));
    	addSequential(new Shift());
    	//addSequential(new EncGyroPlease(3601,3480));//3601,3480
    	//addSequential(new EncGyroPlease(2900,2780));//new
    	addSequential(new TimeDelay(0.5));
    	addSequential(new AlignGear());//new*/
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(701,700));//new
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());
    	//addSequential(new EncGyroPleaseBack(1067, 1300));
    	addParallel(new Shift());
    	addSequential(new EncGyroPleaseBack(2667, 2800));//25inches
    	addParallel(new Shift());
    	addSequential(new TimeDelay(0.25));
    	Robot.drivetrain.stopMotors();
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new GyroTurnRight(30));
    	addParallel(new Shift());
    	addSequential(new EncGyroPleaseBack(2667,2800));//67.5
    	addParallel(new GearPiston());
    	addSequential(new TimeDelay(1.5));
    	addSequential(new EncGyroPlease(600,800));//12inches
    	addParallel(new Shift());
    	addSequential(new GyroTurnRight(60));
    	addSequential(new ShootPIDAuton(0.1));
    	addSequential(new TimeDelay(8.0));
    	addSequential(new ShootPIDAuton(0.1));
    }
}

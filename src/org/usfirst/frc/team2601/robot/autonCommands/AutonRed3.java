package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
//import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonForwardUltra;
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
import org.usfirst.frc.team2601.robot.commands.gear.HopperPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeed;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeedAuton;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonRed3 extends CommandGroup {

    public AutonRed3() {

    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(4208,4208));//10 ft 6
    	addSequential(new Shift());
    	Robot.drivetrain.gyro.reset();
    	//addSequential(new TimeDelay(0.25));
    	addSequential(new GyroTurnRight(56));
    	addSequential(new Shift());
    	//addSequential(new EncGyroPlease(3601,3480));//3601,3480
    	//addSequential(new EncGyroPlease(2900,2780));//new
    	addSequential(new TimeDelay(0.5));
    	//addSequential(new AlignGear());//new*/
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(401,400));
    	//addSequential(new EncGyroPlease(701,700));//new
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());
    	//addSequential(new EncGyroPleaseBack(1025, 1025));
    	addParallel(new Shift());
    	addSequential(new EncGyroPleaseBack(615, 495));//25inches
    	addParallel(new Shift());
    	addSequential(new TimeDelay(0.25));
    	Robot.drivetrain.stopMotors();
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new GyroTurnLeft(-30));
    	addParallel(new Shift());
    	addSequential(new EncGyroPleaseBack(2767,2767));//67.5
    	addParallel(new GearPanel());
    	addSequential(new TimeDelay(1.5));
    	addSequential(new EncGyroPlease(492,492));//12inches
    	addParallel(new Shift());
    	addSequential(new GyroTurnLeft(-60));
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(1000,1000));//unknown distance
    	addSequential(new GyroTurnRight(126));
    	addSequential(new EncGyroPleaseBack(400,400));
    	addSequential(new ShootPIDAuton(0.1));
    	addSequential(new TimeDelay(6.0));
    	addSequential(new ShootPIDAuton(0.1));
      }
}

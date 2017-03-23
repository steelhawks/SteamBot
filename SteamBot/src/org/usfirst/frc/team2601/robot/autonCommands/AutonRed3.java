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
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
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

    	//Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.reset();
    	addSequential(new Shift());
    	addSequential(new EncoderForward(1795));
    	addSequential(new Shift());
    	//Robot.drivetrain.gyro.reset(); Reset line moved to the beginning - 3/19
    	addSequential(new GyroTurnLeft(-50));
    	addSequential(new Shift());
    	//Robot.drivetrain.gyro.reset();
    	//addSequential(new AutonGyroForwardUltra(15)); Taken off because it is inaccurate - 3/19
    	addSequential(new EncoderForward(2700));
    	addSequential(new Shift());
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new EncoderBackward(800));
    	addSequential(new Shift());
    	addSequential(new EncoderBackward(2000));
    	Robot.drivetrain.stopMotors();
    	Robot.drivetrain.gyro.reset();
    	addSequential(new Shift());
    	addSequential(new TimeDelay(0.25));
    	Robot.drivetrain.stopMotors();
    	Robot.drivetrain.gyro.reset();
    	addSequential(new GyroTurnRight(20));
    	Robot.drivetrain.stopMotors();
    	addSequential(new GearPiston());
    	addSequential(new PushGear());
    	addSequential(new ShootPIDAuton(0.1));
    	addSequential(new TimeDelay(10.0));
    	addSequential(new ShootPIDAuton(0.1));
    	
      }
}

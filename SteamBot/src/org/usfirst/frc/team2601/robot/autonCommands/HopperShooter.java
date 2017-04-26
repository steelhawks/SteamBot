package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseBack;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
import org.usfirst.frc.team2601.robot.commands.gear.GearPanel;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperShooter extends CommandGroup {

    public HopperShooter() {
    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(4066,4066));
    	addSequential(new Shift());
    	Robot.drivetrain.gyro.reset();
    	addSequential(new GyroTurnRight(80));
    	addSequential(new Shift());
    	addParallel(new GearPanel());
    	addSequential(new EncGyroPlease(1476,1476));
    	addSequential(new TimeDelay(2.0));
    	addParallel(new GearPanel());
    	addSequential(new EncGyroPleaseBack(1476,1476));//
    	addSequential(new Shift());
    	addSequential(new GyroTurnRight(20));
    	addSequential(new Shift());
    	addSequential(new EncGyroPlease(1476,1476));
    	addSequential(new Shift());
    	addSequential(new GyroTurnRight(180));
    	addSequential(new Shift());
    	addSequential(new EncGyroPleaseBack(1000,1000));
    	addSequential(new ShootPIDAuton(0.1));
    	addSequential(new TimeDelay(3.0));
    	addSequential(new ShootPIDAuton(0.1));
    	
    }
}

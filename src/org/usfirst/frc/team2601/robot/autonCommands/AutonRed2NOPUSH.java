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
import org.usfirst.frc.team2601.robot.commands.drivetrain.ReverseDirection;
import org.usfirst.frc.team2601.robot.commands.gear.GearPanel;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeed;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeedAuton;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonRed2NOPUSH extends CommandGroup {

    public AutonRed2NOPUSH() {
    	
    	Robot.shooter.PIDAuton = false;
    	Robot.drivetrain.gyro.zeroYaw();
    	addSequential(new TimeDelay(0.5));
    	//addSequential(new AutonGyroForwardUltra(7));// <-- this is commented out due to inconsistency
    	//System.out.println(Robot.drivetrain.gearUltra.getRangeInches());
    	addSequential(new EncGyroPlease(6601,6480));//3601,3480
    	//addSequential(new EncoderBackward(2700));
    	//Robot.drivetrain.stopMotors();
    	//addSequential(new GearPiston());//swapped
    	//addSequential(new TimeDelay(1.0));
    	//addSequential(new TimeDelay(1.0));
    	//addSequential(new EncoderForward(500));
    	//addSequential(new EncGyroPleaseBack(1500,1900));
    	//addSequential(new GearPiston());//swapped
    	Robot.drivetrain.stopMotors();
    	addSequential(new ReverseDirection());
    	//addSequential(new ShootPIDAuton(4.0));
    	
    }
}

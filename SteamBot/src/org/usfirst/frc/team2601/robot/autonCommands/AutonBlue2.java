package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForwardUltra;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeed;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeedAuton;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonBlue2 extends CommandGroup {

    public AutonBlue2() {
    	
    	addSequential(new EncoderForward(1000));
    	addSequential(new AlignGear());
    	addSequential(new EncoderForward(200));
    	addParallel(new GearPiston());
    	addSequential(new EncoderBackward(200));
    	addSequential(new GyroTurnLeft(20));//
    	addSequential(new AutonGyroForwardUltra(10));
 //  	addSequential(new ShootPIDAuton(2.0));

    	//addParallel(new SetFullSpeed();
    	addSequential(new EncoderBackward(200));
    	addSequential(new GyroTurnRight(20));//
    	addSequential(new EncoderForward(1000));
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

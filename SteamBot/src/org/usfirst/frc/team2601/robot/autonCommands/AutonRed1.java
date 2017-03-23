package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.AutonGyroForwardUltra;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.gear.GearPanel;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonRed1 extends CommandGroup {

    public AutonRed1() {
    	
    	addSequential(new EncoderForward(1000));
    	addSequential(new GyroTurnRight(44.25));
    	addSequential(new AutonGyroForwardUltra(5));
    	//addSequential(new EncoderForward(7000));
/* comment out for debugging - pfw
    	Timer.delay(1.0);
    	addSequential(new GearPiston());
    	addSequential(new PushGear());
    	Timer.delay(1.0);
    	addSequential(new EncoderBackward(1000));
    	addSequential(new GyroTurnLeft(-60));
    	addSequential(new EncoderForward(4000));
    	addSequential(new GyroTurnLeft(-37));
    	addSequential(new EncoderForward(3200));
    	*/
    	//addSequential(new GyroTurnRight(20));
    	//addSequential(new EncoderForward(500));
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

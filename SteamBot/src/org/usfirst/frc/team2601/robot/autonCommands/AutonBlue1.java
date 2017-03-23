package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderForward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonBlue1 extends CommandGroup {

    public AutonBlue1() {
    	
    	addSequential(new EncoderForward(4500));
    	addSequential(new GyroTurnLeft(20));
    	addSequential(new EncoderForward(1000));
    	addSequential(new AlignGear());
    	addSequential(new EncoderForward(200));
    	addSequential(new EncoderBackward(500));
    	addSequential(new GyroTurnRight(20));
    	addSequential(new EncoderForward(4000));
    	addSequential(new GyroTurnLeft(20));
    	addSequential(new EncoderForward(500));
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

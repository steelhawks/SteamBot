package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.commands.EncoderHolder;
import org.usfirst.frc.team2601.robot.commands.TimeDelay;
import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncoderBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrain.GyroTurnRight;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AlignGearTest extends CommandGroup {

    public AlignGearTest() {
    	
    	//addSequential(new AlignGear());
    	//addSequential(new EncoderBackward(10000));
    	//addSequential(new EncoderHolder());
    	/*addSequential(new AlignGear());
    	addSequential(new EncGyroPlease(301,300));
    	addSequential(new GearPiston());//swapped
    	addSequential(new TimeDelay(0.5));
    	addSequential(new PushGear());//swapped
    	addSequential(new TimeDelay(0.5));
    	*/
    	addSequential(new GyroTurnRight(50));
    	addSequential(new GyroTurnLeft(-50));
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

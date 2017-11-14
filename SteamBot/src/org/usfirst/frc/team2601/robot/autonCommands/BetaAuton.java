package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.TimerForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BetaAuton extends CommandGroup {

    public BetaAuton() {
        addSequential(new TimerForward(5.0));
    	
    }
}

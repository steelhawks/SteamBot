package org.usfirst.frc.team2601.robot.autonCommands;

import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPlease;
import org.usfirst.frc.team2601.robot.commands.drivetrain.EncGyroPleaseBack;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveGearLoad extends CommandGroup {

    public MoveGearLoad() {
    	
    	addSequential(new EncGyroPleaseBack(150,150));
           }
}

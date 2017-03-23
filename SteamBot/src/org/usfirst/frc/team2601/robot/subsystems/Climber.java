package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.climber.Climb;
import org.usfirst.frc.team2601.robot.commands.climber.ClimbButton;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	CANTalon climberM = new CANTalon(constants.climbM);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ClimbButton());
    }
    
    //Method to start climb motor
    public void climb(Joystick stick){
    	double speed = Math.abs(stick.getY());
    	
    	climberM.set(-speed);//alpha
   		//climberM.set(speed);/beta
    	
    }
    //Code for climb button
    public void climbButton(){
    	if(climberM.get() == 0){
    		climberM.set(1);
    	}else{
    		climberM.set(0);
    	}
    }
    
}


package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.climber.Climb;
import org.usfirst.frc.team2601.robot.commands.climber.ClimbButton;

import com.ctre.phoenix.MotorControl.CAN.TalonSRX;
import com.ctre.phoenix.MotorControl.SmartMotorController.TalonControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	TalonSRX climberM = new TalonSRX(constants.climbM);
	TalonSRX climberM2 = new TalonSRX(constants.climbM2);
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ClimbButton());
    }
    
    public Climber(){
    	climberM.changeControlMode(TalonControlMode.Follower);
    	climberM.set(climberM2.getDeviceID());
    }
    
    
    //Match motors
    
    //Method to start climb motor
    public void climb(Joystick stick){
    	double speed = Math.abs(stick.getY());
    	
    	climberM.set(-speed);//alpha
   		//climberM.set(speed);/beta
    	matchMotors(climberM, climberM2);
    }
    //Code for climb button
    public void climbButton(){
    	if(climberM2.get() == 0){
    		Robot.compressor.stop();//power management
    		climberM2.set(1);
    		//climberM2.set(1);
    	}else{
    		Robot.compressor.start();//power management
    		//climberM.set(0);
    		climberM2.set(0);
    	}
    }
    public void climbButtonStop(){
		Robot.compressor.start();//power management
    	//climberM.set(0);
    	climberM2.set(0);
    }
    private void matchMotors(TalonSRX leader, TalonSRX follower){
    	follower.set(leader.get());
    }    
}



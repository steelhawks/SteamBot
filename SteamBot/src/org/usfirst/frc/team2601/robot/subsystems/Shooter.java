package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.EncoderHolder;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeed;
import org.usfirst.frc.team2601.robot.commands.shooter.Shoot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants constants = Constants.getInstance();
	
	//Instantiate the motors for shooter and conveyor belt
	public CANTalon shooterMotor = new CANTalon(constants.shooterM);
	public CANTalon shooterMotor2 = new CANTalon(constants.shooterM2);
	//public CANTalon agitatorMotor = new CANTalon(constants.agitateM);	

	boolean shield;
	public boolean PIDAuton;
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//setDefaultCommand(new Shoot());
    	//setDefaultCommand(new EncoderHolder());
    	}
    //PID CODE FOR SHOOTER
    public Shooter(){
    	
		shooterMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	shooterMotor2.set(shooterMotor.getDeviceID());
    	
    	shooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooterMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	shooterMotor.setCloseLoopRampRate(0);
    	shooterMotor.reverseSensor(true);
    	shooterMotor.configEncoderCodesPerRev(12);
    	shooterMotor.configNominalOutputVoltage(+0.0f,  -0.0f);
    	shooterMotor.configPeakOutputVoltage(+12.0f,  -12.0f);
    	shooterMotor.setProfile(0);
    	//shooterMotor.setForwardSoftLimit(+15.0);
    	//shooterMotor.setReverseSoftLimit(-15.0);
    	//shooterMotor.setPosition(0);
    	shooterMotor.setF(0);
    	shooterMotor.setP(32);//32
    	shooterMotor.setI(0.03125);//0.03125
    	shooterMotor.setD(0.012);//0.012
    	
    	shooterMotor.setSafetyEnabled(false);
    	shooterMotor2.setSafetyEnabled(false);
    	
    	LiveWindow.addActuator("robot", "Shooter", shooterMotor);
    	
    	PIDAuton = false;
    	//shooterMotor.enable();
    }
    //Get encoder values
    public double getEncoderVal(){
    	return shooterMotor.getPosition();
    } 
    //Old setSpeed method (NO PID)
    public void setSpeed(double speed){
    	if(constants.alpha == false){	
    		shooterMotor.set(-speed);
    	}else{
    		shooterMotor.set(-speed);
    	}
    }
    //Shooting method (with PID)
    public void shootPID(){
    	PIDAuton = !PIDAuton;
    }
    
    
    public void shootShield(){
    	
    }
  }


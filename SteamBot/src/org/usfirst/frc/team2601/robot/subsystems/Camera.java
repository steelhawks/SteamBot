package org.usfirst.frc.team2601.robot.subsystems;

import java.awt.Frame;
import java.awt.Point;
import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.camera.CamServo;

import com.ctre.CANTalon;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 */

public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Constants constants = Constants.getInstance();
	
	//Camera servo
	Servo servo = new Servo(constants.camServo);
	
	public boolean align = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new CamServo());
    }
    //Moves servo to given position
    public void cameraServo(Joystick stick){
    	double servoVal = -stick.getThrottle();
    	servo.set(servoVal);
    }
    
    //Vision
    public void getGearPosition(){
/*    	NetworkTable server = NetworkTable.getTable("GRIP/myContoursReport");
   
    	align = false;
    	
    	double gearMid = 263;
    	
    	Double[] smth = new Double[2];
		//smth[0] = 1.0;
		Double[] xValues = new Double[2];
		ArrayList<Double> k = new ArrayList<Double>(2);
		//19.5 cm
		try{
    		xValues = server.getNumberArray("centerX", smth);
    		System.out.println("got values");
    	}
    	catch(TableKeyNotDefinedException exp){
    		System.out.println("No Values");
    	}
		
		for(int i = 0; i < xValues.length; i++){
			if((xValues[i] >= k.get(i) + 10) || (xValues[i] <= k.get(i) - 10)){
				k.add(xValues[i]);
			}
		}		
		if(k.size() == 2 && (k.get(0) < 590 && k.get(0) > 320)){
			double mid = (xValues[0] + xValues[1])/2;
			double offset = 10;
			
			SmartDashboard.putBoolean("aligned", align);
			if(mid > gearMid + offset){//turn left
				Robot.drivetrain.frontLeftMotor.set(0.25);
				Robot.drivetrain.frontRightMotor.set(0.25);
	    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
	        	matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
	        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
	        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
	        }
			if(mid < gearMid - offset){//turn right
				Robot.drivetrain.frontLeftMotor.set(-0.25);
				Robot.drivetrain.frontRightMotor.set(-0.25);
	    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
	        	matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
	        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
	        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
			}
			if(mid <= (gearMid + offset) || mid >= (gearMid - offset) || gearMid == mid){
				Robot.drivetrain.frontLeftMotor.set(0);
				Robot.drivetrain.frontRightMotor.set(0);
	    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
	        	matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
	        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
	        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
	        	align = true;
			}
		}
		else{
			System.out.println(k.size());
			Robot.drivetrain.frontLeftMotor.set(0);
			Robot.drivetrain.frontRightMotor.set(0);
    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
        	matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
		}
		*///NULL POINTER EXCEPTION
	}    
    private void matchMotors(CANTalon leader, CANTalon follower){
    	follower.set(leader.get());
    }
    
}

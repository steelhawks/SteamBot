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
//stean
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Constants constants = Constants.getInstance();
	
	//Camera servo
	Servo servo = new Servo(constants.camServo);
	
	public boolean align = false;
	public ArrayList<Double> k = new ArrayList<Double>(2);
	public int i = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new CamServo());
    }
    //Moves servo to given position
    public void cameraServo(Joystick stick){
    	double servoVal = -stick.getThrottle();
    	servo.set(servoVal);
    }
    
    //Vision
    public void getGearPosition(){
    	NetworkTable server = NetworkTable.getTable("GRIP/myContoursReport");
    	
    	
    	Double[] smth = new Double[2];
		smth[0] = 1.0;
		Double[] smth2 = new Double[2];
		smth[0] = 1.0;
		Double[] xValues = new Double[2];
		Double[] height = new Double[xValues.length];
		//19.5 cm
		try{
    		xValues = server.getNumberArray("centerX", smth);
    		//height = server.getNumberArray("height", smth2);
    		System.out.println("got values");
    		
    	}
    	catch(TableKeyNotDefinedException exp){
    		System.out.println("No Values");
    	}
		
			try{
				System.out.println("PAST IF");
		    	double gearMid = 320;
				double mid = Math.abs((xValues[0] + xValues[1])/2);
				double offset = 3;
				
				SmartDashboard.putBoolean("aligned", align);
				if(mid > gearMid + offset){//turn left
					System.out.println("Right");
					Robot.drivetrain.frontLeftMotor.set(-0.15);
					Robot.drivetrain.frontRightMotor.set(-0.15);
		    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
		        	//matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
		        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
		        	//matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
		        	System.out.println(xValues[0]);
		    		System.out.println(xValues[1]);	
				
			    	align = false;
			    }else if(mid < gearMid - offset){//turn right
					System.out.println("Left");
					Robot.drivetrain.frontLeftMotor.set(0.15);
					Robot.drivetrain.frontRightMotor.set(0.15);
		    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
		        	//matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
		        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
		        	//matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
		        	System.out.println(xValues[0]);
		    		System.out.println(xValues[1]);	
				
			    	align = false;
			    }else if(mid >= (gearMid + offset) || mid >= (gearMid - offset) || gearMid == mid){
					Robot.drivetrain.frontLeftMotor.set(0);
					Robot.drivetrain.frontRightMotor.set(0);
		    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
		        	matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
		        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
		        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
		        	System.out.println("stop" + i );
			    	align = true;
				}
				System.out.println(mid);
			}
			catch(TableKeyNotDefinedException exp){
				System.out.println("No values");
			}
			i++;		
	}    
    private void matchMotors(CANTalon leader, CANTalon follower){
    	follower.set(leader.get());
    }
    
}

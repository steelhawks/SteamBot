package org.usfirst.frc.team2601.robot.subsystems;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collections;

import org.opencv.imgproc.Imgproc;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.camera.CamServo;

import com.ctre.CANTalon;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.HttpCamera;
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
	private boolean runProcessing = false;
	public Object imgLock = new Object();
	private double centerXOne = 0.0;
	private double centerYOne = 0.0;
	private double centerXTwo = 0.0;
	private double centerYTwo = 0.0;
	private double centerXAvg = 0.0;
	private double rectangleArea = 0.0;
	
	public Camera(){
		//enableVisionThread();
	}
	
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
		smth[0] = 150.0;
		smth[1] = 500.0;
		Double[] xValues = new Double[2];
		Double[] height = new Double[xValues.length];
		//19.5 cm
		try{
			//int contoursFound = 
			//Imgproc.boundingRect(points)
    		xValues = server.getNumberArray("centerX", smth);
    		//height = server.getNumberArray("height", smth2);
    		System.out.println("got values");
    		
    	}
    	catch(TableKeyNotDefinedException exp){
    		System.out.println("No Values");
    		//throw exp;
    	}
		
			try{
				
				//System.out.println("PAST IF");
		    	double gearMid = 320;
				double mid = Math.abs((xValues[0] + xValues[1])/2);
				double offset = 3;
				
				SmartDashboard.putBoolean("aligned", align);
				if(mid > gearMid + offset){//turn left
					System.out.println("Right");
					/*if(constants.alpha == false){
						Robot.drivetrain.frontLeftMotor.set(0.15);
						Robot.drivetrain.frontRightMotor.set(-0.15);
			    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
			        	//matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
			        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
			        	//matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
					}
					else{*/
						Robot.drivetrain.frontLeftMotor.set(-0.2);//-0.15
						Robot.drivetrain.frontRightMotor.set(-0.2);
			    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
			        	//matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
			        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
			        	//matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
					//}
			    	
			    	align = false;
			    }else if(mid < gearMid - offset){//turn right
					System.out.println("Left");
					/*if(constants.alpha == false){
						Robot.drivetrain.frontLeftMotor.set(-0.15);
						Robot.drivetrain.frontRightMotor.set(0.15);
			    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
			        	//matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
			        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
			        	//matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
					}
					else{*/
						Robot.drivetrain.frontLeftMotor.set(0.2);//0.15
						Robot.drivetrain.frontRightMotor.set(0.2);
			    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
			        	//matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
			        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
			        	//matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
					//}
			       		
			    	align = false;
			    }else if((mid >= (gearMid + offset) || mid >= (gearMid - offset) || gearMid == mid)){
					Robot.drivetrain.frontLeftMotor.set(0);
					Robot.drivetrain.frontRightMotor.set(0);
		    		matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.backLeftMotor);
		        	matchMotors(Robot.drivetrain.frontLeftMotor, Robot.drivetrain.middleLeftMotor);
		        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.backRightMotor);
		        	matchMotors(Robot.drivetrain.frontRightMotor, Robot.drivetrain.middleRightMotor);
		        	System.out.println("aligned" + i );
			    	align = true;
				}
				
			}
			catch(ArrayIndexOutOfBoundsException exp){
				System.out.println("No values being published");
			}
			
			i++;
			Timer.delay(0.05);
	}
    
    /*
    public void enableVisionThread(){
    	GearPipeline pipeline =  new GearPipeline();
    	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    	camera.setResolution(640, 480);
    	CvSink cvSink = CameraServer.getInstance().getVideo();
    	CvSource outputStream = CameraServer.getInstance().putVideo("Stream", 640, 480);		
    	Mat mat = new Mat();
    	
    	runProcessing = true;
    	
    	Thread visionThread = new Thread(() -> {
    		while(!Thread.interrupted()){
    			if(cvSink.grabFrame(mat) == 0){
    				outputStream.notifyError(cvSink.getError());
    				SmartDashboard.putString("Vision State", "Acquisition Error");
    				continue;
    			}
    			if(runProcessing){
    				pipeline.process(mat);
    				int contoursFound = pipeline.getFilterContoursOutput().size();
    				SmartDashboard.putNumber("Contours Found", contoursFound);
    				
    				if(contoursFound >= 2){
    					
    					Rect rectOne = Imgproc.boundingRect(pipeline.getFilterContoursOutput().get(0));
    					Rect rectTwo = Imgproc.boundingRect(pipeline.getFilterContoursOutput().get(1));
    					
    	    			//sort rectangles horizontally
        				Rect leftRect = (rectOne.x<rectTwo.x) ? rectOne : rectTwo;
        				Rect rightRect = (rectTwo.x>rectTwo.x) ? rectOne : rectTwo;
        				rectOne = rightRect;
        				rectTwo = leftRect;
        				
        				//calculate center X and center Y
        				centerXOne = rectOne.x + (rectOne.width/2);
        				centerYOne = rectOne.y + (rectOne.height/2);
        				
        				centerXTwo = rectTwo.x + rectTwo.width/2;
        				centerYTwo = rectTwo.x + rectTwo.height/2;
        				
        				double width = rectTwo.x-(rectOne.x + rectOne.width);
        				double height = rectOne.y-(rectTwo.y+rectTwo.height);
        				
        				rectangleArea = width * height;
        				centerYAvg = (centerYOne + centerYTwo) / 2;
        				centerXAvg = (centerXOne+ centerXTwo) / 2;
        				
        				
        				Imgproc.rectangle(mat, new Point(rectOne.x, rectOne.y), 
        						new Point(rectTwo.x + rectTwo.width, rectTwo.y + rectTwo.height),
        						new Scalar(0, 0, 255), 2);
        				Imgproc.rectangle(mat, new Point(centerXAvg-3, centerYAvg-3),
        						new Point(centerXAvg+3, centerYAvg+3),
        						new Scalar(255,0,0),3);
    					
    					SmartDashboard.putString("Vision State", "Executed Overlay");
    				
    				} 
    				
    				SmartDashboard.putNumber("CenterX", centerXAvg);
    				outputStream.putFrame(mat);

    			} else{
    				outputStream.putFrame(mat);
    			}
    		}
    	});
    	visionThread.setDaemon(true);
    	visionThread.start();
    	
    	
    	
    }
    */
    private void matchMotors(CANTalon leader, CANTalon follower){
    	follower.set(leader.get());
    }
    
}

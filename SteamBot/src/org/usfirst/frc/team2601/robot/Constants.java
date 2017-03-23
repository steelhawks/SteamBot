package org.usfirst.frc.team2601.robot;

//import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Constants {
    private static Constants instance = null;
	
	public static Constants getInstance(){
		if (instance == null){
			instance = new Constants();
		}
		return instance;
	}
	
	public static boolean alpha = true;
	
	//Joysticks
	public final static int dJSPort = 0;
	public final static int oJSPort = 1;
	public final static int randP = 5;
	
	public static Joystick dJS;
	public static Joystick oJS;
	public Joystick randJS;
	
	//Driver Buttons
	public final static int shiftB = 1;
	public final static int gearAlignB = 4;
	public final static int reverseB = 3;
	public final static int frontCamB = 7;
	public final static int backCamB = 8;
	
	//Operator Buttons
	public final static int gearB = 10;
	public final static int stopShootB = 15;
	public final static int shootB = 5;
	public final static int pushGearB = 4;
	public final static int gearPanelB = 6;
	public final static int climbB = 11;
	public final static int hopperB = 8;
	
	
	//drivetrain motors
	public final static int frontLeftM = 0;
	public final static int middleLeftM = 1;
	public final static int backLeftM = 2;
	public final static int frontRightM = 3;
	public final static int middleRightM = 4;
	public final static int backRightM = 5;
		
	//shooter motors
	public final static int shooterM = 6;
	public final static int shooterM2 = 8;
	public final static int agitateM = 9;
	
	//climber motors
	public final static int climbM = 7;
	
	//Shifting Solenoids
	public final static int driveSolOn = 0;
	public final static int driveSolOff = 1;
	public final static int shootShieldSolOn = 2;
	public final static int shootShieldSolOff = 3;
	public final static int gearSolOn = 4;
	public final static int gearSolOff = 5;
	public final static int pushGearOn = 6;
	public final static int pushGearOff = 7;
	
	public final static int hopperOn = 0;
	public final static int hopperOff = 1;
	
	public static boolean shieldUp;
	
	//Sensors
	//LEDS
	public int gearLightOn = 2;
	public int gearLightOff = 3;
	
	//Ultrasonic Values
	public int gearUltraInput = 6;
	public int gearUltraOutput = 7;
	public double gearUltraValue;
	
	public int climbUltraInput = 0;
	public int climbUltraOutput = 1;
	public double climbUltraValue;
	
	public double ultrasonicTolerance = 3;
	public boolean moveUltra;

	//encoders
	public final static int leftEncPortI = 4;
	public final static int leftEncPortII = 5;
	public final static int rightEncPortI = 2;
	public final static int rightEncPortII = 3;
	public final static int shootEncPortI = 8;
	public final static int shootEncPortII = 9;
	
	public boolean moveEnc;
	
	//Gyros
	//public int gyroAnalogPin = 0; -- old gyro
	public boolean gyroTurnRight;
	public boolean gyroTurnLeft;
	
	//Camera
	public final static int frontCam = 0;
	public final static int backCam = 1;
	public final static int camQuality = 60;
	public final static int camServo = 5;
}
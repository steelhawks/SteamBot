package org.usfirst.frc.team2601.robot;

//import com.ni.vision.NIVision.Image;

//import edu.wpi.first.wpilibj.CameraServer;
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
	public static boolean operatorJS = true;//if using joystick for operating
	//Joysticks
	public final static int dJSPort = 0;
	public final static int oJSPort = 1;
	public final static int randP = 5;
	
	public static Joystick dJS;
	public static Joystick oJS;
	public Joystick randJS;
	
	//Driver Buttons
	public final static int shiftB = 1;
	public final static int gearAlignB = 10;
	public final static int reverseB = 3;
	public final static int frontCamB = 7;
	public final static int backCamB = 8;
	public final static int moveToShootB = 11;
	public final static int gearLoadB = 12;
	
	//Operator Buttons
		public static int gearB;// = 10;
		public static int stopShootB;// = 15;
		public static int shootB;// = 5;//NOT USED
		public static int pushGearB;/// = 4;
		public static int gearPanelB; //= 6;
		public static int climbB; //= 5;
		public static int hopperB;//= 8;
	
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
	//public final static int agitateM = 9;
	
	//climber motors
	public final static int climbM = 7;
	public final static int climbM2 = 9;
	//Shifting Solenoids
	public final static int driveSolOn = 0;
	public final static int driveSolOff = 1;
	public final static int shootShieldSolOn = 4;//2
	public final static int shootShieldSolOff = 5;//3
	public final static int gearSolOn = 6;//4
	public final static int gearSolOff = 7;//5
	public final static int pushGearOn = 2;//6
	public final static int pushGearOff = 3;//7
	
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
	public final static int leftEncPortI = 2;
	public final static int leftEncPortII = 3;
	public final static int rightEncPortI = 4;
	public final static int rightEncPortII = 5;
	public final static int shootEncPortI = 8;
	public final static int shootEncPortII = 9;
	
	public boolean ultraBool;
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
public Constants(){
	//Operator Buttons
		if(operatorJS == false){
			gearB = 10;
			stopShootB = 15;
			shootB = 5;//NOT USED
			pushGearB = 4;
			gearPanelB = 6;
			climbB = 5;
			hopperB = 8;
		}else{
			gearB = 11;//OPEN AND CLOSES HOCKEY STICKS
			stopShootB = 25;
			shootB = 25;//NOT USED
			pushGearB = 12;//CONTROLS PUSHER
			gearPanelB = 9;//CONTROLS PANEL
			climbB = 1;//controsls climber
			hopperB = 28;
		}	
	}
}
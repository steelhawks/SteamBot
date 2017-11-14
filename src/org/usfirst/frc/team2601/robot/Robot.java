
package org.usfirst.frc.team2601.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2601.robot.autonCommands.AlignGearTest;
import org.usfirst.frc.team2601.robot.autonCommands.AutonBlue1;
import org.usfirst.frc.team2601.robot.autonCommands.AutonBlue3;
import org.usfirst.frc.team2601.robot.autonCommands.AutonBlue3NoHopp;
import org.usfirst.frc.team2601.robot.autonCommands.AutonBlue2;
import org.usfirst.frc.team2601.robot.autonCommands.AutonRed1;
import org.usfirst.frc.team2601.robot.autonCommands.AutonRed3;
import org.usfirst.frc.team2601.robot.autonCommands.AutonRed3NoHopp;
import org.usfirst.frc.team2601.robot.autonCommands.HopperShooter;
import org.usfirst.frc.team2601.robot.autonCommands.Pos1;
import org.usfirst.frc.team2601.robot.autonCommands.Pos1HoppBlu;
import org.usfirst.frc.team2601.robot.autonCommands.Pos2;
import org.usfirst.frc.team2601.robot.autonCommands.Pos3;
import org.usfirst.frc.team2601.robot.autonCommands.Pos3HoppRed;
import org.usfirst.frc.team2601.robot.commands.shooter.ShootPIDAuton;
import org.usfirst.frc.team2601.robot.autonCommands.AutonRed3;
import org.usfirst.frc.team2601.robot.autonCommands.AutonRed2;
import org.usfirst.frc.team2601.robot.autonCommands.AutonRed2NOPUSH;
//import org.usfirst.frc.team2601.robot.subsystems.Camera;
import org.usfirst.frc.team2601.robot.subsystems.Climber;
import org.usfirst.frc.team2601.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2601.robot.subsystems.Gear;
import org.usfirst.frc.team2601.robot.subsystems.Shooter;

import com.ctre.phoenix.MotorControl.CAN.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;	

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
//C:\Users\Steel Hawks 2\Desktop\lt
public class Robot extends IterativeRobot {
	
	//Instantiate Subsystems
	public static final Drivetrain drivetrain = new Drivetrain();
	public static OI oi;
	//public static final Camera camera = new Camera();
	public static final Gear gear = new Gear();
	public static final Shooter shooter = new Shooter();
	public static final Climber climber = new Climber();
	public static final Compressor compressor = new Compressor();

	//Declare Command based structure classes
    Command autonomousCommand;
    SendableChooser<CommandGroup> chooser;
    
	TalonSRX shooterMotor = new TalonSRX(6);//8 beta
	TalonSRX shooterMotor2 = new TalonSRX(8);//6 beta
	TalonSRX agitatorMotor = new TalonSRX(10);
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	shooterMotor2.changeControlMode(TalonSRX.TalonControlMode.Follower);
    	shooterMotor2.set(shooterMotor.getDeviceID());
    	
    	shooterMotor.changeControlMode(TalonSRX.TalonControlMode.Speed);
    	shooterMotor.setFeedbackDevice(TalonSRX.FeedbackDevice.QuadEncoder);
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
    	shooterMotor.setP(16);//32
    	shooterMotor.setI(0.03125);//0.03125
    	shooterMotor.setD(0.012);//0.012
    	
    	
    	shooterMotor.set(0.0);
    	shooterMotor.setSafetyEnabled(false);
    	shooterMotor2.setSafetyEnabled(false);

    	
        //System.out.println("hiii");
    	oi = new OI();
        chooser = new SendableChooser();
        //Code below commented out due to error messages with the camera stream.
        //SmartDashboard smartDashboard = new SmartDashboard();
        
     
        //UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
        //UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
        //MjpegServer s = CameraServer.getInstance().addServer("gripStream");
        //s.setSource(cam0);
        //cam0.setResolution(640, 480); // look into lowering       
        //cam0.setBrightness(100);
        //cam0.setFPS(14);
        //cam1.setResolution(640, 480);    
        //System.out.println("hello");
        
        
        //Autonomous
        //chooser.addDefault("AlignGearTest", new AlignGearTest());
        chooser.addObject("PosOne", new Pos1());
        chooser.addObject("PosTwo", new Pos2());
        chooser.addObject("PosThree", new Pos3());
        /*chooser.addObject("BluOne", new AutonBlue1());
        chooser.addObject("BluTwo", new AutonBlue2());
        chooser.addObject("BluThree", new AutonBlue3());
        chooser.addObject("RedThreeNoHopper", new AutonRed3NoHopp());
        chooser.addObject("BluThreeNoHopper", new AutonBlue3NoHopp());*/
        //chooser.addObject("Pos3HoppRed", new Pos3NoHoppRed());
        //chooser.addObject("Pos1HoppBlue", new Pos1NoHoppBlu());
        chooser.addObject("HopperShooter", new HopperShooter());
        SmartDashboard.putData("Auto mode", chooser);
        System.out.println("hi");
    }	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        //autonomousCommand = (Command) chooser.getSelected();
        //autonomousCommand = new AutonRed1();
    	//autonomousCommand = new AutonRed2();
    	//autonomousCommand = new AutonRed3();
    	//autonomousCommand = new AutonBlue1();
        //autonomousCommand = new AutonBlue3();
    	//autonomousCommand = new AutonRed2NOPUSH();
    	//autonomousCommand = new AlignGearTest();
    	//autonomousCommand = new HopperShooter();
    	//autonomousCommand = new Pos1();
    	autonomousCommand = new Pos2();
    	//autonomousCommand = new Pos3();
    	autonomousCommand.start();
        //SmartDashboard.putData("Auto mode", chooser);
    	shooterMotor.setF(0);
    	shooterMotor.setP(16);//32
    	shooterMotor.setI(0.03125);//0.03125
    	shooterMotor.setD(0.012);//0.012
    	
    	//Robot.shooter.PIDAuton = false;
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        if(Robot.shooter.PIDAuton == true) {
			System.out.println(shooterMotor.getClosedLoopError());
			shooterMotor.set(-1675);//-1675
        }
		else{
			shooterMotor.set(0);
		}
		if (Robot.shooter.PIDAuton == false) {
			agitatorMotor.set(0);
			shooterMotor.set(0);
		}
		
		int err = shooterMotor.getClosedLoopError();
		if(Robot.shooter.PIDAuton == true && err < 2 && err > -3) {
			System.out.println("turn on conveyor " + err);
			agitatorMotor.set(-0.85);
		}else{
			//agitatorMotor.set(0.1);
		} //commented out because there is no agitator motor for now	

    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.drivetrain.gyro.reset();
        Robot.drivetrain.gyro.zeroYaw();
     }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
       
        SmartDashboard.putNumber("ir1", Robot.gear.ir1.getValue());
 	   	SmartDashboard.putNumber("ir2", Robot.gear.ir2.getValue());
     
        
        if (Constants.oJS.getRawButton(1)) {
        	//agitatorMotor.set(1);
        	System.out.println(shooterMotor.getClosedLoopError());
			shooterMotor.set(-1675);
			//agitatorMotor.set(-0.85);
		}
		else {
			shooterMotor.set(0);
		}
		if (Constants.oJS.getRawButton(3)) {
			agitatorMotor.set(-0.85);
		}else{
			agitatorMotor.set(0);
		}
		
//		Timer.delay(0.005); // wait for a motor update time
		
		int err = shooterMotor.getClosedLoopError();
		if (Constants.oJS.getRawButton(1) && err < 2 && err > -3) {
			System.out.println("turn on conveyor " + err);
			Timer.delay(0.5);
			//agitatorMotor.set(-0.85);
		}else{
			//agitatorMotor.set(0.1);;
		} 


    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

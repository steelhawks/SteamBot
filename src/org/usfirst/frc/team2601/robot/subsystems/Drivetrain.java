package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ArcadeDrive;

import com.ctre.phoenix.MotorControl.GroupMotorControllers;
import com.ctre.phoenix.MotorControl.SmartMotorController.TalonControlMode;
import com.ctre.phoenix.MotorControl.CAN.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

	Constants constants = Constants.getInstance();

	boolean gear;// tells you when the drivetrain is on high or low gear true ->
					// low gear false -> high gear
	public static int reverse = 1;

	// Instantiate Drive Motor Controllers
	public MyTalonSRX frontLeftMotor = new MyTalonSRX(constants.frontLeftM);
	public MyTalonSRX middleLeftMotor = new MyTalonSRX(constants.middleLeftM);
	public MyTalonSRX backLeftMotor = new MyTalonSRX(constants.backLeftM);
	public MyTalonSRX frontRightMotor = new MyTalonSRX(constants.frontRightM);
	public MyTalonSRX middleRightMotor = new MyTalonSRX(constants.middleRightM);
	public MyTalonSRX backRightMotor = new MyTalonSRX(constants.backRightM);
	SpeedControllerGroup left = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
	SpeedControllerGroup right = new SpeedControllerGroup(frontRightMotor, backRightMotor);

	// DoubleSolenoid rightShift = new
	// DoubleSolenoid(constants.shootShieldSolOn,constants.shootShieldSolOff);
	public DoubleSolenoid driveShift = new DoubleSolenoid(constants.driveSolOn, constants.driveSolOff);
	// Declare Robot drive
	DifferentialDrive drive = new DifferentialDrive(left, right);
	double leftMotorC;
	double rightMotorC;
	
	
	// Ultrasonics
	public Ultrasonic gearUltra = new Ultrasonic(7, 6);
	double gearUltraValue;
	//public LidarLITE l = new LidarLITE(2,90);
	
	Ultrasonic climbUltra = new Ultrasonic(constants.climbUltraOutput, constants.climbUltraInput);
	double climbUltraValue;

	// Gyros
	//public AnalogGyro gyro = new AnalogGyro(0); //- KoP
	// Gyro (Replaced)
	public AHRS gyro = new AHRS(SPI.Port.kMXP);
	double gyroRate;
	double gyroAngle;
	double kP = -0.011875;//-alpha//-0.095
	double kPb = 0.07;//-alpha;// back
	// more increase kp more left
	// more decrease kp more right

	// Encoders
	public Encoder leftEnc = new Encoder(constants.leftEncPortI, constants.leftEncPortII, false, EncodingType.k4X);
	double leftEncValue;
	double leftEncDist;

	public Encoder rightEnc = new Encoder(constants.rightEncPortI, constants.rightEncPortII, false, EncodingType.k4X);
	double rightEncValue;
	double rightEncDist;

	// PID code for Drivetrain
	PIDController leftSide = new PIDController(0.1, 0.001, 0.0, leftEnc, frontLeftMotor);
	PIDController rightSide = new PIDController(0.1, 0.001, 0.0, rightEnc, frontRightMotor);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Drivetrain() {
		// rightShift.set(DoubleSolenoid.Value.kForward);
		// Shifter
		driveShift.set(DoubleSolenoid.Value.kReverse);
		/*
		 * backLeftMotor.changeControlMode(TalonSRX.TalonControlMode.Follower);
		 * backLeftMotor.set(frontLeftMotor.getDeviceID());
		 * 
		 * middleLeftMotor.changeControlMode(TalonSRX.TalonControlMode.Follower)
		 * ; middleLeftMotor.set(frontLeftMotor.getDeviceID());
		 * 
		 * backRightMotor.changeControlMode(TalonSRX.TalonControlMode.Follower);
		 * backRightMotor.set(frontRightMotor.getDeviceID());
		 * 
		 * middleRightMotor.changeControlMode(TalonSRX.TalonControlMode.Follower
		 * ); middleRightMotor.set(frontRightMotor.getDeviceID());
		 */

		// ready Encoders
		leftEnc.reset();
		rightEnc.reset();

		// ready Ultrasonics
		gearUltra.setEnabled(true);
		gearUltra.setAutomaticMode(true);

		climbUltra.setEnabled(true);
		climbUltra.setAutomaticMode(true);

		constants.ultraBool = false;

		// ready Gyros
		// gyro.initGyro(); this and calibrate are taken out to use the new gyro
		gyro.reset();
		gyro.zeroYaw(); 

		// SmartDashboard.putDouble("kP", kP);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// Sets drive type to arcade
		setDefaultCommand(new ArcadeDrive());
	}

	// Matches drive motors
	private void matchMotors(TalonSRX leader, TalonSRX follower) {
		follower.set(leader.get());
	}

	public void arcadeDrive(Joystick stick) {
		double move = stick.getY();
		double rotate = stick.getTwist();
		drive.arcadeDrive(reverse * move, rotate);
		/*
		 * frontLeftMotor.changeControlMode(TalonControlMode.Voltage);
		 * frontLeftMotor.setVoltageCompensationRampRate(24);
		 * 
		 * frontRightMotor.changeControlMode(TalonControlMode.Voltage);
		 * frontRightMotor.setVoltageCompensationRampRate(24);
		 */
		matchMotors(frontLeftMotor, backLeftMotor);
		matchMotors(frontLeftMotor, middleLeftMotor);
		matchMotors(frontRightMotor, backRightMotor);
		matchMotors(frontRightMotor, middleRightMotor);

		// Output Motor voltage to SD
		SmartDashboard.putNumber("frontLeftMotorVoltage", frontLeftMotor.getOutputCurrent());
		SmartDashboard.putNumber("frontRightMotorVoltage", frontRightMotor.getOutputCurrent());

		
		gyroAngle = gyro.getAngle();
		SmartDashboard.putNumber("GyroAngle", gyroAngle);
		SmartDashboard.putNumber("GetBoardAxis", gyro.getBoardYawAxis().board_axis.getValue());

		// Output Enc values to SD
		leftEncValue = leftEnc.getRate();
		SmartDashboard.putNumber("LeftEncValue", leftEncValue);

		leftEncDist = leftEnc.getDistance();
		SmartDashboard.putNumber("LeftEncDist", leftEncDist);

		rightEncValue = rightEnc.getRate();
		SmartDashboard.putNumber("RightEncValue", rightEncValue);

		rightEncDist = rightEnc.getDistance();
		SmartDashboard.putNumber("RightEncDist", rightEncDist);

		// Output shift value to SD
		SmartDashboard.putBoolean("High Gear", gear);
	}
	
	// Method to shift
	public void shiftGears() {
		if (driveShift.get() == DoubleSolenoid.Value.kForward) {
			driveShift.set(DoubleSolenoid.Value.kReverse);
			// matchSolenoids();
			gear = false; // high gear
		} else {
			driveShift.set(DoubleSolenoid.Value.kForward);
			// matchSolenoids();
			gear = true; // low gear
		}
		matchSolenoids();
	}

	private void matchSolenoids() {
		// driveShift.set(rightShift.get());
	}

	public void moveForwardTimer() {
		frontLeftMotor.set(1.0);
		frontRightMotor.set(-1.0);
		
		matchMotors(frontLeftMotor,backLeftMotor);
		matchMotors(frontLeftMotor, middleLeftMotor);
		matchMotors(frontRightMotor,backRightMotor);
		matchMotors(frontRightMotor, middleRightMotor);
	}
	
	public void EncGyroForward(double leftDist, double rightDist) {
		gyroAngle = gyro.getAngle();

		leftEncDist = leftEnc.getDistance();
		rightEncDist = rightEnc.getDistance();
		System.out.println("Straight");
		System.out.println(gyroAngle);
		// double newK = SmartDashboard.getDouble("kP");
		drive.tankDrive(-0.75, gyroAngle * kP);// *newkP);//ALPHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		//drive.drive(0.75, gyroAngle * kP);
		matchMotors(frontLeftMotor, backLeftMotor);
		// matchMotors(frontLeftMotor, middleLeftMotor);
		matchMotors(frontRightMotor, backRightMotor);
		// matchMotors(frontRightMotor, middleRightMotor);

		if (leftEncDist > leftDist && -rightEncDist > rightDist) {
			constants.ultraBool = true;
		}
		if (leftEncDist <= leftDist && -rightEncDist <= rightDist) {
			constants.ultraBool = false;
		}

	}
	public void EncGyroForwardSlow(double leftDist, double rightDist) {
		gyroAngle = gyro.getAngle();
		
		leftEncDist = leftEnc.getDistance();
		rightEncDist = rightEnc.getDistance();
		//System.out.println("Straight");
		//System.out.println(gyroAngle);
		// double newK = SmartDashboard.getDouble("kP");
		drive.tankDrive(-0.35, gyroAngle * kP);// *newkP);//ALPHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		//drive.drive(0.75, gyroAngle * kP);
		matchMotors(frontLeftMotor, backLeftMotor);
		// matchMotors(frontLeftMotor, middleLeftMotor);
		matchMotors(frontRightMotor, backRightMotor);
		// matchMotors(frontRightMotor, middleRightMotor);

		if (leftEncDist > leftDist && -rightEncDist > rightDist) {
			constants.ultraBool = true;
		}
		if (leftEncDist <= leftDist && -rightEncDist <= rightDist) {
			constants.ultraBool = false;
		}

	}

	public void EncGyroBackward(double leftDist, double rightDist) {
		gyroAngle = gyro.getAngle();

		leftEncDist = leftEnc.getDistance();
		rightEncDist = rightEnc.getDistance();

		drive.tankDrive(0.5, gyroAngle * kPb);//ALPHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		//drive.drive(-0.5, gyroAngle*kPb);
		matchMotors(frontLeftMotor, backLeftMotor);
		// matchMotors(frontLeftMotor, middleLeftMotor);
		matchMotors(frontRightMotor, backRightMotor);
		// matchMotors(frontRightMotor, middleRightMotor);

		if (-leftEncDist + 10 > leftDist && rightEncDist + 10 > rightDist) {
			constants.ultraBool = true;
		}
		if (-leftEncDist + 10 <= leftDist && rightEncDist + 10 <= rightDist) {
			constants.ultraBool = false;
		}

	}

	public void forwardGyroUltra(double distance) {
		gyroAngle = gyro.getAngle();
		gearUltraValue = gearUltra.getRangeInches();
		drive.tankDrive(-0.5, gyroAngle * kP);
		matchMotors(frontLeftMotor, backLeftMotor);
		matchMotors(frontLeftMotor, middleLeftMotor);
		matchMotors(frontRightMotor, backRightMotor);
		matchMotors(frontRightMotor, middleRightMotor);

		if (gearUltraValue <= distance + constants.ultrasonicTolerance) {
			constants.moveUltra = true;
		}
		if (gearUltraValue > distance + constants.ultrasonicTolerance) {
			constants.moveUltra = false;
		}
	}

	public void forwardEncoder(double distance) {

		// leftEnc.reset();
		// rightEnc.reset();

		leftEncDist = leftEnc.getDistance();
		rightEncDist = rightEnc.getDistance();

		double leftEncDistUse = leftEncDist - distance;
		double rightEncDistUse = rightEncDist + distance;

		leftEncDist = leftEnc.getDistance();
		SmartDashboard.putNumber("LeftEncDist", leftEncDist);

		rightEncDist = rightEnc.getDistance();
		SmartDashboard.putNumber("RightEncDist", rightEncDist);

		if (-leftEncDist + 50 < distance && rightEncDist + 50 < distance) {
			frontLeftMotor.set(0.5);
			frontRightMotor.set(-0.5);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.moveEnc = false;
		}
		if (-leftEncDist + 50 >= distance && rightEncDist + 50 >= distance) {
			frontLeftMotor.set(0);
			frontRightMotor.set(0);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.moveEnc = true;
		}

		if (gearUltraValue <= distance + constants.ultrasonicTolerance) {
			constants.moveUltra = true;
		}
		if (gearUltraValue > distance + constants.ultrasonicTolerance) {
			constants.moveUltra = false;
		}
	}

	public void forwardEncoder(double lDistance, double rDistance) {

		// leftEnc.reset();
		// rightEnc.reset();

		leftEncDist = leftEnc.getDistance();
		rightEncDist = rightEnc.getDistance();

		leftEncDist = leftEnc.getDistance();
		SmartDashboard.putNumber("LeftEncDist", leftEncDist);

		rightEncDist = rightEnc.getDistance();
		SmartDashboard.putNumber("RightEncDist", rightEncDist);

		if (-leftEncDist + 50 < lDistance && rightEncDist + 50 < rDistance) {
			frontLeftMotor.set(-0.75);
			frontRightMotor.set(0.75);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.moveEnc = false;
		}
		if (-leftEncDist + 50 >= lDistance && rightEncDist + 50 >= rDistance) {
			frontLeftMotor.set(0);
			frontRightMotor.set(0);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.moveEnc = true;
		}
	}

	public void backwardEncoder(double distance) {

		leftEncDist = leftEnc.getDistance();
		rightEncDist = rightEnc.getDistance();

		leftEncDist = leftEnc.getDistance();
		SmartDashboard.putNumber("LeftEncDist", leftEncDist);

		rightEncDist = rightEnc.getDistance();
		SmartDashboard.putNumber("RightEncDist", rightEncDist);

		if (leftEncDist + 50 < distance && -rightEncDist + 50 < distance) {
			frontLeftMotor.set(0.5);
			frontRightMotor.set(-0.5);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.moveEnc = false;
		}
		if (leftEncDist + 50 >= distance && -rightEncDist + 50 >= distance) {
			frontLeftMotor.set(0);
			frontRightMotor.set(0);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.moveEnc = true;
		}
	}

	public void gyroLock(double desiredAngle) {
		double currentAngle = gyro.getAngle();

		if (desiredAngle < (currentAngle * .85)) {//.95
			double displacement = desiredAngle - currentAngle;
			displacement /= 75.0;
			frontLeftMotor.set(displacement);
			frontRightMotor.set(displacement);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.gyroTurnRight = false;
		} else if (desiredAngle > (currentAngle * 1.15)) {//1.05
			double displacement = currentAngle - desiredAngle;
			displacement /= 75.0;
			frontLeftMotor.set(displacement);
			frontRightMotor.set(displacement);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.gyroTurnRight = false;
		} else{
			frontLeftMotor.set(0);
			frontRightMotor.set(0);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.gyroTurnRight = true;
		}
	}

	// Turns robot based on gyro angle
	public void GyroTurnRight(double angle) {
		gyroAngle = gyro.getAngle() + 9;

		double gyroAngleUse = gyroAngle + angle;

		if (gyroAngle >= angle) {
			frontLeftMotor.set(0);
			frontRightMotor.set(0);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.gyroTurnRight = true;
		}
		if (gyroAngle < angle) {
			// else{
			frontLeftMotor.set(-0.5);
			frontRightMotor.set(-0.5);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			// System.out.println("hi");
			constants.gyroTurnRight = false;
		}

	}

	public void GyroTurnLeft(double angle) {
		gyroAngle = gyro.getAngle() - 9;

		if (gyroAngle <= angle) {
			frontLeftMotor.set(0);
			frontRightMotor.set(0);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.gyroTurnLeft = true;
		}
		if (gyroAngle > angle) {
			// else{
			frontLeftMotor.set(0.5);
			frontRightMotor.set(0.5);
			matchMotors(frontLeftMotor, backLeftMotor);
			matchMotors(frontLeftMotor, middleLeftMotor);
			matchMotors(frontRightMotor, backRightMotor);
			matchMotors(frontRightMotor, middleRightMotor);
			constants.gyroTurnLeft = false;
		}
	}

	// Stop drivetrain
	public void stopMotors() {
		frontLeftMotor.set(0);
		frontRightMotor.set(0);
		backLeftMotor.set(0);
		middleLeftMotor.set(0);
		backRightMotor.set(0);
		middleRightMotor.set(0);
	}

	public void forwardUltra(double distance) {

		gearUltraValue = gearUltra.getRangeInches();
		frontLeftMotor.set(0.75);
		frontRightMotor.set(0.75);
		matchMotors(frontLeftMotor, backLeftMotor);
		matchMotors(frontLeftMotor, middleLeftMotor);
		matchMotors(frontRightMotor, backRightMotor);
		matchMotors(frontRightMotor, middleRightMotor);

		if (gearUltraValue <= distance + constants.ultrasonicTolerance) {
			constants.moveUltra = true;
		}
		if (gearUltraValue > distance + constants.ultrasonicTolerance) {
			constants.moveUltra = false;
		}
	}
}
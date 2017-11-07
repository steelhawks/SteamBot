package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.autonCommands.MoveGearLoad;
import org.usfirst.frc.team2601.robot.autonCommands.MoveToShoot;
import org.usfirst.frc.team2601.robot.autonCommands.Pos1;
import org.usfirst.frc.team2601.robot.autonCommands.Pos2;
import org.usfirst.frc.team2601.robot.autonCommands.Pos3;
import org.usfirst.frc.team2601.robot.commands.EncoderHolder;
import org.usfirst.frc.team2601.robot.commands.camera.AlignGear;
import org.usfirst.frc.team2601.robot.commands.climber.ClimbButton;
import org.usfirst.frc.team2601.robot.commands.climber.ClimbButtonStop;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ArcadeDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrain.ReverseDirection;
import org.usfirst.frc.team2601.robot.commands.drivetrain.Shift;
import org.usfirst.frc.team2601.robot.commands.gear.GearPanel;
import org.usfirst.frc.team2601.robot.commands.gear.GearPiston;
import org.usfirst.frc.team2601.robot.commands.gear.HopperPiston;
import org.usfirst.frc.team2601.robot.commands.gear.PushGear;
import org.usfirst.frc.team2601.robot.commands.shooter.AgitatorMotorBoolean;
import org.usfirst.frc.team2601.robot.commands.shooter.IntakeSpeed;
import org.usfirst.frc.team2601.robot.commands.shooter.SetFullSpeed;
import org.usfirst.frc.team2601.robot.commands.shooter.StopShoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	

	public OI(){
		//Get constants
		Constants constants = Constants.getInstance();
		
		//Instantiate Joysticks and Buttonboard
		constants.dJS = new Joystick(constants.dJSPort);
		constants.oJS = new Joystick(constants.oJSPort);
		constants.randJS = new Joystick(constants.randP);
		
		//Driver Controls
    	Button moveToShoot = new JoystickButton(constants.dJS, constants.moveToShootB);
    	moveToShoot.whenPressed(new MoveToShoot());
		
		Button shift = new JoystickButton(constants.dJS, constants.shiftB);
    	shift.whenPressed(new Shift());		
		
    	Button gearAlign = new JoystickButton(constants.dJS, constants.gearAlignB);
    	gearAlign.whenPressed(new AlignGear());
    	
    	Button reverse = new JoystickButton(constants.dJS, constants.reverseB);
    	reverse.whenPressed(new ReverseDirection());
    	
    	Button gearLoad = new JoystickButton (constants.dJS, constants.gearLoadB);
    	gearLoad.whenPressed(new MoveGearLoad());
    	    	
    	//Operator Controls
    	Button gear = new JoystickButton(constants.oJS, constants.gearB);
    	gear.whenPressed(new GearPiston());
    	
    	Button shootSpeed = new JoystickButton(constants.oJS, constants.shootB);
    	//shootSpeed.whenPressed(new EncoderHolder());
    	shootSpeed.whileHeld(new EncoderHolder());
    	
    	Button agitator = new JoystickButton(constants.oJS, constants.shootB);
    	//agitator.whenPressed(new AgitatorMotorBoolean());
    	agitator.whileHeld(new AgitatorMotorBoolean());
    	
    	Button stopShoot = new JoystickButton(constants.oJS, constants.stopShootB);
    	stopShoot.whenPressed(new StopShoot());
    	
    	Button pushGear = new JoystickButton(constants.oJS, constants.pushGearB);
    	pushGear.whenPressed(new PushGear());
    	
    	Button hopper = new JoystickButton(constants.oJS, constants.hopperB);
    	hopper.whenPressed(new HopperPiston());

    	Button gearPanel = new JoystickButton(constants.oJS, constants.gearPanelB);
    	gearPanel.whenPressed(new GearPanel());
    	
    	Button climb = new JoystickButton(constants.oJS, constants.climbB);
    	//climb.whenPressed(new ClimbButton());
    	//climb.whileHeld(new ClimbButton());
    	//climb.whileActive(new ClimbButton());
    	climb.whenActive(new ClimbButton());
    	climb.whenInactive(new ClimbButtonStop());
    	
    	/*Button pos1 = new JoystickButton(constants.dJS, 7);
    	pos1.whenPressed(new Pos1());
    	
    	Button pos2 = new JoystickButton(constants.dJS, 8);
    	pos2.whenPressed(new Pos2());
    	
    	Button pos3 = new JoystickButton(constants.dJS, 9);
    	pos3.whenPressed(new Pos3());
    	 */   	
    	
    	}
	
}


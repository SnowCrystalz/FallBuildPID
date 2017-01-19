package org.usfirst.frc.team1245.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	public static CANTalon frontLeft;
	public static CANTalon frontRight;
	public static CANTalon rearLeft;
	public static CANTalon rearRight;
	public static RobotDrive robotDrive;
	
	public static final int buttonDriveForward = 1;
	
	public static final double translationalDeadZone = 0.1;
	
	public static void init() {
		frontLeft = new CANTalon(0);
		frontRight = new CANTalon(1);
		rearLeft = new CANTalon(2);
		rearRight = new CANTalon(3);
		
		frontLeft.changeControlMode(ControlMode.Position);
		frontRight.changeControlMode(ControlMode.Position);
		rearLeft.changeControlMode(ControlMode.Position);
		rearRight.changeControlMode(ControlMode.Position);
		
		frontLeft.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		frontRight.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rearLeft.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rearRight.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		
		frontLeft.setPID(0.1, 0.0, 0.0);
		frontRight.setPID(0.1, 0.0, 0.0);
		rearLeft.setPID(0.1, 0.0, 0.0);
		rearRight.setPID(0.1, 0.0, 0.0);
		
		robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
	}
}

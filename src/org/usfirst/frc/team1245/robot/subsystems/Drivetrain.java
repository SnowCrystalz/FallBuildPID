package org.usfirst.frc.team1245.robot.subsystems;

import org.usfirst.frc.team1245.robot.Robot;
import org.usfirst.frc.team1245.robot.RobotMap;
import org.usfirst.frc.team1245.robot.commands.Drive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public PIDController dPID;
	public static double dKp;
	public static double dKi;
	public static double dKd;
	public static boolean dPIDOutputEnabled = false;
	
	public Drivetrain() {
		initDPID();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
    private class AvgEncoder implements PIDSource {

		@Override
		public double pidGet() {
			double average = (RobotMap.frontLeft.get() + RobotMap.frontRight.get() + RobotMap.rearLeft.get() + RobotMap.rearRight.get())/4;
			SmartDashboard.putNumber("AvgEnc", average);
			return average;
		}
    	
    }
    
    private void initDPID() {
    	dPID = new PIDController(dKp, dKi, dKd, new AvgEncoder(), new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				RobotMap.robotDrive.tankDrive(output, output);
			}
		});
    	dPID.setOutputRange(-.8, .8);
    	dPID.setAbsoluteTolerance(5);
    	dPID.setSetpoint(0);
    	LiveWindow.addActuator("Drivetrain", "dPID", dPID);
    }
}


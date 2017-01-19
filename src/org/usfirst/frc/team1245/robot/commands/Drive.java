package org.usfirst.frc.team1245.robot.commands;

import org.usfirst.frc.team1245.robot.Robot;
import org.usfirst.frc.team1245.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("value", Robot.drivetrain.dPID.get());
        SmartDashboard.putNumber("Joystick", Robot.oi.driverJoystick.getX());
    	double x = Robot.oi.driverJoystick.getX(); //Rbot.oi.deadZone(, RobotMap.translationalDeadZone);
    	double y = Robot.oi.driverJoystick.getY(); //Robot.oi.deadZone(Robot.oi.driverJoystick.getY(), RobotMap.translationalDeadZone);
    	if (Robot.oi.driverJoystick.getTrigger()) {
    		x /= 2;
    		y /= 2;
    	}
    	RobotMap.robotDrive.tankDrive(x, y);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team1245.robot.commands;

import org.usfirst.frc.team1245.robot.Robot;
import org.usfirst.frc.team1245.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {

	double distance;
	
    public DriveDistance(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	distance = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.dPIDOutputEnabled = true;
    	Robot.drivetrain.dPID.setSetpoint(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.robotDrive.tankDrive(Robot.drivetrain.dPID.get(), Robot.drivetrain.dPID.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("dPID Error", Robot.drivetrain.dPID.getError());
        SmartDashboard.putBoolean("is dPID ontarget", Robot.drivetrain.dPID.onTarget());
    	return (Math.abs(Robot.drivetrain.dPID.getError()) < 2);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.dPIDOutputEnabled = false;
        Robot.drivetrain.dPID.reset();
        RobotMap.robotDrive.tankDrive(0, 0);
        SmartDashboard.putNumber("dPID Error", Robot.drivetrain.dPID.getError());
        SmartDashboard.putBoolean("is dPID ontarget", Robot.drivetrain.dPID.onTarget());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

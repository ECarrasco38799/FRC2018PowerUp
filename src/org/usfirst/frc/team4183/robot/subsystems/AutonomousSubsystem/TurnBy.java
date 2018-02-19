package org.usfirst.frc.team4183.robot.subsystems.AutonomousSubsystem;

import org.usfirst.frc.team4183.robot.Robot;

import org.usfirst.frc.team4183.robot.subsystems.AutonomousSubsystem.AutonomousSubsystem.AutoTaskDescriptor;
import org.usfirst.frc.team4183.robot.subsystems.AutonomousSubsystem.AutonomousSubsystem.AutoTasks;

import org.usfirst.frc.team4183.utils.CommandUtils;

import edu.wpi.first.wpilibj.command.Command;



public class TurnBy extends Command 
{
	private double timeout_sec;
	
	public TurnBy( double angle_deg, double aTimeout_sec) 
	{		
		requires( Robot.autonomousSubsystem);
		
		AutonomousSubsystem.setDriveTask(AutoTasks.TURN_BY, angle_deg);
		timeout_sec = aTimeout_sec;
	}

	@Override
	protected void initialize() 
	{
		// Nothing else to initialize
	}

	@Override
	protected void execute() 
	{
		// Nothing else to execute
	}
	
	
	@Override
	protected boolean isFinished() 
	{
		// TODO: Determine if something more interesting to do
		// has popped up (e.g., re-planned goes back to idle
		// for the decision of what to do next.
		boolean replan = false;
		
		// Wait for standby or timeout
		AutoTaskDescriptor currentDriveTask = AutonomousSubsystem.getDriveTask();
		boolean timeout = (timeSinceInitialized() > timeout_sec);
		
		// If timeout, then force subsystem to stop task; if standby it already stopped
		AutonomousSubsystem.setDriveTaskComplete(true);
		
		if (replan || timeout || (currentDriveTask.task == AutoTasks.STANDBY))
		{
			// Go back to idle to decide what is next, if anything
			return CommandUtils.stateChange(this, new Idle());
		}
			
		return false;
	}
	
	@Override
	protected void end() 
	{
	}
	

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author sgoldman
 */
public class PIDalignment extends CommandBase{
    private PIDController pidController;
    private int iterations;
    private double myAngle;
    private boolean cancelled = false;
    
    public PIDalignment(double angle){
        requires(CommandBase.chassis);
        //pidController = new PIDController(1.0, 1.0, 1.0, CommandBase.chassis.getGyroPIDSource(), CommandBase.chassis);
        //pidController.setSetpoint(0);
        myAngle = angle;
        if(myAngle < 0) end();
    }

    protected void initialize() {
        //SmartDashboard.putString("Align Attempted", "Nope");
    }

    protected void execute() {
        /*if (!pidController.isEnable()) {
            pidController.enable();
        }*/
        //pidController.getSetpoint();
        //SmartDashboard.putString("Align Attempted", "Maybe");
        SmartDashboard.putNumber("Angle", chassis.getAngle());
        CommandBase.chassis.driveToAngle(myAngle);
    }

    protected boolean isFinished() {
        if(cancelled)
        {
            return true;
        }
        //SmartDashboard.putBoolean("isFinished", CommandBase.chassis.aligned(myAngle));
        return CommandBase.chassis.aligned(myAngle);
    }

    protected void end() {
        cancelled = true;
        //SmartDashboard.putBoolean("executing", false);
        iterations = 0;
        //pidController.disable();
    }
    
    protected void interrupted() {
    }
    
}

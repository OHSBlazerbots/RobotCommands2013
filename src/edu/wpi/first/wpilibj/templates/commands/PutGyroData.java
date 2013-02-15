/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author jcannon
 */
public class PutGyroData extends CommandBase {
    
    public PutGyroData() {
    requires(CommandBase.chassis);
    }

    protected void initialize() {
    }

    protected void execute() {
        SmartDashboard.putNumber("Angle",CommandBase.chassis.getAngle());
        SmartDashboard.putNumber("Acceleration X",CommandBase.chassis.getAccelerationX());
        SmartDashboard.putNumber("Acceleration Y",CommandBase.chassis.getAccelerationY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

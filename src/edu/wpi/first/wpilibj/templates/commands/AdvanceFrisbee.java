/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author jcannon
 */
public class AdvanceFrisbee extends CommandBase{

    protected void initialize() {
        requires(CommandBase.pneumatics);
    }

    protected void execute() {
        CommandBase.pneumatics.advanceFrisbee();
        CommandBase.pneumatics.stopAdvance();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

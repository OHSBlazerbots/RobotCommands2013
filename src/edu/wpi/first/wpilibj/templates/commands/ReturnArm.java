/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author jcannon
 */
public class ReturnArm extends CommandBase{
    
    public ReturnArm(){
        requires(CommandBase.pneumatics);
    }

    protected void initialize() {
        
    }

    protected void execute() {
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

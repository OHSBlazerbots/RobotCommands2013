/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author jcannon
 */
public class Climb extends CommandBase{
    
    public Climb(){
        requires(CommandBase.pneumatics);
    }

    protected void initialize() {
        
    }

    protected void execute() {
        if(CommandBase.pneumatics.getStateClimber() == DoubleSolenoid.Value.kForward) {
            pneumatics.putUsDown();
        }
        else{
            pneumatics.pickUsUp();
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

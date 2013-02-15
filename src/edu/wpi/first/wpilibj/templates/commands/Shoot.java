/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 *
 * @author jcannon
 */
public class Shoot extends CommandBase{
    public Shoot() {
        requires(CommandBase.shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
    }

    protected void initialize() {
    }

    protected void execute() {
        if(!shooter.isShooterOn()){
            shooter.shoot();
        }
        else{
            shooter.off();
        }
       /* else if (!Shooter.getSwitch()){
            shooter.shoot();
            System.out.print("Shoot!");
        }*/
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }


}

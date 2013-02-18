/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author jcannon
 */
public class Autonomous extends CommandGroup {
    
    public Autonomous() {
        addSequential(new PIDalignment(270));
        addSequential(new DriveStraight(2.0));
        addSequential(new PIDalignment(0));
        addSequential(new ShootProcess());
    }

}

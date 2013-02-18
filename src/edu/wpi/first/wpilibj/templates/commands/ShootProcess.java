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
public class ShootProcess extends CommandGroup{
    public static Shoot shoot;
    public static AdvanceFrisbee frisgo;
    public static ReturnArm armback;
    
    
    public ShootProcess(){
        addParallel(shoot);
        addSequential(frisgo, 1.0);
        addSequential(armback);
        addSequential(shoot);
        
    }
  
    
}

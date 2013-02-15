/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author jcannon
 */
public class Shooter extends Subsystem{

     // Two spike relays are used to shoot the ball.
    private Relay bottomShooterRelay;
    private Relay topShooterRelay;
    //public static DigitalInput shooterSwitch;

    // The states tells us if the shooter is off or on.
    private boolean shooterOn;

    public Shooter(int bottomRelayPort, int topRelayPort/*, int limitSwitchPort*/) {
        bottomShooterRelay = new Relay(bottomRelayPort);
        topShooterRelay = new Relay(topRelayPort);
        shooterOn = false;
        //shooterSwitch = new DigitalInput(limitSwitchPort);
    }

    /**
     * Set the default command for the shooter.
     */
    public void initDefaultCommand() {
        // nothing here for now.
    }

    /**
     * Turn the shooter and shoots the ball if its in the shooter.
     */
    public void shoot() {
        /**
         * I would like to have a limit switch that triggers when the ball goes
         * over the peak of the BallPicker. Or have something that limits the
         * balls until they want to be shot, like a sliding door or something.
         */
        topShooterRelay.set(Relay.Value.kForward);
        bottomShooterRelay.set(Relay.Value.kForward);
        shooterOn = true;
    }

    /**
     * Turn the shooter off.
     */
    public void off() {
        bottomShooterRelay.set(Relay.Value.kOff);
        topShooterRelay.set(Relay.Value.kOff);
        shooterOn = false;
    }

    /**
     * Check if the shooter is on.
     */
    public boolean isShooterOn() {
        return shooterOn;
    }

  /*  public static boolean getSwitch(){
        System.out.println("Switch: " + shooterSwitch.get());
        return shooterSwitch.get();
    }*/

    /**
     * Initialize the shooter with the right relay ports.
     * 
     * @param bottomRelayPort
     * @param topRelayPort
     */


}

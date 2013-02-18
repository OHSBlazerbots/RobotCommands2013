/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author jcannon
 */
public class Pneumatics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Relay solenoidRelay;
    //Solenoid secondSolenoid;
    DoubleSolenoid doubleSolenoid;
    Compressor compressor;

    public void pickUsUp(){
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
        //secondSolenoid.set(true);
    }
    public void putUsDown(){
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
        //secondSolenoid.set(false);
    }
    public void advanceFrisbee(){
        solenoidRelay.set(Relay.Value.kForward);
    }
    public void stopAdvance(){
        solenoidRelay.set(Relay.Value.kReverse);
    }
    public Value getStateClimber(){
        //return secondSolenoid.get();
        return doubleSolenoid.get();
    }
    public Relay.Value getStateAdvancer(){
        return solenoidRelay.get();
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public Pneumatics(int relaySolenoidChannel, int forwardSolenoidChannel, int reverseSolenoidChannel, int pressureSwitchChannel, int compressorRelayChannel){
        solenoidRelay = new Relay(relaySolenoidChannel);
        //secondSolenoid = new Solenoid(secondSolenoidChannel);
        doubleSolenoid = new DoubleSolenoid(forwardSolenoidChannel, reverseSolenoidChannel);
        compressor = new Compressor(pressureSwitchChannel, compressorRelayChannel);
        compressor.start();
    }
}

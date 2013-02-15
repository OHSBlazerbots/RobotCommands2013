/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author jcannon
 */
public class Pneumatics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Solenoid solenoid;
    Solenoid secondSolenoid;
    //DoubleSolenoid doubleSolenoid;
    Compressor compressor;

    public void pickUsUp(){
       // doubleSolenoid.set(DoubleSolenoid.Value.kForward);
        secondSolenoid.set(true);
    }
    public void putUsDown(){
      //  doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
        secondSolenoid.set(false);
    }
    public void advanceFrisbee(){
        solenoid.set(true);
    }
    public void stopAdvance(){
        solenoid.set(false);
    }
    public boolean getStateClimber(){
        return secondSolenoid.get();
    }
    public boolean getStateAdvancer(){
        return solenoid.get();
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public Pneumatics(int singleSolenoidChannel, int secondSolenoidChannel, int pressureSwitchChannel, int compressorRelayChannel){
        solenoid = new Solenoid(singleSolenoidChannel);
        secondSolenoid = new Solenoid(secondSolenoidChannel);
        //doubleSolenoid = new DoubleSolenoid(forwardSolenoidChannel, reverseSolenoidChannel);
        compressor = new Compressor(pressureSwitchChannel, compressorRelayChannel);
        compressor.start();
    }
}

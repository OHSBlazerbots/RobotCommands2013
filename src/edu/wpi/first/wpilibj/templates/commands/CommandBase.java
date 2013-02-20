package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.Chassis;
import edu.wpi.first.wpilibj.templates.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;
import edu.wpi.first.wpilibj.templates.subsystems.Targeting;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Chassis chassis;
    public static Targeting targeting;
    public static Shooter shooter;
    public static Pneumatics pneumatics;
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        //System.out.println("Initializing...");
        chassis = new Chassis(RobotMap.leftMotor, RobotMap.rightMotor);
        //System.out.println("Chassis initializing!");
        shooter = new Shooter(RobotMap.SHOOTER_TOP_RELAY_PORT, RobotMap.SHOOTER_BOTTOM_RELAY_PORT/*, RobotMap.SHOOTER_LIMIT_SWITCH_PORT*/);
        pneumatics = new Pneumatics(RobotMap.CLIMB_SOLENOID_RELAY_PORT, RobotMap.FRISBEE_SOLENOID_PORT_FORWARD, RobotMap.FRISBEE_SOLENOID_PORT_REVERSE, RobotMap.COMPRESSOR_PRESSURE_SWITCH_PORT, RobotMap.COMPRESSOR_RELAY_PORT);
        targeting = new Targeting(RobotMap.LED_RING_RELAY_PORT);
        oi = new OI();
        //System.out.println("OI Initializing!");
        //SmartDashboard.putNumber("Angle", chassis.getAngle());
        //SmartDashboard.putNumber("Acceleration X", chassis.getAccelerationX());
        //SmartDashboard.putNumber("Acceleration Y", chassis.getAccelerationY());

        // Show what command your subsystem is running on the SmartDashboard

    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}

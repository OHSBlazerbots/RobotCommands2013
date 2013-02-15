package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.

    public static final int leftMotor = 1;
    public static final int rightMotor = 2;
    public static final int GYRO_SLOT = 1;
    public static final int GYRO_CHANNEL = 2;
    public static final int ACCELEROMETERX_PORT = 4;
    public static final int ACCELEROMETERY_PORT = 5;
    
    
    //THese are digital port #'s. They are separate fron the ones above.
    public static final int COMPRESSOR_PRESSURE_SWITCH_PORT = 5;
    public static final int COMPRESSOR_RELAY_PORT = 6;
    public static final int SHOOTER_BOTTOM_RELAY_PORT = 1;
    public static final int SHOOTER_TOP_RELAY_PORT = 2;
    
    public static final int SHOOTER_LIMIT_SWITCH_PORT = 1;
    
    
    public static final int FRISBEE_ADVANCE_SOLENOID_PORT = 1;
    public static final int CLIMB_SOLENOID_PORT = 2;
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}

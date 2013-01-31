package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static OI instance = null;
    private static final int JOYSTICK_PORT = 1;
    private Joystick joystick;
    private DriveWithJoystick drive;

    public OI() {
        joystick = new Joystick(JOYSTICK_PORT);
    }

    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }

    public Joystick getJoystick() {
        return joystick;
    }
}

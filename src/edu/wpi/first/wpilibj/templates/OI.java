package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.templates.commands.PIDalignment;
import edu.wpi.first.wpilibj.templates.commands.Shoot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static OI instance = null;
    private static final int JOYSTICK_PORT = 1;
    private Joystick joystick;
    private DriveWithJoystick drive;
    private Button shootButton;
    private final JoystickButton lineUpButton;
    private final JoystickButton lineDownButton;
    private final JoystickButton lineLeftButton;
    private final JoystickButton lineRightButton;
    private final JoystickButton cancelButton;
    
    public OI() {
        joystick = new Joystick(JOYSTICK_PORT);
        shootButton = new JoystickButton(joystick, 6);
        shootButton.whenPressed(new Shoot());
        lineUpButton = new JoystickButton(joystick, 4);
        lineUpButton.whenPressed(new PIDalignment(0));
        lineDownButton = new JoystickButton(joystick, 1);
        lineDownButton.whenPressed(new PIDalignment(180));
        lineLeftButton = new JoystickButton(joystick, 3);
        lineLeftButton.whenPressed(new PIDalignment(270));
        lineRightButton = new JoystickButton(joystick, 2);
        lineRightButton.whenPressed(new PIDalignment(90));
        cancelButton = new JoystickButton(joystick, 7);
        cancelButton.whenPressed(new PIDalignment(-5));
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

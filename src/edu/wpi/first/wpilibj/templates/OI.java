package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.AdvanceFrisbee;
import edu.wpi.first.wpilibj.templates.commands.CenterTarget;
import edu.wpi.first.wpilibj.templates.commands.Climb;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.templates.commands.PIDalignment;
import edu.wpi.first.wpilibj.templates.commands.ReturnArm;
import edu.wpi.first.wpilibj.templates.commands.Shoot;
import edu.wpi.first.wpilibj.templates.commands.ShootProcess;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private static OI instance = null;
    private static final int JOYSTICK_PORT = 1;
    private static final int JOYSTICK_PORT_2 = 2;
    private Joystick joystick;
    private Joystick joystick2;
    private DriveWithJoystick drive;
    private Button shootButton;
    private final JoystickButton autoTurnButton;
    private final JoystickButton centerTargetButton;
    private final JoystickButton climbButton;
    private final JoystickButton frisbeeForwardButton;
    private final JoystickButton frisbeeBackButton;
    
    public OI() {
        SmartDashboard.putNumber("Autoturn", 0);
        joystick = new Joystick(JOYSTICK_PORT);
        joystick2 = new Joystick(JOYSTICK_PORT_2);
        shootButton = new JoystickButton(joystick2, 6);
        shootButton.whenPressed(new Shoot());
        autoTurnButton = new JoystickButton(joystick, 4);
        autoTurnButton.whenPressed(new PIDalignment(SmartDashboard.getNumber("Autoturn")));
        centerTargetButton = new JoystickButton(joystick, 2);
        //centerTargetButton.whenPressed(new CenterTarget());
        climbButton = new JoystickButton(joystick2, 4);
        climbButton.whenPressed(new Climb());
        frisbeeForwardButton = new JoystickButton(joystick2, 3);
        frisbeeForwardButton.whenPressed(new AdvanceFrisbee());
        frisbeeBackButton = new JoystickButton(joystick2, 2);
        frisbeeBackButton.whenPressed(new ReturnArm());
        
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

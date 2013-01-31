/**
 * Copyright 2012. FRC Team 3807.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.templates.RBDrive;

/**
 * The chassis subsystem is responsible for driving the robot and nothing else.
 */
public class Chassis extends Subsystem {
    // The RBDrive translates joystick commands into speed controller commands.

    public RBDrive drive;

    /**
     * Create an instance of the chassis class with the appropriate motors.
     *
     * @param frontLeftMotor
     * @param rearLeftMotor
     * @param frontRightMotor
     * @param rearRightMotor
     */
    public Chassis(int LeftMotor, int RightMotor) {

        drive = new RBDrive(LeftMotor, RightMotor);
        drive.setSafetyEnabled(false);
    }

    /**
     * Set the default command for the chassis
     */
    public void initDefaultCommand() {
        // Set the default command to driving with the joystick.
        setDefaultCommand(new DriveWithJoystick());
    }

    /**
     * Command the chassis to drive with the joystick.
     *
     * @param joystick
     */
    public void driveWithJoyStick(Joystick joystick) {
        drive.arcadeDrive(joystick.getY(), joystick.getX());
    }

    /**
     * Command the chassis to drive with the Kinect. Raising the right arm moves
     * the chassis forward. Lowering the right arm does nothing. The left arm is
     * used to control rotation(turning). Raising the left arm turns right and
     * lowering it turns left.
     *
     * @param leftArm
     * @param rightArm
     */
    public void driveWithKinect(KinectStick leftArm, KinectStick rightArm) {
        if (rightArm.getY() <= 0) {
            drive.arcadeDrive(rightArm.getY(), leftArm.getY());
        }
    }

    /**
     * Drive the robot forward.
     */
    public void straight() {
        drive.arcadeDrive(0.25, 0);
    }
}

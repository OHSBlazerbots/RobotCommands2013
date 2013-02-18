/**
 * Copyright 2012. FRC Team 3807.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RBDrive;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;

/**
 * The chassis subsystem is responsible for driving the robot and nothing else.
 */
public class Chassis extends Subsystem {
    // The RBDrive translates joystick commands into speed controller commands.
    public static RBDrive drive;
    private Gyro gyro;
    private Accelerometer accelerometerX;
    private Accelerometer accelerometerY;
    
    /**
     * Create an instance of the chassis class with the appropriate motors.
     *
     * @param frontLeftMotor
     * @param rearLeftMotor
     * @param frontRightMotor
     * @param rearRightMotor
     */
    public Chassis(int LeftMotor, int RightMotor) {
        gyro = new Gyro(RobotMap.GYRO_SLOT, RobotMap.GYRO_CHANNEL)/*(RobotMap.GYRO_PORT, 2);*/;
        accelerometerX = new Accelerometer(RobotMap.ACCELEROMETERX_PORT);
        LiveWindow.addSensor("GyroAccelerometer", "Accelerometer X", accelerometerX);
        accelerometerY = new Accelerometer(RobotMap.ACCELEROMETERY_PORT);
        LiveWindow.addSensor("GyroAccelerometer", "Accelerometer Y", accelerometerY);   
        //drive = new RBDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        //drive.setSafetyEnabled(false);
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
        drive.arcadeDrive(joystick.getY(), -joystick.getX());
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

    public double getAngle(){
        //SmartDashboard.putDouble("Angle", gyro.getAngle());
        //return (gyro.getAngle()%360 + 360)%360;
        double angle = gyro.getAngle() % (360);
        if (angle<0.0) {
            angle += 360;
        }
        return angle;
    }

    public double getAccelerationX(){
        //SmartDashboard.putDouble("Acceleration X", accelerometerX.getAcceleration());
        return accelerometerX.getAcceleration();
       
    }

    public double getAccelerationY(){ 
        //SmartDashboard.putDouble("Acceleration X", accelerometerX.getAcceleration());
        return accelerometerY.getAcceleration();
    }
    
    public PIDSource getGyroPIDSource(){
        return gyro;
    }

    public void pidWrite(double d) {
        double angle = getAngle();
        SmartDashboard.putNumber("pidWrite:d", d);
        SmartDashboard.putNumber("pidWrite:angle", angle);
        double correctTurn = d - angle;
        double correctTurnWithJoystick = (correctTurn / Math.PI) - 1;
        SmartDashboard.putNumber("correctTurnInAngles", correctTurnWithJoystick);
        drive.drive(0.1, d);
    }

    public boolean aligned(double angle) {
        return Math.abs(getAngle() - angle) < 1;
    }
    
    public void drive(double output, double curve) {
        drive.drive(output, curve);
    }
    
    public void driveToAngle(double angle) {
        if(angle < 180)
        {
            if(getAngle() > 180 + angle || getAngle() <= angle)
            {
                drive.arcadeDrive(0, 0.5);
            }
            else{
                drive.arcadeDrive(0, -0.5);
            }
        }
        else if(angle < 360)
        {
            if(getAngle() >= angle || getAngle() < angle - 180)
            {
                drive.arcadeDrive(0, 0.5);
            }
            else
            {
                drive.arcadeDrive(0, -0.5);
            }
        }
        else 
        {
            driveToAngle(angle % 360);
        }
    }

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import java.lang.*;

/**
 *
 * @author bhuang
 */
public class SnapShot extends CommandBase {

    public SnapShot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(CommandBase.targeting);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        try {
            System.out.println("clic");
            ColorImage temp = CommandBase.targeting.processImage();
            //if(temp != null) temp.write("/tmp/processed.bmp");
            SmartDashboard.putString("State: ", "Processing...");
            BinaryImage thresholdImage =  temp.thresholdHSL(48, 128, 45, 255, 128, 255);
            if(thresholdImage != null) thresholdImage.write("/tmp/thresh.bmp");
            BinaryImage filterImage = thresholdImage.removeSmallObjects(false, 2);
            if(filterImage != null) filterImage.write("/tmp/filter.bmp");
            BinaryImage convexImage = filterImage.convexHull(false);
            if(convexImage != null) convexImage.write("/tmp/convex.bmp");
            BinaryImage finalImage = targeting.filterForValidRectangles(convexImage);
            if(finalImage != null) finalImage.write("/tmp/final.bmp");
            //SmartDashboard.putData("Processed Image", finalImage);
            SmartDashboard.putString("State: ", "Done");
            double best_score = 0;
            int best_x = 0;
            int best_y = 0;
            int bestWidth = 1000000;
            int bestHeight = 1000000;
            double distance = 0;
            ParticleAnalysisReport[] reports = finalImage.getOrderedParticleAnalysisReports();  // get list of results
            for (int i = 0; i < reports.length; i++) {                                // print results
                ParticleAnalysisReport r = reports[i];
                System.out.println(r.toString());
                if(r.particleQuality > best_score) {
                    best_x = r.center_mass_x;
                    best_y = r.center_mass_y;
                    bestWidth = r.boundingRectWidth;
                    bestHeight = r.boundingRectHeight;
                }
            }
            System.out.println("Best Scores: " + best_x + " , " + best_y);
            //bestWidth = bestWidth/bestHeight * (2/(bestWidth/bestHeight));
            double correctionFactor = 1.0;
            double partRatio = bestWidth / bestHeight;
            //correctionFactor = Math.sin((Math.PI / 2) * (1 - Math.atan(partRatio) / Math.atan(2.0))); //atan doesn't work!
            distance = 4.5*640/bestWidth/2/Math.tan(21.75 * Math.PI / 180);
            SmartDashboard.putString("bestTarget", "Best Scores: " + best_x + " , " + best_y);
            SmartDashboard.putString("Distance: ", distance + " ft");
            
            //double myGyro = CommandBase.sensors.getAngle();
            //SmartDashboard.putString("Gyro", " " + myGyro);
            
            //double accelerometerX = CommandBase.sensors.getAccelerationX();
            //SmartDashboard.putString("AccelerometerX", " " + accelerometerX);
            
            //double accelerometerY = CommandBase.sensors.getAccelerationY();
            //SmartDashboard.putString("AccelerometerY", " " + accelerometerY);
            
            finalImage.free();
            convexImage.free();
            filterImage.free();
            thresholdImage.free();
            temp.free();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
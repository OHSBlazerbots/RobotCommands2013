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

/**
 *
 * @author jcannon
 */
public class CenterTarget extends CommandBase {

    private static int best_x;

    public CenterTarget() {
        // Use requires() here to declare subsystem dependencies
        requires(CommandBase.targeting);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        best_x = 0;
        try {
            while (best_x < 310 || best_x > 330) {
                ColorImage temp = CommandBase.targeting.processImage();
                BinaryImage thresholdImage = temp.thresholdHSL(48, 128, 45, 255, 128, 255);
                BinaryImage filterImage = thresholdImage.removeSmallObjects(false, 2);
                BinaryImage convexImage = filterImage.convexHull(false);
                BinaryImage finalImage = targeting.filterForValidRectangles(convexImage);
                double best_score = 0;

                ParticleAnalysisReport[] reports = finalImage.getOrderedParticleAnalysisReports();  // get list of results
                for (int i = 0; i < reports.length; i++) {                                // print results
                    ParticleAnalysisReport r = reports[i];
                    if (r.particleQuality > best_score) {
                        best_x = r.center_mass_x;
                    }
                }
                CommandBase.targeting.centerTarget(best_x);
            }

        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

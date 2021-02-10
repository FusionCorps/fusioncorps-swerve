package frc.robot.math;

import frc.robot.Constants;

import static java.lang.Math.atan2;

public class SwerveCalcs { // Runs the math needed for schmoving

    static double alpha;
    static double distToWheel;

    static double forward_speed;
    static double strafe_speed;
    static double rot_added_speed;


    public SwerveCalcs(double forward_input, double strafe_input, double rot_input) {
        new Constants();

        this.alpha = Math.atan(Constants.TRACK_LENGTH_METERS/Constants.TRACK_WIDTH_METERS);
        this.distToWheel = Math.sqrt((Math.pow(Constants.TRACK_LENGTH_METERS, 2)) +
                (Math.pow(Constants.TRACK_WIDTH_METERS, 2)));

        this.forward_speed = forward_input*Constants.SWERVE_FORWARD_SPEED_MAX;
        this.strafe_speed = strafe_input*Constants.SWERVE_STRAFE_SPEED_MAX;
        this.rot_added_speed = rot_input*distToWheel*Constants.SWERVE_ROT_SPEED_MAX;
    }



    public static void updateVals(double forward_input, double strafe_input, double rot_input) {
        new Constants();

        forward_speed = forward_input*Constants.SWERVE_FORWARD_SPEED_MAX;
        strafe_speed = strafe_input*Constants.SWERVE_STRAFE_SPEED_MAX;
        rot_added_speed = rot_input*distToWheel*Constants.SWERVE_ROT_SPEED_MAX;
    }

    public static double getSpeed(double forward_input, double strafe_input, double rot_input, int position) throws Exception { // position 0 1 2 3 referring to FL BL FR BR

        double tempSpeed;

        updateVals(forward_input, strafe_input, rot_input);

        if (position == 0) {
            double tempSpeedFwd = forward_speed - rot_added_speed*Math.cos(alpha);
            double tempSpeedStr = strafe_speed - rot_added_speed*Math.sin(alpha);
            tempSpeed = Math.sqrt(Math.pow(tempSpeedFwd, 2) + Math.pow(tempSpeedStr, 2));
        } else if (position == 1) {
            double tempSpeedFwd = forward_speed - rot_added_speed*Math.sin(alpha);
            double tempSpeedStr = strafe_speed + rot_added_speed*Math.cos(alpha);
            tempSpeed = Math.sqrt(Math.pow(tempSpeedFwd, 2) + Math.pow(tempSpeedStr, 2));
        } else if (position == 2) {
            double tempSpeedFwd = forward_speed + rot_added_speed*Math.sin(alpha);
            double tempSpeedStr = strafe_speed - rot_added_speed*Math.cos(alpha);
            tempSpeed = Math.sqrt(Math.pow(tempSpeedFwd, 2) + Math.pow(tempSpeedStr, 2));
        } else if (position == 3) {
            double tempSpeedFwd = forward_speed + rot_added_speed*Math.cos(alpha);
            double tempSpeedStr = strafe_speed + rot_added_speed*Math.sin(alpha);
            tempSpeed = Math.sqrt(Math.pow(tempSpeedFwd, 2) + Math.pow(tempSpeedStr, 2));
        } else {
            throw new Exception("The speed was not initialized with a proper slot.");
        }

        return tempSpeed;

    }

    public static double getAngle(double forward_input, double strafe_input, double rot_input, int position) throws Exception { // position 0 1 2 3 referring to FL BL FR BR

        double tempAngle;

        updateVals(forward_input, strafe_input, rot_input);

        if (position == 0) {
            double tempSpeedFwd = forward_speed - rot_added_speed*Math.cos(alpha);
            double tempSpeedStr = strafe_speed - rot_added_speed*Math.sin(alpha);
            tempAngle = atan2(tempSpeedStr, tempSpeedFwd);
        } else if (position == 1) {
            double tempSpeedFwd = forward_speed - rot_added_speed*Math.sin(alpha);
            double tempSpeedStr = strafe_speed + rot_added_speed*Math.cos(alpha);
            tempAngle = atan2(tempSpeedStr, tempSpeedFwd);
        } else if (position == 2) {
            double tempSpeedFwd = forward_speed + rot_added_speed*Math.sin(alpha);
            double tempSpeedStr = strafe_speed - rot_added_speed*Math.cos(alpha);
            tempAngle = atan2(tempSpeedStr, tempSpeedFwd);
        } else if (position == 3) {
            double tempSpeedFwd = forward_speed + rot_added_speed*Math.cos(alpha);
            double tempSpeedStr = strafe_speed + rot_added_speed*Math.sin(alpha);
            tempAngle = atan2(tempSpeedStr, tempSpeedFwd);
        } else {
            throw new Exception("The angle was not initialized with a proper slot.");
        }

        return tempAngle;

    }
}


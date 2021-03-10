package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

import static frc.robot.RobotContainer.mController;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.StrictMath.PI;

public class RunFieldCentricSwerve extends CommandBase {

    Chassis mChassis;

    public RunFieldCentricSwerve(Chassis chassis) {
        mChassis = chassis;
        this.addRequirements(mChassis);
    }

    private SlewRateLimiter limiter = new SlewRateLimiter(2.5);
    public double angle = mChassis.ahrs.getAngle();

    @Override
    public void execute() {

        angle = mChassis.ahrs.getAngle() % 360;

        System.out.println(angle);


        try {
            mChassis.runSwerve(mController.getRawAxis(1)*cos(angle/360*(2*PI)) + mController.
                            getRawAxis(0)*sin(angle/360*(2*PI)),
                    mController.getRawAxis(1)*sin(angle/360*(2*PI)) - mController.
                            getRawAxis(0)*cos(angle/360*(2*PI)),
                    mController.getRawAxis(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("Trying to drive");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}


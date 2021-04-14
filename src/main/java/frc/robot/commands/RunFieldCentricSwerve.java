package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.math.SigmoidGenerator;
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

    private SlewRateLimiter fwdLimiter = new SlewRateLimiter(4.5);
    private SlewRateLimiter strLimiter = new SlewRateLimiter(4.5);
    private SlewRateLimiter rotLimiter = new SlewRateLimiter(4.5);

    private SigmoidGenerator responseCurve = new SigmoidGenerator(1.0);

    public double angle = mChassis.ahrs.getAngle();

    @Override
    public void initialize() {
        mChassis.comboFR.zero();
        mChassis.comboBR.zero();
        mChassis.comboFL.zero();
        mChassis.comboBL.zero();
    }

    @Override
    public void execute() {

        angle = -(mChassis.ahrs.getAngle() % 360);



        double axis0 = responseCurve.calculate(mController.getRawAxis(0));
        double axis1 = responseCurve.calculate(mController.getRawAxis(1));
        double axis4 = responseCurve.calculate(mController.getRawAxis(4));



//        try {
//            mChassis.runSwerve(fwdLimiter.calculate(axis1*cos(angle/360*(2*PI)) + axis0*sin(angle/360*(2*PI))),
//                    strLimiter.calculate(axis1*sin(angle/360*(2*PI)) - axis0*cos(angle/360*(2*PI))),
//                    rotLimiter.calculate(mController.getRawAxis(4)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            mChassis.runSwerve(fwdLimiter.calculate(axis1*cos(angle/360*(2*PI)) + axis0*sin(angle/360*(2*PI))),
                    strLimiter.calculate(axis1*sin(angle/360*(2*PI)) - axis0*cos(angle/360*(2*PI))),
                    rotLimiter.calculate(mController.getRawAxis(4)));
        } catch (Exception e) {
        }
//        System.out.println("Trying to drive");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}


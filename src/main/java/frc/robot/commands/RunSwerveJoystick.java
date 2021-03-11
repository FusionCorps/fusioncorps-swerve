package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

import static frc.robot.RobotContainer.mController;


public class RunSwerveJoystick extends CommandBase {

    Chassis mChassis;

    public RunSwerveJoystick(Chassis chassis) {
        mChassis = chassis;
        this.addRequirements(mChassis);
    }

    private SlewRateLimiter limiter = new SlewRateLimiter(2.5);

    @Override
    public void execute() {
        try {
            mChassis.runSwerve(mController.getRawAxis(1),
                    -mController.getRawAxis(0),
                    mController.getRawAxis(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Trying to drive");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

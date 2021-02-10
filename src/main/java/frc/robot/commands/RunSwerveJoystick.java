package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Chassis;

import static frc.robot.RobotContainer.mController;


public class RunSwerveJoystick extends Command {

    Chassis mChassis;

    public RunSwerveJoystick(Chassis chassis) {
        this.requires(chassis);
        mChassis = chassis;
    }

    private SlewRateLimiter limiter = new SlewRateLimiter(2.5);

    @Override
    protected void execute() {
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
    protected boolean isFinished() {
        return false;
    }
}

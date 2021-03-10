package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

import static frc.robot.RobotContainer.mController;

public class TestCommandExecutable extends CommandBase {

    Chassis mChassis;

    public TestCommandExecutable(Chassis chassis) {
        mChassis = chassis;
        this.addRequirements(mChassis);
    }

    private SlewRateLimiter limiter = new SlewRateLimiter(2.5);

    @Override
    public void execute() {
        System.out.println("Bruh Moment");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

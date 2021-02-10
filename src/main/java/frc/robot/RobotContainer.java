package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Chassis;

public class RobotContainer {

    public static final Chassis mChassis = new Chassis();
    public static XboxController mController = new XboxController(0);

}

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.TestCommand;
import frc.robot.subsystems.Chassis;

public class RobotContainer {

    public RobotContainer() {
        configureButtonBindings();
    }

    public static final Chassis mChassis = new Chassis();
    public static XboxController mController = new XboxController(0);

    private void configureButtonBindings() {
        new JoystickButton(mController, XboxController.Button.kA.value)
                .whenPressed(new TestCommand());
    }

}

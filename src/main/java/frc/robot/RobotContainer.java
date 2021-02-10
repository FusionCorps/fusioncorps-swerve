package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.RunSwerveJoystick;
import frc.robot.commands.TestCommand;
import frc.robot.subsystems.Chassis;

public class RobotContainer {

    private static final Chassis mChassis = new Chassis();
    public static XboxController mController = new XboxController(0);

    public RobotContainer() {
        configureButtonBindings();
        System.out.println("Container Initialized");

        mChassis.setDefaultCommand(new RunSwerveJoystick(mChassis));

    }

    private void configureButtonBindings() {
        new JoystickButton(mController, XboxController.Button.kA.value)
                .whenPressed(new TestCommand());
    }



}

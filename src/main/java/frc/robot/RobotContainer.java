package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.Chassis;

public class RobotContainer {

    public static final Chassis mChassis = new Chassis();
    public static final XboxController mController = new XboxController(0);

    private static final XboxController pController = new XboxController(0);





    public RobotContainer() {
        configureButtonBindings();
        System.out.println("Container Initialized");

        mChassis.setDefaultCommand(new RunFieldCentricSwerve(mChassis));

    }

    JoystickButton mBbutton = new JoystickButton(mController, XboxController.Button.kB.value);

    private void configureButtonBindings() {
        new JoystickButton(mController, XboxController.Button.kB.value)
                .whenPressed((Command) new ResetGyro(mChassis));
        System.out.println("Buttons set");
    }



}

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.*;
import frc.robot.subsystems.Chassis;

public class RobotContainer {

    public static final Chassis mChassis = new Chassis();
    public static XboxController mController = new XboxController(0);



    public RobotContainer() {
        configureButtonBindings();
        System.out.println("Container Initialized");

        mChassis.setDefaultCommand(new RunFieldCentricSwerve(mChassis));

    }

    JoystickButton mBbutton = new JoystickButton(mController, XboxController.Button.kB.value);

    private void configureButtonBindings() {
        new JoystickButton(mController, XboxController.Button.kBumperRight.value)
                .whenPressed(new ResetGyro());

    }



}

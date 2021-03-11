package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

import static frc.robot.RobotContainer.mChassis;

public class ResetGyro extends InstantCommand {

    private final Chassis cChassis;

    public ResetGyro(Chassis chassis) {
        cChassis = chassis;

    }

    @Override
    public void initialize() {
        System.out.println("Trying to Reset");
        cChassis.ahrs.reset();
    }




}
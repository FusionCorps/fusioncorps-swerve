package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class ResetGyro extends InstantCommand {




    @Override
    public void initialize() {
        System.out.println("Trying to Reset");
    }



}
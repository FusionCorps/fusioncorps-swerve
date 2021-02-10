package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class TestCommand extends InstantCommand {
    @Override
    protected void initialize() {
         System.out.println("Testing");
    }
}

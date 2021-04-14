package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Chassis;

public class ZeroAxes extends CommandBase {

    private final Chassis cChassis;

    public ZeroAxes(Chassis chassis) {
        cChassis = chassis;
    }

    @Override
    public void initialize() {
        cChassis.comboFL.zero();
        cChassis.comboBL.zero();
        cChassis.comboFR.zero();
        cChassis.comboBR.zero();
    }

    @Override
    public void execute() {
        cChassis.comboFL.zero();
        cChassis.comboBL.zero();
        cChassis.comboFR.zero();
        cChassis.comboBR.zero();
    }

}

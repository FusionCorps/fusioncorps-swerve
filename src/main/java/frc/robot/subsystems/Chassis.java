package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.RunSwerveJoystick;
import frc.robot.math.SwerveCalcs;
import frc.robot.modules.SwerveCombo;


import static frc.robot.math.SwerveCalcs.*;

public class Chassis extends SubsystemBase {
    

    WPI_TalonFX drive0 = new WPI_TalonFX(3);
    WPI_TalonFX drive1 = new WPI_TalonFX(2);
    WPI_TalonFX drive2 = new WPI_TalonFX(7);
    WPI_TalonFX drive3 = new WPI_TalonFX(0);

    WPI_TalonFX axis0 = new WPI_TalonFX(4);
    WPI_TalonFX axis1 = new WPI_TalonFX(6);
    WPI_TalonFX axis2 = new WPI_TalonFX(1);
    WPI_TalonFX axis3 = new WPI_TalonFX(5);

    SwerveCombo comboFL = new SwerveCombo(drive0, axis0, 0);
    SwerveCombo comboBL = new SwerveCombo(drive1, axis1, 1);
    SwerveCombo comboFR = new SwerveCombo(drive2, axis2, 2);
    SwerveCombo comboBR = new SwerveCombo(drive3, axis3, 3);

    public Chassis() {
        System.out.println("Chassis Created");
    }
    


    public void runSwerve(double fwd, double str, double rot) throws Exception {

        new SwerveCalcs(fwd, str, rot);
        this.comboFL.passArgs(getSpeed(fwd, str, rot, 0), getAngle(fwd, str, rot, 0));
        this.comboBL.passArgs(getSpeed(fwd, str, rot, 1), getAngle(fwd, str, rot, 1));
        this.comboFR.passArgs(getSpeed(fwd, str, rot, 2), getAngle(fwd, str, rot, 2));
        this.comboBR.passArgs(getSpeed(fwd, str, rot, 3), getAngle(fwd, str, rot, 3));



    }


}

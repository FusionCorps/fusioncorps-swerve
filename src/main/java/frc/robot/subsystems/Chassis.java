package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.RunSwerveJoystick;
import frc.robot.math.SwerveCalcs;
import frc.robot.modules.SwerveCombo;


import static frc.robot.math.SwerveCalcs.*;
import static java.lang.Double.max;

public class Chassis extends SubsystemBase {
    

    WPI_TalonFX drive0 = new WPI_TalonFX(3);
    WPI_TalonFX drive1 = new WPI_TalonFX(2);
    WPI_TalonFX drive2 = new WPI_TalonFX(7);
    WPI_TalonFX drive3 = new WPI_TalonFX(0);

    WPI_TalonFX axis0 = new WPI_TalonFX(4);
    WPI_TalonFX axis1 = new WPI_TalonFX(6);
    WPI_TalonFX axis2 = new WPI_TalonFX(1);
    WPI_TalonFX axis3 = new WPI_TalonFX(5);

    SwerveCombo comboFL = new SwerveCombo(axis0, drive0, 0);
    SwerveCombo comboBL = new SwerveCombo(axis1, drive1, 1);
    SwerveCombo comboFR = new SwerveCombo(axis2, drive2, 2);
    SwerveCombo comboBR = new SwerveCombo(axis3, drive3, 3);

    public Chassis() {
        System.out.println("Chassis Created");
    }
    


    public void runSwerve(double fwd, double str, double rot) throws Exception {

        new SwerveCalcs(fwd, str, rot);

        double ratio = 1.0;

        double speedFL = getSpeed(fwd, str, rot, 0);
        double speedBL = getSpeed(fwd, str, rot, 1);
        double speedFR = getSpeed(fwd, str, rot, 2);
        double speedBR = getSpeed(fwd, str, rot, 3);

        double maxWheelSpeed = max(max(speedFL, speedBL), max(speedFR, speedBR));

        if (maxWheelSpeed > Constants.MAX_SPEED) {
            ratio = (Constants.MAX_SPEED/ maxWheelSpeed);
        } else {
            ratio = 1.0;
        }

        this.comboFL.passArgs(ratio*getSpeed(fwd, str, rot, 0), getAngle(fwd, str, rot, 0));
        this.comboBL.passArgs(ratio*getSpeed(fwd, str, rot, 1), getAngle(fwd, str, rot, 1));
        this.comboFR.passArgs(ratio*getSpeed(fwd, str, rot, 2), getAngle(fwd, str, rot, 2));
        this.comboBR.passArgs(ratio*getSpeed(fwd, str, rot, 3), getAngle(fwd, str, rot, 3));



    }


}

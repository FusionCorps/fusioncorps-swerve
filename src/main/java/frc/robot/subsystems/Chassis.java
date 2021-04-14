package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
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

    CANCoder coder0 = new CANCoder(12); // FL
    CANCoder coder1 = new CANCoder(10); // BL
    CANCoder coder2 = new CANCoder(11); // FR
    CANCoder coder3 = new CANCoder(13); // BR


    public SwerveCombo comboFL;
    public SwerveCombo comboBL;
    public SwerveCombo comboFR;
    public SwerveCombo comboBR;

    public static AHRS ahrs;



    public Chassis() {
        ahrs = new AHRS(SPI.Port.kMXP);
        ahrs.calibrate();

        comboFL = new SwerveCombo(axis0, drive0, coder0, 0);
        comboBL = new SwerveCombo(axis1, drive1, coder1, 1);
        comboFR = new SwerveCombo(axis2, drive2, coder2, 2);
        comboBR = new SwerveCombo(axis3, drive3, coder3, 3);
    }



    public void resetHeading() {
        ahrs.reset();
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

        this.comboFL.passArgs(ratio*speedFL, getAngle(fwd, str, rot, 0));
        this.comboBL.passArgs(ratio*speedBL, getAngle(fwd, str, rot, 1));
        this.comboFR.passArgs(ratio*speedFR, getAngle(fwd, str, rot, 2));
        this.comboBR.passArgs(ratio*speedBR, getAngle(fwd, str, rot, 3));



    }


}

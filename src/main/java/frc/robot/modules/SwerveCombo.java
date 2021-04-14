package frc.robot.modules;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import frc.robot.Constants;
import frc.robot.math.SwerveCalcs;

import static frc.robot.Constants.*;
import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class SwerveCombo {

    WPI_TalonFX axisMotor;
    WPI_TalonFX driveMotor;
    int mPosition;
    double jankCode;
    double absEncDeg;
    CANCoder coder;

    // Note: Phoenix Lib init knocks motors out of alignment
    // Wait until you see that on the console before running, else realign

    public SwerveCombo(WPI_TalonFX axisInit, WPI_TalonFX driveInit, CANCoder coderInit, int position) {

        new Constants();

        this.coder = coderInit;

        this.absEncDeg = (int) 0;

        this.axisMotor = axisInit;
        this.axisMotor.setInverted(TalonFXInvertType.Clockwise);
        this.axisMotor.configNeutralDeadband(0.0001);
        this.axisMotor.configSelectedFeedbackSensor(FeedbackDevice.valueOf(1));

        this.axisMotor.setSelectedSensorPosition(-(this.absEncDeg/360)*2048*STEERING_RATIO);
        this.axisMotor.config_kF(0, Constants.AXIS_kF);
        this.axisMotor.config_kP(0, Constants.AXIS_kP);
        this.axisMotor.config_kI(0, Constants.AXIS_kI);
        this.axisMotor.config_kD(0, Constants.AXIS_kD);
        this.axisMotor.setNeutralMode(NeutralMode.Coast);

        this.driveMotor = driveInit;
        this.driveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        this.driveMotor.setInverted(TalonFXInvertType.CounterClockwise);
        this.driveMotor.configNeutralDeadband(0.01);
        this.driveMotor.setSelectedSensorPosition(0);
        this.driveMotor.config_kF(0, Constants.DRIVE_kF);
        this.driveMotor.config_kP(0, Constants.DRIVE_kP);
        this.driveMotor.config_kI(0, Constants.DRIVE_kI);
        this.driveMotor.config_kD(0, Constants.DRIVE_kD);
        this.driveMotor.setNeutralMode(NeutralMode.Coast);

        this.mPosition = position;


    }


    public void passArgs(double speed, double angle) {


        new Constants();

        double encAngle = axisMotor.getSelectedSensorPosition()/STEERING_RATIO/2048*(2*PI);
        double driveConstant = 204.8/(2*PI)*DRIVING_RATIO/WHEEL_RADIUS_METERS;
        double angleConstant = 2048/(2*PI)*STEERING_RATIO;

        speed *= driveConstant;

        double encTrue = encAngle%(2*PI);

        double dTheta = angle - encTrue;

        if (abs(-2*PI + dTheta) < abs(dTheta)) {
            if (abs(-2*PI + dTheta) < abs(2*PI + dTheta)) {
                dTheta = -2*PI + dTheta;
            } else {
                dTheta = 2*PI + dTheta;
            }
        } else if (abs(dTheta) > abs(2*PI + dTheta)) {
            dTheta = 2*PI + dTheta;
        }

        double angleFinal = encAngle + dTheta;
        angleFinal *= angleConstant;

        this.driveMotor.set(ControlMode.Velocity, speed);
        if (speed < 120) {
            this.axisMotor.set(ControlMode.Velocity, 0);
            this.driveMotor.set(ControlMode.Velocity, 0);
        } else {
            this.axisMotor.set(ControlMode.Position, angleFinal);
            this.driveMotor.set(ControlMode.Velocity, speed);
        }
    }

    public void zero() {
        absEncDeg = this.coder.getAbsolutePosition();
        this.axisMotor.setSelectedSensorPosition(-(absEncDeg/360)*2048*STEERING_RATIO);
        this.axisMotor.set(ControlMode.Velocity, 0);
    }




}

package Libraries;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Gyro {

    private LinearOpMode opMode;

    private BNO055IMU gyro;
    private Orientation angles;

    public Gyro(LinearOpMode opMode, boolean IMUenabled) {
        this.opMode = opMode;

        if (IMUenabled){
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json";
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            gyro = this.opMode.hardwareMap.get(BNO055IMU.class, "imu");
            gyro.initialize(parameters);
        }

    }


    public void updateGyroValues() {
        angles = gyro.getAngularOrientation();

    }

    public Acceleration getAcceleration() {
        updateGyroValues();
        return gyro.getAcceleration();

    }

    public double getGyroYaw() {
        updateGyroValues();
        return angles.firstAngle;

    }

    public double getGyroPitch() {
        updateGyroValues();
        return angles.secondAngle;

    }

    public double getGyroRoll() {
        updateGyroValues();
        return angles.thirdAngle;

    }

}


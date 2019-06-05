package org.firstinspires.ftc.TeamName.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.Hyperfang.Sensors.IMU;
import org.firstinspires.ftc.Hyperfang.Sensors.PID;
import org.firstinspires.ftc.Hyperfang.Sensors.Range;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeManagerImpl;

//Singleton Design Pattern
public class Base {

    //Singleton object
    private static Base obj;
    private static OpMode mOpMode;

    //Encoder Variables
    private static final double COUNTS_PER_MOTOR_REV = 1440;     // Rev Orbital 40:1
    private static final double DRIVE_GEAR_REDUCTION = 15 / 10.0;// Drive-Train Gear Ratio.
    private static final double WHEEL_DIAMETER_INCHES = 4.0;     // AndyMark Stealth/Omni Wheels
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    //Tolerance Variables for the movement methods.
    private static final double encTolerance = 1;
    private static final double turnTolerance = 2;
    private static final double rangeTolerance = 1;

    //Logging variables of the Robot position.
    private double curEnc;
    private double curAng;
    private double curDis;

    //Drive-train Motors.
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;

    //Sensor Instantiation
    private IMU imu;
    private Range rSensor;

    //PID Variables
    private PID turnPID;
    private PID rangePID;
    private boolean loopOneT;
    private boolean loopOneR;
    private int rampPow;

    //Initializes the base object.
    public static Base getInstance() {
        if (obj == null) {
            throw new NullPointerException("Base Object not created with an OpMode.");
        }
        return obj;
    }

    //Initializes the base object.
    public static Base getInstance(OpMode opMode) {
        if (obj == null) {
            obj = new Base(opMode);
        }
        return obj;
    }

    //Initializes the base object.
    private Base(OpMode opMode) {
        mOpMode = opMode;
        frontLeft = mOpMode.hardwareMap.get(DcMotor.class, "frontL");
        frontRight = mOpMode.hardwareMap.get(DcMotor.class, "frontR");
        backLeft = mOpMode.hardwareMap.get(DcMotor.class, "backL");
        backRight = mOpMode.hardwareMap.get(DcMotor.class, "backR");
        rSensor = new Range("range", opMode);
        loopOneT = true;
        loopOneR = true;
        mOpMode.telemetry.addLine("Base Version 2 Initialized.");
    }



    //After testing the encoders we found out the FTC SDK clears encoder information (unlike motors)
    //in between OpModes. Due to this, this method must be called in init to set the encoders.
    public void ftcEnc() {
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        curEnc = 0;
        curDis = 0;
        resetEncoders();
    }

    //@TODO set negatives based on the robot hardware
    public void setPower(double left, double right)
    {
        backLeft.setPower(left);
        backRight.setPower(right);
        frontLeft.setPower(left);
        backRight.setPower(right);
    }



    //Stops the robot.
    public void stopMotors() {
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    //Resets the encoder count.
    public void resetEncoders() {
        curEnc = 0;
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public double getEncoderAvg() {
        double countZeros = 0;

        if (frontLeft.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (frontRight.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (backLeft.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (backRight.getCurrentPosition() == 0) {
            countZeros++;
        }

        if (countZeros == 4) {
            return 0;
        }

        return (Math.abs(frontLeft.getCurrentPosition()) +
                Math.abs(frontRight.getCurrentPosition()) +
                Math.abs(backRight.getCurrentPosition()) +
                Math.abs(backLeft.getCurrentPosition())) / (4 - countZeros);

    }


    //Sets a target position for all our encoders. (Inches)


    public void moveEncoder(double power, double distance, double timeout) {
        ElapsedTime time = new ElapsedTime();

        resetEncoders();
        time.reset();

        while (getEncoderAvg() < distance && time.seconds() < timeout) {
            setPower(power,power);

        }
        stopMotors();

    }

    public void turnPID(double kP, double kI, double kD, double angle, boolean right, double timeout) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        double initialAngle = getHeading();

        double error;
        double power;

        double proportional;
        double integral = 0;
        double derivative;

        double previousTime;
        double previousError = angle - Math.abs(getHeading() - initialAngle);

        while (Math.abs(getHeading() - (angle + initialAngle)) > 1 && time.seconds() < timeout) {
            error = angle - Math.abs(getHeading() - initialAngle);

            previousTime = time.seconds();

            proportional = error * kP;
            integral += error * (time.seconds() - previousTime) * kI;
            derivative = ((error - previousError) / (time.seconds() - previousTime)) * kD;

            power = proportional + integral + derivative;

            //@TODO make sure to change negative based on HW
            setPower(power, -power);


            previousError = error;
        }
        stopMotors();

    }

    //Returns the current heading value of the range sensor.
    public double getHeading() {
        return imu.getHeading();
    }
}




package LactoseIntolerantLibs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    private LinearOpMode opMode;

    private DcMotor intakeL;
    private DcMotor intakeR;

    private Servo pivotL;
    private Servo pivotR;

    private CRServo collectL;
    private CRServo collectR;

    public Intake (LinearOpMode opMode) {
        //EVERYTHING EXCEPT FOR PIVOT IS COMMENTED B/C OF WHAT'S ON THE ROBOT RN
        // TODO: 3/11/2019 Wire all parts of robot, and start testing all mechanisms
//        intakeL = opMode.hardwareMap.dcMotor.get("intakeL");
//        intakeR = opMode.hardwareMap.dcMotor.get("intakeR");

        pivotL = opMode.hardwareMap.servo.get("pivotL");
        pivotR = opMode.hardwareMap.servo.get("pivotR");

//        collectL = opMode.hardwareMap.crservo.get("collectL");
//        collectR = opMode.hardwareMap.crservo.get("collectR");
//
//        intakeL.setDirection(DcMotorSimple.Direction.FORWARD);
//        intakeR.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        collectL.setDirection(DcMotorSimple.Direction.FORWARD);
//        collectR.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void extend(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            intakeL.setPower(1);
            intakeR.setPower(1);

        }
        intakeL.setPower(0);
        intakeR.setPower(0);

    }

    public void retract(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            intakeL.setPower(-1);
            intakeR.setPower(-1);

        }
        intakeL.setPower(0);
        intakeR.setPower(0);

    }

    public void collect(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            collectL.setPower(1);
            collectR.setPower(1);

        }
        collectL.setPower(0);
        collectR.setPower(0);

    }

    public void spitOut(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            collectL.setPower(-0.7);
            collectR.setPower(-0.7);

        }
        collectL.setPower(0);
        collectR.setPower(0);

    }

    public void setPivotPos(double left, double right)
    {
        pivotL.setPosition(left);
        pivotR.setPosition(right);
    }

    public double getPivotLPos()
    {
        return pivotL.getPosition();
    }
    public double getPivotRPos()
    {
        return pivotR.getPosition();
    }


}

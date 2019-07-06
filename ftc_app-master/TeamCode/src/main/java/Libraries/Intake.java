package Libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    private LinearOpMode opMode;

    private DcMotor intakeMotor;
    private DcMotor intakeSlide;

    private Servo intakeGate;



    public Intake (LinearOpMode opMode) {

        intakeMotor = opMode.hardwareMap.dcMotor.get("intakeMotor");
        //intakeSlide = opMode.hardwareMap.dcMotor.get("intakeSlide");


        intakeGate = opMode.hardwareMap.servo.get("intakeGate");

    }

    public void extend(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            intakeSlide.setPower(1);
        }
        intakeSlide.setPower(0);
    }

    public void retract(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            intakeSlide.setPower(-1);
        }
        intakeSlide.setPower(0);
    }

    public void collect(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            intakeMotor.setPower(1);
        }
        intakeMotor.setPower(0);
    }

    public void spitOut(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            intakeMotor.setPower(-0.7);
        }
        intakeMotor.setPower(0);
    }

    public void setIntakeGate(double position)
    {
        intakeGate.setPosition(position);
    }


    public double getIntakeGatePosition()
    {
        return intakeGate.getPosition();
    }


}

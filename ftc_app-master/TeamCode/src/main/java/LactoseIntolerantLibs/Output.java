package LactoseIntolerantLibs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Output {

    private LinearOpMode opMode;

    private DcMotor output;

    private Servo deposit;

    public Output(LinearOpMode opMode) {
        this.opMode = opMode;

        output = opMode.hardwareMap.dcMotor.get("output");

        deposit = opMode.hardwareMap.servo.get("deposit");

        output.setDirection(DcMotorSimple.Direction.FORWARD);
        output.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    public void extend(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            output.setPower(1);

        }
        output.setPower(0);

    }

    public void retract(double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            output.setPower(-1);

        }
        output.setPower(0);

    }

    public void deposit() {
        deposit.setPosition(0);

    }

}

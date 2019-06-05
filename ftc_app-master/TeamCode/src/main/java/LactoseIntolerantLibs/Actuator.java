package LactoseIntolerantLibs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Actuator {

    private LinearOpMode opMode;

    private DcMotor actuator;

    public Actuator(LinearOpMode opMode) {
        this.opMode = opMode;

        actuator = opMode.hardwareMap.dcMotor.get("actuator");

        actuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void hang(double power, double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            actuator.setPower(power);

        }
        actuator.setPower(0);

    }

    public void dehang(double power, double timeout) {
        ElapsedTime time = new ElapsedTime();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            actuator.setPower(-power);

        }
        actuator.setPower(0);

    }


}

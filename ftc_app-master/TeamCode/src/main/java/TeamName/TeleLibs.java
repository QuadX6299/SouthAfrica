package TeamName;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class TeleLibs extends OpMode {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    private DcMotor intakeMotor;
    private DcMotor intakeSlide;

    private DcMotor arm;



    private Servo intakeGate;
    private Servo knocker;



    @Override
    public void init() {
        // MOTOR INITIALZATION
        fl = hardwareMap.dcMotor.get("fl"); //0
        fr = hardwareMap.dcMotor.get("fr"); //2
        bl = hardwareMap.dcMotor.get("bl"); //1
        br = hardwareMap.dcMotor.get("br"); //3

        //actuator = hardwareMap.dcMotor.get("actuator");

        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        intakeSlide = hardwareMap.dcMotor.get("intakeSpool");

        //output = hardwareMap.dcMotor.get("output");

        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeSlide.setDirection(DcMotorSimple.Direction.FORWARD);


        // SERVO INITIALIZATION
        intakeGate = hardwareMap.servo.get("intakeGate");
        knocker = hardwareMap.servo.get("knocker");



        telemetry.addLine("Initialized");
        telemetry.update();

    }

    // =======================================  DRIVE  =============================================

    public void arcadeDrive() {
        //checking for valid range to apply power (has to give greater power than .1)
        if (((Math.abs(Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y))) > .1) ||
                Math.abs(Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4) > .1) {

            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double theta = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = -gamepad1.right_stick_x;

            //as per unit circle cos gives x, sin gives you y
            double FL = r * Math.cos(theta) + rightX;
            double FR = r * Math.sin(theta) - rightX;
            double BL = r * Math.sin(theta) + rightX;
            double BR = r * Math.cos(theta) - rightX;

            //make sure you don't try and give power bigger than 1
            if (((Math.abs(FL) > 1) || (Math.abs(BL) > 1)) || ((Math.abs(FR) > 1) || (Math.abs(BR) > 1))) {
                FL /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));
                BL /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));
                FR /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));
                BR /= Math.max(Math.max(Math.abs(FL), Math.abs(FR)), Math.max(Math.abs(BL), Math.abs(BR)));

            }
            fl.setPower(FL);
            fr.setPower(FR);
            bl.setPower(BL);
            br.setPower(BR);

        }
        else {
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);

        }

    }

    // =======================================  INTAKE  ============================================

    public void intakeSlide() {
        double left_trigger = gamepad1.left_trigger;
        double right_trigger = gamepad1.right_trigger;

        if (left_trigger > 0.05) {
            intakeSlide.setPower(-left_trigger);
        }
        else if (right_trigger > 0.05) {
            intakeSlide.setPower(right_trigger);
        }
        else {
            intakeSlide.setPower(0);
        }

    }

//    public double getIntakeEncoder() {
//        return ((intakeL.getCurrentPosition() + intakeR.getCurrentPosition()) / 2);
//
//    }

    public void knocker()
    {
        double position = 0.1;
        if (gamepad1.a)
            knocker.setPosition(position + 0.1);
        else if (gamepad1.b)
            knocker.setPosition(position - 0.1);

    }

    public void collect() {
        if (gamepad2.left_bumper) {
            intakeMotor.setPower(0.7);

        }
        else if (gamepad2.right_bumper) {
            intakeMotor.setPower(-0.7);

        }
        intakeMotor.setPower(0.0);

    }

    // ==========================================  OUTPUT  =========================================

    public void armMove() {
        double outputPower = gamepad2.right_stick_y;

        if (Math.abs(outputPower) > 0.08) {
            arm.setPower(outputPower);

        }
        else {
           arm.setPower(0);

        }

    }



}

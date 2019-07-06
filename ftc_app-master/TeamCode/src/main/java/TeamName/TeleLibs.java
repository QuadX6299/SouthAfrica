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

    public Servo intakeGate;
    public Servo knocker;

    @Override
    public void init() {
        // MOTOR INITIALZATION
        fl = hardwareMap.dcMotor.get("fl"); //0
        fr = hardwareMap.dcMotor.get("fr"); //2
        bl = hardwareMap.dcMotor.get("bl"); //1
        br = hardwareMap.dcMotor.get("br"); //3

        arm = hardwareMap.dcMotor.get("arm");

        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        //intakeSlide = hardwareMap.dcMotor.get("intakeSpool");


        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.FORWARD);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        //intakeSlide.setDirection(DcMotorSimple.Direction.FORWARD);


        //SERVO INITIALIZATION
        intakeGate = hardwareMap.servo.get("intakeGate");
        knocker = hardwareMap.servo.get("knocker");

        knocker.setPosition(0.9);
        intakeGate.setPosition(0.0);


        telemetry.addLine("Initialized");
        //telemetry.addData("intakeGatePos: ", intakeGate.getPosition());
        telemetry.update();

    }

    // =======================================  DRIVE  =============================================

//    public void strafe() {
//        if (gamepad1.dpad_right) {
//            fl.setPower(1.0);
//            fr.setPower(-1.0);
//            bl.setPower(-1.0);
//            br.setPower(1.0);
//
//        }
//        else if (gamepad1.dpad_left){
//            fl.setPower(-1.0);
//            fr.setPower(1.0);
//            bl.setPower(1.0);
//            br.setPower(-1.0);
//
//        } else {
//            fl.setPower(0.0);
//            fr.setPower(0.0);
//            bl.setPower(0.0);
//            br.setPower(0.0);
//
//        }
//
//    }

    public void drive()
    {
        if (Math.abs(gamepad1.left_stick_y) > .01 ) {
            fl.setPower(gamepad1.left_stick_y);
            bl.setPower(-gamepad1.left_stick_y);
            fr.setPower(gamepad1.left_stick_y);
            br.setPower(-gamepad1.left_stick_y);

        }
        else if (gamepad1.right_stick_x < -.01 || gamepad1.right_stick_x > .01 ) {
            fl.setPower(-gamepad1.right_stick_x );
            fr.setPower(gamepad1.right_stick_x );
            bl.setPower(gamepad1.right_stick_x);
            br.setPower(-gamepad1.right_stick_x);

        } else if (Math.abs(gamepad1.left_stick_x )> 0.01)
        {
            fl.setPower(-gamepad1.left_stick_x);
            bl.setPower(-gamepad1.left_stick_x);
            fr.setPower(gamepad1.left_stick_x);
            br.setPower(gamepad1.left_stick_x);





        } else {
            fl.setPower(0.0);
            bl.setPower(0.0);
            fr.setPower(0.0);
            br.setPower(0.0);
        }
    }

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


        //down
        if (gamepad2.a)
            knocker.setPosition(0.5);
        //up
        else if (gamepad2.b)
            knocker.setPosition(0.9);

    }


    public void intakeGateMove(){


        //closed
        if(gamepad1.a)
        {
            intakeGate.setPosition(0.0);
        }

        //open
        if(gamepad1.b)
        {
            intakeGate.setPosition(0.3);
        }
    }

    public void collect() {
        double left_trigger = gamepad2.left_trigger;
        double right_trigger = gamepad2.right_trigger;

        if (left_trigger > 0.05) {
            intakeMotor.setPower(left_trigger);
        }
        else if (right_trigger > 0.05) {
            intakeMotor.setPower(-right_trigger);
        }
        else {
            intakeMotor.setPower(0);
        }

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

package LactoseIntolerant;

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

    private DcMotor intakeL;
    private DcMotor intakeR;

    private DcMotor output;

    private CRServo collectL;
    private CRServo collectR;

    private DcMotor actuator;

    private Servo pivotL;
    private Servo pivotR;

    private Servo deposit;

    private Thread retractThread;
    private Thread scoreThread;

    private double intakeInitEncoder;
    private double outputInitEncoder;
    private boolean readyToScore;

    @Override
    public void init() {
        // MOTOR INITIALZATION
        fl = hardwareMap.dcMotor.get("fl"); //0
        fr = hardwareMap.dcMotor.get("fr"); //2
        bl = hardwareMap.dcMotor.get("bl"); //1
        br = hardwareMap.dcMotor.get("br"); //3

        //actuator = hardwareMap.dcMotor.get("actuator");

        intakeL = hardwareMap.dcMotor.get("intakeL");
        intakeR = hardwareMap.dcMotor.get("intakeR");

        //output = hardwareMap.dcMotor.get("output");

        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeL.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeR.setDirection(DcMotorSimple.Direction.REVERSE);


        // SERVO INITIALIZATION
        //pivotL = hardwareMap.servo.get("pivotL");
//        pivotR = hardwareMap.servo.get("pivotR");
//        deposit = hardwareMap.servo.get("outputServo");

        //left manip VEX motors out for now, cause not fully wired, add back once wired
        //collectL = hardwareMap.crservo.get("collectL");
        //collectR = hardwareMap.crservo.get("collectR");
        //output.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeInitEncoder = getIntakeEncoder();
        readyToScore = false;

        // initializing threads
        //retractThread = new Thread(retract);
        //scoreThread = new Thread(score);

        telemetry.addData("Intake encoder", intakeInitEncoder);

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    // =====================================  RUNNABLES  ===========================================

//    public Runnable retract = new Runnable() {
//        @Override
//        public void run() {
//            if (gamepad2.b) {
//                 while (-getIntakeEncoder() < 0) {
//                     intakeL.setPower(-1);
//                     intakeR.setPower(-1);
//
//                 }
//                 intakeL.setPower(0);
//                 intakeR.setPower(0);
//
//                 pivotL.setPosition(0.5); //TODO TEST CONSTANT
//                 pivotR.setPosition(0.5); // TODO TEST CONSTANT
//
//                ElapsedTime time = new ElapsedTime();
//
//                while (time.seconds() < 0.5) {
//                    collectL.setPower(0.7);
//                    collectR.setPower(0.7);
//
//                }
//                collectL.setPower(0);
//                collectR.setPower(0);
//
//                readyToScore = true;
//
//                Thread.currentThread().interrupt();
//
//            }
//
//        }
//    };

//    public Runnable score = new Runnable() {
//        @Override
//        public void run() {
//            if (readyToScore) {
//                outputInitEncoder = output.getCurrentPosition();
//
//                while (Math.abs(output.getCurrentPosition() - outputInitEncoder) < 1000) { //TODO TEST CONSTANT
//                    output.setPower(1);
//
//                }
//                output.setPower(0);
//
//                deposit.setPosition(0.5); //TODO TEST CONSTANT
//
//                outputInitEncoder = output.getCurrentPosition();
//                while (Math.abs(output.getCurrentPosition() - outputInitEncoder) < 1000) { //TODO TEST CONSTANT
//                    output.setPower(-1);
//
//                }
//                output.setPower(0);
//
//            }
//            readyToScore = false;
//
//            Thread.currentThread().interrupt();
//
//        }
//    };
//
//    @Override
//    public void stop() {
//        retractThread.interrupt();
//        scoreThread.interrupt();
//
//    }
//
//    @Override
//    public void start() {
//        retractThread.start();
//        scoreThread.start();
//
//    }


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

    public void intakeSlides() {
        double left_trigger = gamepad1.left_trigger;
        double right_trigger = gamepad1.right_trigger;

        if (left_trigger > 0.05) {
            //the right intake slide is set to reverse, may have to change that
            intakeL.setPower(-left_trigger);
            intakeR.setPower(-left_trigger);

        }
        else if (right_trigger > 0.05) {
            intakeL.setPower(right_trigger);
            intakeR.setPower(right_trigger);

        }
        else {
            intakeL.setPower(0);
            intakeR.setPower(0);

        }

    }

    public double getIntakeEncoder() {
        return ((intakeL.getCurrentPosition() + intakeR.getCurrentPosition()) / 2);

    }

    public void collect() {
        if (gamepad2.left_bumper) {
            collectL.setPower(0.7);
            collectR.setPower(0.7);

        }
        else if (gamepad2.right_bumper) {
            collectL.setPower(-0.7);
            collectR.setPower(-0.7);

        }
        collectL.setPower(0);
        collectR.setPower(0);

    }

    // ==========================================  OUTPUT  =========================================

    public void outputSlides() {
        double outputPower = gamepad2.right_stick_y;

        if (Math.abs(outputPower) > 0.08) {
            output.setPower(outputPower);

        }
        else {
            output.setPower(0);

        }

    }

    // ==========================================  ACTUATOR  =======================================

    public void actuator() {
        boolean dpad_Up = gamepad2.dpad_up;
        boolean dpad_down = gamepad2.dpad_down;

        if (dpad_Up) {
            actuator.setPower(1);

        }
        else if (dpad_down) {
            actuator.setPower(-1);

        }
        else {
            actuator.setPower(0);

        }

    }


}

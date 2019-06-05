package org.firstinspires.ftc.TeamName.Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Controls {
    //Robot Object Instantiation
    private static Controls obj;
    private OpMode mOpMode;

    //Initializes the Controls object.
    public static Controls getInstance() {
        if (obj == null) {
            throw new NullPointerException("Control Object not created with an OpMode.");
        }
        return obj;
    }

    //Initializes the Controls object.
    public static Controls getInstance(OpMode opMode) {
        if (obj == null) {
            obj = new Controls(opMode);
        }
        return obj;
    }

    //Initializes the controls object.
    private Controls(OpMode opMode) {
        mOpMode = opMode;
        Base.getInstance(opMode);
        mOpMode.gamepad1.setJoystickDeadzone(.075f);
        mOpMode.gamepad2.setJoystickDeadzone(.075f);
        mOpMode.telemetry.addLine("Controls initiated");
    }

    //Initializes the robot movement.
    public void initRobot() {
        Base.getInstance(mOpMode).ftcEnc();
    }

    //Drive Method
    //Arcade Mode uses left stick to go forward, and right stick to turn.
    public void moveArcade() {
        double linear = -mOpMode.gamepad1.left_stick_y;
        double turn = -mOpMode.gamepad1.right_stick_x;
        //TODO make arcade move method
    }

    //Drive Method
    //Tank Mode uses one stick to control each wheel.
    public void moveTank() {
        //In this case, set power left and right.
        Base.getInstance().setPower(-mOpMode.gamepad1.left_stick_y, -mOpMode.gamepad1.right_stick_y);
    }


}

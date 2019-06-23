package LactoseIntolerant;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import LactoseIntolerantLibs.Drivetrain;
import LactoseIntolerantLibs.Intake;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
        (name = "ServoTest", group = "Auto")

public class ServoTest extends LinearOpMode {
    Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {

        intake = new Intake(this);
        intake.setPivotPos(0.75,0.25);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        intake.setPivotPos(0.5,0.5);
        telemetry.addData("Left: ", intake.getPivotLPos());
        telemetry.addData("Right: ", intake.getPivotRPos());
        telemetry.update();
    }
}

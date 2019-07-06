package TeamName;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Libraries.Drivetrain;
import Libraries.Intake;
import Libraries.Knocker;
import Libraries.Output;

@TeleOp(name = "Auto", group = "Auto")

public class auto extends LinearOpMode {
    Drivetrain dt;
    Output output;
    Intake intake;
    Knocker knocker;


    @Override
    public void runOpMode() {
        dt = new Drivetrain(this);
        output = new Output(this);
        intake = new Intake(this);
        knocker = new Knocker(this);

        waitForStart();

        dt.setPower(0.75, 2500);
        dt.turn(0.5, 500, true);
        dt.sleep(1000);
        dt.setPower(0.75, 900);
        dt.turn(0.5, 500, true);
        dt.setPower(0.75, 250);
        dt.turn(0.5, 1000, true);
        dt.setPower(0.75, 700);
        dt.turn(0.5, 500, true);
        dt.setPower(0.75, 1250);
        dt.turn(0.5, 500, true);
        dt.setPower(0.75, 2000);
        dt.setPower(0.75, 2000);
        dt.turn(-0.5, 500, false);
        dt.stopMotors();
    }
}
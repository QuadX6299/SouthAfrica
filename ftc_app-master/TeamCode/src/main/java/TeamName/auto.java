package TeamName;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Libraries.Drivetrain;
import Libraries.Intake;
import Libraries.Knocker;
import Libraries.Output;


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

        dt.setPower(0.75);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dt.stopMotors();

        dt.turn(0.5,true);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dt.stopMotors();

    }
}


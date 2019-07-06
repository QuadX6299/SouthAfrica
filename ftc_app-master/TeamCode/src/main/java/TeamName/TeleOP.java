package TeamName;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOP", group = "TeleOp")
public class TeleOP extends TeleLibs {

    public void loop() {
        drive();



        intakeSlide();
        collect();
        knocker();
        armMove();

        intakeGateMove();
//
        if (gamepad1.a)
        {
            intakeGate.setPosition(0.0);
        }
        if (gamepad1.b)
        {
            intakeGate.setPosition(0.3);
        }
//
        telemetry.addData("intakeGatePos:", intakeGate.getPosition());
        telemetry.addData("knockerPos:", knocker.getPosition());
    }
}

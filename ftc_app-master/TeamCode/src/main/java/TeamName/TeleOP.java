package TeamName;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOP", group = "TeleOp")
public class TeleOP extends TeleLibs {

    public void loop() {
        arcadeDrive();
        intakeSlide();
        collect();
        knocker();
        armMove();
    }
}

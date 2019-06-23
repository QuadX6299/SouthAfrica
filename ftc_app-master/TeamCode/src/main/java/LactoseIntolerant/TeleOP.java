package LactoseIntolerant;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "DownWithGluten", group = "TeleOp")
public class TeleOP extends TeleLibs {

    public void loop() {
        arcadeDrive();
        //actuator();
        intakeSlides();
        //outputSlides();
        //collect();

    }
}

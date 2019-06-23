package Libraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Knocker {

    private LinearOpMode opMode;
    private Servo knocker;



    public Knocker(LinearOpMode opMode) {
        knocker = opMode.hardwareMap.servo.get("knocker");
    }


    public void setKnocker(double position)
    {
        knocker.setPosition(position);
    }


    public double getKnockerPosition()
    {
        return knocker.getPosition();
    }


}

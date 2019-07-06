//package TeamName;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import Libraries.Drivetrain;
//import Libraries.Intake;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//@Autonomous(name = "ServoTest", group = "Auto")
//
//public class ServoTest extends LinearOpMode {
//    Intake intake;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        intake = new Intake(this);
//        intake.setIntakeGate(0.75);
//
//        telemetry.addLine("Initialized");
//        telemetry.update();
//
//        waitForStart();
//
//        intake.setIntakeGate(0.5);
//
//        telemetry.addData("Gate Position: ", intake.getIntakeGatePosition());
//        telemetry.update();
//    }
//}

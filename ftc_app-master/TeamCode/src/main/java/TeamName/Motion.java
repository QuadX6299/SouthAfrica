//package TeamName;
//
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
//import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//
//// TODO: FOR THE CSV STUFF, SHOULDN'T WE START TIMER WHEN WE START, NOT WHEN WE INIT?
//
//@TeleOp
//        (name = "Test", group = "TeleOp")
//public class Motion extends TeleLibs {
//
//    private DcMotor fl;
//    private DcMotor fr;
//    private DcMotor bl;
//    private DcMotor br;
//
//    private BNO055IMU gyro;
//    private Orientation angles;
//
//    ElapsedTime time = new ElapsedTime();
//
//    boolean toggle = false;
//    StringBuilder saveTo;
//
//    public void init() {
//        fl = hardwareMap.dcMotor.get("fl");
//        fr = hardwareMap.dcMotor.get("fr");
//        bl = hardwareMap.dcMotor.get("bl");
//        br = hardwareMap.dcMotor.get("br");
//
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
//        parameters.loggingEnabled = true;
//        parameters.loggingTag = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//        gyro = hardwareMap.get(BNO055IMU.class, "imu");
//        gyro.initialize(parameters);
//
//        saveTo = new StringBuilder();
//
//        time.reset();
//
//        saveTo.append("Time,X-Accel,Y-Accel,Z-Accel\n");
//
//    }
//
//    public void loop() {
//        if (gamepad1.x) {
//            while (gamepad1.x);
//
//            toggle = !toggle;
//
//        }
//
//        if (toggle) {
//            fl.setPower(1);
//            fr.setPower(1);
//            bl.setPower(1);
//            br.setPower(1);
//
//            Acceleration a = gyro.getAcceleration();
//            saveTo.append("" + time.milliseconds() + "," + a.xAccel + "," + a.yAccel + "," + a.zAccel + "\n");
//
//        }
//        if (gamepad1.y) {
//            CSV.writeToCSV(saveTo.toString());
//
//        }
//    }
//}
//
//

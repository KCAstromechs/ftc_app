package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.camera.VisionBaseOliver;

@Disabled
@Autonomous(name="speedtest", group = "test")
public class zspeedtesting extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseOliver driveBase = new DriveBaseOliver(false, true, this);
        VisionBaseOliver camera = new VisionBaseOliver(true, true, this);
        RobotBaseOliver robotBase = new RobotBaseOliver(true, this);
        waitForStart();

        driveBase.frontLeft.setPower(1);
        driveBase.frontRight.setPower(-1);
        driveBase.backLeft.setPower(1);
        driveBase.backRight.setPower(-1);

        sleep(1000);

        driveBase.frontLeft.setPower(0);
        driveBase.frontRight.setPower(0);
        driveBase.backLeft.setPower(0);
        driveBase.backRight.setPower(0);

        while(true){
            telemetry.addData("gyro", driveBase.zRotation);
            telemetry.update();
        }
    }
}
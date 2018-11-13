package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Rover Basic", group = "test")
public class autoSlaHappyRover extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseSlaHappy driveTrain = new DriveBaseSlaHappy(true, false, this);
        VisionBaseSlaHappy camera = new VisionBaseSlaHappy(true, true, this);
        RobotBaseSlaHappy robotBase = new RobotBaseSlaHappy(true, this);
        waitForStart();

        VisionBaseSlaHappy.MINERALS result = camera.analyzeSample(450, 700, 0, 1200);

        if (result == VisionBaseSlaHappy.MINERALS.LEFT) {
            telemetry.addLine("LEFT position");
        }
        if (result == VisionBaseSlaHappy.MINERALS.CENTER) {
            telemetry.addLine("CENTER position");
        }
        if (result == VisionBaseSlaHappy.MINERALS.RIGHT) {
            telemetry.addLine("RIGHT position");
        }
        telemetry.update();

        sleep(3000);


    }
}

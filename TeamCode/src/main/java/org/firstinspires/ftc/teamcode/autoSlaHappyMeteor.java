package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Meteor Basic", group = "test")
public class autoSlaHappyMeteor extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseSlaHappy driveTrain = new DriveBaseSlaHappy(true, true, this);
        VisionBaseSlaHappy camera = new VisionBaseSlaHappy(true, true, this);
        RobotBaseSlaHappy robotBase = new RobotBaseSlaHappy(true, this);
        waitForStart();

        robotBase.land();

        VisionBaseSlaHappy.MINERALS result = camera.analyzeSample(450, 700, 0, 1200);
        driveTrain.hasBeenZeroed = false;


        if (result == VisionBaseSlaHappy.MINERALS.LEFT) {
            telemetry.addLine("LEFT position");
            driveTrain.turnGyro(335, .5);
        }
        if (result == VisionBaseSlaHappy.MINERALS.CENTER) {
            driveTrain.turnGyro(0, .5);
            telemetry.addLine("CENTER position");
        }
        if (result == VisionBaseSlaHappy.MINERALS.RIGHT) {
            driveTrain.turnGyro(25, .5);
            telemetry.addLine("RIGHT position");
        }
        telemetry.update();

        sleep(3000);


    }
}

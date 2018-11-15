package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Meteor Basic", group = "test")
public class autoSlaHappyMeteor extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseSlaHappy driveBase = new DriveBaseSlaHappy(false, true, this);
        VisionBaseSlaHappy camera = new VisionBaseSlaHappy(true, true, this);
        RobotBaseSlaHappy robotBase = new RobotBaseSlaHappy(true, this);
        waitForStart();

        //robotBase.land();

        VisionBaseSlaHappy.MINERALS result = camera.analyzeSample(450, 700, 0, 1200);
        driveBase.hasBeenZeroed = false;

        driveBase.driveStraight(14, 0, 0.25);

        if (result == VisionBaseSlaHappy.MINERALS.LEFT) {
            telemetry.addLine("LEFT position");
            driveBase.strafe(14, 0, 1);
        }
        if (result == VisionBaseSlaHappy.MINERALS.CENTER) {
            telemetry.addLine("CENTER position");
        }
        if (result == VisionBaseSlaHappy.MINERALS.RIGHT) {
            telemetry.addLine("RIGHT position");
            driveBase.strafe(14, 0, -1);
        }
        telemetry.update();

        driveBase.driveStraight(14, 0);

        sleep(3000);


    }
}

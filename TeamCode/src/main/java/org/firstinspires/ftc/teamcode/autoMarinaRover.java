package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.camera.VisionBaseMarina;

@Autonomous(name="Marina Rover", group = "marina")
public class autoMarinaRover extends LinearOpMode {

    Servo led;

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseMarinaAdvanced driveBase = new DriveBaseMarinaAdvanced(false, true, this);
        VisionBaseMarina camera = new VisionBaseMarina(false, this,100, 350, 0, 1280);
        RobotBaseMarina robotBase = new RobotBaseMarina(true, this);

        led = hardwareMap.servo.get("elleedee");

        waitForStart();

        robotBase.land();

        driveBase.hasBeenZeroed = false;

        sleep(1000);

        led.setPosition(0.7745);

        driveBase.driveStraight(1.5, 0, -0.4);

        VisionBaseMarina.MINERALS result = camera.analyzeSampleNoSave();

        led.setPosition(0.6935);

//        sleep(5000);

        if (result == VisionBaseMarina.MINERALS.RIGHT) {
            driveBase.turn(120, 0.6);
            driveBase.driveStraight(33, 120, false);
            driveBase.turn(42);
            driveBase.driveStraight(33, 42, false);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.turn(95, 0.6);
            driveBase.driveStraight(27, 95, false);
            driveBase.turn(75);
            driveBase.driveStraight(23, 75, false);
        } else if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.turn(60, 0.6);
            driveBase.driveStraight(28, 60, false);
            driveBase.turn(114);
            driveBase.driveStraight(24, 114, false);
        }

        if(result != VisionBaseMarina.MINERALS.RIGHT) driveBase.turn(45);

        robotBase.deployMarker();

        sleep(1414);

        driveBase.turn(135);

        driveBase.strafe(8, 135, -0.8, 2);

        driveBase.driveStraight(45, 138,135, true, -1600, 4);
    }
}

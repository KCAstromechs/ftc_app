package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.camera.VisionBaseMarina;

@Disabled
@Autonomous(name="Meteor Marina Two Block", group = "marina")
public class autoMarinaMeteorTwoBlockOld extends LinearOpMode {

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

        driveBase.strafe(10, 0, 0.8);

        if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.driveStraight(10, 0, false);
        } else if (result == VisionBaseMarina.MINERALS.RIGHT) {
            driveBase.driveStraight(16, 0, true);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.driveStraight(1, 0, 0.8);
        }

        driveBase.strafe(6, 0, 0.8);
        driveBase.strafe(8, 0, -0.8);

        if (result == VisionBaseMarina.MINERALS.RIGHT) {
            driveBase.driveStraight(45, 0, false);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.driveStraight(32, 0, false);
        } else if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.driveStraight(21, 0, false);
        }

        driveBase.turn(230, 0.6);

        driveBase.strafe(32, 230, 0.8);

        robotBase.deployMarker();

        sleep(1414);/*

        driveBase.strafe(8, 225, -0.8);

        driveBase.turn(265, 0.6);

        if (result == VisionBaseMarina.MINERALS.RIGHT) {
            //driveBase.driveStraight(45, 270, false);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.driveStraight(10, 270, false);
        } else if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.driveStraight(20, 270, false);
        }

        driveBase.strafe(10, 270, 0.8);
        driveBase.strafe(10, 270, -0.8);*/
        if (result == VisionBaseMarina.MINERALS.RIGHT) {
            driveBase.turn(170, 0.8);
            driveBase.driveStraight(24, 160, false);
            driveBase.driveStraight(24, 160, true);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.turn(205, 0.8);
            driveBase.driveStraight(21, 195, false);
            driveBase.driveStraight(21, 195, true);
        } else if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.driveStraight(24, 225, false);
            driveBase.driveStraight(24, 225, true);
        }
        driveBase.turn(300, 0.6);

        driveBase.driveStraight(46, 308,315, true, -1600, 0);
    }
}

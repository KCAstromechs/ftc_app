package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.camera.VisionBaseCuddlefish;

@Autonomous(name="Meteor Cuddlefish Two Block", group = "cuddlefish")
public class autoCuddlefishMeteorTwoBlock extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        oscillationTest2 driveBase = new oscillationTest2(false, true, this);
        VisionBaseCuddlefish camera = new VisionBaseCuddlefish(false, this,100, 350, 0, 1280);
        RobotBaseCuddlefish robotBase = new RobotBaseCuddlefish(true, this);

        waitForStart();

        robotBase.land();

        driveBase.hasBeenZeroed = false;

        sleep(1000);

        driveBase.driveStraight(1.5, 0, -0.4);

        VisionBaseCuddlefish.MINERALS result = camera.analyzeSampleNoSave();

        driveBase.strafe(10, 0, 0.8);

        if (result == VisionBaseCuddlefish.MINERALS.LEFT) {
            driveBase.driveStraight(10, 0, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            driveBase.driveStraight(16, 0, true);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.driveStraight(1, 0, 0.8);
        }

        driveBase.strafe(10, 0, 0.8);
        driveBase.strafe(12, 0, -0.8);

        if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            driveBase.driveStraight(45, 0, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.driveStraight(34, 0, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.LEFT) {
            driveBase.driveStraight(25, 0, false);
        }

        driveBase.turn(230, 0.6);

        driveBase.strafe(32, 230, 0.8);

        robotBase.deployMarker();

        sleep(1414);/*

        driveBase.strafe(8, 225, -0.8);

        driveBase.turn(265, 0.6);

        if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            //driveBase.driveStraight(45, 270, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.driveStraight(10, 270, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.LEFT) {
            driveBase.driveStraight(20, 270, false);
        }

        driveBase.strafe(10, 270, 0.8);
        driveBase.strafe(10, 270, -0.8);*/
        if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            driveBase.turn(175, 0.8);
            driveBase.driveStraight(18, 165, false);
            driveBase.driveStraight(18, 165, true);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.turn(205, 0.8);
            driveBase.driveStraight(18, 195, false);
            driveBase.driveStraight(18, 195, true);
        } else if (result == VisionBaseCuddlefish.MINERALS.LEFT) {
            driveBase.driveStraight(24, 225, false);
            driveBase.driveStraight(24, 225, true);
        }
        driveBase.turn(305, 0.6);

        driveBase.driveStraight(42, 315, true, -1600, 0);
    }
}

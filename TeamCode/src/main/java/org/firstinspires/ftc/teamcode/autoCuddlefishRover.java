package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.camera.VisionBaseCuddlefish;

@Autonomous(name="Rover Cuddlefish", group = "cuddlefish")
public class autoCuddlefishRover extends LinearOpMode {

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

        VisionBaseCuddlefish.MINERALS result = camera.analyzeSample();

        driveBase.strafe(10, 0, 0.8);

        if (result == VisionBaseCuddlefish.MINERALS.LEFT) {
            driveBase.driveStraight(10, 0, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            driveBase.driveStraight(14, 0, true);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.driveStraight(1, 0, 0.8);
        }

        driveBase.strafe(10, 0, 0.8);
        driveBase.strafe(12, 0, -0.8);

        if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            driveBase.driveStraight(45, 0, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.driveStraight(35, 0, false);
        } else if (result == VisionBaseCuddlefish.MINERALS.LEFT) {
            driveBase.driveStraight(25, 0, false);
        }

        driveBase.turn(40, 0.6);

        driveBase.strafe(32, 40, 0.8);

        robotBase.deployMarker();

        sleep(1414);

        driveBase.turn(130, 0.6);

        driveBase.driveStraight(38, 135, true, -1600, 0);

        //wuiowehrsagdior
    }
}

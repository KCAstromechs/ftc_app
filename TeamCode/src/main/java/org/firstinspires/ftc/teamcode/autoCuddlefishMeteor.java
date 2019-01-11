package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.camera.VisionBaseCuddlefish;

@Autonomous(name="Meteor Cuddlefish", group = "cuddlefish")
public class autoCuddlefishMeteor extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseCuddlefish driveBase = new DriveBaseCuddlefish(false, true, this);
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
            driveBase.driveStraight(6, 0, -0.8);
        } else if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            driveBase.driveStraight(10, 0, 0.8);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.driveStraight(2, 0, 0.8);
        }

        driveBase.strafe(10, 0, 0.8);
        driveBase.strafe(12, 0, -0.8);

        if (result == VisionBaseCuddlefish.MINERALS.RIGHT) {
            driveBase.driveStraight(34, 0, -0.8);
        } else if (result == VisionBaseCuddlefish.MINERALS.CENTER) {
            driveBase.driveStraight(24, 0, -0.8);
        } else if (result == VisionBaseCuddlefish.MINERALS.LEFT) {
            driveBase.driveStraight(14, 0, -0.8);
        }

        driveBase.turn(230, 0.6);

        driveBase.strafe(28, 230, 0.8);

        robotBase.deployMarker();

        sleep(1414);

        driveBase.turn(310, 0.6);

        driveBase.driveStraight(36, 310, 0.8);

        //wuiowehrsagdior
    }
}

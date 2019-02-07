package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.camera.VisionBaseMarina;

@Autonomous(name="Meteor Marina", group = "marina")
public class autoMarinaMeteor extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseMarinaAdvanced driveBase = new DriveBaseMarinaAdvanced(false, true, this);
        VisionBaseMarina camera = new VisionBaseMarina(false, this,100, 350, 0, 1280);
        RobotBaseMarina robotBase = new RobotBaseMarina(true, this);

        waitForStart();

        robotBase.land();

        driveBase.hasBeenZeroed = false;

        sleep(1000);

        driveBase.driveStraight(1.5, 0, -0.4);

        VisionBaseMarina.MINERALS result = camera.analyzeSample();

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

        sleep(1414);

        driveBase.turn(300, 0.6);

        //driveBase.driveStraight(42, 310, true);

        //this is the beginning of the end

        driveBase.driveStraight(46, 308,315, true, -1600, 0);
    }
}

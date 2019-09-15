package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.camera.VisionBaseMarina;

@Disabled
@Autonomous(name="Marina Rover", group = "marina")
public class autoMarinaRoverOld extends LinearOpMode {

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

        driveBase.strafe(8, 0, 0.8);
        driveBase.strafe(10, 0, -0.8);

        if (result == VisionBaseMarina.MINERALS.RIGHT) {
            driveBase.driveStraight(45, 0, false);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.driveStraight(32, 0, false);
        } else if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.driveStraight(21, 0, false);
        }

        driveBase.turn(40, 0.6);

        driveBase.strafe(32, 40, 0.8);

        robotBase.deployMarker();

        sleep(1414);

        driveBase.turn(130, 0.6);

        driveBase.driveStraight(38, 138,135, true, -1600, 0);

        //wuiowehrsagdior
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.camera.VisionBaseMarina;

@Autonomous(name="Marina Meteor Two Block", group = "marina")
public class autoMarinaMeteorTwoBlock extends LinearOpMode {

    Servo led;

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseMarinaAdvanced driveBase = new DriveBaseMarinaAdvanced(false, true, this);
        VisionBaseMarina camera = new VisionBaseMarina(false, this,100, 350, 0, 1280);
        RobotBaseMarina robotBase = new RobotBaseMarina(true, this);

        led = hardwareMap.servo.get("elleedee");

        driveBase.setSpeedLimit(0.68);

        waitForStart();

        robotBase.land();

        driveBase.hasBeenZeroed = false;

        sleep(1000);

        led.setPosition(0.7745);

        driveBase.driveStraight(1.5, 0, -0.4);

        VisionBaseMarina.MINERALS result = camera.analyzeSampleNoSave();

        led.setPosition(0.6935);

        if (result == VisionBaseMarina.MINERALS.RIGHT) {
            driveBase.turn(120, 0.6);
            driveBase.driveStraight(32, 120, false);
            driveBase.driveStraight(9.5, 120, 0.8);
            driveBase.turn(8);
            driveBase.driveStraight(46, 0, false);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.turn(95, 0.6);
            driveBase.driveStraight(26, 95, false);
            driveBase.driveStraight(9.5, 95, 0.8);
            driveBase.turn(8);
            driveBase.driveStraight(38, 0, false);
        } else if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.turn(60, 0.6);
            driveBase.driveStraight(28, 60, false);
            driveBase.driveStraight(9.5, 60, 0.8);
            driveBase.turn(8);
            driveBase.driveStraight(28, 0, false);
        }

        driveBase.driveStraightStall(0.8, 0, -0.4);
        driveBase.turn(320);

        driveBase.driveStraight(38, 318, false);

        driveBase.turn(225);

        robotBase.deployMarker();

        sleep(1010);

        if (result == VisionBaseMarina.MINERALS.RIGHT) {
           driveBase.turn(150);
           driveBase.driveStraight(28, 153, false);
           driveBase.driveStraight(26, 153, true);
        } else if (result == VisionBaseMarina.MINERALS.CENTER) {
            driveBase.turn(178);
            driveBase.driveStraight(25,183,false);
            driveBase.driveStraight(25,183,true);
        } else if (result == VisionBaseMarina.MINERALS.LEFT) {
            driveBase.driveStraight(32,235,false);
            driveBase.driveStraight(32,235,true);
        }
        driveBase.turn(313);

        driveBase.driveStraight(60, 312,315, true, -1600, 3.5);
    }
}

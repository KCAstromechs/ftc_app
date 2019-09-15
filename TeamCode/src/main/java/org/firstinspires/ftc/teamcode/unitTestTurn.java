package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="unitTestTurn")
public class unitTestTurn extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseMarinaAdvanced driveBase = new DriveBaseMarinaAdvanced(false, true, this);

        waitForStart();

        driveBase.turn(45);

        telemetry.addData("gyro", driveBase.zRotation);
        telemetry.update();

        while(!gamepad1.a);

        driveBase.turn(135);

        telemetry.addData("gyro", driveBase.zRotation);
        telemetry.update();

        while(!gamepad1.a);

        driveBase.turn(0);

        telemetry.addData("gyro", driveBase.zRotation);
        telemetry.update();

        while(!gamepad1.a);

        driveBase.turn(315);

        telemetry.addData("gyro", driveBase.zRotation);
        telemetry.update();

        while(!gamepad1.a);

        driveBase.turn(225);

        telemetry.addData("gyro", driveBase.zRotation);
        telemetry.update();

        while(!gamepad1.a);

        driveBase.turn(0);

        telemetry.addData("gyro", driveBase.zRotation);
        telemetry.update();

        while(!gamepad1.a);
    }
}

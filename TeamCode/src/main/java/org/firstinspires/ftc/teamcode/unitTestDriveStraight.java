package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="unitTestDriveStraight")
public class unitTestDriveStraight extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseMarinaAdvanced driveBase = new DriveBaseMarinaAdvanced(false, true, this);

        driveBase.setSpeedLimit(0.5);

        waitForStart();

        driveBase.driveStraight(12, 0, true);

        while(!gamepad1.a);

        driveBase.driveStraight(24, 0, true);

        while(!gamepad1.a);

        driveBase.driveStraight(36, 0, true);

        while(!gamepad1.a);

        driveBase.driveStraight(48, 0, true);

        while(!gamepad1.a);
    }
}

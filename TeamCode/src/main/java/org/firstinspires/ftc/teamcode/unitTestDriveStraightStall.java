package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="unitTestDriveStraightStall")
public class unitTestDriveStraightStall extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseMarinaAdvanced driveBase = new DriveBaseMarinaAdvanced(false, true, this);

        driveBase.setSpeedLimit(0.5);

        waitForStart();

        driveBase.driveStraightStall(1.8, 0, -0.28);

        while(!gamepad1.a);
    }
}

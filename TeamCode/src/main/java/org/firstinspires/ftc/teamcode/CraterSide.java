package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="CraterSideBasic", group = "test")
public class CraterSide extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveBaseSlaHappy driveTrain = new DriveBaseSlaHappy(true, true, this);

        waitForStart();

        driveTrain.turnGyro(90, .4);

    }
}

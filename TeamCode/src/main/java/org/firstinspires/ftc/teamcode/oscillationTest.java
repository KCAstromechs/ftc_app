package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="cup of tea")
public class oscillationTest extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {
        oscillationTest2 driveBase = new oscillationTest2(false, true, this);
        waitForStart();

        driveBase.driveStraight(1.5, 0, -0.4);


        driveBase.strafe(10, 0, 0.8);

        driveBase.driveStraight(1, 0, 0.8);

        driveBase.strafe(10, 0, 0.8);
        driveBase.strafe(12, 0, -0.8);

        driveBase.driveStraight(30, 0, false);


        driveBase.turn(40, 0.6);

        driveBase.strafe(32, 40, 0.8);

        sleep(1414);

        driveBase.turn(130, 0.6);

        driveBase.driveStraight(38, 135, true);
    }
}

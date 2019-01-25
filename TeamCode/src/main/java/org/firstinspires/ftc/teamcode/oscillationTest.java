package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="cup of tea")
public class oscillationTest extends LinearOpMode {

    boolean straight;

    @Override
    public void runOpMode() throws InterruptedException {
        oscillationTest2 driveBase = new oscillationTest2(false, true, this);

        while(opModeIsActive()) {
            if (gamepad1.a) {
                straight = true;
                break;
            }
            else if (gamepad1.b) {
                straight = false;
                break;
            }
        }

        waitForStart();

        if (straight)
            driveBase.driveStraight(18, 0, true);
        else
            driveBase.strafe(18, 0, 0.8);
    }
}

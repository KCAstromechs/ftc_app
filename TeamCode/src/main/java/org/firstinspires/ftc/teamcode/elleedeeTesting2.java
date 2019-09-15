package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="elleedeeTesting2", group="testing")
public class elleedeeTesting2 extends LinearOpMode {

    //init vars
    double a, b, c, d;

    Servo m1;

    @Override
    public void runOpMode() {
        m1 = hardwareMap.servo.get("elleedee");
        a = 0.6425;
        waitForStart();

        while(true) {
            m1.setPosition(a);
            telemetry.addData("v", m1.getPosition());
            telemetry.addData("a", a);
            telemetry.update();
            if(gamepad1.dpad_up) {
                a += 0.0001;
                sleep(40);
            } else if (gamepad1.dpad_down){
                a-=0.0025;
                sleep(40);
            }
        }
    }
}
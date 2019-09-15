package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="elleedeeTesting", group="testing")
public class elleedeeTesting extends OpMode {

    //init vars
    float a, b, c, d;

    Servo m1;

    @Override
    public void init() {
        m1 = hardwareMap.servo.get("elleedee");
        a = 0.6425f;
    }

    @Override
    public void loop() {

        m1.setPosition(0.7675);
        telemetry.addData("v",m1.getPosition());

    }

    @Override
    public void stop() {

    }
}
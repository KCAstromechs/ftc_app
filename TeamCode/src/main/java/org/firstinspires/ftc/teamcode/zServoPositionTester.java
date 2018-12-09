package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="servoPositionTester", group="zzzzzzz")
public class zServoPositionTester extends OpMode {

    //init vars
    Servo a;

    double servoPos;

    @Override
    public void init() {
        a = hardwareMap.servo.get("flap");    //change me to test different servos
    }

    @Override
    public void loop() {

        if(gamepad1.left_bumper){
            servoPos-=0.01;
        }
        if(gamepad1.right_bumper){
            servoPos+=0.01;
        }
        if(servoPos<0){
            servoPos = 0;
        }
        if(servoPos>1){
            servoPos=1;
        }
        a.setPosition(servoPos);
        telemetry.addData("servoPos", servoPos);
        telemetry.update();

    }

    @Override
    public void stop() {

    }
}
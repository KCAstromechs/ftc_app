package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="lifterTeleopTEST", group="Test")
public class lifterTeleopTEST extends OpMode {

    //init vars
    private double s;

    private DcMotor lift1, lift2;
    private Servo a;

    @Override
    public void init() {
        lift1 = hardwareMap.dcMotor.get("liftLeft");
        lift2 = hardwareMap.dcMotor.get("liftRight");
        a = hardwareMap.servo.get("latch");
    }

    @Override
    public void loop() {

        s = gamepad1.left_stick_y;

        lift1.setPower(s);
        lift2.setPower(-s);

        if(gamepad1.a) {
            a.setPosition(1);
        }
        if(gamepad1.b){
            a.setPosition(0);
        }
    }

    @Override
    public void stop() {

    }
}
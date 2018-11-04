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
    private Servo a, holdR, holdL;

    @Override
    public void init() {
        lift1 = hardwareMap.dcMotor.get("liftLeft");
        lift2 = hardwareMap.dcMotor.get("liftRight");
        a = hardwareMap.servo.get("latch");
        holdR = hardwareMap.servo.get("holdRight");
        holdL = hardwareMap.servo.get("holdLeft");
    }

    @Override
    public void loop() {

        s = gamepad1.left_stick_y;

        lift1.setPower(s);
        lift2.setPower(-s);

        if(gamepad1.a) {
            a.setPosition(0.75);
        }
        if(gamepad1.b){
            a.setPosition(0.45);
        }
        if(gamepad1.x){
            holdL.setPosition(0.8);
            holdR.setPosition(0.1);
        }
        if(gamepad1.y){
            holdL.setPosition(0.5);
            holdR.setPosition(0.5);
        }
    }

    @Override
    public void stop() {

    }
}
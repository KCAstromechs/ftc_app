package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name="aaaaaaaaaaaaaaaaaaaaaauto", group = "test")
public class aaaaaaaaaaaaaaaaaaaaaauto extends LinearOpMode {

    //init vars
    private DcMotor lift1, lift2;
    private Servo a, holdR, holdL;

    @Override
    public void runOpMode() throws InterruptedException {

        //initialize all bot stuff
        lift1 = hardwareMap.dcMotor.get("liftLeft");
        lift2 = hardwareMap.dcMotor.get("liftRight");
        a = hardwareMap.servo.get("latch");
        holdR = hardwareMap.servo.get("holdRight");
        holdL = hardwareMap.servo.get("holdLeft");

        waitForStart();

        //here be dragons
        //they be friends

        lift1.setPower(-1);
        lift2.setPower(1);

        sleep(1000);

        holdL.setPosition(0.8);
        holdR.setPosition(0.1);

        sleep(1000);

        lift1.setPower(1);
        lift2.setPower(-1);

        sleep(1000);

        lift1.setPower(0);
        lift2.setPower(0);

        sleep(1500);

        a.setPosition(0.75);

        sleep(500);
    }
}
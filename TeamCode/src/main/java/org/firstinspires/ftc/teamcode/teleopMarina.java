package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="teleop marina")
public class teleopMarina extends OpMode {

    //init vars
    private float left, right, left2, right2, leftT, rightT, frontLeftPower, backLeftPower, frontRightPower, backRightPower;
    private DcMotor frontRight, frontLeft, backRight, backLeft, extender, lift, climb, collector;
    private Servo led;
    private double f = 0;
    private double error = 0;
    private double targetT = 0;
    private boolean t = false;
    private boolean jInput = false;
    private boolean setColor = true;
    private int turbo = 9;
    private int turbo2 = 3;


    @Override
    public void init() {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        extender = hardwareMap.dcMotor.get("extender");
        collector = hardwareMap.dcMotor.get("collect");
        lift = hardwareMap.dcMotor.get("lift");
        climb = hardwareMap.dcMotor.get("climb");

        led = hardwareMap.servo.get("elleedee");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        extender.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        climb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        climb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extender.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extender.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        if(!jInput){
            if(gamepad1.left_stick_y>0.1 || gamepad2.left_stick_y>0.1 || gamepad1.right_stick_y>0.1 || gamepad2.right_stick_y>0.1){
                jInput = true;
                targetT = getRuntime()+90;
            }
        } else if (setColor) {
            if (getRuntime() > targetT) {
                led.setPosition(0.749);
                setColor = false;
            }
        } else if (getRuntime()>targetT+20){
            led.setPosition(0.66575);
        }

        if(!t){
            f = lift.getCurrentPosition();
            t = true;
        }

        turbo = 9;
        turbo2 = 3;

        left = (Math.abs(gamepad1.left_stick_y) < 0.05) ? 0 : gamepad1.left_stick_y;
        right = (Math.abs(gamepad1.right_stick_y) < 0.05) ? 0 : gamepad1.right_stick_y;
        left2 = (Math.abs(gamepad2.left_stick_y) < 0.05) ? 0 : gamepad2.left_stick_y;
        right2 = (Math.abs(gamepad2.right_stick_y) < 0.1) ? 0 : gamepad2.right_stick_y;
        leftT = (Math.abs(gamepad1.left_trigger) < 0.05) ? 0 : gamepad1.left_trigger;
        rightT = (Math.abs(gamepad1.right_trigger) < 0.05) ? 0 : gamepad1.right_trigger;

        frontLeftPower = left - rightT + leftT;
        backLeftPower = left + rightT - leftT;
        frontRightPower = right + rightT - leftT;
        backRightPower = right - rightT + leftT;

        reducePowers(Math.max(frontLeftPower, Math.max(backLeftPower, Math.max(frontRightPower, backRightPower))));

        if (gamepad1.right_bumper || gamepad1.left_bumper) turbo ++;

        frontRight.setPower((frontRightPower*turbo)/10);
        backRight.setPower((backRightPower*turbo)/10);
        frontLeft.setPower((frontLeftPower*turbo)/10);
        backLeft.setPower((backLeftPower*turbo)/10);

        extender.setPower(-left2);

        if(gamepad2.right_trigger>0.5 || gamepad2.left_trigger>0.5){
            collector.setPower(0);
        } else {
            if (gamepad2.dpad_down) {
                collector.setPower(0);
            } else if (gamepad2.dpad_left) {
                collector.setPower(-1);
            } else if (gamepad2.dpad_right) {
                collector.setPower(1);
            }
        }

        if (gamepad2.right_bumper) turbo2 ++;
        if (gamepad2.left_bumper) turbo2 ++;

        if(right2==0) {
            error = f - lift.getCurrentPosition();
            lift.setPower(error/810);
        } else {
            f = (lift.getCurrentPosition());
            lift.setPower((-right2*turbo2)/5);
        }

        if(gamepad1.dpad_down){
            climb.setPower(-1);
        } else if (gamepad1.dpad_up){
            climb.setPower(1);
        } else {
            climb.setPower(0);
        }

        telemetry.addData("climb", climb.getCurrentPosition());
        telemetry.addData("error", error);
        telemetry.addData("f", f);
        telemetry.addData("pos", lift.getCurrentPosition());
        telemetry.addData("power", lift.getPower());
        telemetry.addData("up", gamepad1.dpad_up);
        telemetry.addData("down", gamepad1.dpad_down);
        telemetry.addData("extender", extender.getCurrentPosition());
        telemetry.update();
    }

    private void reducePowers(float power) {

        if (power > 1.0) {

            float multiplier = 1/power;

            frontLeftPower *= multiplier;
            frontRightPower *= multiplier;
            backLeftPower *= multiplier;
            backRightPower *= multiplier;
        }
    }

    @Override
    public void stop() {}
}
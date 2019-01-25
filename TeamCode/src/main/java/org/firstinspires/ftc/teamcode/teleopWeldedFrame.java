package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="teleop cuttlefish")
public class teleopWeldedFrame extends OpMode {

    //init vars
    private float left, right, left2, right2, leftT, rightT, frontLeftPower, backLeftPower, frontRightPower, backRightPower;
    private DcMotor frontRight, frontLeft, backRight, backLeft, extender, lift, climb, collector;
    private double f = 0;
    private double error = 0;
    private boolean t = false;
    private int turbo = 3;
    private int turbo2 = 3;


    @Override
    public void init() {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        extender = hardwareMap.dcMotor.get("extender");
        collector = hardwareMap.dcMotor.ge  t("collect");
        lift = hardwareMap.dcMotor.get("lift");
        climb = hardwareMap.dcMotor.get("climb");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

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
        if(!t){
            f = lift.getCurrentPosition();
            t = true;
        }

        turbo = 3;
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

        if (gamepad1.right_bumper) turbo ++;
        if (gamepad1.left_bumper) turbo ++;

        frontRight.setPower((frontRightPower*turbo)/5);
        backRight.setPower((backRightPower*turbo)/5);
        frontLeft.setPower((frontLeftPower*turbo)/5);
        backLeft.setPower((backLeftPower*turbo)/5);

        extender.setPower(left2);

        if(gamepad2.a){
            collector.setPower(1);
        } else if (gamepad2.b){
            collector.setPower(-1);
        } else if (gamepad2.x){
            collector.setPower(0);
        }

        if (gamepad2.right_bumper) turbo2 ++;
        if (gamepad2.left_bumper) turbo2 ++;

        if(right2==0) {
            error = f - lift.getCurrentPosition();
            lift.setPower(error/1620);
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
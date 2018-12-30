package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="jank Teleop")
public class JankTeleop extends OpMode {

    //init vars
    float left, right, leftT, rightT, frontLeftPower, backLeftPower, frontRightPower, backRightPower;
    boolean a, y;
    DriveBaseOliver driveBase;
    Toggle toggleA, toggleY;
    DcMotor d, b, c;
    CRServo e;
    double f = 0;
    double error = 0;

    @Override
    public void init() {
        driveBase = new DriveBaseOliver(false, false, this);

        d = hardwareMap.dcMotor.get("lift");
        b = hardwareMap.dcMotor.get("rotate");
        c = hardwareMap.dcMotor.get("extender");
        e = hardwareMap.crservo.get("spin");
        b.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        b.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        toggleA = new Toggle();
        toggleY = new Toggle();
    }

    @Override
    public void loop() {


        left = (Math.abs(gamepad1.left_stick_y) < 0.05) ? 0 : gamepad1.left_stick_y;
        right = (Math.abs(gamepad1.right_stick_y) < 0.05) ? 0 : gamepad1.right_stick_y;
        leftT = (Math.abs(gamepad1.left_trigger) < 0.05) ? 0 : gamepad1.left_trigger;
        rightT = (Math.abs(gamepad1.right_trigger) < 0.05) ? 0 : gamepad1.right_trigger;

        frontLeftPower = left - rightT + leftT;
        backLeftPower = left + rightT - leftT;
        frontRightPower = right + rightT - leftT;
        backRightPower = right - rightT + leftT;

        reducePowers(Math.max(frontLeftPower, Math.max(backLeftPower, Math.max(frontRightPower, backRightPower))));

        driveBase.updateDriveMotors(frontLeftPower, frontRightPower, backLeftPower, backRightPower);

        c.setPower(gamepad2.left_stick_y);

        f+= (gamepad2.right_stick_y*100.);
        error = f - b.getCurrentPosition();
        b.setPower(error/1620);

        if (gamepad2.right_trigger>0.2) {
            d.setPower(gamepad1.right_trigger);
        } else if (gamepad2.left_trigger>0.2) {
            d.setPower(-gamepad1.left_trigger);
        }

        if (gamepad1.a) {
            e.setPower(1);
        } else if (gamepad1.b) {
            e.setPower(-1);
        } else if (gamepad1.x) {
            e.setPower(0);
        }

        telemetry.addData("back left: ", driveBase.backLeft.getCurrentPosition());
        telemetry.addData("back right: ", driveBase.backRight.getCurrentPosition());
        telemetry.addData("front left: ", driveBase.frontLeft.getCurrentPosition());
        telemetry.addData("front right: ", driveBase.frontRight.getCurrentPosition());
        telemetry.addData("f", f);
        telemetry.addData("encoder", b.getCurrentPosition());
        telemetry.addData("error", error);
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
    public void stop() {

    }
}

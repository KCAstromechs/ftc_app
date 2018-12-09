package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name="Oliver Teleop")
public class teleopOliver extends OpMode {

    //init vars
    float left, right, leftT, rightT, frontLeftPower, backLeftPower, frontRightPower, backRightPower;
    boolean a, y;
    DriveBaseOliver driveBase;
    RobotBaseOliver robotBase;
    Toggle toggleA, toggleY;

    @Override
    public void init() {
        driveBase = new DriveBaseOliver(false, false, this);
        robotBase = new RobotBaseOliver(true, this);

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

        a = toggleA.update(gamepad1.a);
        y = toggleY.update(gamepad1.y);

        if (y)
            robotBase.latch.setPosition(0.75);
        else
            robotBase.latch.setPosition(0.45);

        if (a) {
            robotBase.holdR.setPosition(0.1);
            robotBase.holdL.setPosition(0.8);
        }
        else {
            robotBase.holdR.setPosition(0.5);
            robotBase.holdL.setPosition(0.5);
        }

        if (gamepad1.dpad_up) {
            robotBase.liftR.setPower(1);
            robotBase.liftL.setPower(1);
        } else if (gamepad1.dpad_down) {
            robotBase.liftR.setPower(-1);
            robotBase.liftL.setPower(-1);
        } else {
            robotBase.liftR.setPower(0);
            robotBase.liftL.setPower(0);
        }
        telemetry.addData("back left: ", driveBase.backLeft.getCurrentPosition());
        telemetry.addData("back right: ", driveBase.backRight.getCurrentPosition());
        telemetry.addData("front left: ", driveBase.frontLeft.getCurrentPosition());
        telemetry.addData("front right: ", driveBase.frontRight.getCurrentPosition());
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
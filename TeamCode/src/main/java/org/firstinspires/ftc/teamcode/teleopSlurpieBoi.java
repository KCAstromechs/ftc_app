package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Slurp Teleop")
public class teleopSlurpieBoi extends OpMode {

    //init vars
    float left, right, leftT, rightT, frontLeftPower, backLeftPower, frontRightPower, backRightPower;
    boolean a, y;
    DriveBaseOliver driveBase;
    RobotBaseOliver robotBase;
    Toggle toggleA, toggleY;

    float armLength = 32;
    float strafeSpeed = 54.7f;
    float turnSpeed = 2.15f;
    float turnFactorL;
    float turnFactorR;
    float strafe;


    @Override
    public void init() {
        driveBase = new DriveBaseOliver(false, false, this);
        robotBase = new RobotBaseOliver(true, this);

        toggleA = new Toggle();
        toggleY = new Toggle();
    }

    @Override
    public void loop() {
        strafe = 0;

        left = (Math.abs(gamepad1.left_stick_y) < 0.05) ? 0 : gamepad1.left_stick_y;
        right = (Math.abs(gamepad1.right_stick_y) < 0.05) ? 0 : gamepad1.right_stick_y;
        leftT = (Math.abs(gamepad1.left_trigger) < 0.05) ? 0 : gamepad1.left_trigger;
        rightT = (Math.abs(gamepad1.right_trigger) < 0.05) ? 0 : gamepad1.right_trigger;

        if(left != 0) turnFactorL = (float) ((360/turnSpeed)/((armLength*2*Math.PI)/(strafeSpeed*left))); else turnFactorL=0;

        if(right != 0) turnFactorL = (float) ((360/turnSpeed)/((armLength*2*Math.PI)/(strafeSpeed*right))); else turnFactorR=0;

        strafe -= left;
        strafe += right;
        strafe -= leftT;
        strafe += rightT;


        frontLeftPower = (left*turnFactorL) - strafe;
        backLeftPower = (left*turnFactorL) + strafe;
        frontRightPower = (right*turnFactorR) + strafe;
        backRightPower = (right*turnFactorR) - strafe;

        reducePowers(Math.max(frontLeftPower, Math.max(backLeftPower, Math.max(frontRightPower, backRightPower))));

        driveBase.updateDriveMotors(frontLeftPower/2, frontRightPower/2, backLeftPower/2, backRightPower/2);


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
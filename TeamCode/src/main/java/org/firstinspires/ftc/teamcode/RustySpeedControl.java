package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Disabled
@Autonomous(name= "Rusty SpdCntrl Test")
public class RustySpeedControl extends LinearOpMode {
    DcMotor frontRight, frontLeft, backLeft, backRight, encoderMotor;
    boolean speedControl;
    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontright");
        frontLeft = hardwareMap.dcMotor.get("frontleft");
        backRight = hardwareMap.dcMotor.get("backright");
        backLeft = hardwareMap.dcMotor.get("backleft");
        encoderMotor = frontLeft;

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        speedControl = true;


        waitForStart();

        //
        //
        // drive(24, .75);

        currrrvyDrive(36, 0.3, 3, true);
    }

    public void drive (int inches, double driveSpeed) {
        int travelLen = 275 * inches;           //275 is the # encoder clicks / inche

        frontLeft.setPower(driveSpeed);
        frontRight.setPower(driveSpeed);
        backLeft.setPower(driveSpeed);
        backRight.setPower(driveSpeed);

        while (Math.abs(encoderMotor.getCurrentPosition()) < travelLen) {
            sleep(10);
            if(!opModeIsActive()) {
                break;
            }
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void currrrvyDrive(int inchesOuter, double driveSpeed, double curveRatio, boolean isSpeed) {
        int travelLen = 275 * inchesOuter;           //275 is the # encoder clicks / inches
        resetEncoders(isSpeed);
        double leftPower = 0, rightPower = 0;


        if (curveRatio > 0) {
            leftPower = driveSpeed;
            rightPower = driveSpeed/Math.abs(curveRatio);
        }
        else {
            rightPower = driveSpeed;
            leftPower = driveSpeed/Math.abs(curveRatio);
        }

        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);

        while (Math.abs(encoderMotor.getCurrentPosition()) < travelLen) {
            sleep(10);
            telemetry.addData("rightEncoders", frontRight.getCurrentPosition());
            telemetry.addData("leftEncoders", frontLeft.getCurrentPosition());
            telemetry.addData("rightPower", frontRight.getPower());
            telemetry.addData("leftPower", frontLeft.getPower());
            telemetry.update();
            if(!opModeIsActive()) {
                break;
            }
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void resetEncoders(boolean isSpeed) {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if(isSpeed) {
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            speedControl  = true;
        }
        if(!isSpeed) {
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            speedControl  = false;
        }

    }
}

/*
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DriveBaseSlaHappy {
    DcMotor frontRight, frontLeft, backRight, backLeft;

    OpMode callingOpMode;

    boolean speedControl;
    boolean debug;

    int ticksPerInch = (int) ((1100)/(4 * Math.PI));

    public DriveBaseSlaHappy (boolean _speedControl, boolean _debug, OpMode _callingOpMode){
        speedControl = _speedControl;
        debug = _debug;
        callingOpMode = _callingOpMode;

        frontRight = callingOpMode.hardwareMap.dcMotor.get("frontRight");
        frontLeft = callingOpMode.hardwareMap.dcMotor.get("frontLeft");
        backRight = callingOpMode.hardwareMap.dcMotor.get("backRight");
        backLeft = callingOpMode.hardwareMap.dcMotor.get("backLeft");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    public void updateDriveMotors(double frontleft, double frontright, double backleft, double backright) {
        frontRight.setPower(frontright);
        backRight.setPower(backright);
        frontLeft.setPower(frontleft);
        backLeft.setPower(backleft);
    }
    public void driveStraight(double distance, double speed) {
        resetEncoders(speedControl);
        int encoderDist = (int) (distance * ticksPerInch);

        while (Math.abs(encoderDist) > Math.abs(frontRight.getCurrentPosition())  ) {
            if(distance > 0) {
                updateDriveMotors(speed, speed, speed, speed);
            }
            else {
                updateDriveMotors(-speed, -speed, -speed, -speed);
            }
        }
        updateDriveMotors(0, 0, 0, 0);

    }
    public void turn (double distance, double speed) {
        resetEncoders(speedControl);
        int encoderDist = (int) (distance * ticksPerInch);

        while (Math.abs(encoderDist) > Math.abs(frontRight.getCurrentPosition())  ) {
            if(distance > 0) {
                updateDriveMotors(speed, -speed, speed, -speed);
            }
            else {
                updateDriveMotors(-speed, speed, -speed, speed);
            }
        }
        updateDriveMotors(0, 0, 0, 0);
    }

    public void driveCurvy() {

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
        else {
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            speedControl  = false;
        }

    }
}

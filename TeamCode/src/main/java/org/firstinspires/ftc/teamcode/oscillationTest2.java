package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class oscillationTest2 extends DriveBaseCuddlefish {

    double k = 0.001;
    double d = 0.08;
    double dA = 0.025;

    DcMotor extender, lift;

    public oscillationTest2(boolean _speedControl, boolean _debug, OpMode _callingOpMode) {
        super(_speedControl, _debug, _callingOpMode);
        lift = callingOpMode.hardwareMap.dcMotor.get("lift");
        extender = callingOpMode.hardwareMap.dcMotor.get("extender");

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void driveStraight(double inches, float heading, boolean powerD)  throws InterruptedException {
        double error;                                           //The number of degrees between the true heading and desired heading
        double errorDLast;                                           //The number of degrees between the true heading and desired heading
        double errorD;                                           //The number of degrees between the true heading and desired heading
        double correction;                                      //Modifies power to account for error
        double leftPower;                                       //Power being fed to left side of bot
        double rightPower;                                      //Power being fed to right side of bot
        double max;                                             //To be used to keep powers from exceeding 1
        double derivative;
        long loops = 0;
        heading = (int) normalize360(heading);

        resetEncoders(speedControl);
        int target;
        if (powerD) {
            target = (int) (inches * ticksPerInch);
        } else {
            target = (int) -(inches * ticksPerInch);
        }

        double power = 0;

        errorD = target - encoderMotor.getCurrentPosition();

        while ((Math.abs(errorD) > 13) && ((LinearOpMode) callingOpMode).opModeIsActive()) {

            errorDLast = errorD;

            errorD = target - encoderMotor.getCurrentPosition();

            error = heading - zRotation;

            while (error > 180) error = (error - 360);
            while (error <= -180) error = (error + 360);

            correction = Range.clip(error * P_DRIVE_COEFF, -1, 1);

            derivative = (errorD-errorDLast)/10.;

            power = (errorD * k)+(derivative*d);

            if(power > 0.05 && power < 0.15){
                power = 0.15;
            } else if (power < -0.05 && power > -0.15){
                power = -0.15;
            } else if (-0.05 < power && power < 0.05){
                power = 0.;
            }

            leftPower = power - correction;
            rightPower = power + correction;

            max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
            if (max > 1.0) {
                leftPower /= max;
                rightPower /= max;
            }
            updateDriveMotors(leftPower, rightPower, leftPower, rightPower);

            if ((loops % 10) ==  0) {
                callingOpMode.telemetry.addData("gyro" , zRotation);
                callingOpMode.telemetry.addData("encoder" , encoderMotor.getCurrentPosition());
                callingOpMode.telemetry.addData("loops", loops);
                callingOpMode.telemetry.addData("d", derivative);
                callingOpMode.telemetry.update();
            }

            loops++;

            Thread.sleep(10);

            if ((loops > 10) && (Math.abs(derivative) < 0.1)){
                callingOpMode.telemetry.addData("reaqftlpshdg", "sduiouxzfgbnho");
                callingOpMode.telemetry.addData("d", derivative);
                callingOpMode.telemetry.update();
                break;
            }

            Thread.yield();
        }
        updateDriveMotors(0, 0, 0, 0);
        Thread.sleep(500);
    }
    
    public void driveStraight(double inches, float heading, boolean powerD, double armTarget, double extendTarget)  throws InterruptedException {
        double error;
        double errorDLast;
        double errorD;
        double correction;
        double leftPower;
        double rightPower;
        double max;
        double derivative;
        double f = armTarget;
        double errorA = f - lift.getCurrentPosition();
        double errorALast;
        double derivativeA;
        long loops = 0;
        heading = (int) normalize360(heading);
        double targetT = callingOpMode.getRuntime()+3;

        resetEncoders(false);
        int target;
        if (powerD) {
            target = (int) (inches * ticksPerInch);
        } else {
            target = (int) -(inches * ticksPerInch);
        }


        double power = 0;

        errorD = target - encoderMotor.getCurrentPosition();

        extender.setPower(1);

        while (((LinearOpMode) callingOpMode).opModeIsActive()) {

            errorDLast = errorD;

            errorD = target - encoderMotor.getCurrentPosition();

            error = heading - zRotation;

            while (error > 180) error = (error - 360);
            while (error <= -180) error = (error + 360);

            correction = Range.clip(error * DriveBaseCuddlefish.P_DRIVE_COEFF, -1, 1);

            derivative = (errorD-errorDLast)/10.;

            power = (errorD * k)+(derivative*d);

            if(power > 0 && power < 0.15){
                power = 0.15;
            } else if (power < 0 && power > -0.15){
                power = -0.15;
            } else if (-0.05 < power && power < 0.05){
                power = 0.;
            }

            leftPower = power - correction;
            rightPower = power + correction;

            max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
            if (max > 1.0) {
                leftPower /= max;
                rightPower /= max;
            }
            if (Math.abs(errorD) > 13) {
                updateDriveMotors(leftPower, rightPower, leftPower, rightPower);
            } else {
                updateDriveMotors(0,0,0,0);
            }

            errorALast = errorA;
            errorA = f - lift.getCurrentPosition();
            derivativeA = (errorA-errorALast)/10.;
            lift.setPower((errorA/810)+(derivativeA*dA));

            if(!(callingOpMode.getRuntime()<targetT)){
                extender.setPower(0);
            }

            if ((loops % 10) ==  0) {
                callingOpMode.telemetry.addData("gyro" , zRotation);
                callingOpMode.telemetry.addData("encoder" , encoderMotor.getCurrentPosition());
                callingOpMode.telemetry.addData("loops", loops);
                callingOpMode.telemetry.addData("d", derivative);
                callingOpMode.telemetry.addData("power", extender.getPower());
                callingOpMode.telemetry.addData("targetT", targetT);
                callingOpMode.telemetry.addData("runtime", callingOpMode.getRuntime());
                callingOpMode.telemetry.update();
            }

            loops++;

            Thread.sleep(10);
        }
        updateDriveMotors(0, 0, 0, 0);
        extender.setPower(0);
        lift.setPower(0);
    }
}

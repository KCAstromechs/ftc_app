package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class DriveBaseMarinaAdvanced extends DriveBaseMarina {

    double k = 0.0008;
    double kTurn = 0.028;
    double d = 0.05;
    double dA = 0.025;

    double speedLimit = 1.0;

    DcMotor extender, lift;

    public DriveBaseMarinaAdvanced(boolean _speedControl, boolean _debug, OpMode _callingOpMode) {
        super(_speedControl, _debug, _callingOpMode);
        lift = callingOpMode.hardwareMap.dcMotor.get("lift");
        extender = callingOpMode.hardwareMap.dcMotor.get("extender");
        extender.setDirection(DcMotor.Direction.REVERSE);

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setKconstant(double _k){
        k=_k;
    }

    public void setKTurnconstant(double _kTurn){
        kTurn=_kTurn;
    }

    public void setSpeedLimit(double _speedLimit){
        speedLimit = _speedLimit;
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
        long loopsD = 0;
        heading = (int) normalize360(heading);

        resetEncoders(speedControl);
        int target;
        if (powerD) {
            target = (int) (inches * ticksPerInch);
        } else {
            target = (int) -(inches * ticksPerInch);
        }

        target *= 0.9;

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

            if(power > 0 && power < 0.15){
                power = 0.15;
            } else if (power < -0 && power > -0.15){
                power = -0.15;
            }

            leftPower = power - correction;
            rightPower = power + correction;

            max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
            if (max > speedLimit) {
                leftPower /= (max/speedLimit);
                rightPower /= (max/speedLimit);
            }
            updateDriveMotors(leftPower, rightPower, leftPower, rightPower);

            if ((loops % 10) ==  0) {
                callingOpMode.telemetry.addData("gyro" , zRotation);
                callingOpMode.telemetry.addData("encoder" , encoderMotor.getCurrentPosition());
                callingOpMode.telemetry.addData("loops", loops);
                callingOpMode.telemetry.addData("d", derivative);
                callingOpMode.telemetry.addData("pwr", power);
                callingOpMode.telemetry.update();
            }

            loops++;

            Thread.sleep(10);

            if ((loops > 10) && (Math.abs(derivative) < 0.1)){
                if(loopsD<18){
                    loopsD++;
                } else {
                    callingOpMode.telemetry.addData("reaqftlpshdg", "sduiouxzfgbnho");
                    callingOpMode.telemetry.addData("d", derivative);
                    break;
                }
            } else {
                loopsD = 0;
            }

            Thread.yield();
        }
        updateDriveMotors(0, 0, 0, 0);
        Thread.sleep(250);
        callingOpMode.telemetry.addData("gyro" , zRotation);
        callingOpMode.telemetry.addData("encoder" , encoderMotor.getCurrentPosition());
        callingOpMode.telemetry.addData("loops", loops);
        callingOpMode.telemetry.addData("errorD", errorD);
        callingOpMode.telemetry.update();
    }
    
    public void driveStraight(double inches, float heading, float heading2, boolean powerD, double armTarget, double extendTarget)  throws InterruptedException {
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
        heading2 = (int) normalize360(heading2);
        boolean newheading = false;
        if(extendTarget==0){
            extendTarget=5;
        }
        double targetT = callingOpMode.getRuntime()+extendTarget;

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

            if(Math.abs(encoderMotor.getCurrentPosition())>900 && !newheading){
                heading = heading2;
                newheading = true;
            }

            errorDLast = errorD;

            errorD = target - encoderMotor.getCurrentPosition();

            error = heading - zRotation;

            while (error > 180) error = (error - 360);
            while (error <= -180) error = (error + 360);

            correction = Range.clip(error * DriveBaseMarina.P_DRIVE_COEFF, -1, 1);

            derivative = (errorD-errorDLast)/10.;

            power = (errorD * k)+(derivative*d);

            if(power > 0 && power < 0.15){
                power = 0.15;
            } else if (power < 0 && power > -0.15){
                power = -0.15;
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
                break;
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

    public void driveStraightStall(double timeout, float heading, double power) throws InterruptedException {
        double error;                                           //The number of degrees between the true heading and desired heading
        double correction;                                      //Modifies power to account for error
        double leftPower;                                       //Power being fed to left side of bot
        double rightPower;                                      //Power being fed to right side of bot
        double max;                                             //To be used to keep powers from exceeding 1
        long loops = 0;
        long loopsD = 0;
        double target = callingOpMode.getRuntime()+timeout;
        int pos = 0;
        int posLast;
        heading = (int) normalize360(heading);

        resetEncoders(speedControl);

        power = Range.clip(power, -1.0, 1.0);


        while (target > callingOpMode.getRuntime() && ((LinearOpMode) callingOpMode).opModeIsActive()) {

            error = heading - zRotation;

            while (error > 180) error = (error - 360);
            while (error <= -180) error = (error + 360);

            correction = Range.clip(error * P_DRIVE_COEFF, -1, 1);

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
                callingOpMode.telemetry.addData("encoder" , pos);
                callingOpMode.telemetry.addData("loops", loops);
                callingOpMode.telemetry.update();
            }

            posLast = pos;
            pos = encoderMotor.getCurrentPosition();

            loops++;

            if ((loops > 10) && (Math.abs(pos-posLast) < 1)){
                if(loopsD<18){
                    loopsD++;
                } else {
                    callingOpMode.telemetry.addData("reaqftlpshdg", "sduiouxzfgbnho");
                    break;
                }
            } else {
                loopsD = 0;
            }

            Thread.yield();
        }
        updateDriveMotors(0, 0, 0, 0);
        Thread.sleep(500);
    }

    public void turn(float turnHeading) throws InterruptedException {
        int wrapFix = 0;                                        //Can be used to modify values and make math around 0 easier
        float shiftedTurnHeading = turnHeading;                 //Can be used in conjunction with wrapFix to make math around 0 easier
        long loops = 0;
        double power = 0;

        //If heading is not on correct scale, put it between 0-360
        turnHeading = normalize360(turnHeading);

        //Figure out how far the robot would have to turn in counterclockwise & clockwise directions
        float cclockwise = zRotation - turnHeading;
        float clockwise = turnHeading - zRotation;

        //Normalize cwise & ccwise values to between 0=360
        clockwise = normalize360(clockwise);
        cclockwise = normalize360(cclockwise);
        int error = 5;

        //sets the distance to the target gyro value that we will accept
        if (turnHeading - error < 0 || turnHeading + error > 360) {
            wrapFix = 180;                                      //if within the range where the clockmath breaks, shift to an easier position
            shiftedTurnHeading = normalize360(turnHeading + wrapFix);
        }

        //If it would be longer to take the ccwise path, we go *** CLOCKWISE ***
        if(Math.abs(cclockwise) >= Math.abs(clockwise)){

            //While we're not within our error, and we haven't overshot, and the bot is running
            while(Math.abs(normalize360(zRotation + wrapFix)- shiftedTurnHeading) > error &&
                    Math.abs(cclockwise) >= Math.abs(clockwise) && ((LinearOpMode) callingOpMode).opModeIsActive()) {

                //Figure out how far the robot would have to turn in counterclockwise & clockwise directions
                cclockwise = zRotation - turnHeading;
                clockwise = turnHeading - zRotation;

                //Normalize cwise & ccwise values to between 0=360
                clockwise = normalize360(clockwise);
                cclockwise = normalize360(cclockwise);

                power = clockwise*kTurn;

                if(power>=0.6){
                    power = 0.6;
                }

                updateDriveMotors(-power, power, -power, power);

                if ((loops % 10) ==  0) {
                    callingOpMode.telemetry.addData("gyro" , zRotation);
                    callingOpMode.telemetry.addData("loops", loops);
                    callingOpMode.telemetry.update();
                }

                //Chill a hot decisecond
                Thread.sleep(10);
            }
        }
        //If it would take longer to take the cwise path, we go *** COUNTERCLOCKWISE ***
        else if(Math.abs(clockwise) > Math.abs(cclockwise)) {

            //While we're not within our error, and we haven't overshot, and the bot is running
            while (Math.abs(normalize360(zRotation + wrapFix) - shiftedTurnHeading) > error &&
                    Math.abs(clockwise) > Math.abs(cclockwise) && ((LinearOpMode) callingOpMode).opModeIsActive()) {

                //Figure out how far the robot would have to turn in counterclockwise & clockwise directions
                cclockwise = zRotation - turnHeading;
                clockwise = turnHeading - zRotation;

                //Normalize cwise & ccwise values to between 0=360
                clockwise = normalize360(clockwise);
                cclockwise = normalize360(cclockwise);

                power = cclockwise*kTurn;

                if(power>=0.6){
                    power = 0.6;
                }

                updateDriveMotors(power, -power, power, -power);

                if ((loops % 10) ==  0) {
                    callingOpMode.telemetry.addData("gyro" , zRotation);
                    callingOpMode.telemetry.addData("loops", loops);
                    callingOpMode.telemetry.update();
                }

                //Hold up a hot decisecond
                Thread.sleep(10);
            }
        }
        updateDriveMotors(0,0,0,0);
        Thread.sleep(250);
    }

    public void strafe(double inches, float heading, double power, double timeout)  throws InterruptedException {
        double targetT = callingOpMode.getRuntime()+timeout;
        double error;                                           //The number of degrees between the true heading and desired heading
        double correction;                                      //Modifies power to account for error
        double frontPower;                                       //Power being fed to left side of bot
        double backPower;                                      //Power being fed to right side of bot
        double max;                                             //To be used to keep powers from exceeding 1
        long loops = 0;
        heading = (int) normalize360(heading);

        resetEncoders(speedControl);
        int target = (int) (inches * ticksPerInch);

        power = Range.clip(power, -1.0, 1.0);


        while (Math.abs(target) > Math.abs(encoderMotor.getCurrentPosition())  && ((LinearOpMode) callingOpMode).opModeIsActive() && callingOpMode.getRuntime()<targetT) {

            error = heading - zRotation;

            while (error > 180) error = (error - 360);
            while (error <= -180) error = (error + 360);

            correction = Range.clip(error * P_DRIVE_COEFF, -1, 1);

            frontPower = power + correction;
            backPower = power - correction;

            max = Math.max(Math.abs(frontPower), Math.abs(backPower));
            if (max > 1.0) {
                backPower /= max;
                frontPower /= max;
            }

            if ((loops % 10) ==  0) {
                callingOpMode.telemetry.addData("gyro" , zRotation);
                callingOpMode.telemetry.addData("encoder" , encoderMotor.getCurrentPosition());
                callingOpMode.telemetry.addData("loops", loops);
                callingOpMode.telemetry.update();
            }

            updateDriveMotors(-frontPower, frontPower, backPower, -backPower);

            loops++;

            Thread.yield();
        }
        updateDriveMotors(0, 0, 0, 0);
        Thread.sleep(500);
    }
}

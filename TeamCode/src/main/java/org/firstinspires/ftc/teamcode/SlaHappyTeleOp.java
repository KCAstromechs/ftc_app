package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="SlaHappy Tele")
public class SlaHappyTeleOp extends OpMode {

    //init vars
    float leftP, rightP, leftTP, rightTP, FLpwr, FRpwr, BLpwr, BRpwr;
    boolean a, x;
    DriveBaseSlaHappy driveBase;
    Toggle toggleA, toggleX, toggleY;
    Servo latch, holdRight, holdLeft;
    DcMotor liftLeft, liftRight;

    @Override
    public void init() {
        //RobotBaseSlaHappy robotBase = new RobotBase[robot_name]();

        driveBase = new DriveBaseSlaHappy(false, false, this);

        toggleA = new Toggle();
        toggleX = new Toggle();
        toggleY = new Toggle();

        liftLeft = hardwareMap.dcMotor.get("liftLeft");
        liftRight = hardwareMap.dcMotor.get("liftRight");

        latch = hardwareMap.servo.get("latch");
        holdRight = hardwareMap.servo.get("holdRight");
        holdLeft = hardwareMap.servo.get("holdLeft");

        liftRight.setDirection(DcMotor.Direction.REVERSE);

        //robotBase.init(args if needed);
    }

    @Override
    public void loop() {


        leftP = gamepad1.left_stick_y;
        rightP = gamepad1.right_stick_y;
        leftTP = gamepad1.left_trigger;
        rightTP = gamepad1.right_trigger;

        FLpwr = leftP + rightTP - leftTP;
        FRpwr = rightP + rightTP - leftTP;
        BLpwr = leftP - rightTP + leftTP;
        BRpwr = rightP - rightTP + leftTP;

        driveBase.updateDriveMotors(FLpwr, FRpwr, BLpwr, BRpwr);

        a = toggleA.update(gamepad1.a);
        x = toggleX.update(gamepad1.x);

        if (x)
            latch.setPosition(0.75);
        else
            latch.setPosition(0.45);

        if (a) {
            holdRight.setPosition(0.1);
            holdLeft.setPosition(0.8);
        }
        else {
            holdRight.setPosition(0.5);
            holdLeft.setPosition(0.5);
        }

        if (gamepad1.dpad_up) {
            liftRight.setPower(0.75);
            liftLeft.setPower(0.75);
        }
        else if (gamepad1.dpad_down) {
            liftRight.setPower(-0.75);
            liftLeft.setPower(-0.75);
        }
        else {
            liftRight.setPower(0);
            liftLeft.setPower(0);
        }
    }

    @Override
    public void stop() {

    }
}
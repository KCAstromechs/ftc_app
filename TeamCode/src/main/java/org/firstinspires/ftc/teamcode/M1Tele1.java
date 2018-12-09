package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="SlapHappyTele")
public class M1Tele1 extends OpMode {

    DcMotor m_frontR, m_frontL, m_backR, m_backL, m_liftR, m_liftL;
    Servo s_latch, s_mark, s_holdR, s_holdL;
    Toggle toggleA, toggleB, toggleX, toggleY;
    double leftP, rightP, leftTP, rightTP, FLpwr, FRpwr, BLpwr, BRpwr, liftPower;
    boolean a, b, x, y;

    @Override
    public void init() {
        m_frontR = hardwareMap.dcMotor.get("frontRight");
        m_frontL = hardwareMap.dcMotor.get("frontLeft");
        m_backR = hardwareMap.dcMotor.get("backRight");
        m_backL = hardwareMap.dcMotor.get("backLeft");
        m_liftR = hardwareMap.dcMotor.get("liftRight");
        m_liftL = hardwareMap.dcMotor.get("liftLeft");

        s_latch = hardwareMap.servo.get("latch");
        s_mark = hardwareMap.servo.get("mark");
        s_holdR = hardwareMap.servo.get("holdRight");
        s_holdL = hardwareMap.servo.get("holdLeft");

        toggleA = new Toggle();
        toggleB = new Toggle();
        toggleX = new Toggle();
        toggleY = new Toggle();

        m_frontL.setDirection(DcMotor.Direction.REVERSE);
        m_backL.setDirection(DcMotor.Direction.REVERSE);

        m_frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        m_liftR.setDirection(DcMotor.Direction.REVERSE);
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

        m_frontR.setPower(FRpwr);
        m_frontL.setPower(FLpwr);
        m_backR.setPower(BRpwr);
        m_backL.setPower(BLpwr);

        a = toggleA.update(gamepad1.a);
        b = toggleB.update(gamepad1.b);
        x = toggleX.update(gamepad1.x);
        y = toggleY.update(gamepad1.y);

        liftPower = gamepad2.left_stick_y;

        m_liftL.setPower(liftPower);
        m_liftR.setPower(liftPower);

        if (b) {
            s_latch.setPosition(0.45);
        } else{
            s_latch.setPosition(0.75);
        }
        if (b) {
            //s_mark.setPosition(0);
        }
        if (x) {
            //s_holdL.setPosition(0);
        }
        if (y) {
            s_holdR.setPosition(.1);
            s_holdL.setPosition(.8);
        }
        else {
            s_holdR.setPosition(.5);
            s_holdL.setPosition(.5);
        }
        telemetry.addData("Y:", y);
        telemetry.addData("B:", b);
        telemetry.addData("gameB:", gamepad1.b);
        telemetry.addData("gameY:", gamepad1.y);
        telemetry.update();
    }
}

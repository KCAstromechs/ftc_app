package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="encodertesting", group="testing")
public class encodertesting extends OpMode {

    //init vars
    float a, b, c, d;

    DcMotor m1, m2, m3, m4;

    @Override
    public void init() {
        m1 = hardwareMap.dcMotor.get("1");
        m2 = hardwareMap.dcMotor.get("2");
        m3 = hardwareMap.dcMotor.get("3");
        m4 = hardwareMap.dcMotor.get("4");
        m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {

        a = gamepad1.left_stick_y;
        b = gamepad1.right_stick_y;
        c = gamepad2.left_stick_y;
        d = gamepad2.right_stick_y;

        m1.setPower(a);
        m2.setPower(b);
        m3.setPower(c);
        m4.setPower(d);

        telemetry.addData("1", m1.getCurrentPosition());
        telemetry.addData("2", m2.getCurrentPosition());
        telemetry.addData("3", m3.getCurrentPosition());
        telemetry.addData("4", m4.getCurrentPosition());
    }

    @Override
    public void stop() {

    }
}
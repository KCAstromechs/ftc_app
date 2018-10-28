package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name= "DriveTrainTester")
public class DriveTrainTester extends OpMode {
    DcMotor backLeft, backRight, frontLeft, frontRight;
    Toggle latchTest = new Toggle();
    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
    }
    @Override
    public void loop() {
        latchTest.update(gamepad1.b);
        backLeft.setPower(0);
        telemetry.addData("Toggle:", latchTest.update(true));
        telemetry.update();
    }


}

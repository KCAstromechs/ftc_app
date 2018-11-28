package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static android.content.Context.SENSOR_SERVICE;
import static java.lang.Thread.sleep;

public class RobotBaseOliver implements SensorEventListener {
    //variables for gyro operation
    private float zero;
    private float rawGyro;
    public int sensorDataCounter = 0;

    //arrays for gyro operation
    private float[] rotationMatrix = new float[9];
    private float[] orientation = new float[3];
    //objects for gyro operation
    private SensorManager mSensorManager;
    private Sensor mRotationVectorSensor;

    protected boolean hasBeenZeroed= false;

    private float zRotation;

    boolean debug;
    OpMode callingOpMode;

    public DcMotor liftL, liftR;
    public Servo latch, holdR, holdL, mark, flap;

    public RobotBaseOliver(boolean _debug, OpMode _callingOpMode) {
        debug = _debug;
        callingOpMode = _callingOpMode;

        liftL = callingOpMode.hardwareMap.dcMotor.get("liftLeft");
        liftR = callingOpMode.hardwareMap.dcMotor.get("liftRight");
        latch = callingOpMode.hardwareMap.servo.get("latch");
        holdR = callingOpMode.hardwareMap.servo.get("holdRight");
        holdL = callingOpMode.hardwareMap.servo.get("holdLeft");
        mark = callingOpMode.hardwareMap.servo.get("mark");
        flap = callingOpMode.hardwareMap.servo.get("flap");
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);
        mark.setPosition(0.75);
        flap.setPosition(0.7);
    }

    public void land () throws InterruptedException {
        liftL.setPower(-1);
        liftR.setPower(-1);

        sleep(1000);

        holdL.setPosition(0.8);
        holdR.setPosition(0.1);

        sleep(1000);

        liftL.setPower(1);
        liftR.setPower(1);

        sleep(1000);

        liftL.setPower(0);
        liftR.setPower(0);

        sleep(1500);

        latch.setPosition(0.75);

        sleep(500);
    }

    public void deployMarker () {
        mark.setPosition(1);
    }

    public void deployFlap () {
        flap.setPosition(0.3);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

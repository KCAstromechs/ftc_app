package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

public class RobotBaseMarina implements SensorEventListener {
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

    public DcMotor climb;
    public Servo mark;

    public RobotBaseMarina(boolean _debug, OpMode _callingOpMode) {
        debug = _debug;
        callingOpMode = _callingOpMode;

        mark = callingOpMode.hardwareMap.servo.get("mark");
        climb = callingOpMode.hardwareMap.dcMotor.get("climb");

        mark.setPosition(0.1);

        climb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        climb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        climb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void land () throws InterruptedException {
        climb.setPower(1);
        while(true){
            if (!(climb.getCurrentPosition() < 11800)) break;
        }
        climb.setPower(0);
    }

    public void deployMarker () {
        mark.setPosition(0.5);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

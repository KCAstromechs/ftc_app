package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static android.content.Context.SENSOR_SERVICE;

public class RobotBaseSlaHappy implements SensorEventListener {
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


    public RobotBaseSlaHappy(boolean _debug, OpMode _callingOpMode) {
        debug = _debug;
        callingOpMode = _callingOpMode;

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

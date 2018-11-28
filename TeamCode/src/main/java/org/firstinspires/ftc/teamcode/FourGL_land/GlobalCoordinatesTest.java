package org.firstinspires.ftc.teamcode.FourGL_land;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.DriveBaseOliver;

public class GlobalCoordinatesTest extends OpMode {
    //TODO set globalX and globalY
    //TODO add waypoints

    public float globalX, globalY;//0-144
        DriveBaseOliver driveTrain;
    @Override
    public void init() {
        driveTrain = new DriveBaseOliver(true, true, this);
    }

    @Override
    public void loop() {

    }
    public void followWaypoint(double x, double y, float heading) throws InterruptedException {
        double mag = pythagorize(globalX - x, globalY - y);
        float driveAngle = (float) Math.atan((y - globalY)/(x - globalX));

        driveTrain.turn(driveAngle, .3);
        driveTrain.driveStraight(mag, .2f);
        driveTrain.turn(heading, .3);
    }
    public double pythagorize(double a, double b) {
        return (Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
    }

}

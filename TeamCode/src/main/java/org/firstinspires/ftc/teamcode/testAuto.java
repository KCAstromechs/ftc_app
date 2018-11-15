package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous (name="Test Auto", group = "Temp")
public class testAuto extends LinearOpMode {

    //init vars

    @Override
    public void runOpMode() throws InterruptedException {

        //initialize all bot stuff
       // RobotBaseSlaHappy robotBase = new RobotBaseSlaHappy();
        DriveBaseSlaHappy driveBase = new DriveBaseSlaHappy(false, false, this);
       // LooknBase[robot_name] looknBase = new LooknBase[robot_name]();
        //robotBase.init(args if needed);
       // looknBase.init(args if needed);

        waitForStart();

        driveBase.driveStraight(18, 0.5f);
        driveBase.driveStraight(-18, 0.5f);
    }
}
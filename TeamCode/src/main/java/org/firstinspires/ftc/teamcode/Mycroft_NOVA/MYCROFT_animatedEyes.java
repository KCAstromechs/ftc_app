package org.firstinspires.ftc.teamcode.Mycroft_NOVA;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;

import org.firstinspires.ftc.teamcode.Toggle;

import java.util.LinkedList;

//TODO Restructure code to support a state machine
//TODO Test functionality in general
//TODO Decide what pair of phones to use and verify configuration
//TODO Add infrastrucure for mycroft-app controller communication
//  TODO Animate the mycroft eyes themselves

@TeleOp (name="animated eyes TeleOp", group="TeleOp")
public class MYCROFT_animatedEyes extends OpMode {

    MYCROFT_RB robotBase;

    long shooterTime = 1000;
    long shooterStart = 0;

    byte currentDriveDist = 0;

    Toggle collector;
    boolean writingToQueue = true;
    long timeLast;
    LinkedList mycroftQueue;

    public DigitalChannel digChan, clockChan;
    GPIO_Monitor signal;
    byte currentMsg = 0;

    boolean manualReloadIsRunning = false;
    boolean manualReloadIsFinishing = false;
    private byte currentData;

    DrivetrainTracker driveTrainStatus;

    @Override
    public void init() {
        robotBase = new MYCROFT_RB();
        robotBase.init(hardwareMap, this);
        robotBase.setReloadAfterShot(true);

        signal = new GPIO_Monitor(this);

        collector = new Toggle();

        signal.start();

        driveTrainStatus = DrivetrainTracker.STOPPED;

        digChan = hardwareMap.digitalChannel.get("digChan");
        clockChan = hardwareMap.digitalChannel.get("clockChan");
        digChan.setMode(DigitalChannel.Mode.INPUT);
        clockChan.setMode(DigitalChannel.Mode.INPUT);
        //digChan.setMode(DigitalChannel.Mode.OUTPUT);
        //digChan.setState(false);
        LinkedList mycroftQueue = new LinkedList();
        timeLast = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        if (timeLast - System.currentTimeMillis() >= 10 && writingToQueue) {
            timeLast = System.currentTimeMillis();
        }

        // doesn't allocate memory, creates pointer to byte array in signal
        byte[] msgAndData = signal.getMessage();
        if (msgAndData != null) {
            System.out.println("SSS msg " + msgAndData[0]);
            System.out.println("SSS data " + msgAndData[1]);
            if(msgAndData[0] == 11) {
                driveTrainStatus = robotBase.beelineForLoop(driveTrainStatus, true, msgAndData[1], 0);
                currentDriveDist = msgAndData[1];
            }

            /*if(currentMsg == 3) {
                if(collector.update(true)) {
                    robotBase.startShooter();
                    shooterStart = System.currentTimeMillis();
                    System.out.println("SSS started shot");
                }
            }*/
        }
        if (System.currentTimeMillis() - shooterStart > shooterTime) {
            robotBase.stopShooter();
        }
        driveTrainStatus = robotBase.beelineForLoop(driveTrainStatus, false, currentDriveDist, 0);
        /*
        System.out.println("MMM frontLeft: " + robotBase.motorFrontLeft.getCurrentPosition());
        System.out.println("MMM frontRight: " + robotBase.motorFrontRight.getCurrentPosition());
        System.out.println("MMM backLeft: " + robotBase.motorBackLeft.getCurrentPosition());
        System.out.println("MMM backRight: " + robotBase.motorBackRight.getCurrentPosition());
        */

        //Manage MYCROFT communication
        switch (driveTrainStatus) {
            case FORWARD:
                //Green stripes falling
            case REVERSE:
                //Red stripes rising
            case CW_TURN:
                //Blue Lights on eyes rotate CW
            case CCW_TURN:
                //Blue Lights on eyes rotate CCW
            case STOPPED:
                //Probably no light
        }
    }

    public byte getSignalState(){
        if(digChan.getState()) return 1;
        return 0;
    }
    public byte getClockState(){
        if(clockChan.getState()) return 1;
        return 0;
    }
}


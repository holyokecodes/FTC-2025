package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;


public class AutoOpStuff {
    HardwareMap hardwareMap;
    MecanumDrive driveBase;

    public AutoOpStuff(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void init() {
        ElapsedTime time = new ElapsedTime();

        Motor frontLeftMotor = new Motor (hardwareMap, "frontLeftMotor");
        Motor frontRightMotor = new Motor (hardwareMap, "backLeftMotor");
        Motor backLeftMotor = new Motor (hardwareMap, "frontRightMotor");
        Motor backRightMotor = new Motor (hardwareMap, "backRightMotor");

        driveBase = new MecanumDrive (frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
    }



    public void driveFoward(double runTime, boolean opActive,double time) {

        double strafeSpeed = 1;
        double fowardSpeed = 0;
        double rotateSpeed = 0;
        double heading = 0;

        drive(runTime, opActive, strafeSpeed, fowardSpeed, rotateSpeed, heading, time);
    }

    public void strafeLeft(double runTime, boolean opActive,double time) {
        double strafeSpeed = 0;
        double fowardSpeed = -1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(runTime, opActive, strafeSpeed, fowardSpeed, rotateSpeed, heading, time);    }

    public void strafeRight(double runTime, boolean opActive, double time) {
        double strafeSpeed = 0;
        double fowardSpeed = 1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(runTime, opActive, strafeSpeed, fowardSpeed, rotateSpeed, heading, time);
    }

    public void strafeBack(double runTime, boolean opActive,double time) {
        double strafeSpeed = 0;
        double fowardSpeed = -1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(runTime, opActive, strafeSpeed, fowardSpeed, rotateSpeed, heading, time);    }


    public void drive(double runTime, boolean opActive, double strafeSpeed, double fowardSpeed, double rotateSpeed, double heading, double time ) {

        double startTime = runTime;

        while(runTime - startTime < time && opActive) {

            // this. is only needed if a parameter has the same name
            driveBase.driveFieldCentric(strafeSpeed, fowardSpeed, rotateSpeed,heading, false);
        }
    }
}

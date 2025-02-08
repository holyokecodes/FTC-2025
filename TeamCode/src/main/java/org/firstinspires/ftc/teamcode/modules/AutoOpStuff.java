package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;


public class AutoOpStuff {
    HardwareMap hardwareMap;
    MecanumDrive driveBase;
    LinearOpMode opMode;

    public AutoOpStuff(HardwareMap hardwareMap, LinearOpMode opMode) {
        this.hardwareMap = hardwareMap;
        this.opMode = opMode;
    }

    public void init() {
        ElapsedTime time = new ElapsedTime();

        Motor frontLeftMotor = new Motor (hardwareMap, "frontLeftMotor");
        Motor frontRightMotor = new Motor (hardwareMap, "backLeftMotor");
        Motor backLeftMotor = new Motor (hardwareMap, "frontRightMotor");
        Motor backRightMotor = new Motor (hardwareMap, "backRightMotor");

        driveBase = new MecanumDrive (frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
    }



    public void driveFoward(double time) {

        double strafeSpeed = 1;
        double fowardSpeed = 0;
        double rotateSpeed = 0;
        double heading = 0;

        drive(strafeSpeed, fowardSpeed, rotateSpeed, heading, time);
    }

    public void strafeLeft(double time) {
        double strafeSpeed = 0;
        double fowardSpeed = -1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(strafeSpeed, fowardSpeed, rotateSpeed, heading, time);    }

    public void strafeRight(double time) {
        double strafeSpeed = 0;
        double fowardSpeed = 1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(strafeSpeed, fowardSpeed, rotateSpeed, heading, time);
    }

    public void strafeBack(double time) {
        double strafeSpeed = 0;
        double fowardSpeed = -1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(strafeSpeed, fowardSpeed, rotateSpeed, heading, time);    }


    public void drive(double strafeSpeed, double fowardSpeed, double rotateSpeed, double heading, double time ) {

        double startTime = opMode.getRuntime();

        while(opMode.getRuntime() - startTime < time && opMode.opModeIsActive()) {

            // this. is only needed if a parameter has the same name
            driveBase.driveFieldCentric(strafeSpeed, fowardSpeed, rotateSpeed,heading, false);
        }
    }
}

package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class AutoOpStuff {
    HardwareMap hardwareMap;
    MecanumDrive driveBase;
    LinearOpMode opMode;
    IMU imu;
    YawPitchRollAngles robotOrientation;
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

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        imu.initialize(parameters);

        robotOrientation = imu.getRobotYawPitchRollAngles();
        imu.resetYaw();
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

        drive(strafeSpeed, fowardSpeed, rotateSpeed, heading, time);
    }

    public void strafeRight(double time) {
        double strafeSpeed = 0;
        double fowardSpeed = 1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(strafeSpeed, fowardSpeed, rotateSpeed, heading, time);
    }

    public void driveBack(double time) {
        double strafeSpeed = 0;
        double fowardSpeed = -1;
        double rotateSpeed = 0;
        double heading = 0;

        drive(strafeSpeed, fowardSpeed, rotateSpeed, heading, time);
    }



    public void drive(double strafeSpeed, double fowardSpeed, double rotateSpeed, double heading, double time ) {

        double startTime = opMode.getRuntime();

        while(opMode.getRuntime() - startTime < time && opMode.opModeIsActive()) {

            // this. is only needed if a parameter has the same name
            driveBase.driveFieldCentric(strafeSpeed, fowardSpeed, rotateSpeed,heading, false);
        }
    }
    public void rotate(double degrees) {
        imu.resetYaw();

        double startYaw   = robotOrientation.getYaw(AngleUnit.DEGREES);

        while(robotOrientation.getYaw(AngleUnit.DEGREES) < startYaw + degrees) {
            robotOrientation = imu.getRobotYawPitchRollAngles();
            this.opMode.telemetry.addData("Yaw", robotOrientation.getYaw(AngleUnit.DEGREES));
            this.opMode.telemetry.addData("Pitch", robotOrientation.getPitch(AngleUnit.DEGREES));
            this.opMode.telemetry.addData("Roll", robotOrientation.getRoll(AngleUnit.DEGREES));
            this.opMode.telemetry.update();
            driveBase.driveFieldCentric(0, 0, 0.4,0, false);
        }
    }

    public void wait_a_sec(double time) {
        double startTime = opMode.getRuntime();
        while(opMode.getRuntime() - startTime < time && opMode.opModeIsActive()) {
            driveBase.driveFieldCentric(0, 0, 0, 0, false);
        }
    }

}

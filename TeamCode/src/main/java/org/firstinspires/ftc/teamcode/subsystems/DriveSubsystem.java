package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import java.util.function.DoubleSupplier;

public class DriveSubsystem extends SubsystemBase {
    private MecanumDrive drive;
    private DoubleSupplier xPower, yPower, rotPower;
    public DriveSubsystem(Motor frontLeftMotor, Motor frontRightMotor, Motor backLeftMotor, Motor backRightMotor, GamepadEx gamepad) {
        xPower = gamepad::getLeftX;
        yPower = gamepad::getLeftY;
        rotPower = gamepad::getRightX;

        drive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
    }

    public void drive() {
        drive.driveRobotCentric(xPower.getAsDouble(), yPower.getAsDouble(), rotPower.getAsDouble());
    }
}

package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.CollectSample;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.DumpBucket;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;


@TeleOp
public class TeleOp2025 extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx driverOp = new GamepadEx(gamepad1);

        DriveSubsystem driveSystem = new DriveSubsystem(
                new Motor(hardwareMap, "frontLeftMotor"),
                new Motor(hardwareMap, "backLeftMotor"),
                new Motor(hardwareMap, "frontRightMotor"),
                new Motor(hardwareMap, "backRightMotor"),
                driverOp
        );

        Bucket bucket = new Bucket(hardwareMap, "flipperServo");

        driverOp.getGamepadButton(GamepadKeys.Button.X).whenPressed(new DumpBucket(bucket));
        driverOp.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new CollectSample(bucket));

        driveSystem.setDefaultCommand(new DriveCommand(driveSystem));

        schedule(new RunCommand(telemetry::update));
    }
}
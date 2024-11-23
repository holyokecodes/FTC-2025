package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Wrist {
    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;
    private final Telemetry telemetry;
    private ButtonReader dpadUp;
    private ButtonReader dpadDown;

    Servo wristServo;

    private double wristServoTargetPosition = 0.1;

    public Wrist(HardwareMap hardwareMap, GamepadEx gamepadEx, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
        this.telemetry = telemetry;
    }

    public void init() {
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        wristServo.setPosition(wristServoTargetPosition);

        dpadUp = new ButtonReader(gamepadEx, GamepadKeys.Button.DPAD_UP);
        dpadDown = new ButtonReader(gamepadEx, GamepadKeys.Button.DPAD_DOWN);
    }

    public void run() {
        if (dpadDown.isDown()) {
           wristServoTargetPosition += 0.01;
        }

        if (dpadUp.isDown()) {
            wristServoTargetPosition -= 0.01;
        }

        if (wristServoTargetPosition < 0) {
            wristServoTargetPosition = 0;
        }

        if (wristServoTargetPosition > 1.0) {
            wristServoTargetPosition = 1.0;
        }

        wristServo.setPosition(wristServoTargetPosition);

        telemetry.addData("wrist position", wristServo.getPosition());
        telemetry.addData("wrist target position", wristServoTargetPosition);
    }
}

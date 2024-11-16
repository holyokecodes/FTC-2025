package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Flipper {
    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    private Servo flipperServo;

    private ButtonReader xButton;

    boolean flipped = false;

    public Flipper(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init() {
        flipperServo = hardwareMap.get(Servo.class, "flipperServo");

        xButton = new ButtonReader(gamepadEx, GamepadKeys.Button.X);
    }

    public void run() {
        xButton.readValue();
        if (xButton.wasJustPressed()) {
            flipped = !flipped;
        }

        flipperServo.setPosition(flipped? 0 : 180);
    }
}

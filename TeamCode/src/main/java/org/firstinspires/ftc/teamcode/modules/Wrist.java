package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {
    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    Servo wristServo;

    public Wrist(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init() {
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        wristServo.setPosition(60);
    }

    public void run() {

    }
}

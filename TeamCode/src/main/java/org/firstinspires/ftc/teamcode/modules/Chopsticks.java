package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Chopsticks {

    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    private Servo chopstick1;
    private Servo chopstick2;
    private ButtonReader xButton;
    private ButtonReader yButton;
    private ButtonReader bButton;

    public Chopsticks(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init() {
        chopstick1 = hardwareMap.get(Servo.class, "chopstick1");
        chopstick2 = hardwareMap.get(Servo.class, "chopstick2");
        xButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.X);
        yButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.Y);
        bButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.B);
    }

    public void run() {
        xButton.readValue();
        yButton.readValue();
        bButton.readValue();

        if(xButton.wasJustReleased()) {
            chopstick1.setPosition(1);
            chopstick2.setPosition(0);
        }

        if(yButton.wasJustReleased()) {
            chopstick1.setPosition(0.1);
            chopstick2.setPosition(0.65);
        }

        if (bButton.wasJustReleased()) {
            chopstick1.setPosition(0);
            chopstick2.setPosition(0.75);
        }
    }
}

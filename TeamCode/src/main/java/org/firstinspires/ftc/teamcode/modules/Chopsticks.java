package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


enum Position {
    Open,
    Closed,
    Half
}

public class Chopsticks {

    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    private Servo chopstick1;
    private Servo chopstick2;
    private ButtonReader aButton;
    private ButtonReader bButton;

    private Position position = Position.Open;

    public Chopsticks(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init() {
        chopstick1 = hardwareMap.get(Servo.class, "chopstick1");
        chopstick2 = hardwareMap.get(Servo.class, "chopstick2");
        bButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.B);
        aButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.A);

    }

    public void run() {
        bButton.readValue();
        if (bButton.wasJustReleased()) {
            if(position != Position.Closed) {
                position = Position.Closed;
            } else {
                position = Position.Open;
            }
        }

        if(aButton.wasJustReleased()) {
            if(position != Position.Half) {
                position = Position.Half;
            } else {
                position = Position.Closed;
            }
        }


        switch(position) {
            case Open:
                chopstick1.setPosition(1);
                chopstick2.setPosition(0);
                break;
            case Closed:
                chopstick1.setPosition(0);
                chopstick2.setPosition(0.75);
                break;
            case Half:
                chopstick1.setPosition(0.1);
                chopstick2.setPosition(0.65);
                break;
        }
    }
}

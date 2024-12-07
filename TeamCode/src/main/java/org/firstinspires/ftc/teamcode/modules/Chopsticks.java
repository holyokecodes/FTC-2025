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
    private ButtonReader bButton;

    private boolean open = false;

    public Chopsticks(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init() {
        chopstick1 = hardwareMap.get(Servo.class, "chopstick1");
        chopstick2 = hardwareMap.get(Servo.class, "chopstick2");
        bButton = new ButtonReader(this.gamepadEx, GamepadKeys.Button.B);

    }

    public void run() {
        bButton.readValue();
        if (bButton.wasJustReleased()) {
            open = !open;
        }

        chopstick1.setPosition(open? 0 : 180);
        chopstick2.setPosition(open? 0 : 180);
    }

}

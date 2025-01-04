package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {
    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    private Motor liftMotor;
    ButtonReader dPadUp;
    ButtonReader dPadDown;

    private int liftMotorPosition = 0;

    public Lift(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init() {
        // Setup code here
        liftMotor = new Motor(hardwareMap, "liftMotor");
        liftMotor.setRunMode(Motor.RunMode.PositionControl);
        liftMotor.setPositionCoefficient(0.05);
        liftMotor.setPositionTolerance(10);
        liftMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        liftMotor.resetEncoder();

        dPadUp = new ButtonReader(gamepadEx, GamepadKeys.Button.DPAD_UP);
        dPadDown = new ButtonReader(gamepadEx, GamepadKeys.Button.DPAD_DOWN);
    }

    public void run() {
        // Drive code here
        if (dPadUp.isDown()) {
            liftMotorPosition -= 5;
        }

        if(dPadDown.isDown()) {
            liftMotorPosition += 5;
        }

        if (liftMotorPosition > 0) {
            liftMotorPosition = 0;
        } else if (liftMotorPosition < -3470) {
            liftMotorPosition = -3470;
        }

        liftMotor.set(0.5);
        liftMotor.setTargetPosition(liftMotorPosition);
    }
}

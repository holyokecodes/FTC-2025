package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Elbow {
    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    private Motor armMotor;

    private final Telemetry telemetry;

    private TriggerReader leftTrigger;
    private TriggerReader rightTrigger;

    private int armMotorTargetPosition = 0;

    public Elbow(HardwareMap hardwareMap, GamepadEx gamepadEx, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
        this.telemetry = telemetry;
    }

    public void init() {
        // Initialization code here
        armMotor = new Motor(hardwareMap, "armMotor");
        armMotor.setRunMode(Motor.RunMode.PositionControl);
        armMotor.setPositionCoefficient(0.05);
        armMotor.setPositionTolerance(3);
        armMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        armMotor.resetEncoder();

        leftTrigger = new TriggerReader(gamepadEx, GamepadKeys.Trigger.LEFT_TRIGGER);
        rightTrigger = new TriggerReader(gamepadEx, GamepadKeys.Trigger.RIGHT_TRIGGER);
    }

    public void run() {
        // Run code here
        rightTrigger.readValue();
        leftTrigger.readValue();

        if (rightTrigger.isDown()) {
            armMotorTargetPosition += 5;
        }
        if (leftTrigger.isDown()) {
            armMotorTargetPosition -= 5;
        }

        armMotor.set(0.75);
        if(armMotorTargetPosition > 1960) {
            armMotorTargetPosition = 1960;
        }
        if(armMotorTargetPosition < 0) {
            armMotorTargetPosition = 0;
        }
        armMotor.setTargetPosition(armMotorTargetPosition);

        telemetry.addData("arm position", armMotor.getCurrentPosition());
        telemetry.addData("arm target position", armMotorTargetPosition);
    }
}

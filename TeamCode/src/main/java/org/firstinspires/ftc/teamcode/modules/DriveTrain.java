package org.firstinspires.ftc.teamcode.modules;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    private final HardwareMap hardwareMap;
    private final GamepadEx gamepadEx;

    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;

    private ButtonReader yButton;

    private boolean isSlowMode = false;


    public DriveTrain(HardwareMap hardwareMap, GamepadEx gamepadEx) {
        this.hardwareMap = hardwareMap;
        this.gamepadEx = gamepadEx;
    }

    public void init() {
        // Setup code here
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        yButton = new ButtonReader(gamepadEx, GamepadKeys.Button.Y);
    }

    public void run() {
        // Drive code here
        double leftX = -gamepadEx.getLeftX();
        double leftY = gamepadEx.getLeftY();
        double rightX = gamepadEx.getRightX();

        double robotPower = Math.hypot(leftX, leftY);
        double robotAngle = Math.atan2(leftY, leftX) - Math.toRadians(45);

        double frontLeftPower = robotPower * Math.cos(robotAngle) + rightX;
        double frontRightPower = robotPower * Math.sin(robotAngle) - rightX;
        double backLeftPower = robotPower * Math.sin(robotAngle) + rightX;
        double backRightPower = robotPower * Math.cos(robotAngle) - rightX;

        yButton.readValue();
        if (yButton.wasJustReleased()) {
            isSlowMode = !isSlowMode;
        }

        double speedMultiplier = isSlowMode ? 0.25 : 1;


        backLeftMotor.setPower(-backLeftPower * speedMultiplier);
        backRightMotor.setPower(backRightPower * speedMultiplier);
        frontLeftMotor.setPower(-frontLeftPower * speedMultiplier);
        frontRightMotor.setPower(frontRightPower * speedMultiplier);
    }
}

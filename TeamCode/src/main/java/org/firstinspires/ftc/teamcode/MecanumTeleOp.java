package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

enum Direction {
    Still,
    Intake,
    Outtake,
}

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        boolean isSlowMode = false;

        Direction intakeStatus = Direction.Still;

//        boolean bucketFlipped = false;


//        Servo bucketServo;
        CRServo intakeServo;
        Servo wristServo;

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        Motor armMotor = new Motor(hardwareMap, "armMotor");

        armMotor.setRunMode(Motor.RunMode.PositionControl);
        armMotor.setPositionCoefficient(0.05);
        armMotor.setPositionTolerance(3);
        armMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        armMotor.resetEncoder();

        int armMotorTargetPosition = 0;
//
//        Motor longArmMotor = new Motor(hardwareMap, "longArmMotor");
//
//        longArmMotor.setRunMode(Motor.RunMode.PositionControl);
//        longArmMotor.setPositionCoefficient(0.05);
//        longArmMotor.setPositionTolerance(3);




        // Init Servos
//        bucketServo = hardwareMap.get(Servo.class, "bucketServo");
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");
        wristServo = hardwareMap.get(Servo.class, "wristServo");

        GamepadEx controller1 = new GamepadEx(gamepad1);

        ButtonReader aButton = new ButtonReader(controller1, GamepadKeys.Button.A);
        ButtonReader bButton = new ButtonReader(controller1, GamepadKeys.Button.B);
        ButtonReader yButton = new ButtonReader(controller1, GamepadKeys.Button.Y);
        ButtonReader xButton = new ButtonReader(controller1, GamepadKeys.Button.X);
        ButtonReader leftBumper = new ButtonReader(controller1, GamepadKeys.Button.LEFT_BUMPER);
        ButtonReader rightBumper = new ButtonReader(controller1, GamepadKeys.Button.RIGHT_BUMPER);

        TriggerReader leftTrigger = new TriggerReader(controller1, GamepadKeys.Trigger.LEFT_TRIGGER);
        TriggerReader rightTrigger = new TriggerReader(controller1, GamepadKeys.Trigger.RIGHT_TRIGGER);


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double leftX = -gamepad1.left_stick_x;
            double leftY = gamepad1.left_stick_y;
            double rightX = gamepad1.right_stick_x;

            double robotPower = Math.hypot(leftX, leftY);
            double robotAngle = Math.atan2(leftY, leftX) - Math.toRadians(45);

            double frontLeftPower = robotPower * Math.cos(robotAngle) + rightX;
            double frontRightPower = robotPower * Math.sin(robotAngle) - rightX;
            double backLeftPower = robotPower * Math.sin(robotAngle) + rightX;
            double backRightPower = robotPower * Math.cos(robotAngle) - rightX;

            double speedMultiplier = isSlowMode ? 0.25 : 1;

            backLeftMotor.setPower(-backLeftPower * speedMultiplier);
            backRightMotor.setPower(backRightPower * speedMultiplier);
            frontLeftMotor.setPower(-frontLeftPower * speedMultiplier);
            frontRightMotor.setPower(frontRightPower * speedMultiplier);

            yButton.readValue();
            aButton.readValue();
            bButton.readValue();
            xButton.readValue();

            rightBumper.readValue();
            leftBumper.readValue();

            rightTrigger.readValue();
            leftTrigger.readValue();


            // Y toggle slow mode
            // arm triggers up and down
            // intake A and B
            // X is toggle bucket
            // linear arm go up and down bumpers
            // dpad is shortcuts


            // slow mode
            if (yButton.wasJustReleased()) {
                isSlowMode = !isSlowMode;
            }

            if (rightTrigger.isDown()) {
                armMotorTargetPosition += 5;
            }
            if (leftTrigger.isDown()) {
                armMotorTargetPosition -= 5;
            }

            if (aButton.wasJustReleased()) {
                if(intakeStatus == Direction.Intake) {
                    intakeStatus = Direction.Still;
                } else {
                    intakeStatus = Direction.Intake;
                }
            }

            if (bButton.wasJustReleased()) {
                if(intakeStatus == Direction.Outtake) {
                    intakeStatus = Direction.Still;
                } else {
                    intakeStatus = Direction.Outtake;
                }
            }

            switch (intakeStatus) {
                case Intake:
                    intakeServo.setPower(-1);
                    break;
                case Outtake:
                    intakeServo.setPower(1);
                    break;
                default:
                    intakeServo.setPower(0);
            }

            if (armMotor.getCurrentPosition() < 600) {
                wristServo.setPosition(60);
            } else if (armMotor.getCurrentPosition() > 600 && armMotor.getCurrentPosition() < 1200) {
                wristServo.setPosition(0);
            } else if (armMotor.getCurrentPosition() > 1200) {
                wristServo.setPosition(180);
            }



//            if (xButton.wasJustReleased()) {
//                bucketFlipped = !bucketFlipped;
//            }

//            bucketServo.setPosition(bucketFlipped ? 0 : 180);

//            if (leftBumper.isDown()) {
//                longArmMotor.setTargetPosition(longArmMotor.getCurrentPosition() + 5);
//            }
//
//            if (rightTrigger.isDown()) {
//                longArmMotor.setTargetPosition(longArmMotor.getCurrentPosition() - 5);
//            }


            armMotor.set(0.75);

            armMotor.setTargetPosition(armMotorTargetPosition);

            telemetry.addData("Arm Position", armMotor.getCurrentPosition());
            telemetry.addData("Arm Target Position", armMotorTargetPosition);

            telemetry.update();

        }
    }
}
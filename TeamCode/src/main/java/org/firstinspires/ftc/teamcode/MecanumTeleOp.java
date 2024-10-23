package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    static double speedMultiplier = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor armMotor;
        DcMotor longArmMotor;

        Servo wristServo;
        CRServo intakeServo;

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setTargetPosition(armMotor.getCurrentPosition());

        longArmMotor = hardwareMap.dcMotor.get("longArmMotor");
        longArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        longArmMotor.setTargetPosition(armMotor.getCurrentPosition());

        // Init Servos
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");

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

            backLeftMotor.setPower(-backLeftPower * speedMultiplier);
            backRightMotor.setPower(backRightPower * speedMultiplier);
            frontLeftMotor.setPower(-frontLeftPower * speedMultiplier);
            frontRightMotor.setPower(frontRightPower * speedMultiplier);



            if(gamepad1.a) {
                armMotor.setTargetPosition(armMotor.getTargetPosition() + 5);
            }
            if(gamepad1.b) {
                armMotor.setTargetPosition(armMotor.getTargetPosition() - 5);
            }

            if(gamepad1.right_trigger > 0.1) {
                intakeServo.setPower(1.0);
            } else if (gamepad1.left_trigger > 0.1) {
                intakeServo.setPower(-1.0);
            } else {
                intakeServo.setPower(0);
            }

            if(gamepad1.right_bumper) {
                wristServo.setPosition(wristServo.getPosition() + 1);
            }

            if(gamepad1.left_bumper) {
                wristServo.setPosition(wristServo.getPosition() - 1);
            }

            if (gamepad1.dpad_up) {
                longArmMotor.setTargetPosition(longArmMotor.getTargetPosition() + 5);
            }

            if (gamepad2.dpad_down) {
                longArmMotor.setTargetPosition(longArmMotor.getTargetPosition() - 5);
            }

            ((DcMotorEx) armMotor).setVelocity(2100);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            ((DcMotorEx) longArmMotor).setVelocity(2100);
            longArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.addData("long arm position", longArmMotor.getCurrentPosition());
            telemetry.addData("long arm target position", longArmMotor.getTargetPosition());

        }
    }
}
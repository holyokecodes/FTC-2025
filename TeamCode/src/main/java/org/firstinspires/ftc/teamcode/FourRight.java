package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.AutoOpStuff;

@Autonomous
public class FourRight extends LinearOpMode {
    HardwareMap hardwareMap;
    AutoOpStuff autoOpStuff = new AutoOpStuff(hardwareMap);
    @Override
   public void runOpMode() {
        ElapsedTime time = new ElapsedTime();

        autoOpStuff.init();

        waitForStart();
        time.reset();


        autoOpStuff.strafeRight(getRuntime(), opModeIsActive(), 1.5);

    }
}

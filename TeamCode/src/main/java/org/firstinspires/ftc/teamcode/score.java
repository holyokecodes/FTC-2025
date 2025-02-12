package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.AutoOpStuff;

@Autonomous
public class score extends LinearOpMode {

    @Override
    public void runOpMode() {
        ElapsedTime time = new ElapsedTime();

        AutoOpStuff autoOpStuff = new AutoOpStuff(hardwareMap, this);

        waitForStart();

        autoOpStuff.init();

        time.reset();

        autoOpStuff.rotate(90);

        autoOpStuff.wait_a_sec(0.5);

        autoOpStuff.driveFoward(0.5);

        autoOpStuff.wait_a_sec(0.5);

        autoOpStuff.rotate(90);
    }
}

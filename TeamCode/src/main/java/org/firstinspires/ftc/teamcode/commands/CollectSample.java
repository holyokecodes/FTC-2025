package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Bucket;

public class CollectSample extends CommandBase {
    private final Bucket m_bucketSubsystem;

    public CollectSample(Bucket subsystem) {
        m_bucketSubsystem = subsystem;
        addRequirements(m_bucketSubsystem);
    }

    @Override
    public void initialize() {
        m_bucketSubsystem.collect();
    }

    @Override
    public boolean isFinished() {
        return m_bucketSubsystem.isCollected();
    }
}

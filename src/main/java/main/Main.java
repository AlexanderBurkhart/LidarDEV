package main;

import java.util.concurrent.TimeUnit;

import lib.Looper;
import lib.util.InterpolatingDouble;
import lib.util.InterpolatingTreeMap;
import lib.util.Logger;
import lib.math.Pose2d;
import lidar.LidarProcessor;

public class Main
{
    private static final int kPoseMapSize = 100;
    private static InterpolatingTreeMap<InterpolatingDouble, Pose2d> sPoses =
            new InterpolatingTreeMap<InterpolatingDouble, Pose2d>(kPoseMapSize);

    public static void main(String[] args)
    {
        Looper mLooper;
        LidarProcessor mLidarProcessor;
        Logger.setVerbosity("DEBUG");

        mLooper = new Looper();
        mLidarProcessor = new LidarProcessor();
        mLooper.register(mLidarProcessor);
        boolean started = mLidarProcessor.isConnected();

        Pose2d zeroPose = new Pose2d();
        double ts = System.currentTimeMillis() / 1000d;
        sPoses.put(new InterpolatingDouble(ts), zeroPose);
        mLooper.start();
        while (true)
        {
            try
            {
                // Lidar Processing occurs in LidarProcessor
                mLidarProcessor.startNewScan(10000);
                Thread.sleep(200);
            }
            catch(Exception e)
            {
                Logger.exception(e);
            }
        }
    }

    public static Pose2d getRobotPose(double timestamp)
    {
        return sPoses.get(new InterpolatingDouble(timestamp));
    }
}

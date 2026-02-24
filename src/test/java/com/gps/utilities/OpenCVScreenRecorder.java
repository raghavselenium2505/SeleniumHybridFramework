package com.gps.utilities;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenCVScreenRecorder {

    private FFmpegFrameRecorder recorder;
    private Java2DFrameConverter converter = new Java2DFrameConverter();
    private Robot robot;
    private Rectangle screenBounds;
    private volatile boolean recording = false;
    private Thread recordingThread;

    public synchronized void startRecording(String testName) throws Exception {

        if (recording) {
            return; // Prevent duplicate start
        }

        screenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String filePath = System.getProperty("user.dir")
                + "\\recordings\\"
                + testName + "_" + timestamp + ".mp4";

        new File(System.getProperty("user.dir") + "\\recordings\\").mkdirs();

        recorder = new FFmpegFrameRecorder(filePath,
                screenBounds.width,
                screenBounds.height);

        recorder.setFormat("mp4");
        recorder.setFrameRate(15);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setVideoBitrate(4000000);
        recorder.setGopSize(30);
        recorder.setVideoOption("preset", "ultrafast");

        recorder.start();

        robot = new Robot();
        recording = true;

        recordingThread = new Thread(() -> {
            try {
                while (recording) {

                    BufferedImage screen =
                            robot.createScreenCapture(screenBounds);

                    Frame frame = converter.convert(screen);
                    recorder.record(frame);

                    Thread.sleep(66); // ~15 FPS
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        recordingThread.setDaemon(true);
        recordingThread.start();
    }

    public synchronized String stopRecording() throws Exception {

        if (!recording) {
            return null;
        }

        recording = false;

        if (recordingThread != null) {
            recordingThread.join();
        }

        if (recorder != null) {
            recorder.stop();
            recorder.release();
        }

        return "Recording Stopped Successfully";
    }
}
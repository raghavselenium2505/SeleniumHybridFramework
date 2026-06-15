package com.seleapi.utils;

import org.bytedeco.javacv.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class VideoRecorderUtil {

    private static FFmpegFrameRecorder recorder;
    private static boolean isRecording = false;
    private static Thread recordingThread;
    private static String filePath;

    public static void startRecording(String testName) throws Exception {

        String folder = System.getProperty("user.dir") + "/Videos/";
        new File(folder).mkdirs();

        filePath = folder + testName + ".mp4";

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        recorder = new FFmpegFrameRecorder(filePath,
                screenSize.width,
                screenSize.height);

        recorder.setFormat("mp4");
        recorder.setFrameRate(15);
        recorder.setVideoCodec(org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264);

        recorder.start();

        isRecording = true;

        recordingThread = new Thread(() -> {
            try {
                Robot robot = new Robot();
                Java2DFrameConverter converter = new Java2DFrameConverter();

                while (isRecording) {
                    BufferedImage screen = robot.createScreenCapture(
                            new Rectangle(screenSize));

                    recorder.record(converter.convert(screen));

                    Thread.sleep(100);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        recordingThread.start();
    }

    public static void stopRecording() throws Exception {

        isRecording = false;

        if (recordingThread != null) {
            recordingThread.join();
        }

        if (recorder != null) {
            recorder.stop();
            recorder.release();
        }
    }

    public static String getVideoPath() {
        return filePath;
    }
}
package com.gps.utilities;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;

public class OpenCVScreenRecorder {
    private FFmpegFrameRecorder recorder;
    private Java2DFrameConverter converter = new Java2DFrameConverter();
    private Robot robot;
    private Rectangle screenBounds;
    private boolean recording = false;

    public void startRecording(String fileName) throws Exception {
        screenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        recorder = new FFmpegFrameRecorder(new File(fileName), screenBounds.width, screenBounds.height);
        recorder.setFormat("mp4");
        recorder.setFrameRate(15);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        
        // ✅ FIX: Change pixel format to avoid error
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);

        // ✅ Fix brightness issue
        recorder.setVideoBitrate(4000000);
        recorder.setGopSize(30);
        recorder.setVideoOption("preset", "medium");

        recorder.start();
        
        robot = new Robot();
        recording = true;

        new Thread(() -> {
            while (recording) {
                try {
                    BufferedImage screenshot = robot.createScreenCapture(screenBounds);

                    // ✅ Convert Image to YUV420P-compatible format
                    BufferedImage correctedImage = correctBrightness(screenshot);

                    Frame frame = converter.convert(correctedImage);
                    recorder.record(frame);
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stopRecording() throws Exception {
        recording = false;
        recorder.stop();
        recorder.release();
    }
    
    private BufferedImage correctBrightness(BufferedImage image) {
        RescaleOp rescaleOp = new RescaleOp(1.0f, -10, null);
        BufferedImage adjustedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = adjustedImage.createGraphics();
        g2d.drawImage(image, rescaleOp, 0, 0);
        g2d.dispose();
        return adjustedImage;
    }
}

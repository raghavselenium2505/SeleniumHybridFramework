package com.seleapi.reporting;

import org.apache.log4j.Logger;

import com.seleapi.utils.VideoRecorderUtil;

public class VideoManager {

    public static Logger log =
            Logger.getLogger(VideoManager.class);

    private VideoManager() {

    }

    public static void startRecording(
            String testName) {

        try {

            VideoRecorderUtil
                    .startRecording(testName);

            log.info(
                    "Video Recording Started : "
                    + testName);

        } catch (Exception e) {

            log.error(
                    "Failed To Start Recording",
                    e);
        }
    }

    public static void stopRecording() {

        try {

            VideoRecorderUtil
                    .stopRecording();

            log.info(
                    "Video Recording Stopped");

        } catch (Exception e) {

            log.error(
                    "Failed To Stop Recording",
                    e);
        }
    }

    public static String getVideoPath() {

        try {

            String videoPath =
                    VideoRecorderUtil
                    .getVideoPath();

            log.info(
                    "Video Path : "
                    + videoPath);

            return videoPath;

        } catch (Exception e) {

            log.error(
                    "Failed To Fetch Video Path",
                    e);

            return null;
        }
    }
}
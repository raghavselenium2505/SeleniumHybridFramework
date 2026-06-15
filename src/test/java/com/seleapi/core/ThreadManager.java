package com.seleapi.core;

import org.apache.log4j.Logger;

public class ThreadManager {

    public static Logger log =
            Logger.getLogger(ThreadManager.class);

    private static ThreadLocal<Long> threadId =
            new ThreadLocal<>();

    public static void setThreadId() {

        Long id = Thread.currentThread().getId();

        threadId.set(id);

        log.info("Thread ID Assigned : " + id);
    }

    public static Long getThreadId() {

        return threadId.get();
    }

    public static void unload() {

        log.info("Removing Thread ID");

        threadId.remove();
    }
}
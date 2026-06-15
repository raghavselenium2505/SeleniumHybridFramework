package com.seleapi.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class TestContext {

    public static Logger log =
            Logger.getLogger(TestContext.class);

    private static ThreadLocal<Map<String, Object>> context =
            ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {

        log.info("Adding Context Data : " + key);

        context.get().put(key, value);
    }

    public static Object get(String key) {

        return context.get().get(key);
    }

    public static void unload() {

        log.info("Clearing Test Context");

        context.remove();
    }
}
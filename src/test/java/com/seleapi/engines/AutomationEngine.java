package com.seleapi.engines;

public interface AutomationEngine {

    void start();

    void stop();

    void launchApplication(String url);
}
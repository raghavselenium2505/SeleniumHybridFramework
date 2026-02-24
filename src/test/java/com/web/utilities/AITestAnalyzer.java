package com.web.utilities;

public class AITestAnalyzer {

    public static String analyze(Exception e) {

        // 1️⃣ PRIORITY: Exception type (MOST ACCURATE)
        if (e instanceof org.openqa.selenium.NoSuchElementException) {
            return "AI: Element not found. Locator is incorrect or element not loaded.";
        }

        if (e instanceof org.openqa.selenium.TimeoutException) {
            return "AI: Timeout occurred. Increase explicit wait or check performance.";
        }

        if (e instanceof org.openqa.selenium.StaleElementReferenceException) {
            return "AI: DOM refreshed. Re-locate the element.";
        }

        if (e instanceof org.openqa.selenium.ElementClickInterceptedException) {
            return "AI: Element blocked. Scroll or use JavaScript click.";
        }

        if (e instanceof org.openqa.selenium.SessionNotCreatedException) {
            return "AI: Browser and driver version mismatch.";
        }

        // 2️⃣ FALLBACK: message-based (only if type unknown)
        String msg = e.getMessage();
        if (msg == null) {
            return "AI: Exception message is null. Check logs.";
        }

        msg = msg.toLowerCase();

        if (msg.contains("invalid selector")) {
            return "AI: Invalid locator syntax. Check XPath/CSS.";
        }

        // 3️⃣ FINAL FALLBACK
        return "AI: Unknown failure. Check screenshot and logs.";
    }
}

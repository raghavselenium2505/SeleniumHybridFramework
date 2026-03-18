package com.web.utilities;

import org.testng.SkipException;

public class RunModeUtil {

	public static void verifyRunMode(String runMode, String source, String testName) {

	    if (runMode.equalsIgnoreCase("no")) {

	        throw new SkipException(
	            "Test skipped because RunMode is NO in " + source + " for -> " + testName
	        );
	    }
	}
}
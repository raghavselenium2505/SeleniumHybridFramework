//TestListener.java

package com.web.utilities;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.web.TestBase;

public class TestListener extends TestBase implements ITestListener{

	public void  OnTestFailure(ITestResult result)
	{
		try
		{
			captureScreenshot("test.png");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

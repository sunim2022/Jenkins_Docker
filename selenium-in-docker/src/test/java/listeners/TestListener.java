package listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class TestListener implements ITestListener {
	
	private static Logger LOG = LogManager.getLogger(TestListener.class);
	
	@Override
	public void onStart(ITestContext context) {
		LOG.info("-------------------- Testing started ----------------------");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		LOG.info("-------------------- " + result.getName() + "PASSED" + "----------------------");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		LOG.info("-------------------- Testing finished ----------------------");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		LOG.error("Test case failed:" + result.getName());
		captureScreenShot(result);
	}	

	private void captureScreenShot(ITestResult result) {

		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

		try {
			captureScreenshotAsFile(result, driver);
			
			//Allure.addAttachment(result.getName(), new ByteArrayInputStream(takeScreenShot(driver)));
			takeScreenShot(driver);
			;
		} catch (Exception e) {
			LOG.error("Exception while taking screenshot ", e);
		}
	}

	private void captureScreenshotAsFile(ITestResult result, WebDriver driver) throws IOException {
		// To create reference of TakesScreenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		// Call method to capture screenshot
		File src = screenshot.getScreenshotAs(OutputType.FILE);

		String ssPath = System.getProperty("user.dir") + "\\screenshots";
		// Copy files to specific location
		FileUtils.copyFile(src, new File(ssPath + "\\" + result.getName() + ".png"));
		LOG.info("Successfully captured a screenshot");
	}
	
	@Attachment(value = "Test screenshot", type = "image/png")
	private byte[] takeScreenShot(WebDriver driver) throws IOException {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}

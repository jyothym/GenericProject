package gen.prj.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;

import gen.prj.pageactions.HomeScreen;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest extends TestListenerAdapter {
	public ExtentTest testReporter;
	public WebDriver driver;
	public HomeScreen homePage;

	protected static final int SLEEP_INTERVAL = 3000;
	Properties prop = new Properties();
	InputStream input = null;

	@BeforeClass
	public WebDriver intializeDriver(Object[] arg0) throws IOException {
		input = new FileInputStream("config.properties");
		prop.load(input);
		String browsername = prop.getProperty("browser");
		String devicename = prop.getProperty("device");
		String osVersion = prop.getProperty("osVersion");
		driver = BrowserFactory.getBrowser(browsername, devicename, osVersion);
		Dimension d = new Dimension(414,736);
		driver.manage().window().setSize(d);
		return driver;
	}

	@BeforeMethod
	public void beforeMethod(Method caller) {
		ExtentTestManager.startTest(caller.getName())
		.assignCategory(Thread.currentThread().getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result, Method caller) throws Exception {
		if (result.isSuccess()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "<pre>" + getStackTrace(result.getThrowable()) + "</pre>");
			System.out.println("******" + result.getThrowable().toString());
			String screenshotPath = CaptureScreenshot.captureScreenshot(driver, caller.getName());
			String image = ExtentTestManager.getTest().addScreenCapture(screenshotPath);
			ExtentTestManager.getTest().log(LogStatus.FAIL, image);
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped");
		}
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	protected String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	@AfterClass
	public void closeBrowser(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		if(driver!=null)
			driver.quit();
		ExtentManager.getInstance().flush();
	}
}

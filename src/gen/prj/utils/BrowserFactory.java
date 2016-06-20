package gen.prj.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

	public static WebDriver getBrowser(String browserName, String device, String osVersion) throws MalformedURLException {
		WebDriver driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch(browserName) {
		case "safari":
			driver = new SafariDriver();
			break;

		case "chrome":
			ChromeDriverManager.getInstance().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "phantomJS":
			PhantomJsDriverManager.getInstance().setup();
			driver = new PhantomJSDriver();
			break;

		case "android":
			capabilities.setCapability("deviceName", device);
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", osVersion);
			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability("setJavaScriptEnabled", true);
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			break;

		case "iOS":
			capabilities.setCapability("deviceName", device);
			// to run on real device, uncomment the below line
			//capabilities.setCapability(MobileCapabilityType.UDID, "ffa50fa3ec0764135fb32ab7024a30a929af5093");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("platformVersion", osVersion);
			capabilities.setCapability("browserName", "safari");
			driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			break;
		}
		return driver;
	}
}

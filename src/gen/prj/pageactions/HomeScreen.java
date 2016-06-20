package gen.prj.pageactions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import gen.prj.pageobjects.HomeScreenObjects;

public class HomeScreen {

	public HomeScreenObjects homeObjects;
	WebDriver driver;
	Properties prop = new Properties();
	InputStream input = null;

	public HomeScreen(WebDriver driver) {
		homeObjects = new HomeScreenObjects(driver);
		this.driver = driver;
	}

	public WebDriver loadApplicationURL() throws IOException, InterruptedException {
		input = new FileInputStream("config.properties");
		prop.load(input);

		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		Thread.sleep(1000);
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return homeObjects.btnSearch.getAttribute("value").equals("Google Search");
			}
		});
		return driver;
	}
}

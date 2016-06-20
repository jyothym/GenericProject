package gen.prj.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitforElement {
	private static final long DEFAULT_TIMEOUT = 10;

	final public static boolean waitForElToBeRemoved(WebDriver driver, final By by) {
	    try {
	        driver.manage().timeouts()
	                .implicitlyWait(0, TimeUnit.SECONDS);

	        WebDriverWait wait = new WebDriverWait(driver,
	                DEFAULT_TIMEOUT);

	        boolean present = wait
	                .ignoring(StaleElementReferenceException.class)
	                .ignoring(NoSuchElementException.class)
	                .until(ExpectedConditions.invisibilityOfElementLocated(by));

	        return present;
	    } catch (Exception e) {
	        return false;
	    } finally {
	        driver.manage().timeouts()
	                .implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
	    }
	}
}

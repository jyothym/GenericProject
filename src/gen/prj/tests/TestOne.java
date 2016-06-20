package gen.prj.tests;

import java.io.IOException;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.Test;

import gen.prj.pageactions.HomeScreen;
import gen.prj.utils.BaseTest;

public class TestOne extends BaseTest {

	@Test
	public void testSearchNow() throws InterruptedException, IOException, ParseException {
		homePage = new HomeScreen(driver);

		System.out.println("Running OneTest...");
		homePage.loadApplicationURL();
		Assert.assertEquals(homePage.homeObjects.btnSearch.getAttribute("value"), "Google ");
	}
}

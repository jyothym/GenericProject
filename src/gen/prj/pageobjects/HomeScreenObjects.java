package gen.prj.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeScreenObjects {

	public HomeScreenObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "sb_ifc0")
	public WebElement txtSearch;
	
	@FindBy(name = "btnK")
	public WebElement btnSearch;
	
	@FindBy(xpath = "title")
	public WebElement lblTitle;

}
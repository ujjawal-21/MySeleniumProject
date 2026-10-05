package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.Utilities;

public class HomePage {
	
	private WebDriver driver;
	private Utilities ut;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		ut = new Utilities();
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "login2")
	private WebElement loginLink;
	
	@FindBy(id = "cartur")
	private WebElement cartLink;
	
	@FindBy(id = "nameofuser")
	private WebElement loggedInUserName;
	
	@FindBy(xpath = "//h4//child::a[contains(@href, '1')]")
	private WebElement productLink;
	
	
	public void clickOnLoginLink() {
		ut.checkElementVisibility(driver, loginLink);
		loginLink.click();
	}
	
	public String getLoggedInUser() {
		ut.checkElementVisibility(driver, loggedInUserName);
		return loggedInUserName.getText();
	}
	
	public void clickOnCartLink() {
		ut.checkElementVisibility(driver, cartLink);
		cartLink.click();
	}
	
	public void clickOnProduct() {	
		ut.checkElementClickable(driver, productLink);
	}
	
	public String getProductName() {
		ut.checkElementVisibility(driver, productLink);
		return productLink.getText();
	}
}

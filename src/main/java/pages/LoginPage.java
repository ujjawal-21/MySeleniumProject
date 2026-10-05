package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.Utilities;

public class LoginPage{
	
	private Utilities ut;
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		ut = new Utilities();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "logInModalLabel")
	private WebElement loginHeaderText;
	
	@FindBy(id = "loginusername")
	private WebElement usernameTxtBox;
	
	@FindBy(id = "loginpassword")
	private WebElement passwordTxtBox;
	
	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginBtn;
	
	public String getLoginHeader() {
		ut.checkElementVisibility(driver, loginHeaderText);
		return loginHeaderText.getText();
	}
	
	public void enterUsername(String username) {
		ut.checkElementVisibility(driver, usernameTxtBox);
		usernameTxtBox.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		ut.checkElementVisibility(driver, passwordTxtBox);
		passwordTxtBox.sendKeys(password);
	}
	
	public void clickOnLogin() {
		ut.checkElementVisibility(driver, loginBtn);
		loginBtn.click();
	}
	

}

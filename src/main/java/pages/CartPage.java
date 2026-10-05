package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.Utilities;

public class CartPage {
	
	private WebDriver driver;
	private Utilities ut;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		ut = new Utilities();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//tbody//following::td)[2]")
	private WebElement productName;
	
	@FindBy(xpath = "//button[@class='btn btn-success']")
	private WebElement placeOrderBtn;

	public String getProductName() {
		ut.checkElementVisibility(driver, productName);
		return productName.getText();
	}
	
	public void placeOrder() {
		ut.checkElementVisibility(driver, placeOrderBtn);
		placeOrderBtn.click();
	}
	
	

}

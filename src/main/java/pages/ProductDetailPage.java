package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.Utilities;

public class ProductDetailPage {
	private WebDriver driver;
	private Utilities ut;
	
	
	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		ut = new Utilities();
		PageFactory.initElements(driver, this);
		msg = "Product added.";
	}
	
	@FindBy(tagName = "h2")
	private WebElement productName;

	@FindBy(tagName = "h3")
	private WebElement productPrice;
	
	@FindBy(tagName = "strong")
	private WebElement productDescription;
	
	@FindBy(xpath = "//a[@onClick='addToCart(1)']")
	private WebElement cartBtn;
	
	private String msg;

	public String getProductName() {
		ut.checkElementVisibility(driver, productName);
		return productName.getText();
	}


	public WebElement getProductPrice() {
		ut.checkElementClickable(driver, productPrice);
		return productPrice;
	}


	public WebElement getProductDescription() {
		ut.checkElementVisibility(driver, productDescription);
		return productDescription;
	}

	public WebElement getCartBtn() {
		ut.checkElementVisibility(driver, cartBtn);
		return cartBtn;
	}
	
	public String getMsg() {
		return msg;
	}
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import library.Utilities;

public class CustomerDetailsPage {

	private Utilities ut;
	private WebDriver driver;
	
	public CustomerDetailsPage(WebDriver driver) {
		ut = new Utilities();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "name")
	private WebElement customerName;
	
	@FindBy(id = "country")
	private WebElement country;
	
	@FindBy(id= "city")
	private WebElement city;
	
	@FindBy(id = "card")
	private WebElement cardNumber;
	
	@FindBy(id = "month")
	private WebElement expiryMonth;
	
	@FindBy(id = "year")
	private WebElement year;
	
	@FindBy(xpath = "//button[text()='Purchase']")
	private WebElement purchaseBtn;
	
	@FindBy(xpath = "(//h2)[3]")
	private WebElement orderConfirmationMsg;
	
	@FindBy(id = "orderModalLabel")
	private WebElement customerDetailPageHeader;
	
	public void enterCustomerName(String name) {
		ut.checkElementVisibility(driver, customerName);
		customerName.sendKeys(name);
	}
	
	public void enterCountry(String cust_country) {
		ut.checkElementVisibility(driver, country);
		country.sendKeys(cust_country);
	}
	
	public void enterCity(String cust_city) {
		ut.checkElementVisibility(driver, city);
		city.sendKeys(cust_city);
	}
	
	public void enterCardNumber(String creditcardNumber) {
		ut.checkElementVisibility(driver, cardNumber);
		cardNumber.sendKeys(creditcardNumber);
	}
	
	public void enterMonth(String month) {
		ut.checkElementVisibility(driver, expiryMonth);
		expiryMonth.sendKeys(month);
	}
	
	public void enterYear(String ExpiryYear) {
		ut.checkElementVisibility(driver, year);
		year.sendKeys(ExpiryYear);
	}
	
	public void clickOnPurchaseBtn() {
		ut.checkElementClickable(driver, purchaseBtn);
		purchaseBtn.click();
	}
	
	public String getOrderConfirmation() {
		ut.checkElementVisibility(driver, orderConfirmationMsg);
		return orderConfirmationMsg.getText();
	}
	
	public String getPageHeader() {
		ut.checkElementVisibility(driver, customerDetailPageHeader);
		return customerDetailPageHeader.getText();
	}
}

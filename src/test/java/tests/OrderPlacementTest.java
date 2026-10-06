package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import library.Base;
import library.ExcelUtils;
import library.ExtentReportListener;
import library.Utilities;
import pages.CartPage;
import pages.CustomerDetailsPage;
import pages.HomePage;
import pages.ProductDetailPage;

@Listeners(ExtentReportListener.class)
public class OrderPlacementTest extends Base {
	private Logger logger = LoggerFactory.getLogger(OrderPlacementTest.class);
	private CustomerDetailsPage cdp;
	private CartPage cp;
	private HomePage hp;
	private Utilities ut;
	private ProductDetailPage pdp;
	
	
	@DataProvider
	public Object[][] testData() {
		Object[][] data = ExcelUtils.getTestData("Sheet1");
		return data;
	}
	
	
	@BeforeMethod
	public void preRequisites() throws InterruptedException {
		initBrowser();
		ut = new Utilities();
		logger.info("*********Logging to App**********");
		ut.loginToApp(getDriver());
		hp = new HomePage(getDriver());
		Thread.sleep(3000);
		logger.info("*********Clicking on a product from Homepage**********");
		hp.clickOnProduct();
		
		logger.info("*********Navigating to PDP page**********");
		pdp = new ProductDetailPage(getDriver());
		logger.info("*********Clicking on Add to cart button**********");
		pdp.getCartBtn().click();
		logger.info("*********Accepting alert**********");
		ut.checkAlertVisibility(getDriver());
		logger.info("*********Clicking on cart link**********");
		hp.clickOnCartLink();
		Thread.sleep(4000);
		logger.info("*********Navigating to cart page**********");
		cp = new CartPage(getDriver());
	}
	
	@Test(dataProvider = "testData")
	public void placeOrderTest(String cust_name, String country, String city, String cardNum, String month, String year) {
		logger.info("*********placeOrderTest Initiated**********");
		try {
			logger.info("*********Clicking on place order button**********");
			cp.placeOrder();
			
			logger.info("*********Customer detail page displayed**********");
			cdp = new CustomerDetailsPage(getDriver());
			Assert.assertEquals(cdp.getPageHeader(), "Place order");
			
			logger.info("*********Entering customer Name**********");
			cdp.enterCustomerName(cust_name);
			
			logger.info("*********Entering customer Country**********");
			cdp.enterCountry(country);
			
			logger.info("*********Entering customer City**********");
			cdp.enterCity(city);
			
			logger.info("*********Entering Card Number**********");
			cdp.enterCardNumber(cardNum);
			
			logger.info("*********Entering Card Expiry Month**********");
			cdp.enterMonth(month);
			
			logger.info("*********Entering Card Expiry Year**********");
			cdp.enterYear(year);
			
			logger.info("*********Clicking on Purchase Button**********");
			cdp.clickOnPurchaseBtn();
			
			logger.info("*********Verifying Order Confirmation Message**********");
			Assert.assertEquals(cdp.getOrderConfirmation(), "Thank you for your purchase!");
		}
		catch(Exception ex) {
			String exceptionName = ex.getClass().getSimpleName();
			String errorMsg = ex.getMessage().split("\\r?\\n")[0];
			
			logger.error("placeOrderTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
			Assert.fail();
		}
	}
	

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
}

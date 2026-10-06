package tests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import library.Base;
import library.ExtentReportListener;
import library.Utilities;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;


@Listeners(ExtentReportListener.class)
public class AddToCartTest extends Base{

	private static Logger logger = LoggerFactory.getLogger(AddToCartTest.class);
	String productName;
	private ProductDetailPage pdp;
	private HomePage hp;
	private Utilities ut;
	
	
	
	@BeforeClass
	public void prequisites() throws InterruptedException {
		initBrowser();
		hp = new HomePage(getDriver());
		ut = new Utilities();
		logger.info("*********Logging to App**********");
		ut.loginToApp(getDriver());
		productName = hp.getProductName();
		Thread.sleep(3000);
		logger.info("*********Clicking on a product from Homepage**********");
		hp.clickOnProduct();
		logger.info("*********Navigating to PDP page**********");
		pdp = new ProductDetailPage(getDriver());
	}
	
	@Test
	public void addProductToCartTest() {
		logger.info("*********addProductToCartTest Initiated**********");
		try {
			logger.info("*********Clicking on Add to cart button**********");
			pdp.getCartBtn().click();
			String alertMsg = ut.checkAlertVisibility(getDriver());
			
			logger.info("*********Verifying alert message**********");
			Assert.assertEquals(alertMsg, pdp.getMsg());
		}
		catch(Exception ex) {
			String exceptionName = ex.getClass().getSimpleName();
			String errorMsg = ex.getMessage().split("\\r?\\n")[0];
			
			logger.error("productdetailPageNavigationTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
			Assert.fail(errorMsg);
		}
			
	}
	
	@Test
	public void checkCartTest() {
		logger.info("*********checkCartTest Initiated**********");
		try {
			logger.info("*********Clicking on cart link**********");
			hp.clickOnCartLink();
			logger.info("*********Navigating to cart page**********");
			CartPage cp = new CartPage(getDriver());
		
			logger.info("*********Verifying product in cart**********");
			Assert.assertEquals(cp.getProductName(), productName);
		}
		catch(Exception ex) {
			String exceptionName = ex.getClass().getSimpleName();
			String errorMsg = ex.getMessage().split("\\r?\\n")[0];
			
			logger.error("productdetailPageNavigationTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
			Assert.fail(errorMsg);
		}
		
	}
	
	@AfterClass
	public void tearDown() {
		getDriver().quit();
	}
	

}

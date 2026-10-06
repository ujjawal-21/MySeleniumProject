package tests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import library.Base;
import library.ExtentReportListener;
import library.Utilities;
import pages.HomePage;
import pages.ProductDetailPage;

@Listeners(ExtentReportListener.class)
public class ProductDetailTest extends Base{

	private Logger logger = LoggerFactory.getLogger(ProductDetailTest.class);
	private ProductDetailPage pdp;
	private Utilities ut;
	private HomePage hp;
	
	
	@BeforeClass
	public void preRequisites() {
		initBrowser();
		ut = new Utilities();
		logger.info("*********Logging to App**********");
		hp = new HomePage(getDriver());
		ut.loginToApp(getDriver());
	}
	
	
	@Test
	public void productdetailPageNavigationTest() throws InterruptedException {
		
		logger.info("*********productdetailPageNavigationTest Initiated**********");
		
		try {
			logger.info("*********Clicking on a product from Homepage**********");
			String currentProduct = hp.getProductName();
			Thread.sleep(5000);
			hp.clickOnProduct();
			
			logger.info("*********Navigating to PDP page**********");
			pdp = new ProductDetailPage(getDriver());
			
			logger.info("*********Verifying product Name on PDP page**********");
			Assert.assertEquals(pdp.getProductName(), currentProduct);
		
		}
		catch(Exception ex) {
			String exceptionName = ex.getClass().getSimpleName();
			String errorMsg = ex.getMessage().split("\\r?\\n")[0];
			
			logger.error("productdetailPageNavigationTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
			Assert.fail(errorMsg);
		}
	}
	
	@Test(priority=1)
	public void pdp_attributeTest() {
		SoftAssert softAssert = new SoftAssert();
		
		logger.info("*********pdp_attributeTest Initiated**********");
		try {
			logger.info("*********Verifying product price**********");
			softAssert.assertTrue(pdp.getProductPrice().isDisplayed());
				
			logger.info("*********Verifying product description**********");
			softAssert.assertTrue(pdp.getProductDescription().isDisplayed(), "Description Failed");
			
			logger.info("*********Verifying cart button**********");
			softAssert.assertTrue(pdp.getCartBtn().isDisplayed(), "Cart Btn failed");
			
			softAssert.assertAll();
				
		}
		catch(Exception ex) {
			String exceptionName = ex.getClass().getSimpleName();
			String errorMsg = ex.getMessage().split("\\r?\\n")[0];
			
			logger.error("pdp_attributeTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
			Assert.fail();
		}
	}
	
	@AfterClass
	public void tearDown() {
		getDriver().quit();
	}
}

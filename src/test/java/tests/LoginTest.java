package tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import library.Base;
import library.ExtentReportListener;
import library.Utilities;
import pages.HomePage;
import pages.LoginPage;


@Listeners(ExtentReportListener.class)
public class LoginTest extends Base{
	private static Logger logger = LoggerFactory.getLogger(LoginTest.class);
	private HomePage hp;
	private Utilities ut;
	private LoginPage lp;
	
	@BeforeMethod
	public void initialize() {
		initBrowser();
		hp = new HomePage(getDriver());
		ut = new Utilities();
		lp = new LoginPage(getDriver());
		hp.clickOnLoginLink();
	}
	
	@Test(priority = 1)
	public void loginSuccessTest() throws Exception {
			logger.info("*********LoginSuccessTest Initiated**********");
			try{
					Assert.assertEquals(lp.getLoginHeader(), "Log in");					
					
					logger.info("*********Injecting valid username**********");
					lp.enterUsername("admin123");
					
					
					logger.info("*********Injecting valid password**********");
					lp.enterPassword("admin");
				
					logger.info("*********Trying to login**********");
					lp.clickOnLogin();
					
					Assert.assertEquals(hp.getLoggedInUser(),"Welcome admin");
				
			}
			catch(NoSuchElementException elementNotFound) {
				String exceptionName = elementNotFound.getClass().getSimpleName();
				String errorMsg = elementNotFound.getMessage().split("\\r?\\n")[0];
				
				logger.error("loginSuccessTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
				Assert.fail();
			}
			catch(UnhandledAlertException uae) {
				String exceptionName = uae.getClass().getSimpleName();
				String errorMsg = uae.getAlertText();
				ut.checkAlertVisibility(getDriver());
				
				logger.error("loginSuccessTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
				Assert.fail();
			}
			catch(Exception ex) {
				String exceptionName = ex.getClass().getSimpleName();
				String errorMsg = ex.getMessage().split("\\r?\\n")[0];
				
				logger.error("loginSuccessTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
				Assert.fail();
			}
	}
	
	
	@Test(priority = 2)
	public void loginFailureTest() throws Exception {
		//Thread.sleep(3000);
		logger.info("*********LoginFailureTest Initiated**********");
		
		try{
			Assert.assertEquals(lp.getLoginHeader(), "Log in");		
			
			logger.info("*********Injecting invalid username**********");
			lp.enterUsername("admin123");
			
			logger.info("*********Injecting invalid password**********");
			lp.enterPassword("admin");

			logger.info("*********Trying to login**********");
			lp.clickOnLogin();
			
			logger.info("*********Error Msg Appeared**********");
			String errorMsg = ut.checkAlertVisibility(getDriver());
			Assert.assertEquals(errorMsg, "Wrong password.");		
		}
		catch(NoSuchElementException elementNotFound) {
			String exceptionName = elementNotFound.getClass().getSimpleName();
			String errorMsg = elementNotFound.getMessage().split("\\r?\\n")[0];
			
			logger.error("loginFailureTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
			Assert.fail();
		}
		catch(Exception ex) {
			String exceptionName = ex.getClass().getSimpleName();
			String errorMsg = ex.getMessage().split("\\r?\\n")[0];
			
			logger.error("loginFailureTest FAILED: {} FOUND => {}", exceptionName, errorMsg);
			Assert.fail();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}

}
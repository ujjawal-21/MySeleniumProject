package library;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.latest.autofill.model.FilledField;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;
import pages.LoginPage;
import sun.net.ftp.FtpDirEntry.Type;

public class Utilities {
	
	
	private void checkElementEvent(WebDriver driver, WebElement element, By locator, String event) {
	
		switch(event) {
			case "ElementToBeClickable":
				new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(element));
				break;
			case "ElementToBeVisibleByElement":
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
				break;
			case "ElementToBeVisibleByLocator":
				new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(locator));
				break;
			case "alertIsPresent":
				new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
				break;
		}
		
	}
	
	public void checkElementVisibility(WebDriver driver, WebElement element) {
		checkElementEvent(driver, element, null, "ElementToBeVisibleByElement");
	}
	
	public By checkElementVisibility(WebDriver driver, By locator) {
		checkElementEvent(driver, null, locator, "ElementToBeVisibleByLocator");
		return locator;
	}
	
	public void checkElementClickable(WebDriver driver, WebElement element) {
		checkElementEvent(driver, element, null, "ElementToBeClickable");
		element.click();
	}
	
	public String checkAlertVisibility(WebDriver driver) {
		checkElementEvent(driver, null, null, "alertIsPresent");
		String msg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return msg;
	}
	
	public void takeScreenshot(WebDriver driver, String testCaseName) throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String timestamp = LocalDateTime.now().format(formatter);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String des = currentDir+"/screenshots/"+testCaseName+"_"+timestamp+".png";
		FileUtils.copyFile(src, new File(des));
	}

	public void loginToApp(WebDriver driver) {
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		
		hp.clickOnLoginLink();
		lp.enterUsername("admin");
		lp.enterPassword("admin");
		lp.clickOnLogin();
	}
}

package tests;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNGDemo {
	
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		System.out.println("-----Executing New Test Case------");
	}

	@Parameters("browser")
	@Test(groups = "smoke")
	public void testA(String browser) {
		if(browser.equalsIgnoreCase("edge")) {
			WebDriver driver = new EdgeDriver();
		}
	}
	
	
	@Test(groups = "smoke")
	public void testB() {
		System.out.println("***TEST B***");
	}
	
	@Test(groups = {"smoke", "regression"})
	public void testC() {
		System.out.println("***TEST C***");
	}
	
	@Test(groups = "regression")
	public void testD() {
		System.out.println("***TEST D***");
	}
	
	@Test(groups = "sanity")
	public void testE() {
		System.out.println("***TEST E***");
	}
	
	@AfterMethod(alwaysRun = true)
	public void close() {
		System.out.println("--------Execution Done---------");
	}

}
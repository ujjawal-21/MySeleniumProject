package tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@Listeners(ListenerActivity.class)
public class TestDemoA {
	
	@DataProvider(name = "userDetails")
	public Object[][] getData() {
		return new Object[][] {
			{"user1", "pass1"},
			{"user2", "pass2"}
		};
	}

	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		System.out.println("-----Executing New Test Case------");
	}

	@Test(dataProvider = "userDetails")
	public void test1(String username, String password) {
		System.out.println("Username: "+username+" Password: "+password);
	}
	
	
	@Test
	public void test2(){
		System.out.println(10/0);
	}
	
	@Test(priority = 1, dependsOnMethods = "test2")
	public void test3() {
		System.out.println("***TEST 3***");
	}
	
	@Test(priority = -3)
	public void test4() {
		System.out.println("***TEST 4***");
	}
	
	@Test(priority = -2)
	public void test5() {
		System.out.println("***TEST 5***");
	}
	
	@AfterMethod(alwaysRun = true)
	public void close() {
		System.out.println("--------Execution Done---------");
	}
}

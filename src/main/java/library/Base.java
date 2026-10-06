package library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Base {
	
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private Properties prop;
	private FileInputStream fin;
	private Logger logger = LoggerFactory.getLogger(Base.class);
	private EdgeOptions options;
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public Base() {
		options = new EdgeOptions();

		options.setUnhandledPromptBehaviour(
		    UnexpectedAlertBehaviour.IGNORE
		);
		
		options.addArguments("headless=new");
		prop = new Properties();
		try {
			fin = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void initBrowser() {
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			logger.info("*********Launching Chrome Browser********");
			driver.set(new ChromeDriver());
		}
		else {
			logger.info("*********Launching Edge Browser********");
			driver.set(new EdgeDriver(options));
		}
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

}

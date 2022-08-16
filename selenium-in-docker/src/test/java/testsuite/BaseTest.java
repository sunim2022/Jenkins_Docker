package testsuite;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.demo.constants.PageConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;

	public WebDriver initializeDriver(String browserName) {
		
		try {
			
			System.out.println("###################" + browserName);
			
			if (browserName.equals(PageConstants.CHROME_BROWSER)) {

				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				options.addArguments("--headless","--whitelisted-ips","--no-sandbox","--disable-extensions");
				driver = new ChromeDriver(options);

			} else if (browserName.equals(PageConstants.FIREFOX_BROWSER)) {

				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				options.addArguments("--headless","--whitelisted-ips","--no-sandbox","--disable-extensions");
				driver = new FirefoxDriver(options);

			} else if (browserName.equals(PageConstants.EDGE_BROWSER)) {

				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				options.addArguments("--headless","--whitelisted-ips","--no-sandbox","--disable-extensions");
				driver = new EdgeDriver(options);

			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		return driver;
	}
	
	

}

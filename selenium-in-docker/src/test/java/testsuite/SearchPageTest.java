package testsuite;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demo.constants.PageConstants;
import com.demo.pages.SearchPage;
import com.demo.pages.SearchResults;

import listeners.TestListener;

@Listeners(TestListener.class)
public class SearchPageTest extends BaseTest {

	@Parameters({"browser"})
	@BeforeClass
	public void beforeTest(@Optional("Chrome") String browser, ITestContext testContext) {

		driver = initializeDriver(browser);
		testContext.setAttribute("driver", driver);		
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
	}
	
	@Test
	public void launchDSPortalTest() {

		driver.get(PageConstants.LAUNCH_URL);
		SearchPage launchPage = new SearchPage(driver);
		Assert.assertEquals(launchPage.getTitle(), PageConstants.LAUNCH_PAGE_TITLE);
		
		String str = "hello world";
		SearchResults homePage = launchPage.enterText(str);
		Assert.assertEquals(homePage.getTitle(), str + PageConstants.SEARCH_PAGE_TITLE);

	}


}

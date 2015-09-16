package automationFramework;

import java.net.URL;
import java.net.MalformedURLException;
import org.testng.annotations.*;
import org.hamcrest.core.Is;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCase {
	private ExtentReports extent;
    private ExtentTest test;
	private String webLink = "http://scottyoon221.github.io/";
    private WebDriver driver;// = new FirefoxDriver();
    private DesiredCapabilities capability = null;  

    @Parameters({"browser", "version", "os"})
    @BeforeClass
    public void beforeClass(String browser, String version, String os) throws MalformedURLException, InterruptedException {
    	extent = new ExtentManager().setExtentReports(browser);
    	if(browser.equals("firefox")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");     
            capability.setVersion(version);
    	}
    	
	    if(browser.equals("iexplore")) {    
	        capability = DesiredCapabilities.internetExplorer();
	        capability.setBrowserName("iexplore");
	      //make sure to download and install IEDriverserver.exe in your machine first
	        System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
	        capability.setVersion(version);
	    } 
	    
	    if(browser.equals("chrome")) {    
	    	System.out.println("Test scripts running on chrome");
	        capability = DesiredCapabilities.chrome();
	        capability.setBrowserName("chrome");
	        //make sure to download and install chromedriver in your machine first
	        System.setProperty("webdriver.chrome.driver", "chromedriver");
	        capability.setVersion(version);
	    } 
	    
	    if(os.equals("WINDOWS")) {    
	    	capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
	    }
		else if(os.equals("XP")) {
			capability.setPlatform(org.openqa.selenium.Platform.XP);
		}
		else if(os.equals("Mac")) {
			capability.setPlatform(org.openqa.selenium.Platform.MAC);
		} 
		else if(os.equals("Linux")) {
			capability.setPlatform(org.openqa.selenium.Platform.LINUX);
		}  
		else {
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
		}  
	    
	    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
    } 

	@AfterTest 
	public void afterTest() throws Exception {
		//Close the browser
		driver.quit();
	}
	
	@AfterMethod
	public void afterMethod() throws Exception {
		extent.endTest(test);
		extent.flush();
	}
	
	@Test
	public void testTitle() {
		
		test = extent
        .startTest("Portfolio Title", "Verifies if title is displayed")
        .assignCategory("BVT");
        try
        {
        	test.log(LogStatus.INFO, "Navigating to scottyoon221.github.io");
        	driver.get(webLink);
        	// Verify the title
        	MatcherAssert.assertThat(driver.getTitle(), Is.is("Scott Yoon | Portfolio"));
        	test.log(LogStatus.PASS, "Title is correctly displayed");
        }
        catch (AssertionError e)
        {
        	test.log(LogStatus.FAIL, "<pre>" + e.toString() + "</pre>");
            throw e;
        }    
	}
	
	@Test(enabled = false)
	public void testEmailLink() {
		test = extent
        .startTest("Email Icon", "Verifies if email icon is clickable")
        .assignCategory("BVT");
		
		try
        {
			driver.get(webLink);
	    	// Click email icon on navigation bar
		    driver.findElement(By.className("fa-envelope")).click();
		    test.log(LogStatus.PASS, "Successfully clicked email icon");
        }
        catch (AssertionError e)
        {
        	test.log(LogStatus.FAIL, "<pre>" + e.toString() + "</pre>");
            throw e;
        }    
	}

	@Test
	public void testGitHubLink() {
		test = extent
        .startTest("Github Icon", "Verifies if github icon is clickable")
        .assignCategory("BVT");
		
		try
        {
			driver.get(webLink);
			// Click github icon on navigation bar
		    driver.findElement(By.className("fa-github-alt")).click();
		    // Switch to new window
		    for (String handle : driver.getWindowHandles()) {
		        driver.switchTo().window(handle);
		    }
		    MatcherAssert.assertThat(driver.getCurrentUrl(), Is.is("https://github.com/scottyoon221"));
		    test.log(LogStatus.PASS, "Successfully clicked github icon");
        }
        catch (AssertionError e)
        {
        	test.log(LogStatus.FAIL, "<pre>" + e.toString()+"</pre>");
            throw e;
        }    
	}
	
	@Test
	public void testProjectLink() {
		test = extent
        .startTest("Project Link", "Verifies the opcity of project section changes to 1 upon clicking project link")
        .assignCategory("BVT");
		
		try
        {
			driver.get(webLink);
			// Click project link
			driver.findElement(By.className("projects")).click();
			Thread.sleep(2000);
			// Verify the opacity of project is 1
			WebElement element = driver.findElement(By.className("project-section"));
			MatcherAssert.assertThat(element.getCssValue("opacity"), Is.is("1"));
			test.log(LogStatus.PASS, "Successfully clicked project link");
        }
        catch (AssertionError e)
        {
        	test.log(LogStatus.FAIL, "<pre>" + e.toString()+"</pre>");
            throw e;
        }    
        catch (InterruptedException e) {
        	test.log(LogStatus.FAIL, "<pre>" + e.toString()+"</pre>");
        }
	}
}

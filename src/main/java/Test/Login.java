package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import Utility.*;

import MiHCM.*;

	public class Login {

	    WebDriver driver;

	    LoginPage objLogin;

	    HomePage objHomePage;
	    ExtendReport extRpt = new ExtendReport();


	    /**

	     * This test case will login in http://demo.guru99.com/V4/

	     * Verify login page title as guru99 bank

	     * Login to application

	     * Verify the home page using Dashboard message
	     * @throws ParseException 
	     * @throws IOException 
	     * @throws FileNotFoundException 

	     */

	    @Test(priority=0)

	    public void test_Home_Page_Appear_Correct() throws FileNotFoundException, IOException, ParseException{
	    	System.setProperty("webdriver.chrome.driver","C:\\seldrv\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
	        driver.get(JSONReader.ReadJSONFile("URL", "./Data/data.json"));
	        extRpt.setUpReport();
	        //Create Login Page object
	    extRpt.startTestCase("User Login");
	    objLogin = new LoginPage(driver);

	    //Verify login page title

	    String loginPageTitle = objLogin.getLoginTitle();

	    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

	    //login to application

	    objLogin.loginToGuru99(JSONReader.ReadJSONFile("User_Name", "./Data/data.json"), JSONReader.ReadJSONFile("Password", "./Data/data.json"));

	    // go the next page

	    objHomePage = new HomePage(driver);

	    //Verify home page

	    if (objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123")  )
	    {
	    	extRpt.logEvents("Logged Successfully");	
	    }
	    else 
	    {
	    	extRpt.logEventsFail("Did not log in successfully...");
	    }
	    
	    
	    extRpt.createFinalReport();
	    }
	}
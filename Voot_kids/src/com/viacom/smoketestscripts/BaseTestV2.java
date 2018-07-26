package com.viacom.smoketestscripts;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.utils.ExtentManager;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;

public class BaseTestV2 {
	public AndroidDriver driver;

	public ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	protected Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
	public int networkCount = 0;
	public int sessionCount = 0;
  @BeforeSuite
  public void suite()
  {
	  
  }

	@BeforeMethod
	public void setup() throws InterruptedException {
		System.out.println("BeforeMethod()...");
	 	com.viacom.utils.Utilities.startAppiumServer();
	
		
	}


	@AfterMethod
	public void tearDown() throws Exception {
		System.out.println("AfterMethod()");

		if (rep != null) {
			rep.endTest(test);
			System.out.println("rep...");
			rep.flush();
		}

		try {
			if (driver != null)
				/*driver.resetApp();
			System.out.println("Reseted..");*/
				driver.quit();

		} finally {

			com.viacom.utils.Utilities.stopAppiumServer();
			System.out.println("server stopped");
		}		

	}

	
	public void launchApp() throws InterruptedException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", VootConstants.BROWSER_NAME);
		capabilities.setCapability("VERSION", VootConstants.DEVICE_VERSION);
	    capabilities.setCapability("deviceName", VootConstants.DEVICE_NAME);
		capabilities.setCapability("platformName", VootConstants.PLATFORM_NAME);
		capabilities.setCapability("udid","RS50042139aca" );
		capabilities.setCapability("newCommandTimeout", 60 * 5);

		if (VootConstants.ENVIRONMENT.equalsIgnoreCase("PreProduction")) {
			File app = new File(VootConstants.APK_PATH);
			capabilities.setCapability("app", app.getAbsolutePath());
		} else if (VootConstants.ENVIRONMENT.equalsIgnoreCase("Production")) {
			capabilities.setCapability("appPackage", "com.tv.vootkids");
			capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKHomeActivity");
			System.out.println("Production,...");
			capabilities.setCapability("autoDismissAlerts", true);	
			capabilities.setCapability("noReset","true");
			  capabilities.setCapability("fullReset", "false");
		}
		try {
			
			driver = new AndroidDriver(new URL(VootConstants.HUB_URL), capabilities);
		} catch (Exception e) {

			test.log(LogStatus.FAIL, "Application did not launch as Appium session not created");
			e.printStackTrace();
			Assert.fail("Application did not launch as Appium session not created");

		}
			try {

				/*if (driver.findElementsByName("in-app message image").size() > 0) {
					driver.findElementByName("in-app message close").click();
				}*/
				if(driver==null)
					BasePageV2.reportFail("Application did not launch unexpectedly");
				/*driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();*/

			} catch (Exception e) {}
		 
			/*for(int i=0;i<10;i++)
			{
				try{
					driver.findElementById("com.tv.v18.viola:id/gateway_btn_view").click();
					break;
				}catch(Exception e)
				{
					Thread.sleep(2000);
				}
			}
*/
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	@AfterSuite
	public void close() throws Exception {
		// SendMail.main(null);
		System.out.println("after suite");
		

	}

}

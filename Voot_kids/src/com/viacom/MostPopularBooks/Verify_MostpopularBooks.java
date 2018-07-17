package com.viacom.MostPopularBooks;

import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class Verify_MostpopularBooks extends BaseTestV2 {
	String testName = "Verify_MostpopularBooks";
	public static int rowno=0;
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{
		if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Most popular Audio stories");
		test.log(LogStatus.INFO, "Starting the test to Verify Most Popular Books tray: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno , "Verify the availability of favourites tray when user is not favourited any episode/book/audiobook");			
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);	
		if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10))
		{
    		homepagev2.read_tab.click();
	    	   String text="MOST POPULAR EBOOKS";
	    	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	    	test.log(LogStatus.INFO, "Navigation to (read) tab is passed");
		    BasePageV2.takeScreenshot();  
		}
		else
		BasePageV2.reportFail("Unable to Navigate to Listen tab");
		
	  int list = driver.findElementsByXPath("//android.widget.TextView[@text='MOST POPULAR EBOOKS']/ancestor::android.view.ViewGroup[@index='1']//android.view.ViewGroup").size();		
	  Utilities.scrolltoElementusingtext(driver, "//android.widget.Button[@text='SEE ALL']/../..//android.widget.TextView[@text='MOST POPULAR EBOOKS']");
	  if(list>=6)
	  {
		  //need to implement using api
	      if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
		  homepagev2.seeAll.click();
	  }
	  else
	  {
		  test.log(LogStatus.FAIL, "See all is not visible as card count is 8");
		  BasePageV2.takeScreenshot();
	  }
		  
	}		
			@DataProvider
			public Object[][] getData(){
			return DataUtil.getData(testName,xls);							
				}
		}
			

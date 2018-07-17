package com.viacom.smoketestscripts;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class Masthead_carouselScripts extends BaseTestV2 {
	
	String testName = "Masthead_carouselScripts";
	public static int rowno=0;
	public static int rowno1=0;
	
	
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{
		if(GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Verify Masthead_carousel of all tabs");
		test.log(LogStatus.INFO, "Starting the test to Verify Masthead_carousel of all tabs: "+VootConstants.DEVICE_NAME);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno , "Verify the Carousel section UI in My Stuff tab(One card should be fully visible,2nd card should be half visible)");			
		 int rowno1=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno1 ,"Verify the Carousel section UI in My Stuff tab(Two Cards should be completely visible,3rd tile should be half visible)");	
		
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		//Api call to verify Home mystuffCarousel
		String url_mystuffCarousel=xls.getCellData("Api", 1, 4);
	    Map map = BasePageV2.apiparams(4);
	    System.out.println(map);
		System.out.println(url_mystuffCarousel);
		Response resp_mystuffCarousel=Utilities.requestUtilitypost(url_mystuffCarousel, map); 
		System.out.println(resp_mystuffCarousel);
		
		
		//End of Api call to verify Home mystuffCarousel
		if(homepagev2.mystuff_tab.isSelected())
    	{
		   test.log(LogStatus.INFO, "Verifying masthead carousel for My stuff");
		   if(Utilities.explicitWaitVisible(driver, homepagev2.mast_carousel, 10))
		   {
		    int size = driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']")).size();
		    System.out.println(size);
		    if(size>=2)
		    {
		     test.log(LogStatus.PASS, "Verify the Carousel section UI in My Stuff tab(One card should be fully visible,2nd card should be half visible)is passed");
		     BasePageV2.takeScreenshot();
		     homepagev2.smokeresults("",rowno1, "pass");
		    	 }
		    	   else
		    		   test.log(LogStatus.FAIL, "Verify the Carousel section UI in My Stuff tab(One card should be fully visible,2nd card should be half visible)is failed");		   
		       try{
		    	   Utilities.horizontalSwipe(driver);
		    	   Thread.sleep(2000);
		          }
		       catch (Exception e) {}
		       int size2 = driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']")).size();
		       System.out.println(size2);
		       if(size2>=3)
		       {
		    	test.log(LogStatus.PASS, "Verify the Carousel section UI in My Stuff tab(Two Cards should be completely visible,3rd tile should be half visible)is passed");
		    	BasePageV2.takeScreenshot();
		        homepagev2.smokeresults("",rowno1, "pass");
		       }
		    	else
		    		test.log(LogStatus.FAIL, "Verify the Carousel section UI in My Stuff tab(Two Cards should be completely visible,3rd tile should be half visible)is failed");		       		       
		       }
		       else
		    	    test.log(LogStatus.FAIL, "Unable to find Masthead carousel");    	
    	 }	
    	else
 	       test.log(LogStatus.FAIL, "Unable to find My Stuff tab");    	    	    	   	   
	}		
		@DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					
		}
}
	


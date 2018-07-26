package com.viacom.smoketestscripts;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyLastViewedUI extends BaseTestV2{
	
	String testName = "VerifyLastViewedUI";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedUI(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("Verify UI of Last Viewed in My Stuff tab");
		test.log(LogStatus.INFO, "Starting test for 165: Verify the UI of 'Last Viewed' section in My Stuff Tab");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls165 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno165=xls165.getRowCount("Smoke_Results")+1;
		xls165.setCellData("Smoke_Results", "Testcase Name",rowno165,"165: Verify the UI of 'Last Viewed' section in My Stuff Tab");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 basepagev2.takeScreenshot();
			 //System.out.println(driver.getPageSource());
		 }
		//Login module to be added
		
		//Verification of 165
		test.log(LogStatus.INFO, "Starting test for 165: Verify the UI of 'Last Viewed' section in My Stuff Tab");
		//Click on My Profile tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
			 homepagev2.mystuff_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on My Stuff tab");
			 basepagev2.takeScreenshot();
		 }
		 else
			 basepagev2.reportFail("Unable to click on My Stuff tab");
		 
		 //Verify LAST VIEWED tray title
		 if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedTrayTitle, 5)) {
			 if(homepagev2.lastViewedTrayTitle.getAttribute("text").equals("LAST VIEWED"))
				 test.log(LogStatus.INFO, "LAST VIEWED tray title is displayed");
			 else {
				 test.log(LogStatus.INFO, "LAST VIEWED tray title is not displayed");
				 errCount++;
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "LAST VIEWED tray title is not displayed");
			 errCount++;
		 }
		 
		 //Verify Clear button
		 if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedClear, 3)) {
			 if(homepagev2.lastViewedClear.getAttribute("text").equals("Clear"))
				 test.log(LogStatus.INFO, "Clear button is displayed");
			 else {
				 test.log(LogStatus.INFO, "Clear button is not displayed");
				 errCount++;
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "Clear button is not displayed");
			 errCount++;
		 }
		 
		 
		 
		 //Fetch all cards
		 List<WebElement> lastViewedNames;
		 List<WebElement> lastViewedSub;
		 ArrayList<String> completeList=new ArrayList<String>();
		 boolean complete=false;
		 int lastViewedKey=1;
		 String fromUI;
		 while(complete==false) {
			lastViewedNames=driver.findElementsByXPath("//android.widget.TextView[@text='LAST VIEWED']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.view.ViewGroup//android.widget.TextView[@index='3']");
			lastViewedSub=driver.findElementsByXPath("//android.widget.TextView[@text='LAST VIEWED']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.view.ViewGroup//android.widget.TextView[@index='4']");
			if(lastViewedNames.size()==0) {
				lastViewedNames=driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@index='0']//android.view.ViewGroup//android.widget.TextView[@index='3']");
				lastViewedSub=driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@index='0']//android.view.ViewGroup//android.widget.TextView[@index='4']");
				//android.view.ViewGroup[@index='0']//android.widget.FrameLayout[@index='1']
				if(lastViewedNames.size()==0) {
					complete=true;
				}	
				else {
					for(int count=0;count<lastViewedNames.size();count++) {
						fromUI=lastViewedSub.get(count).getAttribute("text")+" "+lastViewedNames.get(count).getAttribute("text");
						if(!completeList.contains(fromUI)) 
							completeList.add(fromUI);										
					}
					complete=true;
				}
			}
			else {
				for(int count=0;count<lastViewedNames.size();count++) {
					fromUI=lastViewedSub.get(count).getAttribute("text")+" "+lastViewedNames.get(count).getAttribute("text");
					if(!completeList.contains(fromUI)) 
						completeList.add(fromUI);										
				}
			}
			Utilities.verticalSwipe(driver);
			Thread.sleep(2000);
		 }
		System.out.println(completeList);
		//From watchHistory API
		int api_totalItems_watchHistory=0;
		String url_watchHistory="";
		String api_watchHistory="Watch History";
		String apiname_watchHistory="";
		Response resp_watchHistory=null;
		int rows_watchHistory=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rows_watchHistory;rNum++){
			apiname_watchHistory=xls.getCellData("Api", "API Name", rNum);
			if(apiname_watchHistory.equals(api_watchHistory)){
				url_watchHistory=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(2, xls, api_watchHistory);
				resp_watchHistory=Utilities.requestUtilitypost(url_watchHistory, map);
				api_totalItems_watchHistory=resp_watchHistory.jsonPath().get("assets.items.size()");
			 }
		}
		if(api_totalItems_watchHistory==8) {
			//Add code for non availablity of See All button display
		}
		if(api_totalItems_watchHistory<=8) {
			//Add code for non availability of See All button display
			if(api_totalItems_watchHistory==completeList.size()) {
				test.log(LogStatus.INFO, "Last viewed displays "+completeList.size()+" assets in the carousal and same is returned by the API");
			}
			else {
				 test.log(LogStatus.FAIL, "There is a mismatch in the total items from API and that displayed in UI. Last viewed displays "+completeList.size()+" assets in the carousal API returns "+api_totalItems_watchHistory);
				 homepagev2.smokeresults("165: Verify the UI of 'Last Viewed' section in My Stuff Tab",rowno165, "FAIL");
				 homepagev2.reportFail("165: Verify the UI of 'Last Viewed' section in My Stuff Tab is FAIL");
			}
			//Verify items displayed with API
			for(int count=0;count<api_totalItems_watchHistory;count++) {
				String descAPI=resp_watchHistory.jsonPath().get("assets.items["+count+"].desc");
				String episodeAPI=resp_watchHistory.jsonPath().get("assets.items["+count+"].episodeNo");
				int intseasonAPI=resp_watchHistory.jsonPath().get("assets.items["+count+"].season");
				String seasonAPI=Integer.toString(intseasonAPI);
				String refSeriesTitleAPI=resp_watchHistory.jsonPath().get("assets.items["+count+"].refSeriesTitle");
				String fromAPI="E"+episodeAPI+" "+"S"+seasonAPI+" - "+descAPI+" "+refSeriesTitleAPI;
				//Checking order
				if(fromAPI.equals(completeList.get(count)))
					test.log(LogStatus.INFO, "Matching UI and API data for "+refSeriesTitleAPI+" "+"E"+episodeAPI+" "+"S"+seasonAPI);
				else {
					test.log(LogStatus.FAIL, "Non Matching UI and API data for "+refSeriesTitleAPI+" "+"E"+episodeAPI+" "+"S"+seasonAPI);
					errCount++;
				}		
			}
			if(errCount>0) {
				homepagev2.smokeresults("165: Verify the UI of 'Last Viewed' section in My Stuff Tab",rowno165, "FAIL");
				homepagev2.reportFail("165: Verify the UI of 'Last Viewed' section in My Stuff Tab is FAIL");
			}
			else {
				homepagev2.smokeresults("165: Verify the UI of 'Last Viewed' section in My Stuff Tab",rowno165, "PASS");
				homepagev2.reportPass("165: Verify the UI of 'Last Viewed' section in My Stuff Tab is PASS");
			}
			
		}
		else {
			//Add Code for availability of See All button	
		}

	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}

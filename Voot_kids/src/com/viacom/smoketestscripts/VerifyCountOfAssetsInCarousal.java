package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class VerifyCountOfAssetsInCarousal extends BaseTestV2{
	
	String testName = "VerifyCountOfAssetsInCarousal";
	@Test(dataProvider = "getData")
	public void VerifyCountOfAssetsInCarousal(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("Verify Count of Assets In Carousal");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls142 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno142=xls142.getRowCount("Smoke_Results")+1;
		xls142.setCellData("Smoke_Results", "Testcase Name",rowno142,"142: Validate the Maximum count of assets in the Masthead Carousel section under all tabs.");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homePage=new HomePageV2(driver,test);
		 if(Utilities.explicitWaitVisible(driver, homePage.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 BasePageV2.takeScreenshot();
			 //System.out.println(driver.getPageSource());
		 }
		 
		//Login module to be added
		 
//Verification for My Stuff 	 
		HashSet<String> myStuffSet=new HashSet<String>();
		while(homePage.watch_tab.getAttribute("selected").equals("false")) {
			List<WebElement> carousalListMyStuff=driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@index='0']/android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
			for(int count=0;count<carousalListMyStuff.size();count++) {
				myStuffSet.add(carousalListMyStuff.get(count).getText());
			}
			Utilities.horizontalSwipe(driver);		
		}
		if(myStuffSet.size()>5) {
			test.log(LogStatus.FAIL, "My Stuff displays more than 5 assets in the carousal.");
			errCount++;
		}
		//Verify API results
		//Calling getPersonalizedCarousel.json API for My Stuff tab
		 int api_totalItems_mytuff=0;
		 String url_myStuff="";
		 String api_myStuff="Carousal Card Count";
		 String apiname_myStuff="";
		 int rowsMyStuff=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsMyStuff;rNum++){
			 apiname_myStuff=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_myStuff.equals(api_myStuff)){
				 url_myStuff=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(2, xls, api_myStuff);
				 Response resp_mystuff=Utilities.requestUtilitypost(url_myStuff, map);
				 api_totalItems_mytuff=resp_mystuff.jsonPath().get("assets.items.size()");
			 }
		 }
		 if(myStuffSet.size()==api_totalItems_mytuff) {
			 test.log(LogStatus.PASS, "My Stuff displays "+myStuffSet.size()+" assets in the carousal and same is returned by the API");
		 }
		 else {
			 test.log(LogStatus.FAIL, "My Stuff displays "+myStuffSet.size()+" unique assets in the carousal but API returns "+api_totalItems_mytuff);
			 errCount++; 
		 }
		 
//Verification for Watch	 
			HashSet<String> watchSet=new HashSet<String>();
			while(homePage.Listen_tab.getAttribute("selected").equals("false")) {
				List<WebElement> carousalListWatch=driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@index='0']/android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
				for(int count=0;count<carousalListWatch.size();count++) {
					watchSet.add(carousalListWatch.get(count).getText());
				}
				Utilities.horizontalSwipe(driver);		
			}
			if(watchSet.size()>5) {
				test.log(LogStatus.FAIL, "Watch tab displays more than 5 assets in the carousal.");
				errCount++;
			}
			//Verify API results
			//Calling getPersonalizedCarousel.json API for Watch tab
			 int api_totalItems_watch=0;
			 String url_watch="";
			 String api_watch="Watch Card Count";
			 String apiname_watch="";
			 int rows_watch=xls.getRowCount("Api");
			 for(int rNum=1;rNum<=rows_watch;rNum++){
				 apiname_watch=xls.getCellData("Api", "API Name", rNum);
				 if(apiname_watch.equals(api_watch)){
					 url_watch=xls.getCellData("Api", "Url", rNum);
					 Map map=BasePageV2.apiparams(2, xls, api_watch);
					 Response resp_watch=Utilities.requestUtilitypost(url_watch, map);
					 api_totalItems_watch=resp_watch.jsonPath().get("assets.items.size()");
				 }
			 }
			 if(watchSet.size()==api_totalItems_watch) {
				 test.log(LogStatus.PASS, "Watch displays "+watchSet.size()+" assets in the carousal and same is returned by the API");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Watch displays "+watchSet.size()+" unique assets in the carousal but API returns "+api_totalItems_watch);
				 errCount++; 
			 }
			 
//Verification for Audio	 
				HashSet<String> audioSet=new HashSet<String>();
				while(homePage.read_tab.getAttribute("selected").equals("false")) {
					List<WebElement> carousalListAudio=driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@index='0']/android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
					for(int count=0;count<carousalListAudio.size();count++) {
						audioSet.add(carousalListAudio.get(count).getText());
					}
					Utilities.horizontalSwipe(driver);		
				}
				if(audioSet.size()>5) {
					test.log(LogStatus.FAIL, "Audio tab displays more than 5 assets in the carousal.");
					errCount++;
				}
				//Verify API results
				//Calling getPersonalizedCarousel.json API for Audio tab
				 int api_totalItems_audio=0;
				 String url_audio="";
				 String api_audio="Audio Card Count";
				 String apiname_audio="";
				 int rows_audio=xls.getRowCount("Api");
				 for(int rNum=1;rNum<=rows_audio;rNum++){
					 apiname_audio=xls.getCellData("Api", "API Name", rNum);
					 if(apiname_audio.equals(api_audio)){
						 url_audio=xls.getCellData("Api", "Url", rNum);
						 Map map=BasePageV2.apiparams(2, xls, api_audio);
						 Response resp_audio=Utilities.requestUtilitypost(url_audio, map);
						 api_totalItems_audio=resp_audio.jsonPath().get("assets.items.size()");
					 }
				 }
				 if(audioSet.size()==api_totalItems_audio) {
					 test.log(LogStatus.PASS, "Audio displays "+audioSet.size()+" assets in the carousal and same is returned by the API");
				 }
				 else {
					 test.log(LogStatus.FAIL, "Audio displays "+audioSet.size()+" unique assets in the carousal but API returns "+api_totalItems_audio);
					 errCount++; 
				 }
//Verification for Read
					HashSet<String> readSet=new HashSet<String>();
					while(homePage.search_tab.getAttribute("selected").equals("false")) {
						List<WebElement> carousalListRead=driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@index='0']/android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
						for(int count=0;count<carousalListRead.size();count++) {
							readSet.add(carousalListRead.get(count).getText());
						}
						Utilities.horizontalSwipe(driver);		
					}
					if(readSet.size()>5) {
						test.log(LogStatus.FAIL, "Read tab displays more than 5 assets in the carousal.");
						errCount++;
					}
					//Verify API results
					//Calling getPersonalizedCarousel.json API for Audio tab
					 int api_totalItems_read=0;
					 String url_read="";
					 String api_read="Read Card Count";
					 String apiname_read="";
					 int rows_read=xls.getRowCount("Api");
					 for(int rNum=1;rNum<=rows_read;rNum++){
						 apiname_read=xls.getCellData("Api", "API Name", rNum);
						 if(apiname_read.equals(api_read)){
							 url_read=xls.getCellData("Api", "Url", rNum);
							 Map map=BasePageV2.apiparams(2, xls, api_read);
							 Response resp_read=Utilities.requestUtilitypost(url_read, map);
							 api_totalItems_read=resp_read.jsonPath().get("assets.items.size()");
						 }
					 }
					 if(readSet.size()==api_totalItems_read) {
						 test.log(LogStatus.PASS, "Read tab displays "+readSet.size()+" assets in the carousal and same is returned by the API");
					 }
					 else {
						 test.log(LogStatus.FAIL, "Read tab displays "+readSet.size()+" unique assets in the carousal but API returns "+api_totalItems_read);
						 errCount++; 
					 }
//Final result verification
		if(errCount==0) {
			 homePage.smokeresults("142:Validate the Maximum count of assets in the Masthead Carousel section under all tabs.",rowno142, "PASS");
			 homePage.reportPass("142:Validate the Maximum count of assets in the Masthead Carousel section under all tabs is PASS");
		}
		else {
			 homePage.smokeresults("142:Validate the Maximum count of assets in the Masthead Carousel section under all tabs.",rowno142, "FAIL");
			 homePage.reportFail("142:Validate the Maximum count of assets in the Masthead Carousel section under all tabs is FAIL");
		}
			 
			 
		
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}

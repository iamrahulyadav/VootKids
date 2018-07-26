package com.viacom.smoketestscripts;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//Author Tanisha
public class VerifyFavouritesTrayAndIcon extends BaseTestV2{
	
	String testName = "VerifyFavouritesTrayAndIcon";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedUI(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("Verify Favourites Icon");
		test.log(LogStatus.INFO, "Starting 195: Verify the availability of favourites tray when user is not favourited any episode/book/audiobook");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls195 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno195=xls195.getRowCount("Smoke_Results")+1;
		xls195.setCellData("Smoke_Results", "Testcase Name",rowno195,"195: Verify the availability of favourites tray when user is not favourited any episode/book/audiobook");
		
		Xls_Reader xls196 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno196=xls196.getRowCount("Smoke_Results")+1;
		xls196.setCellData("Smoke_Results", "Testcase Name",rowno196,"196: Verify the availibility of favourite icon in show detail page");
		
		Xls_Reader xls197 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno197=xls197.getRowCount("Smoke_Results")+1;
		xls197.setCellData("Smoke_Results", "Testcase Name",rowno197,"197: Verify the availibility of favourite icon in audio book detail page");
		
		Xls_Reader xls198 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno198=xls198.getRowCount("Smoke_Results")+1;
		xls198.setCellData("Smoke_Results", "Testcase Name",rowno198,"198: Verify the availibility of favourite icon in episode player");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 basepagev2.takeScreenshot();
			 //System.out.println(driver.getPageSource());
		 }
		//Login module to be added
		
		//Verification of 195
		//Click on My Profile tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
			 homepagev2.mystuff_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on My Stuff tab");
			 basepagev2.takeScreenshot();
			 //Verify if API Favourites list API returns empty
				int api_totalItems_favoritesList=0;
				String url_favoritesList="";
				String api_favoritesList="Favourite List";
				String apiname_favoritesList="";
				Response resp_favoritesList=null;
				int api_responseCode;
				int rows_favoritesList=xls.getRowCount("Api");
				for(int rNum=1;rNum<=rows_favoritesList;rNum++){
					apiname_favoritesList=xls.getCellData("Api", "API Name", rNum);
					if(apiname_favoritesList.equals(api_favoritesList)){
						url_favoritesList=xls.getCellData("Api", "Url", rNum);
						Map map=BasePageV2.apiparams(2, xls, api_favoritesList);
						resp_favoritesList=Utilities.requestUtilitypost(url_favoritesList, map);
						//api_totalItems_favoritesList=resp_favoritesList.jsonPath().get("assets.items.size()");
						api_responseCode=resp_favoritesList.jsonPath().get("status.code");
						if(api_responseCode==404) {
							test.log(LogStatus.INFO, "Favourites list API has returned an empty set");
							//Verify Favourites tray availablity
							String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
							boolean presence=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
							if(presence==true) {
								 homepagev2.smokeresults("195: Verify the availability of favourites tray when user is not favourited any episode/book/audiobook",rowno195, "FAIL");
								 test.log(LogStatus.FAIL, "195: Verify the availability of favourites tray when user is not favourited any episode/book/audiobook is FAIL");
								 basepagev2.takeScreenshot();
							}
							else {
								 homepagev2.smokeresults("195: Verify the availability of favourites tray when user is not favourited any episode/book/audiobook",rowno195, "PASS");
								 homepagev2.reportPass("195: Verify the availability of favourites tray when user is not favourited any episode/book/audiobook is PASS");
							}
							
						}
						else
							test.log(LogStatus.INFO, "Favourites list API has not returned an empty set. Response Code returned is "+api_responseCode);
					 }
				}
		 }
		 else
			 test.log(LogStatus.FAIL,"Unable to click on My Stuff tab");
	
		 //Utilities.verticalSwipe(driver);

	//Verification of 196
		//Scroll to upwards 
		Utilities.verticalSwipeReverse(driver);
		//Click on Watch tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			 homepagev2.watch_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Watch tab");
			 basepagev2.takeScreenshot();
			 String firstCharLocator="//android.widget.TextView[@text='ALL CHARACTERS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView";
			 boolean charPresence=Utilities.verticalSwipeAndFind(driver,firstCharLocator);
			 if(Utilities.explicitWaitClickable(driver, watchpagev2.watchCharactersText1.get(0), 5)) {
				 watchpagev2.watchCharactersText1.get(0).click(); 
				 test.log(LogStatus.INFO, "Clicked on the first Character from the ALL CHARACTERS tray");  
				 BasePageV2.takeScreenshot();	
				 //Verify the presence of Favorite icon
				 if(Utilities.explicitWaitVisible(driver, showpagev2.favIconShowDetails, 3)) {
					 homepagev2.smokeresults("196: Verify the availibility of favourite icon in show detail page",rowno196, "FAIL");
					 test.log(LogStatus.FAIL, "196: Verify the availibility of favourite icon in show detail page is FAIL");
					 basepagev2.takeScreenshot();
				}
				else {
					 homepagev2.smokeresults("196: Verify the availability of favourites tray when user is not favourited any episode/book/audiobookVerify the availibility of favourite icon in show detail page",rowno196, "PASS");
					 homepagev2.reportPass("196: Verify the availibility of favourite icon in show detail page is PASS");	 
				 }

			 }
			 else {
				 test.log(LogStatus.FAIL,"Unable to click on the first Character from the ALL CHARACTERS tray");  
			 }
			 
		 }
		 else
			 test.log(LogStatus.FAIL, "Unable to click on Watch tab");
		 
	//Verification of 197
		 driver.launchApp();
		 //Click on Audio tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10)) {
			 homepagev2.Listen_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Listen tab");
			 basepagev2.takeScreenshot();
			 String firstBookLocator="//android.widget.TextView[@text='NEW AUDIOSTORIES']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView";
			 boolean bookPresence=Utilities.verticalSwipeAndFind(driver,firstBookLocator);
			 if(Utilities.explicitWaitClickable(driver, readpagev2.newAudioText1.get(0), 5)) {
				 readpagev2.newAudioText1.get(0).click(); 
				 test.log(LogStatus.INFO, "Clicked on the first Audio Story from the NEW AUDIOSTORIES tray");  
				 BasePageV2.takeScreenshot();	
				 //Verify the presence of Favorite icon
				 if(!Utilities.explicitWaitVisible(driver, readpagev2.favIconAudioDetails, 3)) {
					 homepagev2.smokeresults("197: Verify the availibility of favourite icon in audio book detail page",rowno197, "FAIL");
					 test.log(LogStatus.FAIL, "197: Verify the availibility of favourite icon in audio book detail page is FAIL");
					 basepagev2.takeScreenshot();
				}
				else {
					 homepagev2.smokeresults("197: Verify the availibility of favourite icon in audio book detail page",rowno197, "PASS");
					 homepagev2.reportPass("197: Verify the availibility of favourite icon in audio book detail page is PASS");	 
				 }

			 }
			 else {
				 test.log(LogStatus.FAIL,"Unable to click on the first Audio Story from the NEW AUDIOSTORIES tray");  
			 }
			 
		 }
		 else
			 test.log(LogStatus.FAIL, "Unable to click on Read tab");
		 
	//Verification of 198
		 driver.launchApp();
		 //Click on Watch tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			 homepagev2.watch_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Watch tab");
			 basepagev2.takeScreenshot();
			 //Click on the first carousal item
			 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
				 watchpagev2.firstItemInCarousal.click();
				 if(Utilities.explicitWaitVisible(driver, watchpagev2.watchFirstItemPlayer, 3)) {
					 test.log(LogStatus.INFO, "Player is displayed on tapping first item of Carousal in Watch tab");
					 //Tap on player until controls are visible
					 for(int time=0;time<=10;time++) {
						 try {
							 watchpagev2.watchFirstItemPlayer.click();
							 if(Utilities.explicitWaitClickable(driver,watchpagev2.watchFirstItemPlayerFav,3)) {
								 watchpagev2.watchFirstItemPlayerFav.click();
								 test.log(LogStatus.INFO, "Favourites icon is present in player");
								 homepagev2.smokeresults("198: Verify the availibility of favourite icon in episode player",rowno198, "PASS");
								 homepagev2.reportPass("198: Verify the availibility of favourite icon in episode player is PASS");	 	 
								 break;
							 }
							 else
								 Thread.sleep(1000);
						 }
						 catch(Exception e) {
							 Thread.sleep(1000);
							 if(time==10) {
								 test.log(LogStatus.FAIL, "Player is not clickable"); 
								 basepagev2.takeScreenshot();
								 errCount++;
							 }			 
						 }					 
					 }				 
				 }
				 else {
					 test.log(LogStatus.FAIL, "Player is not displayed on tapping first item of Carousal in Watch tab");
					 basepagev2.takeScreenshot();
					 errCount++;
				 }			 
			 }
			 else {
				 test.log(LogStatus.FAIL, "Unable to click on first item of Carousal in Watch tab");
				 basepagev2.takeScreenshot();
				 errCount++;
			 }	 			 
		 }
		 else {
			 test.log(LogStatus.FAIL, "Unable to click on Watch tab");
			 basepagev2.takeScreenshot();
			 errCount++;
		 }
		 if(errCount>0) {
			 homepagev2.smokeresults("198: Verify the availibility of favourite icon in episode player",rowno198, "FAIL");
			 test.log(LogStatus.FAIL, "198: Verify the availibility of favourite icon in episode player is FAIL");
			 basepagev2.takeScreenshot();
		 }
			 
	

	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}

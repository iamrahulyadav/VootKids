package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
public class VerifyCardMetadata extends BaseTestV2{
	
	String testName = "VerifyCardMetadata";
	@Test(dataProvider = "getData")
	public void VerifyCardTypeInMyStuff(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		int errCountMediaIconWatch=0;
		test = rep.startTest("Verify Card Metadata");
		test.log(LogStatus.INFO, "Starting test for 152: Verify Episode Card Metadata");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls152 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno152=xls152.getRowCount("Smoke_Results")+1;
		xls152.setCellData("Smoke_Results", "Testcase Name",rowno152,"152: Verify Episode Card Metadata");
		
		Xls_Reader xls257 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno257=xls257.getRowCount("Smoke_Results")+1;
		xls257.setCellData("Smoke_Results", "Testcase Name",rowno257,"257: Verify Episode Card Metadata");
		
		Xls_Reader xls153 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno153=xls153.getRowCount("Smoke_Results")+1;
		xls153.setCellData("Smoke_Results", "Testcase Name",rowno153,"153: Verify Read Card Metadata");
		
		Xls_Reader xls154 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno154=xls154.getRowCount("Smoke_Results")+1;
		xls154.setCellData("Smoke_Results", "Testcase Name",rowno154,"154: Verify Audio Card Metadata");
		
		Xls_Reader xls256 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno256=xls256.getRowCount("Smoke_Results")+1;
		xls256.setCellData("Smoke_Results", "Testcase Name",rowno256,"256: Horizontal swiping in My Stuff carousal");
		
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homePage=new HomePageV2(driver,test);
		 if(Utilities.explicitWaitVisible(driver, homePage.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 BasePageV2.takeScreenshot();
			 //System.out.println(driver.getPageSource());
		 }
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 
		//Login module to be added
		 
		//Click on Watch tab
		 
		 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			 homepagev2.watch_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Watch tab");
			 BasePageV2.takeScreenshot();
		 }
		 else
			 BasePageV2.reportFail("Unable to click on Watch tab");
		 
		 
		//Scroll and fetch data from UI
		//Fetching Card episode name and presence of media type image icon for first card
		 WebElement episodeName=null;
		 ArrayList<String> episodeNames=new ArrayList<String>();
		 try {
			 episodeName=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']");
			 episodeNames.add(episodeName.getAttribute("text"));
			 test.log(LogStatus.INFO, "Episode name fetched from UI is "+episodeName.getAttribute("text"));
			 driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
			 test.log(LogStatus.INFO, "Media icon is displayed in UI for "+episodeName.getAttribute("text"));
		 }
		 catch(Exception e) {
			 test.log(LogStatus.INFO, "Episode name/icon is not displayed for episode 1");
			 errCount++; 
		 }
		 Utilities.horizontalSwipeCarousalSlow(driver);
		 //Fetching Card episode name and presence of media type image icon for the remaining cards
		 for(int count=2;count<=5;count++) {
			 try {
				 Thread.sleep(1000);
				 episodeName=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
				 episodeNames.add(episodeName.getAttribute("text"));
				 test.log(LogStatus.INFO, "Episode name fetched from UI is "+episodeName.getAttribute("text"));
				 driver.findElementByXPath("//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
				 test.log(LogStatus.INFO, "Media icon is displayed in UI for "+episodeName.getAttribute("text"));
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "Episode name/icon is not displayed for episode "+count);
				 errCount++; 
			 }
			 Utilities.horizontalSwipeCarousalSlow(driver);
		 }		 
	
	
		//Calling getPersonalizedCarousel.json API for Watch tab to verify media type for Video icon
		//Calling config.json to get 
		 int api_totalItems_watch=0;
		 String url_watch="";
		 String url_config="";
		 String api_watch="Watch Card Count";
		 String api_config="Config";
		 String apiname_watch="";
		 String apiname_config="";
		 ArrayList<Integer> Listof_Watch=new ArrayList<Integer>();
		 ArrayList<Integer> Listof_Read=new ArrayList<Integer>();
		 ArrayList<Integer> Listof_Audio=new ArrayList<Integer>();
		 Hashtable<String,Integer> table=null;
		 int rowsMyStuff=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsMyStuff;rNum++){
			 apiname_config=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_config.equals(api_config)){
				 url_config=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_config);
				 Response resp_config=Utilities.requestUtilitypost(url_config, map);
				 resp_config.then().assertThat().statusCode(200);
				 //resp_config.prettyPrint();
				 Map<String,Integer> ott=resp_config.jsonPath().get("assets.OTT");
				 for(Map.Entry<String, Integer> m :ott.entrySet()) {
					 if(m.getKey().equals("EPISODE_TYPE"))
						Listof_Watch.add(m.getValue());	
					 if(m.getKey().equals("EBOOK_TYPE"))
						Listof_Read.add(m.getValue());	
					 if(m.getKey().equals("AUDIO_TYPE"))
						Listof_Audio.add(m.getValue());	
				 }
			 }		 
		 }
		 ArrayList<String> episodeNamesAPI=new ArrayList<String>();
		 for(int rNum=1;rNum<=rowsMyStuff;rNum++){
			 apiname_watch=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_watch.equals(api_watch)){
				 url_watch=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_watch);
				 Response resp_watch=Utilities.requestUtilitypost(url_watch, map);
				 api_totalItems_watch=resp_watch.jsonPath().get("assets.items.size()");
				 for(int item=0; item<api_totalItems_watch; item++) {
					 int mediatype=resp_watch.jsonPath().get("assets.items["+item+"].mediaType");
					 String title=resp_watch.jsonPath().get("assets.items["+item+"].title");
					 episodeNamesAPI.add(title);
					 if(!Listof_Watch.contains(mediatype)) {
						test.log(LogStatus.FAIL, "Incorrect Media type icon is displayed for episode "+title ); 
						errCountMediaIconWatch++;
					 }
					 else
						test.log(LogStatus.INFO, "Correct Media type icon is displayed for episode "+title ); 
				 }
			 }
		 }

		//Verifiying title in UI and API
		boolean found=false;
		for(int count=0;count<episodeNames.size();count++) {
			String UIname=episodeNames.get(count);
			for(int count1=0;count1<episodeNamesAPI.size();count1++) {
				if(UIname.equalsIgnoreCase(episodeNamesAPI.get(count1))) {
					test.log(LogStatus.PASS, UIname+" item is verified present in the Watch API");
					found=true;
					break;
				}
			}
			if(found==false){
				test.log(LogStatus.FAIL, episodeName.getAttribute("text")+" item is not present in the Watch API");
				errCount++;
			}
		}
		////Final result verification for 256
			if(errCountMediaIconWatch==0) {
				 homePage.smokeresults("256: Verify the type of cards in Watch tab",rowno256, "PASS");
				 homePage.reportPass("256: Verify the type of cards in Watch tab is PASS");
			}
			else {
				 homePage.smokeresults("256: Verify the type of cards in Watch tab",rowno256, "FAIL");
				 homePage.reportFail("256: Verify the type of cards in Watch tab is is FAIL");
			}
		 
		//Final result verification for 152 and 257
			if(errCount==0) {
				 homePage.smokeresults("152: Verify episode card metadata: Title and Image Icon in Watch tab",rowno152, "PASS");
				 homePage.reportPass("152: Verify episode card metadata: Title and Image Icon in Watch tab is PASS");
			}
			else {
				 homePage.smokeresults("152: Verify episode card metadata: Title and Image Icon in Watch tab",rowno152, "FAIL");
				 homePage.reportFail("152: Verify episode card metadata: Title and Image Icon in Watch tab is FAIL");
			}
			if(errCount==0) {
				 homePage.smokeresults("257: Verify episode card metadata: Title and Image Icon in Watch tab",rowno257, "PASS");
				 homePage.reportPass("257: Verify episode card metadata: Title and Image Icon in Watch tab is PASS");
			}
			else {
				 homePage.smokeresults("257: Verify episode card metadata: Title and Image Icon in Watch tab",rowno257, "FAIL");
				 homePage.reportFail("257: Verify episode card metadata: Title and Image Icon in Watch tab is FAIL");
			}
			 
		//Verification of 153
		test.log(LogStatus.INFO, "Starting test for 153: Verify Read Card Metadata");
		errCount=0;
		
		//Click on Read tab
			 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
				 homepagev2.read_tab.click();	
				 test.log(LogStatus.INFO, "Clicked on Read tab");
				 BasePageV2.takeScreenshot();
			 }
			 else
				 BasePageV2.reportFail("Unable to click on Read tab");
			
		//Scroll and fetch data from UI
		//Fetching Card book name and presence of media type image icon for first card
		WebElement bookName=null;
		WebElement authorName=null;
		try {
			bookName=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']");
			authorName=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='2']");
			test.log(LogStatus.INFO, "Book name fetched from UI is "+bookName.getAttribute("text")+" and author name is "+authorName.getAttribute("text"));
			driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
				test.log(LogStatus.INFO, "Media icon is displayed in UI for book "+bookName.getAttribute("text"));
			}
		catch(Exception e) {
				test.log(LogStatus.INFO, "Book name/author/icon is NOT displayed for book card 1");
				errCount++; 
			}
			Utilities.horizontalSwipeCarousalSlow(driver);
			//Fetching Card episode name and presence of media type image icon for the remaining cards
		for(int count=2;count<=5;count++) {
			try {
				Thread.sleep(1000);
				bookName=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
				authorName=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/subtitle']");
				test.log(LogStatus.INFO, "Book name fetched from UI is "+bookName.getAttribute("text")+" and author name is "+authorName.getAttribute("text"));
				driver.findElementByXPath("//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
				test.log(LogStatus.INFO, "Media icon is displayed in UI for book "+bookName.getAttribute("text"));
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Book name/author/icon is NOT displayed for book card "+count);
				errCount++; 
			}
			Utilities.horizontalSwipeCarousalSlow(driver);
		}		 	
		//Calling getPersonalizedCarousel.json API for Read tab to verify media type for Book icon
		int api_totalItems_read=0;
		String url_read="";
		String api_read="Read Card Count";
		String apiname_read="";
		int rowsRead=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rowsMyStuff;rNum++){
		apiname_read=xls.getCellData("Api", "API Name", rNum);
		if(apiname_read.equals(api_read)){
			url_read=xls.getCellData("Api", "Url", rNum);
			Map map=BasePageV2.apiparams(0, xls, api_read);
			Response resp_read=Utilities.requestUtilitypost(url_read, map);
			api_totalItems_read=resp_read.jsonPath().get("assets.items.size()");
			for(int item=0; item<api_totalItems_read; item++) {
				int mediatype=resp_read.jsonPath().get("assets.items["+item+"].mediaType");
				String title=resp_read.jsonPath().get("assets.items["+item+"].title");
				if(!Listof_Read.contains(mediatype)) {
					test.log(LogStatus.FAIL, "Incorrect Media type icon is displayed for book "+title ); 
					errCount++;
				}
				else
					test.log(LogStatus.INFO, "Correct Media type icon is displayed for book "+title);  
				}
			}
		}
		//Final result verification for 153
		if(errCount==0) {
			homePage.smokeresults("153: Verify read card metadata: Title and Image Icon in Read tab",rowno153, "PASS");
			homePage.reportPass("153: Verify read card metadata: Title and Image Icon in Read tab is PASS");
		}
		else {
			homePage.smokeresults("153: Verify read card metadata: Title and Image Icon in Read tab",rowno153, "FAIL");
			homePage.reportFail("153: Verify read card metadata: Title and Image Icon in Read tab is FAIL");
		}
			
		//Verification of 154
		test.log(LogStatus.INFO, "Starting test for 154: Verify Audio Card Metadata");
		errCount=0;
		
		//Click on Audio tab
			 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
				 homepagev2.Listen_tab.click();	
				 test.log(LogStatus.INFO, "Clicked on Listen tab");
				 BasePageV2.takeScreenshot();
			 }
			 else
				 BasePageV2.reportFail("Unable to click on Listen tab");
			
		//Scroll and fetch data from UI
		//Fetching Card audio name and presence of media type image icon for first card
		WebElement audioName=null;
		WebElement audioauthorName=null;
		try {
			audioName=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']");
			test.log(LogStatus.INFO, "Audio card name fetched from UI is "+audioName.getAttribute("text"));
			driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
			test.log(LogStatus.INFO, "Media icon is displayed in UI for audio "+audioName.getAttribute("text"));
			}
		catch(Exception e) {
				test.log(LogStatus.INFO, "Audio name/icon is NOT displayed for audio card 1");
				errCount++; 
			}
		Utilities.horizontalSwipeCarousalSlow(driver);
			//Fetching Card audio name and presence of media type image icon for the remaining cards
		for(int count=2;count<=5;count++) {
			try {
				Thread.sleep(1000);
				audioName=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
				test.log(LogStatus.INFO, "Audio name fetched from UI is "+audioName.getAttribute("text"));
				driver.findElementByXPath("//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
				test.log(LogStatus.INFO, "Media icon is displayed in UI for audio "+audioName.getAttribute("text"));
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Audio name/icon is NOT displayed for audio card "+count);
				errCount++; 
			}
			Utilities.horizontalSwipeCarousalSlow(driver);
		}		 	
		//Calling getPersonalizedCarousel.json API for Read tab to verify media type for Book icon
		int api_totalItems_audio=0;
		String url_audio="";
		String api_audio="Audio Card Count";
		String apiname_audio="";
		int rowsAudio=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rowsAudio;rNum++){
		apiname_audio=xls.getCellData("Api", "API Name", rNum);
		if(apiname_audio.equals(api_audio)){
			url_audio=xls.getCellData("Api", "Url", rNum);
			Map map=BasePageV2.apiparams(0, xls, api_audio);
			Response resp_audio=Utilities.requestUtilitypost(url_audio, map);
			api_totalItems_audio=resp_audio.jsonPath().get("assets.items.size()");
			for(int item=0; item<api_totalItems_audio; item++) {
				int mediatype=resp_audio.jsonPath().get("assets.items["+item+"].mediaType");
				String title=resp_audio.jsonPath().get("assets.items["+item+"].title");
				if(!Listof_Audio.contains(mediatype)) {
					test.log(LogStatus.FAIL, "Incorrect Media type icon is displayed for audio "+title ); 
					errCount++;
				}
				else
					test.log(LogStatus.INFO, "Correct Media type icon is displayed for audio "+title);  
				}
			}
		}
		//Final result verification for 154
		if(errCount==0) {
			homePage.smokeresults("154: Verify audio card metadata: Title and Image Icon in Audio tab",rowno154, "PASS");
			homePage.reportPass("154: Verify audio card metadata: Title and Image Icon in Audio tab is PASS");
		}
		else {
			homePage.smokeresults("154: Verify audio card metadata: Title and Image Icon in Audio tab",rowno154, "FAIL");
			homePage.reportFail("154: Verify audio card metadata: Title and Image Icon in audio tab is FAIL");
		}			
			
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}

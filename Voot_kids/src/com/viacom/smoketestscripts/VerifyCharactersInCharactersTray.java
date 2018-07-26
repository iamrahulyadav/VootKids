package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Hashtable;
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
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.MobileElement;

//289.Validate the functionality by tapping on tray name-> Yes(NA)
//290.Verify the characters present in characters tray-> Yes

public class VerifyCharactersInCharactersTray extends BaseTestV2{
	
	String testName = "VerifyCharactersInCharactersTray";
	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("Verify ALL CHARACTERS tray in Watch tab");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		Xls_Reader xls289 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno289=xls289.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno289,"289: All Characters tray click functionality");
		
		Xls_Reader xls290 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno290=xls290.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno290,"290: Character show card");
		
		Xls_Reader xls291 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno291=xls291.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno291,"291: Character show card Metadata");
		
		Xls_Reader xls293 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno293=xls293.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno293,"293: Verify the availibility of 'See All' Button in All Characters Tray if there are > 8 characters");
		
		Xls_Reader xls294 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno294=xls294.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno294,"294: Verify 'See All' button if there are 8 characters");
		
		Xls_Reader xls295 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno295=xls295.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno295,"295: Verify 'See All' button if there are < 8 characters");
		 
		//Launch Voot kids app
		 launchApp();
		 test.log(LogStatus.INFO, "Application launched successfully");
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 basepagev2.takeScreenshot();
		 //System.out.println(driver.getPageSource());
		 
		//Tap on Watch
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			 homepagev2.watch_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Watch tab");
			 BasePageV2.takeScreenshot();
		 }
		 else
			 BasePageV2.reportFail("Unable to click on Watch tab");
		 
		 //Scroll down till all characters tray
		 String text="ALL CHARACTERS";	
		 try {
			 driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
			 test.log(LogStatus.INFO, "Scrolled to ALL CHARACTERS");
			 BasePageV2.takeScreenshot();
		 }
		 catch(Exception e) {
			 homepagev2.reportFail("Unable to scroll to ALL CHARACTERS section");
			 
		 }
		 //Verify if ALL CHARACTERS section is displayed
		 WatchPageV2 watchPage=new WatchPageV2(driver,test);
		 if(Utilities.explicitWaitVisible(driver, watchPage.allCharacterText, 10)) 	
			 test.log(LogStatus.INFO, "ALL CHARACTERS tray title is displayed");
		 else
			 homepagev2.reportFail("ALL CHARACTERS tray title is not displayed");
		 
		 
		 
		 //Verify the API response for All Characters
		 //Calling kidsCharacters.json API
		 int totalitemsofapi=0;
		 int size=0;
		 String url_allCharacters="";
		 String apiname_allCharacters="";
		 ArrayList<String> characterNamesFromAPI=new ArrayList<String>();
		 ArrayList<String> characterNamesFromUI=new ArrayList<String>();
		 int characterTextsCount=0;
		 
		 int rows=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows;rNum++){
			 String apiname=xls.getCellData("Api", "API Name", rNum);
			 if(apiname.equals("All Characters")){
				 url_allCharacters=xls.getCellData("Api", "Url", rNum);
				 apiname_allCharacters="All Characters";
				 Map map=BasePageV2.apiparams(2, xls, apiname_allCharacters);
				 Response resp_allCharacters=Utilities.requestUtilitypost(url_allCharacters, map);
				 //resp_allCharacters.prettyPrint();
				 totalitemsofapi=resp_allCharacters.jsonPath().get("assets.items.size()");
				 //Get the items from the API
				 for(int count1=0;count1<totalitemsofapi;count1++) {
					   String characterName=resp_allCharacters.jsonPath().get("assets.items["+count1+"].title"); 
					   characterNamesFromAPI.add(characterName);
				 }
			 }
		 }
		   if(totalitemsofapi<1) {
			   test.log(LogStatus.INFO, "API returns empty list for All Characters");
			   size=watchPage.watchCharactersText1.size();
			   if (size<1) {
				 test.log(LogStatus.INFO, "App displays no characters."); 
				//Front end NOTHING HERE need to implement
			   }
			   else 
				   homepagev2.reportFail("API and front end mismatch in All Characters list");
			   //See All button should not be available
			   if(watchPage.seeAll.isDisplayed()) {
				   homepagev2.reportFail("See All is displayed even though there are no characters.");
			   }
		   }
		   else {
			   characterTextsCount=watchPage.watchCharactersText1.size(); 
			   if(characterTextsCount!=0) {
				   homepagev2.smokeresults("290:Character show card",rowno290, "PASS");
				   homepagev2.reportPass("Different kids shows character should be displayed in this tray is Pass");
				   homepagev2.smokeresults("291:Character show card Metadata",rowno291, "PASS");
				   homepagev2.reportPass("Show card should include the following name of the kid character is Pass");
			   }
			   else {
				   homepagev2.smokeresults("290:Character show card",rowno290, "FAIL");
				   homepagev2.reportFail("Different kids shows character should be displayed in this tray is Fail");
				   homepagev2.smokeresults("291:Character show card Metadata",rowno291, "FAIL");
				   homepagev2.reportFail("Show card should include the following name of the kid character is Fail");
			   }
		   }
		   if(totalitemsofapi==8) {
			   ////Check if SeeAll button is there
			   if(Utilities.explicitWaitVisible(driver, watchPage.seeAll, 5)) { 		 
				 homepagev2.smokeresults("294: Verify 'See All' button if there are 8 characters",rowno294, "FAIL");
				 homepagev2.reportFail("Verify 'See All' button if there are 8 characters is Fail");
			   }
			   else {
				 homepagev2.smokeresults("294: Verify 'See All' button if there are 8 characters",rowno294, "PASS");
				 homepagev2.reportPass("Verify 'See All' button if there are 8 characters is Fail");
			   }
		   }
		   
		   
		   
		   
		   if(totalitemsofapi>=1 && totalitemsofapi<=8){ 
			   if(Utilities.explicitWaitVisible(driver, watchPage.allCharacters, 5)) { 		 
				 watchPage.allCharacters.click();
				 //Verify next page launch
				 homepagev2.reportFail("All Characters tray is clickable even though Characters are below 8");
			   }
			   else {
				   test.log(LogStatus.INFO, "All Characters tray is not clickable when Characters are below 8 which is expected behavior");
			   }

			   //Check if SeeAll button is there
			   if(Utilities.explicitWaitVisible(driver, watchPage.seeAll, 5)) { 		 
				 homepagev2.smokeresults("295: Verify 'See All' button if there are < 8 characters",rowno295, "FAIL");
				 homepagev2.reportFail("Verify 'See All' button if there are < 8 characters is Fail");
			   }
			   else {
				 homepagev2.smokeresults("295: Verify 'See All' button if there are < 8 characters",rowno295, "PASS");
				 homepagev2.reportPass("Verify 'See All' button if there are < 8 characters is Pass");
			   }		   
			   characterTextsCount=watchPage.watchCharactersText1.size();
			   for (int count=0;count<characterTextsCount;count++) {
				   String characterName=watchPage.watchCharactersText1.get(count).getText();	  
				   characterNamesFromUI.add(characterName);
			   }
			   if(characterNamesFromUI.size()!=characterNamesFromAPI.size()) {
				   homepagev2.reportFail("Count of characters mismatch in API and UI");  
			   }
			   else {
				   for(int count=0;count<characterNamesFromUI.size();count++) {
					   if(!characterNamesFromAPI.contains(characterNamesFromUI.get(count)))
						   homepagev2.reportFail("API does not contain "+characterNamesFromUI.get(count)); 
				   }
			   }	   
		   }
		   
		   else{
			 //Verify 289 Click functionality on tray name
			   if(Utilities.explicitWaitVisible(driver, watchPage.allCharacters, 5)) { 		 
				 watchPage.allCharacters.click();
				 //Verify next page launch
				 homepagev2.smokeresults("289: On tapping the tray name should lead to all character 'See All' page",rowno295, "PASS");
				 homepagev2.reportFail("289: On tapping the tray name should lead to all character 'See All' page is PASS");
			   }
			   else {
				 homepagev2.smokeresults("289: On tapping the tray name should lead to all character 'See All' page",rowno295, "FAIL");
				 homepagev2.reportFail("289: On tapping the tray name should lead to all character 'See All' page is Fail");
			   }
			   
			//293.Verify the availability of 'See All' Button in All Characters Tray if there are > 8 characters
			   if(Utilities.explicitWaitVisible(driver, watchPage.seeAll, 5)) { 		 
				 homepagev2.smokeresults("293: 'See All' button should be displayed if the tray has more than 8 characters.",rowno293, "PASS");
				 homepagev2.reportFail("293: 'See All' button should be displayed if the tray has more than 8 characters is PASS");
			   }
			   else {
				 homepagev2.smokeresults("293: 'See All' button should be displayed if the tray has more than 8 characters.",rowno293, "FAIL");
				 homepagev2.reportFail("293: 'See All' button should be displayed if the tray has more than 8 characters is FAIL");
			   }
			   
			   characterTextsCount=watchPage.watchCharactersText1.size();
			   for (int count=0;count<characterTextsCount;count++) {
				   String characerName=watchPage.watchCharactersText1.get(count).getText();	  
				   characterNamesFromUI.add(characerName);
			   }
			   if(characterNamesFromUI.size()!=characterNamesFromAPI.size()) {
				   homepagev2.reportFail("Count of characters mismatch in API and UI");  
			   }
			   else {
				   for(int count=0;count<characterNamesFromUI.size();count++) {
					   if(!characterNamesFromAPI.contains(characterNamesFromUI.get(count)))
						   homepagev2.reportFail("API does not contain "+characterNamesFromUI.get(count)); 
				   }
			   }
		   }
		   
		   
		   
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
	
	
}

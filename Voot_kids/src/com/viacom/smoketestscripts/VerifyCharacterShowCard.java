package com.viacom.smoketestscripts;

import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
//292.Character show card navigation

public class VerifyCharacterShowCard extends BaseTestV2{
	
	String testName = "VerifyCharacterShowCard";
	@Test(dataProvider = "getData")
	public void VerifyCharacterShowCard(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("Character show card navigation");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno,"292: Character show card navigation");
		 
		//Launch Voot kids app
		 launchApp();
		 test.log(LogStatus.INFO, "Application launched successfully");
		 //BasePageV2.takeScreenshot();
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
			 //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
			 JavascriptExecutor js = (JavascriptExecutor) driver;
	         HashMap scrollObject = new HashMap<>();
	         scrollObject.put("predicateString", "value == '"+"ALL CHARACTERS"+"'");
	         js.executeScript("mobile: scroll", scrollObject);

			 
			 
			 
			 test.log(LogStatus.INFO, "Scrolled to ALL CHARACTERS");
			 BasePageV2.takeScreenshot();
		 }
		 catch(Exception e) {
			 homepagev2.reportFail("Unable to scroll to ALL CHARACTERS section");
			 
		 }
/*		 
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
		 String charNameInList="";
		 
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
			   //size=watchPage.allCharacters.size();
			   if (size<1) {
				 test.log(LogStatus.INFO, "App displays no characters."); 
				//Front end NOTHING HERE need to implement
			   }
			   else 
				   homepagev2.reportFail("API and front end mismatch in All Characters list");   
		   }
		   
		   else{	
			   try {
				   charNameInList=watchPage.watchCharactersText1.get(0).getText();
			   }
			   catch(Exception e) {
				   test.log(LogStatus.FAIL, "Unable to fetch name of the first Character from the ALL CHARACTERS tray");
			   }
			   if(Utilities.explicitWaitClickable(driver, watchPage.watchCharactersText1.get(0), 5)) {
				   watchPage.watchCharactersText1.get(0).click(); 
				   test.log(LogStatus.INFO, "Clicked on the first Character from the ALL CHARACTERS tray");  
				   BasePageV2.takeScreenshot();
				   
			   }
			   else {
				   homepagev2.reportFail("Unable to click on the first Character from the ALL CHARACTERS tray");  
			   }
			   
			   String charNameInDetails=watchPage.characterDetailsTitle.getText();
			   if(charNameInList.equalsIgnoreCase(charNameInDetails)) {
				   homepagev2.smokeresults("Character show card navigation",rowno, "PASS");
				   homepagev2.reportPass("Character show card navigation is successful");
			   }
			   else {
				   homepagev2.smokeresults("Character show card navigation",rowno, "FAIL");
				   homepagev2.reportFail("Character show card navigation has failed");   
			   }
		      
		   }*/
		   	   
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
	
	
	
}

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

public class VerifyDisplayCardOrderInCarousal extends BaseTestV2{
	
	String testName = "VerifyDisplayCardOrderInCarousal";
	@Test(dataProvider = "getData")
	public void VerifyDisplayCardOrderInCarousal(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("Verify the Display Card Order In Carousal");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls149 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno149=xls149.getRowCount("Smoke_Results")+1;
		xls149.setCellData("Smoke_Results", "Testcase Name",rowno149,"149: Verify the Display Card Order In Carousal");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homePage=new HomePageV2(driver,test);
		 if(Utilities.explicitWaitVisible(driver, homePage.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 BasePageV2.takeScreenshot();
			 //System.out.println(driver.getPageSource());
		 }
		 
		 //Login module to be added
		 	 
		HashSet<String> carousalNameSet=new HashSet<String>();
		ArrayList<String> carousalNameList=new ArrayList<String>();
		while(homePage.watch_tab.getAttribute("selected").equals("false")) {
			List<WebElement> carousalList=driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@index='0']/android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
			for(int count=0;count<carousalList.size();count++) {
				carousalNameSet.add(carousalList.get(count).getText());
				carousalNameList.add(carousalList.get(count).getText());
			}
			Utilities.horizontalSwipe(driver);		
		}
		if(carousalNameSet.size()!=5) {
			homePage.smokeresults("149:Verify the order of displaying cards in Carousel section of My Stuff since carousal does not contain 5 cards",rowno149, "FAIL");
			homePage.reportFail("Since carousal does not contain 5 cards or contains duplicate cards failing the case");
		}

		
		//Verify API results
		//Calling getPersonalizedCarousel.json API
		 int totalitemsofapi=0;
		 int size=0;
		 String url_carousalCards="";
		 String apiname_carousalCards="";
		 ArrayList<String> carousalCardsFromAPI=new ArrayList<String>();
		 String carousalCardsInList="";
		 
		 int rows=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows;rNum++){
			 String apiname=xls.getCellData("Api", "API Name", rNum);
			 if(apiname.equals("Carousal Card Order")){
				 url_carousalCards=xls.getCellData("Api", "Url", rNum);
				 apiname_carousalCards="Carousal Card Order";
				 Map map=BasePageV2.apiparams(2, xls, apiname_carousalCards);
				 Response resp_carusalCards=Utilities.requestUtilitypost(url_carousalCards, map);
				 //resp_allCharacters.prettyPrint();
				 totalitemsofapi=resp_carusalCards.jsonPath().get("assets.items.size()");
				 //Get the items from the API
				 for(int count1=0;count1<totalitemsofapi;count1++) {
					   String carousalCardName=resp_carusalCards.jsonPath().get("assets.items["+count1+"].title"); 
					   carousalCardsFromAPI.add(carousalCardName);
				 }
			 }
		 }
		 //Verification of test case 149
		 Iterator<String> it=carousalNameSet.iterator();
		 for(int count=(totalitemsofapi-1);count>=0;count--) {
			 String fromUI=it.next();
			 if(carousalCardsFromAPI.get(count).equalsIgnoreCase(fromUI)) {
				 test.log(LogStatus.INFO, "Verified API and UI items in Carousal for "+carousalCardsFromAPI.get(count));
				 if(count==0) {
					 homePage.smokeresults("149:Verify the order of displaying cards in Carousel section of My Stuff",rowno149, "PASS");
					 homePage.reportPass("149:Verify the order of displaying cards in Carousel section of My Stuff is PASS");
				 }
			 }
			 else {
				 test.log(LogStatus.INFO, "Order of API and UI items in Carousal are incorrect for "+carousalCardsFromAPI.get(count));
				 homePage.smokeresults("149:Verify the order of displaying cards in Carousel section of My Stuff",rowno149, "FAIL");
				 homePage.reportFail("149:Verify the order of displaying cards in Carousel section of My Stuff is FAIL");
			 }
				 
		 }	
		
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}

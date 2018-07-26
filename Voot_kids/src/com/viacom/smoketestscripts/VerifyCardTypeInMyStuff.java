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
public class VerifyCardTypeInMyStuff extends BaseTestV2{
	
	String testName = "VerifyCardTypeInMyStuff";
	@Test(dataProvider = "getData")
	public void VerifyCardTypeInMyStuff(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("Verify Card Type In My Stuff");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls151 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno151=xls151.getRowCount("Smoke_Results")+1;
		xls151.setCellData("Smoke_Results", "Testcase Name",rowno151,"151: Verify Card Type In My Stuff.");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homePage=new HomePageV2(driver,test);
		 if(Utilities.explicitWaitVisible(driver, homePage.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 BasePageV2.takeScreenshot();
			 //System.out.println(driver.getPageSource());
		 }
		 
		//Login module to be added
		 
		//Fetching Card type image icon for first card
		 WebElement cardname=null;
		 try {
			 cardname=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
			 driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
			 test.log(LogStatus.INFO, "Card Type icon is displayed for Card "+cardname.getAttribute("text")+" in My Stuff");
		 }
		 catch(Exception e) {
			 test.log(LogStatus.INFO, "Card Type icon is NOT displayed for Card "+cardname.getAttribute("text")+" in My Stuff");
			 errCount++; 
		 }
		 //Utilities.horizontalSwipe(driver);
		 //Fetching Card type image icon for the remaining cards
		 for(int count=2;count<=5;count++) {
			 try {
				 cardname=driver.findElementByXPath("//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
				 driver.findElementByXPath("//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.ImageView[@resource-id='com.tv.vootkids:id/book_icon_img']");
				 test.log(LogStatus.INFO, "Card Type icon is displayed for Card "+cardname.getAttribute("text")+" in My Stuff");
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "Card Type icon is NOT displayed for Card "+cardname.getAttribute("text")+" in My Stuff");
				 errCount++; 
			 }
			 Utilities.horizontalSwipe(driver);
		 }
		 
		//Verify API results
		//Calling getPersonalizedCarousel.json API for My Stuff tab
		//Calling config.json to get 
		 int api_totalItems_mytuff=0;
		 String url_myStuff="";
		 String url_config="";
		 String api_myStuff="Carousal Card Count";
		 String api_config="Config";
		 String apiname_myStuff="";
		 String apiname_config="";
		 ArrayList<Integer> Listof_OTT=null;
		 Hashtable<String,Integer> table=null;
		 int rowsMyStuff=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsMyStuff;rNum++){
			 apiname_config=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_config.equals(api_config)){
				 url_config=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_config);
				 Response resp_config=Utilities.requestUtilitypost(url_config, map);
				 Listof_OTT = new ArrayList<Integer>();
				 resp_config.then().assertThat().statusCode(200);
				 //resp_config.prettyPrint();
				 Map<String,Integer> ott=resp_config.jsonPath().get("assets.OTT");
				 for(Map.Entry<String, Integer> m :ott.entrySet()) {
					 if(m.getKey().equals("AUDIO_TYPE")||m.getKey().equals("EBOOK_TYPE")||m.getKey().equals("EPISODE_TYPE"))
						Listof_OTT.add(m.getValue());	
				 }
			 }		 
		 }
	 
		 for(int rNum=1;rNum<=rowsMyStuff;rNum++){
			 apiname_myStuff=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_myStuff.equals(api_myStuff)){
				 url_myStuff=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_myStuff);
				 Response resp_mystuff=Utilities.requestUtilitypost(url_myStuff, map);
				 api_totalItems_mytuff=resp_mystuff.jsonPath().get("assets.items.size()");
				 for(int item=0; item<5; item++) {
					 int mediatype=resp_mystuff.jsonPath().get("assets.items["+item+"].mediaType");
					 String title=resp_mystuff.jsonPath().get("assets.items["+item+"].title");
					 if(!Listof_OTT.contains(mediatype)) {
						test.log(LogStatus.FAIL, "The mediatype of "+title+ " does not belong to either Audio/eBook/Episode" ); 
						errCount++;
					 }
					 else
						test.log(LogStatus.INFO, "The mediatype of "+title+ " belongs to either Audio/eBook/Episode" );  
				 }
			 }
		 }
		 
		//Final result verification
			if(errCount==0) {
				 homePage.smokeresults("151: My Stuff should display Video card/Book card/Audio card",rowno151, "PASS");
				 homePage.reportPass("151: My Stuff should display Video card/Book card/Audio card is PASS");
			}
			else {
				 homePage.smokeresults("151: My Stuff should display Video card/Book card/Audio card",rowno151, "FAIL");
				 homePage.reportFail("151: My Stuff should display Video card/Book card/Audio card is FAIL");
			}
			 
		
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}

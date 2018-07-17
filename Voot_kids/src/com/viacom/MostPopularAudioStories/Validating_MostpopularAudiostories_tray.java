package com.viacom.MostPopularAudioStories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
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

public class Validating_MostpopularAudiostories_tray extends BaseTestV2 {
	String testName = "Validating_MostpopularAudiostories_tray";
	public static int rowno=0;
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{
		if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Most popular Audio stories");
		test.log(LogStatus.INFO, "Starting the test to Validate Most popular Audio stories tray: "+VootConstants.DEVICE_NAME);
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
		/*if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10))
		{
    		homepagev2.Listen_tab.click();
    		 String text="MOST POPULAR AUDIOS";
	    	   driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	    	   test.log(LogStatus.INFO, "Navigation to (Listen) tab is passed");
		       BasePageV2.takeScreenshot();  
		}
		else
		BasePageV2.reportFail("Unable to Navigate to Listen tab");
		
	  int list = driver.findElementsByXPath("//android.widget.TextView[@text='MOST POPULAR AUDIOS']/ancestor::android.view.ViewGroup[@index='1']//android.view.ViewGroup").size();		
	  Utilities.scrolltoElementusingtext(driver, "//android.widget.Button[@text='SEE ALL']/../..//android.widget.TextView[@text='MOST POPULAR AUDIOS']");
	  if(list>=6)
	  {
		  //need to implement using api
		  Utilities.verticalSwipe(driver);
		  int list1 = driver.findElementsByXPath("//android.widget.TextView[@text='MOST POPULAR AUDIOS']/ancestor::android.view.ViewGroup[@index='1']//android.view.ViewGroup").size();		
		  if(list1==8)
		  {
		  if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
		  homepagev2.seeAll.click();
	     BasePageV2.takeScreenshot();
		  }
		  else
		  test.log(LogStatus.INFO, "Card kist is not available");
		  BasePageV2.takeScreenshot();
	  }
	  else
	  {
		  test.log(LogStatus.INFO, "See all is not visible as card count is 8");
		  BasePageV2.takeScreenshot();
	  }*/
	  
	  //API CHECK
	  BasicConfigurator.configure();
		RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		Response resp1=RestAssured.
		given().
		relaxedHTTPSValidation().
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		headers("peppaPig", "M2ZseFBKUTE5YnFaY0xoMQ==qNG9f8sLNOc1mff/2lng2H3+/yXCgAxwfxXJ38cN3PtE33CD/tF7vSrL+1Es6qiEY1f8S0z1iPfvkLKRgvMhoEImWpfBao1noFXTpMMQOvJ/Tp/+ocKsB4A1E25vSzURHtv1ecpG+HX5KgKQnCUnww==").
		headers("platform", "android").
		when().
		get("http://apiuat.vootkids.com/app/ui/v1/tabs/audio.json");
		//resp1.prettyPrint();
		resp1.then().assertThat().statusCode(200);
		//String x1=resp1.jsonPath().get("assets[0].segmentedTabs[0].tabLabel");
		String title="Most Popular Audios";
		int x= resp1.jsonPath().get("assets.size()");
		System.out.println(x);
	    for(int i=0;i<x;i++)
	    {
	    	String y=resp1.jsonPath().get("assets["+i+"].title");
	    	System.out.println(y);
	    	if(y.equals(title))
	    	{
	    		int x1= resp1.jsonPath().get("assets["+i+"].assets[0].items.size()");
	    		System.out.println(x1);
	    		for(int m=0;m<x1;m++)
	    		{
	    			int type=resp1.jsonPath().get("assets["+i+"].assets[0].items["+m+"].mediaType");
	    			String title1=resp1.jsonPath().get("assets["+i+"].assets[0].items["+m+"].title");
	    			System.out.println("type : "+type);
	    			System.out.println("title : "+title1);

	    		}

	    	}
	    	
	    }
	  //End of API check
	  
		  
	}		
			@DataProvider
			public Object[][] getData(){
			return DataUtil.getData(testName,xls);							
				}
		}
			

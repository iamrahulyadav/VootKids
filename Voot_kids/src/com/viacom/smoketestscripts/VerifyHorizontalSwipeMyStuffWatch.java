package com.viacom.smoketestscripts;

import java.time.Duration;
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
public class VerifyHorizontalSwipeMyStuffWatch extends BaseTestV2{
	
	String testName = "VerifyHorizontalSwipe";
	@Test(dataProvider = "getData")
	public void VerifyHorizontalSwipe(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("Verify 1. horizontal swipe for My Stuff and Watch. 2. Behavior after resuming from background for My Stuff, Watch and Read Carousal cards");
		test.log(LogStatus.INFO, "Starting test for 155: Verify horizontal swipe for right and left in My Stuff");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls155 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno155=xls155.getRowCount("Smoke_Results")+1;
		xls155.setCellData("Smoke_Results", "Testcase Name",rowno155,"155: Horizontal swiping in My Stuff carousal");
		
		Xls_Reader xls157 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno157=xls157.getRowCount("Smoke_Results")+1;
		xls157.setCellData("Smoke_Results", "Testcase Name",rowno157,"157: Verify user remains in same card after minimize and resuming the app in My Stuff Carousal");
		
		Xls_Reader xls258 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno258=xls258.getRowCount("Smoke_Results")+1;
		xls258.setCellData("Smoke_Results", "Testcase Name",rowno258,"258: Horizontal swiping in Watch carousal");
		
		Xls_Reader xls259 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno259=xls259.getRowCount("Smoke_Results")+1;
		xls259.setCellData("Smoke_Results", "Testcase Name",rowno259,"259: Verify user remains in same card after minimize and resuming the app in Watch Carousal");
		
		Xls_Reader xls260 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno260=xls260.getRowCount("Smoke_Results")+1;
		xls260.setCellData("Smoke_Results", "Testcase Name",rowno260,"260: Verify user remains in same card after minimize and resuming the app in Read Carousal");
		
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
		
		//Verification of 155
		test.log(LogStatus.INFO, "Starting test for 155: Verify right and left horizontal swipe for carousal in My Stuff");
		//Click on My Profile tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
			 homepagev2.mystuff_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on My Stuff tab");
			 BasePageV2.takeScreenshot();
		 }
		 else
			 BasePageV2.reportFail("Unable to click on My Stuff tab");
		 
		 WebElement firstCardBeforeScrollMyStuff;
		 WebElement firstCardAfterScrollMyStuff;
		 //Get the first card name before swipe
		 firstCardBeforeScrollMyStuff=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']");
		 test.log(LogStatus.INFO, "First Card before scroll is "+firstCardBeforeScrollMyStuff.getAttribute("text"));
		 //Scroll 5 times and verify if Watch tab is displayed or not 
		 for(int count=1;count<=5;count++) {
			 Thread.sleep(1000);
			 Utilities.horizontalSwipeCarousalSlow(driver);
		 }
		 Thread.sleep(1000);
		 Utilities.horizontalSwipeCarousalSlow2(driver);
		 if(homepagev2.watch_tab.getAttribute("selected").equals("true")) {
			 test.log(LogStatus.INFO, "Horizontal swiping to the right is successful in My Stuff carousal");
			 BasePageV2.takeScreenshot();
			 //Verify left scroll
			 Utilities.horizontalSwipeulta(driver);
			 if(homepagev2.mystuff_tab.getAttribute("selected").equals("true")) {
				 test.log(LogStatus.INFO, "One left scroll should highlight My Stuff tab is PASS"); 
				 BasePageV2.takeScreenshot();
				 for(int count=1;count<=4;count++) {
					 Utilities.horizontalSwipeulta(driver);
				 }
				 try {
					 firstCardAfterScrollMyStuff=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']");
					 test.log(LogStatus.INFO, "First Card after scroll is "+firstCardAfterScrollMyStuff.getAttribute("text"));
					 if(firstCardBeforeScrollMyStuff.getAttribute("text").equals(firstCardAfterScrollMyStuff.getAttribute("text"))) {
						 homePage.smokeresults("155: Horizontal swiping in My Stuff carousal",rowno155, "PASS");
						 homePage.reportPass("155: Horizontal swiping in My Stuff carousal is PASS");
					 }
					 else {
						 homePage.smokeresults("155: Horizontal swiping to the right in My Stuff carousal",rowno155, "FAIL");
						 homePage.reportFail("155: Horizontal swiping to the right in My Stuff carousal is FAIL");
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Unable to locate the first card after swiping rights");
					 homePage.smokeresults("155: Horizontal swiping to the left in My Stuff carousal",rowno155, "FAIL");
					 homePage.reportFail("155: Horizontal swiping to the left in My Stuff carousal is FAIL");
				 }
			 }
			 else {
				 homePage.smokeresults("155: Horizontal swiping to the left in My Stuff carousal",rowno155, "FAIL");
				 homePage.reportFail("155: Horizontal swiping to the left in My Stuff carousal is FAIL");
			 }			 
		 }
		 else {
			 homePage.smokeresults("155: Horizontal swiping to the right in My Stuff carousal is FAIL",rowno155, "FAIL");
			 homePage.reportFail("155: Horizontal swiping to the right in My Stuff carousal is FAIL");
		 }
		 
		//Verification of 157
		 test.log(LogStatus.INFO, "Starting test for 157: Verify user remains in same card after minimize and resuming the app in My Stuff Carousal");
		
		//Click on My Profile tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
			 homepagev2.mystuff_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on My Stuff tab");
			 BasePageV2.takeScreenshot();
		 }
		 else
			 BasePageV2.reportFail("Unable to click on My Stuff tab");

		 //Put the app background for first card
		 WebElement nameBeforeBck=null;
		 WebElement nameAfterBck=null;
		 try {
			 nameBeforeBck=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']"); 
			 test.log(LogStatus.INFO, "Card "+nameBeforeBck.getAttribute("text")+" is displayed in foreground");
		 }
		 catch(Exception e) {
			 test.log(LogStatus.INFO, "Unable to fetch the title of card 1");
		 }
		 
		 driver.runAppInBackground(Duration.ofSeconds(5));
		 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
		 driver.currentActivity();
		 //Fetch card name again
		 try {
			 nameAfterBck=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']"); 
			 test.log(LogStatus.INFO, "Card "+nameAfterBck.getAttribute("text")+" is displayed after app is resumed from background");
			 BasePageV2.takeScreenshot();
			 if(nameBeforeBck.getAttribute("text").equals(nameAfterBck.getAttribute("text")))
				 test.log(LogStatus.PASS, "User has remained in card 1 after resume from background");
			 else {
				 test.log(LogStatus.FAIL, "User has NOT remained in card 1 after resume from background");
				 errCount++;
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Unable to fetch the title of card 1");
		 }
		 //Verification for the rest of the cards
		 Utilities.horizontalSwipeCarousalSlow(driver);
		 for(int count=2;count<=5;count++) {
			 try {
				 Thread.sleep(1000);
				 nameBeforeBck=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
				 test.log(LogStatus.INFO, "Card "+nameBeforeBck.getAttribute("text")+" is displayed in foreground");
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "Unable to fetch the title of card "+count);
			 }
			 
			 driver.runAppInBackground(Duration.ofSeconds(5));
			 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
			 driver.currentActivity();
			 //Fetch card name again
			 try {
				 nameAfterBck=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
				 test.log(LogStatus.INFO, "Card "+nameAfterBck.getAttribute("text")+" is displayed after app is resumed from background");
				 BasePageV2.takeScreenshot();
				 if(nameBeforeBck.getAttribute("text").equals(nameAfterBck.getAttribute("text")))
					 test.log(LogStatus.PASS, "User has remained in card "+count+" after resume from background");
				 else {
					 test.log(LogStatus.FAIL, "User has NOT remained in card "+count+" after resume from background");
					 errCount++;
				 }	 
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Unable to fetch the title of card "+count);
			 }
			 Utilities.horizontalSwipeCarousalSlow(driver);
		 }	
	//Final result verification for 157
	if(errCount==0) {
		homePage.smokeresults("157: Verify user remains in same card after minimize and resuming the app",rowno157, "PASS");
		homePage.reportPass("157: Verify user remains in same card after minimize and resuming the app is PASS");
	}
	else {
		homePage.smokeresults("157: Verify user remains in same card after minimize and resuming the app",rowno157, "FAIL");
		homePage.reportFail("157: Verify user remains in same card after minimize and resuming the app is FAIL");
	}
	
	//258: Horizontal swiping in Watch carousal
	//Relaunch app
	test.log(LogStatus.INFO, "Starting test for 258: Verify right and left horizontal swipe for carousal in Watch");
	driver.launchApp();
	//Click on Watch tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
		 homepagev2.watch_tab.click();	
		 test.log(LogStatus.INFO, "Clicked on Watch tab");
		 BasePageV2.takeScreenshot();
	 }
	 else
		 BasePageV2.reportFail("Unable to click on My Stuff tab");
	 
	 String firstCardBeforeScrollWatch;
	 String firstCardAfterScrollWatch;
	 //Get the first card name before swipe
	 Thread.sleep(1000);
	 firstCardBeforeScrollWatch=driver.findElementByXPath("//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']").getAttribute("text");
	 test.log(LogStatus.INFO, "First Card before scroll is "+firstCardBeforeScrollWatch);
	 //Scroll 5 times and verify if Watch tab is displayed or not 
	 for(int count=1;count<=5;count++) {
		 Thread.sleep(1000);
		 Utilities.horizontalSwipeCarousalSlow(driver);
	 }
	 Thread.sleep(1000);
	 Utilities.horizontalSwipeCarousalSlow2(driver);
	 if(homepagev2.Listen_tab.getAttribute("selected").equals("true")) {
		 test.log(LogStatus.INFO, "Horizontal swiping to the right is successful in Watch carousal");
		 BasePageV2.takeScreenshot();
		 //Verify left scroll
		 Utilities.horizontalSwipeulta(driver);
		 if(homepagev2.watch_tab.getAttribute("selected").equals("true")) {
			 test.log(LogStatus.INFO, "One left scroll should highlight Watch tab is PASS"); 
			 BasePageV2.takeScreenshot();
			 for(int count=1;count<=5;count++) {
				 Utilities.horizontalSwipeReverseSlow(driver);
			 }
			 try {
				 firstCardAfterScrollWatch=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']").getAttribute("text");
				 test.log(LogStatus.INFO, "First Card after scroll is "+firstCardAfterScrollWatch);
				 if(firstCardBeforeScrollWatch.equals(firstCardAfterScrollWatch)) {
					 homePage.smokeresults("258: Horizontal swiping in Watch carousal",rowno258, "PASS");
					 homePage.reportPass("258: Horizontal swiping in Watch carousal is PASS");
				 }
				 else {
					 homePage.smokeresults("258: Horizontal swiping to the right in Watch carousal",rowno258, "FAIL");
					 homePage.reportFail("258: Horizontal swiping to the right in Watch carousal is FAIL");
				 }
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Unable to locate the first card after swiping right");
				 homePage.smokeresults("258: Horizontal swiping to the left in Watch carousal",rowno258, "FAIL");
				 homePage.reportFail("258: Horizontal swiping to the left in Watch carousal is FAIL");
			 }
		 }
		 else {
			 homePage.smokeresults("258: Horizontal swiping to the left in Watch carousal",rowno258, "FAIL");
			 homePage.reportFail("258: Horizontal swiping to the left in Watch carousal is FAIL");
		 }			 
	 }
	 else {
		 homePage.smokeresults("258: Horizontal swiping to the right in Watch carousal is FAIL",rowno258, "FAIL");
		 homePage.reportFail("258: Horizontal swiping to the right in Watch carousal is FAIL");
	 }
	
	 
	//Verification of 259
	 errCount=0;
	 test.log(LogStatus.INFO, "Starting test for 259: Verify user remains in same card after minimize and resuming the app in Watch Carousal");
	//Click on Watch tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
		 homepagev2.watch_tab.click();	
		 test.log(LogStatus.INFO, "Clicked on Watch tab");
		 BasePageV2.takeScreenshot();
	 }
	 else
		 BasePageV2.reportFail("Unable to click on Watch tab");

	 //Put the app background for first card
	 WebElement nameBeforeBckWatch=null;
	 WebElement nameAfterBckWatch=null;
	 try {
		 nameBeforeBckWatch=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']"); 
		 test.log(LogStatus.INFO, "Card "+nameBeforeBckWatch.getAttribute("text")+" is displayed in foreground");
	 }
	 catch(Exception e) {
		 test.log(LogStatus.INFO, "Unable to fetch the title of card 1");
	 }
	 
	 driver.runAppInBackground(Duration.ofSeconds(5));
	 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
	 driver.currentActivity();
	 //Fetch card name again
	 try {
		 nameAfterBckWatch=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']"); 
		 test.log(LogStatus.INFO, "Card "+nameAfterBckWatch.getAttribute("text")+" is displayed after app is resumed from background");
		 BasePageV2.takeScreenshot();
		 if(nameBeforeBckWatch.getAttribute("text").equals(nameAfterBckWatch.getAttribute("text")))
			 test.log(LogStatus.PASS, "User has remained in card 1 after resume from background");
		 else {
			 test.log(LogStatus.FAIL, "User has NOT remained in card 1 after resume from background");
			 errCount++;
		 }	 
	 }
	 catch(Exception e) {
		 test.log(LogStatus.FAIL, "Unable to fetch the title of card 1");
	 }
	 //Verification for the rest of the cards
	 Utilities.horizontalSwipeCarousalSlow(driver);
	 for(int count=2;count<=5;count++) {
		 try {
			 Thread.sleep(1000);
			 nameBeforeBckWatch=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
			 test.log(LogStatus.INFO, "Card "+nameBeforeBckWatch.getAttribute("text")+" is displayed in foreground");
		 }
		 catch(Exception e) {
			 test.log(LogStatus.INFO, "Unable to fetch the title of card "+count);
		 }
		 
		 driver.runAppInBackground(Duration.ofSeconds(5));
		 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
		 driver.currentActivity();
		 //Fetch card name again
		 try {
			 nameAfterBckWatch=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
			 test.log(LogStatus.INFO, "Card "+nameAfterBckWatch.getAttribute("text")+" is displayed after app is resumed from background");
			 BasePageV2.takeScreenshot();
			 if(nameBeforeBckWatch.getAttribute("text").equals(nameAfterBckWatch.getAttribute("text")))
				 test.log(LogStatus.PASS, "User has remained in card "+count+" after resume from background");
			 else {
				 test.log(LogStatus.FAIL, "User has NOT remained in card "+count+" after resume from background");
				 errCount++;
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Unable to fetch the title of card "+count);
		 }
		 Utilities.horizontalSwipeCarousalSlow(driver);
	 }	
	 //Final result verification for 259
	 if(errCount==0) {
		 homePage.smokeresults("259: Verify user remains in same card after minimize and resuming the app in Watch Carousal",rowno259, "PASS");
		 homePage.reportPass("259: Verify user remains in same card after minimize and resuming the app in Watch Carousal is PASS");
	 }
	 else {
		 homePage.smokeresults("259: Verify user remains in same card after minimize and resuming the app in Watch Carousal",rowno259, "FAIL");
		 homePage.reportFail("259: Verify user remains in same card after minimize and resuming the app in Watch Carousal is FAIL");
	 }
	 
		//Verification of 260
	 errCount=0;
	 test.log(LogStatus.INFO, "Starting test for 260: Verify user remains in same card after minimize and resuming the app in Read Carousal");
	//Click on Read tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) {
		 homepagev2.read_tab.click();	
		 test.log(LogStatus.INFO, "Clicked on Read tab");
		 BasePageV2.takeScreenshot();
	 }
	 else
		 BasePageV2.reportFail("Unable to click on Read tab");

	 //Put the app background for first card
	 WebElement nameBeforeBckRead=null;
	 WebElement nameAfterBckRead=null;
	 try {
		 nameBeforeBckRead=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']"); 
		 test.log(LogStatus.INFO, "Card "+nameBeforeBckRead.getAttribute("text")+" is displayed in foreground");
	 }
	 catch(Exception e) {
		 test.log(LogStatus.INFO, "Unable to fetch the title of card 1");
	 }
	 
	 driver.runAppInBackground(Duration.ofSeconds(5));
	 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
	 driver.currentActivity();
	 //Fetch card name again
	 try {
		 nameAfterBckRead=driver.findElementByXPath("//android.widget.LinearLayout[@index='0']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@resource-id='com.tv.vootkids:id/title']"); 
		 test.log(LogStatus.INFO, "Card "+nameAfterBckRead.getAttribute("text")+" is displayed after app is resumed from background");
		 BasePageV2.takeScreenshot();
		 if(nameBeforeBckRead.getAttribute("text").equals(nameAfterBckRead.getAttribute("text")))
			 test.log(LogStatus.PASS, "User has remained in card 1 after resume from background");
		 else {
			 test.log(LogStatus.FAIL, "User has NOT remained in card 1 after resume from background");
			 errCount++;
		 }	 
	 }
	 catch(Exception e) {
		 test.log(LogStatus.FAIL, "Unable to fetch the title of card 1");
	 }
	 //Verification for the rest of the cards
	 Utilities.horizontalSwipeCarousalSlow(driver);
	 for(int count=2;count<=5;count++) {
		 try {
			 Thread.sleep(1000);
			 nameBeforeBckRead=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
			 test.log(LogStatus.INFO, "Card "+nameBeforeBckRead.getAttribute("text")+" is displayed in foreground");
		 }
		 catch(Exception e) {
			 test.log(LogStatus.INFO, "Unable to fetch the title of card "+count);
		 }
		 
		 driver.runAppInBackground(Duration.ofSeconds(5));
		 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
		 driver.currentActivity();
		 //Fetch card name again
		 try {
			 nameAfterBckRead=driver.findElementByXPath("//android.support.v4.view.ViewPager[@index='1']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/title']");
			 test.log(LogStatus.INFO, "Card "+nameAfterBckRead.getAttribute("text")+" is displayed after app is resumed from background");
			 BasePageV2.takeScreenshot();
			 if(nameBeforeBckRead.getAttribute("text").equals(nameAfterBckRead.getAttribute("text")))
				 test.log(LogStatus.PASS, "User has remained in card "+count+" after resume from background");
			 else {
				 test.log(LogStatus.FAIL, "User has NOT remained in card "+count+" after resume from background");
				 errCount++;
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Unable to fetch the title of card "+count);
		 }
		 Utilities.horizontalSwipeCarousalSlow(driver);
	 }	
	 //Final result verification for 259
	 if(errCount==0) {
		 homePage.smokeresults("260: Verify user remains in same card after minimize and resuming the app in Read Carousal",rowno260, "PASS");
		 homePage.reportPass("260: Verify user remains in same card after minimize and resuming the app in Read Carousal is PASS");
	 }
	 else {
		 homePage.smokeresults("260: Verify user remains in same card after minimize and resuming the app in Read Carousal",rowno260, "FAIL");
		 homePage.reportFail("260: Verify user remains in same card after minimize and resuming the app in Read Carousal is FAIL");
	 }
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}

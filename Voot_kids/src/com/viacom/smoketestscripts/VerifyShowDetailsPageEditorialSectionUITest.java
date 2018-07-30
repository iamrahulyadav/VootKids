package com.viacom.smoketestscripts;

import java.time.Duration;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class VerifyShowDetailsPageEditorialSectionUITest extends BaseTestV2{
	String testName = "VerifyShowDetailsPageEditorialSectionUITest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Show Detail screen Editorial Section UI");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Show Detail screen Tray UI "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of the show detail screen  UI:");	
		
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	WatchPageV2  watchpagev2=new WatchPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	
//click on watch tab	
	test.log(LogStatus.INFO, "Navigating to Watch Page");
	
	if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 30))
	homepagev2.watch_tab.click();
	else
	BasePageV2.reportFail("Not able to click on watch tab");
	
	String xpath="//android.widget.TextView[@text='ALL CHARACTERS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView";
	Utilities.verticalSwipe(driver, xpath);
	Thread.sleep(2000);	
	test.log(LogStatus.INFO, "Navigating to any of show under All Characters Section");
	if(Utilities.explicitWaitClickable(driver, watchpagev2.allCharactersFirstShow, 30))
	watchpagev2.allCharactersFirstShow.click();
	else
	BasePageV2.reportFail("Not able to click show under All Characters section");
	
			//Scroll to  Editorial section
			String editorialsection="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and @text='RELATED SHOWS']";
			Utilities.verticalSwipe(driver, editorialsection);
			Thread.sleep(1000);
			//check for editorial section
			if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSection , 30))
			{
				//test.log(LogStatus.INFO, "Editorial section - is displayed");
				
				//Scroll to  Editorial section
				String editorialsectionRelatedTray="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED SHOWS']";
				Utilities.verticalSwipe(driver, editorialsectionRelatedTray);
				
				if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionRelatedTray , 10))
				{
					//Scroll to  editorial section content
					String editorialsectionRelatedTraycontent="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and ( @text='RELATED SHOWS'  or @text='BOOKS')]//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView";
					Utilities.verticalSwipe(driver, editorialsectionRelatedTraycontent);
					Thread.sleep(1000);
					String showtitle,showpageshowtitle;
					//check for editorial section content
					if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContent, 20))
					{
						test.log(LogStatus.INFO, "content(s) are displayed under Related Shows Tray");
						showtitle=showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContentTitle.getText();
						BasePageV2.takeScreenshot();
						showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContent.click();
						//cHECK FOR Show title
						test.log(LogStatus.INFO, "Clicking on the Show under Related Shows Tray");
						if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageShowTitle , 30))
						{
							
							if(showspagev2.showDetailPageShowTitle.getText().equalsIgnoreCase(showtitle))
							{								
								test.log(LogStatus.INFO, "Navigated to the relevant show");
								BasePageV2.reportPass("Testcase : 'Verify the functionality by tapping on cards:' is Passed");
							}
							else
							BasePageV2.reportFail("Not navigating to the respective show Detail Page ");
						}
						else
						BasePageV2.reportFail("Show Title is not displayed in Show Detail page");
						
						
					}
				/*	else if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionYouMayLikeTrayFirstContent, 20))
					{
						test.log(LogStatus.INFO, "content are displayed under Editorial section ");
					}*/
					else
						BasePageV2.reportFail("No content are displayed under Related Shows Tray");
				}
				
				
				
			
			}
			else
				BasePageV2.reportFail("Editorial section is not displayed in show details page");
			
	//	BasePageV2.reportPass("Testcase : 'Verify the UI of the show detail screen:' is Passed");
	}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}

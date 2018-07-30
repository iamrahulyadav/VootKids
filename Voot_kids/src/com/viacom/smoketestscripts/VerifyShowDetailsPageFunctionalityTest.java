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

public class VerifyShowDetailsPageFunctionalityTest extends BaseTestV2{
	String testName = "VerifyShowDetailsPageFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Show Detail screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Show Detail screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of the show detail screen:");	
		
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
	
	
	test.log(LogStatus.INFO, "Verifying show details page UI");
	
	//Check fOR back button	
	if(Utilities.explicitWaitClickable(driver, showspagev2.showDetailPageBackButton, 30))
	{
		test.log(LogStatus.INFO, "Back button is displayed");
		test.log(LogStatus.INFO, "Verifying back button functionality");
		BasePageV2.takeScreenshot();
		test.log(LogStatus.INFO, "Clicking on Back button");
		showspagev2.showDetailPageBackButton.click();
		String xpath2="//android.widget.TextView[@text='ALL CHARACTERS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView";
		Utilities.verticalSwipe(driver, xpath2);
		Thread.sleep(2000);	
		//test.log(LogStatus.INFO, "Navigating to any of show under All Characters Section");
		if(Utilities.explicitWaitClickable(driver, watchpagev2.allCharactersFirstShow, 30))
		{
			BasePageV2.reportPass("Testcase : 'User should be taken back to the previous screen on tapping the back button' is Passed");
			watchpagev2.allCharactersFirstShow.click();
		}
		else
		BasePageV2.reportFail("Not able to click show under All Characters section");
		
	}
	else
	BasePageV2.reportFail("Back button is not displayed in Show Detail page");
	
	test.log(LogStatus.INFO, "Navigating to the Show Detail page again");
	// Check for character image
	
	if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageCharacterImage , 30))
	test.log(LogStatus.INFO, "Character Image is displayed");
	else
	BasePageV2.reportFail("Character Image is not displayed in Show Detail page");
	
	//cHECK FOR PLAY BUTTON
	
	if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPagePlayButton , 30))
	test.log(LogStatus.INFO, "Play Button is displayed");
	else
	BasePageV2.reportFail("Play Button is not displayed in Show Detail page");
	
	
	//cHECK FOR Show title
	
		if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageShowTitle , 30))
		test.log(LogStatus.INFO, "Show Title is displayed");
		else
		BasePageV2.reportFail("Show Title is not displayed in Show Detail page");
		
		
		//cHECK FOR Show Info
		
		if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageShowInfo , 30))
		test.log(LogStatus.INFO, "Show Info is displayed");
		else
		BasePageV2.reportFail("Show Info is not displayed in Show Detail page");
		
		
		//Scroll to Language available section
		String lang="//android.widget.TextView[@resource-id='com.tv.vootkids:id/textview_available_langauges']";
		Utilities.verticalSwipe(driver, lang);
		Thread.sleep(1000);
		   //Check for Language available section
		    if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageLanguagesAvailableInfo , 30))
			test.log(LogStatus.INFO, "Languages Available Info is displayed");
			else
			BasePageV2.reportFail("Show Info is not displayed in Show Detail page");
		
			//Scroll to Download episodes button
			String download="//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/btn_download_item']";
			Utilities.verticalSwipe(driver, download);
			Thread.sleep(1000);
			 
			//Check for Episodes Download button
		    if(Utilities.explicitWaitClickable(driver, showspagev2.showDetailPageDownlaodEpisodesButton , 30))
			{
		    	test.log(LogStatus.INFO, "Download Episodes Button is displayed");
		    	test.log(LogStatus.INFO, "Verifying Download Episodes Button functionality-");
		    	
		    	//Click dOWNLOAD episodes button
		    	showspagev2.showDetailPageDownlaodEpisodesButton.click();
		        if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageDownlaodEpisodesButton , 30))
		        {
		        	test.log(LogStatus.INFO, "Clicking on Download Episodes button navigated to Download Episodes screen");
		        	BasePageV2.reportPass("Testcase : 'Verify 'Download Episodes' button functionality' is Passed");
		        }
			}
			else
			BasePageV2.reportFail("Download Episodes Button is not displayed in Show Detail page");
		    
		    
		    //Clicking back button in EDownload Episodes screen
		    if(Utilities.explicitWaitClickable(driver, launchpagev2.backButton , 30))
					{
		    	        test.log(LogStatus.INFO, "Clicking on back button in Download Episodes screen");
		            	launchpagev2.backButton.click();
					}
		    else
		    	BasePageV2.reportFail("Back button is not present in Download Episodes screen");
		    
			
			//Scroll to  episodes section
			String episode="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt'  and @text='EPISODES']";
			Utilities.verticalSwipe(driver, episode);
			Thread.sleep(1000);
			 //Check for Episodes section
			if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEpisodesSection , 30))
			{
				test.log(LogStatus.INFO, "Episodes section is Present");
				//Scroll to  episodes section video
				String episodevideo="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView";
				Utilities.verticalSwipe(driver, episodevideo);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEpisodesSectionFirstVideo , 30))
				{		
					test.log(LogStatus.INFO, "Videos are displayed under Episodes section");
			   int count=0, no=0;
			   String title="", way="";
			
			    List<String> videotitles=new LinkedList<String>();
			   //Check number of videos under Episodes section (loop)
		        count=showspagev2.showDetailPageEpisodesSectionTitles.size();		
				if(count!=0 && count>8)
			  	 {
					if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 10))
					{
						test.log(LogStatus.INFO, "See All buttton is displayed for more than 8 videos under Episodes Section");
					}
					else
						{
						    BasePageV2.reportFail("See All Button not displayed for more than 8 videos");
						}
			  	  }
				else if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 10))
				{
					BasePageV2.reportFail("See All button is displayed for less than 8 videos under Episodes Section");
				}
				else
					BasePageV2.reportPass("Testcase : 'Verify the Episodes section UI whe the tray has <=8 card:' is Passed");
						
				
				 for(int i=0;i<count;i++)
			     {
			    	title=showspagev2.showDetailPageEpisodesSectionTitles.get(i).getText();
			    	if(!videotitles.contains(title))
			    	videotitles.add(title);
			     }
				List<String> videotitlesbeforesort=new LinkedList<String>(videotitles);
				System.out.println("Before sorting :"+videotitlesbeforesort);
			    Collections.sort(videotitles);
			    System.out.println("After sorting :"+videotitles);
			    
			    //Verify the sorting order
			    test.log(LogStatus.INFO, "Verifying the Sorting order of all Episodes");
			    for(int i=0;i<count;i++)
			     {
			    	    String bef=videotitlesbeforesort.get(i);
				        String aft=videotitles.get(i);
				        System.out.println(bef);
				        System.out.println(aft);
				    	if(!bef.equals(aft))
			    		BasePageV2.reportFail("Episode - "+bef+": is not in sorted order");
			     }
			    BasePageV2.reportPass("Testcase : 'Verify the sorting order of the episodes by default:' is Passed");
		     }
				else
					BasePageV2.reportFail("No videos are displayed under Episodes section");
				
			}
			else
			BasePageV2.reportFail("Episodes section is not displayed in Show Detail page");
			

			//Scroll to  Editorial section
			String editorialsectionrelatedTray="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and @text='RELATED SHOWS']";
			Utilities.verticalSwipe(driver, editorialsectionrelatedTray);
			Thread.sleep(1000);
			//check for editorial section
			if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSection , 30))
			{
				test.log(LogStatus.INFO, "Editorial section is displayed");
				
				//Scroll to  editorial section content
				String editorialsectioncontent="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and ( @text='RELATED SHOWS'  or @text='BOOKS')]//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView";
				Utilities.verticalSwipe(driver, editorialsectioncontent);
				Thread.sleep(1000);
				//check for editorial section content
				if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "content are displayed under Editorial section ");
				}
				else if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionYouMayLikeTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "content are displayed under Editorial section ");
				}
				else
					BasePageV2.reportFail("No content are displayed under Editorial section");
			}
			else
				BasePageV2.reportFail("Editorial section is not displayed in show details page");
			
		BasePageV2.reportPass("Testcase : 'Verify the UI of the show detail screen:' is Passed");
	}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}

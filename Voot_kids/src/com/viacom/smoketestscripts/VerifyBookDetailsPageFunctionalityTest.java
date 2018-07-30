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
import com.viacom.pagesVersion2.BooksPageV2;
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

public class VerifyBookDetailsPageFunctionalityTest extends BaseTestV2{
	String testName = "VerifyBookDetailsPageFunctionalityTest";
	
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Book Detail screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Book Detail screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of the book detail screen:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int metadatarow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",metadatarow, "Verify the metadata of  the book:");	
		
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	WatchPageV2  watchpagev2=new WatchPageV2(driver,test);
	BooksPageV2  bookspagev2=new BooksPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	
//click on watch tab	
	test.log(LogStatus.INFO, "Navigating to Read Tab");
	Thread.sleep(1000);
	if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 30))
	homepagev2.read_tab.click();
	else
	BasePageV2.reportFail("Not able to click on read tab");
	Thread.sleep(1000);
	String xpath="//android.widget.TextView[@text='NEW BOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView";
	Utilities.verticalSwipe(driver, xpath);
	Thread.sleep(2000);	
	test.log(LogStatus.INFO, "Navigating to any of book under New Books Section");
	
	if(Utilities.explicitWaitClickable(driver, bookspagev2.newBooksFirstBook, 30))
	bookspagev2.newBooksFirstBook.click();
	else
	BasePageV2.reportFail("Not able to click book under New Books section");
	
	
	test.log(LogStatus.INFO, "Verifying Books details page UI");
	
	//Check fOR back button	
	if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBackButton, 30))
	{
		test.log(LogStatus.INFO, "Back button is displayed");
		test.log(LogStatus.INFO, "Verifying Back icon functionality in Book Detail screen:");
		BasePageV2.takeScreenshot();
		bookspagev2.bookDetailPageBackButton.click();
		if(Utilities.explicitWaitClickable(driver, bookspagev2.newBooksFirstBook, 30))
		{
			BasePageV2.reportPass("Testcase : 'Verify the click funtionality on tapping on back icon in book detail page' is Passed");
			bookspagev2.newBooksFirstBook.click();
		}
			else
			BasePageV2.reportFail("Not able to click book under New Books section");
	}
	else
	BasePageV2.reportFail("Back button is not displayed in Book Detail screen");
	
	//cHECK FOR Favorite button
	
	if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageFavoriteButton , 30))
	test.log(LogStatus.INFO, "Favoriting Button is displayed");
	else
	BasePageV2.reportFail("Favorite Button is not displayed in Book Detail screen");
	
	
	//cHECK FOR bOOK title
	
		if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookTitle , 30))
		{
			if(!bookspagev2.bookDetailPageBookTitle.getText().isEmpty() && bookspagev2.bookDetailPageBookTitle.getText()!=null)
			test.log(LogStatus.INFO, "Book Title is displayed");
			else
		    test.log(LogStatus.FAIL, "Book Title is missing");
		}
		else
		BasePageV2.reportFail("Book Title is not displayed in Show Detail page");
		
		
		//cHECK FOR bOOK Author name
		
		if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookAuthorName , 30))
		{
			if(!bookspagev2.bookDetailPageBookAuthorName.getText().isEmpty() && bookspagev2.bookDetailPageBookAuthorName.getText()!=null)
			test.log(LogStatus.INFO, "Book Author name is displayed");
			else
			test.log(LogStatus.FAIL, "Book Author name is missing");
		}
		else
		BasePageV2.reportFail("Book Author Name is not displayed");
		
		
		//cHECK FOR bOOK description
		
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookDescription , 30))
				{
					if(!bookspagev2.bookDetailPageBookDescription.getText().isEmpty() && bookspagev2.bookDetailPageBookDescription.getText()!=null)
					test.log(LogStatus.INFO, "Description about Book is displayed");
					else
					test.log(LogStatus.FAIL, "Description about Book is missing");
				}
				else
				BasePageV2.reportFail("Description about Book is not displayed in Book Detail screen");
				
				
				BasePageV2.reportPass("Testcase : 'Verify the metadata of  the book:' is Passed");	
		//scroll to book level 
				xpath="//android.widget.TextView[@text='Level']";
				Utilities.verticalSwipe(driver, xpath);
				
		//Check For Book Level
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookLevelCount , 30))
				{
					if(!bookspagev2.bookDetailPageBookLevelCount.getText().isEmpty() && bookspagev2.bookDetailPageBookLevelCount.getText()!=null)
						test.log(LogStatus.INFO, "Book Level is displayed");
						else
						test.log(LogStatus.FAIL, "Book Level is missing");
					
				}
				else
				BasePageV2.reportFail("Book Level  is not displayed in Book Detail screen");

		
				//Check For Book Read Time
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageReadTime , 30))
				{
					if(!bookspagev2.bookDetailPageReadTime.getText().isEmpty() && bookspagev2.bookDetailPageReadTime.getText()!=null)
						test.log(LogStatus.INFO, "Book Read Time is displayed");
						else
						test.log(LogStatus.FAIL, "Book Read Time is missing");
					
				}
				else
				BasePageV2.reportFail("Book Read Time is not displayed in Book Detail screen");
				
				
				//Check For Book Read Time
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageNarrationIcon , 30))
				{
					    test.log(LogStatus.INFO, "Book Narration icon is displayed");
				}
				else
				BasePageV2.reportFail("Book Narration icon is not displayed");
				
				
				
			//Scroll to Download episodes button
			String download="//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/btn_download_item']";
			Utilities.verticalSwipe(driver, download);
			Thread.sleep(1000);
			
			//Check for Episodes Download button
		    if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageDownlaodEpisodesButton , 30))
			{
		    	test.log(LogStatus.INFO, "Download Book Button is displayed");
		    	test.log(LogStatus.INFO, "Verifying Download Book Button functionality-");
		    	
		    	/*//Click dOWNLOAD episodes button
		    	showspagev2.showDetailPageDownlaodEpisodesButton.click();
		        if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageDownlaodEpisodesButton , 30))
		        {
		        	test.log(LogStatus.INFO, "Clicking on Download Episodes button navigated to Download Episodes screen");
		        	BasePageV2.reportPass("Testcase : 'Verify 'Download Episodes' button functionality' is Passed");
		        }*/
			}
			else
			BasePageV2.reportFail("Download Book Button is not displayed in Show Detail page");
		    
		  /*  
		    //Clicking back button in EDownload Episodes screen
		    if(Utilities.explicitWaitClickable(driver, launchpagev2.backButton , 30))
					{
		    	        test.log(LogStatus.INFO, "Clicking on back button in Download Episodes screen");
		            	launchpagev2.backButton.click();
					}
		    else
		    	BasePageV2.reportFail("Back button is not present in Download Episodes screen");
		    */
			/*
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
			BasePageV2.reportFail("Episodes section is not displayed in Show Detail page");*/
			

			//Scroll to  Editorial section
			String editorialsectionrelatedTray="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and @text='RELATED EBOOKS']";
			Utilities.verticalSwipe(driver, editorialsectionrelatedTray);
			Thread.sleep(1000);
			//check for editorial section
			if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageRelatedBooksTray , 30))
			{
				test.log(LogStatus.INFO, "Related EBooks Tray is displayed");
				
				//Scroll to  related tray content
				String relatedbooktraycontent="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView";
				Utilities.verticalSwipe(driver, relatedbooktraycontent);
				Thread.sleep(1000);
				String bookTitle;
				//check for editorial section content
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageRelatedTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "Book(s) are displayed under Related EBooks");
					
					
					//Check for icon, title , description , 
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					bookTitle=bookspagev2.bookDetailPageEditorialSectionRelatedTrayFirstContentTitle.getText();
					BasePageV2.takeScreenshot();
				
					
					test.log(LogStatus.INFO, "Clicking on the book under Related Shows Tray");
					bookspagev2.bookDetailPageRelatedTrayFirstContent.click();
					
					//cHECK FOR book title

					if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookTitle , 30))
					{
						
						if(bookspagev2.bookDetailPageBookTitle.getText().equalsIgnoreCase(bookTitle))
						{								
							test.log(LogStatus.INFO, "Navigated to the relevant book");
							BasePageV2.reportPass("Testcase : 'Verify the functionality by tapping on a Book card present under any configurable tray(Ex: Related/You May Also Like)' is Passed");
						}
						else
						BasePageV2.reportFail("Not navigating to the respective Book Detail Page ");
					}
					else
					BasePageV2.reportFail("Book Title is not displayed in Show Detail page");
					
					
					
					
					
					
					
					
				}
				/*else if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionYouMayLikeTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "content are displayed under Editorial section ");
				}*/
				else
					BasePageV2.reportFail("No books are displayed under Related Ebooks Tray");
			}
			else
				BasePageV2.reportFail("Related ebooks Tray is not displayed in Book details page");
			
		BasePageV2.reportPass("Testcase : 'Verify the UI of the Book detail screen:' is Passed");
	}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}

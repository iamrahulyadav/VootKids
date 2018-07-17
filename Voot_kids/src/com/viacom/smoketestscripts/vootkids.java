package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.viacom.utils.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.MobileElement;

public class vootkids extends BaseTestV2 {
	
	String testName = "vootkids";
	public static int rowno=0;
	public static int rowno1=0;
	public static int rowno2=0;

	@SuppressWarnings("static-access")
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{
		if(GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("vootkids");
		test.log(LogStatus.INFO, "Starting the test for Verify all videos: "+VootConstants.DEVICE_NAME);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno , "Top UIbar (Profile,Voot Kids logo,Cast icon) is verified");		
		int rowno1=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno1 , "Top UIbar (profile, Tab Bars(My Stuff, Watch, Read, Listen & Search),Cast icon) is verified");		
		int rowno2=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno2 , "Verify 'Profile' icon functionality");			
		int rowno3=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno3 , "Verify 'Voot Kids' logo functionality");	
		int rowno4=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno4 , "Verify 'Chromecast' icon functionality");	
		int rowno5=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno5 , "Verify top bar UI while scrolling down the page");			
		int rowno6=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno6, "Verify top bar UI while scrolling up the page");			
		int rowno7=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno7, "Verify Tab bar menu UI when there are more than 5 options present in the section");			
		int rowno8=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno8, "Verify top bar UI while scrolling up the page");			
		int rowno9=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno9, "Scrolling up the page should bring the tap bar menu back");			
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		
		//calling Config API
		ArrayList<String>List_tabs=new ArrayList<String>();
		String url_tab=xls.getCellData("Api", 1, 4);
		System.out.println(url_tab);
		Response resp_tab=Utilities.requestUtility(url_tab);
	    int totaltabsapi=resp_tab.jsonPath().get("assets.size()");
	    System.out.println(totaltabsapi);
	    for(int i=0;i<=totaltabsapi;i++)
	    {
	    	String tabid=resp_tab.jsonPath().get("assets.tabId["+i+"]");
	    	List_tabs.add(tabid);	    				    				    			
	    }
	    System.out.println(List_tabs);
		//test purpose
		//end test purpose		
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 10))
			{
			   homepagev2.profilepic.click();
				if(Utilities.explicitWaitClickable(driver, homepagev2.switch_prof, 20))
				{								
					if(Utilities.explicitWaitClickable(driver, homepagev2.btn_editprofile, 20))
					{	
						test.log(LogStatus.INFO, "Verified edit profile button");
					}
					else
						test.log(LogStatus.FAIL, "Edit profile button is not their");					
					
					if(Utilities.explicitWaitVisible(driver, homepagev2.btn_parentzone, 20))
					{	
						test.log(LogStatus.INFO, "Verified Prent zone button");
						BasePageV2.takeScreenshot();
					}
					else
						test.log(LogStatus.FAIL, "Parent zone button is not their");
					
					if(Utilities.explicitWaitClickable(driver, homepagev2.btn_cancel_fromprofile, 20))
					{	
						homepagev2.btn_cancel_fromprofile.click();
					}
					else
						test.log(LogStatus.FAIL, "Cancel button is not available");					
				}
				else
					
			    test.log(LogStatus.INFO, "Profile icon is able to click");
			    homepagev2.smokeresults("",rowno2, "pass");
			}
			else
				test.log(LogStatus.FAIL, "Profile icon is not able to click");
			
			if(Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 20))
			{
					System.out.println("Kids Logo Icon is able to click");
			}
			else
			System.out.println("Kids Logo Icon is not able to click");


			if(Utilities.explicitWaitVisible(driver, homepagev2.casticon, 20))
			{
			test.log(LogStatus.INFO, "Cast icon is able to click");
	        homepagev2.smokeresults("Verify 'Chromecast' icon functionality",rowno4, "pass");
			}
			else
				test.log(LogStatus.FAIL, "Cast icon is not able to click");
	        homepagev2.smokeresults("Top UIbar (Profile,Voot Kids logo,Cast icon) is verified",vootkids.rowno, "pass");

	     
	   //Navigation of tabs        
		List<WebElement> tabs=null;
		try{									
			tabs=driver.findElementsByClassName("android.support.v7.app.ActionBar$Tab");
			System.out.println(tabs.size());		
		}
		catch (Exception e) {}
		for(int i=0;i<tabs.size();i++)
		{			
			WebElement tab=tabs.get(i);
			
			switch(i)
			{  		
			    case 0:
			    	try{ 
			       //Utilities.explicitWaitClickable(driver, tab, 10);
			      //tab.click();
			    	if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_text, 20))
			       {
			       test.log(LogStatus.INFO, "Navigation to (My Stuff) is passed");
			       BasePageV2.takeScreenshot();
			       }
			       else
				    	if(!Utilities.explicitWaitVisible(driver, homepagev2.mystuff_text, 20))
				    	{
				    	   String text="My Stuff";
				    	   driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
				    	   test.log(LogStatus.INFO, "Navigation to (My Stuff) is passed");
					       BasePageV2.takeScreenshot();  
					    	if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 20))
					       {
					    		BasePageV2.swipetokidslogo(driver);			    		
					       }
					       }
				    	else
						test.log(LogStatus.FAIL, "Navigation to (My Stuff) is failed");
				     }
				catch (Exception e) {}
			    case 1:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10))			    	
			    		homepagev2.watch_tab.click();	
			    	if(Utilities.explicitWaitVisible(driver, homepagev2.watchtab_text, 20))
			       {
			       test.log(LogStatus.INFO, "Navigation to (Watch) is passed");
			       BasePageV2.takeScreenshot();
			       }
			       else
						test.log(LogStatus.FAIL, "Navigation to (Watch) is failed");
				     }
				catch (Exception e) {}
			    case 2:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10))
			    		homepagev2.read_tab.click();
			       if(Utilities.explicitWaitVisible(driver, homepagev2.readtab_text, 20))
			       {
			       test.log(LogStatus.INFO, "Navigation to (read) is passed");
			       BasePageV2.takeScreenshot();
			       }
			       else
			       if(!Utilities.explicitWaitVisible(driver, homepagev2.readtab_text, 20))
			       {
			    	   String text="MOST POPULAR EBOOKS";
			    	   driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
			    	   test.log(LogStatus.INFO, "Navigation to (read) tab is passed");
				       BasePageV2.takeScreenshot();  
				       if(!Utilities.explicitWaitVisible(driver, homepagev2.readtab_text, 20))
				       {
				    		BasePageV2.swipetokidslogo(driver);			    		
				       }
			       }
			       else
						test.log(LogStatus.FAIL, "Navigation to (read)tab is failed");
				     }
				catch (Exception e) {}
			    case 3:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10))
			    		homepagev2.Listen_tab.click();
			    	if(Utilities.explicitWaitVisible(driver, homepagev2.Listentab_text, 20))
				       {
				       test.log(LogStatus.INFO, "Navigation to (Listen) is passed");
				       BasePageV2.takeScreenshot();
				       }
				       else
				       if(!Utilities.explicitWaitVisible(driver, homepagev2.Listentab_text, 20))
				       {
				    	   String text="MOST POPULAR AUDIOS";
				    	   driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
				    	   test.log(LogStatus.INFO, "Navigation to (Listen) tab is passed");
					       BasePageV2.takeScreenshot();  
					       if(!Utilities.explicitWaitVisible(driver, homepagev2.Listentab_text, 20))
					       {
					    		BasePageV2.swipetokidslogo(driver);			    		
					       }
				       }
				       else
							test.log(LogStatus.FAIL, "Navigation to (read)tab is failed");
					     
				     }
				catch (Exception e) {}
			    case 4:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.search_tab, 10))
			    		homepagev2.search_tab.click();
			    if(Utilities.explicitWaitVisible(driver, homepagev2.searchtab_text, 20))	
			       {
			       test.log(LogStatus.INFO, "Navigation to (Search) is passed");
			       BasePageV2.takeScreenshot();
			       }
			       else
						test.log(LogStatus.FAIL, "Navigation to (Search) is failed");
				     }
				catch (Exception e) {}			
			}
			if(i<5)
   				break;
		}		
        homepagev2.smokeresults("Top UIbar (profile, Tab Bars(My Stuff, Watch, Read, Listen & Search),Cast icon) is verified",rowno1, "pass");

        //scroll down
        
        if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 20))
        {
        	homepagev2.mystuff_tab.click();
        	test.log(LogStatus.INFO, "Tapping on My stuff to check scrolling");
        }
        else
        	test.log(LogStatus.FAIL, "Unable to tap on My stuff tab");
        try{
        String text="FAVOURITES";
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
        }
        catch (Exception e) {
     test.log(LogStatus.FAIL, "Unable to find My Favourite tab");		}
     	try{
            if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 10))
            {
               test.log(LogStatus.INFO, "Page is scrolled and Menu tab is hided");
               BasePageV2.takeScreenshot();
               homepagev2.smokeresults("Verify top bar UI while scrolling down the page",rowno5, "pass");             
               homepagev2.smokeresults("",rowno8, "pass");
            }
            else
            {
             test.log(LogStatus.FAIL, "Page is scrolled but Menu tab is unable to hide");
            }
     	}
     	catch (Exception e) {
            test.log(LogStatus.FAIL, "Unable to find the Main tab(Voot logo)");
		}
     

           if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 20))
           {
               Utilities.verticalSwipeDown(driver);
             if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 10))
             {
                 driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\""+homepagev2.kidslogoicon+"\").instance(0))");
	          // Utilities.verticalSwipeDown(driver);	 
	          // Utilities.verticalSwipeDown(driver);	    
                homepagev2.kidslogoicon.click();
              homepagev2.smokeresults("Verify 'Voot Kids' logo functionality",rowno3, "pass");
             }
             else
 				test.log(LogStatus.FAIL, "Kids Logo Icon is not able to click");

             if(Utilities.explicitWaitVisible(driver, homepagev2.homecarousal_layout, 10))
             {
 	         test.log(LogStatus.INFO, "Page is scrolled up and Menu tab is Visible");
             BasePageV2.takeScreenshot();
  	         homepagev2.smokeresults("",rowno6, "pass"); 	      
	         homepagev2.smokeresults("",rowno9, "pass");
             }
             else
     	        test.log(LogStatus.FAIL, "Page is scrolled up and Menu tab is not Visible");
           }
             else
      			test.log(LogStatus.FAIL, "Main tab is visible didn't scroll");
           
           //Checking tab is scrolable or not with api size
           if(totaltabsapi>=5)
          {
           if(Utilities.explicitWaitVisible(driver, homepagev2.actiontab, 10))
           {
        	   Utilities.appbar(driver, homepagev2.actiontab);
               homepagev2.smokeresults("",rowno7, "pass");
        	   test.log(LogStatus.INFO, "The icons should became scrollable as more sections are added");
        	   BasePageV2.takeScreenshot();
           }
           else       
        	   test.log(LogStatus.INFO, "Unable to scroll as size is fixed");
          }	  
           //End of Checking tab is scrolable or not with api size

	}
		@DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					
		}
}

package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class Just_foryou extends BaseTestV2 {
	
	String testName = "Masthead_carouselScripts";
	/*public static int rowno=0;
	public static int rowno1=0;	
	public static int rowno2=0;	
	public static int rowno3=0;	
	public static int rowno4=0;	*/	
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("Verify Masthead_carousel of all tabs");
		test.log(LogStatus.INFO, "Starting the test to Verify Just For You Section: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno,"Verify 'Just For You' (Section Title,List of cards)");	
		 int rowno1=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno1,"Verify 'See All' should be present in 'Just For You' section if the tray has more than 8 cards");	
		int rowno2=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno2,"Verify the availibility of 'See All' button in 'Just For You' section when 8 assets present");	
		int rowno3=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno3,"Verify the functionality by tapping ''Just For You'' title");	
		int rowno4=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno4,"Verify the type of cards present under 'Just For You' section:");	
		int rowno5=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno5,"Verify the 'See All' button functionality in 'Just For You' Section");
		int rowno6=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno6,"Nothing here is displayed");	
		int rowno7=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno7,"Verify the displayed metadata for episode card");	
		int rowno8=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno8,"Verify the displayed metadata for show card");	
		int rowno9=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno9,"Verify the displayed metadata for book card");	
		int rowno10=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno10,"Verify the displayed metadata for Audio Story card");	
		int rowno11=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno11,"Verify UI of JFY see all page");	
		int rowno12=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno12,"Verify JFY list view page scroll functionality");	
		int rowno13=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno13, "Verify the functionality by scrolling empty space on the right of the title");											
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);		
		String  justforutext = " ",justforutext1=" ";
		String titleName="";
		String titles=" ";
		String episode="";
		String vduration="";
	    ArrayList<String> descFE=new ArrayList<String>();
	    ArrayList<String> VdurationFE=new ArrayList<String>();	
	    ArrayList<String> EpnoFE=new ArrayList<String>();		
		//api check		
	    		//calling Config API
				String url_Config=xls.getCellData("Api", 1, 3);
			    Response resp_Config=Utilities.requestUtility(url_Config);
			    ArrayList<Integer> Listof_OTT = new ArrayList<Integer>();
			    resp_Config.then().assertThat().statusCode(200);
			    resp_Config.prettyPrint();
			    Map<String,Integer> ott=resp_Config.jsonPath().get("assets.OTT");
			    for(Map.Entry<String, Integer> m :ott.entrySet())			    
			    Listof_OTT.add(m.getValue());			    
	    		//End of calling config API
	    
	    		//calling just for you API 
	    		String url_justforYou=xls.getCellData("Api", 1, 2);
			    Response resp_justforYou=Utilities.requestUtility(url_justforYou);
				//resp_justforYou.then().assertThat().statusCode(200);
				//resp_justforYou.prettyPrint();
			    int totalitemsofapi=resp_justforYou.jsonPath().get("assets.totalItems");
			    System.out.println(totalitemsofapi);		
			    ArrayList<String> Listof_descapi=new ArrayList<String>();
			    ArrayList<Integer> Listof_mediatypeapi = new ArrayList<Integer>();
			    ArrayList<Long> Listof_Vdurationapi = new ArrayList<Long>();
			    ArrayList<Integer> Listof_Epnoapi = new ArrayList<Integer>();			    
			    int x= resp_justforYou.jsonPath().get("assets.items.size()");
			    System.out.println(x);
			    for(int i=0;i<x;i++)
		      {
		        int mediatype= resp_justforYou.jsonPath().get("assets.items["+i+"].mediaType");
		        Listof_mediatypeapi.add(mediatype);
		        String descfromapi=resp_justforYou.jsonPath().get("assets.items["+i+"].desc");
		        Listof_descapi.add(descfromapi);
		        long Vduration=resp_justforYou.jsonPath().getInt("assets.items["+i+"].duration");
		        long v_duration=TimeUnit.MILLISECONDS.toMinutes(Vduration);
		        Listof_Vdurationapi.add(v_duration);
		        int Epnomapi=resp_justforYou.jsonPath().get("assets.items["+i+"].episodeNo");
		        Listof_Epnoapi.add(Epnomapi);
		        String nullvalforrefSeriesTitle = resp_justforYou.jsonPath().get("assets.items["+i+"].refSeriesTitle").toString();
		    	if(nullvalforrefSeriesTitle.equals("null"))		    	
		    		BasePageV2.reportFail("Reference Title is Missing for the video"+mediatype);
		    	String nullvalfortitle = resp_justforYou.jsonPath().get("assets.items["+i+"].title").toString();
		    	if(nullvalfortitle.equals("null"))	
		    		BasePageV2.reportFail("Title is Missing for the video"+mediatype);
		       }
			    System.out.println(Listof_descapi.size());
			    System.out.println(Listof_mediatypeapi);
			    System.out.println(Listof_descapi);
			    System.out.println(Listof_Vdurationapi);
		//End of just for you API Check	
			
			    //Navigaing to Mystuff 		
			    try{
			    	String end="//android.widget.TextView[@text='JUST FOR YOU']";
			    	Utilities.verticalSwipe(driver, end);
			    	test.log(LogStatus.INFO, "Navigated to My stuff to verify Just for you section");
				    
			    	if(totalitemsofapi==0)
			    	{
			    		test.log(LogStatus.INFO, "Nothing here is Displayed");
				        homepagev2.smokeresults("",rowno6, "pass");
			    	}			    	
			    if(totalitemsofapi>=8)
			    	{
			    	test.log(LogStatus.PASS, "'Just For You' title and List of cards is Verified ");
			        homepagev2.smokeresults("",rowno, "pass");
			    	}			    
			    	BasePageV2.takeScreenshot();
			    //swiping on empty space to navigate watch section
			    try{
			    	Utilities.horizontalSwipe(driver);
			    if(!Utilities.explicitWaitVisible(driver, homepagev2.justforu_text, 10))
			    	{
			    	test.log(LogStatus.PASS, "Verify the functionality by scrolling empty space on the right of the title");
			        homepagev2.smokeresults("",rowno13, "pass");
			    	BasePageV2.takeScreenshot();
			    	}
			    else
			    	{
			    	BasePageV2.reportFail("Verify the functionality by scrolling empty space on the right of the title is failed");
			    	}
			    }
	       catch (Exception e) {
	    	   					e.printStackTrace();	       					
	    	   					}
	       //Swiping left to go back to just for you section on My stuff tab
			    try{
			    	Utilities.horizontalSwipeulta(driver);
			    	if(Utilities.explicitWaitVisible(driver, homepagev2.justforu_text, 10))			    	
			    		test.log(LogStatus.INFO, "Navigating back to My stuff to continue..");			    
			    	else			    	
			    		BasePageV2.reportFail("Unable to Navigate back to Mystuff tab");			    	
			    }
			    	catch (Exception e) {
	    	   						e.printStackTrace();	       					
	       								}
			    justforutext= homepagev2.justforu_text.getText().trim();
		    //clicking on Just for you verification
			    if(Utilities.explicitWaitClickable(driver, homepagev2.justforu_text, 10))
			    {
			    	homepagev2.justforu_text.click();
			    	justforutext1= homepagev2.justforu_text_inside.getText().trim();
			    	homepagev2.smokeresults("",rowno3, "pass");
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.justforu_bckbtn, 5))
			    	{
				    	homepagev2.smokeresults("",rowno11, "pass");
				    	homepagev2.smokeresults("",rowno12, "pass");
			    		if(justforutext.equalsIgnoreCase(justforutext1))
					  {
						test.log(LogStatus.PASS, "Verify the functionality by tapping 'Just For You' title"); 
						BasePageV2.takeScreenshot();
						homepagev2.justforu_bckbtn.click();
					  }
					  else
						  test.log(LogStatus.INFO, "Unable to navigate to 'SEE ALL' on tap of 'Just For You' as the card size is 8");
			    	}
			    }
			    else
			    {
				  test.log(LogStatus.INFO, "Unable to navigate to 'SEE ALL' on tap of 'Just For You' as the card size is 8");
				  BasePageV2.takeScreenshot();
				//if card size is 8 (When See All is not their)
				  Utilities.verticalSwipe(driver);
					for(int i=1;i<=8;i++)
					{
						try{
							WebElement size8=driver.findElementByXPath("(//android.widget.TextView[@text='JUST FOR YOU']/ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_description'])["+i+"]");
							titles=size8.getText().trim();
							episode=titles.substring(1,3);
							WebElement videoduration=driver.findElementByXPath("(//android.widget.TextView[@text='JUST FOR YOU']/ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_video_duration'])["+i+"]");
							vduration=videoduration.getText().trim();
							vduration=vduration.split(" ")[0];
							}
							catch (Exception e) {
								Utilities.verticalSwipe(driver);
								WebElement size8=driver.findElementByXPath("(//android.widget.TextView[@text='JUST FOR YOU']/ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_description'])["+i+"]");
								titles=size8.getText().trim();
								episode=titles.substring(1,3);
								WebElement videoduration=driver.findElementByXPath("(//android.widget.TextView[@text='JUST FOR YOU']/ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_video_duration'])["+i+"]");
								vduration=videoduration.getText().trim();
								vduration=vduration.split(" ")[0];
												}
						if(!descFE.contains(titles))						
							descFE.add(titles);											
						else					
							continue;	
						if(!VdurationFE.contains(vduration))						
							VdurationFE.add(vduration);
						else					
							continue;
						if(!EpnoFE.contains(episode))
							EpnoFE.add(episode);
						else
							continue;	
					}	
					if(Listof_mediatypeapi.containsAll(Listof_OTT))
					{
					test.log(LogStatus.INFO, "Media Type is validated");	
					homepagev2.smokeresults("",rowno4, "pass");
					}
					else
						BasePageV2.reportFail("Media id is Not Validated");
					
					if(descFE.size()==totalitemsofapi)
					{
					test.log(LogStatus.INFO, "Number of Cards are same as from API");															
						if(descFE.contains(Listof_descapi))
						test.log(LogStatus.INFO, "All video decsciptions are same as API");													
						else
							BasePageV2.reportFail("All video decsciptions are not same as API");
						if(VdurationFE.contains(Listof_Vdurationapi))
							test.log(LogStatus.INFO, "All video durations matches with API");
						else
							BasePageV2.reportFail("All video durations are not same as API");
						if(EpnoFE.contains(Listof_Epnoapi))
							test.log(LogStatus.INFO, "All Episode numbers are same as API");
						else
							BasePageV2.reportFail("All Episode numbers are not same as API");
					}
					else
						BasePageV2.reportFail("Number of Cards are same as from API");					
			 //End of If card size is 8 (When See All is not their)
	        }	      
	        }
	        catch (Exception e) {
	        test.log(LogStatus.FAIL, "Unable to find Just for you section");
	        BasePageV2.takeScreenshot();
	        }						
		try{
	        Utilities.verticalSwipe(driver);
		   //int listsize=driver.findElementsByXPath("//android.widget.TextView[@text='JUST FOR YOU']/../..//android.view.ViewGroup").size();
		  // System.out.println(listsize);
		if(totalitemsofapi>=9)
		   {
			String end="//android.widget.Button[@text='SEE ALL']";
		    Utilities.verticalSwipe(driver, end);
		    if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
			{	
		    if(totalitemsofapi>=9)
			{
			test.log(LogStatus.PASS, "Verify the availibility of 'See All' button in 'JUST FOR YOU' as the tray has More than 8 cards");
	        homepagev2.smokeresults("",rowno1, "pass");
			BasePageV2.takeScreenshot();
			}
			 }
			    else
				{
				  test.log(LogStatus.INFO, "Verify the availibility of 'See All' button in 'Just For You' section when 8 assets present");					
				  BasePageV2.takeScreenshot();
				  homepagev2.smokeresults("",rowno2, "pass");
				}
				 }	
		             else
		             {
		            	 test.log(LogStatus.INFO, "'SEE ALL' button is not Available as card size if 8");
						 BasePageV2.takeScreenshot();
		             }
			}
				catch (Exception e) {
								e.printStackTrace();
									}	
			if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
			{
				homepagev2.seeAll.click();
				BasePageV2.takeScreenshot();
				homepagev2.smokeresults("",rowno5, "pass");
//(checking duration,name,title inside card))
		    List<WebElement> titlesinsidejustforu = driver.findElementsById("com.tv.vootkids:id/grid_description");		    
		    for(int i=1;i<10;i++)
			{
			try{
				WebElement cc=driver.findElement(By.xpath("(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_description'])["+i+"]"));
				titles=cc.getText().trim();
				episode=titles.substring(1,3);
				WebElement videoduration=driver.findElementByXPath("(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_video_duration'])["+i+"]");
				vduration=videoduration.getText().trim();
				vduration=vduration.split(" ")[0];
				}
				catch (Exception e) {
					try{
						Utilities.verticalSwipe(driver);
						WebElement cc=driver.findElement(By.xpath("(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_description'])["+i+"]"));
						titles=cc.getText().trim();
						episode=titles.substring(1,3);
						WebElement videoduration=driver.findElementByXPath("(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_video_duration'])["+i+"]");
						vduration=videoduration.getText().trim();
						vduration=vduration.split(" ")[0];
					   }
						catch (Exception e1) {
						try{
						titlesinsidejustforu = driver.findElementsById("com.tv.vootkids:id/grid_description");
						for(int j=0;j<titlesinsidejustforu.size();j++)
						{	
						titles=titlesinsidejustforu.get(j).getText().trim();
						episode=titles.substring(1,3);
						if(!descFE.contains(titles))			             				
							descFE.add(titles);			             
						}
		               }
						catch (Exception e4) {}				
						}					
					}			
					System.out.println(titles);
					
				//Comparing size of items with titles size
					if(!descFE.contains(titles))						
						descFE.add(titles);											
					else					
						continue;	
					if(!VdurationFE.contains(vduration))						
						VdurationFE.add(vduration);
					else					
						continue;
					if(!EpnoFE.contains(episode))
						EpnoFE.add(episode);
					else
						continue;		
			}	
			System.out.println(descFE);
			System.out.println(VdurationFE);
			System.out.println(EpnoFE);
			//
			Collections.disjoint(Listof_mediatypeapi, Listof_OTT);
			if(Listof_mediatypeapi.containsAll(Listof_OTT))
			{
			test.log(LogStatus.INFO, "Media Type is validated");	
			homepagev2.smokeresults("",rowno7, "pass");
			homepagev2.smokeresults("",rowno8, "pass");
			homepagev2.smokeresults("",rowno9, "pass");
			homepagev2.smokeresults("",rowno10, "pass");
			}
				else
					BasePageV2.reportFail("Media id is Not Validated");
			if(descFE.size()==totalitemsofapi)
			{
				test.log(LogStatus.INFO, "Number of Cards are same as from API");															
				if(descFE.contains(Listof_descapi))
				test.log(LogStatus.INFO, "All video decsciptions are same as API");													
				else
					BasePageV2.reportFail("All video decsciptions are not same as API");
				if(VdurationFE.contains(Listof_Vdurationapi))
				test.log(LogStatus.INFO, "All video durations matches with API");
				else
					BasePageV2.reportFail("All video durations are not same as API");
				if(EpnoFE.contains(Listof_Epnoapi))
					test.log(LogStatus.INFO, "All Episode numbers are same as API");
				else
					BasePageV2.reportFail("All Episode numbers are not same as API");
			}
			else
					BasePageV2.reportFail("Number of Cards are same as from API");				
//End of (checking duration,name,title inside card))						 
		} 
			else
					BasePageV2.reportFail("Unable to tap on SEE ALL");
			}		
				@DataProvider
				public Object[][] getData(){
					return DataUtil.getData(testName,xls);						
				}
		}
					
		
		
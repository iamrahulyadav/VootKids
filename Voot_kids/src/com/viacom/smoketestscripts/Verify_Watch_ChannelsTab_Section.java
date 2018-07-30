package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class Verify_Watch_ChannelsTab_Section extends BaseTestV2 {

	// TODO Auto-generated constructor stub

	String testName = "Verify_Watch_ChannelsTab_Section";
	/*public static int rowno=0;
		public static int rowno1=0;	
		public static int rowno2=0;	
		public static int rowno3=0;	
		public static int rowno4=0;	*/	
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("Verify Watch Channels Section");
		test.log(LogStatus.INFO, "Starting the test to Verify Channels Section: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno,"Verify the UI of Channels tab");	
		int rowno1=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno1,"Verify the availibility of 'See All' button in Channels tab if there are > 8 channels");	
		int rowno2=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno2,"Verify the 'See All' button should not displayed in Channels tab  if there are < 8 channels");	
		int rowno3=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno3,"'See All' button should NOT be displayed in Channels tab if the tab contains exact 8 channels.");	
		int rowno4=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno4,"Verify the 'See All' button functionality");	
		int rowno5=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno5,"Verify the navigation on tapping on to any of the channels:");

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


		try {

			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					System.out.println("Watch tab is visibled in tab bar");
					homepagev2.watch_tab.click();
					String text="Channels";
					driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
					Utilities.verticalSwipe(driver);
					BasePageV2.takeScreenshot();
					homepagev2.smokeresults("", rowno, "pass");
					BasePageV2.reportPass("Channels Section is selected and  kids channel should be displayed");

				}else {
					homepagev2.smokeresults("", rowno, "Fail");
					BasePageV2.reportFail("Channels Section doesnotselcted and kids channels not displayed");
				}

				
			try {
				
				launchApp();
				if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
					if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
						homepagev2.watch_tab.click();
						String text="Channels";
						driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
						homepagev2.watchTab_channels.click();
						Thread.sleep(1000);
						int rows=xls.getRowCount("Api");
						for(int rNum=2;rNum<=rows;rNum++)
						{
							String apiname=xls.getCellData("Api", "API Name", rNum);
							if(apiname.equals("Channels"))
							{
								String url_allCharacters=xls.getCellData("Api", "Url", rNum);
								Response resp_allCharacters=Utilities.requestUtility(url_allCharacters);
								totalitemsofapi=resp_allCharacters.jsonPath().get("assets.totalItems");
								//Remaining code as required
								if(homepagev2.watchTab_channels.isSelected() == true && apiname == "Channels" ) {
									if(totalitemsofapi == 0) {
										Utilities.verticalSwipe(driver);
										homepagev2.takeScreenshot();
										test.log(LogStatus.INFO, "Nothing here is Displayed");	
									}else if (totalitemsofapi > 8) {
										String end="//android.widget.Button[@text='SEE ALL']";
										Utilities.verticalSwipe(driver, end);
										if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
										{	
											if(totalitemsofapi > 8)
											{
												homepagev2.smokeresults("",rowno1, "pass");
												BasePageV2.takeScreenshot();
												test.log(LogStatus.PASS, "Verify the availibility of 'See All' button in 'Channels' as the tray has More than 8 channels");
												BasePageV2.reportPass("Virifyied 'Sell All' is there in Channels Section");

												if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
												{
													homepagev2.seeAll.click();
													Thread.sleep(10000);
													BasePageV2.takeScreenshot();
													homepagev2.smokeresults("",rowno4, "pass");
													test.log(LogStatus.PASS, "Verify 'See All' button is tapped in 'Channels' as the tray has More than 8 channels");
													for (int i = 0; i <=10 ; i++) {
														WebElement channelTap=driver.findElement(By.xpath("(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_title'])["+i+"]")); 
														channelTap.click();
														Thread.sleep(10000);
														homepagev2.smokeresults("",rowno5, "pass");
														BasePageV2.reportPass("Verifyied the navigation on tapping on to any of the channels");
														break;
													}

												}


											}

										}


									}else if (totalitemsofapi <= 8) {

										if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
										{	

										}else {
											test.log(LogStatus.PASS, "Verify the availibility of 'See All' button in 'Channels' as the tray has <= 8 channels");
											homepagev2.smokeresults("",rowno2, "pass");
											homepagev2.smokeresults("",rowno3, "pass");
											BasePageV2.takeScreenshot();
											BasePageV2.reportPass("Virifyied 'Sell All' is not there in Channels Section");
										}

									}else {

										homepagev2.smokeresults("",rowno1, "Faill");
										homepagev2.smokeresults("",rowno2, "Faill");
										homepagev2.smokeresults("",rowno3, "Faill");
										homepagev2.smokeresults("",rowno4, "Faill");
										homepagev2.smokeresults("",rowno5, "Faill");
										BasePageV2.reportFail("Channels Section not clicksble or API data is empty");
									}

								}

							}

						}

					}

				}
				
				
			
			} catch (Exception e) {
				BasePageV2.reportFail("Channels Section API not found");
			}

			
		}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}

}

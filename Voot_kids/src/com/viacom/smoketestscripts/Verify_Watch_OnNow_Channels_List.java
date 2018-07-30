package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.or.ThreadGroupRenderer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;

public class Verify_Watch_OnNow_Channels_List extends BaseTestV2 {

	// TODO Auto-generated constructor stub

	String testName = "Watch tab of under OnNow/Channels List Scripts";

	/*
	 * public static int rowno=0; public static int rowno1=0; public static int
	 * rowno2=0; public static int rowno3=0; public static int rowno4=0;
	 */
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify OnNow/Channels List ");
		test.log(LogStatus.INFO, "Starting the test to Verify OnNow/Channels List : " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno,
				"Verify UI of 'On Now' list view page(PageTitle,BackArrow,10 cards Should be Loaded)");
		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1,
				"Verify the scroll functionality of 'On Now' tray list view page");
		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2, "Verify the order of displayed cards in OnNow List");
		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3,
				"Verify UI of 'Channels' list view page(PageTitle,BackArrow,10 cards Should be Loaded)");
		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4,
				"Verify the scroll functionality of 'Channels' tray list view page");
		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5,
				"Verify the order of displayed cards in Channels List");

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		String justforutext = " ", justforutext1 = " ";
		String titleName = "";
		String titles = " ";
		String episode = "";
		String vduration = "";
		ArrayList<String> descFE = new ArrayList<String>();
		ArrayList<String> VdurationFE = new ArrayList<String>();
		ArrayList<String> EpnoFE = new ArrayList<String>();
		// api check
		// calling Config API
		String url_Config = xls.getCellData("Api", 1, 3);
		Response resp_Config = Utilities.requestUtility(url_Config);
		ArrayList<Integer> Listof_OTT = new ArrayList<Integer>();
		resp_Config.then().assertThat().statusCode(200);
		resp_Config.prettyPrint();
		Map<String, Integer> ott = resp_Config.jsonPath().get("assets.OTT");
		for (Map.Entry<String, Integer> m : ott.entrySet())
			Listof_OTT.add(m.getValue());
		// End of calling config API

		// calling just for you API
		String url_justforYou = xls.getCellData("Api", 1, 2);
		Response resp_justforYou = Utilities.requestUtility(url_justforYou);
		// resp_justforYou.then().assertThat().statusCode(200);
		// resp_justforYou.prettyPrint();
		int totalitemsofapi = resp_justforYou.jsonPath().get("assets.totalItems");
		System.out.println(totalitemsofapi);
		ArrayList<String> Listof_descapi = new ArrayList<String>();
		ArrayList<Integer> Listof_mediatypeapi = new ArrayList<Integer>();
		ArrayList<Long> Listof_Vdurationapi = new ArrayList<Long>();
		ArrayList<Integer> Listof_Epnoapi = new ArrayList<Integer>();
		int x = resp_justforYou.jsonPath().get("assets.items.size()");
		System.out.println(x);
		for (int i = 0; i < x; i++) {
			int mediatype = resp_justforYou.jsonPath().get("assets.items[" + i + "].mediaType");
			Listof_mediatypeapi.add(mediatype);
			String descfromapi = resp_justforYou.jsonPath().get("assets.items[" + i + "].desc");
			Listof_descapi.add(descfromapi);
			long Vduration = resp_justforYou.jsonPath().getInt("assets.items[" + i + "].duration");
			long v_duration = TimeUnit.MILLISECONDS.toMinutes(Vduration);
			Listof_Vdurationapi.add(v_duration);
			int Epnomapi = resp_justforYou.jsonPath().get("assets.items[" + i + "].episodeNo");
			Listof_Epnoapi.add(Epnomapi);
			String nullvalforrefSeriesTitle = resp_justforYou.jsonPath().get("assets.items[" + i + "].refSeriesTitle")
					.toString();
			if (nullvalforrefSeriesTitle.equals("null"))
				BasePageV2.reportFail("Reference Title is Missing for the video" + mediatype);
			String nullvalfortitle = resp_justforYou.jsonPath().get("assets.items[" + i + "].title").toString();
			if (nullvalfortitle.equals("null"))
				BasePageV2.reportFail("Title is Missing for the video" + mediatype);
		}
		System.out.println(Listof_descapi.size());
		System.out.println(Listof_mediatypeapi);
		System.out.println(Listof_descapi);
		System.out.println(Listof_Vdurationapi);
		// End of just for you API Check

		try {

			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					System.out.println("Watch tab is visibled in tab bar");
					homepagev2.watch_tab.click();
					String oNowText = "On Now";
					driver.findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
									+ oNowText + "\").instance(0))");
					if (homepagev2.watchtab_text.isSelected()) {
						driver.findElementByAndroidUIAutomator(
								"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
										+ (homepagev2.seeAll.getText()) + "\").instance(0))");
						homepagev2.seeAll.click();
						Thread.sleep(10000);
						driver.findElementByAndroidUIAutomator(
								"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
										+ (homepagev2.watchtab_text.getText()) + "\").instance(0))");
						BasePageV2.takeScreenshot();
						List<WebElement> titlesinsideonNowscetion = driver
								.findElementsById("com.tv.vootkids:id/recent_recycler_view");
						for (int j = 0; j < titlesinsideonNowscetion.size(); j++) {
							titles = titlesinsideonNowscetion.get(j).getText().trim();
							episode = titles.substring(1, 3);
							if (!descFE.contains(titles))
								descFE.add(titles);
						}
						int rows = xls.getRowCount("Api");
						for (int rNum = 2; rNum <= rows; rNum++) {
							String apiname = xls.getCellData("Api", "API Name", rNum);
							if (apiname.equals("On Now")) // Channels
							{
								String url_allCharacters = xls.getCellData("Api", "Url", rNum);
								Response resp_allCharacters = Utilities.requestUtility(url_allCharacters);
								totalitemsofapi = resp_allCharacters.jsonPath().get("assets.totalItems");
								// Remaining code as required
							}
							if (descFE.size() == totalitemsofapi) {
								BasePageV2.takeScreenshot();
								homepagev2.smokeresults("", rowno, "pass");
								BasePageV2.reportPass(
										"onNow Channels Section is selected and  kids channel should be displayed");

							}

						}
					} else if (Utilities.explicitWaitClickable(driver, homepagev2.watchtab_text, 10)) {
						homepagev2.watchtab_text.click();
						driver.findElementByAndroidUIAutomator(
								"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
										+ (homepagev2.seeAll.getText()) + "\").instance(0))");
						homepagev2.seeAll.click();
						Thread.sleep(10000);
						driver.findElementByAndroidUIAutomator(
								"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
										+ (homepagev2.watchtab_text.getText()) + "\").instance(0))");
						BasePageV2.takeScreenshot();
						List<WebElement> titlesinsideonNowscetion = driver
								.findElementsById("com.tv.vootkids:id/recent_recycler_view");
						for (int j = 0; j < titlesinsideonNowscetion.size(); j++) {
							titles = titlesinsideonNowscetion.get(j).getText().trim();
							episode = titles.substring(1, 3);
							if (!descFE.contains(titles))
								descFE.add(titles);
						}
						int rows = xls.getRowCount("Api");
						for (int rNum = 2; rNum <= rows; rNum++) {
							String apiname = xls.getCellData("Api", "API Name", rNum);
							if (apiname.equals("On Now")) // Channels
							{
								String url_allCharacters = xls.getCellData("Api", "Url", rNum);
								Response resp_allCharacters = Utilities.requestUtility(url_allCharacters);
								totalitemsofapi = resp_allCharacters.jsonPath().get("assets.totalItems");
								// Remaining code as required
							}
							if (descFE.size() == totalitemsofapi) {
								BasePageV2.takeScreenshot();
								homepagev2.smokeresults("", rowno, "pass");
								BasePageV2.reportPass(
										"onNow Channels Section is selected and  kids channel should be displayed");

							}

						}

					} else {
						BasePageV2.takeScreenshot();
						homepagev2.smokeresults("", rowno, "Faill");
						BasePageV2.reportPass(
								"Not Navigating to onNow Channels Section and  kids channel should not be displayed");
					}
				}
			}

			launchApp();
			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					driver.findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
									+ (homepagev2.watchTab_channels.getText()) + "\").instance(0))");
					homepagev2.watchTab_channels.click();
					Thread.sleep(10000);
					BasePageV2.takeScreenshot();
					List<WebElement> titlesinsideonNowscetion = driver
							.findElementsById("com.tv.vootkids:id/recent_recycler_view");
					List<WebElement> titlesinsideonchannelsscetion = driver
							.findElementsById("com.tv.vootkids:id/recent_recycler_view");
					for (int j = 0; j < titlesinsideonchannelsscetion.size(); j++) {
						titles = titlesinsideonchannelsscetion.get(j).getText().trim();
						episode = titles.substring(1, 3);
						if (!descFE.contains(titles))
							descFE.add(titles);
					}
					int rows = xls.getRowCount("Api");
					for (int rNum = 2; rNum <= rows; rNum++) {
						String apiname = xls.getCellData("Api", "API Name", rNum);
						if (apiname.equals("Channels")) // Channels
						{
							String url_allCharacters = xls.getCellData("Api", "Url", rNum);
							Response resp_allCharacters = Utilities.requestUtility(url_allCharacters);
							totalitemsofapi = resp_allCharacters.jsonPath().get("assets.totalItems");
							// Remaining code as required
						}
						if (descFE.size() == totalitemsofapi) {
							driver.findElementByAndroidUIAutomator(
									"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
											+ (homepagev2.seeAll.getText()) + "\").instance(0))");
							if (Utilities.explicitWaitVisible(driver, homepagev2.Chennels_Back_btn, 5)) {
								if (Utilities.explicitWaitClickable(driver, homepagev2.Chennels_Back_btn, 5)) {
									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno3, "pass");
									BasePageV2.reportPass(
											"Channels Section is selected and  kids channel should be displayed");
								}
							}

						}

					}

				}

			} else {

				BasePageV2.takeScreenshot();
				homepagev2.smokeresults("", rowno3, "Faill");
				BasePageV2.reportPass("Channels Section is not navigated  and  kids channel should not be displayed");

			}

			launchApp();

			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					driver.findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
									+ (homepagev2.watchTab_channels.getText()) + "\").instance(0))");
					homepagev2.watchTab_channels.click();
					Thread.sleep(10000);
					int rows = xls.getRowCount("Api");
					for (int rNum = 2; rNum <= rows; rNum++) {
						String apiname = xls.getCellData("Api", "API Name", rNum);
						if (apiname.equals("Channels")) {
							String url_allCharacters = xls.getCellData("Api", "Url", rNum);
							Response resp_allCharacters = Utilities.requestUtility(url_allCharacters);
							totalitemsofapi = resp_allCharacters.jsonPath().get("assets.totalItems");
							if (totalitemsofapi > 8) {
								driver.findElementByAndroidUIAutomator(
										"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
												+ (homepagev2.seeAll.getText()) + "\").instance(0))");
								homepagev2.seeAll.click();
								Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
								if (Utilities.explicitWaitVisible(driver, homepagev2.channels_characters_CHANNELS_Text,
										10)) {
									test.log(LogStatus.PASS, "Verified CHANNELS Page having channels list ");
									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno4, "Pass");
									BasePageV2.reportPass("Channels Section is not navigated to CHANNELS List page");

								}
								ArrayList<String> charTitle = new ArrayList<String>();
								for (int i = 1; i < 10; i++) {
									try {

										WebElement cc = driver.findElement(By.xpath(
												"(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_title'])["
														+ i + "]"));
										titles = cc.getText().trim();
										episode = titles.substring(1, 3);
										if (!charTitle.contains(titles))
											charTitle.add(titles);

									} catch (Exception e) {
										e.printStackTrace();
									}

								}

								if (charTitle.size() == totalitemsofapi) {
									test.log(LogStatus.PASS, "Verified CHANNELS Page cards displayied order ");
									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno5, "Pass");

								} else {

									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno5, "Faill");
									BasePageV2.reportFail("CHANNELS cards not found");
								}
							}
						}
					}
				} else {
					homepagev2.smokeresults("", rowno4, "Faill");
					BasePageV2.reportFail("CHANNELS List page not found as well Watch Tab also");
				}

			}

		} catch (Exception e) {
			homepagev2.smokeresults("", rowno, "Faill");
			homepagev2.smokeresults("", rowno3, "Faill");
			homepagev2.smokeresults("", rowno4, "Faill");
			homepagev2.smokeresults("", rowno5, "Faill");
			BasePageV2.reportFail("Watch tab icon not found in home page");

		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}

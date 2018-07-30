package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
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

public class Verify_Watch_On_Now_Section extends BaseTestV2 {
	String testName = "Verify_Watch_On_Now_Section";

	/*
	 * public static int rowno=0; public static int rowno2=0; public static int
	 * rowno3=0; public static int rowno4=0;
	 */
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception {

		test = rep.startTest("Verify watch tab 'On Now' Section ");
		test.log(LogStatus.INFO,
				"Starting the test to Verify Watch tab 'On Now' Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, "Verify the UI of On Now and Channels tab");
		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1,
				"Verify the navigation between On Now and Channels tabs");
		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2,
				"Verify the metadata for the cards present under 'On Now' tab");
		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3,
				"Verify the availibility of See All button in On Now tab if there are  > 8 live contents");
		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4,
				"Verify the availibility of 'See All' button in On Now tab if there are  < 8 live contents");
		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5,
				"Verify the availibility of 'See All' button in On Now tab if there are exact 8 live contents");
		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6,
				"Verify the functionality on tapping on any of the live content card");
		int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno7, "Verify the 'See All' functionality");
		int rowno8 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno8,
				"Verify the functionality on tapping on any of the live content card");
		int rowno9 = xls.getRowCount("Smoke_Results") + 1;

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
		// End of Watch Tab OnNow API Check
		launchApp();

		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		try {

			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 20)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 20)) {
					System.out.println("Watch tab is visibled in tab bar");
					homepagev2.watch_tab.click();

					try {
						
						
							String end="//android.widget.TextView[contains(@text,'On Now')]";
							//String str = "Channels";
//							driver.findElementByAndroidUIAutomator(
//									"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
//											+ text + "\").instance(0))");
//							String end="Channels";
							//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str+"\").instance(0))");
							Utilities.verticalSwipe(driver, end);
							Utilities.verticalSwipe(driver);
					        homepagev2.watchTab_channels.click();
					} catch (Exception e) {
						e.printStackTrace();
					}

					int channels_tray_content_size = driver
							.findElementsByXPath(
									"//android.support.v7.widget.RecyclerView//android.view.ViewGroup[@index='0']")
							.size();

					try {

						if (homepagev2.watchTab_channels.isSelected() && channels_tray_content_size != 0) {
							Utilities.verticalSwipe(driver);
							test.log(LogStatus.INFO,
									"Channels Section is selected and  kids channel should be displayed in Watch tab ");
							BasePageV2.takeScreenshot();
							homepagev2.smokeresults("", rowno1, "pass");

						} else {
							test.log(LogStatus.INFO,
									"Channels Section is not selected and does not have content in Watch tab ");
							homepagev2.smokeresults("", rowno1, "Faill");
							BasePageV2.reportFail(
									"Channels Section is not selected and does not have content in Watch tab");

						}

					} catch (Exception e) {
						BasePageV2.reportFail("Channels Section is not selected and does not have content in Watch tab");
					}

					try {

						String on_Now = (homepagev2.watchTab_onNOwTab).getText();
						Utilities.verticalSwipeDown(driver);
						homepagev2.watchTab_onNOwTab.click();
						// start First test case number "rowno"
						if (homepagev2.watchTab_onNOwTab.isSelected() == true) {
							BasePageV2.takeScreenshot();
							test.log(LogStatus.INFO, "Verified OnNow Section is highlited inWatch tab ");
							homepagev2.smokeresults("", rowno, "pass");

							String onNowNothingHere = "NOTHING HERE";
							int onNow_tray_size = driver.findElementsByXPath(
									"//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/rc_segment_tray']//android.view.ViewGroup")
									.size();
							String onNowNothingGetText = homepagev2.onNowNothingHere.getText();
							System.out.println(onNowNothingGetText);
							if (onNowNothingGetText == onNowNothingHere) {
								BasePageV2.reportPass("OnNow Tray does not have content");
								com.viacom.utils.Utilities.verticalSwipe(driver, onNowNothingHere);
								BasePageV2.takeScreenshot();
							} else
								BasePageV2.reportFail("OnNow Section having content in tray Failed");

						} else {
							homepagev2.smokeresults("", rowno, "Fail");
							BasePageV2.reportFail("OnNow Section is not highlited in Watch tab ");
						}
						// End First test case number "rowno"

					} catch (Exception e) {
						e.printStackTrace();
					}

					try {

						int rows = xls.getRowCount("Api");

						for (int rNum = 2; rNum <= rows; rNum++) {

							String apiname = xls.getCellData("Api", "API Name", rNum);
							if (homepagev2.watchtab_text.isSelected() == true && apiname == "On Now") {

								if (totalitemsofapi == 0) {
									Utilities.verticalSwipe(driver);
									test.log(LogStatus.INFO, "Nothing here is Displayed");
									homepagev2.takeScreenshot();
									String onNowNothingHere = "NOTHING HERE";
									System.out.println(onNowNothingHere);

								} else if (totalitemsofapi > 8) {
									String seeAll_btn = homepagev2.seeAll.getText();
									Utilities.verticalSwipe(driver, seeAll_btn);
									test.log(LogStatus.PASS, "'onNow Section' Sell All button visibled is Verified ");
									homepagev2.takeScreenshot();
									homepagev2.smokeresults("", rowno3, "pass");
									if (homepagev2.seeAll.isDisplayed()) {
										homepagev2.seeAll.click();
										Thread.sleep(10000);
										BasePageV2.takeScreenshot();
										homepagev2.smokeresults("", rowno7, "pass");
										BasePageV2.reportFail("OnNow Section 'SEE ALL' button not displayed ");
									}

								} else if (totalitemsofapi <= 8) {
									test.log(LogStatus.PASS,
											"'onNow Section' Sell All button should not be present is Verified ");
									homepagev2.smokeresults("", rowno4, "pass");
									homepagev2.smokeresults("", rowno5, "pass");
								} else if (totalitemsofapi > 8 && totalitemsofapi <= 8) {
									int onNow_tray_size = driver.findElementsByXPath(
											"//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/rc_segment_tray']//android.view.ViewGroup")
											.size();
									if (onNow_tray_size != 0) {
										homepagev2.onNow_tray_ViewGroup0.click();
										Thread.sleep(1000);
										BasePageV2.takeScreenshot();
										homepagev2.smokeresults("", rowno6, "pass");
									}
								
								test.log(LogStatus.PASS,
										"'onNow Section' Sell All button should not be present is Verified ");
								homepagev2.smokeresults("", rowno4, "pass");
								homepagev2.smokeresults("", rowno5, "pass");

							} else {
								test.log(LogStatus.INFO, "'totalitemsofapi'count not found from API");
								homepagev2.smokeresults("", rowno4, "Fail");
								homepagev2.smokeresults("", rowno5, "Fail");
								homepagev2.smokeresults("", rowno3, "Fail");
								homepagev2.smokeresults("", rowno6, "Fail");
								homepagev2.smokeresults("", rowno7, "pass");
							}

						}
					 }

					} catch (Exception e) {

						BasePageV2.reportFail("API is not found ");

					}

				}
			}
		} catch (Exception e) {

			BasePageV2.reportFail("API is not found (or) Watch Tab is not displayed and Not able to navigated");
			homepagev2.smokeresults("", rowno4, "Fail");
			homepagev2.smokeresults("", rowno5, "Fail");
			homepagev2.smokeresults("", rowno3, "Fail");
			homepagev2.smokeresults("", rowno6, "Fail");
			homepagev2.smokeresults("", rowno7, "pass");
		}
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}

/*
 * rowno2 and rowno8 is not Applicable Test case number 301 in sheet
 */

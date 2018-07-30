package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

public class Verify_Watch_Channel_Info extends BaseTestV2 {

	String testName = "Verify_Watch_Channel_Info";

	/*
	 * public static int rowno=0; public static int rowno1=0; public static int
	 * rowno2=0; public static int rowno3=0; public static int rowno4=0;
	 */
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Watch_Channel Info ");
		test.log(LogStatus.INFO, "Starting the test to Verify Watch_Channel Info: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno,
				"Verify Back arrow functionality in Channel Info page");
		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1,
				"Verify the scrolling functionality for list of channels in Channel Info page");
		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2,
				"Verify the metadata displayed for Live content/Channels list");
		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3,
				"Verify the Play button functionality for live content");
		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4,
				"Verfiy the metadata for the live content post completion of present live content");
		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5,
				"Verify the segmented tabs available in Channel Info page below the live content section");
		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6,
				"Verfiy the UI of Characters tab when the tab has less than 8 cards:");
		int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno7,
				"Verfiy the navigation on tapping on any of the kids character.");
		int rowno8 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno8,
				"Verify the functionality on switching to Schedule tab.");
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
				if (Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
					homepagev2.watch_tab.click();
					Thread.sleep(10000);
					if (Utilities.explicitWaitVisible(driver, homepagev2.watchTab_channels, 10)) {
						if (Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
							homepagev2.watchTab_channels.click();
							if (Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10)) {
								homepagev2.seeAll.click();
								if (Utilities.explicitWaitClickable(driver, homepagev2.Chennels_Back_btn, 10)) {
									BasePageV2.takeScreenshot();
									homepagev2.Chennels_Back_btn.click();
									if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
										homepagev2.smokeresults("", rowno, "Pass");
										BasePageV2
												.reportPass("Vrifyied Back arrow functionality in Channel Info page ");
									} else {
										homepagev2.smokeresults("", rowno, "Faill");
										BasePageV2.reportPass(" Back arrow is not found in Channel Info page ");
									}
								}
							}

						}

					} else {
						homepagev2.smokeresults("", rowno, "Faill");
						BasePageV2.reportPass(" Channel Info page not found ");

					}
				}

			}

			launchApp();
			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
					homepagev2.watch_tab.click();
					Thread.sleep(10000);
					if (Utilities.explicitWaitVisible(driver, homepagev2.watchTab_channels, 10)) {
						if (Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
							homepagev2.watchTab_channels.click();
                            Thread.sleep(10000);
                            homepagev2.channels_characters_kids_card.click();
							if (Utilities.explicitWaitVisible(driver, homepagev2.channels_characters, 10)) {
								if (homepagev2.channels_characters.isSelected()
										&& homepagev2.channels_schedule.isDisplayed()) {
									homepagev2.smokeresults("", rowno5, "Pass");
									BasePageV2.takeScreenshot();
									BasePageV2.reportPass(" Verified Character - should be highligted by default ");

									// API verification started
									int rows = xls.getRowCount("Api");
									for (int rNum = 2; rNum <= rows; rNum++) {
										String apiname = xls.getCellData("Api", "API Name", rNum);
										if (apiname.equals("Characters")) {
											String url_allCharacters = xls.getCellData("Api", "Url", rNum);
											Response resp_allCharacters = Utilities.requestUtility(url_allCharacters);
											totalitemsofapi = resp_allCharacters.jsonPath().get("assets.totalItems");
											// Remaining code as required
											if (totalitemsofapi < 8) {
												homepagev2.takeScreenshot();
												test.log(LogStatus.PASS, "Characters Tab having > 8 cards Verified ");
												homepagev2.smokeresults("", rowno6, "pass");
												homepagev2.channels_characters_kids_card.click();
												Thread.sleep(10000);
												test.log(LogStatus.PASS, "Characters Tab kid charcter card tapped");
												BasePageV2.takeScreenshot();
												homepagev2.smokeresults("", rowno7, "pass");
												

											} else {
												homepagev2.smokeresults("", rowno6, "Faill");
												homepagev2.smokeresults("", rowno7, "Faill");
												BasePageV2.reportFail("Characters tab does not have cards");
											}

										}
									}
								} else {
									homepagev2.smokeresults("", rowno5, "Faill");
									homepagev2.smokeresults("", rowno6, "Faill");
									homepagev2.smokeresults("", rowno7, "Faill");
									BasePageV2.reportPass(
											" In channels section Character & Schedule options not found & does not have characters cards in Characters Tab");
								}
							}

						}
					}
				}
			} else {
				homepagev2.smokeresults("", rowno5, "Faill");
				BasePageV2.reportPass(" Watch Tap option is not found ");
			}

			launchApp();

			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
					homepagev2.watch_tab.click();
					Thread.sleep(10000);
					if (Utilities.explicitWaitVisible(driver, homepagev2.watchTab_channels, 10)) {
						if (Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
							homepagev2.watchTab_channels.click();
                            Thread.sleep(10000);
							homepagev2.channels_characters_kids_card.click();
							if (Utilities.explicitWaitVisible(driver, homepagev2.channels_schedule, 10)) {
								if (Utilities.explicitWaitClickable(driver, homepagev2.channels_schedule, 10)) {
									homepagev2.channels_schedule.click();
									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno8, "Pass");
									BasePageV2.reportPass(" Virified Schedule tab tapped and  hightlited ");
								} else {
									homepagev2.smokeresults("", rowno8, "Faill");
									BasePageV2.reportFail("Schedule tap is not found in channels section");
								}
							}

						}
					}
				}
			} else {
				homepagev2.smokeresults("", rowno8, "Faill");
				BasePageV2.reportFail("Schedule tap is not found in channels section");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}

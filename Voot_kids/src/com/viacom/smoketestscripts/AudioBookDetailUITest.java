package com.viacom.smoketestscripts;

import java.util.Hashtable;
import java.util.List;

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
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class AudioBookDetailUITest extends BaseTestV2
{
	String testName = "AudioBookDetailUITest";
	String testCase = "'Verify the navigation when tapping on cards'";
	String testCase1 = "'Verify the UI of Audio book detail page UI'";
	String testCase2 = "'Verify Play button functionality'";
	String testCase3 = "'Verify Download icon UI post complete download'";
	String testCase4 = "'Verify the type of cards to be displayed under 'Related/You May Also Like' tray'";
	String testCase5 = "'Verify the Episodes section UI whe the tray has <=8 card'";

	@Test(dataProvider = "getData")
	public void audioBookUI(Hashtable<String, String> data) throws Exception
	{

		if (GlobalVariables.break_Flag) throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Audio Book detail page Functionality");
		test.log(LogStatus.INFO, "Starting the test for 'Sign Up' Functionality: " + VootConstants.DEVICE_NAME);

		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N"))
		{
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);

		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase);

		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1, testCase1);

		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2, testCase2);

		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3, testCase3);

		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4, testCase4);

		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5, testCase4);

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		//Validating Test Case (testCase) 
		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5))
			test.log(LogStatus.INFO, "Home Page is displayed");
		else homepagev2.signup();

		try
		{
			homepagev2.listen.click();
		} catch (Exception e)
		{
			BasePageV2.reportFail("Listen icon tab not displayed");
		}
		// 3. Tap on any Audio book tile present on the screen

		try

		{
			// Utilities.verticalSwipe(driver);
			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.audioBook, 2))
				{
					homepagev2.audioBook.click();
					break;
				}
				else
				{
					Utilities.verticalSwipe(driver);
				}
			}
		} catch (Exception e)
		{
			BasePageV2.reportFail("Audio book not displayed");

		}
		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 5))
			if (homepagev2.playAudiobookbutton.isDisplayed())
			{
				test.log(LogStatus.INFO, "Audio Book Detail Page is displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
				homepagev2.smokeresults(testCase, rowno, "Pass");

				try
				{
					homepagev2.playAudiobookbutton.click();
					if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 10))
					{
						test.log(LogStatus.INFO, "Audio Player is displayed");
						BasePageV2.takeScreenshot();
						test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is pass");
						homepagev2.smokeresults(testCase2, rowno2, "Pass");
						driver.pressKeyCode(AndroidKeyCode.BACK);
					}
					else BasePageV2.reportFail("Audio Player not displayed");

				} catch (Exception e)
				{
					BasePageV2.reportFail("Play Audio Button in audio book detail page is not displayed");
				}

			}
			else BasePageV2.reportFail("Audio Book detail page is not displayed");
		else BasePageV2.reportFail("Audio Book detail page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.backButton, 3))
			test.log(LogStatus.INFO, "Back button displayed in Audio Book detail page");
		else BasePageV2.reportFail("Back button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 3))
			test.log(LogStatus.INFO,
					"'Audio Book Title' is displayed in Audio Book detail page" + homepagev2.audTitle.getText());
		else BasePageV2.reportFail("'Audio Book Title' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.favIcon, 3))
			test.log(LogStatus.INFO, "'Favourite icon' is displayed in Audio Book detail page");
		else BasePageV2.reportFail("'Favourite icon' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 3))
			test.log(LogStatus.INFO, "'Play Audio' button is displayed in Audio Book detail page");
		else BasePageV2.reportFail("'Play Audio' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audauthorContainer, 3))
			test.log(LogStatus.INFO, "'Author container' displayed in Audio Book detail page");
		else BasePageV2.reportFail("'Author container' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audauthorname, 3))
			test.log(LogStatus.INFO,
					"'Author name' displayed in Audio Book detail page is: " + homepagev2.audauthorname.getText());
		else BasePageV2.reportFail("'Author name' is not displayed");

		try
		{
			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.naratorContainer, 2))
					break;
				else Utilities.verticalSwipe(driver);
			}

		} catch (Exception e)
		{
			BasePageV2.reportFail("Download icon not displayed");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.naratorContainer, 3))
			test.log(LogStatus.INFO, "'Narrator container' displayed in Audio Book detail page");
		else BasePageV2.reportFail("'Narrator container' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.naratorname, 3))
			test.log(LogStatus.INFO,
					"'Narrated by' displayed in Audio Book detail page is: " + homepagev2.naratorname.getText());
		else BasePageV2.reportFail("'Narrated by' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.description, 3))
			test.log(LogStatus.INFO, "'Audio Book description' is displayed in Audio Book detail page is: "
					+ homepagev2.description.getText());
		else BasePageV2.reportFail("'Audio Book description' is not displayed");

		try
		{
			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.downloadIcon, 2))
					break;
				else Utilities.verticalSwipe(driver);
			}

		} catch (Exception e)
		{
			BasePageV2.reportFail("Download icon not displayed");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadAudioBookText, 3))
			test.log(LogStatus.INFO,
					"Download Audio Book Text is displayed: " + homepagev2.downloadAudioBookText.getText());
		else BasePageV2.reportFail("'Download Audio Book' text is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.duration, 3))
			test.log(LogStatus.INFO, "Audio duration displayed is " + homepagev2.duration.getText());
		else BasePageV2.reportFail("'Audio duration' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadIcon, 3))
			test.log(LogStatus.INFO, "'Download icon' is displayed in Audio Book detail page");
		else BasePageV2.reportFail("'Download icon' is not displayed");

		try
		{
			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.recentTypeTab, 2))
					break;
				else Utilities.verticalSwipe(driver);
			}

		} catch (Exception e)
		{
			BasePageV2.reportFail("Recent types not");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.recentTypeTab, 3))
			test.log(LogStatus.INFO, "Recent types tab displayed is: " + homepagev2.recentTypeTab.getText());
		else BasePageV2.reportFail("'Recent types tab' is not displayed");

		BasePageV2.takeScreenshot();
		test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
		homepagev2.smokeresults(testCase1, rowno1, "Pass");

		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadIcon, 3))
			homepagev2.downloadIcon.click();
		else BasePageV2.reportFail("Download Icon is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.midqualityDownload, 3))
			homepagev2.midqualityDownload.click();
		else BasePageV2.reportFail("Download quality selection options not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.allowDownload, 3))
			homepagev2.allowDownload.click();
		else BasePageV2.reportFail("Download confirmation not displayed");

		try
		{
			next:
			for (int i = 1; i <= 100; i++)
			{
				Thread.sleep(1500);
				if (Utilities.explicitWaitVisible(driver, homepagev2.downloadedItem, 5))
					if (homepagev2.downloadedItem.isDisplayed())
					{
					test.log(LogStatus.INFO, "Content downloaded successfully");
					test.log(LogStatus.INFO, "Download Status displayed as: " + homepagev2.DownloadStatus.getText());
					BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is passed");
					homepagev2.smokeresults(testCase3, rowno3, "Pass");
					break;
					}
					else continue next;
			}

		} catch (Exception e)
		{
			BasePageV2.reportFail("Failed to download Audio Book");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.recentTypeTab, 5))
		{
			test.log(LogStatus.INFO, "Displayed tab is: " + homepagev2.recentTypeTab.getText());
			for (int i = 0; i <= 2; i++)
			{
				Utilities.verticalSwipe(driver);
			}
		}
		else BasePageV2.reportFail("Recent types tabs not displayed");

		//Validating Test Case testCase4
		String url = "http://apistg.vootkids.com/app/recommendation/v1/getRecommendations.json?useCaseId=UCAPPEAUDIO&method=audioRelated&uId=471a76d3bf1241b787895f0828da7269&profileId=5b581c196d8d1912c0c719be&limit=10&offSet=0";
		Response rskids = Utilities.requestUtility(url);
		int totalcards = rskids.jsonPath().get("assets.totalItems");
		List<WebElement> audioContents = driver.findElements(By.id("com.tv.vootkids:id/recent_recycler_view"));
		int numberofAudioCards = audioContents.size();
		for (int i = 0; i < totalcards; i++)
		{
			WebElement element = driver.findElement(By.xpath("//android.view.ViewGroup[@index='" + i + "']"));

			if (Utilities.explicitWaitVisible(driver, element, 3))
			{

				String contentType = rskids.jsonPath().get("assets.items[" + i + "].contentType");
				test.log(LogStatus.INFO, "Content type of Content: " + (i + 1) + " is: " + contentType);
				if (contentType.contains("Audio"))
				{
					test.log(LogStatus.INFO, "Card displayed is related to audio");
				}
				else BasePageV2.reportFail("Audio Card is not displayed under: " + homepagev2.recentTypeTab.getText()
						+ " tab in audio book detail page");
			}
			else BasePageV2.reportFail("Audio Card: " + (i + 1) + " is not displayed");
		}
		BasePageV2.takeScreenshot();
		test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Pass");
		homepagev2.smokeresults(testCase4, rowno4, "Pass");

		//Validating Test Case testCase5
		test.log(LogStatus.INFO, "Total Number of contents displayed under tray: " + homepagev2.recentTypeTab.getText()
				+ " is " + totalcards);
		if (totalcards <= 8)
		{
			try
			{
				if (homepagev2.traySeeAll.isDisplayed())
				{
					test.log(LogStatus.INFO, "'See All' Button is displayed for tray: '"
							+ homepagev2.recentTypeTab.getText() + "' which contains content count <=8");
					BasePageV2.takeScreenshot();
					HomePageV2.smokeresults(testCase5, rowno5, "Fail");
					test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is fail");
				}
				else
				{
					test.log(LogStatus.INFO, "'See All' Button is not displayed for tray: '"
							+ homepagev2.recentTypeTab.getText() + "' which contains content count <=8");
					BasePageV2.takeScreenshot();
					HomePageV2.smokeresults(testCase5, rowno5, "Pass");
					test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Pass");
				}

			} catch (Exception e)
			{
				test.log(LogStatus.INFO, "'See All' Button is not displayed for tray: '"
						+ homepagev2.recentTypeTab.getText() + "' which contains content count <=8");
				BasePageV2.takeScreenshot();
				HomePageV2.smokeresults(testCase5, rowno5, "Pass");
				test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Pass");
			}
		}
		else test.log(LogStatus.INFO,
				"Number of contents present under the tray: " + homepagev2.recentTypeTab.getText() + " is > 8");
	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(testName, xls);

	}
}

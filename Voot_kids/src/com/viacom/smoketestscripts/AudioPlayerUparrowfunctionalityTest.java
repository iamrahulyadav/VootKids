package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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

@Test(dataProvider = "getData")
public class AudioPlayerUparrowfunctionalityTest extends BaseTestV2
{
	String testName = "AudioPlayerUparrowfunctionalityTest";
	String testCase = "'Verify the Up arrow functionality'";
	String testCase1 = "'Verify metadata for the cards present under Related tray'";
	String testCase2 = "'Verify the metadata displayed for cards present under Daily Picks section'";
	String testCase3 = "'Verify the displayed screen post completion of current audio playback'";
	String contentTitle = "";
	String testCase4 = "'Verify 'Listen Again' button functionality in 'You Might Also Like' screen'";
	String testCase5 = "'Verify 'Close' button functionality in 'You Might Also Like' screen'";
	String testCase6 = "'Verify the UI of Related section (Audio player)";

	/*
	 * String testCase1 =
	 * "'Verify the functionality of device back button in audio player'"; String
	 * testCase2 = "'To verify the UI of inline audio player'";
	 */

	@SuppressWarnings(
	{ "null", "unlikely-arg-type" })
	public void inlineAudioFunction(Hashtable<String, String> data) throws Exception
	{
		if (GlobalVariables.break_Flag) throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating 'Audio Player Up Arrow' Functionality");
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
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5, testCase5);

		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6, testCase6);

		/*
		 * int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		 * xls.setCellData("Smoke_Results", "Testcase Name", rowno1, testCase1);
		 * 
		 * int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		 * xls.setCellData("Smoke_Results", "Testcase Name", rowno2, testCase2);
		 */
		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		try
		{
			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
			{
				test.log(LogStatus.INFO, "Home Page is displayed");
			}
			else
			{
				homepagev2.signup();
			}
		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Unable to navigate to Home Page");
		}

		// 2. Tap on Listen icon on top bar menu
		try
		{
			homepagev2.listen.click();
		}
		catch (Exception e)
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
		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Audio book not displayed");

		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 10))
		{
			homepagev2.playAudiobookbutton.click();
		}
		else BasePageV2.reportFail("Play button is not displayed in audio detail page");

		try
		{
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 10))
				contentTitle = homepagev2.audioTitle.getText();
			else BasePageV2.reportFail("Audio Title Not found");

		}
		catch (Exception e)
		{
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10))
		{
			test.log(LogStatus.INFO,
					" 'Audio playlist expander (up arrow)' switch button is displayed and clicking on it");
			BasePageV2.takeScreenshot();
			homepagev2.audioplaylistExpand.click();
		}
		else BasePageV2.reportFail(" 'Audio playlist expander (up arrow)' switch button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistCollapse, 10))
		{
			if (homepagev2.audioplaylistCollapse.isDisplayed() && homepagev2.related.isDisplayed()
					&& homepagev2.morefromauthor.isDisplayed() && homepagev2.dailypicks.isDisplayed())
			{
				test.log(LogStatus.INFO, "'Audio playlist collapser (Down arrow)' is displayed");
				test.log(LogStatus.INFO, "'Related tab' is displayed");
				test.log(LogStatus.INFO, "'Daily pics tab' is displayed");
				test.log(LogStatus.INFO, "'More From Author tab' is displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is passed");
				homepagev2.smokeresults(testCase, rowno, "Pass");
			}
			else BasePageV2.reportFail("Tabs not displayed according to the design when tapping on 'Up Arrow' button");

		}
		else BasePageV2.reportFail(" 'Audio playlist collapser (Down arrow)' switch button is not displayed");

		if (Utilities.explicitWaitClickable(driver, homepagev2.related, 5))
			homepagev2.related.click();
		else BasePageV2.reportFail("Related tab not displayed");

		List<WebElement> elems = driver.findElementsByClassName("android.support.v7.widget.RecyclerView");

		int j = elems.size();
		test.log(LogStatus.INFO, "number of elements: " + j);

		// int j = elems.size();
		// int count = 1;

		try
		{
			for (WebElement elem : elems)
			{

				if (Utilities.explicitWaitVisible(driver, elem, 1))
				{
					String playlistcontentTitle = homepagev2.plalistcontentTitle.getText();
					String playlistAuthor = homepagev2.plalistcontentAuthor.getText();

					test.log(LogStatus.INFO, "Validating meta data of 'Audio Card' present under 'Related Tab' ");
					test.log(LogStatus.INFO, "Title of 'Audio Book': " + playlistcontentTitle);
					test.log(LogStatus.INFO, "Title of 'Author of Audio Book': " + playlistAuthor);
					if (homepagev2.categoryicon.isDisplayed())
						test.log(LogStatus.INFO, "Category Icon displayed");
					else BasePageV2.reportFail("Category icon not displayed");

				}
				else
				{
					test.log(LogStatus.INFO, "Mata data validation done for audio cards present under 'Related Tab' ");
					break;
				}

			}

			BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is passed");
			homepagev2.smokeresults(testCase1, rowno1, "Pass");
		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Audio Cards not displayed");
		}

		try
		{

			ArrayList<String> audioTitle = new ArrayList<String>();
			ArrayList<String> audioAuthor = new ArrayList<String>();
			ArrayList<String> audioTitleapi = new ArrayList<String>();
			ArrayList<String> audioAuthorapi = new ArrayList<String>();
			String url = "http://apistg.vootkids.com/app/recommendation/v1/getRecommendations.json?useCaseId=UCAPPEAUDIO&method=audioRelated&uId=471a76d3bf1241b787895f0828da7269&profileId=5b581c196d8d1912c0c719be&limit=10&offSet=0";
			Response rskids = Utilities.requestUtility(url);
			int totalCards = rskids.jsonPath().get("assets.totalItems");
			System.out.println(totalCards);
			for (int i = 0; i < totalCards; i++)
			{
				String songAuthor = rskids.jsonPath().get("assets.items[" + i + "].author");
				String songTitle = rskids.jsonPath().get("assets.items[" + i + "].title");
				audioTitleapi.add(songTitle);
				audioAuthorapi.add(songAuthor);
			}
			System.out.println(audioTitleapi);
			System.out.println(audioAuthorapi);

			List<WebElement> elems1 = driver.findElementsByClassName("android.support.v7.widget.RecyclerView");
			for (WebElement elem1 : elems1)
			{

				if (Utilities.explicitWaitVisible(driver, elem1, 1))
				{
					String playlistcontentTitle = homepagev2.plalistcontentTitle.getText();
					String playlistAuthor = homepagev2.plalistcontentAuthor.getText();
					audioTitle.add(playlistcontentTitle);
					audioAuthor.add(playlistAuthor);
				}
				else
				{
					test.log(LogStatus.INFO, "Mata data validation done for audio cards present under 'Related Tab' ");
					break;
				}

			}
			System.out.println(audioTitle);
			if (audioTitle.contains(audioTitleapi))
			{
				test.log(LogStatus.INFO, "Audio Book Titles are matching");
			}
			else
			{
				test.log(LogStatus.INFO, "Audio Book Titles are not matching");
				BasePageV2.takeScreenshot();
			}
			if (audioAuthor.contains(audioAuthorapi))
			{
				test.log(LogStatus.INFO, "Audio Book Authors are matching");

				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is Passed");
				homepagev2.smokeresults(testCase6, rowno6, "Pass");

			}
			else
			{
				test.log(LogStatus.INFO, "Audio Book Authors are not matching");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
				homepagev2.smokeresults(testCase6, rowno6, "Fail");
			}
		}
		catch (Exception e) { }
		
		try
		{
			homepagev2.dailypicks.click();
		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Daily Pics tab not displayed");
		}

		List<WebElement> elems1 = driver.findElementsByClassName("android.support.v7.widget.RecyclerView");
		int k = elems1.size();
		test.log(LogStatus.INFO, "number of elements: " + j);
		// int j = elems.size();
		// int count = 1;
		try
		{
			for (WebElement elem : elems1)
			{
				if (Utilities.explicitWaitVisible(driver, elem, 1))
				{
					test.log(LogStatus.INFO, "Validating meta data of 'Audio Card' present under 'Daily Pics' tab");
					test.log(LogStatus.INFO, "Title of 'Audio Book': " + homepagev2.plalistcontentTitle.getText());
					test.log(LogStatus.INFO,
							"Title of 'Author of Audio Book': " + homepagev2.plalistcontentAuthor.getText());
					if (homepagev2.categoryicon.isDisplayed())
						test.log(LogStatus.INFO, "Category Icon displayed");
					else BasePageV2.reportFail("Category icon not displayed");
				}
				else
				{
					test.log(LogStatus.INFO, "Mata data validation done for audio cards present under 'Daily Pics' ");
					break;
				}

			}
			BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is passed");
			homepagev2.smokeresults(testCase2, rowno2, "Pass");
		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Audio Cards not displayed");
		}

		try
		{
			homepagev2.audioplaylistCollapse.click();
		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Audio Playlist Collapser 'Down Arrow' not displayed");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 5))
		{
			Utilities.scrubtillend(driver, homepagev2.audioseekBar);

		}
		else BasePageV2.reportFail("Unable to scrub the audio");

		if (Utilities.explicitWaitVisible(driver, homepagev2.listenAgainBtn, 25))
		{
			if (homepagev2.audioplayerClose.isDisplayed())
				test.log(LogStatus.INFO, "'Close Button' is displayed when scrubbed to end of audio");
			else BasePageV2.reportFail("Close Button is not displayed when scrubbed to end of audio");

			if (homepagev2.youmayalsoLike.isDisplayed())
				test.log(LogStatus.INFO, "'You might also like..' text is displayed when scrubbed to end of audio");
			else BasePageV2.reportFail("'You might also like..' text is not displayed when scrubbed to end of audio");

			if (homepagev2.AudioOverlay.isDisplayed())
				test.log(LogStatus.INFO, "'Audo Overlay' is displayed when scrubbed to end of audio");
			else BasePageV2.reportFail("Audo Overlay' is not displayed when scrubbed to end of audio");

			if (homepagev2.listenAgainBtn.isDisplayed())
				test.log(LogStatus.INFO, "'Listen Again Button' is displayed when scrubbed to end of audio");
			else BasePageV2.reportFail("'Listen Again Button' is not displayed when scrubbed to end of audio");

			BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Passed");
			homepagev2.smokeresults(testCase3, rowno3, "Pass");

			if (homepagev2.listenAgainBtn.isDisplayed())
				homepagev2.listenAgainBtn.click();
			else BasePageV2.reportFail("Listen Again Button not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 10))
			{
				String audioTitlePostclickListen = homepagev2.audioTitle.getText();
				test.log(LogStatus.INFO, "Audio Title Before Clicking Listen button is: " + contentTitle);
				test.log(LogStatus.INFO, "Audio Title After Clicking Listen button is: " + audioTitlePostclickListen);
				if (audioTitlePostclickListen.equalsIgnoreCase(contentTitle))
				{
					test.log(LogStatus.INFO,
							"Audio Title Before Clicking Listen button and After clicking listen button are matching");
					test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Passed");
					homepagev2.smokeresults(testCase4, rowno4, "Pass");
				}
				else
				{
					test.log(LogStatus.INFO,
							"Audio Title Before Clicking Listen button and After clicking listen button are not matching");
					test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Passed");
					homepagev2.smokeresults(testCase4, rowno4, "Fail");
				}
			}
			Thread.sleep(3000);
			Utilities.scrubtillend(driver, homepagev2.audioseekBar);
			/*
			 * try { String OverlayPage = driver.getPageSource(); test.log(LogStatus.INFO,
			 * OverlayPage); } catch (Exception e) {
			 * 
			 * }
			 */
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 25))

				homepagev2.audioplayerClose.click();
			else BasePageV2.reportFail("Unable to click on close button");

			if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 25))
			{
				if (homepagev2.playAudiobookbutton.isDisplayed())
				{
					test.log(LogStatus.INFO,
							"After clicking on close button in audio overlay Navigated to the page from where audio played");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is passed");
					homepagev2.smokeresults(testCase5, rowno5, "Pass");
				}
				else BasePageV2.reportFail(
						"After clicking on close button in audio overlay not Navigated to the page from where audio played");

			}
			else BasePageV2.reportFail(
					"After clicking on close button in audio overlay not Navigated to the page from where audio played");

		}
		else BasePageV2.reportFail("Listen Again Button not displayed");

	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(testName, xls);

	}
}

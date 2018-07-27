package com.viacom.smoketestscripts;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class audioPlayerFunctionalityTest extends BaseTestV2
{
	String testName = "audioPlayerFunctionalityTest";
	String testCase = "'Verify the navigation of Audio player when tapped on any Audio book card'";
	String testCase1 = "'Verify UI of audio player'";
	String testCase2 = "'Verify UI of audio player controls after tapping on the player screen'";
	String testCase3 = "Verify Favourite functionality by adding audio (adding)";
	String testCase4 = "'Verify the functionality of close button in audio player'";
	String testCase5 = "'Verify the functionality of device back button in audio player'";
	String testCase6 = "'To verify the UI of inline audio player'";

	@Test(dataProvider = "getData")
	public void audioPlayerFunctionality(Hashtable<String, String> data) throws Exception
	{

		if (GlobalVariables.break_Flag) throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Audio player Functionality");
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
		 * int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		 * xls.setCellData("Smoke_Results", "Testcase Name", rowno7, testCase7);
		 */

		// Steps:
		// 1. Launch Voot Kids app

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		try
		{
			if (homepagev2.profilepic.isDisplayed())
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
			homepagev2.signup();
			if (!homepagev2.profilepic.isDisplayed())
			{
				homepagev2.reportFail("Unable to navigate to Home Page");
				BasePageV2.takeScreenshot();
			}
			else
			{
				test.log(LogStatus.INFO, "Home Page is displayed");
			}
		}

		// 2. Tap on Listen icon on top bar menu
		try
		{
			homepagev2.listen.click();
		}
		catch (Exception e)
		{
			homepagev2.reportFail("Listen icon tab not displayed");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults(testCase, rowno, "Fail");
		}
		// 3. Tap on any Audio book tile present on the screen

		try

		{
			// Utilities.verticalSwipe(driver);
			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.audioBook, 3))
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
			homepagev2.reportFail("Audio book not displayed");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults(testCase, rowno, "Fail");
		}

		try
		{
			if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 10))
				homepagev2.playAudiobookbutton.click();
			else BasePageV2.reportFail("Play button not displayed in audio detail page");

		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Play button not displayed in audio detail page");
		}

		try
		{
			if (homepagev2.audioplayPause.isDisplayed())
			{
				test.log(LogStatus.INFO, "Tapping on audiobook navigated to audio player");
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is passed");
				// driver.pressKeyCode(AndroidKeyCode.BACK);
				HomePageV2.smokeresults(testCase, rowno, "Pass");
				BasePageV2.takeScreenshot();
			}
			else
			{
				test.log(LogStatus.INFO, "Tapping on audiobook navigated is not to audio player");
				test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
				BasePageV2.takeScreenshot();
				HomePageV2.smokeresults(testCase, rowno, "Fail");
			}
		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Audio Player not displayed");
		}

		// 4. Observe the behavior.

		// Expected Result: 'On tapping on any of the Audio book tile card should lead
		// to Audio player
		try
		{
			// Verify UI of audio player
			// Verify UI of audio player controls after tapping on the player screen

			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 10))
			{
				test.log(LogStatus.INFO,
						"Title of currently playing audio book is: " + homepagev2.audioTitle.getText().toString());
				try
				{
					Thread.sleep(2000);
					homepagev2.audioplayPause.click();
				}
				catch (Exception e)
				{
					BasePageV2.reportFail("Unable to pause Audio");
				}
			}

		}
		catch (Exception e)
		{
			BasePageV2.reportFail("Audio 'play/pause toggle' button is not displayed");
		}
		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 10))
			test.log(LogStatus.INFO, "Audio 'Play/Pause toggle' button is displayed");
		else BasePageV2.reportFail("Audio 'Play/Pause toggle' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiobackward, 10))
			test.log(LogStatus.INFO, "Audio 'Rewind' button is displayed");
		else BasePageV2.reportFail("Audio 'Rewind' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioForward, 10))
			test.log(LogStatus.INFO, "Audio 'Forward' button is displayed");
		else BasePageV2.reportFail("Audio 'Forward' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiofavouriteIcon, 10))
			test.log(LogStatus.INFO, "Audio 'Favourite icon' button is displayed");
		else BasePageV2.reportFail("Audio 'Favourite icon' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 10))
			test.log(LogStatus.INFO, "Audio 'Seek bar' button is displayed");
		else BasePageV2.reportFail("Audio 'Seek bar' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 10))
			test.log(LogStatus.INFO, "Audio 'Player close' button is displayed");
		else BasePageV2.reportFail("Audio 'Player close' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 10))
			test.log(LogStatus.INFO, "Audio 'Title' is displayed: " + homepagev2.audioTitle.getText().toString());
		else BasePageV2.reportFail("Audio 'Title' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10))
			test.log(LogStatus.INFO, "Audio 'Play list expander' button is displayed");
		else BasePageV2.reportFail("Audio 'Play list expander' button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiorunningDuration, 10))
			test.log(LogStatus.INFO, "Audio 'Running duration' is displayed");
		else BasePageV2.reportFail("Audio 'Running duration' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiototalDuration, 10))
			test.log(LogStatus.INFO, "Audio 'Total duration' is displayed");
		else BasePageV2.reportFail("Audio 'Total duration' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiominiplayerSwitch, 10))
		{
			test.log(LogStatus.INFO, "Audio 'mini player switch' button is displayed");
			test.log(LogStatus.INFO, "Tapping on audio book navigated to Full screen audio player");
			test.log(LogStatus.PASS, "Test case: " + testCase1 + " is passed");
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is passed");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
			BasePageV2.takeScreenshot();
		}
		else BasePageV2.reportFail("Tapping on audio book is not navigated to Full screen audio player");

		// 'Verify the functionality of close button in audio player

		// 4. Tap on close button on top right.

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiominiplayerSwitch, 10))
		{
			test.log(LogStatus.INFO, "Audio player displayed in full screen");

			Thread.sleep(5000);

			driver.pressKeyCode(AndroidKeyCode.BACK);
		}
		else BasePageV2.reportFail(" 'Mini player' switch button is not displayed");

		if (homepagev2.playAudiobookbutton.isDisplayed())
		{
			test.log(LogStatus.INFO,
					"Navigated to page from where audio was played while tapping on device back button");
			test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is passed");
			HomePageV2.smokeresults(testCase5, rowno5, "Pass");
			BasePageV2.takeScreenshot();
			homepagev2.playAudiobookbutton.click();
		}
		else BasePageV2.reportFail("Play button is not displayed in audio detail page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiominiplayerSwitch, 10))
		{
			test.log(LogStatus.INFO, "Audio player displayed in full screen");
			homepagev2.audiominiplayerSwitch.click();
		}
		else BasePageV2.reportFail(" 'Mini player' switch button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.inlinePlayerBookTitle, 25))
			test.log(LogStatus.INFO,
					"Book title displayed on inline player: " + homepagev2.inlinePlayerBookTitle.getText().toString());
		else BasePageV2.reportFail("Book Title not displayed on inline player");

		if (Utilities.explicitWaitVisible(driver, homepagev2.inlinePlayerPlaypause, 10))
			test.log(LogStatus.INFO, "'Play/pause toggle' displayed on inline player");
		else BasePageV2.reportFail("'Play/pause toggle' not displayed on inline player");

		if (Utilities.explicitWaitVisible(driver, homepagev2.inlinePlayerSeekbar, 10))
			test.log(LogStatus.INFO, "'Inline player Seek bar' displayed on inline player");
		else BasePageV2.reportFail("'Inline player Seek bar' not displayed on inline player");

		test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is passed");
		homepagev2.smokeresults(testCase6, rowno6, "Pass");
		BasePageV2.takeScreenshot();

		if (homepagev2.playAudiobookbutton.isDisplayed())
			homepagev2.playAudiobookbutton.click();
		else BasePageV2.reportFail("Play button is not displayed in audio detail page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 10))
		{
			test.log(LogStatus.INFO, "Clicking on close button");
			BasePageV2.takeScreenshot();
			homepagev2.audioplayerClose.click();

		}
		else BasePageV2.reportFail("Audio 'Player close' button is not displayed");

		if (homepagev2.playAudiobookbutton.isDisplayed())
		{
			test.log(LogStatus.INFO,
					"Navigated to page from where audio was played while clicking on Audio player close button");
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is passed");
			HomePageV2.smokeresults(testCase4, rowno4, "Pass");
			BasePageV2.takeScreenshot();
		}

	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(testName, xls);

	}

}

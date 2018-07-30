package com.viacom.smoketestscripts;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class searchFunctionalityTest extends BaseTestV2
{
	String testName = "searchFunctionalityTest";
	String testCase = "'Verify Search page UI when landing on the page for the 1st time'";
	String testCase1 = "'Verify Search page UI post performing search functionality(basically 2nd time onwards')";
	String testCase2 = "'Verify 'Surprise Me' button functionality'";
	String testCase3 = "'Verify 'Recent Searches' section'";
	String testCase4 = "'Verify the metadata of each card in recent search'";
	String testCase5 = "'Verify the sorting order for the tiles present under 'Recent Searches' section'";
	String defaulttextInsidesearchtextfield = "Start typing..";
	String searchKey = "BirthofKrishna";
	String searchKey1 = "oddbods";

	@Test(dataProvider = "getData")
	public void audioPlayerFunctionality(Hashtable<String, String> data) throws Exception
	{

		if (GlobalVariables.break_Flag) throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Search Functionality");
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

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5))
		{
			System.out.println("Already signed in");
		}
		else homepagev2.signup();

		// Clicking on Search icon
		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5))
		{
			homepagev2.search.click();
		}
		else BasePageV2.reportFail("Seach icon not displayed");

		// Verifying test case testCase (All test Cases declared as global variable in
		// this class)

		// Popular search section not displayed hence not validated in this sprint

		// Validating visibility of Search text field and visibility of default text

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5))
		{
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			String defaultsearchtext = homepagev2.searchTextBox.getText();

			if (defaultsearchtext.equalsIgnoreCase(defaulttextInsidesearchtextfield))
			{
				test.log(LogStatus.INFO, "Default Search text displayed is: " + defaultsearchtext);
				test.log(LogStatus.INFO, "Actual text to display: " + defaulttextInsidesearchtextfield);
			}
			else test.log(LogStatus.INFO, "Default search text not matched with Actual Search text");

		}
		else BasePageV2.reportFail("'Search text field' is not displayed");

		// Validating visibility of Voice button 
		if (Utilities.explicitWaitVisible(driver, homepagev2.voiceBtn, 5))
		{
			test.log(LogStatus.INFO, "'Voice Search' Button is displayed");
		}
		else BasePageV2.reportFail("'Voice Search' Button is not displayed");

		// Validating visibility of Surprise me button 
		if (Utilities.explicitWaitVisible(driver, homepagev2.surprisemeBtn, 5))
		{
			test.log(LogStatus.INFO, "'Surprise Me' Button is displayed");
		}
		else BasePageV2.reportFail("'Surprise Me' Button is not displayed");

		BasePageV2.takeScreenshot();
		test.log(LogStatus.PASS, "Test Case: " + testCase + " is passed");
		HomePageV2.smokeresults(testCase, rowno, "Pass");

		// Validating 2nd Test Case 
		if (Utilities.explicitWaitVisible(driver, homepagev2.listen, 3))
			homepagev2.listen.click();
		else BasePageV2.reportFail("'Listen Button' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5))
		{
			test.log(LogStatus.INFO, "Clicking on search");
			homepagev2.search.click();
		}
		else BasePageV2.reportFail("'Search icon' not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5))
		{
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.sendKeys(searchKey);
			test.log(LogStatus.INFO, "Searched for 'birth of krishna'");
			try
			{
				driver.hideKeyboard();
			} catch (Exception e)
			{
			}
			WebElement searchcontent = driver.findElement(By.id("com.tv.vootkids:id/grid_title"));
			if (Utilities.explicitWaitVisible(driver, searchcontent, 5))
			{
				System.out.println("Search result displayed");

			}
			else System.out.println("Search result not displayed");
			String recentsearchTitle = driver.findElement(By.id("com.tv.vootkids:id/grid_title")).getText();
			test.log(LogStatus.INFO, "Actual Recent search title: " + recentsearchTitle);
			test.log(LogStatus.INFO, "Expected Recent search title: " + searchKey);
			if (recentsearchTitle.equalsIgnoreCase(searchKey))
			{
				test.log(LogStatus.INFO, "Actual Search title is matching with expected title");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Passed");
				HomePageV2.smokeresults(testCase3, rowno3, "Pass");
			}
			else
			{
				test.log(LogStatus.INFO, "Actual Search title is not matching with expected title");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
				HomePageV2.smokeresults(testCase3, rowno3, "Fail");
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.contentDuration, 2))
				test.log(LogStatus.INFO, "Content duration displayed: " + homepagev2.contentDuration.getText());
			else BasePageV2.reportFail("Content duration not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.contentTitle, 2))
				test.log(LogStatus.INFO, "Content Title displayed: " + homepagev2.contentTitle.getText());
			else BasePageV2.reportFail("Content Title not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.EpnumAndDescription, 2))
				test.log(LogStatus.INFO, "Content Episode number and Description displayed: "
						+ homepagev2.EpnumAndDescription.getText());
			else BasePageV2.reportFail("Content Description not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.contentitemCategory, 2))
				test.log(LogStatus.INFO, "Content Category displayed");
			else BasePageV2.reportFail("Content Category not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.searchClear, 2))
				test.log(LogStatus.INFO, "Search page close button displayed");
			else BasePageV2.reportFail("Search page close button not displayed");

			BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Passed");
			HomePageV2.smokeresults(testCase4, rowno4, "Pass");
		}
		else BasePageV2.reportFail("'Search text field' is not displayed");

		BasePageV2.takeScreenshot();
		if (Utilities.explicitWaitVisible(driver, homepagev2.listen, 3))
			homepagev2.listen.click();
		else BasePageV2.reportFail("'Listen Button' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5))
		{
			test.log(LogStatus.INFO, "Clicking on search");
			homepagev2.search.click();
		}
		else BasePageV2.reportFail("'Search icon' not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5))
		{
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.sendKeys(searchKey1);
			test.log(LogStatus.INFO, "Searched for 'oddbods'");
			try
			{
				driver.hideKeyboard();
			} catch (Exception e)
			{
			}
			WebElement searchcontent = driver.findElement(By.id("com.tv.vootkids:id/grid_title"));
			if (Utilities.explicitWaitVisible(driver, searchcontent, 5))
			{
				System.out.println("Search result displayed");

			}
			else System.out.println("Search result not displayed");
			String nextsearchTitle = driver.findElement(By.id("com.tv.vootkids:id/grid_title")).getText();
			test.log(LogStatus.INFO, "Actual Recent search title: " + nextsearchTitle);
			test.log(LogStatus.INFO, "Recent search title: " + searchKey1);
			test.log(LogStatus.INFO, "Previous searched data: " + searchKey);
			if (nextsearchTitle.equalsIgnoreCase(searchKey))
			{
				test.log(LogStatus.INFO, "Previous Search dislayed first");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
				HomePageV2.smokeresults(testCase5, rowno5, "Fail");
			}
			else
			{
				test.log(LogStatus.INFO, "Recent  Search displayed first");
				if (Utilities.explicitWaitVisible(driver, homepagev2.contentDuration, 2))
					test.log(LogStatus.INFO, "Content duration displayed: " + homepagev2.contentDuration.getText());
				else BasePageV2.reportFail("Content duration not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.contentTitle, 2))
					test.log(LogStatus.INFO, "Content Title displayed: " + homepagev2.contentTitle.getText());
				else BasePageV2.reportFail("Content Title not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.EpnumAndDescription, 2))
					test.log(LogStatus.INFO, "Content Episode number and Description displayed: "
							+ homepagev2.EpnumAndDescription.getText());
				else BasePageV2.reportFail("Content Description not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.contentitemCategory, 2))
					test.log(LogStatus.INFO, "Content Category displayed");
				else BasePageV2.reportFail("Content Category not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.searchClear, 2))
					test.log(LogStatus.INFO, "Search page close button displayed");
				else BasePageV2.reportFail("Search page close button not displayed");

				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Passed");
				HomePageV2.smokeresults(testCase5, rowno5, "Pass");
			}

		}
		else BasePageV2.reportFail("Search text box not displayed");

		test.log(LogStatus.INFO, "Closing search page");
		if (Utilities.explicitWaitVisible(driver, homepagev2.searchClear, 2))
			homepagev2.searchClear.click();
		else BasePageV2.reportFail("Unable to close search page");
		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5))

		{
			test.log(LogStatus.INFO, "Clicking on search");
			homepagev2.search.click();
		}

		else BasePageV2.reportFail("Unable to click search page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5))
		{
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			String defaultsearchtext = homepagev2.searchTextBox.getText();
			test.log(LogStatus.INFO, "Actual text to display: " + defaultsearchtext);
			// Validating visibility of Voice button
			if (Utilities.explicitWaitVisible(driver, homepagev2.voiceBtn, 5))
				test.log(LogStatus.INFO, "'Voice Search' Button is displayed");
			else BasePageV2.reportFail("'Voice Search' Button is not displayed");

			// Validating visibility of Surprise me button
			if (Utilities.explicitWaitVisible(driver, homepagev2.surprisemeBtn, 5))
			{
				test.log(LogStatus.INFO, "'Surprise Me' Button is displayed");
				homepagev2.surprisemeBtn.click();

			}
			else BasePageV2.reportFail("'Surprise Me' Button is not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.videoPlayerTitle, 25))
			{
				String title = homepagev2.videoPlayerTitle.getText();
				test.log(LogStatus.INFO, "Title of currently playing video is: " + title);
				test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is passed");
				HomePageV2.smokeresults(testCase2, rowno2, "Pass");
			}
			else
			{

				test.log(LogStatus.INFO, "Title not displayed for currently playing video");
				test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
				HomePageV2.smokeresults(testCase2, rowno2, "Fail");
			}
			BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is passed");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		}

		/*
		 * try {
		 * 
		 * Dimension size1 = homepagev2.playerskin.getSize(); int x = size1.width / 2;
		 * int y = size1.height / 2; System.out.println(x); System.out.println(y); try {
		 * 
		 * @SuppressWarnings("rawtypes") MultiTouchAction maction = new
		 * MultiTouchAction((MobileDriver) driver); // Thread.sleep(3000);
		 * 
		 * @SuppressWarnings( { "rawtypes" }) TouchAction action1 = new
		 * TouchAction((MobileDriver) driver).tap(new PointOption().point(x, y))
		 * .release().perform(); maction.add(action1).perform(); // Thread.sleep(6000);
		 * 
		 * @SuppressWarnings( { "rawtypes" }) TouchAction action3 = new
		 * TouchAction((MobileDriver) driver).longPress(new PointOption().point(x, y))
		 * .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().
		 * perform();
		 * 
		 * @SuppressWarnings( { "rawtypes" }) TouchAction action4 = new
		 * TouchAction((MobileDriver) driver).tap(new PointOption().point(x, y))
		 * .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().
		 * perform(); maction.add(action3).add(action4).perform(); } catch (Exception e)
		 * {}
		 * 
		 * System.out.println(driver.getPageSource());
		 * 
		 * } catch (Exception e) {}
		 */

	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(testName, xls);

	}
}

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

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class signupFunctionalityCreateProfilePageTest extends BaseTestV2
{
	String pin = "1111";
	String testName = "signupFunctionalityCreateProfilePageTest";
	String email = Utilities.generateEmailid();
	String mobileNumber = Utilities.generateMobileNumber();
	String password = "daya123";
	String testCase = "Verify Next button functionality (Invalid - trying to sign up by keeping mobile number empty) ";
	String testCase1 = "Verify Next button functionality (By Entering Valid Mobile number and Password)";
	String testCase2 = "'Verify the navigation post sign up & relaunching the app'";
	String testCase3 = "Verify if user is navigated to PIN creation page post entering details and exiting & relaunching the app";
	String testCase4 = "'Verify the functionality of 'Enter Code Manually' link text";
	String testCase5 = "'Verify Next button functionality' (OTP Landing page should display)";
	String testCase6 = "'Verify the UI of Manual verification code page'";
	String phonenumberInvalidError = "Please Enter Mobile No.";
	String headertextManualCodePage = "Sign Up";
	String headerSubtitleOTP = "Please enter the OTP sent to";
	String notrecievedotp = "Not Received your OTP?";
	String resendotp = "Resend OTP";

	@Test(dataProvider = "getData")
	public void signUpFunctionalityCreateProfilePage(Hashtable<String, String> data) throws Exception
	{

		if (GlobalVariables.break_Flag) throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating 'Sign Up' Functionality");
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

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5)){
			homepagev2.logout();
		}
		else{
			
		}
		homepagev2.signUpPagefromWelcomeScreen();
		homepagev2.signUpwithoutMobile(email, password);
		if (Utilities.explicitWaitVisible(driver, homepagev2.phoneNumberError, 25))
		{
			String errorPhoneNumber = homepagev2.phoneNumberError.getText().toString();
			test.log(LogStatus.INFO, "Actual Phone number error message: " + errorPhoneNumber);
			test.log(LogStatus.INFO, "Expected Phone number error message: " + phonenumberInvalidError);
			if (errorPhoneNumber.equalsIgnoreCase(phonenumberInvalidError))
			{
				test.log(LogStatus.INFO, "Invalid Phone Number Error message is displayed");
				homepagev2.smokeresults(testCase, rowno, "Pass");
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Passed");
				BasePageV2.takeScreenshot();
			}
			else
			{
				test.log(LogStatus.INFO,
						"Invalid Phone number Error message is not matching with expected error message");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
				homepagev2.smokeresults(testCase, rowno, "Fail");
			}

		}
		else
		{
			test.log(LogStatus.INFO, "Invalid Phone Number Error message is not displayed");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			homepagev2.smokeresults(testCase, rowno, "Fail");
		}

		try
		{
			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(2000);
			homepagev2.signUpPagefromWelcomeScreen();
		}
		catch (Exception e)
		{
		}
		homepagev2.signUpwithoutEmail(mobileNumber, password);

		if (Utilities.explicitWaitVisible(driver, launchPageV2.entercodeManuallyLink, 10))
		{
			if (launchPageV2.entercodeManuallyLink.isDisplayed())
			{
				test.log(LogStatus.INFO, "OTP Loading Page is displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is passed");
				launchPageV2.entercodeManuallyLink.click();
			}
			else
			{
				test.log(LogStatus.INFO, "OTP Loading Page is not displayed");
				test.log(LogStatus.INFO, "Unable to click on enter code Manually link");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
				homepagev2.smokeresults(testCase4, rowno4, "Fail");
				test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
				homepagev2.smokeresults(testCase5, rowno5, "Fail");
			}
		}
		else
		{
			test.log(LogStatus.INFO, "Enter code manually link not displayed");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
			homepagev2.smokeresults(testCase4, rowno4, "Fail");
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.autoCodedetectBtn, 10))
		{
			if (launchPageV2.autoCodedetectBtn.isDisplayed())
			{
				test.log(LogStatus.INFO, "Manual Verification code page is displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case:" + testCase4 + " is Passed");

				// 4 OTP text field verification
				int i = 1;
				// int count=0;
				while (i <= 5)
				{
					// count=i;
					if (i > 0 && i < 5)
					{
						WebElement element = driver.findElement(By.id("com.tv.vootkids:id/otp_pin_" + i));
						if (element.isDisplayed())
						{
							test.log(LogStatus.INFO, "OTP Text field" + i + " is displayed");
							i++;
						}
						else
						{
							test.log(LogStatus.INFO, "OTP Text field" + i + " is displayed");
							BasePageV2.takeScreenshot();
							test.log(LogStatus.FAIL, "TestCase: " + testCase6 + " is Fail");
							homepagev2.smokeresults(testCase6, rowno6, "Fail");
							break;
						}

					}
					else
					{
						try
						{
							WebElement element = driver.findElement(By.id("com.tv.vootkids:id/otp_pin_" + i));
							element.isDisplayed();
							test.log(LogStatus.INFO, "OTP Text field" + i + " is displayed");
							BasePageV2.takeScreenshot();
							test.log(LogStatus.FAIL, "TestCase: " + testCase6 + " is Fail");
							homepagev2.smokeresults(testCase6, rowno6, "Fail");
							break;
						}
						catch (Exception e)
						{
							test.log(LogStatus.INFO, "Total number of OTP Text fields displayed: " + (i - 1));
							BasePageV2.takeScreenshot();
							test.log(LogStatus.PASS, "TestCase: " + testCase6 + " is Passed");
							homepagev2.smokeresults(testCase6, rowno6, "Pass");
							break;
						}
					}

				}

				if (launchPageV2.otpPageHeader.isDisplayed() && launchPageV2.otpPageHeaderSubtitle.isDisplayed()
						&& launchPageV2.backButton.isDisplayed() && launchPageV2.notRecievedOtp.isDisplayed()
						&& launchPageV2.autoCodedetectBtn.isDisplayed() && launchPageV2.resendOTP.isDisplayed())
				{
					// Verifying header text
					test.log(LogStatus.INFO, "Back Button is displayed in 'Manual Verification code page'");
					test.log(LogStatus.INFO, "Auto detect code button is displayed in 'Manual Verification code page'");
					String otppageheader = launchPageV2.otpPageHeader.getText().toString();
					test.log(LogStatus.INFO, "Actual header text: " + otppageheader);
					test.log(LogStatus.INFO, "Expected header text: " + headertextManualCodePage);
					if (otppageheader.equalsIgnoreCase(headertextManualCodePage))
					{
						test.log(LogStatus.INFO, "Manual Verification Header text is displayed as: " + otppageheader);
					}
					else
					{
						test.log(LogStatus.INFO,
								"Header Title of manual Verification code page Header text is not matching with Expected text: "
										+ otppageheader);
						test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
						HomePageV2.smokeresults(testCase6, rowno6, "Fail");
					}
					/*
					 * String headertextManualCodePage="Sign Up"; String
					 * headerSubtitleOTP="Please enter the OTP sent to"; String
					 * notrecievedotp="Not Received your OTP?"; String resendotp="Resend OTP";
					 */

					// Verifying header sub title
					String headersubtitle = launchPageV2.otpPageHeaderSubtitle.getText().toString();
					test.log(LogStatus.INFO, "Actual header Subtitle: " + headersubtitle);
					test.log(LogStatus.INFO, "Expected header Subtitle: " + headerSubtitleOTP);
					if (headersubtitle.contains(headerSubtitleOTP))
					{
						test.log(LogStatus.INFO,
								"Manual Verification Header Subtitle is displayed as: " + headersubtitle);
					}
					else
					{
						test.log(LogStatus.INFO,
								"Header Title of manual Verification code page Header text is not matching with Expected text: "
										+ otppageheader);
						test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
						HomePageV2.smokeresults(testCase6, rowno6, "Fail");
					}
					// Verifying not recieved otp text
					String notrecievedotptext = launchPageV2.notRecievedOtp.getText().toString();
					test.log(LogStatus.INFO, "Actual header Subtitle: " + notrecievedotptext);
					test.log(LogStatus.INFO, "Expected header Subtitle: " + notrecievedotp);
					if (notrecievedotptext.equalsIgnoreCase(notrecievedotp))
					{
						test.log(LogStatus.INFO,
								"Manual Verification 'Not Recieved OTP text' is displayed as: " + notrecievedotptext);
					}
					else
					{
						test.log(LogStatus.INFO,
								"Header Title of manual Verification code page 'Not Recieved OTP text' is not matching with Expected text: "
										+ otppageheader);
						test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
						HomePageV2.smokeresults(testCase6, rowno6, "Fail");
					}

					// Verifying resend otp link
					String resend = launchPageV2.resendOTP.getText().toString();
					test.log(LogStatus.INFO, "Actual header Subtitle: " + resend);
					test.log(LogStatus.INFO, "Expected header Subtitle: " + resendotp);
					if (resend.contains(resendotp))
					{
						test.log(LogStatus.INFO, "Manual Verification 'Resend OTP' is displayed as: " + resend);
					}
					else
					{
						test.log(LogStatus.INFO,
								"Header Title of manual Verification code page 'Resend OTP' is not matching with Expected text: "
										+ otppageheader);
						test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
						HomePageV2.smokeresults(testCase6, rowno6, "Fail");
					}
					test.log(LogStatus.INFO,
							"Header Title of manual Verification code page 'UI' is displayed according to the design");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is Passed");
					HomePageV2.smokeresults(testCase6, rowno6, "Pass");

				}
				else
				{
					test.log(LogStatus.INFO,
							"Header Title of manual Verification code page 'UI' is not according to the design");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
					HomePageV2.smokeresults(testCase6, rowno6, "Fail");
				}

				try
				{
					launchPageV2.backButton.click();
				}
				catch (Exception e)
				{
					homepagev2.reportFail("Back button not displayed");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
					test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
					homepagev2.smokeresults(testCase4, rowno4, "Fail");
					homepagev2.smokeresults(testCase5, rowno5, "Fail");
				}
			}
			else
			{
				test.log(LogStatus.INFO, "Manual Verification code page is not displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case:" + testCase4 + " is Fail");
				launchPageV2.backButton.click();
			}
		}
		else
		{
			test.log(LogStatus.INFO, "Manual Verification code page is not displayed");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case:" + testCase4 + " is Fail");
			launchPageV2.backButton.click();
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 25))
		{
			if (homepagev2.createPinPage.isDisplayed())
			{
				test.log(LogStatus.INFO, "Create Pin Page before Killing the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Passed");
				homepagev2.smokeresults(testCase1, rowno1, "Pass");
			}
			else
			{
				test.log(LogStatus.INFO, "Create Pin Page not displayed before Killing the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
				test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
				homepagev2.smokeresults(testCase1, rowno1, "Fail");
				homepagev2.smokeresults(testCase2, rowno2, "Fail");
			}
		}
		else
		{
			test.log(LogStatus.INFO, "Create Pin Page not displayed before Killing the App");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			homepagev2.smokeresults(testCase1, rowno1, "Fail");
			homepagev2.smokeresults(testCase2, rowno2, "Fail");
			try
			{
				driver.closeApp();
			}
			catch (Exception e)
			{

			}
			driver.startActivity(new Activity("com.tv.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

			if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 120))
			{
				test.log(LogStatus.INFO, "Relaunching the application");
				if (homepagev2.createPinPage.isDisplayed())
				{
					test.log(LogStatus.INFO, "Create Pin Page After Relaunching the App");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Passed");
					homepagev2.smokeresults(testCase2, rowno2, "Pass");
				}
				else
				{
					test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
					homepagev2.smokeresults(testCase2, rowno2, "Fail");
				}
			}
			else
			{
				test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
				homepagev2.smokeresults(testCase2, rowno2, "Fail");
			}
			if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25))
			{
				if (launchPageV2.pinContainer.isDisplayed())
				{
					launchPageV2.pinContainer.clear();
					launchPageV2.pinContainer.sendKeys("1111");
				}
				else
				{
					test.log(LogStatus.INFO, "Pin container not displayed");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is fail");
					homepagev2.smokeresults(testCase3, rowno3, "Fail");

				}
			}
			else
			{
				homepagev2.reportFail("'Create Pin' Page not displayed");
				homepagev2.smokeresults(testCase, rowno, "Fail");
				System.out.println();
			}

			try
			{
				driver.hideKeyboard();
			}
			catch (Exception e)
			{

			}

			test.log(LogStatus.INFO, "Killing the application to validate test case: " + testCase3);

			try
			{
				driver.closeApp();
			}
			catch (Exception e)
			{

			}
			driver.startActivity(new Activity("com.tv.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

			if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 120))
			{
				test.log(LogStatus.INFO, "Relaunching the application");
				if (homepagev2.createPinPage.isDisplayed())
				{
					test.log(LogStatus.INFO, "Create Pin Page After Relaunching the App");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Passed");
					homepagev2.smokeresults(testCase2, rowno3, "Pass");
				}
				else
				{
					test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
					homepagev2.smokeresults(testCase3, rowno3, "Fail");
				}
			}
			else
			{
				test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
				homepagev2.smokeresults(testCase3, rowno3, "Fail");
			}

			try
			{
				if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25))
				{
					launchPageV2.pinContainer.clear();
					launchPageV2.pinContainer.sendKeys(pin);
				}
				else
				{
					System.out.println();
				}
				try
				{
					driver.hideKeyboard();
				}
				catch (Exception e)
				{
				}
				try
				{
					// launchPageV2.buttonCreatePin.click();
				}
				catch (Exception e)
				{
				}
			}
			catch (Exception e)
			{
			}

			// Confirm pin
			try
			{
				if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25))
				{
					launchPageV2.pinContainer.clear();
					launchPageV2.pinContainer.sendKeys(pin);
				}
				else
				{
					System.out.println();
				}

				try
				{
					driver.hideKeyboard();
				}
				catch (Exception e)
				{
				}

				try
				{
					launchPageV2.buttonCreatePin.click();
				}
				catch (Exception e)
				{
				}
			}
			catch (Exception e)
			{
			}
			try
			{
				if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25))
				{
					launchPageV2.kidsName.clear();
					launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
				}
				else
				{
					HomePageV2.reportFail("Kids name text field not displayed");
					BasePageV2.takeScreenshot();
				}

				if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
				{
					launchPageV2.DOB.click();
				}
				else
				{
					HomePageV2.reportFail("DOB Field not displayed");
					BasePageV2.takeScreenshot();
				}

				if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
				{
					launchPageV2.DOBSelect.click();
				}
				else
				{
					HomePageV2.reportFail("Date selector not displayed");
					BasePageV2.takeScreenshot();
				}

				try
				{
					driver.hideKeyboard();
				}
				catch (Exception e)
				{
				}
				try
				{
					launchPageV2.next.click();

					Thread.sleep(7000);
					launchPageV2.next.click();

				}
				catch (Exception e)
				{
					HomePageV2.reportFail("Next Button not displayed in Kids detail page");
					BasePageV2.takeScreenshot();
				}

				try
				{
					for (int i = 1; i <= 4; i++)
					{
						WebElement element = driver.findElement(By
								.xpath("(//android.widget.ImageView[@resource-id='com.tv.vootkids:id/character_icon'])["
										+ i + "]"));
						if (Utilities.explicitWaitVisible(driver, element, 10))
						{
							element.click();
						}
						else
						{
							HomePageV2.reportFail("Kids Characters not displayed");
							BasePageV2.takeScreenshot();
						}

					}
					Thread.sleep(7000);
					WebElement Skills = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
					if (Utilities.explicitWaitVisible(driver, Skills, 5))
					{
						Skills.click();
					}
					else
					{
						HomePageV2.reportFail("Skills not displayed");
						BasePageV2.takeScreenshot();
					}
					try
					{
						Thread.sleep(2000);
						launchPageV2.next.click();
					}
					catch (Exception e)
					{
						HomePageV2.reportFail("Next button not displayed in character selection page");
						BasePageV2.takeScreenshot();
					}
					if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 25))
					{
						launchPageV2.letsGo.click();
					}
					else
					{
						HomePageV2.reportFail("lets Go button not displayed in character selection page");
						BasePageV2.takeScreenshot();
					}

					try
					{
						for (int i = 1; i <= 5; i++)
						{
							if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
							{
								if (homepagev2.freshAppNotificationCancel.isDisplayed())
								{
									homepagev2.freshAppNotificationCancel.click();
								}
								else
								{
									break;
								}
							}
						}
					}
					catch (Exception e)
					{

					}

					if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
					{
						if (homepagev2.profilepic.isDisplayed())
						{
							test.log(LogStatus.INFO, "Sign up Successful");
						}
						else
						{
							HomePageV2.reportFail("Unable to signup with valid credentials");
							BasePageV2.takeScreenshot();
						}
					}

				}
				catch (Exception e)
				{

				}
			}
			catch (Exception e)
			{
			}
			HomePageV2.logout();
		}
		test.log(LogStatus.INFO, "Killing the application to validate test case: " + testCase2);
		try
		{
			driver.closeApp();
		}
		catch (Exception e)
		{

		}
		driver.startActivity(new Activity("com.tv.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 120))
		{
			test.log(LogStatus.INFO, "Relaunching the application");
			if (homepagev2.createPinPage.isDisplayed())
			{
				test.log(LogStatus.INFO, "Create Pin Page After Relaunching the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Passed");
				homepagev2.smokeresults(testCase2, rowno2, "Pass");
			}
			else
			{
				test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
				homepagev2.smokeresults(testCase2, rowno2, "Fail");
			}
		}
		else
		{
			test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			homepagev2.smokeresults(testCase2, rowno2, "Fail");
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25))
		{
			if (launchPageV2.pinContainer.isDisplayed())
			{
				launchPageV2.pinContainer.clear();
				launchPageV2.pinContainer.sendKeys("1111");
			}
			else
			{
				test.log(LogStatus.INFO, "Pin container not displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is fail");
				homepagev2.smokeresults(testCase3, rowno3, "Fail");

			}
		}
		else
		{
			homepagev2.reportFail("'Create Pin' Page not displayed");
			homepagev2.smokeresults(testCase, rowno, "Fail");
			System.out.println();
		}

		try
		{
			driver.hideKeyboard();
		}
		catch (Exception e)
		{

		}

		test.log(LogStatus.INFO, "Killing the application to validate test case: " + testCase3);

		try
		{
			driver.closeApp();
		}
		catch (Exception e)
		{

		}
		driver.startActivity(new Activity("com.tv.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 120))
		{
			test.log(LogStatus.INFO, "Relaunching the application");
			if (homepagev2.createPinPage.isDisplayed())
			{
				test.log(LogStatus.INFO, "Create Pin Page After Relaunching the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Passed");
				homepagev2.smokeresults(testCase2, rowno3, "Pass");
			}
			else
			{
				test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
				homepagev2.smokeresults(testCase3, rowno3, "Fail");
			}
		}
		else
		{
			test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			homepagev2.smokeresults(testCase3, rowno3, "Fail");
		}

		try
		{
			if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25))
			{
				launchPageV2.pinContainer.clear();
				launchPageV2.pinContainer.sendKeys(pin);
			}
			else
			{
				System.out.println();
			}
			try
			{
				driver.hideKeyboard();
			}
			catch (Exception e)
			{
			}
			try
			{
				// launchPageV2.buttonCreatePin.click();
			}
			catch (Exception e)
			{
			}
		}
		catch (Exception e)
		{
		}

		// Confirm pin
		try
		{
			if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25))
			{
				launchPageV2.pinContainer.clear();
				launchPageV2.pinContainer.sendKeys(pin);
			}
			else
			{
				System.out.println();
			}

			try
			{
				driver.hideKeyboard();
			}
			catch (Exception e)
			{
			}

			try
			{
				launchPageV2.buttonCreatePin.click();
			}
			catch (Exception e)
			{
			}
		}
		catch (Exception e)
		{
		}
		try
		{
			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25))
			{
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
			}
			else
			{
				HomePageV2.reportFail("Kids name text field not displayed");
				BasePageV2.takeScreenshot();
			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
			{
				launchPageV2.DOB.click();
			}
			else
			{
				HomePageV2.reportFail("DOB Field not displayed");
				BasePageV2.takeScreenshot();
			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
			{
				launchPageV2.DOBSelect.click();
			}
			else
			{
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try
			{
				driver.hideKeyboard();
			}
			catch (Exception e)
			{
			}
			try
			{
				launchPageV2.next.click();

				Thread.sleep(7000);
				launchPageV2.next.click();

			}
			catch (Exception e)
			{
				HomePageV2.reportFail("Next Button not displayed in Kids detail page");
				BasePageV2.takeScreenshot();
			}

			try
			{
				for (int i = 1; i <= 4; i++)
				{
					WebElement element = driver.findElement(
							By.xpath("(//android.widget.ImageView[@resource-id='com.tv.vootkids:id/character_icon'])["
									+ i + "]"));
					if (Utilities.explicitWaitVisible(driver, element, 10))
					{
						element.click();
					}
					else
					{
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}

				}
				Thread.sleep(7000);
				WebElement Skills = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisible(driver, Skills, 5))
				{
					Skills.click();
				}
				else
				{
					HomePageV2.reportFail("Skills not displayed");
					BasePageV2.takeScreenshot();
				}
				try
				{
					Thread.sleep(2000);
					launchPageV2.next.click();
				}
				catch (Exception e)
				{
					HomePageV2.reportFail("Next button not displayed in character selection page");
					BasePageV2.takeScreenshot();
				}
				if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 25))
				{
					launchPageV2.letsGo.click();
				}
				else
				{
					HomePageV2.reportFail("lets Go button not displayed in character selection page");
					BasePageV2.takeScreenshot();
				}

				try
				{
					for (int i = 1; i <= 5; i++)
					{
						if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
						{
							if (homepagev2.freshAppNotificationCancel.isDisplayed())
							{
								homepagev2.freshAppNotificationCancel.click();
							}
							else
							{
								break;
							}
						}
					}
				}
				catch (Exception e)
				{

				}

				if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
				{
					if (homepagev2.profilepic.isDisplayed())
					{
						test.log(LogStatus.INFO, "Sign up Successful");
					}
					else
					{
						HomePageV2.reportFail("Unable to signup with valid credentials");
						BasePageV2.takeScreenshot();
					}
				}

			}
			catch (Exception e)
			{

			}
		}
		catch (Exception e)
		{
		}
		HomePageV2.logout();
	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(testName, xls);

	}

}

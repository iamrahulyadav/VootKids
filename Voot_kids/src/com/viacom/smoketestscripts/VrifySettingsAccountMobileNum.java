package com.viacom.smoketestscripts;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VrifySettingsAccountMobileNum extends BaseTestV2 {
	String testName = "VrifySettingsAccountMobileNum";

	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Settings ACCOUNT Mobile Number Page ");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls741 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno741 = xls741.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno741,
				"741: Verify the click functionality of Mobile No. field in Account settings page");

		Xls_Reader xls1742 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno742 = xls1742.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno742,
				"742: Verify the UI of Mobile No. page ");

		Xls_Reader xls743 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno743 = xls743.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno743,
				"724: Verify the click functionality of Back arrow in Mobile No. Page");

		Xls_Reader xls744 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno744 = xls744.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno744, "744: Verify the click functionality of Edit option in Mobile No. Page");

		Xls_Reader xls751 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno751 = xls751.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno751,
				"751: Verify country code is autofilled as user starts typing mobile no. in the text field");

		
		
		
		/*
		 * Need to add test step in this class below in between test cases 
		 * 2. SignUp/Login using valid email ID/mobile no.
		 * 
		 * And
		 * 
		 * Hard coded pin "1234" in below code using next line code
		 * 
		 * if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer,
		 * 20)) launchpagev2.parentPinContainer.sendKeys("1111"); Set the pin here
		 * Default "1111" Later in this Class
		 * 
		 * Passing in Email Edit  " suresh.k@ifocussystec.com"   /// Hard coded in Email in this step
		 * Passing Mobile Number : "8904584821"                   /// Hard coded number in this step
		 * 
		 * Hard Coded Emai"suresh.kutagula@gmail.com" in below test steps nned to Modify
		 * 
		 */

		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");

		LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
		SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		// navigating to settings 'ACCOUNT' page 

		try {
			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

				homepagev2.profilepic.click(); // tap on profile icon
				test.log(LogStatus.INFO, "Succusfully entered to Switch profile page");

			}
		} catch (Exception e) {
			BasePageV2.reportFail("Profile Icon Not Fould to navigate to Switch profile ");
		}
		
		
		try {
			
			if (Utilities.explicitWaitVisible(driver, settingsPage.settingsIcon, 10)) {
				settingsPage.settingsIcon.click();
				if (Utilities.explicitWaitVisible(driver, settingsPage.ParentZoneTile, 10)) {
					settingsPage.parentPinContainer.sendKeys("1234");
					Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZoneAccount, 10);
					settingsPage.settingTextinParantZoneAccount.click();
					settingsPage.accountMobile.click();
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumTile, 10)) {
						test.log(LogStatus.PASS,
								"Verify the click functionality of Mobile No. field in Account settings page");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("741: Verify the click functionality of Mobile No. field in Account settings page",
								rowno741, "PASS");
					}
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumEditText, 10)) {
							if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumSaveBtn, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumCuntryCodeText, 10)) {
									test.log(LogStatus.PASS,
											"Verified the UI of Mobile No. page");
									settingsPage.takeScreenshot();
									homepagev2.smokeresults("741: Verify the UI of Mobile No. page",
											rowno742, "PASS");
								
							}
						}
					}
					
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumEditText, 10)) {
						settingsPage.addMobileNumEditText.click();
						test.log(LogStatus.PASS,
								"Verified the click functionality of Edit option in Mobile No. Page");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("744: Verify the click functionality of Edit option in Mobile No. Page",
								rowno744, "PASS");
					}
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumCuntryCodeText, 10)) {
						
						test.log(LogStatus.PASS,
								"Verified country code is autofilled as user starts typing mobile no. in the text field");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("751: Verify country code is autofilled as user starts typing mobile no. in the text field",
								rowno751, "PASS");
					}
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumTile, 10)) {
					settingsPage.addMobileNumBackBtn.click();
					 if(Utilities.explicitWaitVisible(driver, settingsPage.settingsAccount, 10)) {
						 test.log(LogStatus.PASS,
									"Verified the click functionality of Back arrow in Mobile No. Page.");
							settingsPage.takeScreenshot();
							homepagev2.smokeresults("743: Verify the click functionality of Back arrow in Mobile No. Page",
									rowno743, "PASS");
					 }
					}
					
			
				}
		
			
			
			}	
			
		}catch(Exception e) {
			SettingsPageV2.reportFail("Not Found Settings Icon to naviagte to Settings page");
			
		}
		
		
	
	}
	
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
	
}


	


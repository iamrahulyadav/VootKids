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

public class VrifySettingsPinPage extends BaseTestV2 {

	String testName = "VerifySettingsPinPage";

	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Settings Pin Page ");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls702 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno702 = xls702.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno702, "702: Validate Settings icon functionality:");

		Xls_Reader xls703 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno703 = xls703.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno703,
				"703: Verify the UI of PIN verification for Settings Page");

		Xls_Reader xls1704 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno704 = xls1704.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno704,
				"704: Verify the functionality of Back arrow in PIN verification for Settings Page");

		Xls_Reader xls715 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno715 = xls715.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno715,
				"715: Verify Submit button functionality by entering valid PIN:");

		Xls_Reader xls716 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno716 = xls716.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno716, "716: Verify the UI of Settings Main Screen");

		Xls_Reader xls717 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno717 = xls717.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno717,
				"717: Verify Back arrow functionality in Settings screen");

		Xls_Reader xls718 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno718 = xls718.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno718,
				"718: Verify the funcionality of Logout feature in settings main screen");

		Xls_Reader xls719 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno719 = xls719.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno719, "719: Validate Account Settings functionality:");

		Xls_Reader xls720 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno720 = xls720.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno720, "720: Verify the UI of Account Settings page");
		
		Xls_Reader xls721 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno721 = xls721.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno721, "721: Verify 'ACCOUNT'Back icon functionality: ");

		/*
		 * Need to add test step in this class below in between test cases 2. Sign
		 * Up/Login using valid email ID/mobile no.
		 * 
		 * And
		 * 
		 * if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer,
		 * 20)) launchpagev2.parentPinContainer.sendKeys("1111"); Set the pin here
		 * Default "1111" Later in this Class
		 */

		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");

		LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
		SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		// navigating to settings page and verifying pin screen
		try {
			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

				homepagev2.profilepic.click(); // tap on profile icon
				test.log(LogStatus.INFO, "Succusfully entered to Switch profile page");

			}
		} catch (Exception e) {
			BasePageV2.reportFail("Profile Icon Not Fould to navigate to Switch profile ");
		}
		if (Utilities.explicitWaitVisible(driver, settingsPage.settingsIcon, 10)) {
			Thread.sleep(10000);
			settingsPage.settingsIcon.click(); // tapping setting icon
			test.log(LogStatus.INFO, "Succusfully entered to parent Zone");
			if (Utilities.explicitWaitVisible(driver, settingsPage.ParentZoneTile, 10)) {
				settingsPage.takeScreenshot();
				homepagev2.smokeresults("702: Validated Settings icon functionality", rowno702, "PASS");
			}
			// navigating the PARENT Zone Screen
			if (Utilities.explicitWaitVisible(driver, settingsPage.ParentZoneTile, 10)) {
				if (Utilities.explicitWaitVisible(driver, settingsPage.enterPinToacess, 10)) {
					if (Utilities.explicitWaitVisible(driver, settingsPage.forGotPinText, 10)) {
						if (Utilities.explicitWaitVisible(driver, settingsPage.bckBtnParentZone, 10)) {
							test.log(LogStatus.PASS, "Verified PARENT ZONE Page having Expected Elements");
							settingsPage.takeScreenshot();
							homepagev2.smokeresults("703: Verified the UI of PIN verification for Settings Page",
									rowno703, "PASS");
						}
					}
				}

			} else {
				test.log(LogStatus.FAIL, "In 'PARENT ZONE' page expected elements are not found");
				homepagev2.reportFail("702,703 -- In 'PARENT ZONE' page expected elements are not found");
			}
			if (Utilities.explicitWaitVisible(driver, settingsPage.bckBtnParentZone, 10)) {
				settingsPage.bckBtnParentZone.click(); // Click Back Button in PARENT ZONE page Navigate to Switch Profile
														
				if (Utilities.explicitWaitVisible(driver, settingsPage.settingsIcon, 10)) {
					test.log(LogStatus.PASS, "PARENT ZONE Page having Expected Elements");
					settingsPage.takeScreenshot();
					homepagev2.smokeresults(
							"Verified the functionality of Back arrow in PIN verification for Settings Page", rowno704,
							"PASS");
					settingsPage.settingsIcon.click();
					if (Utilities.explicitWaitVisible(driver, settingsPage.parentPinContainer, 20)) {
						settingsPage.parentPinContainer.sendKeys("1234"); // set the pin "1111" default
						if (Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZone, 10)) {
							test.log(LogStatus.PASS,
									"User should be navigated to Settings page after successful verification of PIN");
							settingsPage.takeScreenshot();
							homepagev2.smokeresults("715: Verified Submit button functionality by entering valid PIN:",
									rowno715, "PASS");

						}
						// navigating to SETTINGs Screen which have
						// Accounts,profiles,Device,share,Help&support
						if (Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZone, 10)) {
							if (Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZoneAccount,
									10)) {
								if (Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZoneProfiles,
										10)) {
									if (Utilities.explicitWaitVisible(driver,
											settingsPage.settingTextinParantZoneDevice, 10)) {
										if (Utilities.explicitWaitVisible(driver,
												settingsPage.settingTextinParantZoneShare, 10)) {
											if (Utilities.explicitWaitVisible(driver,
													settingsPage.settingTextinParantZoneHelpSupport, 10)) {
												if (!Utilities.explicitWaitVisible(driver,
														settingsPage.settingTextinParantZoneRateUs, 10)) {
													test.log(LogStatus.PASS,
															"Verified the UI of Settings Main Screen Having expected Elements");
													settingsPage.takeScreenshot();
													homepagev2.smokeresults(
															"716: Verified the UI of Settings Main Screen", rowno716,
															"PASS");
												}
											}
										}
									}
								}
							}

						} else {

							test.log(LogStatus.FAIL, "Not able Verified the UI of Settings Main Screen");

						}

						if (Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZoneAccount, 10)) {
							settingsPage.settingTextinParantZoneAccount.click();
							Thread.sleep(10000);
							// Validating the ACCOUNT screen
							if (Utilities.explicitWaitVisible(driver, settingsPage.settingsAccount, 10)) {
								test.log(LogStatus.PASS, "Validated Account Settings functionality");
								settingsPage.takeScreenshot();
								homepagev2.smokeresults("719: Validated Account Settings functionality", rowno719,
										"PASS");

								if (Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
									if (Utilities.explicitWaitVisible(driver, settingsPage.accountMobile, 10)) {
										if (Utilities.explicitWaitVisible(driver, settingsPage.accountResetPawd, 10)) {
											if (Utilities.explicitWaitVisible(driver,
													settingsPage.accountResetparZonePin, 10)) {
												test.log(LogStatus.PASS, "Verified the UI of Account Settings page");
												settingsPage.takeScreenshot();
												homepagev2.smokeresults("720: Verified the UI of Account Settings page",
														rowno720, "PASS");

											}
										}
									}

								}

								if (Utilities.explicitWaitVisible(driver, settingsPage.accountBackBtn, 10)) {
									settingsPage.accountBackBtn.click();
									if(Utilities.explicitWaitVisible(driver, settingsPage.settingsAccount, 10)) {
									settingsPage.takeScreenshot();
									homepagev2.smokeresults("721: Verify 'ACCOUNT'Back icon functionality",
											rowno721, "PASS");
									}
								}

							} else {
								test.log(LogStatus.FAIL, "Not navigating to ACCOUNT screen");
							}

						}

						// Validation the SETTINGS Back button
						if (Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZonebackbtn, 20)) {
							if (Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZonebackbtn,
									20)) {
								settingsPage.settingTextinParantZonebackbtn.click();
								if (Utilities.explicitWaitVisible(driver, settingsPage.switchProfile, 20)) {
									test.log(LogStatus.PASS, "Verified Back arrow functionality in Settings screen");
									settingsPage.takeScreenshot();
									homepagev2.smokeresults("717: Verified Back arrow functionality in Settings screen",
											rowno717, "PASS");
								} else {
									test.log(LogStatus.FAIL,
											" Back arrow functionality not working in Settings screen");
								}
							}

						}

						// Validating the LOG OUT Button

						// if(Utilities.explicitWaitVisible(driver, settingsPage.settingsIcon, 10)) {
						// settingsPage.settingsIcon.click();
						// Thread.sleep(10000);
						// if(Utilities.explicitWaitVisible(driver, settingsPage.parentPinContainer,
						// 20)) {
						// settingsPage.parentPinContainer.sendKeys("1234");
						// if (Utilities.explicitWaitVisible(driver, settingsPage.settingsLogOut, 20)) {
						// settingsPage.settingsLogOut.click(); // Clicking the Log out Button in
						// Setting screen
						// if(Utilities.explicitWaitVisible(driver, element, 10)) {
						// test.log(LogStatus.PASS, "Verified Back arrow functionality in Settings
						// screen");
						// settingsPage.takeScreenshot();
						// homepagev2.smokeresults("718: Verified the Logout feature in settings main
						// screen",rowno718, "PASS");
						// }else {
						// test.log(LogStatus.FAIL, " 'LOG OUT' Button Functionality is not working");
						// }
						// }
						// }
						// }

					} else {
						test.log(LogStatus.FAIL, "Parent Zone 'PIN Code ");
						homepagev2.reportFail("715,716,717,718,719,720,721, Settings Icon not Found to navigate to 'SETTINGS'");
					}

				}else {
					
					
					homepagev2.reportFail("7o4, functionality of Back arrow in PIN verification for Settings Page Not Working");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Settings Icon not Found");
			
			homepagev2.reportFail("715,716,717,718,719,720,721, Settings Icon not Found to navigate to 'SETTINGS'");

		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}

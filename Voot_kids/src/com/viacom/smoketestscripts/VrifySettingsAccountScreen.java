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

public class VrifySettingsAccountScreen extends BaseTestV2 {

	String testName = "VrifySettingsAccountScreen";

	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Settings ACCOUNT Page ");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls722 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno722 = xls722.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno722,
				"722: Verify the email id present on the Account ");

		Xls_Reader xls1723 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno723 = xls1723.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno723,
				"723: Verify the mobile number present on the Account Settings main screen");

		Xls_Reader xls724 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno724 = xls724.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno724,
				"724: Verify the click functionality of Email field in Account settings page");

		Xls_Reader xls735 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno735 = xls735.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno735, "735: Verify the UI of Email Edit page");

		Xls_Reader xls726 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno726 = xls726.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno726,
				"726: Verify the Back arrow functionality in Email page");

		Xls_Reader xls727 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno727 = xls727.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno727,
				"727: Verify the click functionality of Edit option in Email page");

		Xls_Reader xls728 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno728 = xls728.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno728, "728: Verify the UI of Email edit page after tapping on 'Edit' ");


		Xls_Reader xls736 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno736 = xls736.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno736, "736: Verify the functionality of 'Save' button post entering valid data in 'Email' field");

		Xls_Reader xls731 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno731 = xls731.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno731, "731: Verify the functionality of 'Save ' button post entering invalid data in 'Add Email ID' field");

		Xls_Reader xls732 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno732 = xls732.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno732, "732: Verify the functionality of 'Save ' button by keeping the 'Add Email ID' field blank/empty");
		
		Xls_Reader xls734 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno734 = xls734.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno734, "734: Verify the click functionality of 'Add Email' in Settings page");
		
		Xls_Reader xls737 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno737 = xls737.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno737, "737: Verify the UI of pop-up message post successful adding Email ");
		
		Xls_Reader xls738 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno738 = xls738.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno738, "738: Verify the functionality of Cross mark icon/outside the pop-up notification in Email Added pop-up");
		
		
		/*
		 * Need to add test step in this class below in between test cases 2. Sign
		 * Up/Login using valid email ID/mobile no.
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
				// Passing the Email id :suresh.k@ifocussystec.in" with hard coded mail id
				String emailId = "suresh.k@ifocussystec.in";
				String verifystr = settingsPage.accountEmailId.getText();
				if(emailId.equalsIgnoreCase(verifystr)) {
					test.log(LogStatus.PASS,
							"Verifies the given Mail ID is present on the Account main screen");
					settingsPage.takeScreenshot();
					homepagev2.smokeresults("722: Verify the email id present on the Account",
							rowno722, "PASS");

				}
				/*
				 * Here Mobile Number with country code should come "+91 8904584821"
				 * Here having Bug so need to validate going forward		     
				 */
				if(Utilities.explicitWaitVisible(driver, settingsPage.accountMobile, 10)) {
					String mobileNum = "8904584821";
					String verifystrMobileNum = settingsPage.accountMobileNum.getText();
					if(mobileNum.equalsIgnoreCase(verifystrMobileNum)) {
						test.log(LogStatus.PASS,
								"Verified the Mobile Number present on the Account Main Screen");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("723: Verify the mobile number present on the Account Settings main screen",
								rowno723, "PASS");
					}
				}

				// Checking the From ACCOUNT Email Navigating to Email Page		     

				if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
					settingsPage.accountEmail.click();
					if(Utilities.explicitWaitVisible(driver, settingsPage.addEmail, 10)) {
						test.log(LogStatus.PASS,
								"Verified the Click Functionality on Email filed in Account Settings Page");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("724: Verify the click functionality of Email field in Account settings page",
								rowno724, "PASS");
						// validating Email screen having expected List Options							
						if(Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZonebackbtn, 10)) {
							if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPage.addMailuserTile, 10)) {
									if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSaveBtn, 10)) {
										test.log(LogStatus.PASS,
												"verified the UI of 'Add Email' page UI");
										settingsPage.takeScreenshot();
										homepagev2.smokeresults("735: Verify the UI of 'Add Email' page UI",
												rowno735, "PASS");
									}
									if(Utilities.explicitWaitClickable(driver, settingsPage.addMailEditText, 10)) {
										if(settingsPage.addMailEditText.isEnabled()) {
											test.log(LogStatus.PASS,
													"Click functionality work fine in Edit page Email edit");
											settingsPage.takeScreenshot();
											homepagev2.smokeresults("727: Verify the click functionality of Edit option in Email page",
													rowno727, "PASS");
										}
										if(Utilities.explicitWaitClickable(driver, settingsPage.settingTextinParantZonebackbtn, 10)) {
											settingsPage.settingTextinParantZonebackbtn.click();
											test.log(LogStatus.PASS,
													"verified the Ui of Email page mail Edit is edited succussfully");
											settingsPage.takeScreenshot();
											homepagev2.smokeresults("726: Verify the Back arrow functionality in Email page",
													rowno726, "PASS");		
										}
	// In below step Email id is "suresh.kutagula@gmail.com" Hard carded in below steps , Need to Modify                      
										if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
											settingsPage.accountEmail.click();
											if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
												settingsPage.addMailEditText.clear();
												settingsPage.takeScreenshot();
												settingsPage.addMailEditText.sendKeys("suresh.kutagula@gmail.com");
												settingsPage.addMailSaveBtn.click();
												Thread.sleep(30000);
												
												if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSucssEmailAdd, 10)) {
													if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSucssEmailAddCnlBtn, 10)) {
														test.log(LogStatus.PASS,
																"Verified the UI of pop-up message post successful adding Email");
														settingsPage.takeScreenshot();
														homepagev2.smokeresults("737: Verify the UI of pop-up message post successful adding Email",
																rowno737, "PASS");	
	
													}
												}
												
												
												
												if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSucssEmailAdd, 10)) {
													test.log(LogStatus.PASS,
															"Verify the functionality of 'Save' button post entering valid data in 'Email' field");
													settingsPage.takeScreenshot();
													settingsPage.addMailSucssEmailAddCnlBtn.click();
													test.log(LogStatus.PASS,
															"Verify the functionality of Cross mark icon/outside the pop-up notification in Email Added pop-up");
													
													homepagev2.smokeresults("738: Verify the functionality of 'Save' button post entering valid data in 'Email' field",
															rowno738, "PASS");
													
													homepagev2.smokeresults("730: Verify the functionality of 'Save' button post entering valid data in 'Email' field",
															rowno736, "PASS");	
													test.log(LogStatus.PASS,
															"Verified the UI of Email edit page after tapping on 'Edit'");
													settingsPage.takeScreenshot();
													homepagev2.smokeresults("728: Verify the UI of Email edit page after tapping on 'Edit'",
															rowno728, "PASS");	
												}

											}
										}

										if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
											settingsPage.accountEmail.click();
											if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
												settingsPage.addMailEditText.clear();
												settingsPage.addMailEditText.sendKeys("suresh.k@ifocussystec.in");
												settingsPage.addMailSaveBtn.click();
												Thread.sleep(20000);
												settingsPage.addMailSucssEmailAddCnlBtn.click();

											}
										}	
										
										if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
											
											settingsPage.accountEmail.click();									
											if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
												test.log(LogStatus.PASS,
														"Verified the click functionality of 'Add Email' in Settings page");
												settingsPage.takeScreenshot();
												homepagev2.smokeresults("734: Verify the click functionality of 'Add Email' in Settings page",
														rowno734, "PASS");
												settingsPage.addMailEditText.clear();
												settingsPage.addMailEditText.sendKeys("suresh.k@ifocussystec.");
												settingsPage.addMailSaveBtn.click();
												Thread.sleep(20000);
												if(Utilities.explicitWaitVisible(driver, settingsPage.addMailWrongMsg, 20)) {
													test.log(LogStatus.PASS,
															"Verify the functionality of 'Save ' button post entering invalid data in 'Add Email ID' field");
													settingsPage.takeScreenshot();
													homepagev2.smokeresults("731: Verify the functionality of 'Save ' button post entering invalid data in 'Add Email ID' field",
															rowno731, "PASS");  
														
												}
							// Here Empty Edit text box empty should show "enter Email Id"	in below need to Modify				
												settingsPage.addMailEditText.clear();
												settingsPage.addMailSaveBtn.click();
												Thread.sleep(10000);
												if(settingsPage.addMailEditText.getText().isEmpty() && (settingsPage.addMailWrongMsg.isDisplayed())) {
													test.log(LogStatus.PASS,
															"Verified the functionality of 'Save ' button by keeping the 'Add Email ID' field blank/empty");
													settingsPage.takeScreenshot();
													homepagev2.smokeresults("732: Verify the functionality of 'Save ' button by keeping the 'Add Email ID' field blank/empty",
															rowno732, "PASS");  
												}
												
												
										}
										}
										
									}
								}
							}

						}	
					}

				}	     


			}else {
				test.log(LogStatus.FAIL, "In 'PARENT ZONE' page expected elements are not found");
				homepagev2.reportFail("7 -- Not Navigating to Settings 'ACCOUNT' Screen ");
			}
		  }
		
		}catch(Exception e) {
			settingsPage.reportFail("NOt found Settings Icon to Navigating to Settings Screen");
		}
		
		}
	
	  

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}

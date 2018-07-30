package com.viacom.smoketestscripts;

import java.util.HashSet;
import java.util.Hashtable;

import org.glassfish.grizzly.compression.lzma.impl.Base;
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

public class VerifySettingsDeviceScreen extends BaseTestV2 {
  
	
	
		String testName = "VerifySettingsDeviceScreen";

		@Test(dataProvider = "getData")
		public void verifySettingsDeviceScreen(Hashtable<String, String> data) throws Exception {
			test = rep.startTest("Verify Settings Profiles Page ");
			test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
			// Check run mode
			if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
				throw new SkipException("Skipping the test as Run Mode was: NO");
			}


			Xls_Reader xls824 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno824 = xls824.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno824,
					"824: Verify the UI of Device page.");
			

			Xls_Reader xls871 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno871 = xls871.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno871,
					"871: Verify the UI of Help & Support page");
			
			Xls_Reader xls867 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno867 = xls871.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno867,
					"867: Verify the functionality of share feature by selecting any app");
			
			Xls_Reader xls877 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno877 = xls877.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno877,
					"877: Validate the functionality for 'Rate Us' link");
			
			Xls_Reader xls880 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno880 = xls880.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno880,
					"880: Verify the functionality for 'Rate us on the App Store/Play Store' link by clicking on 'Yes' on the pop-up message");
			
			Xls_Reader xls888 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno888 = xls888.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno888,
					"888: Verify the functionality for 'Rate Us' link by tapping on 'No' on the message");
			
			Xls_Reader xls886 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno886 = xls886.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno886,
					"886: Verify the functionality by tapping on 'Rate Us' button in the pop-up");
			
			Xls_Reader xls891 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno891 = xls891.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno891,
					"891: Verify the functionality of 'send feedback' button in pop-up message");
			
			Xls_Reader xls892 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
			int rowno892 = xls892.getRowCount("Smoke_Results") + 1;
			xls.setCellData("Smoke_Results", "Testcase Name", rowno892,
					"892: Verify if user can send the feedback successfuly");
			
			
			// Launching the Voot-kids App
			launchApp();
			test.log(LogStatus.INFO, "Application launched successfully");
			
			
			LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
			SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
			HomePageV2 homepagev2 = new HomePageV2(driver, test);
			BasePageV2 BasePageV2 = new BasePageV2(driver, test);
			HashSet<String> set = new HashSet<String>();
			String end1 = "//android.widget.TextView[@text='Create New Profile']";

			// navigating to settings 'Profiles' page 

//					   HomePageV2.signup();

			try {
				if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Succusfully entered to Switch profile page");

				}
			} catch (Exception e) {
				BasePageV2.reportFail("Profile Icon Not Fould to navigate to Switch profile ");
			}
			
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
				settingsPageV2.settingsIcon.click();
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
					settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDevice, 10)) {
						settingsPageV2.settingsDevice.click();
						
// device Ui verifying in settings Page	
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDeviceTile, 10)) {
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.devicecellerPlyback, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.devicedefaultsremquality, 10)) {
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceStremQulAuto, 10)) {
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.devicePrefferdLanuage, 10)) {
											if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceLanubgeEnglish, 10)) {
												if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceEnableddownloads, 10)) {
													String end = "//android.widget.TextView[@text='Clear Cache']";
							                         Utilities.verticalSwipe(driver, end);
							                         
													if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceCelluallerDownlods, 10)) {
														if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQuality, 10)) {
															if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadQul360, 10)) {
																if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceNotifications, 10)) {
																	if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceAllowcasting, 10)) {
																		if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceProfilesPINS, 10)) {
																			if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceClearCache, 10)) {
																				
																				test.log(LogStatus.PASS,
																						"Verified the UI of Device page");
																				settingsPageV2.takeScreenshot();
																				homepagev2.smokeresults("778: Verify the UI of Device page",
																						rowno824, "PASS");
																			}
																		}
																	}
																}
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
							test.log(LogStatus.INFO, "Device Page not able to Naviagting");
							BasePageV2.reportFail("Not able to Naviagt to device Page");
						}
						
	
						
						settingsPageV2.deviceBackBtn.click();
						settingsPageV2.settingsHelpSupport.click();
// Help & Support Ui validating
						
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpContactTile, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpContactNumBTN, 10)) {
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpEmailContactBtn, 10)) {
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpFAQs, 10)) {
											if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpTemsConditions, 10)) {
												if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpPrivacyPolicy, 10)) {
						                           Utilities.verticalSwipe(driver);
						                           if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpAppVersion, 10)) {
						                        	   test.log(LogStatus.PASS,
																"Verify the UI of Help & Support page");
														settingsPageV2.takeScreenshot();
														homepagev2.smokeresults("871: Verify the UI of Help & Support page",
																rowno871, "PASS");
						                        	   
						                           }
												}
											}
										}
									}
								}
							}
							
						
						}else {
							test.log(LogStatus.INFO, "Not ble to navigate to Help and Support Page ");
							BasePageV2.reportFail("Help Page is not Found ");
						}
						
						settingsPageV2.helpPagebackBtn.click();
	// Rate Us feature functionality and Ui verifying 			
						String rateUsText = "//android.widget.TextView[@text='Rate Us']";
						Utilities.verticalSwipe(driver, rateUsText);
						settingsPageV2.settingsRateUS.click();
						Thread.sleep(1000);
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsPopMsg, 10)) {
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsPopSkipForNow, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsPopNoText, 10)) {
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsPopYesText, 10)) {
										 test.log(LogStatus.PASS,
													"Validated the functionality for 'Rate Us' link");
											settingsPageV2.takeScreenshot();
											homepagev2.smokeresults("877: Validate the functionality for 'Rate Us' link",
													rowno877, "PASS");
									}
								}
							}
						}else {
							BasePageV2.reportFail("Not able to Navigated to 'Rate Us' Page ");
						}
						
						// clicking on 'yes' in Pop of Rate Us
						 settingsPageV2.rateUsPopYesText.click();
						   String thatsGreat = settingsPageV2.rateusThatsGreat.getText().toString().trim();
						   String rateusPopMsg = settingsPageV2.rateUsPopMsg.getText().toString().trim();
						   String rateUsBtn = settingsPageV2.rateUsRATEUS.getText().toString().trim();
						   String skipForNow = settingsPageV2.rateUsPopSkipForNow.getText().toString().trim();
						   if(thatsGreat.contains("That’s Great") && rateusPopMsg.contains("How about rating us on the Play Store then") && rateUsBtn.contains("RATE US") && skipForNow.contains("SKIP FOR NOW")) {
							   test.log(LogStatus.PASS,
										"Verified the functionality for 'Rate us on the App Store/Play Store' link by clicking on 'Yes' on the pop-up message");
								settingsPageV2.takeScreenshot();
								settingsPageV2.rateUsPopSkipForNow.click();
								homepagev2.smokeresults("880: Verify the functionality for 'Rate us on the App Store/Play Store' link by clicking on 'Yes' on the pop-up message",
										rowno880, "PASS");
							   
							   
						   }else BasePageV2.reportFail("Tapping 'Yes' Not able to Navigate to That's great Pop");
							// clicking on 'No' in Pop of Rate Us					   
						   if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
							   settingsPageV2.settingsRateUS.click();
							   settingsPageV2.rateUsPopNoText.click();
							   Thread.sleep(1000);
							   if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsSendfeedBack, 10)) {
								   test.log(LogStatus.PASS,
											"Verified the functionality for 'Rate Us' link by tapping on 'No' on the message");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults("880: Verify the functionality for 'Rate Us' link by tapping on 'No' on the message",
											rowno888, "PASS");
							   }
							   
							   settingsPageV2.rateUsSendfeedBack.click();
							   Thread.sleep(1000);
							   if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsSendfeedBackPage, 10)) {
								   test.log(LogStatus.PASS,
											"Verified the functionality of 'send feedback' button in pop-up message");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults("891: Verify the functionality of 'send feedback' button in pop-up message",
										rowno891, "PASS");   
							   }else BasePageV2.reportFail("Not able to naviagted to 'Send Feedback' Page");
							   //passing some text to Send freedBack page 
							   if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsSendFeedBackEditText, 10)) {
								  
								   settingsPageV2.rateUsSendFeedBackEditText.sendKeys("nice entertainment with good collection of Cartoon Videos But need more Cartoon Movies Collections");
								   settingsPageV2.rateUsSendFeedBackSendBtn.click();
								   test.log(LogStatus.PASS,
											"Verified if user can send the feedback successfuly ");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults("892: Verify if user can send the feedback successfuly ",
										rowno892, "PASS");  
									
//									 for(int i =1 ; i<5;i++) {
//											driver.navigate().back();
//											if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
//												break;
//											}
//											
//										}
									
									
							   }else {
								   for(int i =1 ; i<5;i++) {
										driver.navigate().back();
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
											break;
										}
										
									}
								   BasePageV2.reportFail("Not able to send feedback with using Send Button");
							   }
							   
							   
						   }else BasePageV2.reportFail("Not able to Naviagte to 'Rate Us' Page");
						//tap on the 'RATE US' Button
						   if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
							   settingsPageV2.settingsRateUS.click();
							   settingsPageV2.rateUsPopYesText.click();
							   if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsRATEUS, 10)) {
								   settingsPageV2.rateUsRATEUS.click();
								   Thread.sleep(1000);
								   if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsBtnTapplayStore, 10)) { // navigate to 'Play Store'(Voot-Kids Store) page
									   test.log(LogStatus.PASS,
												"Verified the functionality by tapping on 'Rate Us' button in the pop-up");
										settingsPageV2.takeScreenshot();
										settingsPageV2.rateUsPopSkipForNow.click();
										homepagev2.smokeresults("886: Verify the functionality by tapping on 'Rate Us' button in the pop-up",
												rowno886, "PASS");
									   
								   }
								   
								   for(int i =1 ; i<5;i++) {
										driver.navigate().back();
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
											break;
										}
										
									}
								   
						   }
					   }else BasePageV2.reportFail("Not able to tap 'RATE US' Button");
						   
						   
						
						settingsPageV2.settingsShare.click();
// Share Feature Functionality verifing
						Thread.sleep(1000);
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.shareGmail, 10)) {
							settingsPageV2.takeScreenshot();
							settingsPageV2.shareGmail.click();
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.shareGmailCompose, 10)) {
							test.log(LogStatus.PASS,
									"Verified the functionality of share feature by selecting any app");
							settingsPageV2.takeScreenshot();
							homepagev2.smokeresults("867: Verify the functionality of share feature by selecting any app",
									rowno867, "PASS");
							}
							for(int i =1 ; i<5;i++) {
								driver.navigate().back();
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsShare, 10)) {
									break;
								}
								
							}
						}else {
							test.log(LogStatus.INFO, "Not able to naviagte to share page");
							BasePageV2.reportFail("Share Page is not able to Naviagted ");
						}
						

//end	
						
					}
			
				}
			}
	
		}
	
		@DataProvider
		public Object[][] getData() {
			return DataUtil.getData(testName, xls);
		}
		
		
}

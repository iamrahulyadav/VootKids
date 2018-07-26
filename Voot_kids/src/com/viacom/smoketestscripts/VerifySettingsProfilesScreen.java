package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifySettingsProfilesScreen extends BaseTestV2 {
	String testName = "VerifySettingsProfilesScreen";

	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Settings Profiles Page ");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls778 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno778 = xls778.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno778,
				"778: Validate Profile icon functionality.");

		Xls_Reader xls1781 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno781 = xls1781.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno781,
				"781: Verify the functionality by tapping on Back arrow from Profile list screen");

		Xls_Reader xls779 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno779 = xls779.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno779,
				"779: Verify profile settings screen UI if user has created < 6 profiles");

		Xls_Reader xls780 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno780 = xls780.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno780, "780: Verify profile settings screen UI if user has created  6 profiles");

		Xls_Reader xls782 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno782 = xls782.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno782,
				"782: Validate the navigation by tapping on Profile icon/arrow icon in profile list screen");
		
		Xls_Reader xls783 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno783 = xls783.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno783,
				"783: Validate 'Create New Profile' button functionality");
		
		
		Xls_Reader xls784 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno784 = xls784.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno784,
				"784: Verify the Edit profile page UI");
		
		
		/*
		 * Need to add test step code in this class below in between test cases 
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
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		 HashSet<String> set = new HashSet<String>();
		 String end1 = "//android.widget.TextView[@text='Create New Profile']";

		// navigating to settings 'Profiles' page 

//		   HomePageV2.signup();
		  
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
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 20)) {
				settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				settingsPageV2.settingsProfile.click();
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileTile, 10)) {
					test.log(LogStatus.PASS,
							"Validated Profile icon functionality");
					settingsPageV2.takeScreenshot();
					homepagev2.smokeresults("778: Validate Profile icon functionality",
							rowno778, "PASS");
				}
		List<WebElement> lis = driver.findElementsByXPath("//android.widget.LinearLayout[@index='3']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/settings_list']//android.view.ViewGroup//android.widget.TextView");
		int size=lis.size();
		System.out.println("List Size :" + size);
		// HashSet<String> set = new HashSet<String>();
		for(int i = 0 ; i<size; i++) {
			try{
				String str = lis.get(i).getText();
				System.out.println("The List Values are : " + str);
				set.add(str);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		for(int i = 0 ; i<size; i++) {
			String end = "//android.widget.TextView[@text='Create New Profile']";
			Utilities.verticalSwipe(driver, end);
			List<WebElement> lis1 = driver.findElementsByXPath("//android.widget.LinearLayout[@index='3']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/settings_list']//android.view.ViewGroup//android.widget.TextView");
			String str1 = lis1.get(i).getText();
			System.out.println("The List Values are : " + str1);
			set.add(str1);
		}
		
		 System.out.println("Array List Values Size " + set.size());
		    System.out.println("Array Vlues are printed here" + set);
		    set.remove("Create New Profile");
			 System.out.println("Original Array Values Size " + set.size());
			    System.out.println("Original Array Vlues are printed here" + set);
			    if(set.size() <= 6 && set.size() != 0 ) {
			    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.setiingsProfileEditProBckBtn, 10)) {
			    		String end = "//android.widget.ImageView[@resource-id='com.tv.vootkids:id/selected_tick']";
			    		Utilities.verticalSwipe(driver, end);
			    		if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileSelectedTick, 10)) {
//			    			String end1 = "//android.widget.TextView[@text='Create New Profile']";
			    			Utilities.verticalSwipe(driver,end1 );
			    			if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateNewPro, 10)) {
			    				test.log(LogStatus.PASS,
										"Verified profile settings screen UI if user has created < 6 profiles");
			    				settingsPageV2.takeScreenshot();
								homepagev2.smokeresults("779: Verify profile settings screen UI if user has created < 6 profiles",
										rowno779, "PASS");
								test.log(LogStatus.PASS,
										"Verified profile settings screen UI if user has created  6 profiles");
								settingsPageV2.takeScreenshot();
								homepagev2.smokeresults("780: Verify profile settings screen UI if user has created  6 profiles",
										rowno780, "PASS");
			    				
			    			}
			    		}
			    	}
			    }
			    
			    
			    
		}
			
			settingsPageV2.settingsProfileBackBtn.click();
		        if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfile, 10)) {
		        	test.log(LogStatus.PASS,
							"Verified the functionality by tapping on Back arrow from Profile list screen");
		        	settingsPageV2.takeScreenshot();
					homepagev2.smokeresults("781: Verify the functionality by tapping on Back arrow from Profile list screen",
							rowno781, "PASS");
					settingsPageV2.settingsProfile.click();
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateImg, 10)) {
		            	settingsPageV2.settingsProfileCreateImg.click();
		            	String editText = settingsPageV2.editTextinEditProfile.getText();
		            	System.out.println("Edit Profile Text should be : " +editText);
		            	if(set.contains(editText) ) {
		            		System.out.println("Set Contains the String profile index[0] " + editText );
		            		test.log(LogStatus.PASS,
									"Validated the navigation by tapping on Profile icon/arrow icon in profile list screen");
		            		settingsPageV2.takeScreenshot();
							homepagev2.smokeresults("781: Validate the navigation by tapping on Profile icon/arrow icon in profile list screen",
									rowno782, "PASS");
							
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsEditProfilePageTile, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.setiingsProfileEditProBckBtn, 10)) {
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProEditProName, 10)) {
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProEditProDateOfBirth, 10)) {
											if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsEditProfileDelProBtn, 10)) {
												if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsEditProfilrNextBtn, 10)) {
								            		test.log(LogStatus.PASS,
															"Verified the Edit profile page UI");
								            		settingsPageV2.takeScreenshot();
													homepagev2.smokeresults("781: Verify the Edit profile page UI",
															rowno784, "PASS");
												}
											}
										}
										
									}
								}
								
							}
							
							
							
							
							
							 settingsPageV2.setiingsProfileEditProBckBtn.click();
							 Utilities.verticalSwipe(driver, end1);
							 settingsPageV2.settingsProfileCreatProfileTile.click();
					            if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreatProfileTile, 10)) {
					            	test.log(LogStatus.PASS,
											"Validated 'Create New Profile' button functionality");
				            		settingsPageV2.takeScreenshot();
									homepagev2.smokeresults("781: Validate 'Create New Profile' button functionality",
											rowno783, "PASS");
					            }
							
		            	}
		            }
		            
		            
		        }		
		       
	}else {
		BasePageV2.reportFail("Settings Icon not found for navigating to Settings Page");
	}
		
	

			}
		
				
	

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}

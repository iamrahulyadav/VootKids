package com.viacom.smoketestscripts;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class VerifySwitchProfileFunctionalityTest extends BaseTestV2{
	String testName = "VerifySwitchProfileFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Switch Profile screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Switch Profile screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify Switch Profile page UI:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int editrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",editrow , "Verify the click functionality of 'Edit Profile Buddy' button:");	
	
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int closerow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",closerow, "Validate Close icon functionality:");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int settingsrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",settingsrow, "Validate Settings button functionality:");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int createrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",createrow, "Validate 'Create new' button functionality:");	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int switchrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",switchrow , "Verify the functionality of switch profile icons:");	
	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	
	
	String un=data.get("Email");
	String pwd=data.get("Password");
	
	test.log(LogStatus.INFO, "Navigating to Switch Profile screen");
	
	//click on profile icon
	if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 10))
		homepagev2.profilepic.click();
	
	//Check whether naviagted to Switch Profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenToolBarTitle, 20))
	{
	 test.log(LogStatus.INFO,"Navigated to Switch Profile Screen successfully");
	}
	else
	BasePageV2.reportFail("Clicking on Back Arrow not navigated to Switch Profile Screen");	

	
	//Checking edit profile Buddy button displayed or not
	if(Utilities.explicitWaitVisible(driver, launchpagev2.editProfileBuddyButton, 20))
    test.log(LogStatus.INFO, "Edit Profile Buddy button is displayed");
	else
	BasePageV2.reportFail("Edit Profile Buddy button is not displayed");
	
	//Checking Parent Zone button displayed or not	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.parentZoneButton, 20))
	test.log(LogStatus.INFO, "Parent zone button is displayed");
	else
	BasePageV2.reportFail("Parent zone button is not displayed");
	
	//Check Settings button dispalyed or not
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.settingsInSwitchProfileScreen, 20))
	test.log(LogStatus.INFO, "Settings button is displayed");
	else
	BasePageV2.reportFail("Settings button is not displayed");
	
	//Check cancel button displayed or not
	if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileCancelButton, 20))
	test.log(LogStatus.INFO, "Cancel button is displayed");
	else
	BasePageV2.reportFail("Cancel button is not displayed");
	
	
	//CERATED Profile name displayed or not
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenProfileName, 20))
	test.log(LogStatus.INFO, "Created kids Profile name is displayed");
	else
	BasePageV2.reportFail("Created Kids Profile name is not displayed");
	
	
	//Current Profile ticked or not
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenSelectedProfile, 20))
	test.log(LogStatus.INFO, "Tick icon present on the current profile");
	else
	BasePageV2.reportFail("Tick icon not present on the current profile");
	
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenProfileAvatar, 20))
		test.log(LogStatus.INFO, "Profile avatar is displayed");
		else
		BasePageV2.reportFail("Profile avatar is not displayed");
	
	   if(Utilities.explicitWaitVisible(driver, launchpagev2.createNewIcon, 20))
	 	test.log(LogStatus.INFO, "Create New Profile Icon is displayed");
		else
		BasePageV2.reportFail("Create New Profile icon is not displayed");
	
	
	BasePageV2.reportPass("Testcase : 'Verify Switch Profile page UI:' is Passed");
	BasePageV2.smokeresults("", uirow, "Pass");
	
	
	//Checking edit profile Buddy button displayed or not
	if(Utilities.explicitWaitClickable(driver, launchpagev2.editProfileBuddyButton, 20))
		launchpagev2.editProfileBuddyButton.click();
	else
	BasePageV2.reportFail("Not able to click on Edit Profile Buddy button");

	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.editProfileBuddyScreenToolBarTitle, 20))
	{
		test.log(LogStatus.INFO, "Navigated to Edit Profile Buddy screen");
		BasePageV2.reportPass("Testcase : 'Verify the click functionality of 'Edit Profile Buddy' button:' is passed");
		BasePageV2.smokeresults("", editrow, "Pass");
	}
	else
	{
		test.log(LogStatus.INFO, "Not able to click on Edit Profile Buddy button");
		BasePageV2.takeScreenshot();
		
	}
	
	driver.pressKeyCode(AndroidKeyCode.BACK);
	
	   /* if(Utilities.explicitWaitClickable(driver, launchpagev2.parentZoneButton, 20))
		launchpagev2.parentZoneButton.click();
		else
		BasePageV2.reportFail("Not able to click on Parent zone button");*/
	
	//Verifying cancel button Functionlaity
	
	test.log(LogStatus.INFO, "Verifying cancel button Functionality");
	
	if(Utilities.explicitWaitClickable(driver, launchpagev2.switchProfileCancelButton, 20))
	launchpagev2.switchProfileCancelButton.click();
	else
	BasePageV2.reportFail("Not able to click Cancel button");
	
	if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 20))
	{
		try{
		  if(homepagev2.mystuff_tab.getAttribute("selected").equals("true")) 
			  {
				 BasePageV2.reportPass("Testcase : 'Validate Close icon functionality:' is Passed");
				 BasePageV2.smokeresults("", closerow, "Pass");
			  }
			  else
					{
				       BasePageV2.takeScreenshot();
				       test.log(LogStatus.FAIL, "Not navigated back to My Stuff page with 'My Stuff' area highlighted in top bar menu");
					}
		  homepagev2.profilepic.click();
		}
		catch(Exception e){
			
		}
	}
	else
		 test.log(LogStatus.FAIL, "Not able to click Close icon in Switch profile screen");
	
	test.log(LogStatus.INFO, "Validating Settings button functionality");
	
	if(Utilities.explicitWaitClickable(driver, homepagev2.settings, 20))
	{
		homepagev2.settings.click();
	}
	else
		BasePageV2.reportFail("Not able to click Settings icon in Switch profile screen");	
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 20))
	{
		BasePageV2.reportPass("Testcase : 'Tapping on Settings icon should be navigated to PIN - enter screen' is Passed");
	    BasePageV2.smokeresults("", settingsrow , "Pass");
	}
	else
		{
		// BasePageV2.takeScreenshot();
		 BasePageV2.reportFail("Not navigated to PIN - enter screen");	
		}
	
	if(Utilities.explicitWaitClickable(driver, launchpagev2.backButton, 20))
	{
		launchpagev2.backButton.click();
	}
	else
		BasePageV2.reportFail("Not able to click back button / Back button is not present");
	
	test.log(LogStatus.INFO, "Validating 'Create new' button functionality:");
	if(Utilities.explicitWaitClickable(driver, launchpagev2.createNewIcon, 20))
	{
		launchpagev2.createNewIcon.click();
	}
	else
		BasePageV2.reportFail("Not able to click Create New Profile button / button is not present");
	
	if(Utilities.explicitWaitClickable(driver, launchpagev2.parentPinContainer, 20))
	{
		launchpagev2.parentPinContainer.sendKeys("1111");
	}
	else
		BasePageV2.reportFail("Not able to click Create New Profile button / button is not present");
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileTellUsFirstScreen, 20))
	{
		BasePageV2.reportPass("Testcase : 'Validate 'Create new' button functionality:' is Passed");
		BasePageV2.smokeresults("", createrow, "Pass");
	}
	else
		BasePageV2.reportFail("Not able to click Create New Profile icon / icon is not present");
	
	driver.pressKeyCode(AndroidKeyCode.BACK);
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenProfileAvatar, 20))
	{
	
		try{
			test.log(LogStatus.INFO, "Before Switching , Selected Profile name is : "+launchpagev2.selectedProfileName.getText());
		}
		catch(Exception e)
		{
			BasePageV2.reportFail("Profile Name is not displayed for the selected profile");
		}
		BasePageV2.takeScreenshot();
	}
	else
		BasePageV2.reportFail("Avatar is missing for the profile");
	
	
	String profname="";
	if(Utilities.explicitWaitVisible(driver, launchpagev2.unselectedProfileAvatar, 20))
	{
		try{
		profname=launchpagev2.unselectedProfileName.getText();	
			test.log(LogStatus.INFO, "Now , Switching to Profile Name : "+profname);
		}
		catch(Exception e)
		{
			BasePageV2.reportFail("Profile Name is not displayed for one of the profile");
		}
		launchpagev2.unselectedProfileAvatar.click();
	}
	else
		BasePageV2.reportFail("Not able to click the profile which is not selected");
	
	
	test.log(LogStatus.INFO, "Validating whether profile is successfully switched to the selected profile");
	
	Thread.sleep(15000);
	if(Utilities.explicitWaitVisible(driver, launchpagev2.selectedProfileName, 20))
	{
		if(launchpagev2.selectedProfileName.getText().equals(profname))
			{
			BasePageV2.reportPass("Testcase : 'Verify the functionality of switch profile icons:' is Passed");
			BasePageV2.smokeresults("", switchrow, "Pass");
			}
		else
			BasePageV2.reportFail("Not switched to the selected profile");
	}
	else
		BasePageV2.reportFail("Profile name is missing for the selected profile");
		
	
	
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}

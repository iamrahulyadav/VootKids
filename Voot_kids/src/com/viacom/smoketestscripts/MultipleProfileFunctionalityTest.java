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

import io.appium.java_client.android.AndroidKeyCode;

public class MultipleProfileFunctionalityTest extends BaseTestV2{
	String email="karthik5278@yahoo.com";
	String validPwd="karu5278";
	String testName="MultipleProfileFunctionalityTest";
	
	@Test(dataProvider = "getData")
	public void multiProfileFunctionalityTest(Hashtable<String, String> data) throws Exception{
		if(GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Login Functionality");
		test.log(LogStatus.INFO, "Starting the test for Verify the UI for login screen: "+VootConstants.DEVICE_NAME);
		
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		//Verify profile selection in 'Select Profile' screen
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno , "Verify profile selection in 'Select Profile' screen");
		
		//Verify 'Create new' button functionality in 'Select Profile' screen
		
		Xls_Reader xls1 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno1=xls1.getRowCount("Smoke_Results")+1;
		xls1.setCellData("Smoke_Results", "Testcase Name",rowno1 , "Verify 'Create new' button functionality in 'Select Profile' screen");
		
		
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		
		test.log(LogStatus.INFO, "Verifying multi-profile user login");
		test.log(LogStatus.INFO, "Clicking on Login gateway button");
		BasePageV2.takeScreenshot();
		try {
			  //ii
			HomePageV2.login(email, validPwd);
			if(Utilities.explicitWaitVisible(driver,launchPageV2.selectProfilePage, 20))
			{
				if(launchPageV2.selectProfilePage.isDisplayed()){
					test.log(LogStatus.INFO, "Select Profile page is displayed");
					test.log(LogStatus.INFO, "Login for multiple user profile is taking to select profile page");
					homepagev2.smokeresults("Verify profile selection in 'Select Profile' screen",rowno, "Pass");
					BasePageV2.takeScreenshot();
				}
				else{
					test.log(LogStatus.FAIL,"Select Profile page page is not displayed for multi-profile user");
					homepagev2.smokeresults("Verify profile selection in 'Select Profile' screen",rowno, "Fail");					
				}
			}


			if(Utilities.explicitWaitVisible(driver,launchPageV2.selectProfileIcon, 20))
			{
				if(launchPageV2.selectProfileIcon.isDisplayed()){
					test.log(LogStatus.INFO, "Selecting profile from multiple profile");
					launchPageV2.selectProfileIcon.click();
					Thread.sleep(5000);
				}
				else{
					test.log(LogStatus.FAIL,"Unable to select profile");
					homepagev2.smokeresults("Verify profile selection in 'Select Profile' screen",rowno, "Fail");
					BasePageV2.takeScreenshot();					
				}
			}
			//Verify 'Create new' button functionality in 'Select Profile' screen

			if(Utilities.explicitWaitVisible(driver,launchPageV2.rightTickIcon, 20))
			{
				if(launchPageV2.rightTickIcon.isDisplayed()) {
					test.log(LogStatus.PASS, "Test Case Verify profile selection in 'Select Profile' screen is passed");
					homepagev2.smokeresults("Verify profile selection in 'Select Profile' screen",rowno, "Pass");
					BasePageV2.takeScreenshot();

				}
				else {
					test.log(LogStatus.FAIL, "Test Case Verify profile selection in 'Select Profile' screen is passed");
					homepagev2.smokeresults("Verify profile selection in 'Select Profile' screen",rowno, "Fail");
					BasePageV2.takeScreenshot();
				}
			}
			
			//Verify 'Create new' button functionality in 'Select Profile' screen
			if(Utilities.explicitWaitVisible(driver,launchPageV2.createNewLink, 20))
			{
				if(launchPageV2.createNewLink.isDisplayed()) {
					test.log(LogStatus.INFO, "Clicking on create new link");
					launchPageV2.createNewLink.click();
					if(Utilities.explicitWaitVisible(driver,launchPageV2.createProfilePage, 20)) {
						if(launchPageV2.createProfilePage.isDisplayed()) {
							test.log(LogStatus.INFO, "Create Profile Page is displayed");
							test.log(LogStatus.PASS, "Test Case Verify 'Create new' button functionality in 'Select Profile' screen is Passed");
							homepagev2.smokeresults("Verify 'Create new' button functionality in 'Select Profile' screen",rowno1, "Pass");
							BasePageV2.takeScreenshot();
							driver.pressKeyCode(AndroidKeyCode.BACK);
							Thread.sleep(3000);
							
						}else {
							test.log(LogStatus.FAIL, "Test Case Verify 'Create new' button functionality in 'Select Profile' screen is Fail");
							homepagev2.smokeresults("Verify 'Create new' button functionality in 'Select Profile' screen",rowno1, "Fail");
							BasePageV2.takeScreenshot();
							launchPageV2.backButton.click();
						}
					}
				}
				else {
					test.log(LogStatus.FAIL, "Test Case Verify 'Create new' button functionality in 'Select Profile' screen is fail");
					homepagev2.smokeresults("Verify 'Create new' button functionality in 'Select Profile' screen",rowno1, "Fail");
					BasePageV2.takeScreenshot();
				}
			}
			
			WebElement continueButton=driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Continue')]"));
			if(Utilities.explicitWaitVisible(driver,continueButton, 20))
				if(continueButton.isDisplayed()){
					test.log(LogStatus.INFO, "Clicking on continue button in Select profile page");
					continueButton.click();
					BasePageV2.takeScreenshot();
				}
				else{
					test.log(LogStatus.FAIL,"Unable to click on continue button");
					homepagev2.smokeresults("Verify 'Create new' button functionality in 'Select Profile' screen",rowno1, "Fail");
					BasePageV2.takeScreenshot();					
				}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click on continue button");
			BasePageV2.takeScreenshot();	
		}
			HomePageV2.logout();
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}

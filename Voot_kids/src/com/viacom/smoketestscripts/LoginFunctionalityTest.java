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

public class LoginFunctionalityTest extends BaseTestV2{
	String testName = "LoginFunctionalityTest";
	String ScreenTitle="Login";
	String headerText="Enter your details";
	String emailHeader="Mobile No./ Email";
	String pwdHeader="Password";
	String dotText="•••••••";
	String deviderText="----Or-----";
	String newMember="Not a member?";
	String email="daya123@daya.com";
	String invalidEmail="dayaabc@daya";
	String nonExixtingUser="dayaabc@daya.in";
	String validPwd="daya123";
	String invalidPwd="123456";
	String ErrorMessagePwd="That’s an incorrect password. Please try again.";
	String invalidEmailandPwd="User does not exist";
	String blankPwd="Please enter a valid password";
	String ErrorMessageEmail="Invalid Mobile No./ Email";
	String passwordErrormsg="Invalid user ID and/or password";
	String invalidEmailIDError="Please enter email Id or phone number";
	String loginBlank="Please enter valid Mobile number/Email Id";
	String validMobileNumber="9898478987";
	String validMobilePassword="pavan123";
	String invalidMobileNumber="9658977474";
	
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
		if(GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Login Functionality");
		test.log(LogStatus.INFO, "Starting the test for Verify the UI for login screen: "+VootConstants.DEVICE_NAME);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno , "Verify the UI for login screen");	
		
		Xls_Reader xls1 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno1=xls1.getRowCount("Smoke_Results")+1;
		xls1.setCellData("Smoke_Results", "Testcase Name",rowno1 , "Verify the functionality of back arrow button in login screen");	
		
		Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno2=xls2.getRowCount("Smoke_Results")+1;
		xls2.setCellData("Smoke_Results", "Testcase Name",rowno2 , "Verify the login screen by giving invalid input in Password field");	
		
		Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno3=xls3.getRowCount("Smoke_Results")+1;
		xls3.setCellData("Smoke_Results", "Testcase Name",rowno3 , "Verify the Password eye functionality by tapping on eye icon in Password field");	
		
		Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno4=xls4.getRowCount("Smoke_Results")+1;
		xls4.setCellData("Smoke_Results", "Testcase Name",rowno4 , "Verify the  login screen by giving invalid input in email id field ");	
		
		Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno5=xls5.getRowCount("Smoke_Results")+1;
		xls5.setCellData("Smoke_Results", "Testcase Name",rowno5 , "Verify the  login screen by giving invalid input in both fields ");
		
		Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno6=xls6.getRowCount("Smoke_Results")+1;
		xls6.setCellData("Smoke_Results", "Testcase Name",rowno6 , "Verify the login screen by not giving any inputs to any of the fields ");
		
		Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno7=xls7.getRowCount("Smoke_Results")+1;
		xls7.setCellData("Smoke_Results", "Testcase Name",rowno7 , "Verify the click functionality of 'Forgot Password?' link ");
		
		Xls_Reader xls8 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno8=xls8.getRowCount("Smoke_Results")+1;
		xls8.setCellData("Smoke_Results", "Testcase Name",rowno8 , "Verify the click functionality of 'Sign Up' link ");
		
		Xls_Reader xls9 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno9=xls9.getRowCount("Smoke_Results")+1;
		xls9.setCellData("Smoke_Results", "Testcase Name",rowno9 , "Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password ");
		
		Xls_Reader xls10 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno10=xls10.getRowCount("Smoke_Results")+1;
		xls10.setCellData("Smoke_Results", "Testcase Name",rowno10 , "Verify the the 'Login' button functionality by giving non registered Mobile number in login screen ");
		
		Xls_Reader xls11 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno11=xls11.getRowCount("Smoke_Results")+1;
		xls11.setCellData("Smoke_Results", "Testcase Name",rowno11 , "Verify country code is autofilled as user enteres mobile no. in the text field ");
		
		Xls_Reader xls12 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno12=xls12.getRowCount("Smoke_Results")+1;
		xls12.setCellData("Smoke_Results", "Testcase Name",rowno12 , "Verify the the 'Login' button functionality by giving valid Mobile number in login screen ");
		
		Xls_Reader xls13 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno13=xls13.getRowCount("Smoke_Results")+1;
		xls13.setCellData("Smoke_Results", "Testcase Name",rowno13 , "Verify the click functionality of 'Login' link in Welcome screen ");
		
		
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25)) {
				test.log(LogStatus.INFO, "Welcome screen displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.INFO, "Clicking on login button");
				launchPageV2.loginGateway.click();
				if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
					if(launchPageV2.emailText.isDisplayed()) {
						test.log(LogStatus.PASS, "Test Case 'Verify the click functionality of 'Login' link in Welcome screen' is passed");
						BasePageV2.takeScreenshot();
						homepagev2.smokeresults("Verify the click functionality of 'Login' link in Welcome screen",rowno13, "Pass");
					}
					else {
						test.log(LogStatus.FAIL, "Test Case 'Verify the click functionality of 'Login' link in Welcome screen' is fail");
						BasePageV2.takeScreenshot();
						homepagev2.smokeresults("Verify the click functionality of 'Login' link in Welcome screen",rowno13, "Fail");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Test Case 'Verify the click functionality of 'Login' link in Welcome screen' is fail");
					BasePageV2.takeScreenshot();
					homepagev2.smokeresults("Verify the click functionality of 'Login' link in Welcome screen",rowno13, "Fail");
				}
			}
			else
			test.log(LogStatus.FAIL, "Unable to find Login Gateway button");
			test.log(LogStatus.INFO, "Clicking on Login Gateway button");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click on Login Gateway button");
			test.log(LogStatus.FAIL,"Unable to click on Login Gateway button");
			homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
		}
		
		//1. Verify the UI for login screen
		test.log(LogStatus.INFO, "Verify the UI for login screen");
		try {
			BasePageV2.takeScreenshot();
			//Verifying Screen title - "Login" at the top of screen 
			if(Utilities.explicitWaitVisible(driver, launchPageV2.headerText, 25))
				test.log(LogStatus.INFO, "Login Screen title found");
			
			String loginHeader= launchPageV2.headerText.getText().toString();
			if(loginHeader.equalsIgnoreCase(ScreenTitle))
				test.log(LogStatus.INFO, "Verified Screen title - Login at the top of screen is displayed: "+loginHeader);
			else
			{
//				test.log(LogStatus.FAIL, "Verified Screen title - Login at the top of screen is not displayed: "+loginHeader);
				test.log(LogStatus.FAIL,"Verified 'Screen title - Login at the top of screen' is not displayed: "+loginHeader);
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			//Verifying "Enter your details" written at the bottom of "login" text
			if(Utilities.explicitWaitVisible(driver, launchPageV2.headerSubtitle, 5))
				test.log(LogStatus.INFO, "Login header found");
			
			String textHeader=launchPageV2.headerSubtitle.getText().toString();
			if(textHeader.equalsIgnoreCase(headerText))
				test.log(LogStatus.INFO, "Verified 'Enter your details' written at the bottom of login text is displayed: "+textHeader);
			else {
//				test.log(LogStatus.FAIL, "Verified Screen title - Login at the top of screen is not displayed: "+textHeader);
				test.log(LogStatus.FAIL,"Verified 'Enter your details' written at the bottom of login text is not displayed");
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			//Back button the top left
			if(Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5))
				test.log(LogStatus.INFO, "Back button");
			
			if(launchPageV2.backButton.isDisplayed())
				test.log(LogStatus.INFO, "'Back button the top left' is displayed: ");
			else {
//				test.log(LogStatus.FAIL, "Back button the top left is not displayed ");
				test.log(LogStatus.FAIL,"'Back button the top left' is not displayed");
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			//"Mobile/Email ID" field
			if(Utilities.explicitWaitVisible(driver, launchPageV2.textAboveEmailTextField, 5))
				test.log(LogStatus.INFO, "Email suggestion text");
			BasePageV2.takeScreenshot();
			String headerEmail=launchPageV2.textAboveEmailTextField.getText().toString();
			if(headerEmail.equalsIgnoreCase(emailHeader))
				test.log(LogStatus.INFO, "'Mobile/Email ID Text' is displayed: "+headerEmail);
			else {
//				test.log(LogStatus.FAIL, "Mobile/Email ID Text is not displayed: "+headerEmail);
				test.log(LogStatus.FAIL,"'Mobile/Email ID Text' is not displayed: "+headerEmail);
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			
			// Password field
			if(Utilities.explicitWaitVisible(driver, launchPageV2.textAbovepwdTextField, 5))
				test.log(LogStatus.INFO, "Password suggestion text");
			
			String headerPwd=launchPageV2.textAbovepwdTextField.getText().toString();
			if(headerPwd.equalsIgnoreCase(pwdHeader))
				test.log(LogStatus.INFO, "'Password' Text is displayed: "+headerPwd);
			else {
//				test.log(LogStatus.FAIL, "Password Text is not displayed: "+headerPwd);
				test.log(LogStatus.FAIL,"'Password' Text is not displayed: "+headerPwd);
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			//"Password" field with "eye icon" button 
			if(Utilities.explicitWaitVisible(driver, launchPageV2.showPwd, 5))
//				test.log(LogStatus.INFO, "Password field with eye icon button is displayed");
			if(launchPageV2.showPwd.isDisplayed())
				test.log(LogStatus.INFO, "'Password field with eye icon' button is displayed ");
			else {
//				test.log(LogStatus.FAIL, "Password field with eye icon button is not displayed ");
				test.log(LogStatus.FAIL,"'Password field with eye icon button' is not displayed");
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			
			//Login button 
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5))
//				test.log(LogStatus.INFO, "Login button is displayed");
			if(launchPageV2.loginButton.isDisplayed())
				test.log(LogStatus.INFO, "'Login button' is displayed ");
			else {
//				test.log(LogStatus.FAIL, "Login button is not displayed ");
				test.log(LogStatus.FAIL,"'Login button' is not displayed ");
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			// Forgot Password link
			if(Utilities.explicitWaitVisible(driver, launchPageV2.forgotPwd, 5))
//				test.log(LogStatus.INFO, "Forgot Password link is displayed");
			if(launchPageV2.forgotPwd.isDisplayed())
				test.log(LogStatus.INFO, "'Forgot Password link' is displayed ");
			else {
//				test.log(LogStatus.FAIL, "Forgot Password link is not displayed ");
				test.log(LogStatus.FAIL,"'Forgot Password link' is not displayed ");
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			
			// "----Or-----" text below Forgot Password link underlined
			if(Utilities.explicitWaitVisible(driver, launchPageV2.OrDivider, 5))
//				test.log(LogStatus.INFO, "----Or----- text below Forgot Password link underlined is displayed");
			if(launchPageV2.OrDivider.isDisplayed())
				test.log(LogStatus.INFO, "'----Or----- 'text below Forgot Password link underlined is displayed is displayed ");
			else {
//				test.log(LogStatus.FAIL, "----Or----- text below Forgot Password link underlined is displayed is not displayed ");
				test.log(LogStatus.FAIL,"'----Or-----' text below Forgot Password link underlined is displayed is not displayed ");
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			
			//Not a member ? Sign Up (link)
			if(Utilities.explicitWaitVisible(driver, launchPageV2.notaMemberText, 5))
				test.log(LogStatus.INFO, "New member link verification");
			String notMember=launchPageV2.notaMemberText.getText().toString();
			if(notMember.equalsIgnoreCase(newMember))
				test.log(LogStatus.INFO, "'Not a member ?' is displayed: "+notMember);
			else {
//				test.log(LogStatus.FAIL, "Not a member ? is not displayed: "+notMember);
				test.log(LogStatus.FAIL,"'Not a member ?' is not displayed: "+notMember);
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
			WebElement element=driver.findElementByXPath("//android.widget.TextView[contains(@text,'sign up')]");
			if(Utilities.explicitWaitVisible(driver, element, 5))
//				test.log(LogStatus.INFO, "Sign Up (link) is displayed");
			if(element.isDisplayed())
				test.log(LogStatus.INFO, "'Sign Up (link)' is displayed ");
			else {
//				test.log(LogStatus.FAIL, "Sign Up (link) is not displayed ");
				test.log(LogStatus.FAIL,"'Sign Up (link)' is not displayed ");
				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
				BasePageV2.takeScreenshot();
//				homepagev2.smokeresults("Verify the UI for login screen",rowno, "Pass");
			}
		}catch(Exception e) {
//			test.log(LogStatus.FAIL, "UI for login screen");
			test.log(LogStatus.FAIL,"UI for login screen");
			homepagev2.smokeresults("Verify the UI for login screen",rowno, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, "Test Case 'Verify the UI for login screen' is passed");
		homepagev2.smokeresults("Verify the UI for login screen",rowno, "Pass");
		BasePageV2.takeScreenshot();

		//2. Verify the functionality of back arrow button in login screen
		test.log(LogStatus.INFO, "Verify the functionality of back arrow button in login screen");
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 25)) {
				test.log(LogStatus.INFO, "Back button is visible");
				launchPageV2.backButton.click();
			}
			else {
				test.log(LogStatus.FAIL,"Back button is not visible");
				homepagev2.smokeresults("Verify the functionality of back arrow button in login screen",rowno1, "Fail");
				BasePageV2.takeScreenshot();
			}	
			
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Back button is not visible");
			homepagev2.smokeresults("Verify the functionality of back arrow button in login screen",rowno1, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
		}
		
	//3. Verify the login screen by giving invalid input in "Password" field
		test.log(LogStatus.INFO, "Verify the login screen by giving invalid input in Password field");
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25)) {
				try {
				if(launchPageV2.loginGateway.isDisplayed()) {
					test.log(LogStatus.INFO, "Welcome screen is visible");
					test.log(LogStatus.PASS, "Test Case 'Verify the functionality of back arrow button in login screen' is passed");
					homepagev2.smokeresults("Verify the functionality of back arrow button in login screen",rowno1, "Pass");
					BasePageV2.takeScreenshot();
				}
				else {
					test.log(LogStatus.FAIL,"Welcome screen is not visible");
					homepagev2.smokeresults("Verify the functionality of back arrow button in login screen",rowno1, "Fail");
					BasePageV2.takeScreenshot();
				}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL,"Welcome screen is not visible");
					homepagev2.smokeresults("Verify the functionality of back arrow button in login screen",rowno1, "Fail");
					BasePageV2.takeScreenshot();
				}
				test.log(LogStatus.INFO, "Clicking on Login Gateway button");
				launchPageV2.loginGateway.click();
			}
			else
				test.log(LogStatus.FAIL, "Unable to find Login Gateway button");
		}catch (Exception e) {
//			test.log(LogStatus.FAIL, "Unable to click on Login Gateway button");
			test.log(LogStatus.FAIL,"Unable to click on Login Gateway button");
			homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
		}
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(email);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Email text field");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Fail");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(invalidPwd);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Fail");
				BasePageV2.takeScreenshot();
			}
			
			//Verify the "Password eye functionality" by tapping on eye icon in "Password" field
			try {
					test.log(LogStatus.INFO, "Checking if Dotted text visible before clicking on eye icon");
				String dots=launchPageV2.pwdText.getText().toString();
			
				if(!dots.contains(invalidPwd)) {
					test.log(LogStatus.INFO, "Dotted text visible before clicking on eye icon");
					BasePageV2.takeScreenshot();
				}
			
			else {
				test.log(LogStatus.FAIL,"Dotted text not visible ");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno3, "Fail");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.showPwd, 25))
			{
				launchPageV2.showPwd.click();
				test.log(LogStatus.INFO, "Eye Icon is visible");
				BasePageV2.takeScreenshot();
			}
			else {
				test.log(LogStatus.FAIL,"Eye Icon not visible");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno3, "Fail");
				BasePageV2.takeScreenshot();
			}
			
			test.log(LogStatus.INFO, "Checking if Normal text is visible after clicking on eye icon");
			String numbers=launchPageV2.pwdText.getText().toString();
		
			if(numbers.contains(invalidPwd)) {
				test.log(LogStatus.INFO, "Normal text visible: "+numbers);
				BasePageV2.takeScreenshot();
			}
		else {
			test.log(LogStatus.FAIL,"Normal text not visible: "+numbers);
			homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno3, "Fail");
			BasePageV2.takeScreenshot();
		}
			test.log(LogStatus.PASS, "Test Case Verify the Password eye functionality by tapping on eye icon in Password field is passed");
			homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno3, "Pass");
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL,"Verify the Password eye functionality by tapping on eye icon in Password field");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno3, "Fail");
				BasePageV2.takeScreenshot();
				e.printStackTrace();
			}
			test.log(LogStatus.INFO, "Clicking on login button");
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
				test.log(LogStatus.INFO, "Clicking on login button");
				launchPageV2.loginButton.click();
			}
			else {
				test.log(LogStatus.FAIL,"Login button is not displayed");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Fail");
				BasePageV2.takeScreenshot();
			}
			WebElement common=driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'com.tv.vootkids:id/common_error_message')]"));
			if(Utilities.explicitWaitVisible(driver, common, 5)) 
				test.log(LogStatus.INFO, "Error message displayed");
				String pwdError=common.getText().toString();
				test.log(LogStatus.INFO, "Expected Title: "+passwordErrormsg);
				test.log(LogStatus.INFO, "Actual Title: "+pwdError);
			if(pwdError.equalsIgnoreCase(passwordErrormsg)) {
				test.log(LogStatus.PASS, "Test Case Verify the login screen by giving invalid input in Password field is passed");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Pass");
				BasePageV2.takeScreenshot();
			}
			else {
				test.log(LogStatus.FAIL,"Verify the login screen by giving invalid input in Password field is fail");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Fail");
				BasePageV2.takeScreenshot();
			}
			homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Pass");
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Verify the login screen by giving invalid input in Password field");
			homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno2, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
		}
		
		//5. Verify the  login screen by giving invalid input (Non Registered user) in email id field 
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(nonExixtingUser);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Email text field");
				homepagev2.smokeresults("Verify the login screen by giving invalid input in Password field",rowno4, "Fail");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(validPwd);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
				homepagev2.smokeresults("Verify the  login screen by giving invalid input in email id field",rowno4, "Fail");
				BasePageV2.takeScreenshot();
			}
			test.log(LogStatus.INFO, "Clicking on login button");
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
				test.log(LogStatus.INFO, "Clicking on login button");
				launchPageV2.loginButton.click();
			}
			else {
				test.log(LogStatus.FAIL,"Login button is not displayed");
				homepagev2.smokeresults("Verify the  login screen by giving invalid input in email id field",rowno4, "Fail");
				BasePageV2.takeScreenshot();
			}
			
				String emailError=launchPageV2.commonErrors.getText().toString();
				test.log(LogStatus.INFO, "Expected Title: "+invalidEmailandPwd);
				test.log(LogStatus.INFO, "Actual Title: "+emailError);
				if(emailError.equalsIgnoreCase(invalidEmailandPwd)) {
					test.log(LogStatus.PASS, "Test Case  Verify the  login screen by giving invalid input in email id field is passed");
					homepagev2.smokeresults("Verify the  login screen by giving invalid input in email id field",rowno4, "Pass");
					BasePageV2.takeScreenshot();
				}
				else {
					test.log(LogStatus.FAIL,"Verify the  login screen by giving invalid input in email id field is fail");
					homepagev2.smokeresults("Verify the  login screen by giving invalid input in email id field",rowno4, "Fail");
					BasePageV2.takeScreenshot();
				}
				homepagev2.smokeresults("Verify the  login screen by giving invalid input in email id field",rowno4, "Pass");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Verify the  login screen by giving invalid input in email id field is fail");
			homepagev2.smokeresults("Verify the  login screen by giving invalid input in email id field",rowno4, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
			}
		
		//Verify the  login screen by giving invalid input in both fields
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(invalidEmail);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Email text field");
				homepagev2.smokeresults("Verify the  login screen by giving invalid input in both fields",rowno5, "Fail");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(invalidPwd);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
				homepagev2.smokeresults("Verify the  login screen by giving invalid input in both fields",rowno5, "Fail");
				BasePageV2.takeScreenshot();
			}
			test.log(LogStatus.INFO, "Clicking on login button");
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
				test.log(LogStatus.INFO, "Clicking on login button");
				launchPageV2.loginButton.click();
			}
			else {
				test.log(LogStatus.FAIL,"Login button is not displayed");
				homepagev2.smokeresults("Verify the  login screen by giving invalid input in both fields",rowno5, "Fail");
				BasePageV2.takeScreenshot();
			}
			Thread.sleep(2000);
				String emailError1=launchPageV2.emailidError.getText().toString();
				test.log(LogStatus.INFO, "Expected Title: "+invalidEmailIDError);
				test.log(LogStatus.INFO, "Actual Title: "+emailError1);
				if(emailError1.equalsIgnoreCase(invalidEmailIDError)) {
					test.log(LogStatus.PASS, "Test Case  Verify the  login screen by giving invalid input in both fields is passed");
					homepagev2.smokeresults("Verify the  login screen by giving invalid input in both fields",rowno5, "Pass");
					BasePageV2.takeScreenshot();
				}
				else {
					test.log(LogStatus.FAIL,"Verify the  login screen by giving invalid input in both fields is fail");
					homepagev2.smokeresults("Verify the  login screen by giving invalid input in email id field",rowno5, "Fail");
					BasePageV2.takeScreenshot();
				}
				homepagev2.smokeresults("Verify the  login screen by giving invalid input in both fields",rowno5, "Pass");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Verify the  login screen by giving invalid input in both fields is fail");
			homepagev2.smokeresults("Verify the  login screen by giving invalid input in both fields",rowno5, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
			}
	
		
		//7. Verify the login screen by not giving any inputs to any of the fields
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Email text field");
				homepagev2.smokeresults("Verify the login screen by not giving any inputs to any of the fields",rowno6, "Fail");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
				homepagev2.smokeresults("Verify the login screen by not giving any inputs to any of the fields",rowno6, "Fail");
				BasePageV2.takeScreenshot();
			}
			test.log(LogStatus.INFO, "Clicking on login button");
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
				test.log(LogStatus.INFO, "Clicking on login button");
				launchPageV2.loginButton.click();
			}
			else {
				test.log(LogStatus.FAIL,"Login button is not displayed");
				homepagev2.smokeresults("Verify the login screen by not giving any inputs to any of the fields",rowno6, "Fail");
				BasePageV2.takeScreenshot();
			}
			String errorBlankLogin=launchPageV2.commonErrors.getText().toString();
			test.log(LogStatus.INFO, "Expected Title: "+loginBlank);
			test.log(LogStatus.INFO, "Actual Title: "+errorBlankLogin);
			if(errorBlankLogin.equalsIgnoreCase(loginBlank)){
				test.log(LogStatus.INFO, "Blank login error: "+errorBlankLogin);
				test.log(LogStatus.PASS, "Test Case 'Verify the login screen by not giving any inputs to any of the fields' is passed");
				homepagev2.smokeresults("Verify the login screen by not giving any inputs to any of the fields",rowno6, "Pass");

			}
			else {
				test.log(LogStatus.FAIL, "Test Case 'Verify the login screen by not giving any inputs to any of the fields' is fail");
				homepagev2.smokeresults("Verify the login screen by not giving any inputs to any of the fields",rowno6, "Fail");
				BasePageV2.takeScreenshot();
			}
			homepagev2.smokeresults("Verify the login screen by not giving any inputs to any of the fields",rowno6, "Pass");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Test Case 'Verify the login screen by not giving any inputs to any of the fields' is fail");
			homepagev2.smokeresults("Verify the login screen by not giving any inputs to any of the fields",rowno6, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
		}
		//8. Verify the click functionality of "Forgot Password?" link
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.forgotPwd, 5)) {
				test.log(LogStatus.INFO, "Clicking on forgot password link");
				if(launchPageV2.forgotPwd.isDisplayed()) {
					launchPageV2.forgotPwd.click();
				}
				else {
					test.log(LogStatus.FAIL, "Unable to click on forgot password");
					homepagev2.smokeresults("Verify the click functionality of 'Forgot Password?' link",rowno7, "Fail");
					BasePageV2.takeScreenshot();
				}
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.forgotPasswordPage, 25)) {
				if(launchPageV2.forgotPasswordPage.isDisplayed()) {
					test.log(LogStatus.INFO, "Forgot Password page is displayed");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case 'Verify the click functionality of 'Forgot Password?' link' is passed");
					homepagev2.smokeresults("Verify the click functionality of 'Forgot Password?' link",rowno7, "Pass");
					Thread.sleep(2000);
					launchPageV2.backButton.click();
				}
				else {
					test.log(LogStatus.FAIL, "Test Case 'Verify the click functionality of 'Forgot Password?' link' is fail");
					homepagev2.smokeresults("Verify the click functionality of 'Forgot Password?' link",rowno7, "Fail");
					BasePageV2.takeScreenshot();
				}
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Test Case 'Verify the click functionality of 'Forgot Password?' link' is fail");
			homepagev2.smokeresults("Verify the click functionality of 'Forgot Password?' link",rowno7, "Fail");
			BasePageV2.takeScreenshot();
		}
		homepagev2.smokeresults("Verify the click functionality of 'Forgot Password?' link",rowno7, "Pass");
		
		//9. Verify the click functionality of "Sign Up" link
		WebElement element=driver.findElementByXPath("//android.widget.TextView[contains(@text,'sign up')]");
		if(Utilities.explicitWaitVisible(driver, element, 5))
			test.log(LogStatus.INFO, "Clicking on Sign Up link");
		if(element.isDisplayed()) {
			element.click();
			Thread.sleep(2000);
			BasePageV2.takeScreenshot();
		}
		else {
			test.log(LogStatus.FAIL, "Unable to click on Sign Up");
			homepagev2.smokeresults("Verify the click functionality of 'Sign Up' link",rowno8, "Fail");
			BasePageV2.takeScreenshot();
		}
		if(Utilities.explicitWaitVisible(driver, launchPageV2.signUpPage, 20)) {
			launchPageV2.backButton.click();
		test.log(LogStatus.PASS, "Test Case 'Verify the click functionality of 'Sign Up' link' is passed");
		homepagev2.smokeresults("Verify the click functionality of 'Sign Up' link",rowno8, "Pass");
		}
		else {
			test.log(LogStatus.FAIL, "Unable to click on Back button");
			homepagev2.smokeresults("Verify the click functionality of 'Sign Up' link",rowno8, "Fail");
			BasePageV2.takeScreenshot();
		}
		
		//10. Verify Login button functionality by giving valid Email Id in "Mobile number/Email ID" fields and valid password
		//Single profile user login functionality
		test.log(LogStatus.INFO, "Verifying Single Profile User Login");
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys("pavan123@pavan.com");
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Email text field");
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys("pavan123");
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
				BasePageV2.takeScreenshot();
			}
			test.log(LogStatus.INFO, "Clicking on login button");
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
				test.log(LogStatus.INFO, "Clicking on login button");
				launchPageV2.loginButton.click();
			}
			else {
				test.log(LogStatus.FAIL,"Login button is not displayed");
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
				BasePageV2.takeScreenshot();
			}
			/*if(Utilities.explicitWaitVisible(driver,homepagev2.kidslogoicon, 20))
			{
				if(homepagev2.kidslogoicon.isDisplayed()) {
					test.log(LogStatus.INFO, "My Stuff page is displayed");
					
				}
				else {
					test.log(LogStatus.FAIL,"My Stuff page is not displayed");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
				}
			}*/
			/*if(!Utilities.explicitWaitVisible(driver, homepagev2.mystuff_text, 20))
	         {
	            String text="My Stuff";
//	            WebElement element1=driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=com.tv.vootkids:id/home_icon]"));
	            
	            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	            test.log(LogStatus.INFO, "Navigation to (My Stuff) is passed");

	            BasePageV2.takeScreenshot();  
	          if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 20))
	            {
//		            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector()."+element1+".instance(0))");

	           BasePageV2.swipetokidslogo(driver);         
	            }
	          test.log(LogStatus.INFO, "My stuff page displayed after login: Single profile user");
	          BasePageV2.takeScreenshot();
	         }
			else {
				test.log(LogStatus.FAIL,"Unable to navigate to (My Stuff)");
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
				BasePageV2.takeScreenshot();
			}*/
		
			test.log(LogStatus.INFO, "Logging out");
			try {
				if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
					test.log(LogStatus.INFO, "My Stuff page is displayed");
					test.log(LogStatus.INFO, "Clicking on profile icon");
					BasePageV2.takeScreenshot();
					homepagev2.profilepic.click();
					
				}else {
					test.log(LogStatus.FAIL,"Profile icon not found");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
				}
			}catch(Exception e) {}
				try {
					if(Utilities.explicitWaitVisible(driver, homepagev2.settings, 20)) {
						homepagev2.settings.click();
					}else {
						test.log(LogStatus.FAIL,"Settings icon not found");
						homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
						BasePageV2.takeScreenshot();
				
					}
				}catch(Exception e) {}
				try {
					if(Utilities.explicitWaitVisible(driver, homepagev2.logout, 20)) {
						homepagev2.logout.click();
					}else {
						test.log(LogStatus.FAIL,"Logout button not found");
						homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
						BasePageV2.takeScreenshot();
					}
				}catch(Exception e) {
					test.log(LogStatus.FAIL,"Unable to logout Single profile user");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
			}	
				try {
					if(Utilities.explicitWaitVisible(driver, homepagev2.confirmLogout, 20)) {
						test.log(LogStatus.INFO,"Clicking on yes to logout");
						BasePageV2.takeScreenshot();
						homepagev2.confirmLogout.click();
					}else {
						test.log(LogStatus.FAIL,"Logout Confirmation popup not displayed");
						homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
						BasePageV2.takeScreenshot();
					}
				}catch(Exception e) {
					test.log(LogStatus.FAIL,"Unable to logout Single profile user");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
			}	
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to logout Single profile user");
			homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
			BasePageV2.takeScreenshot();
		}
		//Validating multi-profile user login
		test.log(LogStatus.INFO, "Verifying multi-profile user login");

		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25))
				launchPageV2.loginGateway.click();
			else
				test.log(LogStatus.FAIL, "Unable to find Login Gateway button");
			test.log(LogStatus.INFO, "Clicking on Login Gateway button");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click on Login Gateway button");
			test.log(LogStatus.FAIL,"Unable to click on Login Gateway button");
			
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
			BasePageV2.takeScreenshot();
			e.printStackTrace();
		}

		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(email);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Email text field");
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(validPwd);
				Thread.sleep(500);
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
				BasePageV2.takeScreenshot();
			}
			test.log(LogStatus.INFO, "Clicking on login button");
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
				test.log(LogStatus.INFO, "Clicking on login button");
				launchPageV2.loginButton.click();
			}
			else {
				test.log(LogStatus.FAIL,"Login button is not displayed");
				homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
				BasePageV2.takeScreenshot();
			}

			if(Utilities.explicitWaitVisible(driver,launchPageV2.selectProfilePage, 20))
			{
				if(launchPageV2.selectProfilePage.isDisplayed()){
					test.log(LogStatus.INFO, "Select Profile page is displayed");
					test.log(LogStatus.INFO, "Login for multiple user profile is taking to select profile page");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Pass");
					BasePageV2.takeScreenshot();
				}
				else{
					test.log(LogStatus.FAIL,"Select Profile page page is not displayed for multi-profile user");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");					
				}
			}


			if(Utilities.explicitWaitVisible(driver,launchPageV2.selectProfileIcon, 20))
			{
				if(launchPageV2.selectProfileIcon.isDisplayed()){
					test.log(LogStatus.INFO, "Selecting profile from multiple profile");
					launchPageV2.selectProfileIcon.click();
					Thread.sleep(10000);
				}
				else{
					test.log(LogStatus.FAIL,"Unable to select profile");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();					
				}
			}
			
			WebElement continueButton=driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Continue')]"));
				if(continueButton.isDisplayed()){
					test.log(LogStatus.INFO, "Clicking on continue button in Select profile page");
					continueButton.click();
				}
				else{
					test.log(LogStatus.FAIL,"Unable to click on continue button");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();					
				}
			

			/*if(Utilities.explicitWaitVisible(driver,homepagev2.kidslogoicon, 20))
			{
				if(homepagev2.kidslogoicon.isDisplayed()) {
					test.log(LogStatus.INFO, "My Stuff page is displayed");
					
				}
				else {
					test.log(LogStatus.FAIL,"My Stuff page is not displayed");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
				}
			}
			*/
			test.log(LogStatus.INFO, "Logging out");
			try {
				if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
					test.log(LogStatus.INFO, "My Stuff page is displayed");
					test.log(LogStatus.INFO, "Clicking on profilepic icon");
					BasePageV2.takeScreenshot();
					homepagev2.profilepic.click();
				}else {
					test.log(LogStatus.FAIL,"Profile icon not found");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
				}
			}catch(Exception e) {}
				try {
					if(Utilities.explicitWaitVisible(driver, homepagev2.settings, 20)) {
						homepagev2.settings.click();
					}else {
						test.log(LogStatus.FAIL,"Settings icon not found");
						homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
						BasePageV2.takeScreenshot();
				
					}
				}catch(Exception e) {}
				try {
					if(Utilities.explicitWaitVisible(driver, homepagev2.logout, 20)) {
						homepagev2.logout.click();
						test.log(LogStatus.PASS, "Test Case 'Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password' is passed");
						homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Pass");

					}else {
						test.log(LogStatus.FAIL,"Logout button not found");
						test.log(LogStatus.FAIL, "Test Case 'Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password' is fail");
						homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
						BasePageV2.takeScreenshot();
					}
				}catch(Exception e) {
					test.log(LogStatus.FAIL,"Unable to logout Multi-profile user");
					test.log(LogStatus.FAIL, "Test Case 'Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password' is fail");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
			}	
				try {
					if(Utilities.explicitWaitVisible(driver, homepagev2.confirmLogout, 20)) {
						test.log(LogStatus.INFO,"Clicking on yes to logout");
						BasePageV2.takeScreenshot();
						homepagev2.confirmLogout.click();
					}else {
						test.log(LogStatus.FAIL,"Logout Confirmation popup not displayed");
						homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
						BasePageV2.takeScreenshot();
					}
				}catch(Exception e) {
					test.log(LogStatus.FAIL,"Unable to logout Single profile user");
					homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
					BasePageV2.takeScreenshot();
			}	
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to logout Single profile user");
			test.log(LogStatus.FAIL, "Test Case 'Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password' is fail");
			homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
			BasePageV2.takeScreenshot();
		}
homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Pass");

try {
	if(Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25))
		launchPageV2.loginGateway.click();
	else
		test.log(LogStatus.FAIL, "Unable to find Login Gateway button");
	test.log(LogStatus.INFO, "Clicking on Login Gateway button");
}catch (Exception e) {
	test.log(LogStatus.FAIL, "Unable to click on Login Gateway button");
	test.log(LogStatus.FAIL,"Unable to click on Login Gateway button");
	homepagev2.smokeresults("Verify country code is autofilled as user enteres mobile no. in the text field",rowno11, "Fail");
	BasePageV2.takeScreenshot();
	e.printStackTrace();
}

//11. Verify the the "Login" button functionality by giving non registered Mobile number in login screen
try {
	if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
		launchPageV2.emailText.click();
		launchPageV2.emailText.clear();
		launchPageV2.emailText.sendKeys(invalidMobileNumber);
		Thread.sleep(500);
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.countryCode, 25))
			{
				if(launchPageV2.countryCode.isDisplayed()) {
					String code=launchPageV2.countryCode.getText().toString();
					test.log(LogStatus.INFO, "Country code Autofilled when user enters a mobile number in mobile number text field: "+code);
					test.log(LogStatus.PASS, "Test case Verify country code is autofilled as user enteres mobile no. in the text field is passed");
					homepagev2.smokeresults("Verify country code is autofilled as user enteres mobile no. in the text field",rowno11, "Pass");
					BasePageV2.takeScreenshot();

				}else {
					test.log(LogStatus.FAIL, "Test case Verify country code is autofilled as user enteres mobile no. in the text field is fail");
					homepagev2.smokeresults("Verify country code is autofilled as user enteres mobile no. in the text field",rowno11, "Fail");
					BasePageV2.takeScreenshot();

				}
			}
		}
		catch(Exception e) {
			
		}
	}
	else {
		test.log(LogStatus.FAIL,"Unable to perform actions on Mobile number text field");
		homepagev2.smokeresults("Verify the the 'Login' button functionality by giving non registered Mobile number in login screen",rowno10, "Fail");
		BasePageV2.takeScreenshot();
	}
	if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
	{
		launchPageV2.pwdText.click();
		launchPageV2.pwdText.clear();
		launchPageV2.pwdText.sendKeys(validPwd);
		Thread.sleep(500);
	}
	else {
		test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
		homepagev2.smokeresults("Verify the the 'Login' button functionality by giving non registered Mobile number in login screen",rowno10, "Fail");
		BasePageV2.takeScreenshot();
	}
	test.log(LogStatus.INFO, "Clicking on login button");
	try {
		driver.hideKeyboard();
		}
		catch(Exception e) {}
	if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
		test.log(LogStatus.INFO, "Clicking on login button");
		launchPageV2.loginButton.click();
	}
	else {
		test.log(LogStatus.FAIL,"Login button is not displayed");
		homepagev2.smokeresults("Verify the the 'Login' button functionality by giving non registered Mobile number in login screen",rowno10, "Fail");
		BasePageV2.takeScreenshot();
	}
	
		String emailError=launchPageV2.commonErrors.getText().toString();
		test.log(LogStatus.INFO, "email error: "+emailError);
		if(emailError.equalsIgnoreCase(invalidEmailandPwd)) {
			test.log(LogStatus.PASS, "Test Case  Verify the  login screen by giving invalid input in Mobile number field is passed");
			homepagev2.smokeresults("Verify the the 'Login' button functionality by giving non registered Mobile number in login screen",rowno10, "Fail");
			BasePageV2.takeScreenshot();
		}
		else {
			test.log(LogStatus.FAIL,"Verify the  login screen by giving invalid input in Mobile number field is fail");
			homepagev2.smokeresults("Verify the the 'Login' button functionality by giving non registered Mobile number in login screen",rowno10, "Fail");
			BasePageV2.takeScreenshot();
		}
		homepagev2.smokeresults("Verify the the 'Login' button functionality by giving non registered Mobile number in login screen",rowno10, "Pass");
}
catch(Exception e) {
	test.log(LogStatus.FAIL,"Verify the  login screen by giving invalid input in Mobile number field is fail");
	homepagev2.smokeresults("Verify the the 'Login' button functionality by giving non registered Mobile number in login screen",rowno10, "Fail");
	BasePageV2.takeScreenshot();
	e.printStackTrace();
	}

	//13.Verify the the "Login" button functionality by giving valid Mobile number in login screen
//test.log(LogStatus.INFO, "Verifying Single Profile User Login");
try {
	if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
		launchPageV2.emailText.click();
		launchPageV2.emailText.clear();
		launchPageV2.emailText.sendKeys(validMobileNumber);
		Thread.sleep(500);
	}
	else {
		test.log(LogStatus.FAIL,"Unable to perform actions on Mobile number field");
		homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
		BasePageV2.takeScreenshot();
	}
	if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
	{
		launchPageV2.pwdText.click();
		launchPageV2.pwdText.clear();
		launchPageV2.pwdText.sendKeys(validMobilePassword);
		Thread.sleep(500);
	}
	else {
		test.log(LogStatus.FAIL,"Unable to perform actions on Password text field");
		homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
		BasePageV2.takeScreenshot();
	}
	test.log(LogStatus.INFO, "Clicking on login button");
	try {
		driver.hideKeyboard();
		}
		catch(Exception e) {}
	if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
		test.log(LogStatus.INFO, "Clicking on login button");
		launchPageV2.loginButton.click();
	}
	else {
		test.log(LogStatus.FAIL,"Login button is not displayed");
		homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
		BasePageV2.takeScreenshot();
	}
	/*if(Utilities.explicitWaitVisible(driver,homepagev2.kidslogoicon, 20))
	{
		if(homepagev2.kidslogoicon.isDisplayed()) {
			test.log(LogStatus.INFO, "My Stuff page is displayed");
			
		}
		else {
			test.log(LogStatus.FAIL,"My Stuff page is not displayed");
			homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
			BasePageV2.takeScreenshot();
		}
	}*/
	/*if(!Utilities.explicitWaitVisible(driver, homepagev2.mystuff_text, 20))
     {
        String text="My Stuff";
//        WebElement element1=driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=com.tv.vootkids:id/home_icon]"));
        
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
        test.log(LogStatus.INFO, "Navigation to (My Stuff) is passed");

        BasePageV2.takeScreenshot();  
      if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 20))
        {
//            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector()."+element1+".instance(0))");

       BasePageV2.swipetokidslogo(driver);         
        }
      test.log(LogStatus.INFO, "My stuff page displayed after login: Single profile user");
      BasePageV2.takeScreenshot();
     }
	else {
		test.log(LogStatus.FAIL,"Unable to navigate to (My Stuff)");
		homepagev2.smokeresults("Verify Login button functionality by giving valid Email Id in 'Mobile number/Email ID' fields and valid password",rowno9, "Fail");
		BasePageV2.takeScreenshot();
	}*/
	test.log(LogStatus.INFO, "Logging out");
	try {
		if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
			test.log(LogStatus.INFO, "My Stuff page is displayed");
			test.log(LogStatus.INFO, "Clicking on profile icon");
			BasePageV2.takeScreenshot();
			homepagev2.profilepic.click();
			
		}else {
			test.log(LogStatus.FAIL,"Profile icon not found");
			homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
			BasePageV2.takeScreenshot();
		}
	}catch(Exception e) {}
		try {
			if(Utilities.explicitWaitVisible(driver, homepagev2.settings, 20)) {
				homepagev2.settings.click();
			}else {
				test.log(LogStatus.FAIL,"Settings icon not found");
				homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
				BasePageV2.takeScreenshot();
		
			}
		}catch(Exception e) {}
		try {
			if(Utilities.explicitWaitVisible(driver, homepagev2.logout, 20)) {
				homepagev2.logout.click();
			}else {
				test.log(LogStatus.FAIL,"Logout button not found");
				homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
				BasePageV2.takeScreenshot();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to logout Single profile user");
			homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
			BasePageV2.takeScreenshot();
	}	
		try {
			if(Utilities.explicitWaitVisible(driver, homepagev2.confirmLogout, 20)) {
				test.log(LogStatus.INFO,"Clicking on yes to logout");
				BasePageV2.takeScreenshot();
				homepagev2.confirmLogout.click();
			}else {
				test.log(LogStatus.FAIL,"Logout Confirmation popup not displayed");
				homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
				BasePageV2.takeScreenshot();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to logout");
			homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
			BasePageV2.takeScreenshot();
	}	
}
catch(Exception e) {
	test.log(LogStatus.FAIL,"Unable to logout ");
	homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Fail");
	BasePageV2.takeScreenshot();
}
test.log(LogStatus.PASS, "Test case Verify the the 'Login' button functionality by giving valid Mobile number in login screen is Passed");
homepagev2.smokeresults("Verify the the 'Login' button functionality by giving valid Mobile number in login screen",rowno12, "Pass");

	}
	
	//11. Verify the the "Login" button functionality by giving non registered Mobile number in login screen
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}

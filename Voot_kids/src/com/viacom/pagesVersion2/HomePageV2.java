package com.viacom.pagesVersion2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.View;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/*************************************************************************************
 * Class : HomePage 
 * Purpose : This class is used for selecting various sections of voot 
 * Remarks : none 
 * Author : Roja KC, 
 * Ifocus Modifications: 26 May 2017- First created
 **************************************************************************************/
public class HomePageV2 extends BasePageV2 {
	
	public HomePageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@FindBy(id="com.tv.vootkids:id/btn_profile_menu")
	public WebElement profilepic;
	
	@FindBy(linkText="BLAZE OF GLORY16")
	public WebElement element;
	
	@FindBy(id="com.tv.vootkids:id/home_icon")
	public WebElement kidslogoicon;
	
	@FindBy(id="com.tv.vootkids:id/btn_cast_menu")
	public WebElement casticon;
	
	@FindBy(id="com.tv.vootkids:id/main.collapsing")
	public WebElement maintab;
	
	@FindBy(id="com.tv.vootkids:id/tabs")
	public WebElement actiontab;
		
	@FindBy(id="com.tv.vootkids:id/parent_for_carousal")
	public WebElement homecarousal_layout;
	
	@FindBy(id="com.tv.vootkids:id/segmented_tray_tab")
	public WebElement swipeto_tabs;
		
	@FindBy(xpath="//android.support.v7.app.ActionBar$Tab[@selected='true']")
	public WebElement highlighted_icon;
	
	
	@FindBy(xpath="//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/tabs']")
	public WebElement current_img;

	@FindBy(xpath="//*[contains(@class,'ActionBar')][@index='0']")
	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.tv.vootkids:id/tab_image'])[0]"))
	public WebElement mystuff_tab;
				
	@FindBy(xpath="//*[contains(@class,'ActionBar')][@index='1']")
	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.tv.vootkids:id/tab_image'])[1]"))
	public WebElement watch_tab;
	
	//new xpath
	@FindBy(xpath="//*[contains(@class,'ActionBar')][@index='2']")
	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.tv.vootkids:id/tab_image'])[2]"))
	public WebElement read_tab;
		
	@FindBy(xpath="//*[contains(@class,'ActionBar')][@index='3']")
	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.tv.vootkids:id/tab_image'])[3]"))
	public WebElement Listen_tab;
		
	@FindBy(xpath="//*[contains(@class,'ActionBar')][@index='4']")
	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.tv.vootkids:id/tab_image'])[4]"))
	public WebElement search_tab; 	
	
	@FindBy(xpath="//android.widget.TextView[@text='Switch profile']")
	public WebElement switch_prof;
	
	@FindBy(id="com.tv.vootkids:id/btn_cancel")
	public WebElement btn_cancel_fromprofile;	
	
	@FindBy(id="com.tv.vootkids:id/btn_edit_profile")
	public WebElement btn_editprofile;
	
	@FindBy(id="com.tv.vootkids:id/btn_parent_zone")
	public WebElement btn_parentzone;

	@FindBy(xpath="//android.widget.TextView[@text='My Stuff']")
	public WebElement mystuff_text;
	
	@FindBy(xpath="//android.widget.TextView[@text='On Now']")
	public WebElement watchtab_text;
	
	@FindBy(xpath="//android.widget.TextView[@text='MOST POPULAR EBOOKS']")
	public WebElement readtab_text;
	
	@FindBy(xpath="//android.widget.TextView[@text='MOST POPULAR AUDIOS']")
	public WebElement Listentab_text;
	
	@FindBy(xpath="//android.widget.EditText[@text='Search']")
	public WebElement searchtab_text;	
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']")
	public WebElement mast_carousel;
	
	@FindBy(xpath="//android.widget.TextView[@text='JUST FOR YOUabc']//ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup")
	public List<WebElement> justforu_size;	
	
	@FindBy(xpath="//android.widget.TextView[@text='Just For You']")
	public WebElement justforu_text_inside;
	
	@FindBy(xpath="//android.widget.TextView[@text='JUST FOR YOU']")
	public WebElement justforu_text;

	@FindBy(id="com.tv.vootkids:id/btn_back")
	public WebElement justforu_bckbtn;
	
	//@FindBy(xpath="//android.widget.TextView[@text='JUST FOR YOUabc']//ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup//android.widget.TextView[resource-id='com.tv.vootkids:id/grid_video_duration']")
	@FindBy(id="com.tv.vootkids:id/grid_video_duration")
	public WebElement justforu_video_duration;
	
	@FindBy(id="com.tv.vootkids:id/grid_title")
	public WebElement justforu_Grid_title;
	
	
	@FindBy(xpath="//android.widget.TextView[@text='JUST FOR YOU']/../..//android.view.ViewGroup")
	public List<WebElement> titles;
	
	@FindBy(xpath="//android.widget.TextView[@text='FAVOURITESabc']")
	public WebElement favourites_text;
	
	@FindBy(xpath="//android.widget.TextView[@text='FAVOURITES']")
	public WebElement favourites_text_insidefavourite;
	
	@FindBy(id="com.tv.vootkids:id/btn_edit")
	public WebElement edit_favourites;
	
	@FindBy(xpath="//android.widget.TextView[@text='ALL CHARACTERSabc']")
	public WebElement allcharecters_text;
	
	
	@FindBy(xpath="//android.widget.TextView[@text='ALL CHARACTERSabc']/..//android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup")
	public WebElement allcharecters_firstcharecter;
		
	@FindBy(id="com.tv.vootkids:id/button_fav")
	public WebElement fav_btn;
	
	@FindBy(id="com.tv.vootkids:id/btn_back")
	public WebElement bckbtn;
		
	@FindBy(xpath="//android.widget.TextView[@text='MOST POPULAR EBOOKSabc']")
	public WebElement popular_books;
	
	@FindBy(xpath="//android.widget.TextView[@text='MOST POPULAR EBOOKSabc']/..//android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup")
	public WebElement popular_books_firstcharecter;
	
	@FindBy(id="com.tv.vootkids:id/btn_settings_menu") //Settings icon in profile pic page
	public WebElement settings;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'LOGOUT')]")//Logout Button
	public WebElement logout;
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'yes')]")//Logout Button
	public WebElement confirmLogout;
		
	@FindBy(xpath="//android.widget.Button[@text='SEE ALL']")
	public WebElement seeAll;
	
	
	
	//Login
	public static void login(String UN, String Pwd) {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		
		test.log(LogStatus.INFO, "Logging In with valid credentials");
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25))
				launchPageV2.loginGateway.click();
			else
				System.out.println("Login link not found");
		}catch (Exception e) {
			System.out.println("Login link not found");			
			e.printStackTrace();
		}
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(UN);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else {
				test.log(LogStatus.FAIL,"Unable to perform actions on Email text field");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(Pwd);
				Thread.sleep(500);
			}
			else {
				BasePageV2.takeScreenshot();
			}
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) {
				launchPageV2.loginButton.click();
			}
			else {
				BasePageV2.takeScreenshot();
			}
		
	}
		catch(Exception e) {
			BasePageV2.takeScreenshot();
		}
		try {
			if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5))	{
				if(homepagev2.profilepic.isDisplayed()) {
					test.log(LogStatus.INFO, "Logged in Successfully");
				}
			}
		}catch(Exception e) {}
		}
	
	//Logout
	public static void logout() {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		test.log(LogStatus.INFO, "Logging out");
		try {
			if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
				BasePageV2.takeScreenshot();
				homepagev2.profilepic.click();
			}else {
				System.out.println("Profile icon not found");
			}
		}catch(Exception e) {}
			try {
				if(Utilities.explicitWaitVisible(driver, homepagev2.settings, 20)) {
					homepagev2.settings.click();
				}else {
					System.out.println("Setings icon not found");
				}
			}catch(Exception e) {}
			try {
				if(Utilities.explicitWaitVisible(driver, homepagev2.logout, 20)) {
					homepagev2.logout.click();

				}else {
					System.out.println("Logout button not found");
				}
			}catch(Exception e) {}	
			try {
				if(Utilities.explicitWaitVisible(driver, homepagev2.confirmLogout, 20)) {
					homepagev2.confirmLogout.click();
				}else {
					System.out.print("");
				}
				Thread.sleep(2000);
				
			}catch(Exception e) {}
			try {
				if(Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 20)) {
					if(launchPageV2.SignUpfromWelcomePage.isDisplayed()) {
						test.log(LogStatus.INFO, "Logged out successfully");
					}
					else {
						System.out.print("");
					}
				
				}
			}catch(Exception e) {
			}
		
	}
	
	public static void signUpPagefromWelcomeScreen() throws Exception {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		try{
			
			if(Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 25)) {
				test.log(LogStatus.INFO, "Clicking on 'JOIN FREE FOR 30 DAYS' Button");
				BasePageV2.takeScreenshot();
				launchPageV2.SignUpfromWelcomePage.click();
			}
		}catch(Exception e) {
			BasePageV2.takeScreenshot();
		}
	}
	
	
	//sign up
	
	
	public static void signUp(String email, String mobileNumber, String pwd) throws Exception {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		/*try{
			
			if(Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 25)) {
				test.log(LogStatus.INFO, "Clicking on 'JOIN FREE FOR 30 DAYS' Button for signup");
				BasePageV2.takeScreenshot();
				launchPageV2.SignUpfromWelcomePage.click();
			}
		}catch(Exception e) {
			homepagev2.reportFail("'JOIN FREE FOR 30 DAYS' Button not found");
			BasePageV2.takeScreenshot();
		}*/
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				Thread.sleep(500);
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(email);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else
				System.out.println("Email Text field not found");
				
		}catch(Exception e) {}
		
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 25)) {
				Thread.sleep(500);
				launchPageV2.mobilenumberText.clear();
				launchPageV2.mobilenumberText.sendKeys(mobileNumber);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else
				System.out.println("Mobile number Text field not found");
				
		}catch(Exception e) {}
		
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25)) {
				Thread.sleep(500);
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(pwd);
				Thread.sleep(500);
			}
			else
				System.out.println("Password Text field not found");
				
		}catch(Exception e) {}	
		
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		
		try {
			launchPageV2.nextButton.click();
		}catch(Exception e) {}
		
	}
	
	public static void blankSignup() {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5)) {
				launchPageV2.backButton.click();
			}
			else {
				BasePageV2.takeScreenshot();
			}
			try {
				HomePageV2.signUpPagefromWelcomeScreen();
			}catch(Exception e) {
				
			}	
		}catch(Exception e) {}
		try {
			launchPageV2.nextButton.click();
		}catch(Exception e) {}
		
	}
	
	
	public static void signUpwithonlyEmail(String email) {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				Thread.sleep(500);
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(email);
				Thread.sleep(500);
			}
			else
				System.out.println("Email Text field not found");
				
		}catch(Exception e) {}
		
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		
		try {
			launchPageV2.nextButton.click();
		}catch(Exception e) {}
		
	}
	
	public static void signUpwithoutEmail(String MobileNo, String Pwd) {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 25)) {
				Thread.sleep(500);
				launchPageV2.mobilenumberText.clear();
				launchPageV2.mobilenumberText.sendKeys(MobileNo);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else
				System.out.println("Mobile number Text field not found");
				
		}catch(Exception e) {}
		
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25)) {
				Thread.sleep(500);
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(Pwd);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else
				System.out.println("Password Text field not found");
				
		}catch(Exception e) {}	
		
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		
		try {
			launchPageV2.nextButton.click();
		}catch(Exception e) {}
		
	}
	public static void signUpwithoutMobile(String email, String pwd) {
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				Thread.sleep(500);
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(email);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else
				System.out.println("Email Text field not found");
				
		}catch(Exception e) {}
		
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 25)) {
				Thread.sleep(500);
				launchPageV2.mobilenumberText.clear();
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else
				System.out.println("Mobile number Text field not found");
				
		}catch(Exception e) {}
		
		try {
			if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25)) {
				Thread.sleep(500);
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(pwd);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
					}
					catch(Exception e) {}
			}
			else
				System.out.println("Password Text field not found");
				
		}catch(Exception e) {}	
		
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		
		try {
			launchPageV2.nextButton.click();
		}catch(Exception e) {}
	}
public static void signUpwithoutPwd(String email, String Mobile) {
	LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
		}
		else
			System.out.println("Email Text field not found");
			
	}catch(Exception e) {}
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 25)) {
			Thread.sleep(500);
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(Mobile);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
		}
		else
			System.out.println("Mobile number Text field not found");
			
	}catch(Exception e) {}
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25)) {
			Thread.sleep(500);
			launchPageV2.pwdText.clear();
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
		}
		else
			System.out.println("Password Text field not found");
			
	}catch(Exception e) {}	
	
	try {
		driver.hideKeyboard();
		}
		catch(Exception e) {}
	
	try {
		launchPageV2.nextButton.click();
	}catch(Exception e) {}
	}

public static void signup() {
	
	LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
	HomePageV2 homepagev2=new HomePageV2(driver,test);
	
	String email=Utilities.generateEmailid();
	String pwd="vinoth123";
	String pin="1111";
	
	try{
		
		if(Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 25)) {
			test.log(LogStatus.INFO, "Clicking on 'JOIN FREE FOR 30 DAYS' Button");
			BasePageV2.takeScreenshot();
			launchPageV2.SignUpfromWelcomePage.click();
		}
	}catch(Exception e) {
		BasePageV2.takeScreenshot();
	}
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
		}
		else
			System.out.println("Email Text field not found");
			
	}catch(Exception e) {}
			
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25)) {
			Thread.sleep(500);
			launchPageV2.pwdText.clear();
			launchPageV2.pwdText.sendKeys(pwd);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
				}
				catch(Exception e) {}
		}
		else
			System.out.println("Password Text field not found");
			
	}catch(Exception e) {}	
	
	try {
		driver.hideKeyboard();
		}
		catch(Exception e) {}
	
	try {
		launchPageV2.nextButton.click();
	}catch(Exception e) {}
	
	//Create pin
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		}
		else {
			System.out.println();
		}
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		try {
			launchPageV2.buttonCreatePin.click();
		}catch(Exception e) {}
	}
	catch(Exception e) {}
	
	//Confirm pin
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		}
		else {
			System.out.println();
		}
		
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		
		try {
			launchPageV2.buttonCreatePin.click();
		}catch(Exception e) {}
	}
	catch(Exception e) {}
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25))
		launchPageV2.kidsName.clear();
		launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
		launchPageV2.DOB.click();
		launchPageV2.DOBSelect.click();
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		launchPageV2.next.click();
		Thread.sleep(7000);
		launchPageV2.next.click();
		
		try {
			for(int i=1;i<=6;i++) {
			WebElement element=driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id='com.tv.vootkids:id/character_icon'])["+i+"]"));
			element.click();
			}
			Thread.sleep(7000);
			WebElement Skills = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
			Skills.click();
			try {
				Thread.sleep(2000);
				launchPageV2.next.click();
			}catch(Exception e) {
				
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 25)) {
				launchPageV2.letsGo.click();
			}
			if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25)) {
				if(homepagev2.profilepic.isDisplayed()) {
					test.log(LogStatus.INFO, "Sign up Successful");
				}
				else {
					test.log(LogStatus.INFO, "Sign up Un Successful");
				}
			}
			
		}catch(Exception e) {
			
		}
	}catch (Exception e) {}
	
	
	
	
	
}


}

	
	

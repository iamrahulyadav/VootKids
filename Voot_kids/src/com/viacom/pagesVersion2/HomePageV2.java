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
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

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
	
	
	public static String pin="1111";


       	// Added by me parameters below once 
	
	@FindBy(xpath="//*[contains(@id='com.tv.vootkids:id/empty_view')]")
	public WebElement onNowNothingHere;
	
	

	@FindBy(className="android.widget.ProgressBar")
	public List<WebElement> progressBar;	
	 
	 @FindBy(xpath="//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='Channels']")
		public WebElement watchTab_channels;
	
	 @FindBy(xpath="//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='On Now']")
		public WebElement watchTab_onNOwTab;
	 
	 @FindBy(xpath="//android.widget.ImageView[@id='com.tv.vootkids:id/btn_profile_menu']")
		public WebElement profileIcon;
	
	@FindBy(xpath="//android.view.ViewGroup[@index='0']")
	public WebElement onNow_tray_ViewGroup0;
	
	@FindBy(xpath=("//android.widget.ImageView[@resource-id = 'com.tv.vootkids:id/btn_back']"))
	public WebElement Chennels_Back_btn;
	
	@FindBy(xpath="//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='Characters']")
	public WebElement channels_characters;
	
	@FindBy(xpath="//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='Schedule']")
	public WebElement channels_schedule;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_title']")
	public WebElement channels_characters_kids_card;
	
	@FindBy(xpath="//android.widget.TextView[@text='CHANNELS']")
	public WebElement channels_characters_CHANNELS_Text;
	
//	@FindBy(xpath="//*[contains(@class,'android.support.v7.widget.RecyclerView')][@index='0']")
//	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.tv.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.tv.vootkids:id/tab_image'])[0]"))
//	public WebElement mystuff_tab;
	
	// End by parameters adding


	
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
	
	@FindBy(xpath = "//android.widget.Button[contains(@text,'yes') or contains(@text,'YES')]") // Logout Button
	public WebElement confirmLogout;
		
	@FindBy(xpath="//android.widget.Button[@text='SEE ALL']")
	public WebElement seeAll;
	
	
	@FindBy(id="com.tv.vootkids:id/btn_info_cancel")  //Cancel all notification after signing up for freshly installed app
	public WebElement freshAppNotificationCancel;	
	
	@FindBy(id="com.tv.vootkids:id/container_pin_inner")
	public WebElement parentalPin;
	
	@FindBy(id="com.tv.vootkids:id/error_message_phone_number")
	public WebElement phoneNumberError;
	
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Create Pin') or contains(@text,'CREATE PIN')]") // Create pin page
	public WebElement createPinPage;
	
	
	
	@FindBy(xpath = "//android.widget.FrameLayout[@index='1']//android.widget.TextView[contains(@text,'Birth of Krishna')]")
	public WebElement audioBook;

	@FindBy(id = "com.tv.vootkids:id/button_preview")
	public WebElement playAudiobookbutton;

	@FindBy(id = "com.tv.vootkids:id/audio_skin_title")
	public WebElement audioTitle;

	@FindBy(id = "com.tv.vootkids:id/button_play_pause_toggle")
	public WebElement audioplayPause;

	@FindBy(id = "com.tv.vootkids:id/forward_player")
	public WebElement audioForward;

	@FindBy(id = "com.tv.vootkids:id/back_ward")
	public WebElement audiobackward;

	@FindBy(id = "com.tv.vootkids:id/down_button_audio_skin")
	public WebElement audiominiplayerSwitch;

	@FindBy(id = "com.tv.vootkids:id/close_button_audio_skin")
	public WebElement audioplayerClose;

	@FindBy(id = "com.tv.vootkids:id/seek_bar")
	public WebElement audioseekBar;

	@FindBy(id = "com.tv.vootkids:id/favourite_button")
	public WebElement audiofavouriteIcon;

	@FindBy(id = "com.tv.vootkids:id/duration_player")
	public WebElement audiorunningDuration;

	@FindBy(id = "com.tv.vootkids:id/totla_duration_player")
	public WebElement audiototalDuration;

	@FindBy(id = "com.tv.vootkids:id/btn_playlist_expand")
	public WebElement audioplaylistExpand;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='3']")
	public WebElement listen;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='0']")
	public WebElement hometab;

	@FindBy(id = "com.tv.vootkids:id/btn_playlist_collapse")
	public WebElement audioplaylistCollapse;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Related')]")
	public WebElement related;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'More From Author')]")
	public WebElement morefromauthor;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Daily Picks')]")
	public WebElement dailypicks;

	@FindBy(id = "com.tv.vootkids:id/seek_bar")
	public WebElement inlinePlayerSeekbar;

	@FindBy(id = "com.tv.vootkids:id/title")
	public WebElement inlinePlayerBookTitle;

	@FindBy(id = "com.tv.vootkids:id/play_pause")
	public WebElement inlinePlayerPlaypause;

	@FindBy(id = "com.tv.vootkids:id/grid_title")
	public WebElement plalistcontentTitle;

	@FindBy(id = "com.tv.vootkids:id/grid_description")
	public WebElement plalistcontentAuthor;

	@FindBy(id = "com.tv.vootkids:id/listen_again")
	public WebElement listenAgainBtn;

	@FindBy(id = "com.tv.vootkids:id/next_image")
	public WebElement AudioOverlay;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'You might also like...')]")
	public WebElement youmayalsoLike;

	@FindBy(xpath = "//android.widget.ImageView[@index='2']")
	public WebElement close;
	
	@FindBy(id = "com.tv.vootkids:id/category_icon")
	public WebElement categoryicon;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@index='0']/android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']/android.widget.TextView")
	public List<WebElement> carousalNames;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt']")
	public WebElement lastViewedTrayTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_clear_txt']")
	public WebElement lastViewedClear;

	@FindBy(id = "com.tv.vootkids:id/player_title")
	public WebElement videoPlayerTitle;

	@FindBy(id = "com.tv.vootkids:id/player_title")
	public WebElement videoPlayersettings;

	@FindBy(id = "com.tv.vootkids:id/favourite_button")
	public WebElement videoPlayerfavouriteBtn;

	@FindBy(id = "com.tv.vootkids:id/favourite_button")
	public WebElement videoPlayerclose;

	@FindBy(id = "com.tv.vootkids:id/player_center_control")
	public WebElement playercenterControl;

	@FindBy(id = "com.tv.vootkids:id/player_skin_container")
	public WebElement playerskin;

	@FindBy(id = "com.tv.vootkids:id/grid_description")
	public WebElement EpnumAndDescription;

	@FindBy(id = "com.tv.vootkids:id/grid_title")
	public WebElement contentTitle;

	@FindBy(id = "com.tv.vootkids:id/grid_item_category")
	public WebElement contentitemCategory;

	@FindBy(id = "com.tv.vootkids:id/grid_video_duration")
	public WebElement contentDuration;
	
	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='4']")
	public WebElement search;

	@FindBy(id = "com.tv.vootkids:id/search_edittext")
	public WebElement searchTextBox;

	@FindBy(id = "com.tv.vootkids:id/voice_button")
	public WebElement voiceBtn;

	@FindBy(id = "com.tv.vootkids:id/button_surprise_me")
	public WebElement surprisemeBtn;

	@FindBy(id = "com.tv.vootkids:id/clear_icon")
	public WebElement searchClear;
	
	@FindBy(id = "com.tv.vootkids:id/button_back")
	public WebElement backButton;

	@FindBy(id = "com.tv.vootkids:id/checkbox_fav_selector")
	public WebElement favIcon;

	@FindBy(id = "com.tv.vootkids:id/textview_title")
	public WebElement audTitle;

	@FindBy(id = "com.tv.vootkids:id/author_container")
	public WebElement audauthorContainer;

	@FindBy(id = "com.tv.vootkids:id/textview_author_name")
	public WebElement audauthorname;

	@FindBy(id = "com.tv.vootkids:id/narrated_by_container")
	public WebElement naratorContainer;

	@FindBy(id = "com.tv.vootkids:id/textview_narrated_by_name")
	public WebElement naratorname;

	@FindBy(id = "com.tv.vootkids:id/textview_description")
	public WebElement description;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'22m')]")
	public WebElement duration;

	@FindBy(id = "com.tv.vootkids:id/download_status_imageview")
	public WebElement downloadIcon;

	@FindBy(id = "com.tv.vootkids:id/download_status_textview")
	public WebElement downloadAudioBookText;

	@FindBy(id = "com.tv.vootkids:id/recent_types_txt")
	public WebElement recentTypeTab;

	@FindBy(id = "com.tv.vootkids:id/btn_download_item")
	public WebElement downloadedItem;

	@FindBy(id = "com.tv.vootkids:id/radio_btn_low")
	public WebElement lowqualityDownload;

	@FindBy(id = "com.tv.vootkids:id/radio_btn_medium")
	public WebElement midqualityDownload;

	@FindBy(id = "com.tv.vootkids:id/radio_btn_high")
	public WebElement highqualityDownload;

	@FindBy(id = "com.tv.vootkids:id/positive_btn")
	public WebElement allowDownload;

	@FindBy(id = "com.tv.vootkids:id/negative_btn")
	public WebElement cancelDownload;

	@FindBy(id = "com.tv.vootkids:id/download_status_textview")
	public WebElement DownloadStatus;

	@FindBy(xpath = "//android.widget.Button[@text='SEE ALL']")
	public WebElement traySeeAll;

	//Episodes related properties
	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='1']")
	public WebElement watchtab;

	
	
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
				if(Utilities.explicitWaitVisible(driver, homepagev2.parentalPin, 20)) {
					homepagev2.parentalPin.clear();
					homepagev2.parentalPin.sendKeys(pin);
				}else {
					HomePageV2.reportFail("Parental pin text field not displayed");
					BasePageV2.takeScreenshot();
				}
			}catch(Exception e) {}
			
			
			//parentalPin
			
			
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

public static void signup() throws Exception {
	
	LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
	HomePageV2 homepagev2=new HomePageV2(driver,test);
	
	String email=Utilities.generateEmailid();
	String pwd="vinoth123";
	//String pin="1234";
	String mobileNumber= Utilities.generateMobileNumber();
	
	if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5)) {
		if(homepagev2.profilepic.isDisplayed()) {
			test.log(LogStatus.INFO, "Already in home page");
			HomePageV2.logout();
			
		}
		else {
			test.log(LogStatus.INFO, "Signing in");
			BasePageV2.takeScreenshot();
		}
	}
	
	
	try{
		
		if(Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 5)) {
			test.log(LogStatus.INFO, "Clicking on 'JOIN FREE FOR 30 DAYS' Button");
			BasePageV2.takeScreenshot();
			launchPageV2.SignUpfromWelcomePage.click();
		}
	}catch(Exception e) {
		BasePageV2.takeScreenshot();
	}
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 5)) {
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
		if(Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 5)) {
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
			
	}catch(Exception e) {
		
	}
			
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
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
		if(Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
			launchPageV2.kidsName.clear();
			launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
		}
		else {
			HomePageV2.reportFail("Kids name text field not displayed");
			BasePageV2.takeScreenshot();
		}
		
		if(Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5)) {
			launchPageV2.DOB.click();
		}
		else {
			HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();
		}
		
		if(Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5)) {
			launchPageV2.DOBSelect.click();
		}
		else {
			HomePageV2.reportFail("Date selector not displayed");
			BasePageV2.takeScreenshot();
		}
		
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		try{
			launchPageV2.next.click();
			
			Thread.sleep(7000);
			launchPageV2.next.click();
			
		}catch(Exception e) {
			HomePageV2.reportFail("Next Button not displayed in Kids detail page");
			BasePageV2.takeScreenshot();
		}
		
		
		
		try {
			for(int i=1;i<=4;i++) {
			WebElement element=driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id='com.tv.vootkids:id/character_icon'])["+i+"]"));
			if(Utilities.explicitWaitVisible(driver,element, 10)) {
				element.click();
			}
			else {
				HomePageV2.reportFail("Kids Characters not displayed");
				BasePageV2.takeScreenshot();
			}
			
			}
			Thread.sleep(7000);
			WebElement Skills = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
			if(Utilities.explicitWaitVisible(driver, Skills, 5)) {
				Skills.click();
			}
			else {
				HomePageV2.reportFail("Skills not displayed");
				BasePageV2.takeScreenshot();
			}
			try {
				Thread.sleep(2000);
				launchPageV2.next.click();
			}catch(Exception e) {
				HomePageV2.reportFail("Next button not displayed in character selection page");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 25)) {
				launchPageV2.letsGo.click();
			}
			else {
				HomePageV2.reportFail("lets Go button not displayed in character selection page");
				BasePageV2.takeScreenshot();
			}
			
			try {
				for(int i=1; i<=5; i++) {
					if(Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5)) {
						if(homepagev2.freshAppNotificationCancel.isDisplayed()) {
							homepagev2.freshAppNotificationCancel.click();
						}
						else {
							break;
						}
					}
				}
			}
			catch(Exception e) {
				
			}
			
			if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25)) {
				if(homepagev2.profilepic.isDisplayed()) {
					test.log(LogStatus.INFO, "Sign up Successful");
				}
				else {
					HomePageV2.reportFail("Unable to signup with valid credentials");
					BasePageV2.takeScreenshot();
				}
			}
			
		}catch(Exception e) {
			
		}
	}catch (Exception e) {}
	
	
	
	
	
}

// Please dont use this method created this method for one of the test case
public static void signuptestCaseIncluded() throws Exception {
	
	LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
	HomePageV2 homepagev2=new HomePageV2(driver,test);
	String testCase="'Verify Next button functionality by entering both Email and Mobile No'";
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	 int rowno=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",rowno , testCase);
	
	
	String email=Utilities.generateEmailid();
	String pwd="vinoth123";
	//String pin="1234";
	String mobileNumber= Utilities.generateMobileNumber();
	
	if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5)) {
		if(homepagev2.profilepic.isDisplayed()) {
			test.log(LogStatus.INFO, "Already in home page");
			HomePageV2.logout();
			
		}
		else {
			test.log(LogStatus.INFO, "Signing in");
			BasePageV2.takeScreenshot();
		}
	}
	
	
	try{
		
		if(Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 5)) {
			test.log(LogStatus.INFO, "Clicking on 'JOIN FREE FOR 30 DAYS' Button");
			BasePageV2.takeScreenshot();
			launchPageV2.SignUpfromWelcomePage.click();
		}
	}catch(Exception e) {
		BasePageV2.takeScreenshot();
	}
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 5)) {
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
		if(Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 5)) {
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
			
	}catch(Exception e) {
		
	}
			
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
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
		Thread.sleep(5000);
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			if(launchPageV2.pinContainer.isDisplayed()) {
					homepagev2.smokeresults(testCase, rowno , "Pass");
					test.log(LogStatus.PASS, "Test Case: "+testCase+" is passed");
				launchPageV2.pinContainer.clear();
				launchPageV2.pinContainer.sendKeys(pin);
			}
		}
		else {
			homepagev2.reportFail("'Create Pin' Page not displayed");
			homepagev2.smokeresults(testCase, rowno , "Fail");
			System.out.println();
		}
			
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		try {
			//launchPageV2.buttonCreatePin.click();
		}catch(Exception e) {}
	}
	catch(Exception e) {}
	
	//Confirm pin
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 3)) {
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
	Thread.sleep(2000);
	
	try {
		if(Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
			launchPageV2.kidsName.clear();
			launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
		}
		else {
			HomePageV2.reportFail("Kids name text field not displayed");
			BasePageV2.takeScreenshot();
		}
		
		if(Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5)) {
			launchPageV2.DOB.click();
		}
		else {
			HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();
		}
		
		if(Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5)) {
			launchPageV2.DOBSelect.click();
		}
		else {
			HomePageV2.reportFail("Date selector not displayed");
			BasePageV2.takeScreenshot();
		}
		
		try {
			driver.hideKeyboard();
			}
			catch(Exception e) {}
		try{
			launchPageV2.next.click();
			
			Thread.sleep(7000);
			launchPageV2.next.click();
			
		}catch(Exception e) {
			HomePageV2.reportFail("Next Button not displayed in Kids detail page");
			BasePageV2.takeScreenshot();
		}
		
		
		
		try {
			for(int i=1;i<=4;i++) {
			WebElement element=driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id='com.tv.vootkids:id/character_icon'])["+i+"]"));
			if(Utilities.explicitWaitVisible(driver,element, 5)) {
				
				//if(element.isDisplayed()) {
					element.click();
				/*}
				else {
					break;
				}*/
			}
			else {
				HomePageV2.reportFail("Kids Characters not displayed");
				BasePageV2.takeScreenshot();
			}
			
			}
			Thread.sleep(7000);
			WebElement Skills = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
			if(Utilities.explicitWaitVisible(driver, Skills, 25)) {
				Skills.click();
			}
			else {
				HomePageV2.reportFail("Skills not displayed");
				BasePageV2.takeScreenshot();
			}
			try {
				Thread.sleep(2000);
				launchPageV2.next.click();
			}catch(Exception e) {
				HomePageV2.reportFail("Next button not displayed in character selection page");
				BasePageV2.takeScreenshot();
			}
			if(Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 25)) {
				launchPageV2.letsGo.click();
			}
			else {
				HomePageV2.reportFail("lets Go button not displayed in character selection page");
				BasePageV2.takeScreenshot();
			}
			
			try {
				for(int i=1; i<=5; i++) {
					if(Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 2)) {
						if(homepagev2.freshAppNotificationCancel.isDisplayed()) {
							homepagev2.freshAppNotificationCancel.click();
						}
						else {
							break;
						}
					}
				}
			}
			catch(Exception e) {
				
			}
			
			if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25)) {
				if(homepagev2.profilepic.isDisplayed()) {
					test.log(LogStatus.INFO, "Sign up Successful");
				}
				else {
					HomePageV2.reportFail("Unable to signup with valid credentials");
					BasePageV2.takeScreenshot();
				}
			}
			
		}catch(Exception e) {
			
		}
	}catch (Exception e) {}
}
public void verifyAndProgressBar() throws Exception
{


 
    
	for(int k=0;k<10;k++) 
{ 
		 if(progressBar.size()==0)	
       break;
     	Thread.sleep(3000);
}        


    
if(progressBar.size()>0)
  {
	          for(int k=0;k<10;k++) 
        { 
       //  if(!Utilities.explicitWaitVisible(driver, progress,5))
     		 if(progressBar.size()==0)	
           break;
     		 else
     			 Thread.sleep(5000);
        }   
             
         if(progressBar.size()>0)
	   reportFail("Video not loaded within 45 seconds");
  }


}

}

	
	

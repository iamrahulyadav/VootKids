package com.viacom.pagesVersion2;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class SettingsPageV2 extends BasePageV2 {

	public SettingsPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	

//	///////////// Adding Setting Elements values  //////////////
	
	@FindBy(id ="com.tv.vootkids:id/btn_settings_menu")
	public WebElement settingsIcon;
	
	@FindBy(xpath="//android.widget.TextView[@text='SWITCH PROFILE']")
	public WebElement switchProfile;
	
	@FindBy(xpath = "//android.widget.TextView[@text='PARENT ZONE']")
	public WebElement ParentZoneTile;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Enter pin to access']")
	public WebElement enterPinToacess;
	
	@FindBy(xpath = "//android.widget.TextView[@text='com.tv.vootkids:id/btn_back']")
	public WebElement submitbtnParentZone;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Forgot Pin?']")
	public WebElement forGotPinText;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.tv.vootkids:id/btn_back']")
	public WebElement bckBtnParentZone;
	
	@FindBy(id = "com.tv.vootkids:id/input_pinView") // Parent Pin container
	 public WebElement parentPinContainer;

	
	@FindBy(xpath = "//android.widget.TextView[@text='SETTINGS']") // Setting Page after entering the Pin
	public WebElement settingTextinParantZone;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Account']")
	public WebElement settingTextinParantZoneAccount;
	
	@FindBy(xpath="//android.widget.TextView[@text='Profiles']")
	public WebElement settingTextinParantZoneProfiles;
	
	@FindBy(xpath="//android.widget.TextView[@text='Profiles']")
	public WebElement settingTextinParantZoneDevice;
	
	@FindBy(xpath="//android.widget.TextView[@text='Share']")
	public WebElement settingTextinParantZoneShare;
	
	@FindBy(xpath="//android.widget.TextView[@text='Help & Support']")
	public WebElement settingTextinParantZoneHelpSupport;
	
	@FindBy(xpath="//android.widget.TextView[@text='Rate Us']")
	public WebElement settingTextinParantZoneRateUs;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/back_btn_settings']")
	public WebElement settingTextinParantZonebackbtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='LOGOUT']")
	public WebElement settingsLogOut;
	
	@FindBy(xpath="//android.widget.TextView[@text='ACCOUNT']")
	public WebElement settingsAccount;
	
	@FindBy(xpath="//android.widget.TextView[@text='Email']")
	public WebElement accountEmail;
	
	@FindBy(xpath="//android.widget.TextView[@text='Mobile']")
	public WebElement accountMobile;
	
	@FindBy(xpath="//android.widget.TextView[@text='Reset Password']")
	public WebElement accountResetPawd;
	
	@FindBy(xpath="//android.widget.TextView[@text='Reset Password']")
	public WebElement accountResetparZonePin;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/btn_profile_menu']")
	public WebElement accountBackBtn;
	
	
//	Passing the Email ID hard coded here 
	@FindBy(xpath="//android.widget.TextView[@text='suresh.k@ifocussystec.in']")
	public WebElement accountEmailId;
	
	
//	Passing the Mobile number hard coded here 
	@FindBy(xpath="//android.widget.TextView[@text='8904584821']")
	public WebElement accountMobileNum;
	
	
	@FindBy(xpath="//android.widget.TextView[@text ='ADD EMAIL']")
	public WebElement addEmail;

// ACCOUNT 'Email page'
	@FindBy(xpath="//android.widget.ImageView[@resouce-id = 'com.tv.vootkids:id/btn_profile_menu']")
	public WebElement addMailBackbtn;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='com.tv.vootkids:id/edit_text_email_id']")
	public WebElement addMailEditText;
	
	@FindBy(xpath="//android.widget.TextView[@text='Email']")
	public WebElement addMailuserTile;
	
	@FindBy(xpath="//android.widget.TextView[@text='Save']")
	public WebElement addMailSaveBtn;
	
//Pop up Email change success massage elements parameters
	
	@FindBy(xpath="//android.widget.TextView[@text='EMAIL ADDED']")
	public WebElement addMailSucssEmailAdd;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/button_cancel']")
	public WebElement addMailSucssEmailAddCnlBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='This Email ID is not valid. Please choose another one.']")
	public WebElement addMailWrongMsg;
	
// Setting Page Account "Mobile Number" Screen
	
	@FindBy(xpath="//android.widget.TextView[@text='ADD MOBILE NUMBER']")
	public WebElement addMobileNumTile;
	
	@FindBy(xpath="//android.widget.TextView[@text='Save']")
	public WebElement addMobileNumSaveBtn;
	
	@FindBy(id="com.tv.vootkids:id/edit_text_phone_number")
	public WebElement addMobileNumEditText;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/btn_profile_menu']")
	public WebElement addMobileNumBackBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='com.tv.vootkids:id/text_country_code']")
	public WebElement addMobileNumCountryCode;
	
	@FindBy(xpath="//android.widget.TextView[@text='IN +91']")
	public WebElement addMobileNumCuntryCodeText;
	
// Settings Profiles Screen verify	
	@FindBy(xpath="//android.widget.TextView[@text='Profiles']")
	public WebElement settingsProfile;
	
	@FindBy(xpath="//android.widget.TextView[@text='PROFILES']")
	public WebElement settingsProfileTile;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/btn_profile_menu']")
	public WebElement settingsProfileBackBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='Create New Profile']")
	public WebElement settingsProfileCreateNewPro;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/profile_image']")
	public WebElement settingsProfileCreateImg;
	
	// Edit Profile Page validating
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/btn_profile_menu']")
	public WebElement setiingsProfileEditProBckBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='EDIT PROFILE']")
	public WebElement settingsEditProfilePageTile;
	
	@FindBy(xpath="//android.widget.Button[@text='DELETE PROFILE']")
	public WebElement settingsEditProfileDelProBtn;
	
	@FindBy(xpath="//android.widget.Button[@text='NEXT']")
	public WebElement settingsEditProfilrNextBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='Name']")
	public WebElement settingsProEditProName;
	
	@FindBy(xpath="//android.widget.TextView[@text='Date of birth']")
	public WebElement settingsProEditProDateOfBirth;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='com.tv.vootkids:id/name_edit_text']")
	public WebElement editProNameEditText;
	
	@FindBy(xpath="//android.widget.TextView[@text='Please enter Name']")
	public WebElement editProErrorMsgForName;
	
	@FindBy(xpath="//android.widget.Button[@text='DONE']")
	public WebElement editproDoneBtn;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/selected_tick']")
	public WebElement settingsProfileSelectedTick;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/list_text']")
	public WebElement editTextinEditProfile;
	
	@FindBy(xpath ="//android.widget.TextView[@text='CREATE PROFILE']")
	public WebElement settingsProfileCreatProfileTile;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/dob_date_et']")
	public WebElement settingsProfileEditProDateEdit;
	
	@FindBy(id="com.tv.vootkids:id/dob_month_et")
	public WebElement settingsProfileEditProMonthEdit;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/dob_year_et']")
	public WebElement settingsProfileEditProYearEdit;
	
  //Date Of The Birth fileds
	@FindBy(xpath="//android.widget.TextView[@resoure-id='com.tv.vootkids:id/dob_year_et']")
	public WebElement settingsDateOfYear;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'2019')]")
	public WebElement Year2019;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'2005')]")
	public WebElement year2005;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'1999')]")
	public WebElement year1999;
	
	@FindBy(id= "android:id/date_picker_header_year")
	public WebElement calendarYearPick;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/back_btn_settings']")
	public WebElement editProBackBtn;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	public WebElement calenderOkBtn;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='android:id/button2']")
	public WebElement calendercancelBtn;
	
	@FindBy(xpath="//android.widget.Button[@text='YES']")
	public WebElement prodelPopYesBtn;
	
	@FindBy(xpath="//android.widget.Button[@text='NO']")
	public WebElement prodelPopNoBtn;
	
	
	
	
}

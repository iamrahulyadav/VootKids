package com.viacom.pagesVersion2;

import java.util.List;

//import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class ShowsPageV2 extends BasePageV2{
	public ShowsPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name= "All Shows")
	public WebElement allShowsTab;
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.tv.v18.viola:id/banner_image_hero_tray']")
	public WebElement allShowsFeaturevideo;
	
	@FindBy(xpath= "//android.widget.RadioButton[@text='Originals']")
	public WebElement originalsTab;
	
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.tv.v18.viola:id/tv_series_title']")
	public List<WebElement> allEpisodesList;
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.tv.v18.viola:id/banner_image_hero_tray']")
	public WebElement originalsFeaturevideo;

	
	@FindBy(name ="Most Popular")
	public WebElement mostPopularTab;
	
	@FindBy(xpath ="//android.widget.TextView[contains(@text,'Voot Shorts')]")
	public WebElement vootShortsTab;
	
	@FindBy(xpath ="//android.widget.TextView[@resource-id='com.tv.v18.viola:id/txt_related_title']")
	public List<WebElement> vootShortsRelatedTitle;
	
	
	
	@FindBy(name ="A - Z")
	public WebElement atozTab;
	
	@FindBy(id ="com.tv.v18.viola:id/btn_filter_shows")
	public WebElement filterTab;

	 @FindBy(id="com.tv.v18.viola:id/title_toolbar")
	 public WebElement toolBarTitle;
	 
	 @FindBy(id="com.tv.v18.viola:id/title")
	 public WebElement playingVideoTitle;

	 
	 @FindBy(id="com.tv.v18.viola:id/show_description")
	 public WebElement descriptionDetailText;
	 
	 @FindBy(id="com.tv.v18.viola:id/more_btn")
	 public WebElement moreChevronBtn;
	 
	 @FindBy(id="com.tv.v18.viola:id/meta_data")
	 public WebElement showMetaData;
	 
	 @FindBy(xpath="//android.widget.CheckBox[@resource-id='com.tv.vootkids:id/checkbox_fav_selector']")
	 public WebElement favIconShowDetails;
}

	
	


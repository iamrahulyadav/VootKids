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
	
	
	//sHOW DETAIL TESTCASE 
	
	
	@FindBy(id="com.tv.vootkids:id/button_back")
	public WebElement showDetailPageBackButton;
	
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/imageview_media_image']")
	public WebElement showDetailPageCharacterImage;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/button_preview' and @text='PLAY']")
	public WebElement showDetailPagePlayButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/textview_title']")
	public WebElement showDetailPageShowTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/textview_description']")
	public WebElement showDetailPageShowInfo;
	
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/textview_available_langauges']")
	public WebElement showDetailPageLanguagesAvailableInfo;
	
	
	
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/btn_download_item']")
	public WebElement showDetailPageDownlaodEpisodesButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/header_text' and @text='DOWNLOAD EPISODES']")
	public WebElement downloadEpisodesScreenTitle;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt'  and @text='EPISODES']")
	public WebElement showDetailPageEpisodesSection;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement showDetailPageEpisodesSectionFirstVideo;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public List<WebElement> showDetailPageEpisodesSectionVideos;

      @FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_description']")
	public List<WebElement> showDetailPageEpisodesSectionTitles;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_description']")
	public List<WebElement> showDetailPageEpisodesSectionTitlesWay2;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and ( @text='RELATED SHOWS'  or @text='You May Also Like')]")
	public WebElement showDetailPageEditorialSection;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED SHOWS']")
	public WebElement showDetailPageEditorialSectionRelatedTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  ( @text='YOU MAY ALSO LIKE'  or @text='You May Also Like')")
	public WebElement showDetailPageEditorialSectionYouMayLikeTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED SHOWS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement showDetailPageEditorialSectionRelatedTrayFirstContent;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED SHOWS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_title']")
	public WebElement showDetailPageEditorialSectionRelatedTrayFirstContentTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='You May Also Like']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement showDetailPageEditorialSectionYouMayLikeTrayFirstContent;
	
	
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

	
	


package com.viacom.pagesVersion2;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BooksPageV2 extends BasePageV2{

	public BooksPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(id="com.tv.vootkids:id/btn_profile_menu")
	public WebElement profilepic;
	
	@FindBy(xpath="//android.widget.TextView[@text='NEW BOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement newBooksFirstBook;
	
	@FindBy(id="com.tv.vootkids:id/checkbox_fav_selector")
	public WebElement bookDetailPageFavoriteButton;
	
	@FindBy(id="com.tv.vootkids:id/imageview_media_image")
	public WebElement bookDetailPageBookCoverImage;
	
	@FindBy(id="com.tv.vootkids:id/textview_title")
	public WebElement bookDetailPageBookTitle;
	
	@FindBy(id="com.tv.vootkids:id/textview_author_name")
	public WebElement bookDetailPageBookAuthorName;
		
	@FindBy(id="com.tv.vootkids:id/textview_description")
	public WebElement bookDetailPageBookDescription;
	
    @FindBy(xpath="//android.widget.TextView[@text='Level']//ancestor::android.widget.LinearLayout//android.widget.TextView[@resource-id='com.tv.vootkids:id/textview_level_count']")
    public WebElement bookDetailPageBookLevelCount;
	
    @FindBy(xpath="//android.widget.TextView[@text='Read Time']//ancestor::android.widget.LinearLayout//android.widget.TextView")
    public WebElement bookDetailPageReadTime;

    @FindBy(xpath="//android.widget.TextView[@text='Narration']//ancestor::android.widget.LinearLayout//android.widget.ImageView")
    public WebElement bookDetailPageNarrationIcon;
	
    @FindBy(id="com.tv.vootkids:id/button_back")
  	public WebElement bookDetailPageBackButton;
	
  	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/btn_download_item']")
  	public WebElement bookDetailPageDownlaodEpisodesButton;
  	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']")
	public WebElement bookDetailPageRelatedBooksTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement bookDetailPageRelatedTrayFirstContent;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement bookDetailPageRelatedTrayContents;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_title']")
	public WebElement bookDetailPageEditorialSectionRelatedTrayFirstContentTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_title']")
	public List<WebElement> bookDetailPageEditorialSectionRelatedTrayContentTitles;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']////android.widget.ImageView[@resource-id='com.tv.vootkids:id/category_icon']")
	public List<WebElement> bookDetailPageEditorialSectionRelatedTrayContentIcons;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/recent_types_txt' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.tv.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_description']")
	public List<WebElement> bookDetailPageEditorialSectionRelatedTrayContentBookDescriptions;
	
	
	
  	
}

package com.viacom.pagesVersion2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class KidsPlayerPageV2 extends BasePageV2{
	public KidsPlayerPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/// checking whether present again after pulling  TRY TRY TRY 
	//committing	
	
	@FindBy(id="com.tv.vootkids:id/player_close_btn")
	public WebElement playerCloseButton;
	
	@FindBy(id="com.tv.vootkids:id/player_title")
	public WebElement playerTitle;
	
	
	@FindBy(id="com.tv.vootkids:id/player_subtitle")  
	public WebElement playerSubTitle;	
	
	@FindBy(id="com.tv.vootkids:id/player_setting")
	public WebElement playerSettings;
	
	@FindBy(id="com.tv.vootkids:id/favourite_button")
	public WebElement playerFavoriteButton;
	

	@FindBy(id="com.tv.vootkids:id/back_ward")
	public WebElement playerBackwardButton;
	
	@FindBy(id="com.tv.vootkids:id/button_play_pause_toggle")
	public WebElement playerPlayPauseButton;
	
	
	@FindBy(id="com.tv.vootkids:id/btn_playlist_expand")
	public WebElement playerBottomUpArrowButton;
	
	@FindBy(id="com.tv.vootkids:id/btn_playlist_collapse")
	public WebElement playerBottomDownArrowButton;
	
	@FindBy(id="com.tv.vootkids:id/forward_player")
	public WebElement playerForwardButton;
	
	@FindBy(id="com.tv.vootkids:id/seek_bar") 
	public WebElement playerSeekBar;	
	
	@FindBy(id="com.tv.vootkids:id/duration_player")
	public WebElement playerCurrentDuration;
	
	@FindBy(id="com.tv.vootkids:id/totla_duration_player")
	public WebElement playerTotalDuration;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.tv.vootkids:id/skin_overlay_controls_layout']")
	public WebElement videoPlayer; 
	
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/tab_title' and @text='Episodes']")
	public WebElement playListEpisodeTab; 
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.tv.vootkids:id/tab_title'  and @text='Related']")
	public WebElement playListRelatedTab; 
	
	
	public void pauseVideo() throws Exception
	{
		  if(Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 5))
		  {
			  playerPlayPauseButton.click();
		  }
		  else
		  {
			  try{
			       videoPlayer.click();			
			       playerPlayPauseButton.click();
			  }
			  catch(Exception e){   }
			  
			  //Check Whether paused
			  
			  if(Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 5))
			  {
				  test.log(LogStatus.INFO, "Video is Paused successfully");
			  }
			  else
			  {
				  try{
				  videoPlayer.click();				 
				  playerPlayPauseButton.click();
				  }
				  catch(Exception e){   }
				  if(Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 10))
				  test.log(LogStatus.INFO, "Video is Paused successfully");  
				  else				  
				  BasePageV2.reportFail("Failed to pause the video");
			  }
		  }
	}
	//scrub functionality
	public void slidehalf(AndroidDriver driver) throws Exception
	{
		HomePageV2 homePage = new HomePageV2(driver, test);
	    int startX = 0;
	    String timebefscrub="", timeaftscrub="" , timeaftscrubback="";
	    //Get vertical location of seekbar.
	    int startY = 0;
	    int endX =0;
	    if(Utilities.explicitWaitVisible(driver,playerCurrentDuration, 10))
		  {
			 timebefscrub=playerCurrentDuration.getText();
			 test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen before scrubbing the video- "+timebefscrub);
		  }
		  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
	if(Utilities.explicitWaitClickable(driver, playerSeekBar, 20))
		{
		// Get x and y coordinates of webelement// 
	     startX = playerSeekBar.getLocation().getX();       
	    //Get vertical location of seekbar.
	     startY = playerSeekBar.getLocation().getY();
	     endX = 400 + startX;
	  }
	else
		reportFail("Seek bar is not visible");
	
	//Verifying fwd scrub
	    TouchAction action1 = new TouchAction((MobileDriver)driver);
	  try{
		  action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release().perform();
	  }
	  catch(Exception e){BasePageV2.reportFail("Failed to scrub the video");}
	  
	    homePage.verifyAndProgressBar();
	    try{
	    	for(int i=1;i<5;i++)
	    	   if(!Utilities.explicitWaitVisible(driver,playerCurrentDuration, 5))
	    	   {
	    		   videoPlayer.click();
	    		   playerPlayPauseButton.click();
	    		   Thread.sleep(3000);
	    	   }
	    	   else
	    		   break;
	    }catch(Exception e)
	    {
	    	reportFail("Failed to pause the video after scrubbing  video forward");
	    }
	    
	    if(Utilities.explicitWaitVisible(driver,playerCurrentDuration, 10))
		  {
			 timeaftscrub=playerCurrentDuration.getText();
			 test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen after scrubbing the video forward- "+timeaftscrub);
			 if(!timeaftscrub.equals(timebefscrub))
				  test.log(LogStatus.INFO, "Scrubber has moved forward succesfully");
			 else
				  test.log(LogStatus.INFO, "Failed to scrub the video forward");
		  }
		  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
	    
	  //Verifying backward scrub 
	    
	    try{
			  action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX-300, startY)).release().perform();
		  }
	      catch(Exception e){BasePageV2.reportFail("Failed to scrub the video backward");}
		    homePage.verifyAndProgressBar();
		    try{
			    try{
			    	for(int i=1;i<5;i++)
			    	   if(!Utilities.explicitWaitVisible(driver,playerCurrentDuration, 5))
			    	   {
			    		   videoPlayer.click();
			    		   playerPlayPauseButton.click();
			    		   Thread.sleep(3000);
			    	   }
			    	   else
			    		   break;
			    }
			    catch(Exception e)
			    {
			    	reportFail("Failed to pause the video after scrubbing  video forward");
			    }
		    }catch(Exception e)
		    {
		    	reportFail("Failed to pause the video after scrubbing  video backward");
		    }
		    
		    if(Utilities.explicitWaitVisible(driver,playerCurrentDuration, 5))
			  {
				 timeaftscrubback=playerCurrentDuration.getText();
				 test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen after scrubbing the video backward- "+timeaftscrubback);
				 if(!timeaftscrub.equals(timeaftscrubback))
				 test.log(LogStatus.INFO, "Scrubber has moved backward succesfully");
				 else
				 test.log(LogStatus.INFO, "  Failed to scrub the video backward");
			  }
			  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
		    
		    
	        
	}
	

}

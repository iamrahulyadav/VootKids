package com.viacom.pagesVersion2;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;
import io.appium.java_client.android.AndroidDriver;

public class ReadPageV2 extends BasePageV2{
	public ReadPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//android.widget.TextView[@text='NEW AUDIOSTORIES']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")
	public List<WebElement> newAudioText1;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.tv.vootkids:id/checkbox_fav_selector']")
	public WebElement favIconAudioDetails;

}
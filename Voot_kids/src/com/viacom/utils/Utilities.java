package com.viacom.utils;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.mail.FileFilterDateIntervalUtils;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
/*************************************************************************************
 * Class        : Utilities
 * Purpose      : This class is used for reusable functions across the framework
 * Remarks      : none
 * Author       : Roja KC, Ifocus
 * Modifications:
 *                20 March 2017 - First created
 
 **************************************************************************************/
public class Utilities extends BaseTestV2{

	private static final WebElement HomePageV2 = null;
	public static AppiumDriverLocalService service;
	
	public static boolean explicitWaitVisible(WebDriver driver, WebElement element, int time)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception e)
		  {
		   return false;
		  }
		  return true;
	}
	
	
	//Scrubbing methods
	public static void scrubtillend(AndroidDriver driver, WebElement seekbar) throws Exception
	{
		try
		{
			int startpoint = seekbar.getLocation().getX();
			int xstart = startpoint + seekbar.getSize().getWidth();
			int yend = seekbar.getLocation().getY();
			TouchAction act = new TouchAction(driver);
			act.press(PointOption.point(startpoint, yend)).moveTo(PointOption.point(xstart - 1, yend)).release()
					.perform();

			test.log(LogStatus.INFO, "Scrubbed till end of Audio Player");
			BasePageV2.takeScreenshot();
		}
		catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Unable to Scrub");
			BasePageV2.takeScreenshot();
		}
	}

	public static void scrubtillhalf(AndroidDriver driver, WebElement seekbar) throws Exception
	{
		try
		{
			int startpoint = seekbar.getLocation().getX();
			int xstart = startpoint + seekbar.getSize().getWidth() / 2;
			int yend = seekbar.getLocation().getY();
			TouchAction act = new TouchAction(driver);
			act.press(PointOption.point(startpoint, yend)).moveTo(PointOption.point(xstart - 1, yend)).release()
					.perform();

			test.log(LogStatus.INFO, "Scrubbed till end of Audio Player");
			BasePageV2.takeScreenshot();
		}
		catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Unable to Scrub");
			BasePageV2.takeScreenshot();
		}
	}
	
	
	public static void verticalSwipeCheckElement(AndroidDriver driver,String end) throws InterruptedException {
	    //scrolling starts  
	            int width  =driver.manage().window().getSize().getWidth();  
	                int height = driver.manage().window().getSize().getHeight();               
	                int startx = width/2;
	                int endx = startx;
	                int starty =  (int)(height*0.80) ;
	                int endy = height/2;
	               // int endy = (int) (height * 0.10);
	                for(int i=0;i<4;i++) 
	       {   
	                 try{
	                  TouchAction act=new TouchAction(driver);
	                  (new TouchAction(driver))
	                         .press(PointOption.point(startx, starty))
	                         .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
	                          .moveTo(PointOption.point(startx, endy))
	                         .release()
	                         .perform();                 }
	                 catch(Exception e)
	                 {}
	              if (driver.findElements(By.xpath(end)).size()>0)
	        break; 
	              
	              
	              /*if(i>10)
	                  {
	                   driver.setConnection(Connection.NONE);
	             driver.setConnection(Connection.WIFI);
	             Thread.sleep(60000);
	                  }*/
	      }
	   }
	
	public static boolean explicitWaitClickable(WebDriver driver,WebElement element,int time)
	 {
		// driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	  try{
	  WebDriverWait wait = new WebDriverWait(driver, time);
	  wait.until(ExpectedConditions.elementToBeClickable(element));
	  }
	  catch(Exception e)
	  {
	   return false;
	  }
      //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  return true;
	 }
	public static boolean isDisplayed(WebDriver driver, WebElement element)
	{
       /*try
       {
		for(int i=0;i<5;i++)
		{
			if(element.isDisplayed())
			break;					
		}
       }
		catch(Exception e)
                {
                  return false;
                }
		return true;*/
		 driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	       try
	       {
		
				if(element.isDisplayed())
					return true;				
			
	       }
			catch(Exception e)
	                {
	                  return false;
	                }
	       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
	}
	 public static String generateEmailid() {
		  String strRandom = "";
		  String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
		  Random rnd = new Random();
		  StringBuilder strRandomNumber = new StringBuilder(9);
		  for (int i = 0; i < 4; i++)
		   {strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));}
		  strRandom = strRandomNumber.toString();  
			String email = strRandom +"@" + "gmail" +".com";
			return email;
		 }
	 public static String generateMobileNumber() {
		  String strRandom = "";
		  String strNumbers = "0123456789";
		  Random rnd = new Random();
		  StringBuilder strRandomNumber = new StringBuilder(9);
		  for (int i = 0; i<9; i++)
		   {strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));}
		  strRandom = strRandomNumber.toString();  
			String mobileNumber = "7"+strRandom;
			return mobileNumber;
		 }
	 
	 public static String generateRandomName() {
		  String strRandom = "";
		  String strNumbers = "abcdefghijklmnopqrstuvwxyz";
		  Random rnd = new Random();
		  StringBuilder strRandomNumber = new StringBuilder(9);
		  for (int i = 0; i<4; i++)
		   {strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));}
		  strRandom = strRandomNumber.toString();  
			String name = strRandom+" "+strRandom;
			return name;
		 }
	 
	 
	 public static String generateEmailidwothoutom() {
		  String strRandom = "";
		  String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
		  Random rnd = new Random();
		  StringBuilder strRandomNumber = new StringBuilder(9);
		  for (int i = 0; i < 4; i++)
		   {strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));}
		  strRandom = strRandomNumber.toString();  
			String email = strRandom +"@" + "gmail" +".c";
			return email;
		 }
	 
	 public static void verticalSwipe(AndroidDriver driver) throws InterruptedException {
			//scrolling starts		
		 int width  =driver.manage().window().getSize().getWidth();  
         int height = driver.manage().window().getSize().getHeight();               
         int startx = width/2;
         int endx = startx;
         int starty =  (int)(height*0.80) ;
         int endy = height/2;
        // int endy = (int) (height * 0.10); 
          try{
           TouchAction act=new TouchAction(driver);
           (new TouchAction(driver))
                  .press(PointOption.point(startx, starty))
                  .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                   .moveTo(PointOption.point(startx, endy))
                  .release()
                  .perform();                 }	
          catch(Exception e)
          {}
	 }
	 
	 public static void captureScreenshot(WebDriver driver) throws IOException {
			EventFiringWebDriver e = new EventFiringWebDriver(driver);
			File srcFile = e.getScreenshotAs(OutputType.FILE);

			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
			Calendar cal = Calendar.getInstance();
			String sysdate1 = dateFormat.format(cal.getTime());
			
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("HHmmss");		
			String sysdate2 = dateFormat1.format(cal.getTime());
	        
			File destFile = new File("Screenshots/"+sysdate1 +"/"+"/"+ sysdate2 + ".png");
			FileUtils.copyFile(srcFile, destFile);
		}

		/*---------------------------------------------------------------------------------------------------------------------*/

		public static void captureScreenshot(AndroidDriver driver) throws IOException {

			EventFiringWebDriver e = new EventFiringWebDriver(driver);
			File srcFile = e.getScreenshotAs(OutputType.FILE);

			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
			Calendar cal = Calendar.getInstance();
			String sysdate1 = dateFormat.format(cal.getTime());
			
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("HHmmss");		
			String sysdate2 = dateFormat1.format(cal.getTime());
			File destFile = new File("Screenshots/"+sysdate1 +"/"+"/"+ sysdate2 + ".png");
			FileUtils.copyFile(srcFile, destFile);
		}
	
		
		 public static void startAppiumServer() {

			  service=AppiumDriverLocalService.buildService(
						new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files (x86)\\Appium\\node.exe"))
								.withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js"))
								.withIPAddress("127.0.0.3").usingPort(4725));
				if (service.isRunning() == true) {
					service.stop();
				} else {
					service.start();
				}
			}
			public static void stopAppiumServer() {
				service.stop();
				System.out.println("service::" + service.isRunning());

			}
   
			
			 //vertical swipe
			public static void verticalSwipe(AndroidDriver driver,String end) throws InterruptedException {
			    //scrolling starts  
			            int width  =driver.manage().window().getSize().getWidth();  
			                int height = driver.manage().window().getSize().getHeight();               
			                int startx = width/2;
			                int endx = startx;
			                int starty =  (int)(height*0.80) ;
			                int endy = height/2;
			               // int endy = (int) (height * 0.10);
			                for(int i=0;i<height;i++) 
			       {   
			                 try{
			                  TouchAction act=new TouchAction(driver);
			                  (new TouchAction(driver))
			                         .press(PointOption.point(startx, starty))
			                         .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
			                         .moveTo(PointOption.point(startx, endy))
			                         . release()
			                         .perform();                 }
			                 catch(Exception e)
			                 {}
			              if (driver.findElements(By.xpath(end)).size()>0)
			        break; 
			              
			              
			      }
			   }
			
			//modified accordingly to voot kids
			public static void verticalSwipeDown(AndroidDriver driver) throws InterruptedException {
				//scrolling starts		
				        int width  =driver.manage().window().getSize().getWidth(); 	
				            int height = driver.manage().window().getSize().getHeight();		            	
				            int startx = width / 2;
				           
				            int starty = (int) (height * 0.70);
				           int endy = (int) (height * 0.80);
				            try{
				               // driver.swipe(startx, starty, startx, endy, 1000);
				               }
				            catch(Exception e){e.printStackTrace();}
			}
			
			
			
				            
			public static void horizontalSwipe(AndroidDriver driver)
			   {
			    
			           int screenwidth  = driver.manage().window().getSize().getWidth(); 
			               int screenheight = driver.manage().window().getSize().getHeight();
			              
			               int startx = (int) (screenwidth * (0.9));
			               int endx = (int)(screenwidth *(0.05));
			               int starty =  screenheight/2;
			               int endy = screenheight/2;
			            //swipe from right to left//
			               
			               try{
			                   TouchAction act=new TouchAction(driver);
			                   (new TouchAction(driver))
			                          .press(PointOption.point(startx, starty))
			                          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
			                           .moveTo(PointOption.point(endx, endy))
			                          .release()
			                          .perform();                 } 
			                  catch(Exception e)
			                  {}                        
			   }
			
			public static void horizontalSwipeulta(AndroidDriver driver)
			   {
			    
			           int screenwidth  = driver.manage().window().getSize().getWidth(); 
			               int screenheight = driver.manage().window().getSize().getHeight();
			              
			               int startx = (int) (screenwidth * (0.9));
			               int endx = (int)(screenwidth *(0.05));
			               int starty =  screenheight/2;
			               int endy = screenheight/2;
			            //swipe from right to left//
			               
			               try{
			                   TouchAction act=new TouchAction(driver);
			                   (new TouchAction(driver))
			                          .press(PointOption.point(endx, starty))
			                          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
			                           .moveTo(PointOption.point(startx, endy))
			                          .release()
			                          .perform();                 } 
			                  catch(Exception e)
			                  {}                        
			   }
			
			
			
			
			public static void horizontalSwipeonelemnt(AndroidDriver driver)
			{
				
				 int screenwidth  = driver.manage().window().getSize().getWidth(); 
		            int screenheight = driver.manage().window().getSize().getHeight();
			           
			            int startx = (int) (screenwidth * (0.8));
			            int endx = (int)(screenwidth *(0.1));
			            int starty = (int) (screenheight*(0.8));
			            int endy = (int) (screenheight*(0.8));
			         //swipe from right to left//			            
			        // driver.swipe(startx, starty, endx, endy, 4000);			         			         
			}
			
			public static void horizontalSwipeForTray(AndroidDriver driver,WebElement el)
			{				
				 int width  = el.getSize().getWidth();
		            int height = el.getSize().getHeight();
		            int startx = (int) (width * (0.9));
		            int endx = (int)(width *(0.05));
		            int starty =  (int) (height*0.8);
		            int endy =(int) (height*0.8);;
		         //swipe from right to left//		            
		         //driver.swipe(startx, starty, endx, endy, 4000);
		            try{
		            	TouchAction act=new TouchAction(driver);
		            	(new TouchAction(driver))
		            	.press(PointOption.point(startx, starty))
		            	.waitAction(WaitOptions.waitOptions(Duration.ofMillis(4000)))
		            	.moveTo(PointOption.point(endx, endy))
		            	.release()
		            	.perform();   
		            } 
		            catch(Exception e)
		            {}                
			}
			
			//WebElement screen
			public static void pauseVideo(AndroidDriver driver,WebElement screen)
			{
				ExtentTest test = null;
				HomePageV2 homePage = new HomePageV2(driver, test);
				
				 Dimension size = screen.getSize();
				 int x5 = size.width / 2;
			        int y5 = size.height / 2;
			       // screen.click();
			        System.out.println(x5);
			        System.out.println(y5);
			        MultiTouchAction mul=new MultiTouchAction(driver);
				TouchAction action=new TouchAction(driver);
				try{
					//action.longPress(screen, 2000).release().perform();
					// action.tap(x5,y5).perform();
					 MultiTouchAction maction = new MultiTouchAction((MobileDriver) driver);
					 //TouchAction action51 = new TouchAction((MobileDriver)driver).tap(360,252).waitAction(500);    
				        //TouchAction action51 = new TouchAction((MobileDriver)driver).longPress(x5, y5).waitAction(1500);
				        screen.click();
				       // maction.add(action51).add(action51).perform();			       
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}	
		
				
			
			
			 public boolean CheckImage(AndroidDriver driver,WebElement el) throws Exception {
					
				      boolean imgflag;  
				        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", el);
				        if (!ImagePresent)
				        {
				             imgflag=false;
				        }
				        else
				        {
				        	imgflag=true;
				        }
				        return imgflag;
					}
			 
			 public static boolean containsSubImage(String screenImagePath) throws InterruptedException
			    {
			     //method to check whether screenshot image contains sub image
			     
			        //String small = "C:\\Users\\Roja KC\\Desktop\\2.png";
			        String small= System.getProperty("user.dir")+"\\images\\preBlank.PNG";
			         
			         Image image1 = Toolkit.getDefaultToolkit().getImage(screenImagePath);
			         Image image2 = Toolkit.getDefaultToolkit().getImage(small);
			  int count=0;
			         try 
			         {    
			             PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false);
			             PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false);
			  
			             int[] data1 = null;
			  
			             if (grab1.grabPixels()) 
			             { 
			                 int width = grab1.getWidth(); 
			                int height = grab1.getHeight();
			                 data1 = new int[width * height];
			                 data1 = (int[]) grab1.getPixels();
			             }
			  
			             int[] data2 = null;
			  
			             if (grab2.grabPixels()) {
			                 int width = grab2.getWidth();
			                 int height = grab2.getHeight();
			                 data2 = new int[width * height];
			                 data2 = (int[]) grab2.getPixels();
			             }
			          
			            // System.out.println("Pixels equal: " + java.util.Arrays.equals(data1, data2));
			            try { for(int i=0;i<data1.length;i++)
			             {
			              for(int j=0;j<data2.length;j++)
			              if(data1[i]==data2[j])
			              {count++;
			              break;
			              }
			             }
			            }
			            catch(Exception e)
			            {
			             
			            }
			             if(count==309120)
			              //System.out.println("SUB IMAGE PRESENT IN LARGER IMAGE");
			             return true;
			             else
			              //System.out.println("SUB IMAGE not PRESENT IN LARGER IMAGE");
			             return false;
			             
			  
			         } catch (InterruptedException e1) {
			             e1.printStackTrace();
			             return false;
			         }
			
          }// end of image checking
			 
			 public static String returnLatestFile(String Folder)
	         {
				 String screenshotFolder=Folder;
		    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		         FileFilterDateIntervalUtils filter =
		             new FileFilterDateIntervalUtils("2017-04-04", "2050-01-20");
		         File folder =  new File(screenshotFolder);
		         File files[] = folder.listFiles(filter);
		        //date
		       
		         String fileName1=files[files.length-1].getName();
				return fileName1;
	        	 
	         }
			 
			 public static void WebElementScreenshot(AndroidDriver driver,WebElement el) throws IOException
			    {
			    // Get entire page screenshot
			        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			        BufferedImage  fullImg = ImageIO.read(screenshot);

			        // Get the location of element on the page
			        org.openqa.selenium.Point point = el.getLocation();

			        // Get width and height of the element
			        int eleWidth = el.getSize().getWidth();
			        int eleHeight = el.getSize().getHeight();

			        // Crop the entire page screenshot to get only element screenshot
			        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
			            eleWidth, eleHeight);
			        ImageIO.write(eleScreenshot, "png", screenshot);

			        // Copy the element screenshot to disk
			        
			        File screenshotLocation = new File(System.getProperty("user.dir")+"//Images//current.png");
			        //File screenshotLocation = new File("C:\\images\\VootSelection_screenshot.png");
			        FileUtils.copyFile(screenshot, screenshotLocation);

			    }
			   public static Boolean imageMatch(String inputpath) throws IOException
			   {
				   //VootSelection_screenshot.png
				   String x= returnLatestFile(VootConstants.SCREENSHOT_PATH);
			     File fileInput = new File(System.getProperty("user.dir")+"//Images//current.png");
			    
			           File fileOutPut = new File(System.getProperty("user.dir")+"//Images//"+inputpath);
			           
			           BufferedImage bufferfileInput = ImageIO.read(fileInput);
			           DataBuffer bufferfileInput1 = ((RenderedImage) bufferfileInput).getData().getDataBuffer();
			           int sizefileInput = bufferfileInput1.getSize();                     
			           BufferedImage bufferfileOutPut = ImageIO.read(fileOutPut);
			           DataBuffer datafileOutPut = bufferfileOutPut.getData().getDataBuffer();
			           int sizefileOutPut = datafileOutPut.getSize();
			           Boolean matchFlag = true;
			           if(sizefileInput == sizefileOutPut) {                         
			              for(int i=0; i<sizefileInput; i++) {
			                    if(bufferfileInput1.getElem(i) != datafileOutPut.getElem(i)) {
			                          matchFlag = false;
			                          break;
			                    }
			               }
			           }
			           else {                           
			              matchFlag = false;
			           Assert.assertTrue(matchFlag, "Images are not same");
			    return matchFlag;
			    
			   }
			    return matchFlag;
			    
			   }
			   
				 public static String scrolltoElementusingtext(AndroidDriver driver,String text)
				 {
					 try{
			    	   driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
					 }
					 catch (Exception e) {
						 e.printStackTrace();
					}
					return text;
					 
				 }
				 public static Response requestUtility(String url)
				  {				   
				   Response resp = RestAssured.
				     given().
				     relaxedHTTPSValidation().
				     contentType(ContentType.JSON).
				     headers("peppaPig","M2ZseFBKUTE5YnFaY0xoMQ==qNG9f8sLNOc1mff/2lng2H3+/yXCgAxwfxXJ38cN3PtE33CD/tF7vSrL+1Es6qiEY1f8S0z1iPfvkLKRgvMhoEImWpfBao1noFXTpMMQOvJ/Tp/+ocKsB4A1E25vSzURHtv1ecpG+HX5KgKQnCUnww==").
				     headers("platform","android").
				     when().
				     get(url);			  
				   return resp;				     
				  }
				 				 
				 public static Response requestUtilitypost(String url,Map<String, String> map)
				  {			   
				   Response resp = RestAssured.
				     given().
				     relaxedHTTPSValidation().
				     contentType(ContentType.JSON).
				     headers("peppaPig","M2ZseFBKUTE5YnFaY0xoMQ==qNG9f8sLNOc1mff/2lng2H3+/yXCgAxwfxXJ38cN3PtE33CD/tF7vSrL+1Es6qiEY1f8S0z1iPfvkLKRgvMhoEImWpfBao1noFXTpMMQOvJ/Tp/+ocKsB4A1E25vSzURHtv1ecpG+HX5KgKQnCUnww==").
				     headers("platform","android").
				     parameters(map).
				     when().
				     get(url);				  
				   return resp;			     
				  }
				 
				 public static boolean isSelected(WebDriver driver, WebElement element)
				 {
				       
				   driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				        try
				        {
				  
				    if(element.isSelected())
				     return true;    
				   
				        }
				   catch(Exception e)
				                 {
				                   return false;
				                 }
				        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				   return true;
				 }
				 
					public  static void appbar(AndroidDriver driver,WebElement appbar)
				    {
				            int startx=appbar.getLocation().getX();
				            int starty=appbar.getLocation().getY();
				            int height = driver.manage().window().getSize().getHeight();
				            int endy=(int)(height*0.100) ;
				            //driver.swipe(startx,starty ,5, endy, 2000);
				            try{
				                   TouchAction act=new TouchAction(driver);
				                   (new TouchAction(driver))
				                          .press(PointOption.point(startx, starty))
				                          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				                           .moveTo(PointOption.point(5, endy))
				                          .release()
				                          .perform();   
				                   } 
				                  catch(Exception e)
				                  {}                        
				    }
					public static void horizontalSwipeCarousalSlow(AndroidDriver driver)
					   {
					    
					           int screenwidth  = driver.manage().window().getSize().getWidth(); 
					               int screenheight = driver.manage().window().getSize().getHeight();
					              
					               int startx = (int) (screenwidth * (0.9));
					               int endx = (int)(screenwidth *(0.5));
					               int starty =  screenheight/2;
					               int endy = screenheight/2;
					            //swipe from right to left//
					               
					               try{
					                   TouchAction act=new TouchAction(driver);
					                   (new TouchAction(driver))
					                          .press(PointOption.point(startx, starty))
					                          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					                           .moveTo(PointOption.point(endx, endy))
					                          .release()
					                          .perform();                 } 
					                  catch(Exception e)
					                  {}                        
					   }
					public static void horizontalSwipeCarousalSlow2(AndroidDriver driver)
					   {
					    
					           int screenwidth  = driver.manage().window().getSize().getWidth(); 
					               int screenheight = driver.manage().window().getSize().getHeight();
					              
					               int startx = (int) (screenwidth * (0.9));
					               int endx = (int)(screenwidth *(0.2));
					               int starty =  screenheight/2;
					               int endy = screenheight/2;
					            //swipe from right to left//
					               
					               try{
					                   TouchAction act=new TouchAction(driver);
					                   (new TouchAction(driver))
					                          .press(PointOption.point(startx, starty))
					                          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					                           .moveTo(PointOption.point(endx, endy))
					                          .release()
					                          .perform();                 } 
					                  catch(Exception e)
					                  {}                        
					   }
					public static void horizontalSwipeReverseSlow(AndroidDriver driver)
					   {
					    
					           int screenwidth  = driver.manage().window().getSize().getWidth(); 
					               int screenheight = driver.manage().window().getSize().getHeight();
					              
					               int startx = (int) (screenwidth * (0.9));
					               int endx = (int)(screenwidth *(0.5));
					               int starty =  screenheight/2;
					               int endy = screenheight/2;
					            //swipe from right to left//
					               
					               try{
					                   TouchAction act=new TouchAction(driver);
					                   (new TouchAction(driver))
					                          .press(PointOption.point(endx, starty))
					                          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					                           .moveTo(PointOption.point(startx, endy))
					                          .release()
					                          .perform();                 } 
					                  catch(Exception e)
					                  {}                        
					   }
					
					public static boolean verticalSwipeAndFind(AndroidDriver driver,String end) throws InterruptedException {
					    //scrolling starts  
					            	int width  =driver.manage().window().getSize().getWidth();  
					                int height = driver.manage().window().getSize().getHeight();               
					                int startx = width/2;
					                int endx = startx;
					                int starty =  (int)(height*0.80) ;
					                int endy = height/2;
					               // int endy = (int) (height * 0.10);
					                for(int i=0;i<height;i++) 
					       {   
					                 try{
					                  TouchAction act=new TouchAction(driver);
					                  (new TouchAction(driver))
					                         .press(PointOption.point(startx, starty))
					                         .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					                         .moveTo(PointOption.point(startx, endy))
					                         . release()
					                         .perform();                 }
					                 catch(Exception e)
					                 {}
					              if (driver.findElements(By.xpath(end)).size()>0)
					            	  return true; 
       
					      }
					      return false;
					  }
					
					 public static void verticalSwipeReverse(AndroidDriver driver) throws InterruptedException {
							//scrolling starts		
						 int width  =driver.manage().window().getSize().getWidth();  
				         int height = driver.manage().window().getSize().getHeight();               
				         int startx = width/2;
				         int endx = startx;
				         int starty =  (int)(height*0.80) ;
				         int endy = height/2;
				        // int endy = (int) (height * 0.10); 
				          try{
				           TouchAction act=new TouchAction(driver);
				           (new TouchAction(driver))
				                  .press(PointOption.point(startx, endy))
				                  .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				                   .moveTo(PointOption.point(startx, starty))
				                  .release()
				                  .perform();                 }	
				          catch(Exception e)
				          {}
					 }
														
}

		




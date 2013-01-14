package br.com.codigo.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import br.com.bean.LoginSuccess;

import com.opera.core.systems.OperaDriver;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@Test(groups = { "LoginOk" })
public class YouOk {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;

  @BeforeSuite
  public void setUp() throws Exception {
    driver = FirefoxBrowser();
    //driver = OperaBrowser();
     
    baseUrl = "http://RJ-DV-010";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

	public WebDriver FirefoxBrowser(){
		return driver = new FirefoxDriver();
	}
  
	/*public WebDriver InternetExplorerBrowser(){
		 return driver = new InternetExplorerDriver();
	}
	
	public WebDriver ChromeBrowser(){
		 return driver = new ChromeDriver();
	}*/
	
	public WebDriver OperaBrowser(){
		 return driver = new OperaDriver();
	}
	
	
	/*public WebDriver IPhoneBrowser() throws Exception{
		 return driver = new IPhoneDriver();
		
	}
	
	public WebDriver AndroidBrowser(){
		 return driver = new AndroidDriver();
	}*/


  @Test public void testYoukPasswordOk() throws Exception {
	System.out.println("testYoukPasswordOk");
    driver.get(baseUrl + "/youknow/affero");
    
    LoginSuccess login = lerXml();
    
    driver.findElement(By.id("login:username")).clear();
    driver.findElement(By.id("login:username")).sendKeys(login.getUser());
    driver.findElement(By.id("login:password")).clear();
    driver.findElement(By.id("login:password")).sendKeys(login.getPassword());
    driver.findElement(By.id("login:login-button")).click();
    //assertTrue(false);
  }

  
  @Test ( dependsOnMethods  = { "testYoukPasswordOk" })
  public void testTitleOk() throws Exception{
	  System.out.println("testTitleOk");
	  Assert.assertTrue(driver.findElement(By.id("login-name")) != null);
  }
  
   
  @Test ( dependsOnMethods  = { "testTitleOk" })
  public void level2() throws Exception{
	  System.out.println("level2");
	  Assert.assertTrue(true);
  }
  
  @Test ( dependsOnMethods  = { "level2" })
  public void level3() throws Exception{
	  System.out.println("level3");
	  Assert.assertTrue(true);
  }
  
    
  
  @AfterSuite
  public void tearDown() throws Exception {
    
    /*String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }*/
    
    driver.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
  
  public LoginSuccess lerXml() throws FileNotFoundException{
	  
	  XStream xstream = new XStream(new DomDriver());
	  xstream.alias("loginsuccess", LoginSuccess.class);
	  	  
	  BufferedReader input = new BufferedReader(new FileReader("test-xml" + File.separator + "login-success.xml"));

	  LoginSuccess login = (LoginSuccess) xstream.fromXML(input);
	   
	  return login;
  }
}



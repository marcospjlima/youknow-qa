package br.com.codigo.b;

import static junit.framework.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class YouK {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  //private StringBuffer verificationErrors = new StringBuffer();

  @BeforeSuite
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://RJ-DV-010/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    testTitleError();
  }

  @Test(groups = { "testng" })
  public void testYoukPasswordLock() throws Exception {
    driver.get(baseUrl + "/youknow/affero");
    driver.findElement(By.id("login:username")).clear();
    driver.findElement(By.id("login:username")).sendKeys("kkk");
    driver.findElement(By.id("login:password")).clear();
    driver.findElement(By.id("login:password")).sendKeys("321");
    driver.findElement(By.id("login:login-button")).click();
  }

  
  @Test ( groups = { "testng" }, dependsOnMethods  = { "testYoukPasswordLock" })
  public void testTitleError() throws Exception{
	  assertTrue(driver.findElement(By.className("msg-title")).getText().equals("Algo deu errado."));
  }
  
  @AfterSuite
  public void tearDown() throws Exception {
	  
	  
	  testTitleError();
	  
	  
	  
    
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
}


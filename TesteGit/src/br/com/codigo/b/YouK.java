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

@Test(groups = { "LoginError" })
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
     
  }

  public void testYoukPasswordLock() throws Exception {
	System.out.println("testYoukPasswordLock");
    driver.get(baseUrl + "/youknow/affero");
    driver.findElement(By.id("login:username")).clear();
    driver.findElement(By.id("login:username")).sendKeys("marcos.jesus@affero.com.br");
    driver.findElement(By.id("login:password")).clear();
    driver.findElement(By.id("login:password")).sendKeys("0");
    driver.findElement(By.id("login:login-button")).click();
    //assertTrue(driver.findElement(By.className("msg-title")).getText().equals("Algo deu errado."));
  }

  
  @Test ( dependsOnMethods  = { "testYoukPasswordLock" })
  public void testTitleError() throws Exception{
	  System.out.println("testTitleError");
	  assertTrue(driver.findElement(By.className("msg-title")).getText().equals("Algo deu errado."));
  }
  
  @Test ( dependsOnMethods  = { "testTitleError" })
  public void level2() throws Exception{
	  System.out.println("level2");
	  //assertTrue(driver.findElement(By.className("msg-title")).getText().equals("Algo deu errado."));
  }
  
  @Test ( dependsOnMethods  = { "level2" })
  public void level3() throws Exception{
	  System.out.println("level3");
	  //assertTrue(driver.findElement(By.className("msg-title")).getText().equals("Algo deu errado."));
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
}


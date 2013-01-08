package br.com.codigo.b;

import static junit.framework.Assert.assertTrue;

import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class YouK {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://youknow.affero.com.br/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testYoukPasswordLock() throws Exception {
    driver.get(baseUrl + "/youknow/affero");
    driver.findElement(By.id("login:username")).clear();
    driver.findElement(By.id("login:username")).sendKeys("marcos.jesus@affero.com.br");
    driver.findElement(By.id("login:password")).clear();
    driver.findElement(By.id("login:password")).sendKeys("123456");
    //driver.findElement(By.id("login:password")).sendKeys("kyo2910");
    driver.findElement(By.id("login:login-button")).click();
  }

  @After
  public void tearDown() throws Exception {
	  
	  
	  assertTrue(driver.findElement(By.className("msg-title")).getText().equals("Algo deu errado."));
	  
	  
    
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


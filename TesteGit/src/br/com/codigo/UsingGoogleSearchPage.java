package br.com.codigo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

public class UsingGoogleSearchPage {
    public static void main(String[] args) {
        // Create a new instance of a driver
        WebDriver driver = new HtmlUnitDriver();

        // Navigate to the right place
        driver.get("http://www.google.com/");

        // Create a new instance of the search page class
        // and initialise any WebElement fields in it.
        GoogleSearchPage page = (GoogleSearchPage) PageFactory.initElements(driver, GoogleSearchPage.class);

        // And now do the search.
        page.searchFor("Cheese");
    }
} 

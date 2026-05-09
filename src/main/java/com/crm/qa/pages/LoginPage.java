package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.base.WaitUtilsKeywords;

public class LoginPage extends TestBase {

    private final WaitUtilsKeywords waitUtils;

    // Page Factory - OR:
    private final By usernameField = By.xpath("//input[@name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton  = By.xpath("//input[@type='submit']");
    private final By signUpButton = By.xpath("//button[contains(text(),'Sign Up')]");
    private final By crmLogo      = By.xpath("//img[contains(@class,'img-responsive')]");

    // Initializing the Page Objects:
    public LoginPage() {
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtilsKeywords(driver);
    }

    // Actions:
    public String validateLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean validateCRMImage() {
        return waitUtils.isElementVisible(crmLogo);
    }

    public HomePage login() {
        waitUtils.typeByLocator(usernameField, prop.getProperty("username"));
        waitUtils.typeByLocator(passwordField, prop.getProperty("password"));
        waitUtils.scrollIntoView(loginButton);
        waitUtils.clickByLocator(loginButton);
        return new HomePage();
    }

    public HomePage login(String un, String pwd) {
        waitUtils.typeByLocator(usernameField, un);
        waitUtils.typeByLocator(passwordField, pwd);
        waitUtils.scrollIntoView(loginButton);
        waitUtils.clickByLocator(loginButton);
        return new HomePage();
    }

}

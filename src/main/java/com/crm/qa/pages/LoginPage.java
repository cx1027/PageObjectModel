package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath = "//input[@data-testid='login-email']")
	WebElement email;

	@FindBy(xpath = "//input[@data-testid='login-password']")
	WebElement password;

	@FindBy(xpath = "//button[@data-testid='login-submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text(),'Register here')]")
	WebElement registerLink;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}

	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}

	public void navigateToLogin() {
		driver.get(prop.getProperty("url") + "login");
	}

	public boolean validateEmailField() {
		return email.isDisplayed();
	}

	public boolean validatePasswordField() {
		return password.isDisplayed();
	}

	public HomePage login(String emailId, String pwd){
		email.sendKeys(emailId);
		password.sendKeys(pwd);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", loginBtn);

		return new HomePage();
	}

}

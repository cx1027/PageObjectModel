package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.base.WaitUtilsKeywords;

public class SignUpPage extends TestBase {

	WaitUtilsKeywords waitUtils;

	@FindBy(xpath = "//input[@data-testid='register-name']")
	WebElement fullName;

	@FindBy(xpath = "//input[@data-testid='register-email']")
	WebElement email;

	@FindBy(xpath = "//input[@data-testid='register-password']")
	WebElement password;

	@FindBy(xpath = "//button[@data-testid='register-submit']")
	WebElement createAccountBtn;

	@FindBy(xpath = "//a[contains(text(),'Log in')]")
	WebElement loginLink;

	public SignUpPage() {
		PageFactory.initElements(driver, this);
		waitUtils = new WaitUtilsKeywords(driver);
	}

	public String validateSignUpPageTitle() {
		return driver.getTitle();
	}

	public void navigateToRegister() {
		driver.get(prop.getProperty("url") + "register");
	}

	public boolean validateFullNameField() {
		return waitUtils.isElementVisible(
				By.xpath("//input[@data-testid='register-name']"));
	}

	public boolean validateEmailField() {
		return waitUtils.isElementVisible(
				By.xpath("//input[@data-testid='register-email']"));
	}

	public boolean validatePasswordField() {
		return waitUtils.isElementVisible(
				By.xpath("//input[@data-testid='register-password']"));
	}

	public boolean validateCreateAccountButton() {
		return waitUtils.isElementVisible(
				By.xpath("//button[@data-testid='register-submit']"));
	}

	public void register(String name, String emailId, String pwd) {
		waitUtils.typeByLocator(
				By.xpath("//input[@data-testid='register-name']"), name);
		waitUtils.typeByLocator(
				By.xpath("//input[@data-testid='register-email']"), emailId);
		waitUtils.typeByLocator(
				By.xpath("//input[@data-testid='register-password']"), pwd);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",
				waitUtils.findElementByLocator(
						By.xpath("//button[@data-testid='register-submit']")));
	}

	public void clickLoginLink() {
		waitUtils.clickByLocator(
				By.xpath("//a[contains(text(),'Log in')]"));
	}

}

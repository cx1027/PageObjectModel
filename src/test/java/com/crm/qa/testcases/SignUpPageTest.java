package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.SignUpPage;

public class SignUpPageTest extends TestBase {
	SignUpPage signUpPage;

	public SignUpPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		signUpPage = new SignUpPage();
		signUpPage.navigateToRegister();
	}

	@Test(priority = 1)
	public void signUpPageTitleTest() {
		String title = signUpPage.validateSignUpPageTitle();
		Assert.assertEquals(title, "Bidshop – fresh food for your business");
	}

	@Test(priority = 2)
	public void validateFullNameFieldTest() {
		boolean flag = signUpPage.validateFullNameField();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void validateEmailFieldTest() {
		boolean flag = signUpPage.validateEmailField();
		Assert.assertTrue(flag);
	}

	@Test(priority = 4)
	public void validatePasswordFieldTest() {
		boolean flag = signUpPage.validatePasswordField();
		Assert.assertTrue(flag);
	}

	@Test(priority = 5)
	public void validateCreateAccountButtonTest() {
		boolean flag = signUpPage.validateCreateAccountButton();
		Assert.assertTrue(flag);
	}

	@Test(priority = 6)
	public void registerTest() {
		signUpPage.register(
				prop.getProperty("signup_fullname"),
				prop.getProperty("signup_email"),
				prop.getProperty("signup_password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

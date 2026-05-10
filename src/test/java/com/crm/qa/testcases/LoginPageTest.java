package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		loginPage.navigateToLogin();
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Bidshop – fresh food for your business");
	}

	@Test(priority=2)
	public void validateEmailFieldTest(){
		loginPage.navigateToLogin();
		boolean flag = loginPage.validateEmailField();
		Assert.assertTrue(flag);
	}

	@Test(priority=3)
	public void validatePasswordFieldTest(){
		loginPage.navigateToLogin();
		boolean flag = loginPage.validatePasswordField();
		Assert.assertTrue(flag);
	}

	@Test(priority=4)
	public void loginTest(){
		homePage = loginPage.login(prop.getProperty("cart_email"), prop.getProperty("cart_password"));
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}

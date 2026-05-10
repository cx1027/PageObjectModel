package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;

public class HomePageTest extends TestBase {
	HomePage homePage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "Bidshop – fresh food for your business");
	}

	@Test(priority = 2)
	public void verifyLogoDisplayedTest() {
		boolean flag = homePage.validateLogoDisplayed();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void verifyNavigationLinksTest() {
		Assert.assertTrue(homePage.validateShopLinkDisplayed());
		Assert.assertTrue(homePage.validateCartLinkDisplayed());
		Assert.assertTrue(homePage.validateLoginLinkDisplayed());
		Assert.assertTrue(homePage.validateRegisterLinkDisplayed());
	}

	@Test(priority = 4)
	public void verifyProductListingTest() {
		boolean flag = homePage.validateProductCountDisplayed();
		Assert.assertTrue(flag);
	}

	@Test(priority = 5)
	public void verifySearchByKeywordBeefTest() {
		homePage.searchByKeyword("beef");
		boolean flag = homePage.isProductVisible("NZ Grass-Fed Beef Mince");
		Assert.assertTrue(flag, "Beef Mince product should appear after searching 'beef'");
	}

	@Test(priority = 6)
	public void verifySearchByKeywordChickenTest() {
		homePage.searchByKeyword("chicken");
		boolean flag = homePage.isProductVisible("Free-Range Chicken Breast");
		Assert.assertTrue(flag, "Chicken Breast product should appear after searching 'chicken'");
	}

	@Test(priority = 7)
	public void verifyFilterByCategoryAllCategoriesTest() {
		homePage.filterByCategory("All categories");
		String countText = homePage.getProductCountText();
		Assert.assertTrue(countText.contains("18"), "Expected 18 products with 'All categories' filter");
	}

	@Test(priority = 8)
	public void verifyFilterByCategoryBakeryTest() {
		homePage.filterByCategory("Bakery");
		String countText = homePage.getProductCountText();
		Assert.assertTrue(countText.contains("2"), "Expected 2 products with 'Bakery' filter");
	}

	@Test(priority = 9)
	public void verifyCategoryDairyKeywordMilkTest() {
		homePage.filterByCategory("Dairy");
		homePage.searchByKeyword("milk");
		boolean flag = homePage.isProductVisible("Anchor Full Cream Milk");
		Assert.assertTrue(flag, "Anchor Full Cream Milk should appear with Dairy filter + 'milk' keyword");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

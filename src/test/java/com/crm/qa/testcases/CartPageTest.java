package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.CartPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class CartPageTest extends TestBase {
	CartPage cartPage;
	HomePage homePage;
	LoginPage loginPage;

	public CartPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		cartPage = new CartPage();
		homePage = new HomePage();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void verifyCartPageTitleTest() {
		cartPage.navigateToCart();
		String title = cartPage.getCartPageTitle();
		Assert.assertEquals(title, "Bidshop – fresh food for your business");
	}

	@Test(priority = 2)
	public void verifyCartHeadingDisplayedTest() {
		cartPage.navigateToCart();
		Assert.assertTrue(cartPage.validateCartHeading());
		Assert.assertEquals(cartPage.getCartHeadingText(), "Your cart");
	}

	@Test(priority = 3)
	public void verifyEmptyCartMessageForGuestTest() {
		cartPage.navigateToCart();
		Assert.assertTrue(cartPage.validateEmptyCartMessage());
		Assert.assertEquals(cartPage.getEmptyCartMessageText(),
				"Please log in to view your cart.");
	}

	@Test(priority = 4)
	public void verifyLoginLinkInEmptyCartTest() {
		cartPage.navigateToCart();
		cartPage.clickLoginLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
	}

	@Test(priority = 5)
	public void verifyEmptyCartMessageForLoggedInTest() {
		loginPage.navigateToLogin();
		loginPage.login(prop.getProperty("cart_email"),
				prop.getProperty("cart_password"));
		cartPage.navigateToCart();
		Assert.assertTrue(cartPage.validateCartHeading());
	}

	@Test(priority = 6)
	public void verifyCartTableVisibleWhenEmptyTest() {
		loginPage.navigateToLogin();
		loginPage.login(prop.getProperty("cart_email"),
				prop.getProperty("cart_password"));
		cartPage.navigateToCart();
		Assert.assertTrue(cartPage.validateCartTable());
	}

	@Test(priority = 7)
	public void addOneProductToCartFromHomepageTest() {
		cartPage.navigateToCart();
		homePage.addFirstProductToCart();
		cartPage.navigateToCart();
		Assert.assertTrue(
				cartPage.isProductInCart("NZ Grass-Fed Beef Mince"),
				"Added product should appear in cart");
	}

	@Test(priority = 8)
	public void addTwoProductsToCartFromHomepageTest() {
		homePage.addFirstProductToCart();
		homePage.addSecondProductToCart();
		cartPage.navigateToCart();
		Assert.assertTrue(
				cartPage.isProductInCart("NZ Grass-Fed Beef Mince"),
				"First added product should appear in cart");
		Assert.assertTrue(
				cartPage.isProductInCart("Free-Range Chicken Breast"),
				"Second added product should appear in cart");
	}

	@Test(priority = 9)
	public void verifyProductVisibleInCartTest() {
		homePage.addFirstProductToCart();
		cartPage.navigateToCart();
		Assert.assertTrue(
				cartPage.isProductRowVisible("NZ Grass-Fed Beef Mince"),
				"Product row should be visible in cart table");
	}

	@Test(priority = 10)
	public void verifyProductPriceDisplayedTest() {
		homePage.addFirstProductToCart();
		cartPage.navigateToCart();
		String unitPrice = cartPage.getProductUnitPrice("NZ Grass-Fed Beef Mince");
		Assert.assertNotNull(unitPrice);
		Assert.assertTrue(unitPrice.length() > 0,
				"Unit price should be displayed for the product");
	}

	@Test(priority = 11)
	public void verifyQuantityFieldFunctionalTest() {
		homePage.addFirstProductToCart();
		cartPage.navigateToCart();
		cartPage.updateQuantity("NZ Grass-Fed Beef Mince", "3");
		String qty = cartPage.getProductQuantity("NZ Grass-Fed Beef Mince");
		Assert.assertEquals(qty, "3",
				"Quantity should be updated to 3 after modification");
	}

	@Test(priority = 12)
	public void verifyRemoveProductFromCartTest() {
		homePage.addFirstProductToCart();
		cartPage.navigateToCart();
		Assert.assertTrue(
				cartPage.isProductInCart("NZ Grass-Fed Beef Mince"),
				"Product should be in cart before removal");
		cartPage.clickRemoveButton("NZ Grass-Fed Beef Mince");
		Assert.assertFalse(
				cartPage.isProductInCart("NZ Grass-Fed Beef Mince"),
				"Product should be removed from cart after clicking remove");
	}

	@Test(priority = 13)
	public void verifyCartSubtotalCalculationTest() {
		homePage.addFirstProductToCart();
		homePage.addSecondProductToCart();
		cartPage.navigateToCart();

		String price1 = cartPage.getProductUnitPrice("NZ Grass-Fed Beef Mince");
		String qty1 = cartPage.getProductQuantity("NZ Grass-Fed Beef Mince");
		String total1 = cartPage.getProductTotal("NZ Grass-Fed Beef Mince");

		String price2 = cartPage.getProductUnitPrice("Free-Range Chicken Breast");
		String qty2 = cartPage.getProductQuantity("Free-Range Chicken Breast");
		String total2 = cartPage.getProductTotal("Free-Range Chicken Breast");

		Assert.assertEquals(total1, price1 + " x " + qty1);
		Assert.assertEquals(total2, price2 + " x " + qty2);
	}

	@Test(priority = 14)
	public void verifyProceedToCheckoutButtonTest() {
		homePage.addFirstProductToCart();
		cartPage.navigateToCart();
		Assert.assertTrue(cartPage.validateProceedToCheckoutButton(),
				"Proceed to Checkout button should be visible");
	}

	@Test(priority = 15)
	public void verifyProceedToCheckoutNavigationTest() {
		homePage.addFirstProductToCart();
		cartPage.navigateToCart();
		cartPage.clickProceedToCheckout();
		Assert.assertTrue(driver.getCurrentUrl().contains("/checkout"),
				"Should navigate to checkout page after clicking Proceed to Checkout");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

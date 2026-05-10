package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.base.WaitUtilsKeywords;

public class CartPage extends TestBase {

	WaitUtilsKeywords waitUtils;

	@FindBy(xpath = "//h1[@data-testid='cart-heading']")
	WebElement cartHeading;

	@FindBy(xpath = "//p[@data-testid='cart-empty-message']")
	WebElement emptyCartMessage;

	@FindBy(xpath = "//a[@data-testid='cart-login-link']")
	WebElement loginLink;

	@FindBy(xpath = "//table[@data-testid='cart-table']")
	WebElement cartTable;

	@FindBy(xpath = "//span[@data-testid='cart-subtotal']")
	WebElement subtotal;

	@FindBy(xpath = "//button[@data-testid='cart-checkout']")
	WebElement proceedToCheckoutBtn;

	public CartPage() {
		PageFactory.initElements(driver, this);
		waitUtils = new WaitUtilsKeywords(driver);
	}

	public String getCartPageTitle() {
		return driver.getTitle();
	}

	public void navigateToCart() {
		driver.get(prop.getProperty("url") + "cart");
	}

	public boolean validateCartHeading() {
		return waitUtils.isElementVisible(
				By.xpath("//h1[@data-testid='cart-heading']"));
	}

	public String getCartHeadingText() {
		return waitUtils.getTextByLocator(
				By.xpath("//h1[@data-testid='cart-heading']"));
	}

	public boolean validateEmptyCartMessage() {
		return waitUtils.isElementVisible(
				By.xpath("//p[@data-testid='cart-empty-message']"));
	}

	public String getEmptyCartMessageText() {
		return waitUtils.getTextByLocator(
				By.xpath("//p[@data-testid='cart-empty-message']"));
	}

	public void clickLoginLink() {
		waitUtils.clickByLocator(
				By.xpath("//a[@data-testid='cart-login-link']"));
	}

	public boolean validateCartTable() {
		return waitUtils.isElementVisible(
				By.xpath("//table[@data-testid='cart-table']"));
	}

	public boolean isProductInCart(String productName) {
		return waitUtils.isElementVisible(
				By.xpath("//*[contains(text(),'" + productName + "')]"));
	}

	public boolean isProductRowVisible(String productName) {
		return waitUtils.isElementVisible(
				By.xpath("//tr[contains(.,'" + productName + "')]"));
	}

	public String getProductUnitPrice(String productName) {
		WebElement row = waitUtils.findElementByLocator(
				By.xpath("//tr[td[contains(.,'" + productName + "')]]"));
		return row.findElement(
				By.xpath(".//td[@data-testid='cart-unit-price']")).getText();
	}

	public String getProductQuantity(String productName) {
		WebElement row = waitUtils.findElementByLocator(
				By.xpath("//tr[td[contains(.,'" + productName + "')]]"));
		WebElement qtyInput = row.findElement(
				By.xpath(".//input[@data-testid='cart-quantity']"));
		return qtyInput.getAttribute("value");
	}

	public String getProductTotal(String productName) {
		WebElement row = waitUtils.findElementByLocator(
				By.xpath("//tr[td[contains(.,'" + productName + "')]]"));
		return row.findElement(
				By.xpath(".//td[@data-testid='cart-product-total']")).getText();
	}

	public void updateQuantity(String productName, String quantity) {
		WebElement row = waitUtils.findElementByLocator(
				By.xpath("//tr[td[contains(.,'" + productName + "')]]"));
		WebElement qtyInput = row.findElement(
				By.xpath(".//input[@data-testid='cart-quantity']"));
		qtyInput.clear();
		qtyInput.sendKeys(quantity);
	}

	public void clickRemoveButton(String productName) {
		WebElement row = waitUtils.findElementByLocator(
				By.xpath("//tr[td[contains(.,'" + productName + "')]]"));
		WebElement removeBtn = row.findElement(
				By.xpath(".//button[@data-testid='cart-remove']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", removeBtn);
	}

	public String getSubtotalText() {
		return waitUtils.getTextByLocator(
				By.xpath("//span[@data-testid='cart-subtotal']"));
	}

	public boolean validateProceedToCheckoutButton() {
		return waitUtils.isElementVisible(
				By.xpath("//button[@data-testid='cart-checkout']"));
	}

	public void clickProceedToCheckout() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",
				waitUtils.findElementByLocator(
						By.xpath("//button[@data-testid='cart-checkout']")));
	}

	public int getCartRowCount() {
		List<WebElement> rows = waitUtils.findVisibleElements(
				By.xpath("//table[@data-testid='cart-table']//tbody//tr"));
		return rows.size();
	}
}

package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.base.WaitUtilsKeywords;

import org.openqa.selenium.JavascriptExecutor;

public class HomePage extends TestBase {

	WaitUtilsKeywords waitUtils;

	@FindBy(xpath = "//a[@data-testid='nav-home']")
	WebElement logo;

	@FindBy(xpath = "//a[@data-testid='nav-products']")
	WebElement shopLink;

	@FindBy(xpath = "//a[@data-testid='nav-cart']")
	WebElement cartLink;

	@FindBy(xpath = "//a[@data-testid='nav-login']")
	WebElement loginLink;

	@FindBy(xpath = "//a[@data-testid='nav-register']")
	WebElement registerLink;

	@FindBy(xpath = "//input[@data-testid='filter-search']")
	WebElement searchInput;

	@FindBy(xpath = "//select[@data-testid='filter-category']")
	WebElement categorySelect;

	public HomePage() {
		PageFactory.initElements(driver, this);
		waitUtils = new WaitUtilsKeywords(driver);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean validateLogoDisplayed() {
		return waitUtils.isElementVisible(By.xpath("//a[@data-testid='nav-home']"));
	}

	public boolean validateShopLinkDisplayed() {
		return waitUtils.isElementVisible(By.xpath("//a[@data-testid='nav-products']"));
	}

	public boolean validateCartLinkDisplayed() {
		return waitUtils.isElementVisible(By.xpath("//a[@data-testid='nav-cart']"));
	}

	public boolean validateLoginLinkDisplayed() {
		return waitUtils.isElementVisible(By.xpath("//a[@data-testid='nav-login']"));
	}

	public boolean validateRegisterLinkDisplayed() {
		return waitUtils.isElementVisible(By.xpath("//a[@data-testid='nav-register']"));
	}

	public boolean validateProductCountDisplayed() {
		return waitUtils.isElementVisible(By.xpath("//span[@data-testid='filter-summary']"));
	}

	public String getProductCountText() {
		waitUtils.waitForTextNot(
				By.xpath("//span[@data-testid='filter-summary']"),
				"Loading products");
		return (String) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].textContent;", driver.findElement(By.xpath("//span[@data-testid='filter-summary']")));
	}

	public void searchByKeyword(String keyword) {
		waitUtils.typeByLocator(By.xpath("//input[@data-testid='filter-search']"), keyword);
	}

	public void clearSearch() {
		waitUtils.typeByLocator(By.xpath("//input[@data-testid='filter-search']"), "");
	}

	public void filterByCategory(String categoryLabel) {
		waitUtils.selectByVisibleText(By.xpath("//select[@data-testid='filter-category']"), categoryLabel);
	}

	public boolean isProductVisible(String productName) {
		return waitUtils.isElementVisible(
				By.xpath("//*[contains(text(),'" + productName + "')]"));
	}

	public void addFirstProductToCart() {
		waitUtils.clickByLocator(
				By.xpath("(//button[@data-testid='product-add-to-cart'])[1]"));
	}

	public void addSecondProductToCart() {
		waitUtils.clickByLocator(
				By.xpath("(//button[@data-testid='product-add-to-cart'])[2]"));
	}
}

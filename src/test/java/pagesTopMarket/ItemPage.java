package pagesTopMarket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ItemPage extends BasePage {

	@FindBy(css = ".ty-product-list__item-name")
	private List<WebElement> itemButton;
	@FindBy(css = ".ty-value-changer__increase")
	private WebElement increaseAmountButton;
	@FindBy(css = ".ty-value-changer__decrease")
	private WebElement decreaseAmountButton;
	@FindBy(css = ".advanced-buttons>.ty-add-to-wish")
	private WebElement addToWishListButton;
	@FindBy(css = ".cm-notification-content")
	private WebElement notificationMessage;
	@FindBy(css = ".ty-btn__add-to-cart")
	private WebElement addToCartButton;
	@FindBy(css = ".ty-add-to-compare")
	private WebElement addToCompareButton;
	@FindBy(css = ".cm-notification-close.close")
	private WebElement closeNotificationMessage;
	@FindBy(css = "div.ty-product-block")
	private WebElement itemInPage;
	@FindBy(css = ".cm-amount")
	private WebElement amountOfItemsField;
	@FindBy(css = ".cm-notification-content>h1")
	private WebElement notificationMessageTitle;
	
	public ItemPage(WebDriver driver) {
		super(driver);
	}

	@Step("Choose first item")
	public void chooseFirstItem() {
		waitForElementToBeVisible(itemButton.get(0));
		itemButton.get(0).click();
	}
	
	@Step("Check if on item page")
	public boolean isOnItemPage() {
		if(itemInPage.isDisplayed())
			return true;
		return false;
	}
	
	@Step("Get amount if items")
	public int getAmountOfItems() {
		String valueOfTheAmountBeforeIncrease=amountOfItemsField.getAttribute("value");
		return Integer.parseInt(valueOfTheAmountBeforeIncrease);
	}
	
	@Step("Increse amount: {amountToIncrease} of item:")
	public void increseAmount(int amountToIncrease) {
		int count=0;
		while(count<amountToIncrease) {
			click(increaseAmountButton);
			count++;
		}
	}
	
	@Step("Decrease amount: {amountToDecrease} of item")
	public void decreaseAmount(int amountToDecrease) {
		int count=0;
		while(count<amountToDecrease) {
			click(decreaseAmountButton);
			count++;
		}
	}
	
	@Step("Add to wish list")
	public void addToWishList() {
		click(addToWishListButton);
		waitForElementToBeVisible(notificationMessage);
	}
	
	@Step("Add to cart")
	public void addToCart() {
		click(addToCartButton);
		waitForElementToBeVisible(notificationMessage);
	}
	
	@Step("Add to compare list")
	public void addToCompare() {
		click(addToCompareButton);
		waitForElementToBeVisible(notificationMessage);
	}
	
	@Step("Get notification message title")
	public String getNotificationMessageTitle() {
		return yourText(notificationMessageTitle);
	}
	
	@Step("Close notification message")
	public void closeNotificationMessage() {
		click(closeNotificationMessage);
	}
}

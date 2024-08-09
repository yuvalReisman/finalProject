package testsTopMarket;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pagesTopMarket.HomePage;

@Epic("Item Page")
public class ItemPageTests extends BaseTest{

	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Get to item")
	@Description("Get to item's page")
	@Story("As a user after I click an item, I expect to get to it's page")
	public void tc01_getToItem() {
		homepage.hoverCategoryAndChoose("Accessories","Memory Cards");
		itempage.chooseFirstItem();
		boolean ExpectedToBeInItemPage=true;
		Assert.assertEquals(ExpectedToBeInItemPage,itempage.isOnItemPage());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Increase amount of items")
	@Description("Increase amount of items")
	@Story("As a user after I increase the amount of an item, the amount should be higher")
	public void tc02_increaseAmountOfItems() {
		int amountBeforeIncrease=itempage.getAmountOfItems();
		int AmountToIncrese=3;
		itempage.increseAmount(AmountToIncrese);
		int amountAfterIncrease=itempage.getAmountOfItems();
		Assert.assertEquals((amountBeforeIncrease+AmountToIncrese),amountAfterIncrease);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Decrease amount of items")
	@Description("Decrease amount of items")
	@Story("As a user I expect less amount from the same item, after I decrease the amount")
	public void tc03_decreaseAmountOfItems() {
		int amountBeforeIncrease=itempage.getAmountOfItems();
		int amount=3;
		itempage.increseAmount(amount);
		itempage.decreaseAmount(amount);
		int amountAfterIncreaseAndDecrease=itempage.getAmountOfItems();
		Assert.assertEquals(amountBeforeIncrease,amountAfterIncreaseAndDecrease);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add to wish list")
	@Description("Add to wish list one item get a notification")
	@Story("As a user after I add an item to wish list, I expect to get a success message")
	public void tc04_addToWishList() {
		itempage.addToWishList();
		String expectedMessage="THE PRODUCT WAS ADDED TO YOUR WISH LIST";
		String actualMessage=itempage.getNotificationMessageTitle();
		Assert.assertEquals(expectedMessage, actualMessage);
		itempage.closeNotificationMessage();
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add to cart multipule items")
	@Description("Add to cart multipule items and get a notification")
	@Story("As a user after I add an item multipule times to wish list, I expect to get a success message")
	public void tc05_addToCartMultipuleItems() {
		itempage.addToCart();
		String expectedMessage="THE PRODUCTS WERE ADDED TO YOUR CART";
		String actualMessage=itempage.getNotificationMessageTitle();
		Assert.assertEquals(expectedMessage, actualMessage);
		itempage.closeNotificationMessage();
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add to cart one item")
	@Description("Add to cart one item and get a notification")
	@Story("As a user after I add an item to cart, I expect to get a success message")
	public void tc06_addToCartOneItem() {
		int amountWeAlreadyIncreased=3;
		itempage.decreaseAmount(amountWeAlreadyIncreased);
		itempage.addToCart();
		String expectedMessage="THE PRODUCT WAS ADDED TO YOUR CART";
		String actualMessage=itempage.getNotificationMessageTitle();
		Assert.assertEquals(expectedMessage, actualMessage);
		itempage.closeNotificationMessage();
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add to compare")
	@Description("Add to compare list the item")
	@Story("As a user after I add an item to compare list, I expect to get a success message")
	public void tc07_addToCompare() {
		itempage.addToCompare();
		String expectedMessage="THE PRODUCT WAS ADDED TO YOUR COMPARISON LIST";
		String actualMessage=itempage.getNotificationMessageTitle();
		Assert.assertEquals(expectedMessage, actualMessage);
	}
}

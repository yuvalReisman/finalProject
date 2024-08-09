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

@Epic("Cart Page")
public class CartPageTests extends BaseTest{

	
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Zero items in cart")
	@Description("Verify 0 items in cart in opening the website")
	@Story("As a user I if I didn't add items to the cart, the cart should be empty")
	public void tc01_zeroItemsInCart() {
		String expectedAmountOfItemsInEmptyCart="0";
		String actualAmountInCart= cartpage.getAmountOfItemsInCartInHomePage();
		Assert.assertEquals(expectedAmountOfItemsInEmptyCart, actualAmountInCart);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add to cart")
	@Description("Add one item to cart")
	@Story("As a user when I add item to the cart, the amount of items should increase")
	public void tc02_addToCart() {
		homepage.hoverCategoryAndChoose("Accessories","Memory Cards");
		itempage.chooseFirstItem();
		itempage.addToCart();
		cartpage.clickOnExitNotificationMessage();
		String expectedAmountAfterAddingItem="1";
		String actualAmountOfItemsInCart= cartpage.getAmountOfItemsInCartInHomePage();
		Assert.assertEquals(expectedAmountAfterAddingItem, actualAmountOfItemsInCart);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Go to cart")
	@Description("Go to cart")
	@Story("As a user after I click on cart,the cart should be opened")
	public void tc03_goToCart() {
		navigationpage.getIntoCart();
		String expectedPageTitle="CART CONTENTS";
		String actualPageTitle= cartpage.getCartPageTitle();
		Assert.assertEquals(expectedPageTitle, actualPageTitle);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Recalculate cart")
	@Description("Recalculate cart and get a notification")
	@Story("As a user after I recalculate the cart's price, I should get a success message")
	public void tc04_recalculateCart() {
		cartpage.recalculateCart();
		String expectedNotification="Notice The product(s) have been updated successfully";
		String actualNotification= cartpage.getSuccessfulNotificationMessage();
		assert actualNotification.contains(expectedNotification);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Increase and recalculate cart")
	@Description("Increase and Recalculate cart to get the right price")
	@Story("As a user after I increase the amount of items and recalculate the cart, the price should be higher")
	public void tc05_increaseAndRecalculateCart() {
		String priceOfTheItemText=cartpage.getPriceOfTheItem();
		int priceOfTheItemInt=Integer.parseInt(priceOfTheItemText);
		cartpage.increaseItem();
		cartpage.recalculateCart();
		String totalPriceOfItemsText=cartpage.getTotalPriceOfTheItems();
		String totalPriceOfItemsTextWithoutComma=totalPriceOfItemsText.replace(",", "");
		int totalPriceOfItemsInt=Integer.parseInt(totalPriceOfItemsTextWithoutComma);
		Assert.assertEquals((priceOfTheItemInt*2),totalPriceOfItemsInt);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Close cart")
	@Description("Close cart and back to item's page")
	@Story("As a user after I click on close cart,the cart should be closed")
	public void tc06_closeCart() {
		cartpage.closeCart();
		String expectedPageTitle="MEMORY CARDS";
		String actualPageTitle= cartpage.getItemsPageTitle();
		Assert.assertEquals(expectedPageTitle, actualPageTitle);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Clear cart")
	@Description("Clear cart and get a notification")
	@Story("As a user after I clear the cart, I should get a success message")
	public void tc07_clearCart() {
		navigationpage.getIntoCart();
		cartpage.clearCart();
		String expectedMessage="Your cart is empty";
		String actualMessage= cartpage.getNoItemsInCartMessage();
		Assert.assertEquals(expectedMessage, actualMessage);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Proceed to checkout page")
	@Description("Proceed to checkout page")
	@Story("As a user after I add an item to the cart, I want to proceed to checkout")
	public void tc08_proceedToCheckout() {
		cartpage.closeCartAfterClear();
		itempage.chooseFirstItem();
		itempage.addToCart();
		cartpage.clickOnExitNotificationMessage();
		navigationpage.getIntoCart();
		cartpage.proceedToCheckout();
		String expectedPageTitle="SECURE CHECKOUT";
		String actualPageTitle= cartpage.getCheckoutPageTitle();
		Assert.assertEquals(expectedPageTitle, actualPageTitle);
	}
}

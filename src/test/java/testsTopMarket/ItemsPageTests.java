package testsTopMarket;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;
import pagesTopMarket.HomePage;

@Epic("Items Page")
public class ItemsPageTests extends BaseTest {

	@BeforeClass
	public void beforeAllClasses() {
		homepage.hoverCategoryAndChoose("Hardware", "Networking Cards");
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Change amount of displayed items")
	@Description("Change amount of displayed items in page")
	@Story("As a user if I change the amount of displayed items, I expect the amount to be changed")
	public void tc1_changeAmountOfDisplayedItemsInPage() {
		String chosenAmountToDisplay="16 Per Page";
		itemspage.amountOfItems(chosenAmountToDisplay);
		String expectedchosenAmountInField=chosenAmountToDisplay;
		String actualAmountInField=itemspage.getChosenAmountOfItemsInButton();
		assert actualAmountInField.equalsIgnoreCase(expectedchosenAmountInField);
		int expectedAmountOfItems=16;
		int actualAmountOfItems=itemspage.getAmountOfItems();
		Assert.assertEquals(expectedAmountOfItems, actualAmountOfItems);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Square display of items")
	@Description("Choose square display of items in page")
	@Story("As a user if I change the display of the items to square, all the items' display should be square display")
	public void tc2_chooseSquareDisplayItems() {
		itemspage.squareDisplayItems();
		Assert.assertTrue(itemspage.isSquareDisplayItems());
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Square and list display of items")
	@Description("Choose square and list display of items in page")
	@Story("As a user if I change the display of the items to square and list, all the items' display should be square and list display")
	public void tc3_chooseSquareAndListDisplayItems() {
		itemspage.squareAndListDisplayItems();
		Assert.assertTrue(itemspage.isSquareAndListDisplayItems());
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "List display of items")
	@Description("Choose list display of items in page")
	@Story("As a user if I change the display of the items to lust, all the items' display should be list display")
	public void tc4_chooseListDisplayItems() {
		itemspage.listDisplayItems();
		Assert.assertTrue(itemspage.isListDisplayItems());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Seacrh company")
	@Description("Seacrh company for it's items to be displayed")
	@Story("As a user if I search company, the company's name should be displayed")
	public void tc5_searchCompany() {
		String companyName = "Nedis";
		itemspage.searchForCompany(companyName);
		Assert.assertTrue(itemspage.isChosenComapnyDisplayed(companyName));
	}

	@Severity(SeverityLevel.MINOR)
	@Test (description = "Hide products filter")
	@Description("Hide products filter in page")
	@Story("As a user if I hide products filter, all the products should be hidden")
	public void tc6_hideProductsFilter() {
		itemspage.hideProductsFilter();
		Assert.assertTrue(itemspage.isProductsFilterHidden());
	}
}

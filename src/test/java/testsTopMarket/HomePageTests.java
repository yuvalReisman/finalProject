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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pagesTopMarket.HomePage;

@Epic("Home Page")
public class HomePageTests extends BaseTest {

	@AfterMethod
	public void afterEachTest() {
		navigationpage.getHome();
		Assert.assertTrue(homepage.isOnHomePage());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Search item")
	@Description("Search one item")
	@Story("As a user if I search an item, I expect to get to the results page")
	public void tc01_searchItem() {
		navigationpage.searchForItem("computer");
		String expectedPageTitle = "SEARCH RESULTS";
		String actualPageTitle = homepage.getsearchResultsPageTitle();
		Assert.assertEquals(expectedPageTitle, actualPageTitle);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Search departement")
	@Description("Search one departement")
	@Story("As a user after I search a department, I expect to get to the results page")
	public void tc02_searchDepartement() {
		String chosenDepartement="Laptops";
		navigationpage.searchDepartment(chosenDepartement);
		String expectedChosenDEpartmentTitle = chosenDepartement;
		String actualChosenDEpartmentTitle = homepage.getDepartmentPageTitle();
		assert actualChosenDEpartmentTitle.contains(expectedChosenDEpartmentTitle);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Choose category from menu")
	@Description("Choose one category from product categories menu")
	@Story("As a user if I choose category from products category menu, I expect to get to category's page")
	public void tc03_chooseCategoryFromProductCategoriesMenu() {
		String chosenCategory="CCTV & Alarm";
		homepage.chooseCategoryFromMenu(chosenCategory);
		String expectedPageTitle = chosenCategory;
		String actualPageTitle = homepage.getCategoryPageTitle();
		assert actualPageTitle.equalsIgnoreCase(expectedPageTitle);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Hover category and choose item")
	@Description("Hover category from product categories menu and choose item")
	@Story("As a user after I hover category from products category menu and choose an item, I expect to get to item's page")
	public void tc04_hoverCategoryFromProductCategoriesMenuAndChooseItem() {
		String chosenCategory="Memory Cards";
		homepage.hoverCategoryAndChoose("Accessories", chosenCategory);
		String expectedPageTitle = chosenCategory;
		String actualPageTitle = homepage.getCategoryPageTitle();
		assert actualPageTitle.equalsIgnoreCase(expectedPageTitle);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Choose category from below")
	@Description("Choose category from below")
	@Story("As a user after I choose category from below, I expect to get to category's page")
	public void tc05_chooseCategoryFromBelow() {
		String chosenCategory="MONITORS";
		homepage.chooseCategoryBelow(chosenCategory);
		String expectedPageTitle = chosenCategory;
		String actualPageTitle = homepage.getCategoryPageTitle();
		assert actualPageTitle.contains(expectedPageTitle);
	}
}

package pagesTopMarket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ItemsPage extends BasePage {

	@FindBy(css = ".ty-sort-container__views-a>.ty-icon-products-multicolumns")
	private WebElement squareDisplayItemsButton;
	@FindBy(css = ".ty-icon-products-without-options")
	private WebElement squareAndListDisplayItemsButton;
	@FindBy(css = ".ty-sort-container__views-a>.ty-icon-short-list")
	private WebElement listDisplayItemsButton;
	@FindBy(css = ".ty-compact-list__content")
	private WebElement itemsDisplayList;
	@FindBy(css = ".ty-grid-list__item")
	private WebElement itemsDisplaySquare;
	@FindBy(css = ".ty-product-list")
	private WebElement itemsDisplaySquareAndList;
	@FindBy(css = ".ty-sort-dropdown>a")
	private List<WebElement> dropDownButton;
	@FindBy(css = ".ty-sort-dropdown__content-item-a")
	private List<WebElement> optionsFromDropDown;
	@FindBy(css = ".hiddenFilter>a")
	private WebElement filterButton;
	@FindBy(css = ".ty-product-filters__search>input")
	private WebElement searchButton;
	@FindBy(css = "#sw_elm_pagination_steps")
	private WebElement amountOfItemsButton;
	@FindBy(css = ".ty-product-list")
	private List<WebElement> amountOfItemsLength;
	@FindBy(css = ".ty-grid-list__item")
	private List<WebElement> squareDisplayItems;
	@FindBy(css = ".ty-product-list")
	private List<WebElement> squareAndListDisplayItems;
	@FindBy(css = ".ty-compact-list__content")
	private List<WebElement> listDisplayItems;
	@FindBy(css = "a>label")
	private List<WebElement> CompaniesName;
	@FindBy(css = ".cm-product-filters")
	private WebElement productsFilter;

	public ItemsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Choose amount of items: {chosenAmountToDisplay} in amount of displayed items field")
	public void amountOfItems(String chosenAmountToDisplay) {
		for (WebElement dropDown : dropDownButton) {
			if (dropDown.getText().equalsIgnoreCase("24 Per Page")) {
				dropDown.click();
				for (WebElement option : optionsFromDropDown) {
					if (option.getText().equalsIgnoreCase(chosenAmountToDisplay)) {
						option.click();
						sleep(2000);
						break;
					}
				}
			}
		}
	}

	@Step("Get the chosen amount of displayed items")
	public String getChosenAmountOfItemsInButton() {
		return yourText(amountOfItemsButton);
	}

	@Step("Get the amount of items in page")
	public int getAmountOfItems() {
		int count=0;
		for (WebElement item : amountOfItemsLength) {
			count++;
		}
		return count;
	}

	@Step("Change to square display of items")
	public void squareDisplayItems() {
		click(squareDisplayItemsButton);
		waitForElementToBeVisible(itemsDisplaySquare);
	}

	@Step("Check if the items had changed to square display")
	public boolean isSquareDisplayItems() {
		return squareDisplayItems.size() > 0;
		
	}
	
	@Step("Change to square and list display of items")
	public void squareAndListDisplayItems() {
		click(squareAndListDisplayItemsButton);
		waitForElementToBeVisible(itemsDisplaySquareAndList);
	}
	
	@Step("Check if the items had changed to square and list display")
	public boolean isSquareAndListDisplayItems() {
		return squareAndListDisplayItems.size() > 0;
		
	}

	@Step("Change to list display of items")
	public void listDisplayItems() {
		click(listDisplayItemsButton);
		waitForElementToBeVisible(itemsDisplayList);
	}

	@Step("Check if the items had changed to list display")
	public boolean isListDisplayItems() {
		return listDisplayItems.size() > 0;
		
	}

	@Step("Search company: {company}")
	public void searchForCompany(String company) {
		waitForElementToBeVisible(searchButton);
		click(searchButton);
		fillInfo(searchButton, company);
	}
	
	@Step("Check if company: {company} is dispalyed")
	public boolean isChosenComapnyDisplayed(String companyName) {
		for (WebElement eachCompany : CompaniesName) {
			if (yourText(eachCompany).equalsIgnoreCase(companyName)) {
				return true;
			}
		}
		return false;
	}
	
	@Step("Hide products filter")
	public void hideProductsFilter() {
		waitForElementToBeClickable(filterButton);
		click(filterButton);
	}

	@Step("Check if hide products filte worked")
	public boolean isProductsFilterHidden() {
		if(productsFilter.isEnabled()) {
			return true;
		}
		return false;
	}
}

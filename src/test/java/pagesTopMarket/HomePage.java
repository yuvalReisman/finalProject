package pagesTopMarket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class HomePage extends BasePage {

	@FindBy(css = ".cm-popup-box>.ty-menu__items>.ty-menu__item>.ty-menu__item-link>div")
	private List<WebElement> categoriesMenuButton;
	@FindBy(css = ".ty-menu__submenu-item>a")
	private List<WebElement> itemInsideMenuButton;
	@FindBy(css = ".ty-mainbox-container>.ty-mainbox-title>a")
	private List<WebElement> categoriesBelowButton;
	@FindBy(css = "span.ty-mainbox-title__left")
	private WebElement searchResultsPageTitle;
	@FindBy(css = ".ty-logo-container")
	private WebElement logoCompanyButton;
	@FindBy(css = ".searched-categories>li")
	private WebElement departementPageTitle;
	@FindBy(css = "h1.ty-mainbox-title>span")
	private WebElement categoryPageTitle;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Step("Check if on home page")
	public boolean isOnHomePage() {
		if(logoCompanyButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	@Step("Get search result page title")
	public String getsearchResultsPageTitle() {
		return yourText(searchResultsPageTitle);
	}
	
	@Step("Get departement page title")
	public String getDepartmentPageTitle() {
		return yourText(departementPageTitle);
	}
	
	@Step("Get category page title")
	public String getCategoryPageTitle() {
		return yourText(categoryPageTitle);
	}
	
	@Step("Choose category: {nameOfCategory} from menu")
	public void chooseCategoryFromMenu(String nameOfCategory) {
		for (WebElement category : categoriesMenuButton) {
			if (category.getText().equalsIgnoreCase(nameOfCategory)) {
				click(category);
				break;
			}
		}
	}

	@Step("Hover category: {nameOfCategory} from menu and choose item: {ItemName}")
	public void hoverCategoryAndChoose(String nameOfCategory, String ItemName) {
		for (WebElement category : categoriesMenuButton) {
			if (yourText(category).equalsIgnoreCase(nameOfCategory)) {
				mouseHover(category);
				break;
			}
		}
		for (WebElement item : itemInsideMenuButton) {
			if (yourText(item).equalsIgnoreCase(ItemName)) {
				click(item);
				break;
			}
		}
	}
	
	@Step("Choose category: {nameOfCategory} from below")
	public void chooseCategoryBelow(String nameOfCategory) {
		for (WebElement category : categoriesBelowButton) {
			if (category.getText().equalsIgnoreCase(nameOfCategory)) {
				click(category);
				break;
			}
		}
	}
}

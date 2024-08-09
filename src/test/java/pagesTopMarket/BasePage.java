package pagesTopMarket;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public abstract class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	Actions mouse;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		mouse = new Actions(driver);
	}

	@Step("Click element: {el}")
	public void click(WebElement el) {
		el.click();
	}

	@Step("Fill element: {el} with text: {text}")
	public void fillInfo(WebElement el, String text) {
		el.sendKeys(text);
	}

	@Step("Get element's text: {el}")
	public String yourText(WebElement el) {
		return el.getText();
	}

	@Step("Wait for {millis} milliseconds")
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Step("Wait for element: {el} to be clickable")
	public void waitForElementToBeClickable(WebElement el) {
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}

	@Step("Wait for element: {el} to be visible")
	public void waitForElementToBeVisible(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	@Step("Hover element: {el}")
	public void mouseHover(WebElement el) {
		mouse.moveToElement(el).build().perform();
	}
}

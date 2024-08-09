package pagesTopMarket;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class CheckoutPage extends BasePage {

	@FindBy(css = ".ty-product-notification__buttons>div.ty-float-right>a.ty-btn")
	private WebElement checkoutButton;
	@FindBy(css = "input#email")
	private WebElement emailField;
	@FindBy(css = "#password1")
	private WebElement firstPasswordField;
	@FindBy(css = "#password2")
	private WebElement secondPasswordField;
	@FindBy(css = "input#eway_cc_number_")
	private WebElement cardNumbersField;
	@FindBy(css = "#email_error_message")
	private WebElement emailErrorMessage;
	@FindBy(css = "#password1_error_message")
	private WebElement firstPasswordError;
	@FindBy(css = "#password2_error_message")
	private WebElement secondPasswordError;
	@FindBy(css = ".cm-cc-number.cm-failed-label")
	private WebElement cardNumbersError;

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@Step("Go to checkout")
	public void goToCheckout() {
		waitForElementToBeVisible(checkoutButton);
		click(checkoutButton);
	}

	@Step("Check if there is no email error")
	public boolean isThereNoEmailError() {
		boolean noError=true;
		try {
			if (emailErrorMessage.isDisplayed())
				noError=false;
		} catch (Exception e) {
			return noError;
		}
		return noError;
	}

	@Step("Get email error message")
	public String getEmailErrorMessage() {
		return yourText(emailErrorMessage);
	}
	
	@Step("Get first password error")
	public String getFirstPasswordError() {
		return yourText(firstPasswordError);
	}
	
	@Step("Get second password error")
	public String getSecondPasswordError() {
		return yourText(secondPasswordError);
	}
	
	@Step("Clear email field")
	public void clearEmail() {
		emailField.clear();
	}

	@Step("Check if there is no passwords error")
	public boolean isThereNoPasswordsError() {
		boolean noError=true;
		try {
			if (firstPasswordError.isDisplayed() || secondPasswordError.isDisplayed())
				noError=false;
		} catch (Exception e) {
			return noError;
		}
		return noError;
	}
	
	@Step("Fill email's field with email: {email}")
	public void fillEmail(String email) {
		fillInfo(emailField, email);
		emailField.sendKeys(Keys.ENTER);
	}

	@Step("Clear all passwords fields")
	public void clearAllPasswordsFields() {
		firstPasswordField.clear();
		secondPasswordField.clear();
	}

	@Step("Fill first password's field with password: {password}")
	public void fillFirstPassword(String password) {
		fillInfo(firstPasswordField, password);
	}

	@Step("Fill first password's field with password: {password} and enter")
	public void fillFirstPasswordAndEnter(String password) {
		fillInfo(firstPasswordField, password);
		firstPasswordField.sendKeys(Keys.ENTER);
	}

	@Step("Fill second password's field with password: {password}")
	public void fillSecondPassword(String password) {
		fillInfo(secondPasswordField, password);
	}

	@Step("Fill second password's field with password: {password} and enter")
	public void fillSecondPasswordAndEnter(String password) {
		fillInfo(secondPasswordField, password);
		secondPasswordField.sendKeys(Keys.ENTER);
	}

	@Step("Fill card numbers's field with numbers: {numbers}")
	public void fillCardNumbers(String numbers) {
		fillInfo(cardNumbersField, numbers);
		cardNumbersField.sendKeys(Keys.ENTER);
	}
	
	@Step("Check if there is no card numbers error")
	public boolean isThereNoCardNumbersError() {
		boolean noError=true;
		try {
			if (cardNumbersError.isDisplayed())
				noError=false;
		} catch (Exception e) {
			return noError;
		}
		return noError;
	}

	@Step("Check if there is card numbers error")
	public boolean isThereCardNumbersError() {
		if(cardNumbersError.isDisplayed())
			return true;
		return false;
	}
	
	@Step("Clear card numbers's field")
	public void clearCardNumbers() {
		cardNumbersField.clear();
	}
}

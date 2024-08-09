package testsTopMarket;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

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

@Epic("Checkout Page")
public class CheckoutPageTests extends BaseTest {

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Go to checkout page")
	@Description("Go to checkout page")
	@Story("As a user when I add an item to cart, I expect to be able to go to checkout")
	public void tc01_goToCheckout() {
		homepage.hoverCategoryAndChoose("Accessories", "Memory Cards");
		itempage.chooseFirstItem();
		itempage.addToCart();
		checkoutpage.goToCheckout();
		String expectedPageTitle="SECURE CHECKOUT";
		String actualPageTitle= cartpage.getCheckoutPageTitle();
		Assert.assertEquals(expectedPageTitle, actualPageTitle);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill valid email")
	@Description("Fill valid email")
	@Story("As a user when I write a valid email, no error should be displayed ")
	public void tc02_fillValidEmail() {
		checkoutpage.fillEmail("yuval@gmail.com");
		boolean expectedNoError=true;
		Assert.assertEquals(expectedNoError,checkoutpage.isThereNoEmailError());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill invalid email")
	@Description("Fill invalid email and expect error")
	@Story("As a user when I write an invalid email, I expect an error ")
	public void tc03_fillInvalidEmail() {
		checkoutpage.clearEmail();
		checkoutpage.fillEmail("yuval");
		String expectedError="The email address in the Email field is invalid.";
		String actualError= checkoutpage.getEmailErrorMessage();
		Assert.assertEquals(expectedError, actualError);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill only first password and enter")
	@Description("Fill only first password and enter and expect error in second password field")
	@Story("As a user if I fill only the first password, I expect to get an error about the missing other password")
	public void tc04_fillOnlyFirstPasswordAndEnter() {
		checkoutpage.clearAllPasswordsFields();
		checkoutpage.fillFirstPasswordAndEnter("123");
		String expectedFirstPasswordError="The passwords in the Confirm password and Password fields do not match.";
		String ActualFirstPasswordError=checkoutpage.getFirstPasswordError();
		Assert.assertEquals(expectedFirstPasswordError, ActualFirstPasswordError);
		String expectedSecondPasswordError="The Confirm password field is mandatory.";
		String ActualSecondPasswordError=checkoutpage.getSecondPasswordError();
		Assert.assertEquals(expectedSecondPasswordError, ActualSecondPasswordError);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill only second password and enter")
	@Description("Fill only second password and enter and expect error in first password field")
	@Story("As a user if I fill only the second password, I expect to get an error about the missing other password")
	public void tc05_fillOnlySecondPasswordAndEnter() {
		checkoutpage.clearAllPasswordsFields();
		checkoutpage.fillSecondPasswordAndEnter("123");
		String expectedFirstPasswordError="The Password field is mandatory.";
		String ActualFirstPasswordError=checkoutpage.getFirstPasswordError();
		Assert.assertEquals(expectedFirstPasswordError, ActualFirstPasswordError);
		String expectedSecondPasswordError="The passwords in the Password and Confirm password fields do not match.";
		String ActualSecondPasswordError=checkoutpage.getSecondPasswordError();
		Assert.assertEquals(expectedSecondPasswordError, ActualSecondPasswordError);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill both passwords the same")
	@Description("Fill both passwords the same")
	@Story("As a user if I fill both passwords, there should be no error")
	public void tc06_fillBothPasswordsTheSame() {
		checkoutpage.clearAllPasswordsFields();
		checkoutpage.fillFirstPassword("123");
		checkoutpage.fillSecondPasswordAndEnter("123");
		boolean expectedNoError=true;
		Assert.assertEquals(expectedNoError,checkoutpage.isThereNoPasswordsError());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill two passwords differently")
	@Description("Fill two passwords differently and expect error")
	@Story("As a user if I fill passwords differently, I expect to get an error")
	public void tc07_fillPasswordsDifferently() {
		checkoutpage.clearAllPasswordsFields();
		checkoutpage.fillFirstPassword("123");
		checkoutpage.fillSecondPasswordAndEnter("456");
		String expectedFirstPasswordError="The passwords in the Confirm password and Password fields do not match.";
		String ActualFirstPasswordError=checkoutpage.getFirstPasswordError();
		Assert.assertEquals(expectedFirstPasswordError, ActualFirstPasswordError);
		String expectedSecondPasswordError="The passwords in the Password and Confirm password fields do not match.";
		String ActualSecondPasswordError=checkoutpage.getSecondPasswordError();
		Assert.assertEquals(expectedSecondPasswordError, ActualSecondPasswordError);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill valid card numbers")
	@Description("Fill the right length of card numbers")
	@Story("As a user if I fill valid card numbers, there should be no error")
	public void tc08_fillValidCardNumbers() {
		checkoutpage.fillCardNumbers("1284569255427784");
		boolean expectedNoError=true;
		Assert.assertEquals(expectedNoError,checkoutpage.isThereNoCardNumbersError());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Fill invalid card numbers")
	@Description("Fill less than the length of card numbers")
	@Story("As a user if I fill invalid card numbers, I expect to get an error")
	public void tc09_fillInvalidCardNumbers() {
		checkoutpage.clearCardNumbers();
		checkoutpage.fillCardNumbers("12");
		boolean expectedExistingError=true;
		Assert.assertEquals(expectedExistingError,checkoutpage.isThereCardNumbersError());
	}
}

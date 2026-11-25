package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CheckoutPage {
    private WebDriver driver;
    private By cartIcon = By.className("shopping_cart_link");
    private By checkoutButton = By.id("checkout");

    // CHECKOUT INFORMATION PAGE
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By overviewProductNames = By.className("inventory_item_name");
    private By totalPrice = By.className("summary_total_label");
    private By finishButton = By.id("finish");

    // ORDER CONFIRMATION PAGE
    private By confirmationMessage = By.className("complete-header");
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // CUSTOMER INFO
    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    // OVERVIEW IF PRODUCTS FROM CHECKOUT
    public List<String> getOverviewProductNames() {
        List<WebElement> products = driver.findElements(overviewProductNames);
        List<String> productNames = new ArrayList<>();

        for (WebElement product : products) {
            productNames.add(product.getText());
        }

        return productNames;
    }


    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}

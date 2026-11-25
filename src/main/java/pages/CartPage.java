package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By cartProductNames = By.className("inventory_item_name");
    private By removeButtons = By.className("cart_button");
    private By continueShoppingButton = By.id("continue-shopping");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getCartProductNames() {
        List<WebElement> products = driver.findElements(cartProductNames);
        List<String> productNames = new ArrayList<>();

        for (WebElement product : products) {
            productNames.add(product.getText());
        }

        return productNames;
    }

    // GET NUMBER OF PRODUCTS IN CART
    public int getCartProductCount() {
        return driver.findElements(cartProductNames).size();
    }

    // REMOVE FIRST PRODUCT FROM CART
    public void removeFirstProduct() {
        driver.findElements(removeButtons).get(0).click();
    }

    // CONTINUE SHOPPING
    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    //PROCEED TO CHECKOUT
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}

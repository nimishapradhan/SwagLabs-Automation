package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtons = By.className("btn_inventory");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSortOption(String optionValue) {
        Select sort = new Select(driver.findElement(sortDropdown));
        sort.selectByValue(optionValue);
    }

    public List<String> getProductNames() {
        List<WebElement> products = driver.findElements(productNames);
        List<String> names = new ArrayList<>();

        for (WebElement product : products) {
            names.add(product.getText());
        }

        return names;
    }

    public List<Double> getProductPrices() {
        List<WebElement> prices = driver.findElements(productPrices);
        List<Double> priceList = new ArrayList<>();

        for (WebElement price : prices) {
            String amount = price.getText().replace("$", "");
            priceList.add(Double.parseDouble(amount));
        }

        return priceList;
    }

    /**
    // ADD ALL ADD TO CART
    public void addProductsToCart(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            driver.findElements(addToCartButtons).get(i).click();
        }
    }

     //Problem: It adds first two products based on their index position.
     //- i = 0 → Sauce Labs Backpack
     //- i = 1 → Sauce Labs Bike Light

     **/

    // ADD SELECTED PRODUCTS TO CART
    public void addProductsToCart(int numberOfProducts) {

        List<WebElement> products = driver.findElements(
                By.className("inventory_item")
        );

        int addedProducts = 0;

        for (WebElement product : products) {

            String name = product.findElement(productNames).getText();

            // Selects products using names

            if (name.equals("Sauce Labs Onesie") ||
                    name.equals("Sauce Labs Bike Light")) {

                product.findElement(addToCartButtons).click();

                addedProducts++;

                if (addedProducts == numberOfProducts) {
                    break;
                }
            }
        }
    }

    // CART BADGE COUNT
    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }
}

package tests;
import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import java.util.List;

/** Test Scenario 4: Add two or more than two items to the Cart (Product Names and Product Count Verification) **/

public class CartTests extends BaseClass {
    public void loginAndAddProducts() {
        //Navigate to the inventory page by logging in with valid credentials
        LoginPage lpg = new LoginPage(driver);
        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        // Hover over items from the list and click the "Add to Cart" button (adding 2 items)
        InventoryPage ipg = new InventoryPage(driver);
        ipg.addProductsToCart(2);

        // Navigate to the shopping cart page to verify the added items
        driver.findElement(org.openqa.selenium.By.className("shopping_cart_link")).click();
    }

    /** Product Names Verification **/

    @Test(priority = 1)
    public void testCartProductNames() {

        // Navigate to inventory page, add two products to the cart, and open the cart
        loginAndAddProducts();

        // Verify that the selected products are added to the cart successfully by checking their names
        CartPage cpg = new CartPage(driver);
        List<String> actualProducts = cpg.getCartProductNames();

        // Confirm the expected product names
        Assert.assertEquals(actualProducts.size(), 2, "Cart should contain two products");
        Assert.assertTrue(actualProducts.contains("Sauce Labs Onesie"));
        Assert.assertTrue(actualProducts.contains("Sauce Labs Bike Light"));
        System.out.println("Products in cart: " + actualProducts);
    }

    /** Product Count Verification **/

    @Test(priority = 2)
    public void testCartProductCount() {
        loginAndAddProducts();

        // Verify that the products are added to the cart successfully by checking the cart item count
        CartPage cpg = new CartPage(driver);
        int actualCount = cpg.getCartProductCount();

        // Confirm the total product count inside the cart is 2
        Assert.assertEquals(actualCount, 2, "Cart product count should be two");
        System.out.println("Total products in cart: " + actualCount);
    }
}

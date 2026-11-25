package tests;
import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Test Scenario 3: Filter Products by Size and Price.
 * Objective: Verify that a valid user can filter items by Name (descending order) and Price from Low to High.
 */

public class InventoryTests extends BaseClass {
    @Test
    public void testProductSorting() {

        // Logging in with valid credentials
        LoginPage lpg = new LoginPage(driver);
        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        // Initialize InventoryPage after navigating to the inventory page
        InventoryPage ipg = new InventoryPage(driver);

        // Apply a filter for Name (descending order - Z to A)
        ipg.selectSortOption("za");

        // Verify that the item listings are updated according to the applied Name filter
        List<String> actualNames = ipg.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames, Collections.reverseOrder());

        // Confirm the displayed product names match the expected descending order
        Assert.assertEquals(actualNames, expectedNames, "Products are not sorted by Name (Z to A)");
        System.out.println("Products sorted by Name (Z to A): " + actualNames);

        // SORT PRODUCTS BY PRICE (LOW to HIGH)
        ipg.selectSortOption("lohi");
        List<Double> actualPrices = ipg.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        // Check item listings are updated according to the applied Price filter
        Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted by Price (Low to High)");
        System.out.println("Products sorted by Price (Low to High): " + actualPrices);
    }
}

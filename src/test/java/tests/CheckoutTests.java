package tests;
import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CheckoutPage;
import java.util.List;

public class CheckoutTests extends BaseClass {

    // Logs in with valid credentials and adds 2 products to the cart so the checkout flow can begin.
    public void loginAndAddProducts() {
        LoginPage lpg = new LoginPage(driver);
        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();
        InventoryPage ipg = new InventoryPage(driver);
        ipg.addProductsToCart(2);
    }

    //CHECKOUT PAGE DETAILS
    public void enterCheckoutInformation(CheckoutPage cpg) {
        cpg.openCart();
        cpg.clickCheckout();
        cpg.enterFirstName("Nimisha");
        cpg.enterLastName("Pradhan");
        cpg.enterPostalCode("44600");
        cpg.clickContinue();
    }

    // Perform Checkout (Billing & Shipping Information Step)
    @Test(priority = 1)
    public void testCheckoutInformation() {
        loginAndAddProducts();
        CheckoutPage cpg = new CheckoutPage(driver);
        enterCheckoutInformation(cpg);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("checkout-step-two.html"), "User should be redirected to checkout overview");
        System.out.println("Checkout information submitted successfully");
    }

    // Verify that the checkout overview displays the correct selected items and calculated total price.
    @Test(priority = 2)
    public void testCheckoutOverview() {
        loginAndAddProducts();
        CheckoutPage cpg = new CheckoutPage(driver);
        enterCheckoutInformation(cpg);
        List<String> actualProducts = cpg.getOverviewProductNames();
        Assert.assertEquals(actualProducts.size(), 2, "Checkout overview should contain two products");
        System.out.println("Checkout products: " + actualProducts);

        // VERIFY TOTAL PRICE
        String actualTotal = cpg.getTotalPrice();
        String expectedTotal = "Total: $19.42";
        Assert.assertEquals(actualTotal, expectedTotal, "Checkout total price does not match");
        System.out.println("Checkout total: " + actualTotal);
    }

    // Verify that a valid user can perform a complete checkout and receive an order confirmation message.
    @Test(priority = 3)
    public void testCompleteCheckout() {
        loginAndAddProducts();
        CheckoutPage cpg = new CheckoutPage(driver);
        enterCheckoutInformation(cpg);
        cpg.clickFinish();

        // VERIFY CONFIRMATION MESSAGE
        String actualMessage = cpg.getConfirmationMessage();
        String expectedMessage = "Thank you for your order!";
        Assert.assertEquals(actualMessage, expectedMessage, "Order confirmation message does not match");
        System.out.println("Order Confirmation: " + actualMessage);
    }
}

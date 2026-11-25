package tests;
import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseClass {

    /**
     * Test Scenario 1: Login with an Invalid User
     * Objective: Verify that you see an error message by logging in with the Locked user.
     */

    @Test(priority = 1)
    public void testInvalidLoginWithLockedUser() {
        // Navigating to the Login page (handled by BaseClass setup) and initialize LoginPage
        LoginPage lpg = new LoginPage(driver);

        // Fill the Login form with invalid (locked-out) user details and submit the Login form
        lpg.enterUserName("locked_out_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        // Verify that an appropriate error message is displayed indicating login failure
        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        String actualError = lpg.getErrorMessage();

        // Assertion: Verify the error message appears and matches the expected text
        Assert.assertEquals(actualError, expectedError);
    }


    /**
     * Test Scenario 2: Login with a Valid User
     * Objective: Verify that a standard user can log in successfully and is redirected to the inventory page.
     */

    @Test(priority = 2)
    public void testValidLogin() {
        // Initialize LoginPage
        LoginPage lpg = new LoginPage(driver);

        // Fill the login form with valid user details and submit the Login form
        lpg.enterUserName("standard_user");
        lpg.enterPassword("secret_sauce");
        lpg.clickLoginButton();

        //Verify successful login by checking the redirected URL
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}

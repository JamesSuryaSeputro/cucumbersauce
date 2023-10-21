package cucumbersauce;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinitions {

    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/james_surya/Documents/cucumbersauce/src/test/resources/chromedriver/chromedriver");
        driver = new ChromeDriver();
    }

    @Given("I am on the Sauce Demo login page")
    public void navigateToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter {string} as my username")
    public void enterUsername(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("I enter {string} as my password")
    public void enterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("I click on the Login button")
    public void clickLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be logged in to the Sauce Demo website")
    public void verifyLogin() {
        driver.findElement(By.className("title"));
    }

    @Then("I should see an error message")
    public void verifyErrorMessage() {
        String errorMessage = "Epic sadface: Username and password do not match any user in this service";
        String actualMessage = driver.findElement((By.cssSelector("[data-test='error']"))).getText();
        Assert.assertEquals(errorMessage, actualMessage);
    }

    public void login(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I am logged in to the Sauce Demo website")
    public void loggedInToWebsite() {
        login();
        Cookie sessionCookie = driver.manage().getCookieNamed("session-username");
        Assert.assertNotNull("Cookie not found", sessionCookie);
        Assert.assertEquals("standard_user", sessionCookie.getValue());
    }

    @When("I click on the Add to Cart button for the first product")
    public void clickAddToCartButton() {
        driver.findElement(By.cssSelector(".inventory_list .inventory_item:first-child .btn_inventory")).click();
    }

    @Then("I should successfully add the product to the cart")
    public void verifyProductAddedToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
        String buttonText = driver.findElement(By.className("btn_secondary")).getText();
        Assert.assertEquals("Remove", buttonText);
    }

    @When("I have a product in my cart")
    public void addProductToCart() {
        login();
        clickAddToCartButton();
    }

    @When("I click on the Remove button for the product")
    public void clickRemoveButton() {
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    @Then("I should successfully remove from my cart")
    public void verifyProductRemovedFromCart() {
        driver.findElement(By.className("removed_cart_item"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

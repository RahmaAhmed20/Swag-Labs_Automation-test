import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Checkout_Page {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("Webdriver.firefox.driver", "C:\\Users\\laptop\\Downloads\\geckodriver-v0.36.0-win-aarch64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void openSwagLab() {
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title does not match!");
    }


    @Test(priority = 2)
    public void loginPageP() {
        WebElement usernameBox = driver.findElement(By.id("user-name"));
        usernameBox.clear();
        usernameBox.sendKeys("standard_user");
        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.clear();
        passwordBox.sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        //   Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "URL did not navigate to products page");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 3)
    public void add() {
        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addToCartBtn.click();

        driver.findElement(By.className("shopping_cart_badge")).click();
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),"Sauce Labs Bolt T-Shirt");

        driver.findElement(By.id("checkout")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html");
    }


    @Test(priority = 4)
    public void testEmptyFields() {
        driver.findElement(By.id("continue")).click();
        WebElement errorMsg = driver.findElement(By.cssSelector(".error-message-container"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), "Error: First Name is required");
    }



    @Test(priority = 6)
    public void specialCharName() {
        driver.findElement(By.id("first-name")).sendKeys("@rahma!");
        driver.findElement(By.id("last-name")).sendKeys("#$ahmed");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector(".error-message-container"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), "Error: Name shouldn't have Special Characters");
    }


    @Test(priority = 7)
    public void NumName() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");

        driver.findElement(By.id("first-name")).sendKeys("rahma123");
        driver.findElement(By.id("last-name")).sendKeys("ahmed456");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector(".error-message-container"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), "Error: Name shouldn't have numbers");
    }

    @Test(priority = 8)
    public void postalCode() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");

        driver.findElement(By.id("first-name")).sendKeys("rahma");
        driver.findElement(By.id("last-name")).sendKeys("ahmed");
        driver.findElement(By.id("postal-code")).sendKeys("ABCXYZ");
        driver.findElement(By.id("continue")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector(".error-message-container"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), "Error: postal code must be numbers");
    }


    @Test(priority = 9)
    public void chekout() {

        driver.get("https://www.saucedemo.com/checkout-step-one.html");

        driver.findElement(By.id("first-name")).sendKeys("Rahma");
        driver.findElement(By.id("last-name")).sendKeys("Ahmed");
        driver.findElement(By.id("postal-code")).sendKeys("11646");
        driver.findElement(By.id("continue")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");

        driver.findElement(By.id("finish")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html");

        WebElement Msg = driver.findElement(By.className("complete-header"));
        Assert.assertTrue(Msg.isDisplayed(), "Order completion message is missing!");
        Assert.assertEquals(Msg.getText(), "Thank you for your order!", "Order message incorrect!");

    }


    @AfterTest
    public void teardown() {
           driver.quit();
    }

}
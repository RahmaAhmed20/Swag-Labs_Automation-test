import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Products_Page {
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
    public void addBtn() {
        //Test add button
        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addToCartBtn.click();
        WebElement removeBtn = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        Assert.assertEquals(removeBtn.getText(), "Remove");

        //Test add cart
        driver.findElement(By.className("shopping_cart_badge")).click();
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),"Sauce Labs Bolt T-Shirt");

        //Test continue shopping button
        driver.findElement(By.id("continue-shopping")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 4)
    public void removeBtn() {
        WebElement removeBtn = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        removeBtn.click();
        WebElement addToCartBtn = driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
        Assert.assertEquals(addToCartBtn.getText(), "Add to cart");
    }

    @Test(priority = 5)
    public void cartcount() {
        WebElement firstAdd = driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]"));
        firstAdd.click();

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "1");

        WebElement secondAdd = driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[2]"));
        secondAdd.click();

        cartBadge = driver.findElement(By.className("shopping_cart_badge")); // Re-locate the element
        Assert.assertEquals(cartBadge.getText(), "2");
    }

    @Test(priority = 6)
    public void tocart() {
        driver.findElement(By.className("shopping_cart_badge")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html");

        driver.findElement(By.id("continue-shopping")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @AfterTest
    public void teardown() {
     //   driver.quit();
    }
}
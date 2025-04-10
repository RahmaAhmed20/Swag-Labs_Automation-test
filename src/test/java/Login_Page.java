import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login_Page {

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
    public void testEmptyFields() {

        driver.findElement(By.id("login-button")).click();
        WebElement errorMsg = driver.findElement(By.cssSelector(".error-message-container"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

    //failed test
        @Test(priority = 3)
        public void loginPageN() {
            WebElement usernameBox = driver.findElement(By.id("user-name"));
            usernameBox.sendKeys("locked_out_user");
            WebElement passwordBox = driver.findElement(By.id("password"));
            passwordBox.sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        }


    //Positive test
    @Test(priority = 4)
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
}

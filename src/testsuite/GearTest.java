package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utility;

import java.time.Duration;

public class GearTest extends Utility {
    static String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        //* Mouse Hover on Gear Menu
        //* Click on Bags
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Gear']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();

        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Bags']")));
        jacketsOption.click();
        //* Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));
        //* Verify the text ‘Overnight Duffle’
        Assert.assertEquals("Overnight Duffle", getTextFromElement(By.xpath("//span[@class='base']")));
        //* Change Qty 3
        driver.findElement(By.xpath("//input[@id='qty']")).clear();
        sendTextToElement(By.xpath("//input[@id='qty']"), "3");
        //* Click on ‘Add to Cart’ Button.
        clickOnElement(By.xpath("//span[normalize-space()='Add to Cart']"));
        //* Verify the text ‘You added Overnight Duffle to your shopping cart.’
        Assert.assertEquals("You added Overnight Duffle to your shopping cart.", getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        //* Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        Thread.sleep(3000);
        //* Verify the product name ‘Overnight Duffle’
        Assert.assertEquals("Overnight Duffle", getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']")));

        //* Verify the Qty is ‘3’
        Assert.assertEquals("3", getTextFromElement(By.xpath("//input[@id='cart-117300-qty']")));
        //* Verify the product price ‘$135.00’
        Assert.assertEquals("$135.00", getTextFromElement(By.xpath("//td[@class='col subtotal']//span[@class='price']")));
        //* Change Qty to ‘5’
        driver.findElement(By.xpath("//input[@id='cart-117300-qty']")).clear();
        sendTextToElement(By.xpath("//input[@id='cart-117300-qty']"), "5");
        //* Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));
        //* Verify the product price ‘$225.00’
        Thread.sleep(3000);
        Assert.assertEquals("$225.00", getTextFromElement(By.xpath("//td[@class='col subtotal']//span[@class='price']")));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

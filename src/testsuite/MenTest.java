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

public class MenTest extends Utility {
    static String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        //* Mouse Hover on Men Menu
        //* Mouse Hover on Bottoms
        //* Click on Pants
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Men']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        WebElement topsMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='level1 nav-3-2 category-item last parent ui-menu-item']")));
        actions.moveToElement(topsMenu).perform();
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='level2 nav-3-2-1 category-item first ui-menu-item']")));
        jacketsOption.click();
        //* Mouse Hover on product name ‘Cronus Yoga Pant’ and click on size 32.
        WebElement menYogaPant = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Cronus Yoga Pant']")));
        Actions actionsOnMenPant = new Actions(driver);
        actionsOnMenPant.moveToElement(menYogaPant).perform();
        WebElement PantOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='swatch-opt-880']//div[@id='option-label-size-143-item-175']")));
        PantOption.click();
        //* Mouse Hover on product name ‘Cronus Yoga Pant’ and click on colour Black.
        WebElement PantColorOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='swatch-opt-880']//div[@id='option-label-color-93-item-49']")));
        PantColorOption.click();
        //* Mouse Hover on product name ‘Cronus Yoga Pant’ and click on ‘Add To Cart’ Button.
        WebElement AddToCartOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[1]//div[1]//div[1]//div[3]//div[1]//div[1]//form[1]//button[1]//span[1]")));
        AddToCartOption.click();
        //* Verify the text ‘You added Cronus Yoga Pant to your shopping cart.’
        Assert.assertEquals("You added Cronus Yoga Pant to your shopping cart.",getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        //* Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        //* Verify the text ‘Shopping Cart.’
        Assert.assertEquals("Shopping Cart", getTextFromElement(By.xpath("//span[@class='base']")));
        //* Verify the product name ‘Cronus Yoga Pant’
        Assert.assertEquals("Cronus Yoga Pant", getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Cronus Yoga Pant']")));
        //* Verify the product size ‘32’
        Assert.assertEquals("32", getTextFromElement(By.xpath("//dd[contains(text(),'32')]")));
        //* Verify the product colour ‘Black’
        Assert.assertEquals("Black", getTextFromElement(By.xpath("//dd[contains(text(),'Black')]")));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

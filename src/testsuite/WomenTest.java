package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WomenTest extends Utility {
    static String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() throws InterruptedException {
        //* Mouse Hover on Women Menu
        //* Mouse Hover on Tops
        //* Click on Jackets
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Women']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        WebElement topsMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Tops']")));
        actions.moveToElement(topsMenu).perform();
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Jackets']")));
        jacketsOption.click();
        
        //* Select Sort By filter “Product Name”
        WebElement dropDownSortBy = driver.findElement(By.xpath("//div[@class='page-wrapper']//div[2]//div[3]//select[1]"));
        Select selectSortBy = new Select(dropDownSortBy);
        selectSortBy.selectByVisibleText("Product Name");

        //* Verify the products name display in alphabetical order
        // Wait for the products to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-item-name")));

        // Fetch all product names
        List<WebElement> productElements = driver.findElements(By.cssSelector(".product-item-name a"));
        List<String> productNames = new ArrayList<>();
        for (WebElement productElement : productElements) {
            productNames.add(productElement.getText());
        }

        // Create a copy of the product names list and sort it
        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames);

        // Compare the original list with the sorted list
        if (productNames.equals(sortedProductNames)) {
            System.out.println("The products are displayed in alphabetical order.");
        } else {
            System.out.println("The products are NOT displayed in alphabetical order.");
        }

    }

    @Test
    public void verifyTheSortByPriceFilter() throws InterruptedException {
        //* Mouse Hover on Women Menu
        //* Mouse Hover on Tops
        //* Click on Jackets
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Women']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        Thread.sleep(3000);
        WebElement topsMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Tops']")));
        actions.moveToElement(topsMenu).perform();
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Jackets']")));
        jacketsOption.click();

        //* Select Sort By filter “Price”
        WebElement dropDownSortBy = driver.findElement(By.xpath("//div[@class='page-wrapper']//div[2]//div[3]//select[1]"));
        Select selectSortBy = new Select(dropDownSortBy);
        selectSortBy.selectByVisibleText("Price");
        //* Verify the products price display in Low to High
        // Wait for the products to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-item")));

        // Fetch all product prices
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".price-wrapper .price"));
        List<Double> productPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replaceAll("[^0-9.]", ""); // Remove currency symbols and commas
            productPrices.add(Double.parseDouble(priceText));
        }

        // Create a copy of the product prices list and sort it
        List<Double> sortedProductPrices = new ArrayList<>(productPrices);
        Collections.sort(sortedProductPrices);

        // Compare the original list with the sorted list
        if (productPrices.equals(sortedProductPrices)) {
            System.out.println("The products are displayed in ascending order of price (Low to High).");
        } else {
            System.out.println("The products are NOT displayed in ascending order of price (Low to High).");
        }

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

package testautomation.beymen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testautomation.beymen.model.ProductDetails;
import testautomation.beymen.utils.FileWriterUtil;
import testautomation.beymen.utils.LoggerUtil;
import testautomation.beymen.utils.WaitHelper;

import java.util.List;

public class ProductPage {
    WebDriver driver;

    private final By productList = By.id("productList");
    private final By productTitle = By.cssSelector(".m-productCard__title");
    private final By productPrice = By.cssSelector(".m-productCard__newPrice");
    private final By cartButton = By.cssSelector(".m-productCard__stock");
    private final By variationsList = By.cssSelector(".m-variation__item");
    private final By addBasketCartButton = By.id("addBasket");
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductPriceFromListAndSaveFile() {
        List<WebElement> products = driver.findElements(productList);

        if (products.isEmpty()) {
            LoggerUtil.logger.error("Product list empty");
            throw new RuntimeException("Product list empty");
        }

        WebElement firstProduct = products.getFirst();
        String name = firstProduct.findElement(productTitle).getText();
        String price = firstProduct.findElement(productPrice).getText();
        ProductDetails product = new ProductDetails(name, price);

        FileWriterUtil.writeProductDetailsToFile(product, "outputs/product_details.txt");
        LoggerUtil.logger.info("Product information written to file");


        return price;
    }

    public void handleClickAddCart() {
        WebElement cartButtonElement = WaitHelper.waitForElementToBeVisible(driver, cartButton);
        cartButtonElement.click();
    }

    public void selectVariant() {
        List<WebElement> variations = driver.findElements(variationsList);
        if (variations.isEmpty()) {
            LoggerUtil.logger.error("Variants empty");
            throw new RuntimeException("Variants empty");
        }

        WebElement randomVariant = variations.getFirst();
        randomVariant.click();
        LoggerUtil.logger.info("Variant selected.");
    }

    public void addToCart() {
        driver.findElement(addBasketCartButton).click();
        LoggerUtil.logger.info("Product added to cart");
    }
}

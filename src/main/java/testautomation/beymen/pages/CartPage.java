package testautomation.beymen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import testautomation.beymen.utils.LoggerUtil;
import testautomation.beymen.utils.WaitHelper;

public class CartPage {
    WebDriver driver;

    private final By cartIcon =  By.xpath("//a[@title='Sepetim']");
    private final By productPriceInCart = By.cssSelector(".priceBox__salePrice");
    private final By quantitySelect = By.id("quantitySelect0-key-0");
    private final By removeButton = By.id("removeCartItemBtn0-key-0");
    private final By emptyCartMessage  = By.cssSelector("#emtyCart .m-empty__messageTitle");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
        LoggerUtil.logger.info("Clicked cart icon");
    }

    public String getProductPriceInCart() {
        WebElement priceElement = WaitHelper.waitForElementToBeVisible(driver, productPriceInCart);
        String price = priceElement.getText();
        LoggerUtil.logger.info("Amount of product: " + price);
        return price;
    }

    public void increaseProductQuantity() {
        WebElement quantityDropdown = WaitHelper.waitForElementToBeVisible(driver, quantitySelect);
        Select select = new Select(quantityDropdown);
        select.selectByValue("2");
        LoggerUtil.logger.info("Product count changed");
    }

    public String getProductQuantity() {
        WebElement quantityDropdown = WaitHelper.waitForElementToBeVisible(driver, quantitySelect);
        Select select = new Select(quantityDropdown);

        return select.getFirstSelectedOption().getText();

    }

    public void removeItemFromCart() {
        driver.findElement(removeButton).click();
        LoggerUtil.logger.info("Product removed from cart");
    }

    public boolean isCartEmpty() {
        try {
            WebElement emptyMessage =  WaitHelper.waitForElementToBeVisible(driver, emptyCartMessage, 15);
            return emptyMessage.isDisplayed() &&
                    emptyMessage.getText().contains("SEPETINIZDE ÜRÜN BULUNMAMAKTADIR");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testautomation.beymen.pages.CartPage;
import testautomation.beymen.pages.HomePage;
import testautomation.beymen.pages.ProductPage;
import testautomation.beymen.utils.*;


import static org.junit.Assert.*;

public class SearchTest {

    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        LoggerUtil.logger.info("Test beginning...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.beymen.com/tr");
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.selectWomenGender();
    }

    @Test
    public void searchAndAddToCartTest() {
        assertTrue("Main page not displayed", homePage.isHomePageDisplayed());
        LoggerUtil.logger.info("Main page displayed successfully");

        homePage.clickSearchBox();
        String shortKeyword = ExcelReader.readCell("data/search_keywords.xlsx", 0, 0);
        homePage.typeSearchKeyword(shortKeyword);
        homePage.clearSearchBox();
        LoggerUtil.logger.info("'Şort' word entered and removed.");
        WaitHelper.waitForSeconds(1);

        String shirtKeyword = ExcelReader.readCell("data/search_keywords.xlsx", 0, 1);
        homePage.typeSearchKeyword(shirtKeyword);
        homePage.pressEnter();
        LoggerUtil.logger.info("'Gömlek' word entered and searched.");
        WaitHelper.waitForSeconds(2);

        ProductPage productPage = new ProductPage(driver);
        String price = productPage.getProductPriceFromListAndSaveFile();
        productPage.handleClickAddCart();
        WaitHelper.waitForSeconds(2);
        productPage.selectVariant();
        productPage.addToCart();
        WaitHelper.waitForSeconds(3);

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        String normalizedPrice = PriceHelper.normalizePrice(price);
        String normalizedProductPriceInCart = PriceHelper.normalizePrice(cartPage.getProductPriceInCart());

        assertEquals(normalizedPrice, normalizedProductPriceInCart);

        cartPage.increaseProductQuantity();
        assertTrue(cartPage.getProductQuantity().contains("2"));

        cartPage.removeItemFromCart();
        WaitHelper.waitForSeconds(3);
        assertTrue(cartPage.isCartEmpty());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            LoggerUtil.logger.info("Browser closed.");
        }
    }
}

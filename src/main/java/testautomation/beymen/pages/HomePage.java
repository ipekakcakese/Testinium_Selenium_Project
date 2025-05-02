package testautomation.beymen.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testautomation.beymen.utils.LoggerUtil;
import testautomation.beymen.utils.WaitHelper;

import java.util.Objects;

public class HomePage {
    WebDriver driver;

    private final By searchBox = By.className("o-header__search--input");
    private final By searchSuggestionBox = By.id("o-searchSuggestion__input");
    private final By clearSearchButton = By.className("o-header__search--close");
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By selectWomenButton = By.id("genderWomanButton");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageDisplayed() {
        return Objects.requireNonNull(driver.getTitle()).contains("Beymen.com");
    }

    public void clickSearchBox() {
        WebElement box = driver.findElement(searchBox);
        box.click();
        LoggerUtil.logger.info("Focused search box" );
    }

    public void typeSearchKeyword(String keyword) {
        WebElement box = driver.findElement(searchSuggestionBox);
        box.sendKeys(keyword);
    }

    public void clearSearchBox() {
        WebElement clearSearchIcon = driver.findElement(clearSearchButton);
        clearSearchIcon.click();
        LoggerUtil.logger.info("Search box cleaned");
    }

    public void pressEnter() {
        WebElement box = driver.findElement(searchSuggestionBox);
        box.sendKeys(Keys.ENTER);
        LoggerUtil.logger.info("Pressed enter");
    }

    public void acceptCookies() {
        WaitHelper.waitForElementToBeVisible(driver,acceptCookiesButton,3).click();
        LoggerUtil.logger.info("Accepted cookies");
    }

    public void selectWomenGender() {
        driver.findElement(selectWomenButton).click();
        LoggerUtil.logger.info("Selected gender");
    }

}

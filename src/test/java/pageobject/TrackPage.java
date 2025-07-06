package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrackPage {

    private WebDriver driver;

    public TrackPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By orderNumberInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    private final By searchButton = By.className("Header_Button__28dPO");
    private final By notFoundMessage = By.className("Track_NotFound__6oaoY");

    public void enterOrderNumber(String orderNumber) {
        driver.findElement(orderNumberInput).sendKeys(orderNumber);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public boolean isNotFoundMessageDisplayed() {
        return driver.findElement(notFoundMessage).isDisplayed();
    }

}

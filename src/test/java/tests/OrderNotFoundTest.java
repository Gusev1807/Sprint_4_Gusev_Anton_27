package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;
import pageobject.TrackPage;

import java.time.Duration;

public class OrderNotFoundTest {

    private WebDriver driver;
    private MainPage mainPage;
    private TrackPage trackPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        trackPage = new TrackPage(driver);
        driver.get(MainPage.PAGE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldShowNotFoundMessageForInvalidOrderNumberTest() {

        mainPage.clickStatusButton();

        // Ждём, что поле ввода станет кликабельным
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Введите номер заказа']")));

        trackPage.enterOrderNumber("000000");
        trackPage.clickSearchButton();

        // Проверка сообщения
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Track_NotFound__6oaoY")));

        Assert.assertTrue("Сообщение об ошибке не отображается", trackPage.isNotFoundMessageDisplayed());
    }
}
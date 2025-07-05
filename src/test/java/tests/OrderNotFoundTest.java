package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;
import pageobject.TrackPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OrderNotFoundTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldShowNotFoundMessageForInvalidOrderNumber() {

        // Кликаем "Статус заказа"
        driver.findElement(MainPage.statusBottom).click();

        By inputLocator = By.xpath("//input[@placeholder='Введите номер заказа']");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputLocator));

        // Вводим несуществующий номер
        driver.findElement(TrackPage.orderNumberInput).sendKeys("000000");

        // Кликаем на кнопку поиска
        driver.findElement(TrackPage.searchButton).click();

        // Проверяем сообщение об ошибке
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(TrackPage.notFoundMessage));
        assertTrue("Сообщение об ошибке не отображается", message.isDisplayed());
    }

}
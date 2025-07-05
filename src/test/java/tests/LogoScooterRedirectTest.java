package tests;

import pageobject.MainPage;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LogoScooterRedirectTest {

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

    // Доп.Тест 1
    @Test
    public void clickScooterLogoReturnsToMainPage() {

        // Кликаем на логотип Самоката
        driver.findElement(MainPage.scooterLogo).click();

        // Проверка: URL остался https://qa-scooter.praktikum-services.ru/
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не перешёл на главную", currentUrl.equals("https://qa-scooter.praktikum-services.ru/"));
    }

    // Доп.Тест 2
    @Test
    public void yandexLogoOpensYandexPage() {
        // Кликаем по логотипу Яндекса
        driver.findElement(MainPage.yandexLogo).click();

        // Переключаемся на вторую вкладку (их станет две)
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Проверяем URL новой вкладки
        String url = driver.getCurrentUrl();
        assertTrue("Не открылся сайт Яндекса", url.contains("yandex"));
    }


}

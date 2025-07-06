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
        driver.get(MainPage.PAGE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Доп.Тест 1
    @Test
    public void clickScooterLogoReturnsToMainPageTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickScooterLogo();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не перешёл на главную", currentUrl.equals(MainPage.PAGE_URL));
    }

    // Доп.Тест 2
    @Test
    public void yandexLogoOpensYandexPageTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickYandexLogo();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        String url = driver.getCurrentUrl();
        assertTrue("Не открылся сайт Яндекса", url.contains("yandex"));
    }


}

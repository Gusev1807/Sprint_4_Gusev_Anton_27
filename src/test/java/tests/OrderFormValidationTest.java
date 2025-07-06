package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

import static org.junit.Assert.assertTrue;

public class OrderFormValidationTest {

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

    @Test
    public void showErrorClassWhenFieldsAreEmptyTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Переход на форму заказа
        mainPage.clickTopOrderButton();

        // Нажать "Далее" без заполнения полей
        orderPage.clickNextButton();

        // Проверяем ошибки для каждого поля
        assertTrue("Поле 'Имя' не подсвечено как ошибка",
                orderPage.getFirstNameInputClass().contains("Input_Error__"));
        assertTrue("Поле 'Фамилия' не подсвечено как ошибка",
                orderPage.getLastNameInputClass().contains("Input_Error__"));
        assertTrue("Поле 'Адрес' не подсвечено как ошибка",
                orderPage.getAddressInputClass().contains("Input_Error__"));
        assertTrue("Поле 'Телефон' не подсвечено как ошибка",
                orderPage.getPhoneInputClass().contains("Input_Error__"));
    }
}

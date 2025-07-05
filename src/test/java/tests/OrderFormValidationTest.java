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
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void showErrorClassWhenFieldsAreEmpty() {

        // Переход на форму заказа
        driver.findElement(MainPage.orderTopButton).click();

        // Нажать "Далее" без заполнения полей
        driver.findElement(OrderPage.nextButton).click();

        // Проверяем ошибки для каждого поля
        assertTrue("Поле 'Имя' не подсвечено как ошибка",
                driver.findElement(OrderPage.firstNameInput).getAttribute("class").contains("Input_Error__"));
        assertTrue("Поле 'Фамилия' не подсвечено как ошибка",
                driver.findElement(OrderPage.lastNameInput).getAttribute("class").contains("Input_Error__"));
        assertTrue("Поле 'Адрес' не подсвечено как ошибка",
                driver.findElement(OrderPage.addressInput).getAttribute("class").contains("Input_Error__"));
        assertTrue("Поле 'Телефон' не подсвечено как ошибка",
                driver.findElement(OrderPage.phoneInput).getAttribute("class").contains("Input_Error__"));

    }
}

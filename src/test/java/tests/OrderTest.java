package tests;

import pageobject.MainPage;
import pageobject.OrderPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    // === Параметры ===
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;
    private final By orderButtonLocator;

    public OrderTest(String firstName, String lastName, String address, String metro, String phone, String date, String comment, By orderButtonLocator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.orderButtonLocator = orderButtonLocator;
    }

    @Parameterized.Parameters(name = "Вариант заполнения {0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Антон", "Гусев", "ул. Ленина, 1", "Черкизовская", "89991112233", "10.07.2025", "Позвонить за час", MainPage.orderTopButton},
                {"Мария", "Смирнова", "ул. Пушкина, 5", "Курская", "89887776655", "11.07.2025", "", MainPage.orderBottomButton},
                {"Яна", "Саитова", "ул. Самарская, 270", "Лубянка", "89272308876", "27.08.2025", "Напомнить за сутки", MainPage.orderTopButton}
        });
    }

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
    public void testOrderScooter() {

        // Кликаем по куки
        driver.findElement(MainPage.cookieButton).click();


        // Кликаем на нужную кнопку Заказать
        driver.findElement(orderButtonLocator).click();

        // Заполняем первую форму
        driver.findElement(OrderPage.firstNameInput).sendKeys(firstName);
        driver.findElement(OrderPage.lastNameInput).sendKeys(lastName);
        driver.findElement(OrderPage.addressInput).sendKeys(address);

        driver.findElement(OrderPage.metroInput).click();
        driver.findElement(By.xpath("//div[@class='select-search__select']//button/div[text()='" + metro + "']")).click();

        driver.findElement(OrderPage.phoneInput).sendKeys(phone);
        driver.findElement(OrderPage.nextButton).click();

        // Заполняем вторую форму
        WebElement dateInput = driver.findElement(OrderPage.dateInput);
        dateInput.sendKeys(date);
        // Нажимаем ESC, чтобы закрыть календарь
        dateInput.sendKeys(Keys.ESCAPE);

        driver.findElement(OrderPage.rentalPeriod).click();
        driver.findElement(OrderPage.rentalPeriodOptionOneDay).click();
        driver.findElement(OrderPage.colorBlack).click();

        if (!comment.isEmpty()) {
            driver.findElement(OrderPage.commentInput).sendKeys(comment);
        }

        driver.findElement(OrderPage.orderButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(OrderPage.confirmButton));
        confirmBtn.click();

        // Проверка: появилось окно об успешном заказе
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(OrderPage.successModal));

        String modalText = driver.findElement(OrderPage.successModal).getText();
        assertTrue("Окно с подтверждением заказа не появилось", modalText.contains("Заказ оформлен"));
    }
}

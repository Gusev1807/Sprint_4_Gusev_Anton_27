package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;
    private final boolean useTopOrderButton;

    public OrderTest(String firstName, String lastName, String address, String metro, String phone,
                     String date, String comment, boolean useTopOrderButton) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.useTopOrderButton = useTopOrderButton;
    }

    @Parameterized.Parameters(name = "Вариант заполнения {0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Антон", "Гусев", "ул. Ленина, 1", "Черкизовская", "89991112233", "10.07.2025", "Позвонить за час", true},
                {"Мария", "Смирнова", "ул. Пушкина, 5", "Курская", "89887776655", "11.07.2025", "", false},
                {"Яна", "Саитова", "ул. Самарская, 270", "Лубянка", "89272308876", "27.08.2025", "Напомнить за сутки", true}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(MainPage.PAGE_URL);

        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testOrderScooter() {
        // Принимаем куки
        mainPage.acceptCookies();

        // Кликаем на нужную кнопку "Заказать"
        if (useTopOrderButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        // Заполняем первую форму
        orderPage.fillFirstForm(firstName, lastName, address, metro, phone);

        // Заполняем вторую форму
        orderPage.fillSecondForm(date, comment);

        // Подтверждаем заказ
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton();

        // Проверяем, что появилось окно подтверждения
        String modalText = orderPage.getSuccessModalText();
        assertTrue("Окно с подтверждением заказа не появилось", modalText.contains("Заказ оформлен"));

    }
}



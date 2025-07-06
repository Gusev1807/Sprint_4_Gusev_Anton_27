package tests;

import pageobject.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccordionTest {

    private static WebDriver driver;

    private final int questionIndex;

    // Массив с ожидаемыми ответами
    private static final String[] EXPECTED_ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    public AccordionTest(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters(name = "Тест вопроса №{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}
        });
    }

    @BeforeClass
    public static void setUpClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(MainPage.PAGE_URL);
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера один раз после всех тестов
        }
    }

    @Test
    public void faqQuestionShouldExpandOnClickTest() {

    MainPage mainPage = new MainPage(driver);
    mainPage.clickQuestion(questionIndex);

        assertTrue("Ответ не отображается для вопроса №" + questionIndex,
                mainPage.isAnswerVisible(questionIndex));

        String actualAnswer = mainPage.getAnswerText(questionIndex);
        String expectedAnswer = EXPECTED_ANSWERS[questionIndex];

        assertEquals("Ответ для вопроса №" + questionIndex + " некорректен", expectedAnswer, actualAnswer);

    }
}

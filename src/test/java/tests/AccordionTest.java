package tests;

import pageobject.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class AccordionTest {

    private static WebDriver driver;

    private final int questionIndex;

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
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера один раз после всех тестов
        }
    }

    @Test
    public void faqQuestionShouldExpandOnClick() {

        By questionLocator = new MainPage().faqQuestionByIndex(questionIndex);
        By answerLocator = new MainPage().faqAnswerByIndex(questionIndex);

        WebElement question = driver.findElement(questionLocator);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));

        WebElement answer = driver.findElement(answerLocator);
        assertTrue("Ответ не отображается для вопроса №" + questionIndex, answer.isDisplayed());
    }
}

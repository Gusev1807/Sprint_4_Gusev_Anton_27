package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Куки
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    // Верхняя кнопка "Заказать"
    private final By orderTopButton = By.className("Button_Button__ra12g");

    public void clickTopOrderButton() {
        driver.findElement(orderTopButton).click();
    }

    // Нижняя кнопка "Заказать"
    private final By orderBottomButton = By.xpath(".//button[text()='Заказать' and contains(@class, 'Button_Middle')]");

    public void clickBottomOrderButton() {
        WebElement element = driver.findElement(orderBottomButton);
        new Actions(driver).moveToElement(element).perform(); // Скроллим до элемента
        element.click();
    }

    // URL Страницы
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Локаторы вопросов и ответов (приватные)
    private By faqQuestionByIndex(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By faqAnswerByIndex(int index) {
        return By.id("accordion__panel-" + index);
    }

    // Клик по вопросу по индексу
    public void clickQuestion(int index) {
        WebElement question = driver.findElement(By.id("accordion__heading-" + index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", question);
        question.click();
    }

    // Проверка, виден ли ответ (здесь с ожиданием)
    public boolean isAnswerVisible(int index) {
        String panelId = "accordion__panel-" + index;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement answerPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(panelId)));
        return answerPanel.isDisplayed();
    }

    // Получить текст ответа
    public String getAnswerText(int index) {
        return driver.findElement(faqAnswerByIndex(index)).getText();
    }

    // Логотипы
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public boolean isScooterLogoDisplayed() {
        return driver.findElement(scooterLogo).isDisplayed();
    }

    public boolean isYandexLogoDisplayed() {
        return driver.findElement(yandexLogo).isDisplayed();
    }

    private final By statusButton = By.className("Header_Link__1TAG7");

    public void clickStatusButton() {
        driver.findElement(statusButton).click();
    }
}


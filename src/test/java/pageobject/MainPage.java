package pageobject;

import org.openqa.selenium.By;

public class MainPage {

    // Куки
    public static final By cookieButton = By.className("App_CookieButton__3cvqF");

    // Кнопки "Заказать"

    // Верхняя кнопка "Заказать"
    public static final By orderTopButton = By.className("Button_Button__ra12g");

    // Нижняя кнопка "Заказать"
    public  static final By orderBottomButton = By.xpath(".//button [text()='Заказать' and contains(@class, 'Button_Middle')]");

    // Раздел "Вопросы о важном"

    // "Вопрос"
    public By faqQuestionByIndex(int index) {
        return By.id("accordion__heading-" + index);
    }

    // "Ответ"
    public By faqAnswerByIndex(int index) {
        return By.id("accordion__panel-" + index);
    }

    // Логотипы

    // Логотип "Самокат"
    public static final By scooterLogo = By.className("Header_LogoScooter__3lsAR");

    // Логотип "Яндекс"
    public static final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    // Кнопка "Статус заказа"
    public static final By statusBottom = By.className("Header_Link__1TAG7");

}

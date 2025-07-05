package pageobject;

import org.openqa.selenium.By;

public class TrackPage {

    // Поле ввода номера заказа
    public static final By orderNumberInput = By.xpath("//input[@placeholder='Введите номер заказа']");

    // Кнопка поиска (лупа)
    public static final By searchButton = By.className("Header_Button__28dPO");

    // Сообщение "Заказ не найден"
    public static final By notFoundMessage = By.className("Track_NotFound__6oaoY");
}

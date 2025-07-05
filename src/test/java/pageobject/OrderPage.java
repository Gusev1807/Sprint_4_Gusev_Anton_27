package pageobject;

import org.openqa.selenium.By;

public class OrderPage {
    // Поля первой формы

    // "Имя"
    public static final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    // "Фамилия"
    public static final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    // "Адрес"
    public static final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // "Станция метро"
    public static final By metroInput = By.className("select-search__input");
    // "Номер телефона"
    public static final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    public static final By nextButton = By.xpath("//button[text()='Далее']");

    // Поля второй формы

    // "Когда привести самокат"
    public static final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // "Выбор срока"
    public static final By rentalPeriod = By.className("Dropdown-placeholder");
    // "Кликаем на сутки"
    public static final By rentalPeriodOptionOneDay = By.xpath("//div[text()='сутки']");
    // "Выбираем цвет черный жемчуг"
    public static final By colorBlack = By.id("black");
    // "Комментарий для курьера"
    public static final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    public static final By orderButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2][contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM')]");
    // Кнопка "Да"
    public static final By confirmButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and normalize-space(text())='Да']");

    // Подтверждение заказа
    public static final By successModal = By.className("Order_ModalHeader__3FDaJ");
}

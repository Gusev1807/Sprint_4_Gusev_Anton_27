package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы первой формы

    // Имя
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    // Фамилия
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    // Адрес
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Станция метро
    private final By metroInput = By.className("select-search__input");
    // Номер телефона
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы второй формы

    // Дата
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Срок аренды
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    // Выбор срока аренды
    private final By rentalPeriodOptionOneDay = By.xpath("//div[text()='сутки']");
    // Цвет самоката
    private final By colorBlack = By.id("black");
    // Комментарий
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    // Кнопка "Да"
    private final By confirmButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and text()='Да']");

    private static final By successModal = By.className("Order_ModalHeader__3FDaJ");

    // Заполнение первой формы
    public void fillFirstForm(String firstName, String lastName, String address, String metro, String phone) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroInput).click();
        driver.findElement(By.xpath("//div[@class='select-search__select']//button/div[text()='" + metro + "']")).click();
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Заполнение второй формы
    public void fillSecondForm(String date, String comment) {
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(dateInput).sendKeys(Keys.ESCAPE); // Закрываем календарь
        driver.findElement(rentalPeriod).click();
        driver.findElement(rentalPeriodOptionOneDay).click();
        driver.findElement(colorBlack).click();

        if (!comment.isEmpty()) {
            driver.findElement(commentInput).sendKeys(comment);
        }
    }

    // Подтверждение заказа
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickConfirmButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmBtn.click();
    }

    public boolean isSuccessModalDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successModal));
        return driver.findElement(successModal).isDisplayed();
    }

    public String getSuccessModalText() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(successModal));
        return driver.findElement(successModal).getText();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Получить класс поля "Имя"
    public String getFirstNameInputClass() {
        return driver.findElement(firstNameInput).getAttribute("class");
    }


    public String getLastNameInputClass() {
        return driver.findElement(lastNameInput).getAttribute("class");
    }

    public String getAddressInputClass() {
        return driver.findElement(addressInput).getAttribute("class");
    }

    public String getPhoneInputClass() {
        return driver.findElement(phoneInput).getAttribute("class");
    }
}


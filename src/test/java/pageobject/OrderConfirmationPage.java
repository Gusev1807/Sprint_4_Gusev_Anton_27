package pageobject;

import org.openqa.selenium.By;


public class OrderConfirmationPage {

    // Заголовок модального окна "Заказ оформлен"
    public static final By orderConfirmedModal = By.className("Order_ModalHeader__3FDaJ");

    // Кнопка "Посмотреть статус"
    public static final By viewStatusButton = By.xpath("//button[text()='Посмотреть статус']");
}

package ru.yandex.praktikum.pageObject;

// Импорт необходимых библиотек
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Класс для работы с всплывающим окном
public class PopUpWindow {
    // Определение переменных
    WebDriver driver;
    private final By popUpHeaderAfterCreateOrder = By.xpath(".//div[text()='Заказ оформлен']");
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    // Конструктор класса
    public PopUpWindow(WebDriver driver) {
        this.driver = driver;
    }

    // Нажатие на кнопку "Да" во всплывающем окне
    public void clickButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

    // Получение текста заголовка после оформления заказа
    public String getHeaderAfterCreateOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(popUpHeaderAfterCreateOrder).getText() != null
                && !driver.findElement(popUpHeaderAfterCreateOrder).getText().isEmpty()
        ));
        return driver.findElement(popUpHeaderAfterCreateOrder).getText();
    }
}

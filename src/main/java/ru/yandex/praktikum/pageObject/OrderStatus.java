package ru.yandex.praktikum.pageObject;

// Импорт необходимых библиотек
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Класс для работы со страницей статуса заказа
public class OrderStatus {
    // Определение переменных
    WebDriver driver;
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']");
    private final By scooterButton = By.xpath(".//*[@alt='Scooter']");
    private final By notFound = By.xpath(".//*[@alt='Not found']");

    // Конструктор класса
    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    // Ожидание загрузки страницы статуса заказа
    public OrderStatus waitLoadOrderStatusPade() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(notFound));
        return this;
    }

    // Нажатие на кнопку "Яндекс"
    public void clickYandex() {
        driver.findElement(yandexButton).click();
    }

    // Нажатие на кнопку "Самокат"
    public void clickScooter() {
        driver.findElement(scooterButton).click();
    }
}

package ru.yandex.praktikum.pageObject;

// Импорт необходимых библиотек
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Класс для тестирования статуса заказа
public class OrderStatusTest {
    // Определение переменных
    WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String numberOrder = "112233";

    // Установка и запуск тестового окружения перед тестом
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(site);
    }

    // Завершение теста и закрытие окружения
    @After
    public void teardown() {
        driver.quit();
    }

    // Тест для проверки статуса заказа без указания номера заказа
    @Test
    public void orderStatusWithoutNumber() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickOrderState()
                .inputOrderNumber(numberOrder)
                .clickGo();
        new OrderStatus(driver)
                .waitLoadOrderStatusPade();
    }
}

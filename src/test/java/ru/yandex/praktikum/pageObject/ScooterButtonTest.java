package ru.yandex.praktikum.pageObject;

// Импорт необходимых библиотек
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

// Класс для тестирования кнопок "Скутер" на различных страницах
public class ScooterButtonTest {
    // Определение переменных
    WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";

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

    // Тест для проверки нажатия на кнопку "Самокат"
    @Test
    public void clickScooterFromAboutRenterPage() {
        HomePage homePage = new HomePage(driver);
        AboutRenter aboutRenter = new AboutRenter(driver);

        homePage.waitForLoadHomePage()
                .clickUpOrderButton();

        aboutRenter.waitForLoadOrderPage()
                .clickScooter();

        new WebDriverWait(driver, Duration.ofSeconds(5));

        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    // Тест для проверки нажатия на кнопку "Самокат"
    @Test
    public void clickScooterFromAboutScooterPage() {
        HomePage homePage = new HomePage(driver);
        AboutRenter aboutRenter = new AboutRenter(driver);
        AboutScooter aboutScooter = new AboutScooter(driver);

        homePage.waitForLoadHomePage()
                .clickUpOrderButton();

        aboutRenter.waitForLoadOrderPage()
                .inputName("Денис")
                .inputSurname("Фамилия")
                .inputAddress("Адрес 1")
                .changeStateMetro(55)
                .inputTelephone("+79998887766")
                .clickNextButton();

        aboutScooter.waitAboutRentHeader()
                .clickScooter();

        new WebDriverWait(driver, Duration.ofSeconds(5));

        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    // Тест для проверки нажатия на кнопку "Самокат"
    @Test
    public void clickScooterFromOrderStatusPage() {
        HomePage homePage = new HomePage(driver);
        OrderStatus orderStatus = new OrderStatus(driver);

        homePage.waitForLoadHomePage()
                .clickOrderState()
                .inputOrderNumber("112233")
                .clickGo();

        orderStatus.waitLoadOrderStatusPade()
                .clickScooter();

        new WebDriverWait(driver, Duration.ofSeconds(5));

        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

}

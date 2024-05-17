package ru.yandex.praktikum.pageObject;

// Импорт необходимых библиотек
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.pageObject.constants.CreateOrderButton.DOWN_BUTTON;
import static ru.yandex.praktikum.pageObject.constants.CreateOrderButton.UP_BUTTON;
import static ru.yandex.praktikum.pageObject.constants.RentDurationConstants.*;
import static ru.yandex.praktikum.pageObject.constants.ScooterColours.*;

// Определение класса для тестирования создания заказа
@RunWith(Parameterized.class)
public class OrderCreateTest {
    // Определение переменных
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    // Конструктор класса
    public OrderCreateTest(Enum button, String name, String surname, String address, int stateMetroNumber, String telephoneNumber,
                           String date, String duration, Enum colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    // Параметризованные параметры для теста
    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "Денис", "Фамилия", "Адрес 1", 1, "79998887766", "15.05.2024", SIX_DAYS, GREY, "Комментарий"},
                {UP_BUTTON, "Лиза", "Фамилия", "Адрес 2", 2, "79998887766", "15.05.2024", FIVE_DAYS, BLACK, "Нет комментария"},
                {UP_BUTTON, "Ричи", "Фамилия", "Адрес 3", 3, "79998887766", "15.05.2024", ONE_DAY, BLACK, "Есть комментарий"},
                {DOWN_BUTTON, "Денис", "Фамилия", "Адрес 1", 1, "79998887766", "15.05.2024", SIX_DAYS, GREY, "Комментарий"},
                {DOWN_BUTTON, "Лиза", "Фамилия", "Адрес 2", 2, "79998887766", "15.05.2024", FIVE_DAYS, BLACK, "Нет комментария"},
                {DOWN_BUTTON, "Ричи", "Фамилия", "Адрес 3", 3, "79998887766", "15.05.2024", ONE_DAY, BLACK, "Есть комментарий"},
        };
    }

    // Установка и запуск тестового окружения перед тестом
    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(site);
    }

    // Завершение теста и закрытие окружения
    @After
    public void teardown() {
        driver.quit();
    }

    // Переименованный тест создания заказа с параметрами
    @Test
    public void testCreateOrderParameterized() {
        // Переход на главную страницу и нажатие на кнопку "Создать заказ"
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCreateOrderButton(button);

        // Заполнение формы информации о заказчике
        new AboutRenter(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        // Заполнение информации о самокате
        new AboutScooter(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        // Подтверждение заказа в всплывающем окне
        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        // Проверка, что заголовок после создания заказа содержит ожидаемый текст
        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}

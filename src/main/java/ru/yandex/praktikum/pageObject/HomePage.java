package ru.yandex.praktikum.pageObject;

// Импорт необходимых библиотек
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.pageObject.constants.CreateOrderButton.DOWN_BUTTON;
import static ru.yandex.praktikum.pageObject.constants.CreateOrderButton.UP_BUTTON;

// Класс для работы с главной страницей
public class HomePage {
    // Определение переменных
    WebDriver driver;
    private final By homeHeader = By.className("Home_Header__iJKdX");
    private final By upOrderButton = By.className("Button_Button__ra12g");
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By questionsHeader = By.className("Home_FourPart__1uthg");
    private final By orderState = By.xpath(".//button[text()='Статус заказа']");
    private final By numberOrder = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By buttonGo = By.xpath(".//button[text()='Go!']");
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']");

    // Конструктор класса
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }

    // Метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    // Метод прокрутки к блоку "Вопросы о важном"
    public HomePage scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    // Метод прокрутки ко второй кнопке "Заказать"
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    // Нажатие на кнопку "Заказать"
    public HomePage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    // Нажатие на кнопку "Заказать"
    public HomePage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }

    // Метод нажатия на кнопку "Создать заказ"
    public void clickCreateOrderButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpOrderButton();
        } else if (button.equals(DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }

    // Нажатие на вопрос
    public HomePage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }

    // Нажатие на кнопку "Статус заказа"
    public HomePage clickOrderState() {
        driver.findElement(orderState).click();
        return this;
    }

    // Ввод номера заказа
    public HomePage inputOrderNumber(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(numberOrder))
                .sendKeys(number);
        return this;
    }

    // Нажатие на кнопку "Go"
    public HomePage clickGo() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonGo))
                .click();
        return this;
    }

    // Нажатие на кнопку "Яндекс"
    public void clickYandexButton() {
        driver.findElement(yandexButton).click();
    }
}

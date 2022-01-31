package ru.geekbrains.homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

@DisplayName("Авторизация зарегестрированного пользователя")
public class AuthRegisteredUserTest {
    WebDriver webDriver;

    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    void turnDown() {
        webDriver.quit();
    }

    @DisplayName("Позитивный тест: правильный пароль")
    @Test
    public void positiveAuthTest() {

        webDriver.get("https://www.livelib.ru/");
        webDriver.manage().window().setSize(new Dimension(1920, 1080));

        webDriver.findElement(By.xpath("//button[text()='Войти']")).click();
        WebElement authForm = webDriver.findElement(By.xpath("//form[@id='regForm__checkin']"));
        authForm.findElement(By.xpath("//*[@placeholder='Введите e-mail или никнейм']"))
                .sendKeys("marina.ahmadulina@bk.ru");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text()='Продолжить']"))).click();
        authForm.findElement(By.xpath("//*[@placeholder='Введите пароль']")).sendKeys("190489");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='reg-popup__wrapper']//button[text()='Войти']"))).click();
        webDriver.findElement(By.xpath("//summary[@title='Меню']")).click();
        Assertions.assertThat(webDriver.findElement(By.xpath("//*[@class='user-nav__login']")).getText())
                .isEqualTo("Ahmadulina Marina");
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 250);");
        webDriver.findElement(By.xpath("//a[text()='Выйти']")).click();
    }

    @DisplayName("Негативный тест: неправильный пароль")
    @Test
    public void negativeAuthTest() {

        webDriver.get("https://www.livelib.ru/");
        webDriver.manage().window().setSize(new Dimension(1920, 1080));

        webDriver.findElement(By.xpath("//button[text()='Войти']")).click();
        WebElement authForm = webDriver.findElement(By.xpath("//form[@id='regForm__checkin']"));
        authForm.findElement(By.xpath("//*[@placeholder='Введите e-mail или никнейм']"))
                .sendKeys("marina.ahmadulina@bk.ru");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text()='Продолжить']"))).click();
        authForm.findElement(By.xpath("//*[@placeholder='Введите пароль']")).sendKeys("invalid_password");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='reg-popup__wrapper']//button[text()='Войти']"))).click();

        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .presenceOfElementLocated(By.id("reg-popup__unvalid")));
    }
}
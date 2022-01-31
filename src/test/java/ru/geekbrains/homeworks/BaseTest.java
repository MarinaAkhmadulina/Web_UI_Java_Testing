package ru.geekbrains.homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver webDriver;

    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://www.livelib.ru/");
        webDriver.manage().window().setSize(new Dimension(1920, 1080));

        webDriver.findElement(By.xpath("//button[text()='Войти']")).click();
        WebElement authForm = webDriver.findElement(By.xpath("//form[@id='regForm__checkin']"));
        authForm.findElement(By.xpath("//*[@placeholder='Введите e-mail или никнейм']")).sendKeys("marina.ahmadulina@bk.ru");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text()='Продолжить']"))).click();
        authForm.findElement(By.xpath("//*[@placeholder='Введите пароль']")).sendKeys("190489");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='reg-popup__wrapper']//button[text()='Войти']"))).click();
    }

    @AfterEach
    void turnDown() {

        try {
            new WebDriverWait(webDriver, 5).until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//summary[@title='Меню']"))).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println(e.getMessage());
            new WebDriverWait(webDriver, 5).until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//summary[@title='Меню']"))).click();
        }

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 300);");
        webDriver.findElement(By.xpath("//a[text()='Выйти']")).click();
        webDriver.quit();
    }
}
package ru.geekbrains.homeworks.HomeWork_FINAL.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.geekbrains.homeworks.HomeWork_FINAL.listener.ActionEventListener;
import java.util.concurrent.TimeUnit;
import static io.qameta.allure.Allure.step;

public class BaseTest {

    protected EventFiringWebDriver webDriver;
    String url = "https://www.livelib.ru/";

    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = new EventFiringWebDriver (WebDriverManager.chromedriver().capabilities(chromeOptions).create());
        webDriver.register(new ActionEventListener());
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get(url);
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterEach
    void turnDown() {
        step("Отображение логов браузера", () -> {
            for (LogEntry logEntry : webDriver.manage().logs().get(LogType.BROWSER)) {
                Allure.addAttachment("logs", logEntry.getMessage());
            }
        });
        webDriver.quit();
    }
}
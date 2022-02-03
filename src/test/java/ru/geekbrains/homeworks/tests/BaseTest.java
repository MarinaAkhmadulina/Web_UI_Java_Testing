package ru.geekbrains.homeworks.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver webDriver;
    String url = "https://www.livelib.ru/";

    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get(url);
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterEach
    void turnDown() {
        webDriver.quit();
    }
}
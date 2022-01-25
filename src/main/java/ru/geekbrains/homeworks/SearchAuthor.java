package ru.geekbrains.homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class SearchAuthor {

    public static void main(String[] args) throws InterruptedException, AWTException {

        ChromeOptions chromeOptions = new ChromeOptions();

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://www.livelib.ru/");
        webDriver.manage().window().setSize(new Dimension(1920, 1080));

        webDriver.findElement(By.id("checkin-email-block")).sendKeys("marina.ahmadulina@bk.ru");
        webDriver.findElement(By.xpath("//button[@onclick='unregblock_check_email_exists();']")).click();
        WebElement authForm = webDriver.findElement(By.xpath("//form[@id='regForm__singin']"));
        authForm.findElement(By.xpath("//*[@placeholder='Введите пароль']")).sendKeys("190489");
        authForm.findElement(By.id("user[submit]")).click();

        Thread.sleep(5000);

        webDriver.findElement(By.id("header-top-search")).click();
        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys("федор достоевский");

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);

        webDriver.findElement(By.xpath("//*[text()='Авторы ']")).click();

        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("scroll(0, 250);");

        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//summary[@title='Меню']")).click();
        jse.executeScript("scroll(0, 250);");
        webDriver.findElement(By.xpath("//a[text()='Выйти']")).click();

        webDriver.quit();
    }
}
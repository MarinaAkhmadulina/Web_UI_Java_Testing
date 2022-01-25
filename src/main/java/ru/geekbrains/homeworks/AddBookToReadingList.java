package ru.geekbrains.homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class AddBookToReadingList {

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
        webDriver.findElement(By.xpath("//*[@placeholder='Поиск книг']")).sendKeys("идиот");

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);

        webDriver.findElement(By.xpath("//div[@data-link='/book/1003980769-idiot-fjodor-dostoevskij']")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.id("span-userbook-1003980769-0")).click();
        webDriver.findElement(By.xpath("//label[text()='Хочу прочитать']")).click();
        webDriver.findElement(By.xpath("//button[text()='Сохранить']")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//summary[@title='Меню']")).click();
        webDriver.findElement(By.xpath("//a[text()='Хочу прочитать']")).click();
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 300);");

        webDriver.findElement(By.xpath("//img[@alt='Фёдор Достоевский - Идиот']")).click();
        webDriver.findElement(By.xpath("//*[@data-view_mode='main']/a")).click();
        webDriver.findElement(By.xpath("//label[text()='Хочу прочитать']")).click();
        webDriver.findElement(By.xpath("//button[text()='Да, удалить']")).click();
        webDriver.findElement(By.xpath("//button[text()='Сохранить']")).click();

        webDriver.findElement(By.xpath("//summary[@title='Меню']")).click();
        jse.executeScript("scroll(0, 300);");
        webDriver.findElement(By.xpath("//a[text()='Выйти']")).click();

        webDriver.quit();
    }
}
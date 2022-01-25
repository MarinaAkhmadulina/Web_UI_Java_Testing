package ru.geekbrains.homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class AuthRegisteredUser {

    public static void main(String[] args) throws ElementNotInteractableException, InterruptedException {

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
        webDriver.findElement(By.xpath("//summary[@title='Меню']")).click();
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 250);");
        webDriver.findElement(By.xpath("//a[text()='Выйти']")).click();

        webDriver.quit();
    }
}
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

        webDriver.findElement(By.xpath("//button[text()='Войти']")).click();
        WebElement authForm = webDriver.findElement(By.xpath("//form[@id='regForm__checkin']"));
        authForm.findElement(By.xpath("//*[@placeholder='Введите e-mail или никнейм']")).sendKeys("marina.ahmadulina@bk.ru");
        Thread.sleep(5000);
        authForm.findElement(By.xpath("//button[text()='Продолжить']")).click();
        authForm.findElement(By.xpath("//*[@placeholder='Введите пароль']")).sendKeys("190489");
        Thread.sleep(5000);
        authForm.findElement(By.xpath("//div[@class='reg-popup__wrapper']//button[text()='Войти']")).click();

        webDriver.findElement(By.xpath("//summary[@title='Меню']")).click();
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 250);");
        webDriver.findElement(By.xpath("//a[text()='Выйти']")).click();

        webDriver.quit();
    }
}
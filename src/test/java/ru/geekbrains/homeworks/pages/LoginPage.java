package ru.geekbrains.homeworks.pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//summary[@title='Меню']")
    private WebElement buttonMenu;

    @FindBy(xpath = "//button[text()='Продолжить']")
    private WebElement buttonContinue;

    @FindBy(xpath = "//a[text()='Выйти']")
    private WebElement buttonLogout;

    @FindBy(xpath = "//*[@placeholder='Введите e-mail или никнейм']")
    private WebElement inputEmail;

    @FindBy(xpath = "//*[@placeholder='Введите пароль']")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[@class='reg-popup__wrapper']//button[text()='Войти']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//*[@class='user-nav__login']")
    private WebElement userName;

    private String stringErrorlocator = "reg-popup__unvalid";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage login(String email, String password) {

        inputEmail.sendKeys(email);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonContinue)).click();
        inputPassword.sendKeys(password);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonLogin)).click();
        return this;
    }

    public MainPage logout() {

        try {
            new WebDriverWait(webDriver, 5)
                    .until(ExpectedConditions.elementToBeClickable(buttonMenu)).click();
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            new WebDriverWait(webDriver, 5)
                    .until(ExpectedConditions.elementToBeClickable(buttonMenu)).click();
        }

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 250);");
        buttonLogout.click();
        return new MainPage(webDriver);
    }

    public LoginPage checkUserName() {

        try {
            new WebDriverWait(webDriver, 5)
                    .until(ExpectedConditions.elementToBeClickable(buttonMenu)).click();
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            new WebDriverWait(webDriver, 5)
                    .until(ExpectedConditions.elementToBeClickable(buttonMenu)).click();
        }
        Assertions.assertThat(userName.getText()).isEqualTo("Ahmadulina Marina");
        buttonMenu.click();
        return this;
    }

    public LoginPage checkError() {

        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .presenceOfElementLocated(By.id(stringErrorlocator)));
        return this;
    }
}
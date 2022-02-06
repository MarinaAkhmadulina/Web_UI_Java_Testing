package ru.geekbrains.homeworks.HomeWork_FINAL.pages;

import io.qameta.allure.Step;
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

    @FindBy(id = "reg-popup__unvalid")
    private WebElement stringError;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Вввести email {email} и пароль {password}")
    public LoginPage login(String email, String password) {

        inputEmail.sendKeys(email);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonContinue)).click();
        inputPassword.sendKeys(password);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonLogin)).click();
        return this;
    }

    @Step("Выйти из аккаунта")
    public MainPage logout() {

        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.elementToBeClickable(buttonMenu)).click();

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("scroll(0, 250);");
        buttonLogout.click();
        return new MainPage(webDriver);
    }

    @Step("Проверить имя пользователя")
    public LoginPage checkUserName() {

        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.elementToBeClickable(buttonMenu)).click();

        Assertions.assertThat(userName.getText()).isEqualTo("Ahmadulina Marina");
        buttonMenu.click();
        return this;
    }

    @Step("Проверить, что появляется сообщение об ошибке авторизации")
    public LoginPage checkError() {

        Assertions.assertThat(stringError.getText()).isEqualTo("Ошибка авторизации!");
        return this;
    }
}
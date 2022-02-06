package ru.geekbrains.homeworks.HomeWork_FINAL.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на кнопку ВОЙТИ")
    public LoginPage clickLoginButton() {

        loginButton.click();
        return new LoginPage(webDriver);
    }
}
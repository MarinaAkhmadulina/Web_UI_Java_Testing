package ru.geekbrains.homeworks.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage clickLoginButton() {

        loginButton.click();
        return new LoginPage(webDriver);
    }
}
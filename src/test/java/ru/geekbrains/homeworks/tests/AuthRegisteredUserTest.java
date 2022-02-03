package ru.geekbrains.homeworks.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.homeworks.pages.MainPage;

@DisplayName("Авторизация зарегистрированного пользователя")
public class AuthRegisteredUserTest extends BaseTest {

    String email = "marina.ahmadulina@bk.ru";
    String password = "190489";
    String invalidPassword = "111111";

    @DisplayName("Позитивный тест: правильный пароль")
    @Test
    public void positiveAuthTest() {

        new MainPage(webDriver).clickLoginButton()
        .login(email,password)
        .checkUserName()
        .logout();
    }

    @DisplayName("Негативный тест: неправильный пароль")
    @Test
    public void negativeAuthTest() {

        new MainPage(webDriver).clickLoginButton()
                .login(email,invalidPassword)
                .checkError();
    }
}
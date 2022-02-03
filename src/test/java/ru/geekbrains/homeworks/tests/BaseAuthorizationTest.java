package ru.geekbrains.homeworks.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.geekbrains.homeworks.pages.MainPage;

public class BaseAuthorizationTest extends BaseTest {

    String email = "marina.ahmadulina@bk.ru";
    String password = "190489";

    @BeforeEach
    void auth() {
        new MainPage(webDriver).clickLoginButton()
                .login(email, password);
    }
}
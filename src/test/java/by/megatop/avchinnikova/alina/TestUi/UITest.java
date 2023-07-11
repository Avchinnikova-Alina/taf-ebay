package by.megatop.avchinnikova.alina.TestUi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UITest extends BaseTest {
    @Test
    public void emptyEmailAndEmptyPassword() {
        megatopPage.clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле", megatopPage.getErrorWhenEmptyEmail());
    }
    @Test
    public void emptyEmailAndCorrectPassword() {
        megatopPage.sendKeysInputPassword("Qwerty1Q").
                clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле", megatopPage.getErrorWhenEmptyEmail());
    }
    @Test
    public void correctEmailAndEmptyPassword() {
        megatopPage.
                sendKeysInputEmail("447858875").
                clickButtonSignIn();
        Assertions.assertEquals("Вы ввели неверный номер телефона и/или пароль", megatopPage.getErrorWhenIncorrectEmailOrPassword());
    }
    @Test
    public void IncorrectEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputEmail("117858875").
                sendKeysInputPassword("Qwerty1Q").
                clickButtonSignIn();
        Assertions.assertEquals("Введите корректно номер телефона", megatopPage.getErrorWhenIncorrectEmail());
    }
    @Test
    public void IncompletelyEnteredDataEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputEmail("1178588").
                sendKeysInputPassword("Qwerty1Q").
                clickButtonSignIn();
        Assertions.assertEquals("Введите телефон полностью", megatopPage.getErrorWhenIncompletelyEmail());
    }
}

package by.megatop.avchinnikova.alina.TestUi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UITest extends BaseTest {
    @Test
    public void emptyEmailAndEmptyPassword() {
        megatopPage.clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле",
                megatopPage.getErrorWhenEmptyEmail());
    }

    @Test
    public void emptyEmailAndCorrectPassword() {
        megatopPage.sendKeysInputPassword(faker.internet().password()).
                clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле",
                megatopPage.getErrorWhenEmptyEmail());
    }

    @Test
    public void correctEmailAndEmptyPassword() {
        megatopPage.
                sendKeysInputEmail(megatopPage.generatePhoneNumber()).
                clickButtonSignIn();
        Assertions.assertEquals("Вы ввели неверный номер телефона и/или пароль",
                megatopPage.getErrorWhenIncorrectEmailOrPassword());
    }

    @Test
    public void IncorrectEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputEmail(faker.phoneNumber().cellPhone()).
                sendKeysInputPassword(faker.internet().password()).
                clickButtonSignIn();
        Assertions.assertEquals("Введите корректно номер телефона",
                megatopPage.getErrorWhenIncorrectEmail());
    }

    @Test
    public void IncompletelyEnteredDataEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputPassword(faker.internet().password()).
                sendKeysInputEmail(megatopPage.generatePhoneNumber()).
                clickButtonSignIn();
        Assertions.assertEquals("Введите телефон полностью",
                megatopPage.getErrorWhenIncompletelyEmail());
    }

    @Test
    public void CorrectEmailAndIncorrectPassword() {
        megatopPage.
                sendKeysInputEmail(megatopPage.generatePhoneNumber()).
                sendKeysInputPassword(faker.internet().password()).
                clickButtonSignIn();
        Assertions.assertEquals("Вы ввели неверный номер телефона и/или пароль",
                megatopPage.getErrorWhenIncorrectEmailOrPassword());
    }

    @Test
    public void CorrectEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputEmail(megatopPage.generatePhoneNumber()).
                sendKeysInputPassword(megatopPage.generatePassword()).
                clickButtonSignIn();
        Assertions.assertEquals("ПРИВЕТ, АЛИНА", megatopPage.getTextSuccessfulLogin());
    }
}

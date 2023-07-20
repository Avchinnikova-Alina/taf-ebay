package by.megatop.avchinnikova.alina.TestUi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class UITest extends BaseTest {
    @Test
    public void emptyEmailAndEmptyPassword() {
        megatopPage.clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле",
                megatopPage.getErrorWhenEmptyEmail());
    }

    @Test
    public void emptyEmailAndCorrectPassword() {
        megatopPage.sendKeysInputPassword(megatopPassword.toString()).
                clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле",
                megatopPage.getErrorWhenEmptyEmail());
    }

    @Test
    public void correctEmailAndEmptyPassword() {
        megatopPage.
                sendKeysInputEmail(phoneNumber.generatePhoneNumber()).
                clickButtonSignIn();
        Assertions.assertEquals("Вы ввели неверный номер телефона и/или пароль",
                megatopPage.getErrorWhenIncorrectEmailOrPassword());
    }

    @Test
    public void IncorrectEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputEmail(faker.phoneNumber().cellPhone()).
                sendKeysInputPassword(megatopPassword.toString()).
                clickButtonSignIn();
        Assertions.assertEquals("Введите корректно номер телефона",
                megatopPage.getErrorWhenIncorrectEmail());
    }

    @Test
    public void IncompletelyEnteredDataEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputPassword(megatopPassword.toString()).
                sendKeysInputEmail(phoneNumber.generatePhoneNumber()).
                clickButtonSignIn();
        Assertions.assertEquals("Введите телефон полностью",
                megatopPage.getErrorWhenIncompletelyEmail());
    }

    @Test
    public void CorrectEmailAndIncorrectPassword() {
        megatopPage.
                sendKeysInputEmail(phoneNumber.generatePhoneNumber()).
                sendKeysInputPassword(faker.internet().password()).
                clickButtonSignIn();
        Assertions.assertEquals("Вы ввели неверный номер телефона и/или пароль",
                megatopPage.getErrorWhenIncorrectEmailOrPassword());
    }

    @Test
    public void CorrectEmailAndCorrectPassword() {
        megatopPage.
                sendKeysInputEmail(megatopPage.generatePhoneNumber()).
                sendKeysInputPassword(megatopPassword.toString()).
                clickButtonSignIn();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assertions.assertEquals("ПРИВЕТ, АЛИНА", megatopPage.getTextSuccessfulLogin());
    }
}

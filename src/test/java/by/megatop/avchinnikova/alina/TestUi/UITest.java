package by.megatop.avchinnikova.alina.TestUi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

public class UITest extends BaseTest {
    @Test
    public void emptyEmailAndEmptyPassword() {
        megatopPage.clickButtonProfile().
                clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле",
                megatopPage.getErrorWhenEmptyEmail());
    }

    @Test
    public void emptyEmailAndCorrectPassword() {
        megatopPage.clickButtonProfile().
                sendKeysInputPassword(megatopPassword.toString()).
                clickButtonSignIn();
        Assertions.assertEquals("Телефон обязательное поле",
                megatopPage.getErrorWhenEmptyEmail());
    }

    @Test
    public void correctEmailAndEmptyPassword() {
        megatopPage.
                clickButtonProfile().
                sendKeysInputEmail(phoneNumber.generatePhoneNumber()).
                clickButtonSignIn();
        Assertions.assertEquals("Вы ввели неверный номер телефона и/или пароль",
                megatopPage.getErrorWhenIncorrectEmailOrPassword());
    }

    @Test
    public void IncorrectEmailAndCorrectPassword() {
        megatopPage.
                clickButtonProfile().
                sendKeysInputEmail(faker.phoneNumber().cellPhone()).
                sendKeysInputPassword(megatopPassword.toString()).
                clickButtonSignIn();
        Assertions.assertEquals("Введите корректно номер телефона",
                megatopPage.getErrorWhenIncorrectEmail());
    }

    @Test
    public void IncompletelyEnteredDataEmailAndCorrectPassword() {
        megatopPage.
                clickButtonProfile().
                sendKeysInputPassword(megatopPassword.toString()).
                sendKeysInputEmail(phoneNumber.generatePhoneNumber()).
                clickButtonSignIn();
        Assertions.assertEquals("Введите телефон полностью",
                megatopPage.getErrorWhenIncompletelyEmail());
    }

    @Test
    public void CorrectEmailAndIncorrectPassword() {
        megatopPage.
                clickButtonProfile().
                sendKeysInputEmail(phoneNumber.generatePhoneNumber()).
                sendKeysInputPassword(faker.internet().password()).
                clickButtonSignIn();
        Assertions.assertEquals("Вы ввели неверный номер телефона и/или пароль",
                megatopPage.getErrorWhenIncorrectEmailOrPassword());
    }

    @Test
    public void CorrectEmailAndCorrectPassword() {
        megatopPage.
                clickButtonProfile().
                sendKeysInputEmail(phoneNumber.generatePhoneNumber()).
                sendKeysInputPassword(megatopPassword.toString()).
                clickButtonSignIn();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assertions.assertEquals("ПРИВЕТ, АЛИНА", megatopPage.getTextSuccessfulLogin());
    }

    @Test
    public void SearchShoes() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        megatopPage.clickButtonSearch();
        megatopPage.sendKeysInputSearch(nameSearch.toString());
        Assertions.assertEquals("ПОИСК ПО ЗАПРОСУ 'КРОССОВКИ'", megatopPage.getTextSuccessfulSearch());
    }
}

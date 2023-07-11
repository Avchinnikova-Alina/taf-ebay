package TestUi;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.MegatopPage;

import java.util.concurrent.TimeUnit;

public class MegatopTest {
    ChromeDriver driver;
    MegatopPage megatopPage;
    Faker faker;
    @BeforeEach
    public void warmUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://megatop.by/");
        megatopPage = new MegatopPage(driver);
        faker = new Faker();
        megatopPage.getAllert();
        megatopPage.clickButtonProfile();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
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
        Assertions.assertEquals("Введите телефон полностью", megatopPage.getErrorWhenIncorrectEmail());
    }
    @AfterEach
    public void tearsDown() {
        driver.quit();
    }
}

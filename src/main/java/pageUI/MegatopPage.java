package pageUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class MegatopPage {
    private ChromeDriver driver;
    private String buttonProfileLocator = "//div[@class='v-responsive__content' and @style='width: 25px;']";
    private String inputEmailLocator = "//form[@class='v-form']//input[contains(@id, 'input-') and @type='text']";
    private String inputPasswordLocator = "//form//input[contains(@id, 'input-') and @type='password']";
    private String errorTextWhenIncorrectEmailOrPasswordLocator = "//div[@class='v-messages__message' and text()='Вы ввели неверный номер телефона и/или пароль']";
    private String errorTextWhenEmptyEmailLocator = "//div[@class='v-messages__message' and text()='Телефон обязательное поле']";
    private String errorTextWhenIncorrectEmailLocator = "//div[@class='v-messages__message' and text()='Введите корректно номер телефона']";
    private String errorTextWhenIncompletelyEmailLocator = "//div[@class='v-messages__message' and text()='Введите телефон полностью']";
    private String buttonSignInLocator = "//button[@type='submit']";
    private String buttonYesLocator = "//*[@id='app']/div[3]/div/div/div/div/div/div/div[2]/button[1]";
    private String getTextSuccessfulLoginLocator = "//div[@class='text-uppercase profile__title col col-12' and contains(text(), 'привет, Алина')]";

    public MegatopPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public MegatopPage clickButtonProfile() {
        WebElement buttonSignIn = driver.findElement(By.xpath(buttonProfileLocator));
        buttonSignIn.click();
        return this;
    }

    public MegatopPage sendKeysInputEmail(String email) {
        WebElement inputEmail = driver.findElement(By.xpath(inputEmailLocator));
        inputEmail.sendKeys(email);
        return this;
    }

    public MegatopPage sendKeysInputPassword(String password) {
        WebElement inputPassword = driver.findElement(By.xpath(inputPasswordLocator));
        inputPassword.sendKeys(password);
        return this;
    }

    public MegatopPage clickButtonSignIn() {
        WebElement buttonSignIn = driver.findElement(By.xpath(buttonSignInLocator));
        buttonSignIn.click();
        return this;
    }

    public String getErrorWhenIncorrectEmailOrPassword() {
        WebElement actError = driver.findElement(By.xpath(errorTextWhenIncorrectEmailOrPasswordLocator));
        String actErrorMessage = actError.getText();
        return actErrorMessage;
    }

    public String getErrorWhenEmptyEmail() {
        WebElement actError = driver.findElement(By.xpath(errorTextWhenEmptyEmailLocator));
        String actErrorMessage = actError.getText();
        return actErrorMessage;
    }

    public String getErrorWhenIncorrectEmail() {
        WebElement actError = driver.findElement(By.xpath(errorTextWhenIncorrectEmailLocator));
        String actErrorMessage = actError.getText();
        return actErrorMessage;
    }

    public String getErrorWhenIncompletelyEmail() {
        WebElement actError = driver.findElement(By.xpath(errorTextWhenIncompletelyEmailLocator));
        String actErrorMessage = actError.getText();
        return actErrorMessage;
    }

    public MegatopPage clickButtonYes() {
        WebElement buttonYes = driver.findElement(By.xpath(buttonYesLocator));
        buttonYes.click();
        return this;
    }

    public String getTextSuccessfulLogin() {
        WebElement getText = driver.findElement(By.xpath(getTextSuccessfulLoginLocator));
        String textSuccessfulLogin = getText.getText();
        return textSuccessfulLogin;
    }

    public String generatePhoneNumber() {
        WebElement passwordField = driver.findElement(By.xpath(inputPasswordLocator));
        String passwordFieldValue = passwordField.getAttribute("value");
        WebElement phoneField = driver.findElement(By.xpath(inputEmailLocator));
        String phoneFieldValue = phoneField.getAttribute("value");
        if (passwordFieldValue.isEmpty()) {
            phoneField.sendKeys("447858875");
        } else {
            if (!passwordFieldValue.isEmpty()) {
                phoneField.sendKeys("1178588");
            }
        }
        return phoneFieldValue;
    }

    public String generatePassword() {
        Random random = new Random();
        String password = "";
        while (!password.equals("qwerty1Q")) {
            password = "";
            for (int i = 0; i < 8; i++) {
                int randomNum = random.nextInt(62);
                char randomChar;
                if (randomNum < 26) {
                    randomChar = (char) ('a' + randomNum);
                } else if (randomNum < 52) {
                    randomChar = (char) ('A' + randomNum - 26);
                } else {
                    randomChar = (char) ('0' + randomNum - 52);
                }
                password += randomChar;
            }
        }
        return password;
    }
}


package pageUI;

import Util.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MegatopPage {
    public ChromeDriver driver;
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
    private String buttonSearchLocator = "//div[@class='v-responsive__content' and @style='width: 24px;']";
    private String inputSearchLocator = "//div[@class='v-text-field__slot']/input";
    private String getTextSuccessfulSearch = "//h1[@class='catalog__title text-uppercase' and @data-v-8b85e2f2='']";

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

    public MegatopPage clickButtonSearch() {
        WebElement buttonSearch = driver.findElement(By.xpath(buttonSearchLocator));
        buttonSearch.click();
        return this;
    }

    public MegatopPage sendKeysInputSearch(String string) {
        Waiters.waitForSeconds(1);
        WebElement inputSearch = driver.findElement(By.xpath(inputSearchLocator));
        inputSearch.click();
        inputSearch.sendKeys(string + Keys.ENTER);
        return this;
    }

    public String getTextSuccessfulSearch() {
        Waiters.waitForSeconds(1);
        WebElement getText = driver.findElement(By.xpath(getTextSuccessfulSearch));
        String textSuccessfulSearch = getText.getText();
        return textSuccessfulSearch;
    }
}


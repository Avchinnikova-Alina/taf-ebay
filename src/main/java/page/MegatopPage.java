package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MegatopPage {
    private ChromeDriver driver;
    private String buttonProfileXpath = "//div[@class=\"v-responsive__content\" and @style=\"width: 25px;\"]";
    private String iFrameXpath = "/html/body/noscript[1]/text()";
    private String inputEmailXpath = "//form[@class=\"v-form\"]//input[contains(@id, \"input-\") and @type=\"text\"]";
    private String inputPasswordXpath = "//input[@id=\"input-341\" and @type=\"password\"]";
    private String errorTextWhenIncorrectEmailOrPasswordXpath = "//div[@class=\"v-messages__message\" and text()=\"Вы ввели неверный номер телефона и/или пароль\"]";
    private String errorTextWhenEmptyEmailXpath = "//div[@class=\"v-messages__message\" and text()=\"Телефон обязательное поле\"]";
    private String errorTextWhenIncorrectEmailXpath = "//div[@class=\"v-messages__message\" and text()=\"Введите корректно номер телефона\"]";
    private String buttonSignInXpath = "//*[@id=\"app\"]/div/div[7]/div/div/div[2]/form/div[3]/div[2]/button";
    private String buttonYesXpath = "//*[@id=\"app\"]/div[3]/div/div/div/div/div/div/div[2]/button[1]";

    public MegatopPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public MegatopPage clickButtonProfile() {
        WebElement buttonSignIn = driver.findElement(By.xpath(buttonProfileXpath));
        buttonSignIn.click();
        return this;
    }

    public void switchToIframe() {
        WebElement iFrame = driver.findElement(By.xpath(iFrameXpath));
        driver.switchTo().frame(iFrame);
    }
    public void clickContinueWithEmail() {
        if (driver.findElements(By.xpath(iFrameXpath)).size() > 0) {
            try {
                switchToIframe();
                WebElement clickContinueWithEmailButton = driver.findElement(By.xpath(inputEmailXpath));
                clickContinueWithEmailButton.click();
            } catch (NoSuchElementException e) {
                System.out.println("no iframe");
            }
        } else {
            WebElement clickContinueWithEmailButton = driver.findElement(By.xpath(inputEmailXpath));
            clickContinueWithEmailButton.click();
        }
    }
    public MegatopPage sendKeysInputEmail(String email) {
        WebElement inputEmail = driver.findElement(By.xpath(inputEmailXpath));
        inputEmail.sendKeys(email);
        return this;
    }

    public MegatopPage sendKeysInputPassword(String password) {
        WebElement inputPassword = driver.findElement(By.xpath(inputPasswordXpath));
        inputPassword.sendKeys(password);
        return this;
    }

    public MegatopPage clickButtonSignIn() {
        WebElement buttonSignIn = driver.findElement(By.xpath(buttonSignInXpath));
        buttonSignIn.click();
        return this;
    }

    public String getErrorWhenIncorrectEmailOrPassword() {
        WebElement actError = driver.findElement(By.xpath(errorTextWhenIncorrectEmailOrPasswordXpath));
        String actErrorMessage = actError.getText();
        return actErrorMessage;
    }

    public String getErrorWhenEmptyEmail() {
        WebElement actError = driver.findElement(By.xpath(errorTextWhenEmptyEmailXpath));
        String actErrorMessage = actError.getText();
        return actErrorMessage;
    }

    public String getErrorWhenIncorrectEmail() {
        WebElement actError = driver.findElement(By.xpath(errorTextWhenIncorrectEmailXpath));
        String actErrorMessage = actError.getText();
        return actErrorMessage;
    }

    public MegatopPage getAllert() {
        WebElement buttonYes = driver.findElement(By.xpath(buttonYesXpath));
        buttonYes.click();
        return this;
    }
}

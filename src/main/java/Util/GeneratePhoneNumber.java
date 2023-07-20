package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GeneratePhoneNumber {
    private ChromeDriver driver;

    public GeneratePhoneNumber(ChromeDriver driver) {
        this.driver = driver;
    }

    public String generatePhoneNumber() {
        WebElement passwordField = driver.findElement(By.xpath("//form//input[contains(@id, 'input-') and @type='password']"));
        String passwordFieldValue = passwordField.getAttribute("value");
        WebElement phoneField = driver.findElement(By.xpath("//form[@class='v-form']//input[contains(@id, 'input-') and @type='text']"));
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
}

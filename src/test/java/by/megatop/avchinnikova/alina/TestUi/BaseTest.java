package by.megatop.avchinnikova.alina.TestUi;

import Util.GeneratePhoneNumber;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageUI.MegatopPage;
import Util.GeneratePassword;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    ChromeDriver driver;
    MegatopPage megatopPage;
    GeneratePassword megatopPassword;
    GeneratePhoneNumber phoneNumber;
    Faker faker;
    @BeforeEach
    public void warmUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://megatop.by/");
        megatopPage = new MegatopPage(driver);
        megatopPassword = new GeneratePassword("qwerty1Q");
        phoneNumber =  new GeneratePhoneNumber(driver);
        faker = new Faker();
        megatopPage.clickButtonYes();
        megatopPage.clickButtonProfile();
    }
    @AfterEach
    public void tearsDown() {
        driver.quit();
    }
}

package by.megatop.avchinnikova.alina.TestUi;

import Util.GenerateNameSearch;
import Util.GeneratePhoneNumber;
import Util.Waiters;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
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
    GenerateNameSearch nameSearch;
    Waiters wait;
    Faker faker;
    @BeforeEach
    public void warmUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://megatop.by/");
        megatopPage = new MegatopPage(driver);
        megatopPassword = new GeneratePassword("qwerty1Q");
        phoneNumber =  new GeneratePhoneNumber(driver);
        wait = new Waiters();
        nameSearch = new GenerateNameSearch("Кроссовки");
        faker = new Faker();
        megatopPage.clickButtonYes();
    }
    @AfterEach
    public void tearsDown() {
        driver.quit();
    }
}

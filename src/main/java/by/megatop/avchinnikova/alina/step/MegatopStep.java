package by.megatop.avchinnikova.alina.step;

import org.openqa.selenium.chrome.ChromeDriver;
import by.megatop.avchinnikova.alina.page.MegatopPage;

public class MegatopStep {
    MegatopPage page;

    public MegatopStep(ChromeDriver driver) {
        page = new MegatopPage(driver);
    }

    public void fillLoginFormAndSubmit(String email, String password) {
        page.getAllert();
        page.clickButtonProfile();
        //page.switchToIframe();
        page.sendKeysInputEmail(email);
        page.sendKeysInputPassword(password);
        page.clickButtonSignIn();
    }
}

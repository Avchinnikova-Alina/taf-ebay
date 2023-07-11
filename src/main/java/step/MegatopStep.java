package step;

import org.openqa.selenium.chrome.ChromeDriver;
import page.MegatopPage;

public class MegatopStep {
    MegatopPage page;

    public MegatopStep(ChromeDriver driver) {
        page = new MegatopPage(driver);
    }

    public void fillLoginFormAndSubmit(String email, String password) {
        page.getAllert();
        page.clickContinueWithEmail();
        page.clickButtonProfile();
        page.sendKeysInputEmail(email);
        page.sendKeysInputPassword(password);
        page.clickButtonSignIn();
    }
}

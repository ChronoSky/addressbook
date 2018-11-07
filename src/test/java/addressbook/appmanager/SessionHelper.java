package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String login, String pass) {
        type(By.name("user") , login);
        type(By.name("pass") , pass);
        click(By.xpath("//*[@name='LoginForm']/*[@value='Login']"));
    }

}

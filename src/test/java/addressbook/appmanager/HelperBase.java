package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String value) {
        click(locator);
        if (value != null){
            String text = wd.findElement(locator).getAttribute("value");
            if (! text.equals(value)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(value);
            }
        }

    }
}

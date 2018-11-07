package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod
    public void setUp() {

        app.init();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        app.stop();
    }

}

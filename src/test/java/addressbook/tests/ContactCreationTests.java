package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

}

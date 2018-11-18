package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactModificationTests extends TestBase{

    String prefix ;
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (!app.contact().isThereAContact()){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("test_FirstName_1").withMiddleName("test_MiddleName_2").withLastName("test_LastName_3").withGroup("test1"), false );
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("test_FirstName_New_1").withMiddleName("test_MiddleName_New_2").withLastName("test_LastName_New_3");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(before.size(), after.size());
        Assert.assertEquals(after , before.withOut(modifyContact).withAdded(contact));
    }



}

package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (!app.contact().isThereAContact()){
            app.goTo().contactPage();
            app.contact().createContact(new ContactData().withFirstName("test1").withMiddleName("test2").withLastName("test3").withGroup("test1"), false );
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().getContactList();
        int index = before.size()-1;
        app.contact().initContactModification();
        int id = before.get(index).getId();
        ContactData contact = new ContactData().withId(id).withFirstName("test4").withMiddleName("test5").withLastName("test6");
        app.contact().fillContactForm(contact, false);
        app.contact().submitContactModification();
        app.contact().returnToHomePage();

        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}

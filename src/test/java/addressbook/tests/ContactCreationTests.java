package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().getContactList();
        ContactData contact = new ContactData().withFirstName("test1").withMiddleName("test2").withLastName("test3").withGroup("test1");
        app.goTo().contactPage();
        app.contact().createContact(contact, true);
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(before.size(), after.size()-1);

        contact.withId(after.stream().max((o1,o2)-> Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }



}

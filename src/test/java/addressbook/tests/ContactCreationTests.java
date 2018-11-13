package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ContactCreationTests extends TestBase{

    String prefix ;
    @BeforeMethod
    public void ensurePreconditions(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd_MM_yyyy");
        prefix = formatForDateNow.format(dateNow);
    }

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("test_FirstName_1").withMiddleName("test_MiddleName_2").withLastName("test_LastName_3").withGroup("test1");
        app.goTo().contactPage();
        app.contact().create(contact, true);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size(), after.size()-1);

        contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }



}

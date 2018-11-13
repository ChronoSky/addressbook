package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void selectContactById(int id) {
        click(By.xpath("//*[@id='maintable']//tr[@name='entry' and .//input[@id='"+ id +"']]//img[@title='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']//tr[@name='entry']"));
        for (WebElement element : elements){
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData group = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(group);
        }
        return contacts;
    }

    public boolean isThereAContact() {
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']//tr[@name='entry']"));
        return elements.size() > 0 ? true : false;
    }

    public void create(ContactData contact, boolean isGroup) {
        fillContactForm(contact, isGroup);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }
}

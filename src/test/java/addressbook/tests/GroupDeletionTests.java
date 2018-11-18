package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.group().all();
        GroupData deletedgroup = before.iterator().next();
        app.group().delete(deletedgroup);
        Groups after = app.group().all();
        Assert.assertEquals(before.size()-1, after.size());
        Assert.assertEquals(after, before.withOut(deletedgroup));

    }



}

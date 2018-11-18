package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class GroupCreationTests  extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        Groups after = app.group().all();
        Assert.assertEquals(before.size(), after.size()-1);

        group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
        Assert.assertEquals(after, before.withAdded(group));
    }

}

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CanDoTest {
    @Test
    public void happyPassCanDoTest(){
        Team team = new Team();
        Story story = new Story();
       team.backlog = Arrays.asList(story);
        Assert.assertTrue(team.canDo());

    }

    @Test
    public void oneStoryTest(){
        Team team = new Team();
        Story story = new Story();
        story.setStoryPoints(1);
        team.backlog = Arrays.asList(story);
        Member member = new Member();

        Assert.assertFalse(team.canDo());
        team.addMember(member);

        Assert.assertTrue(team.canDo());

    }
}

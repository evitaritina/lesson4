import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CanDeliverQualityTest {
    @Test
    public void oneQAoneStoryTest(){
        Team team = new Team();
        Story story = new Story();
        story.setStoryPoints(10);
        story.setTestPoints(10);
        team.backlog = Arrays.asList(story);
        Member memberQA = new Member();
        team.addMember(memberQA);
        memberQA.testingSkills = true;
        Member memberDEV = new Member();
        team.addMember(memberDEV);
        memberDEV.codinSkills = true;
       Assert.assertTrue(team.canDeliverQuality());
       Assert.assertTrue(team.canDo());
        Assert.assertTrue(team.canDeliver());

        story.setTestPoints(1);
        Assert.assertTrue(team.canDeliverQuality());

    }
}

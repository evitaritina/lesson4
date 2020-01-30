import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanDeliverTest {
    @Test
    public void CanDeliverHappyPath_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberDEV2 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberDEV2);
        myTeam.addMember(memberQA1);

        memberDEV1.setVelocity(1.0);
        memberDEV2.setVelocity(0.5);
        memberQA1.setVelocity(1.0);

        Story story = new Story();
        story.setStoryPoints(1);

        myTeam.backlog = Arrays.asList(story);

        assertEquals("Can't delivery", true, myTeam.canDeliver());

    }

    @Test
    public void CanDeliverNegativeScenario_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberDEV2 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberDEV2);
        myTeam.addMember(memberQA1);

        memberDEV1.setVelocity(1.0);
        memberDEV2.setVelocity(0.5);
        memberQA1.setVelocity(1.0);

        Story story1 = new Story();
        story1.setStoryPoints(1);

        Story story2 = new Story();
        story2.setStoryPoints(2);

        myTeam.backlog = Arrays.asList(story1, story2);

        assertEquals("Can delivery", false, myTeam.canDeliver());

    }
}

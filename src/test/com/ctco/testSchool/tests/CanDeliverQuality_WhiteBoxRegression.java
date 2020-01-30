import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CanDeliverQuality_WhiteBoxRegression {

    @Test
    public void CanDeliverQualityHappyPath_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberQA1);

        memberDEV1.setVelocity(1.0);
        memberQA1.setVelocity(1.0);

        Story story1 = new Story();
        story1.setStoryPoints(1);
        Story story2 = new Story();
        story2.setStoryPoints(1);

        myTeam.backlog = Arrays.asList(story1,story2);

        boolean res = myTeam.canDeliver() && myTeam.canDeliverQuality();

        assertEquals("Can't delivery", true, res);
    }
    @Test
    public void CanDeliverQualityNegativeScenario_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberQA1);

        memberDEV1.setVelocity(1.0);
        memberQA1.setVelocity(1.0);

        Story story1 = new Story();
        story1.setStoryPoints(5);

        Story story2 = new Story();
        story2.setTestPoints(12);

        myTeam.backlog = Arrays.asList(story1, story2);
        boolean res = myTeam.canDeliver() && myTeam.canDeliverQuality();

        assertEquals("Can deliver", false, res);

    }
}

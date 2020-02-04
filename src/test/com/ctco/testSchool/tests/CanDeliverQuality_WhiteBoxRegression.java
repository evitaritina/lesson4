package com.ctco.testSchool.tests;

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
        Member memberQA1 = new Member(Member.type.TEST);

        myTeam.addMember(memberQA1);

        memberQA1.setVelocity(1.0);

        Story story = new Story();
        story.setTestPoints(1);

        myTeam.backlog = Arrays.asList(story);

        assertEquals("Can't delivery", true, myTeam.canDeliverQuality());

    }

    @Test
    public void CanDeliverQualityNegativeScenario_test() {
        Team myTeam = new Team();

        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberQA1);
        memberQA1.setVelocity(1.0);

        Story story1 = new Story();
        story1.setTestPoints(5);

        Story story2 = new Story();
        story2.setTestPoints(6);

        myTeam.backlog = Arrays.asList(story1, story2);

        assertEquals("Can deliver", false, myTeam.canDeliverQuality());

    }
}

package com.ctco.testSchool.tests;

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CanDeliveryQuality_BlackBoxRegression {
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
        story1.setTestPoints(5);
        Story story2 = new Story();
        story2.setStoryPoints(1);
        story2.setTestPoints(1);

        myTeam.backlog = Arrays.asList(story1, story2);

        assertEquals("Can't delivery quality", true, myTeam.canDeliverQuality());


    }

    //Negative scenario:
    //   -test story points < QA capacity
    //   - dev story point > DEV capacity
    //Expected: test fails
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


        assertEquals("Can delivery quality", false, myTeam.canDeliverQuality());


    }

    //Test Case:
    //   Story contains only storypoints for DEV
    //   Team is only 1 DEV
    //Expected: test success
    @Test
    public void CanDeliverQuality_3_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberQA1);

        memberDEV1.setVelocity(1.0);
        memberQA1.setVelocity(0.1);

        Story story1 = new Story();
        story1.setStoryPoints(5);
        story1.setTestPoints(0);


        myTeam.backlog = Arrays.asList(story1);


        assertEquals("Can't  delivery quality", true, myTeam.canDeliverQuality());
    }


    //Test Case:
    //   3 DEVs. 3 QAs
    //   4 story
    //
    //Expected: test success
    @Test
    public void CanDeliverQuality_4_test() {
        Team myTeam1 = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        Member memberDEV2 = new Member(Member.type.DEV);
        Member memberQA2 = new Member(Member.type.TEST);

        myTeam1.addMember(memberDEV1);
        myTeam1.addMember(memberQA1);
        myTeam1.addMember(memberDEV2);
        myTeam1.addMember(memberQA2);

        memberDEV1.setVelocity(1.0);
        memberQA1.setVelocity(1.0);

        memberDEV2.setVelocity(1.0);
        memberQA2.setVelocity(1.0);

        Story story1 = new Story();
        story1.setStoryPoints(1);
        story1.setTestPoints(5);

        Story story2 = new Story();
        story2.setStoryPoints(1);
        story2.setTestPoints(1);

        Story story3 = new Story();
        story3.setStoryPoints(1);
        story3.setTestPoints(1);

        Story story4 = new Story();
        story4.setStoryPoints(1);
        story4.setTestPoints(1);

        Story story5 = new Story();
        story5.setStoryPoints(1);
        story5.setTestPoints(1);

        myTeam1.backlog = Arrays.asList(story1, story2, story3, story4, story5);

        assertEquals("Can't delivery quality", true, myTeam1.canDeliverQuality());
    }

    @Test
    public void CanDeliverQuality_velocity_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberQA1);

        memberDEV1.setVelocity(1.2);
        memberQA1.setVelocity(1);

        Story story1 = new Story();
        story1.setStoryPoints(5);
        story1.setTestPoints(0);
        myTeam.backlog = Arrays.asList(story1);
        try {
            myTeam.canDeliverQuality();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Velocity can't be more than 1", e.getMessage());
        }


    }
    @Test
    public void CanDeliverQuality_SprintDays_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberQA1);
        memberDEV1.setVelocity(1);
        memberQA1.setVelocity(1);

        myTeam.sprintDays = 1;

        Story story1 = new Story();
        story1.setStoryPoints(1);
        story1.setTestPoints(1);
        myTeam.backlog = Arrays.asList(story1);
        try {
            myTeam.canDeliverQuality();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            Assert.assertEquals("Sprint should be at least two days long", e.getMessage());
        }


}
    @Test
    public void CanDeliverQuality_StoryPoints_test() {
        Team myTeam = new Team();
        Member memberDEV1 = new Member(Member.type.DEV);
        Member memberQA1 = new Member(Member.type.TEST);
        myTeam.addMember(memberDEV1);
        myTeam.addMember(memberQA1);
        memberDEV1.setVelocity(-1);
        memberQA1.setVelocity(1);

        myTeam.sprintDays = 10;

        Story story1 = new Story();
        story1.setStoryPoints(1);
        story1.setTestPoints(1);
        myTeam.backlog = Arrays.asList(story1);
        try {
            myTeam.canDeliverQuality();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            Assert.assertEquals("Velocity should be positive", e.getMessage());
        }


    }}


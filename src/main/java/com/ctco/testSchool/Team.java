package com.ctco.testSchool;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Member> members = new ArrayList<>();
    public List<Story> backlog;

    public int sprintDays = 10;

    public void addMember(Member member) {
        members.add(member);
    }

    public Double getTeamVelocity() {
        Double velocity = 0.0;
        for (Member m:members
        ) {velocity+=m.velocity*sprintDays;
        }
        return velocity;
    }

    public boolean canDo() {
        Double capacity = getTeamVelocity();
        for (Story s:backlog
        ) {capacity-=s.storyPoints;
        }
        return  capacity>=0;
    }

    /*
    Return true team has enough capacity to do both development ant testing (note tht testers only do testing and developers only do development)
    This method do not support cross-functional team members capable doing both.
     */
    public boolean canDeliverQuality() {
        validateInputs();
        for (Story story:backlog
        ) { if ((story.storyPoints+story.testPoints)>sprintDays) return false;
        }
        return  (canDeliver() && canTest());
    }

    private boolean canTest() {
        return members.stream()
                .filter(item -> item.testingSkills)
                .mapToDouble(i->i.velocity*sprintDays)
                .sum() >=  backlog.stream().mapToDouble(s->s.testPoints).sum();
    }

    /*
    Return true if developers could do the development part, ignoring the fact that there could be not enough testers
     */
    public boolean canDeliver() {
        return members.stream()
                .filter(item -> item.codinSkills)
                .mapToDouble(i->i.velocity*sprintDays)
                .sum() >=  backlog.stream().mapToDouble(s->s.storyPoints).sum();
    }

    /*
        Throws exception if invalid input, such as zero sprint days or any member velicity (also if velocity>1)
     */
    private void validateInputs() {
        if (sprintDays<2) throw new IllegalArgumentException("Sprint should be at least two days long");
        members.stream()
                .filter(item -> item.velocity<=0)
                .findAny()
                .ifPresent(a -> { throw new IllegalArgumentException("Velocity should be positive"); });
        members.stream()
                .filter(item -> item.velocity>1)
                .findAny()
                .ifPresent(a -> { throw new IllegalArgumentException("Velocity can't be more than 1"); });
    }

}
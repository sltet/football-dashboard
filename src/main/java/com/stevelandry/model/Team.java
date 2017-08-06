package com.stevelandry.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Team {

    private String name;
    private Set<Goal> goals;

    public boolean isEqual(Team team) {
        return this.name.equals(team.getName());
    }

    public void addGoal(Goal goal) {
        this.goals.add(goal);
    }

    public String printGoals() {
        if(goals.size() == 0) {
            return "";
        } else {
            String result = "(";
            for (Goal goal : goals) {
                result = result.concat(String.format("%s %d' ", goal.getScorerName(), goal.getMinutes()));
            }
            return result.concat(")");
        }

    }
}

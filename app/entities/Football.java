package entities;

import java.io.Serializable;

public class Football extends SportClub implements Comparable<Football>, Serializable {
    
    private int matchesWon;
    private int matchesLost;
    private int leaguePoints;
    private int totalLeagueGoals;
    private int goalDifference;

    public Football(String clubName, String clubLocation){
        super(clubName, clubLocation);
        this.leaguePoints = 0;
        this.matchesLost = 0;
        this.matchesWon = 0;
        this.totalLeagueGoals = 0;
        this.goalDifference = 0;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getTotalLeagueGoals() {
        return totalLeagueGoals;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public void setTotalLeagueGoals(int totalLeagueGoals) {
        this.totalLeagueGoals = totalLeagueGoals;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    @Override
    public String toString() {
        return super.getClubName();
    }

    @Override
    public int compareTo(Football arg0) {
        return this.leaguePoints - arg0.leaguePoints;
    }
}

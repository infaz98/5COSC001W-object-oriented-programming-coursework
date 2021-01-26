package entities;

import java.io.Serializable;

public class Match implements Serializable{
    
    private int teamOneGoals;
    private int teamTwoGoals;
    private SportClub teamOne;
    private SportClub teamTwo;
    private DateTime matchDate;
   
    public Match(SportClub teamOne, SportClub teamTwo, DateTime matchDate, int teamOneGoals, int teamTwoGoals){
        
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.matchDate = matchDate;
        this.teamOneGoals = teamOneGoals;
        this.teamTwoGoals = teamTwoGoals;

        Football footballClubOne = (Football) teamOne;
        footballClubOne.setTotalLeagueGoals(footballClubOne.getTotalLeagueGoals() + teamOneGoals);
        footballClubOne.setGoalDifference(footballClubOne.getTotalLeagueGoals() - teamTwoGoals);

        Football footballClubTwo = (Football) teamTwo;
        footballClubTwo.setTotalLeagueGoals(footballClubTwo.getTotalLeagueGoals() + teamTwoGoals);
        footballClubTwo.setGoalDifference(footballClubTwo.getTotalLeagueGoals() - teamOneGoals);

        if(teamOneGoals > teamTwoGoals){

            footballClubOne.setLeaguePoints(footballClubOne.getLeaguePoints() + 1);
            footballClubOne.setMatchesWon(footballClubOne.getMatchesWon() + 1);

        }else if (teamOneGoals < teamTwoGoals){

            footballClubTwo.setLeaguePoints(footballClubTwo.getLeaguePoints() + 1);
            footballClubTwo.setMatchesLost(footballClubTwo.getMatchesLost() + 1);

        }
    }
    
    public DateTime getMatchDate() {
        return matchDate;
    }


    public int getTeamOneGoals() {
        return teamOneGoals;
    }

    public int getTeamTwoGoals() {
        return teamTwoGoals;
    }

    public void setMatchDate(DateTime matchDate) {
        this.matchDate = matchDate;
    }

    public void setTeamOne(Football teamOne) {
        this.teamOne = teamOne;
    }

    public void setTeamTwo(Football teamTwo) {
        this.teamTwo = teamTwo;
    }

    public void setTeamOneGoals(int teamOneGoals) {
        this.teamOneGoals = teamOneGoals;
    }

    public void setTeamTwoGoals(int teamTwoGoals) {
        this.teamTwoGoals = teamTwoGoals;
    }

    public SportClub getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(SportClub teamOne) {
        this.teamOne = teamOne;
    }

    public SportClub getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(SportClub teamTwo) {
        this.teamTwo = teamTwo;
    }

    @Override
    public String toString() {
        return "Team One: " + teamOne + " Score: " + teamOneGoals +
        " Team Two: " + teamTwo + " Score: " + teamTwoGoals + " Match Date: " + matchDate;
    }
}                                                                                                                                                                                                                                                                                                                                                                               

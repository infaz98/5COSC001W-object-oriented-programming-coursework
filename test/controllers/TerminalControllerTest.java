package controllers;

import entities.*;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import services.FileHandlingService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TerminalControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    String clubOneName = "Liverpool";
    String clubOneLocation = "London";
    String clubTwoName = "Chelsea";
    String clubTwoLocation = "London";

    String falseClubName = "FalseClubName";

    SportClub sportClubOne = new Football(clubOneName, clubOneLocation);
    SportClub sportClubTwo = new Football(clubTwoName, clubTwoLocation);

    DateTime dateTime = new DateTime(12, 2,2021);

    Match match = new Match(sportClubOne, sportClubTwo,dateTime,1,3);

    @Test
    public void addClub(){
        String clubName = "Chelsea";
        String clubLocation = "London";
        SportClub sportClub = new Football(clubName, clubLocation);
        String message = premierLeagueManager.addSportClub(sportClub);
        assertEquals(sportClub.getClubName() + " added to the league successfully\n", message);
    }

    @Test
    public void tryToAddExistingClub(){
        premierLeagueManager.addSportClub(this.sportClubOne);
        String message = premierLeagueManager.addSportClub(this.sportClubOne);
        assertEquals("Could not add the Club to the League\n", message);
    }

    @Test
    public void removeClub(){
        String message = premierLeagueManager.removeSportClub(clubOneName);
        assertEquals(clubOneName + " has been removed successfully\n",message);
    }

    @Test
    public void removeNotExistingClub(){
        String message = premierLeagueManager.removeSportClub(this.falseClubName);
        assertEquals("Could not remove " + this.falseClubName + "\n",message);
    }

    @Test
    public void addMatch(){
        String message = premierLeagueManager.addMatch(match);
        assertEquals("{ Team One: " + this.match.getTeamOne() + " Score: " + this.match.getTeamOneGoals() + " Team Two: " +
                this.match.getTeamTwo() + " Score: "+ this.match.getTeamTwoGoals() +" Match Date: "+ this.dateTime + " } added successfully\n",message);
    }

    @Test
    public void saveLeagueInformation(){
        premierLeagueManager.addSportClub(sportClubOne);
        premierLeagueManager.addMatch(match);
        FileHandlingService.saveLeagueInformation(premierLeagueManager.getFootballClub(), premierLeagueManager.getLeagueMatches());
    }

    @Test
    public void loadLeagueInformation() throws IOException {
        premierLeagueManager.addSportClub(sportClubOne);
        premierLeagueManager.addMatch(match);
        FileHandlingService.saveLeagueInformation(premierLeagueManager.getFootballClub(), premierLeagueManager.getLeagueMatches());
        FileHandlingService.load();
    }


}




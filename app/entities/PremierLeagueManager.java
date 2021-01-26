package entities;

import utils.ApplicationUtils;

import java.util.ArrayList;

/**
 * <b>assumption</b> - there can be only one premier league manager to manage
 * the premier league. used Singleton design pattern to implement the assumption.
 */

public class PremierLeagueManager implements ILeagueManager {

    private static PremierLeagueManager premierLeagueManager = new PremierLeagueManager();

    private PremierLeagueManager() {
    };

    public static PremierLeagueManager getInstance() {
        return premierLeagueManager;
    }

    private ArrayList<SportClub> footballClub = new ArrayList<>();
    private ArrayList<Match> leagueMatches = new ArrayList<>();

    public ArrayList<SportClub> getFootballClub() {
        return footballClub;
    }

    public void setFootballClub(ArrayList<SportClub> footballClub) {
        this.footballClub = footballClub;
    }

    public ArrayList<Match> getLeagueMatches() {
        return leagueMatches;
    }

    public void setLeagueMatches(ArrayList<Match> leagueMatches) {
        this.leagueMatches = leagueMatches;
    }

    @Override
    public String addSportClub(SportClub sportClub) {
        if(!ApplicationUtils.isClubAvailable(sportClub.getClubName())){
            footballClub.add(sportClub);
            return sportClub.getClubName() + " added to the league successfully\n";
        }else{
            return "Could not add the Club to the League\n";
        }
    }

    @Override
    public String removeSportClub(String clubName) {
        
        for(SportClub sportClub: footballClub){
            if(sportClub.getClubName().equals(clubName)){
                this.footballClub.remove(sportClub);
                return sportClub.getClubName() + " has been removed successfully\n";
            }   
        }
        return "Could not remove " + clubName + "\n";
    }

    @Override
    public String addMatch(Match match) {
        this.leagueMatches.add(match);
        return "{ " + match + " } added successfully\n";
    }
}
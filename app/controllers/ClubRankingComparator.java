package controllers;

import entities.*;

import java.util.Comparator;

public class ClubRankingComparator implements Comparator<SportClub>{

    @Override
    public int compare(SportClub arg0, SportClub arg1) {
        Football footballOne = (Football) arg0;
        Football footballTwo = (Football) arg1;

        if((footballOne.getLeaguePoints() - footballTwo.getLeaguePoints()) != 0){
            return footballTwo.getLeaguePoints() - footballOne.getLeaguePoints();
        }else{
            return footballTwo.getGoalDifference() - footballOne.getGoalDifference();
        }

    }
    
}

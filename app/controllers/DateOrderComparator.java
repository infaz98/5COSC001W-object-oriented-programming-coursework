package controllers;

import entities.*;

import java.util.Comparator;

public class DateOrderComparator implements Comparator<Match> {

    @Override
    public int compare(Match matchOne, Match matchTwo) {

        if((matchOne.getMatchDate().getDate() - matchTwo.getMatchDate().getDate()) != 0){
            return matchOne.getMatchDate().getMonth() - matchTwo.getMatchDate().getMonth();
        }else{
            return matchOne.getMatchDate().getDate() - matchTwo.getMatchDate().getDate();
        }
    }
}

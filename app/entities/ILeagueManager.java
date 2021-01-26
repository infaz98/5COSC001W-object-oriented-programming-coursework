package entities;

import entities.Match;
import entities.SportClub;

public interface ILeagueManager {

/**
 * Add a sport club to the league
 * @param sportClub Sport club needed to add to the list
 * @return String message of added club
 */    
String addSportClub(SportClub sportClub);

/**
 * Remove a sport club from the league
 * @param clubName Sport club needed to remove from the list
 * @return String message of removed club
 */
String removeSportClub(String clubName);

/**
 * Add a match to the league matches
 * @param match Match needed to add to the match list
 * @return String message of added match
 */
String addMatch(Match match);

}

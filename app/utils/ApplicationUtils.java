package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entities.PremierLeagueManager;
import entities.DateTime;
import entities.Football;
import entities.Match;
import entities.SportClub;
import play.libs.Json;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Utils class contains the statics methods that is used for methods 
 * that is required to prompt user input etc. 
 */
public class ApplicationUtils {

    public static Scanner scanner = new Scanner(System.in);


    /**
     * prompt for user input and return a football object based on the input
     * @return Football club object 
     */
    public static SportClub collectingClubInformation(){

        SportClub sportClub;
        String clubName, clubLocation;
        
        System.out.print("Please enter club name: ");
        clubName = scanner.nextLine();

        while(clubName.equals("")){
            System.out.println("Invalid Name !");

            System.out.print("Please enter club name: ");
            clubName = scanner.nextLine();
        }


        System.out.print("Please enter club location: ");
        clubLocation = scanner.nextLine();

        while(clubLocation.equals("")){
            System.out.println("Invalid Club Location !");

            System.out.print("Please enter club location: ");
            clubLocation = scanner.nextLine();
        }
        sportClub = new Football(clubName, clubLocation);
        return sportClub;
    }
    


    /**
     * prompt for user input and return a match object based on the input
     * @return Match object
     */
    public static Match collectingMatchInformation(){

        Match match;
        SportClub sportClubOne, sportClubTwo;
        int date, month, year;
        DateTime matchDate;
        int teamOneGoals, teamTwoGoals;

        PremierLeagueManager premierLeagueManager  = PremierLeagueManager.getInstance();

        for(SportClub sportClub: premierLeagueManager.getFootballClub()){
            System.out.println(premierLeagueManager.getFootballClub().indexOf(sportClub)+ " - " + sportClub);
        }

        System.out.print("Please Select a Club (Club ID): ");
        sportClubOne = premierLeagueManager.getFootballClub().get(scanner.nextInt());
        
        for(SportClub sportClub: premierLeagueManager.getFootballClub()){
            if(!sportClub.equals(sportClubOne)){
                System.out.println(premierLeagueManager.getFootballClub().indexOf(sportClub)+ " - " + sportClub);
            }
        }
        System.out.print("Please Select a Club (Club ID): ");
        sportClubTwo = premierLeagueManager.getFootballClub().get(scanner.nextInt());
        
        System.out.print("Please enter the goals scored by " + sportClubOne.getClubName()+ ":" );
        teamOneGoals = scanner.nextInt();

        while(sportClubOne.equals(sportClubTwo)){
            System.out.println("Select two different teams !");    
            for(SportClub sportClub: premierLeagueManager.getFootballClub()){
                if(!sportClub.equals(sportClubOne)){
                    System.out.println(premierLeagueManager.getFootballClub().indexOf(sportClub)+ " - " + sportClub);
                }
            }
            System.out.print("Please Select a Club (Club ID): ");
            sportClubTwo = premierLeagueManager.getFootballClub().get(scanner.nextInt());    
        } 

        System.out.print("Please enter the goals scored by " + sportClubTwo.getClubName()+ ":" );
        teamTwoGoals = scanner.nextInt();

        System.out.print("Please enter match date: ");
        date = scanner.nextInt();

        System.out.print("Please enter match month: ");
        month = scanner.nextInt();
                
        System.out.print("Please enter match year: ");
        year = scanner.nextInt();

        matchDate = new DateTime(date, month, year);

        match = new Match(sportClubOne, sportClubTwo, matchDate, teamOneGoals, teamTwoGoals);
        return match;
    }

    /**
     * Return the sport club based on given name
     * @param clubName string value of the club name
     * @return sport club object
     */
    public static SportClub findClubByName(String clubName){
        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
        SportClub sportClub = null;
        for(SportClub football: premierLeagueManager.getFootballClub()){
            if(football.getClubName().equals(clubName)){
                sportClub = football;
            }
        }
        return sportClub;
    }

    public static ObjectNode createResponse(Object response, boolean ok) {
        ObjectNode result = Json.newObject();
        result.put("status", ok);
        if (response instanceof String)
            result.put("response", (String) response);
        else result.set("response", (JsonNode) response);

        return result;
    }

    public static Match randomMatch() {

        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
        ArrayList<SportClub> footballClubs = premierLeagueManager.getFootballClub();

        int min = 0;
        int max = footballClubs.size() - 1;

        int teamOne = (int)(Math.random() * (max - min + 1) + min);
        int teamTwo = (int)(Math.random() * (max - min + 1) + min);

        while (teamTwo == teamOne){
            if(teamOne == teamTwo){
                teamTwo = (int)(Math.random() * (max - min + 1) + min);
            }
        }

        int teamOneScore = (int)(Math.random() * (3) + min);
        int teamTwoScore = (int)(Math.random() * (3) + min);

        int date = (int) (Math.random() * (31 - 1 + 1) + 1);
        int month = (int) (Math.random() * (12 - 1 + 1) + 1);
        int year = 2021;

        DateTime dateTime = new DateTime(date, month, year);

        Match match = new Match(footballClubs.get(teamOne),footballClubs.get(teamTwo), dateTime, teamOneScore, teamTwoScore);
        return match;
    }

    public static boolean isClubAvailable(String clubName){
        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
        boolean isClubAvailable = false;
        for(SportClub football: premierLeagueManager.getFootballClub()){
            if(football.getClubName().equalsIgnoreCase(clubName)){
                isClubAvailable = true;
            }
        }
        return isClubAvailable;
    }

}

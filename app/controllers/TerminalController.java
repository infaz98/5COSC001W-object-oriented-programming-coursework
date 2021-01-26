package controllers;

import entities.Football;
import entities.Match;
import entities.PremierLeagueManager;
import entities.SportClub;
import services.FileHandlingService;
import utils.ApplicationUtils;

import java.io.IOException;
import java.util.Scanner;

public class TerminalController {

    public static void main(String[] args) throws IOException {
        terminalMenu();
    }

    public static void terminalMenu() throws IOException {

        //loading league information at the start of the program
        FileHandlingService.load();

        //calling premierLeagueManager reference to manage console base operations
        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

        //String object to display the result of the terminal operations
        String message;


        ClubRankingComparator clubRankingComparator = new ClubRankingComparator();

        String userInput;
        Scanner scanner = new Scanner(System.in);

        do{

            //Application menu
            System.out.println("\n------------------------------------------------------ \n\t\t   Premier League 2021" +
                    "\n------------------------------------------------------ \n");
            System.out.println("1.\t Add Club to the League\n2.\t Delete Club from the League\n3.\t Premier League Points Table\n"+
                    "4.\t Display League Clubs Information\n5.\t Add a Match\n6.\t Launch GUI Interface\nQ\t to exit\n"+
                    "\n* League information are updating automatically"+ "\n------------------------------------------------------ \n"
                    );
            System.out.print("Please select an option: ");


            userInput = scanner.nextLine();
            switch (userInput){
                case "1":
                    FileHandlingService.load();
                    message = premierLeagueManager.addSportClub(ApplicationUtils.collectingClubInformation());
                    FileHandlingService.saveLeagueInformation(premierLeagueManager.getFootballClub(), premierLeagueManager.getLeagueMatches());
                    System.out.println(message);
                    break;

                case "2":
                    System.out.print("Please enter the Football club name: ");
                    String clubName = scanner.nextLine();
                    if(ApplicationUtils.isClubAvailable(clubName)){
                        System.out.print("Press 'Y' to delete the " + clubName + " from League of press 'N' to Cancel: ");
                        String confirmation = scanner.nextLine();
                        if(confirmation.equals("Y") || confirmation.equals("y")){
                            message = premierLeagueManager.removeSportClub(clubName);
                            System.out.println(message);
                        }else if (confirmation.equals("n") || confirmation.equals("N")){
                            System.out.println("Operation canceled");
                        }else {
                            System.out.println("Invalid Selection");
                        }
                    }else{
                        System.out.println("Couldn't delete the Club\n");
                    }
                    FileHandlingService.saveLeagueInformation(premierLeagueManager.getFootballClub(), premierLeagueManager.getLeagueMatches());
                    break;

                case "3":
                    FileHandlingService.load();
                    premierLeagueManager.getFootballClub().sort(clubRankingComparator);
                    System.out.println("-------------------------------------------------------------------------------");
                    for(SportClub sportClub: premierLeagueManager.getFootballClub()){
                        Football football = (Football) sportClub;
                        System.out.format(" %s \t\tMatches Won:\t"+football.getMatchesWon()
                                +"\tMatches Lost:\t"+football.getMatchesLost()+"\tLeague Points:\t"+football.getLeaguePoints(), sportClub.getClubName());
                        System.out.println();
                    }
                    System.out.println("-------------------------------------------------------------------------------");
                    break;

                case "4":
                    System.out.print("Please enter the Football club name: ");
                    String footballClubName = scanner.nextLine();

                    if(ApplicationUtils.isClubAvailable(footballClubName)){
                        try{
                            SportClub sportClub = ApplicationUtils.findClubByName(footballClubName);
                            System.out.format("\nSport Club Name: %s " +"\n"+"Club Location: %s ", sportClub.getClubName(), sportClub.getClubLocation());
                            Football football  = (Football) sportClub;
                            System.out.format("\nMatches Won: %d \nMatches lost: %d \nLeague Points: %d \n", football.getMatchesWon(), football.getMatchesLost(), football.getLeaguePoints());
                        }catch (NullPointerException e){
                            System.out.println("Couldn't find the Club\n");
                        }
                    }else{
                        System.out.println("Couldn't find the Club\n");
                    }
                    break;

                case "5":
                    Match math = ApplicationUtils.collectingMatchInformation();
                    message = premierLeagueManager.addMatch(math);
                    System.out.println(message);
                    FileHandlingService.saveLeagueInformation(premierLeagueManager.getFootballClub(), premierLeagueManager.getLeagueMatches());
                    break;

                case "6":
                    Runtime runtime = Runtime.getRuntime();
                    String url = "http://localhost:4200/";
                    runtime.exec("rundll32 url.dll,FileProtocolHandler "+url);
                    break;

                default:
                    if(!userInput.equals("Q")){
                        System.out.println("Invalid selection\n");
                    }
            }
        }while (!userInput.equals("Q"));
    }
}


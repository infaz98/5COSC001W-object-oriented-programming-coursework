package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.FileHandlingService;
import utils.ApplicationUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles all the API requests of premier league manager
 */

public class APIController extends Controller {

    PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    public Result listClubs() throws IOException {

        FileHandlingService.load();
        ArrayList<SportClub> result = premierLeagueManager.getFootballClub();

        ClubRankingComparator clubRankingComparator = new ClubRankingComparator();
        premierLeagueManager.getFootballClub().sort(clubRankingComparator);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.convertValue(result,JsonNode.class);
        return ok(jsonNode);
    }

    public Result listMatches() throws IOException {

        FileHandlingService.load();
        ArrayList<Match> result = premierLeagueManager.getLeagueMatches();

        DateOrderComparator dateOrderComparator = new DateOrderComparator();
        premierLeagueManager.getLeagueMatches().sort(dateOrderComparator);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.convertValue(result, JsonNode.class);
        return ok(jsonNode);
    }

    public Result sendRandomMatch(){

        Match match = ApplicationUtils.randomMatch();
        premierLeagueManager.addMatch(match);

        FileHandlingService.saveLeagueInformation(premierLeagueManager.getFootballClub(), premierLeagueManager.getLeagueMatches());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.convertValue(match,JsonNode.class);
        return ok(jsonNode);

    }

    //This method can be used if need to add a match through client side
    public Result addMatch(Http.Request request){
        JsonNode json = request.body().asJson();

        if(json == null){
            return badRequest(ApplicationUtils.createResponse("Could not complete",false));
        }else {
            SportClub sportClubOne = ApplicationUtils.findClubByName(json.get("teamOne").asText());
            SportClub sportClubTwo = ApplicationUtils.findClubByName(json.get("teamTwo").asText());
            int teamOneGoals = json.get("teamOneGoals").asInt();
            int teamTwoGoals = json.get("teamTwoGoals").asInt();

            int matchDate = json.get("matchDate").asInt();
            int matchMonth = json.get("matchMonth").asInt();
            int matchYear = json.get("matchYear").asInt();

            DateTime matchDay = new DateTime(matchDate, matchMonth, matchYear);
            Match match = new Match(sportClubOne, sportClubTwo, matchDay, teamOneGoals, teamTwoGoals);
            String message = premierLeagueManager.addMatch(match);
            return ok(ApplicationUtils.createResponse(message,true));
        }
    }

    //This method can be used if need to add a sport club through client side
    public Result addClub(Http.Request request){

        JsonNode json = request.body().asJson();

        if(json == null){
            return badRequest(ApplicationUtils.createResponse("Could not complete",false));
        }else {
            SportClub sportClub = Json.fromJson(json, Football.class);
            String message = premierLeagueManager.addSportClub(sportClub);
            return ok(ApplicationUtils.createResponse(message,true));
        }
    }
}
   
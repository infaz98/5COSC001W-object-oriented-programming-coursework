package services;

import entities.PremierLeagueManager;
import entities.Match;
import entities.SportClub;
import java.io.*;
import java.util.ArrayList;

public class FileHandlingService {

    /**
     * saving premier league matches and clubs into a file in '.ser' format 
     * @param sportClubs getting list of clubs playing in the league as parameter
     * @param matches getting list of played matches in the league as parameter
     */
    public static void saveLeagueInformation(ArrayList<SportClub> sportClubs, ArrayList<Match> matches){
        try{
            FileOutputStream writeData;
            ObjectOutputStream writeStream;

            writeData = new FileOutputStream("clubs.ser");
            writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(sportClubs);

            writeData = new FileOutputStream("matches.ser");
            writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(matches);

            writeStream.flush();
            writeStream.close();
            writeData.flush();
            writeData.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * load premier league clubs and matches information into premier league managers' instances
     */
    public static void load() throws IOException {
        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        while (true){
            try{
                fileInputStream = new FileInputStream("clubs.ser");
                objectInputStream = new ObjectInputStream(fileInputStream);
                ArrayList<SportClub> footballClub = premierLeagueManager.getFootballClub();

                footballClub = (ArrayList<SportClub>) objectInputStream.readObject();
                premierLeagueManager.setFootballClub(footballClub);

                fileInputStream = new FileInputStream("matches.ser");
                objectInputStream = new ObjectInputStream(fileInputStream);

                ArrayList<Match> matches = premierLeagueManager.getLeagueMatches();

                matches = (ArrayList<Match>) objectInputStream.readObject();
                premierLeagueManager.setLeagueMatches(matches);

                objectInputStream.close();
                fileInputStream.close();
                break;
            }catch (EOFException | ClassNotFoundException | FileNotFoundException e){
                break;
            }
        }
    }
}

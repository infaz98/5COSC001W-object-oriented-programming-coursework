package entities;

import java.io.Serializable;

public abstract class SportClub implements Serializable{
    
   private String clubName;
   private String clubLocation;

   /**
    * set the club name and the location
    * @param clubName name of the sport club
    * @param clubLocation location of the sport club
    */
    public SportClub(String clubName, String clubLocation){
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }


   /**
    * @return name of the sport club
    */
   public String getClubName() {
    return this.clubName;
   }

    /**
     * @return location of the sport club
     */
   public String getClubLocation() {
       return this.clubLocation;
   }
}

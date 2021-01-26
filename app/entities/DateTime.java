package entities;
import java.io.Serializable;

public class DateTime implements Comparable<DateTime>, Serializable{
    
    private int date;
    private int month;
    private int year;

    public DateTime(int date, int month, int year){
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.valueOf(date+"/"+month+"/"+year);
    }

    @Override
    public int compareTo(DateTime dateTime) {
        return this.date - dateTime.date;
    }
}

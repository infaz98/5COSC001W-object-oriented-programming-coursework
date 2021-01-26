package entities;

public class UniversityFootballClub {

    private String universityName;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "UniversityFootballClub{" +
                "universityName='" + universityName + '\'' +
                '}';
    }
}

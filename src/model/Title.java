package model;

import com.google.gson.annotations.SerializedName;
import exception.YearConversionException;
import model.dto.TitleOmdb;

public class Title implements Comparable<Title> {
//    @SerializedName("Title")  // --> Defining and using the model record TitleOmdb, don't need to declare @SerializedName
    private final String title;
//    @SerializedName("Year")
    private final int releaseYear;
    private boolean isIncludedInPlan;
    private double sumOfRates;
    private int numOfRates;
    private int durationInMinutes;

    public Title(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public Title(TitleOmdb myTitleOmdb) {
        this.title = myTitleOmdb.title();
        if(myTitleOmdb.year().length() > 4){
            throw new YearConversionException("Error: year length is too long");
        }
        this.releaseYear = Integer.parseInt(myTitleOmdb.year());
        this.durationInMinutes = Integer.parseInt(myTitleOmdb.runtime().substring(0, myTitleOmdb.runtime().indexOf(" ")));
    }

    public String getTitle() {
        return this.title;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public boolean isIncludedInPlan() {
        return this.isIncludedInPlan;
    }

    public int getDurationInMinutes() {
        return this.durationInMinutes;
    }

    public int getNumOfRates() {
        return this.numOfRates;
    }

    public void setIncludedInPlan(boolean includedInPlan) {
        this.isIncludedInPlan = includedInPlan;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void displayInfo(){
        System.out.println("Title: " + title +
                            ", release year: " + releaseYear +
                            ", duration (min): " + getDurationInMinutes());
    }

    public void sendRate(double rate){
        sumOfRates += rate;
        numOfRates++;
    }

    public double getAverageRate(){
        return sumOfRates/numOfRates;
    }

    @Override
    public int compareTo(Title otherTitle) {
        return this.getTitle().compareTo(otherTitle.getTitle());
    }

    @Override
    public String toString() {
        return this.getTitle() + " (" + this.getReleaseYear() + ") | " + this.getDurationInMinutes() + " min";
    }
}

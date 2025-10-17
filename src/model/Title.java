package model;


public class Title implements Comparable<Title> {
    private final String title;
    private final int releaseYear;
    private boolean isIncludedInPlan;
    private double sumOfRates;
    private int numOfRates;
    private int durationInMinutes;

    public Title(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
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
        return this.getTitle() + " (" + this.getReleaseYear() + ")";
    }
}

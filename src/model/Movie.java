package model;

import calculus.Classify;

public class Movie extends Title implements Classify {
    private String director;

    public Movie(String title, int releaseYear) {
        super(title, releaseYear);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int getFinalRate() {
        return (int) getAverageRate()/2;
    }

    @Override
    public String toString() {
        return "Movie: " + super.toString();
    }
}

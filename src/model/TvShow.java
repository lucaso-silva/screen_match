package model;

import calculus.Classify;

public class TvShow extends Title {
    private int season;
    private boolean active;
    private int numEpisodesPerSeason;
    private int minutesPerEpisode;

    public TvShow(String title, int releaseYear) {
        super(title, releaseYear);
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getNumEpisodesPerSeason() {
        return numEpisodesPerSeason;
    }

    public void setNumEpisodesPerSeason(int numEpisodesPerSeason) {
        this.numEpisodesPerSeason = numEpisodesPerSeason;
    }

    public int getMinutesPerEpisode() {
        return minutesPerEpisode;
    }

    public void setMinutesPerEpisode(int minutesPerEpisode) {
        this.minutesPerEpisode = minutesPerEpisode;
    }

    @Override
    public int getDurationInMinutes() {
        return this.numEpisodesPerSeason * this.minutesPerEpisode * this.season;
    }

    @Override
    public String toString() {
        return "TV Show: " + super.toString();
    }
}

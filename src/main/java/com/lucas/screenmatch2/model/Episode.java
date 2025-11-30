package com.lucas.screenmatch2.model;

import jakarta.persistence.*;

import java.time.DateTimeException;
import java.time.LocalDate;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer episode;
    private Double rate;
    private LocalDate dateRelease;
    @ManyToOne
    private Series series;

    public Episode() {}

    public Episode(Integer season, DataEpisode dataEpisode){
        this.season = season;
        this.title = dataEpisode.title();
        this.episode = dataEpisode.episode();
        try{
            this.rate = Double.valueOf(dataEpisode.rate());
        } catch (NumberFormatException e){
            this.rate = 0.0;
        }
        try{
            this.dateRelease = LocalDate.parse(dataEpisode.dateRelease());
        }catch (DateTimeException e){
            this.dateRelease = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", episode=" + episode +
                ", rate=" + rate +
                ", dateRelease=" + dateRelease;
    }
}

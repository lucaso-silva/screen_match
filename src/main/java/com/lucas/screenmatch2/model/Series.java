package com.lucas.screenmatch2.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private  String title;
    @Enumerated(EnumType.STRING)
    private  Category genre;
    private String actors;
    private String plot;
    private Integer totalSeasons;
    private Double rate;
    private String poster;
    @Transient
    private List<Episode> episodes = new ArrayList<>();

    public Series() {
    }

    public Series(DataTvShow dataTvShow) {
        this.title = dataTvShow.title();
        this.genre = Category.fromString(dataTvShow.genre().split(",")[0].trim());
        this.actors = dataTvShow.actors();
        this.plot = dataTvShow.plot();
        this.totalSeasons = dataTvShow.totalSeasons();
        this.rate = OptionalDouble.of(Double.parseDouble(dataTvShow.rate())).orElse(0.0);
        this.poster = dataTvShow.poster();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", genre=" + genre +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", rate=" + rate +
                ", poster='" + poster;
    }
}

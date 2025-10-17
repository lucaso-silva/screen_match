package main;

import calculus.LengthCalculator;
import calculus.SuggestFilter;
import model.Episode;
import model.Movie;
import model.TvShow;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie("Java in the Alura Verso", 2025);
        TvShow tvShow = new TvShow("Game of Thrones", 2011);

        movie.setDurationInMinutes(144);
        movie.setIncludedInPlan(true);

        movie.sendRate(9.5);
        movie.sendRate(9);
        movie.sendRate(9.2);

        movie.displayInfo();
        System.out.println("num of rates: " + movie.getNumOfRates() + " | Included in the plan: " + movie.isIncludedInPlan());
        System.out.printf("Avg rate: %.2f \n", movie.getAverageRate());

        tvShow.setSeason(8);
        tvShow.setNumEpisodesPerSeason(10);
        tvShow.setMinutesPerEpisode(50);

        tvShow.displayInfo();
        System.out.println("num of rates: " + tvShow.getNumOfRates() + " | Included in the plan: " + tvShow.isIncludedInPlan());
        System.out.println("Time need to watch the whole TV Show: " + tvShow.getDurationInMinutes());

        Movie otherMovie = new Movie("Reservoir Dog", 1992);
        otherMovie.setDurationInMinutes(100);

        LengthCalculator myCalculator = new LengthCalculator();
        myCalculator.includeTitleToQueue(movie);
        myCalculator.includeTitleToQueue(tvShow);
        myCalculator.includeTitleToQueue(otherMovie);
        System.out.println("Total length to watch all titles: " + myCalculator.getTotalLength());

        SuggestFilter filter = new SuggestFilter();
        filter.filter(movie);

        Episode gotEp = new Episode();
        gotEp.setTitle("Winter is Coming");
        gotEp.setEpisodeId(1);
        gotEp.setTvShow(tvShow);
        for(int i=0; i<100; i++){
            gotEp.play();
        }
        filter.filter(gotEp);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(otherMovie);

        System.out.println("Movie list size: " + movies.size());
        System.out.println(movies);
    }
}

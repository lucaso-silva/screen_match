package main;

import model.Movie;
import model.Title;
import model.TvShow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainLists {
    public static void main(String[] args) {
        var movie1 = new Movie("Bacurau", 2019);
        movie1.sendRate(9.3);
        var movie2 = new Movie("The Box", 2009 );
        movie2.sendRate(5);
        var tvShow1 = new TvShow("Game of Thrones", 2011);
        var movie3 = new Movie("Anora", 2024);
        movie3.sendRate(7);

        ArrayList<Title> watchList = new ArrayList<>();
        watchList.add(movie1);
        watchList.add(movie2);
        watchList.add(movie3);
        watchList.add(tvShow1);

        for (Title title : watchList) {
            System.out.println(title.getTitle());

//            if(title instanceof Movie movie) {
//                System.out.println("Rate: " + movie.getFinalRate());
//            }

            if(title instanceof Movie movie && movie.getFinalRate() > 2) {
                System.out.println("good rate: " + movie.getFinalRate());
            }
        }

        ArrayList<String> artists = new ArrayList<>();
        artists.add("Leonardo DiCaprio");
        artists.add("Emma Stone");
        artists.add("Adam Sandler");

        System.out.println(artists);
        Collections.sort(artists);
        System.out.println("After sorting: \n" + artists);

        System.out.println(watchList);
        Collections.sort(watchList); //by using Collections.sort I need to implement Comparable and define with field I want to compare and sort
        System.out.println("After sorting by title: \n" + watchList);

        //I also can use sort directly in a List, without using the superclass Collections, so I need to pass as parameter the Comparator.comparing , and then specify what I want to compare and sort
        watchList.sort(Comparator.comparing(Title::getReleaseYear));
        System.out.println("After sorting by year: \n" + watchList);
    }
}

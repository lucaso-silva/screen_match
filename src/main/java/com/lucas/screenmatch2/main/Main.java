package com.lucas.screenmatch2.main;

import com.lucas.screenmatch2.model.*;
import com.lucas.screenmatch2.repository.SeriesRepository;
import com.lucas.screenmatch2.service.ConsumeAPI;
import com.lucas.screenmatch2.service.DataConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private Scanner in = new Scanner(System.in);
    private final String API_KEY = "&apikey=" + getApiKey();
    private final String URL = "https://www.omdbapi.com/?t=";
    private final ConsumeAPI consumeAPI = new ConsumeAPI();
    private final DataConverter dataConverter = new DataConverter();
//    private List<DataTvShow> tvShows = new  ArrayList<>();
    private final SeriesRepository seriesRepository;
    private List<Series> seriesList = new  ArrayList<>();
    private Optional<Series> seriesSearched;

    public Main(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public void displayMenu(){
        var menu = """
                ------------------------------------------------
                ***** Select one of the following options: *****
                ------------------------------------------------
                     1 - Search TV Shows
                     2 - Search Episodes
                     3 - Display TV Shows
                     4 - Search TV Show by Title
                     5 - Find TV Shows by Actor and Rate
                     6 - Find Top 5 Series
                     7 - Search by category
                     8 - Search by number of Seasons and Rate
                     9 - Search Episode by Title Keyword
                    10 - Search Top 5 Episodes
                    11 - Search Episodes From Year
                ------------------------------------------------
                     0 - Exit
                ------------------------------------------------
                """;

        int option;

        do{
            System.out.println(menu);

            option = in.nextInt();
            in.nextLine();
            switch (option) {
                case 1:
                    searchTVShow();
                    break;
                case 2:
                    searchEpisodes();
                    break;
                case 3:
                    displaySavedTvShows();
                    break;
                case 4:
                    findSeriesByTitle();
                    break;
                case 5:
                    findSeriesByActor();
                    break;
                case 6:
                    findTop5Series();
                    break;
                case 7:
                    searchByCategory();
                    break;
                case 8:
                    searchByNumSeasonsAndRate();
                    break;
                case 9:
                    searchEpisodeByTitleKeyword();
                    break;
                case 10:
                    displayTop5Episodes();
                    break;
                case 11:
                    searchEpisodesFromYear();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(option != 0);

    }

    private void displaySavedTvShows() {
//        List<Series> series = new ArrayList<>();
//        series = tvShows.stream()
//                .map(Series::new)
//                .toList();
        seriesList = seriesRepository.findAll();

        seriesList.stream()
                .sorted(Comparator.comparing(Series::getTitle))
                .forEach(System.out::println);
    }

    private DataTvShow getDataTvShow(){
        System.out.println("Enter the TV Show name: ");
        var tvShowName = in.nextLine();

        var data = consumeAPI.getData(URL + tvShowName.replace(" ", "+") + API_KEY);
        return dataConverter.getData(data, DataTvShow.class);
    }

    private void searchEpisodes() {
//        DataTvShow tvShow = getDataTvShow();
        displaySavedTvShows();
        System.out.println("Choose a Series to get episodes: ");
        var seriesName = in.nextLine();

//        Optional<Series> series = seriesList.stream()
//                .filter(s -> s.getTitle().toLowerCase().contains(seriesName.toLowerCase()))
//                .findFirst();
        var series = seriesRepository.findSeriesByTitleContainingIgnoreCase(seriesName);

        if(series.isPresent()){
            var seriesFound = series.get();
            List<DataSeason> seasons = new ArrayList<>();

            for(int i=1; i<= seriesFound.getTotalSeasons(); i++){
                String url = URL + seriesFound.getTitle().replace(" ", "+") + "&Season=" + i + API_KEY;
                var data = consumeAPI.getData(url);
                DataSeason dataSeason = dataConverter.getData(data, DataSeason.class);
                seasons.add(dataSeason);
            }

            seasons.forEach(System.out::println);
            List<Episode> episodes = seasons.stream()
                    .flatMap(d -> d.episodes().stream()
                            .map(e -> new Episode(d.season(), e)))
                    .collect(Collectors.toList());

            seriesFound.setEpisodes(episodes);
            seriesRepository.save(seriesFound);

        }else{
            System.out.println("Series not found");
        }

    }

    private void searchTVShow() {
        var tvShow = getDataTvShow();
//        tvShows.add(tvShow);
        Series series = new Series(tvShow);

        seriesRepository.save(series);
        System.out.println(tvShow);
    }

    private void findSeriesByTitle() {
        System.out.println("Enter Series Title: ");
        var seriesName = in.nextLine();
        seriesSearched = seriesRepository.findSeriesByTitleContainingIgnoreCase(seriesName);

        if(seriesSearched.isPresent()){
            System.out.println(seriesSearched.get());
        }else{
            System.out.println("Series not found");
        }
    }

    private void findSeriesByActor() {
        System.out.println("Enter actor's name: ");
        var actorName = in.nextLine();
        System.out.println("Enter a minimum rate");
        var rate = in.nextDouble();
        List<Series> seriesFound = seriesRepository.findByActorsContainingIgnoreCaseAndRateGreaterThanEqual(actorName, rate);
        System.out.println("TV Shows with " + actorName + " and rate greater than or equal to " +  rate + ": ");
        seriesFound.forEach(s -> System.out.println(s.getTitle() + " - rate: " + s.getRate()));
    }

    private void findTop5Series(){
        List<Series> top5Series = seriesRepository.findTop5ByOrderByRateDesc();
        top5Series.forEach(s -> System.out.println(s.getTitle() + " - rate: " + s.getRate()));
    }

    private void searchByCategory() {
        System.out.println("Please enter the category you want to search: ");
        var category = in.nextLine();
        Category seriesCategory = Category.fromString(category);
        List<Series> seriesByCategory = seriesRepository.findByGenre(seriesCategory);
        seriesByCategory.forEach(System.out::println);
    }

    private void searchByNumSeasonsAndRate() {
        System.out.println("Enter the maximum number of seasons to search: ");
        var maxSeasons = in.nextInt();
        System.out.println("Enter the minimum rate to search: ");
        var minRate = in.nextDouble();
//        List<Series> seriesFound = seriesRepository.findByTotalSeasonsLessThanEqualAndRateGreaterThanEqual(maxSeasons, minRate);
        List<Series> seriesFound = seriesRepository.findBySeasonsAndRate(maxSeasons, minRate);
        System.out.println("TV Shows up to " + maxSeasons + " seasons and rate greater than " + minRate);
        seriesFound.forEach(s -> System.out.println(s.getTitle() + ", total seasons: " + s.getTotalSeasons() + " - rate: " + s.getRate()));
    }

    private void searchEpisodeByTitleKeyword(){
        System.out.println("Enter the title/part of the episode you want to search: ");
        var title = in.nextLine();
        List<Episode> episodesFound = seriesRepository.findEpisodeByTitleKeyword(title);
        episodesFound.forEach(e -> System.out.println(e.getSeries().getTitle() + ", " +
                "Season " + e.getSeason() +
                " - episode " + e.getEpisode() + ": " + e.getTitle()));
    }

    private void displayTop5Episodes() {
        findSeriesByTitle();
        if(seriesSearched.isPresent()){
            Series series = seriesSearched.get();
            List<Episode> episodes = seriesRepository.top5Episodes(series);
            episodes.forEach(e -> System.out.println(e.getTitle() +
                    " [Episode " + e.getEpisode() +
                    " - Season " + e.getSeason() +
                    "] - Rate: " + e.getRate()));
        }
    }

    private void searchEpisodesFromYear(){
        findSeriesByTitle();
        if(seriesSearched.isPresent()){
            Series series = seriesSearched.get();
                System.out.println("Enter the year from which episodes will be displayed: ");
            var startYear = in.nextInt();
            List<Episode> episodes = seriesRepository.findEpisodesFromAYear(series, startYear);
            episodes.forEach(System.out::println);
        }
    }


    private String getApiKey(){
        var props = new Properties();

        try(FileInputStream fis = new FileInputStream("config.properties")){
            props.load(fis);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        return props.getProperty("API_KEY_OMDb");
    }
}
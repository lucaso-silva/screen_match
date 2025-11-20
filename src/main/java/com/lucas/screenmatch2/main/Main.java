package com.lucas.screenmatch2.main;

import com.lucas.screenmatch2.model.DataSeason;
import com.lucas.screenmatch2.model.DataTvShow;
import com.lucas.screenmatch2.model.Series;
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
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private DataConverter dataConverter = new DataConverter();
    private List<DataTvShow> tvShows = new  ArrayList<>();

    public void displayMenu(){
        var menu = """
                ------------------------------------------------
                ***** Select one of the following options: *****
                ------------------------------------------------
                    1 - Search TV Shows
                    2 - Search Episodes
                    3 - Display TV Shows
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
                    displayTvShows();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while(option != 0);

    }

    private void displayTvShows() {
        List<Series> series = new ArrayList<>();
        series = tvShows.stream()
                .map(Series::new)
                .toList();

        series.stream()
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
        DataTvShow tvShow = getDataTvShow();
        List<DataSeason> seasons = new ArrayList<>();

        for(int i=1; i<= tvShow.totalSeasons(); i++){
            String url = URL + tvShow.title().replace(" ", "+") + "&Season=" + i + API_KEY;
            var data = consumeAPI.getData(url);
            DataSeason dataSeason = dataConverter.getData(data, DataSeason.class);
            seasons.add(dataSeason);
        }

        seasons.forEach(System.out::println);
    }

    private void searchTVShow() {
        var tvShow = getDataTvShow();
        tvShows.add(tvShow);
        System.out.println(tvShow);
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
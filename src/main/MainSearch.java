package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Title;
import model.dto.TitleOmdb;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;

import static com.google.gson.FieldNamingPolicy.*;
import static java.net.http.HttpResponse.*;

public class MainSearch {
    public static void main(String[] args) throws IOException, InterruptedException {

        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        }catch(IOException e) {
            e.printStackTrace();
        }

//        String API_KEY = "";
        String API_KEY = props.getProperty("API_KEY");

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the title of the movie ou TV-Show you are looking for: ");
        String movie = in.nextLine();
        movie = movie.trim().replace(" ", "+");

        String url = "https://www.omdbapi.com/?t="+movie+"&apikey="+API_KEY;

        // My first try
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://www.omdbapi.com/?t="+movie+"&apikey="+API_KEY))
//                .build();
//        client.sendAsync(request, BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(System.out::println)
//                .join();

        // IntelliJ suggested -> try-catch
//        try (HttpClient client = HttpClient.newHttpClient()) {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://www.omdbapi.com/?t=" + movie + "&apikey=" + API_KEY))
//                    .build();
//            client.sendAsync(request, BodyHandlers.ofString())
//                    .thenApply(HttpResponse::body)
//                    .thenAccept(System.out::println)
//                    .join();
//        }catch(Exception e) {
//            System.out.println(e.getMessage());
//        }

        // Alura implementation -> change client.sendAsync to the interface HttpResponse<String>
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String json = response.body();
        System.out.println("response json: " + json);

//        Gson gson = new Gson(); //Using this constructor, the fields title, year, and runtime are set to null, because the API Json object these fields are written with uppercase
        Gson gson = new GsonBuilder().setFieldNamingPolicy(UPPER_CAMEL_CASE)
                .create();

//        Title myTitle = gson.fromJson(json, Title.class);  //Need to use @SerializedName("")
//        System.out.println("Title: " + myTitle.getTitle());

        TitleOmdb myTitleOmdb = gson.fromJson(json, TitleOmdb.class);

//        Title myTitle = new Title(myTitleOmdb.title(), Integer.parseInt(myTitleOmdb.year()));
        Title myTitle = new Title(myTitleOmdb);

        System.out.println("title omdb: " + myTitleOmdb);
        System.out.println("title: " + myTitle);

    }
}

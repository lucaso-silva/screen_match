package com.lucas.screenmatch2.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTvShow(@JsonAlias("Title") String title,
                         @JsonAlias("Genre") String genre,
                         @JsonAlias("Actors") String actors,
                         @JsonAlias("Plot") String plot,
                         @JsonAlias("totalSeasons") Integer totalSeasons,
                         @JsonAlias("imdbRating") String rate,
                         @JsonAlias("Poster") String poster
) {
}

package com.lucas.screenmatch2.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer episode,
                          @JsonAlias("imdbRating") String rate,
                          @JsonAlias("Released") String dateRelease
                          ) {
}

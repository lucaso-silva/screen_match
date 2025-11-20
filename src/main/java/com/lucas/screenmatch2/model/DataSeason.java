package com.lucas.screenmatch2.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeason(@JsonAlias("Season") Integer season,
                         @JsonAlias("Episodes") List<DataEpisode> episodes
                         ) {
}

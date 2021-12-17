package com.anishare.animeservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Anime {

    private Long animeId;
    private String animeName;
    private String animeDesc;
    private int userRating;
    private double rating;
    private PosterImage posterImage;
    private Date startingDate;
    private String status;
    private String ageRating;
    private int episodeCount;

}

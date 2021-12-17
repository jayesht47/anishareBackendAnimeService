package com.anishare.animeservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PosterImage {

    private String tiny;
    private String small;
    private String medium;
    private String large;
    private String orignal;

}

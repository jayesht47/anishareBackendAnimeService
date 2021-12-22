package com.anishare.animeservice.service;


import com.anishare.animeservice.models.Anime;
import com.anishare.animeservice.models.PosterImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Service
@Slf4j
public class AnimeService {

    @Autowired
    RestTemplate restTemplate;

    public Anime getAnimeById(Long animeId) {

        HttpHeaders headers = new HttpHeaders();
        Anime anime = new Anime();

        headers.set("Accept","application/vnd.api+json");

        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange("https://kitsu.io/api/edge/anime/" + animeId + "?fields[anime]=titles,synopsis,averageRating,posterImage," +
                        "ageRating,status,startDate,episodeCount,slug",
                HttpMethod.GET,httpEntity, String.class);

        try {

            JSONObject animeData = new JSONObject(response.getBody() );

            log.info("Received Anime info :: "+animeData.toString());

            anime.setAnimeId(animeId);
            anime.setAnimeDesc(animeData.getJSONObject("data").getJSONObject("attributes").get("synopsis").toString());
            anime.setAnimeName(animeData.getJSONObject("data").getJSONObject("attributes").getJSONObject("titles").get("en").toString());
            anime.setRating(Double.parseDouble(animeData.getJSONObject("data").getJSONObject("attributes").get("averageRating").toString()));
            anime.setAgeRating(animeData.getJSONObject("data").getJSONObject("attributes").get("ageRating").toString());
            anime.setStatus(animeData.getJSONObject("data").getJSONObject("attributes").get("status").toString());
            anime.setEpisodeCount(Integer.parseInt(animeData.getJSONObject("data").getJSONObject("attributes").get("episodeCount").toString()));
            anime.setStartingDate(Date.valueOf(animeData.getJSONObject("data").getJSONObject("attributes").get("startDate").toString()));
            anime.setSlug(animeData.getJSONObject("data").getJSONObject("attributes").get("slug").toString());

            //Saving poster images with various sizes
            PosterImage posterImage = new PosterImage();
            posterImage.setTiny(animeData.getJSONObject("data").getJSONObject("attributes").getJSONObject("posterImage").get("tiny").toString());
            posterImage.setSmall(animeData.getJSONObject("data").getJSONObject("attributes").getJSONObject("posterImage").get("small").toString());
            posterImage.setMedium(animeData.getJSONObject("data").getJSONObject("attributes").getJSONObject("posterImage").get("medium").toString());
            posterImage.setLarge(animeData.getJSONObject("data").getJSONObject("attributes").getJSONObject("posterImage").get("large").toString());
            posterImage.setOrignal(animeData.getJSONObject("data").getJSONObject("attributes").getJSONObject("posterImage").get("original").toString());

            anime.setPosterImage(posterImage);



            log.info("Parsed Anime Object ::" + anime.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return anime;

    }
}

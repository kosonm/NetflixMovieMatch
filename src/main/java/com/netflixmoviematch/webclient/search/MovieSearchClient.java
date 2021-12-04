package com.netflixmoviematch.webclient.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflixmoviematch.entity.dto.MovieRatingDto;
import com.netflixmoviematch.model.MovieDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieSearchClient {

    private static final String API_KEY = "f7c25b0abdmshbd9c709e9a6e193p1dad39jsn8c54e7a2a92b";
    private static final String SEARCH_URL = "https://unogsng.p.rapidapi.com/search?";
    private final String results = "results";


    private RestTemplate restTemplate = new RestTemplate();

    // ordered by rating
    public MovieDto getMoviesByRating(int rating) {
        ResponseEntity<String> responseEntity = callGetMethod(SEARCH_URL + "newdate=2017-12-03&orderby=rating&start_rating="
                + rating
                + "&limit=100&audio=english&country_andorunique=unique&offset=0");

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MovieRatingDto movieRatingDto = null;

        try {
            JsonNode body = objectMapper.readTree(responseEntity.getBody()).get(results).get(0);
            movieRatingDto = objectMapper.readValue(body.toString(), MovieRatingDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return MovieDto.builder()
                .imdbRating(movieRatingDto.getImdbRating())
                .synopsis(movieRatingDto.getSynopsis())
                .title(movieRatingDto.getTitle())
                .year(movieRatingDto.getYear())
                .build();
    }

    private ResponseEntity<String> callGetMethod(String url) {
        final HttpHeaders headers = getHttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    private HttpHeaders getHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "unogsng.p.rapidapi.com");
        headers.set("x-rapidapi-key", API_KEY);
        return headers;
    }

}

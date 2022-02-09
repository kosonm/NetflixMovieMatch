package com.netflixmoviematch.webclient.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflixmoviematch.entity.dto.MovieRatingDto;
import com.netflixmoviematch.model.movie.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MovieSearchClient {

    private static final String API_KEY = "f7c25b0abdmshbd9c709e9a6e193p1dad39jsn8c54e7a2a92b";
    private static final String SEARCH_URL = "https://unogsng.p.rapidapi.com/search?";
    private final String RESULTS = "results";
    private final String TOTAL = "total";
    private final int limit = 10;

    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    // ordered by rating, limit right now on 10 results
    public List<MovieDto> getMovies(Map<String, String[]> searchParams) {

        List<MovieDto> movieDtoList = new ArrayList<>();
        MovieDto movieDto = null;

        String url = getSearchUrl(searchParams);

        ResponseEntity<String> responseEntity = callGetMethod(url);

        MovieRatingDto movieRatingDto = null;

        try {
            JsonNode body = objectMapper.readTree(responseEntity.getBody());
            JsonNode result = body.get(RESULTS);
            int totalResults = Integer.valueOf(body.get(TOTAL).toString());

            // TODO rewrite this in streams
            for (int i = 0; i < limit && i < totalResults; i++) {
                movieRatingDto = objectMapper.readValue(result.get(i).toString(), MovieRatingDto.class);
                movieDtoList.add(getMovieDto(movieRatingDto));
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return movieDtoList;
    }

    private String getSearchUrl(Map<String, String[]> searchParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(SEARCH_URL);

        for (Map.Entry<String, String[]> entry : searchParams.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue()[0]);
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private MovieDto getMovieDto(MovieRatingDto movieRatingDto) {
        return MovieDto.builder()
                .imdbRating(movieRatingDto.getImdbRating())
                .synopsis(movieRatingDto.getSynopsis())
                .title(movieRatingDto.getTitle())
                .year(movieRatingDto.getYear())
                .img(movieRatingDto.getImg())
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

package com.netflixmoviematch.controller;

import com.netflixmoviematch.model.movie.MovieDto;
import com.netflixmoviematch.service.impl.MovieSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MovieSearchController {

    private final MovieSearchServiceImpl movieSearchService;

    @GetMapping("/movies")
    public ModelAndView getMovies(HttpServletRequest req){
        Map<String, String[]> paramMap = req.getParameterMap();

        ModelAndView modelAndView = new ModelAndView();
        List<MovieDto> moviesSearchResult = movieSearchService.getMovies(paramMap);
        modelAndView.setViewName("moviesSearchResult");
        modelAndView.addObject("movies", moviesSearchResult);

        return modelAndView;
    }

    @PostMapping("/movies/addLike/{name}")
    public String saveLikedMovieName(@PathVariable String name){



        return "moviesSearchResult";
    }

}

package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        String response = movieService.addMovie(movie);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        String response = movieService.addDirector(director);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie_name") String movieName, @RequestParam("director_name") String directorName) {
        String response = movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity(response, HttpStatus.LOCKED);
    }

    @GetMapping("/get-movie-by-name/{movie_name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movie_name") String movieName) {
        Movie response = movieService.getMovieByName(movieName);
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{director_name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("director_name") String directorName) {
        Director response = movieService.getDirectorByName(directorName);
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director) {
        List<String> response = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity(response, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> response = movieService.findAllMovies();
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director_name") String directorName) {
        String response = movieService.deleteDirectorByName(directorName);
        return new ResponseEntity(response, HttpStatus.REQUEST_TIMEOUT);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        String response = movieService.deleteAllDirectors();
        return new ResponseEntity(response, HttpStatus.LOOP_DETECTED);
    }
}

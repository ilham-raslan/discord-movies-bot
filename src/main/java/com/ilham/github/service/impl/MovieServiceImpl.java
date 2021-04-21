package com.ilham.github.service.impl;

import com.ilham.github.recommendation.RecommendationEngine;
import com.ilham.github.recommendation.tastedive.TastediveRecommendationEngine;
import com.ilham.github.repository.MovieListRepository;
import com.ilham.github.repository.mongo.MongoMovieListRepository;
import com.ilham.github.service.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {
  MovieListRepository movieListRepository;
  RecommendationEngine recommendationEngine;

  public MovieServiceImpl() {
    this.movieListRepository = new MongoMovieListRepository();
    this.recommendationEngine = new TastediveRecommendationEngine();
  }

  @Override
  public void addMovie(String movieName) {
    this.movieListRepository.addMovie(movieName);
  }

  @Override
  public List<String> findAllMovies() {
    return this.movieListRepository.findAllMovies();
  }

  @Override
  public List<String> recommendByMovieName(String movieName) {
    return this.recommendationEngine.recommendByMovieName(movieName);
  }
}

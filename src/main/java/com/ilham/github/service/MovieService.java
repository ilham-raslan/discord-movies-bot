package com.ilham.github.service;

import java.util.List;

public interface MovieService {
  void addMovie(String movieName);

  List<String> findAllMovies();

  List<String> recommendByMovieName(String movieName);
}

package com.ilham.github.repository;

import java.util.List;

public interface MovieListRepository {
  void addMovie(String movieName);

  List<String> findAllMovies();
}

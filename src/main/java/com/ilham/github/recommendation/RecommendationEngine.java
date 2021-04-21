package com.ilham.github.recommendation;

import java.util.List;

public interface RecommendationEngine {
  List<String> recommendByMovieName(String movieName);
}

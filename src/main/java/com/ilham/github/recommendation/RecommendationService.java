package com.ilham.github.recommendation;

import java.util.List;

public interface RecommendationService {
    List<String> recommendByMovieName(String movieName);
}

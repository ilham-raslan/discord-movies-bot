package com.ilham.github.recommendation.tastedive;

import com.ilham.github.properties.credential.CredentialProperties;
import com.ilham.github.recommendation.RecommendationEngine;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class TastediveRecommendationEngine implements RecommendationEngine {
  Properties credentialProperties;

  public TastediveRecommendationEngine() {
    this.credentialProperties = new CredentialProperties();
  }

  @Override
  public List<String> recommendByMovieName(String movieName) {
    List<String> recommendations = new ArrayList<>();
    String apiURL = constructURL(movieName);

    try {
      URL url = new URL(apiURL);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty(
          "User-Agent",
          "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      connection.connect();

      int responseCode = connection.getResponseCode();
      if (responseCode != 200) {
        throw new RuntimeException("Response Code: " + responseCode);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(connection.getInputStream());

        while (scanner.hasNext()) {
          stringBuilder.append(scanner.nextLine());
        }

        scanner.close();

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = (JSONObject) parser.parse(stringBuilder.toString());
        JSONObject similar = (JSONObject) jsonObject.get("Similar");
        JSONArray resultsArray = (JSONArray) similar.get("Results");

        for (Object o : resultsArray) {
          JSONObject recommendation = (JSONObject) o;
          recommendations.add(recommendation.get("Name").toString());
        }
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    return recommendations;
  }

  private String constructURL(String movieName) {
    String baseURL = "https://tastedive.com/api/similar?";
    String apiKey = this.credentialProperties.getProperty("tastedive-api-key");
    String movieNameArguments = movieName.replace(" ", "+");

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(baseURL);
    stringBuilder.append("q=");
    stringBuilder.append(movieNameArguments);
    stringBuilder.append("&");
    stringBuilder.append("k=");
    stringBuilder.append(apiKey);

    return stringBuilder.toString();
  }
}

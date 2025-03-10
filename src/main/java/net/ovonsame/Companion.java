package net.ovonsame;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Companion {
    public static String getJoke(boolean safe, String category, String language) {
        try {
            String url = "https://sv443.net/jokeapi/v2/joke/";
            if(category!=null) {
                url += category + "?safe=" + safe;
            } else {
                url += "Any?";
            }

            if(language!=null) {
                url += "&lang=" + language;
            }
            url += "&type=single";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body()).getString("joke");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String getFact(String language) {
        try {
            String url = "https://uselessfacts.jsph.pl/api/v2/facts/random?";
            if(language!=null) {
                url += "language=" + language;
            }
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body()).getString("text");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String getFactOfNumber(String number) {
        try {
            String url = "http://numbersapi.com/random/trivia?json";
            if(number!=null) {
                url = "http://numbersapi.com/" + number + "/trivia?json";
            }
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body()).getString("text");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String getAdvice() {
        try {
            String url = "https://api.adviceslip.com/advice";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body()).getJSONObject("slip").getString("advice");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    public static String getQuote() {
        try {
            String url = "https://zenquotes.io/api/random";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray json = new JSONArray(response.body());
            return json.getJSONObject(0).get("q").toString().replace(".", "") + " - " + json.getJSONObject(0).getString("a");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static ImmutablePair<String, String> getQuestion() {
        try {
            String url = "https://opentdb.com/api.php?amount=1";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            return ImmutablePair.of(json.getJSONArray("results").getJSONObject(0).getString("question"), json.getJSONArray("results").getJSONObject(0).getString("correct_answer"));
        } catch (Exception e) {
            return ImmutablePair.of(e.getMessage(), e.getMessage());
        }
    }
}

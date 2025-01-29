package com.linkedin.javacodechallenges;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {

    private static final Gson gson = new Gson();
    public static void main(String[] args) {
        // TODO: Call https://icanhazdadjoke.com/ API and display joke
        try{
            HttpClient client = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://icanhazdadjoke.com/"))
                .header("accept", "application/json")
                .build();
            var response = client.send(request, BodyHandlers.ofString());
            System.out.println(getJoke(response.body()));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static String getJoke(String response) {
        var jokeResponse = gson.fromJson(response, JokeResponse.class);
        return jokeResponse.joke();
    }
}

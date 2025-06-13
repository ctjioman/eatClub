package com.ctjioman.eatClub.Service;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.net.http.HttpClient;
import java.net.URI;

import com.ctjioman.eatClub.Model.Resturant;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ChallengeData {
    public ArrayList<Resturant> getChallengeData(String[] args) {
        Gson gson = new Gson();

        ArrayList<Resturant> resturants = new ArrayList<>();
        Resturant resturant;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://eccdn.com.au/misc/challengedata.json"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String apiResponse = response.body().toString();
        System.out.println(apiResponse);

        JsonObject rootObject = JsonParser.parseString(apiResponse).getAsJsonObject();
        JsonArray responseResturants = rootObject.getAsJsonArray("restaurants");

        for (JsonElement element : responseResturants) {
            resturant = new Resturant();
            resturant = gson.fromJson(element, Resturant.class);
            resturants.add(resturant);
        }
    
        return resturants;
    }
}

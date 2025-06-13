package com.ctjioman.eatClub.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctjioman.eatClub.Model.Resturant;
import com.ctjioman.eatClub.Service.ChallengeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class EatClubController {

	@GetMapping("/")
	@ResponseBody
	public String index(@RequestParam(required = false) String timeOfDay) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ChallengeData obj = new ChallengeData();

		System.out.println("timeOfDay: " + timeOfDay);
		ArrayList<Resturant> allResturantData = obj.getChallengeData(null);

		return gson.toJson(allResturantData);
	}

}
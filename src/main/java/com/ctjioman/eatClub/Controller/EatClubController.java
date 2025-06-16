package com.ctjioman.eatClub.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctjioman.eatClub.Model.Deal;
import com.ctjioman.eatClub.Model.GetDealsByTimeOfDayOutput;
import com.ctjioman.eatClub.Model.Resturant;
import com.ctjioman.eatClub.Service.ChallengeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class EatClubController {

	//task 1
	@GetMapping("/")
	@ResponseBody
	public String GetDealsByTimeOfDay(@RequestParam(required = false) String timeOfDay) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ChallengeData challengeDataObj = new ChallengeData();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
		try {
			LocalTime inputDealTime = LocalTime.parse(timeOfDay, formatter);

			ArrayList<Resturant> allResturantData = challengeDataObj.getChallengeData(null);
			ArrayList<GetDealsByTimeOfDayOutput> output = new ArrayList<>();

			for (Resturant resturant : allResturantData) {
				for (Deal deal : resturant.getDeals()) {
					if (isDealActive(formatter, inputDealTime, resturant, deal)) {
						output.add(setGetDealsByTimeOfDayOutput(resturant, deal));
					}

				}
			}
			return gson.toJson(output);


		} catch (Exception e) {
			System.out.println("Error" + e);
			Map<String, String> error = new HashMap<>();
			error.put("error", e.toString());
			return gson.toJson(error);
		}

	}

	private boolean isDealActive(DateTimeFormatter formatter, LocalTime inputDealTime, Resturant resturant, Deal deal) {
		if (deal.isOpenOrStartSet() == false && deal.isCloseOrEndSet() == false) {
			System.out.println("Resturant Name: " + resturant.getName());
			System.out.println("intputTime: " + inputDealTime);
			System.out.println("open: " + resturant.getOpen() + " - "
					+ LocalTime.parse(resturant.getOpen(), formatter));
			System.out.println("close: " + resturant.getClose() + " - "
					+ LocalTime.parse(resturant.getClose(), formatter));

			System.out.println("Input isAfter Open: "
					+ (inputDealTime.isAfter(LocalTime.parse(resturant.getOpen(), formatter))));
			System.out.println("Input isBefore Close: "
					+ (inputDealTime.isBefore(LocalTime.parse(resturant.getClose(), formatter))));
			System.out.println();

			boolean isResturantOpen = inputDealTime.isAfter(LocalTime.parse(resturant.getOpen(), formatter))
					&& inputDealTime.isBefore(LocalTime.parse(resturant.getClose(), formatter));

			if (isResturantOpen == true) {
				return true;
			}
			return false;
		}

		boolean isDealOpenAndActive = deal.isOpenOrStartSet()
				? inputDealTime.isAfter(LocalTime.parse(deal.getOpenOrStart(), formatter))
				: false;
		boolean isDealCloseAndActive = deal.isCloseOrEndSet()
				? inputDealTime.isBefore(LocalTime.parse(deal.getCloseOrEnd(), formatter))
				: false;

		return isDealOpenAndActive && isDealCloseAndActive;
	}

	private GetDealsByTimeOfDayOutput setGetDealsByTimeOfDayOutput(Resturant resturant, Deal deal) {
		GetDealsByTimeOfDayOutput output = new GetDealsByTimeOfDayOutput();
		output.setRestaurantObjectId(resturant.getObjectId());
		output.setDealObjectId(deal.getObjectId());
		output.setRestaurantObjectId(resturant.getObjectId());
		output.setResturantName(resturant.getName());
		output.setResturantAddress1(resturant.getAddress1());
		output.setResturantSuburb(resturant.getSuburb());
		output.setResturantOpen(resturant.getOpen());
		output.setResturantClose(resturant.getClose());
		output.setDealObjectId(deal.getObjectId());
		output.setDiscount(deal.getDiscount());
		output.setDineIn(deal.getDineIn());
		output.setLightning(deal.getLightning());
		output.setQtyLeft(deal.getQtyLeft());
		return output;
	}

}
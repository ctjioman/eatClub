package com.ctjioman.eatClub.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.ctjioman.eatClub.Model.GetPeakTimeOfDealsOutput;
import com.ctjioman.eatClub.Model.OpenTimeEvent;
import com.ctjioman.eatClub.Model.Resturant;
import com.ctjioman.eatClub.Service.ChallengeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class EatClubController {

	// task 1
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
					if (deal.isDealActive(formatter, inputDealTime, resturant, deal)) {
						GetDealsByTimeOfDayOutput getDealsByTimeOfDayOutput = new GetDealsByTimeOfDayOutput();

						output.add(getDealsByTimeOfDayOutput.setGetDealsByTimeOfDayOutput(resturant, deal));
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

	// task 2
	@GetMapping("/getPeakTimeDeals")
	@ResponseBody
	public String GetPeakTimeOfDeals() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ChallengeData challengeDataObj = new ChallengeData();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
		try {
			ArrayList<Resturant> allResturantData = challengeDataObj.getChallengeData(null);
			ArrayList<OpenTimeEvent> events = new ArrayList<>();

			for (Resturant resturant : allResturantData) {
				for (Deal deal : resturant.getDeals()) {
					if (deal.isOpenOrStartSet()) {
						events.add(new OpenTimeEvent(LocalTime.parse(deal.getOpenOrStart(), formatter), true));
					} else {
						events.add(new OpenTimeEvent(LocalTime.parse(resturant.getOpen(), formatter), true));
					}

					if (deal.isCloseOrEndSet()) {
						events.add(new OpenTimeEvent(LocalTime.parse(deal.getCloseOrEnd(), formatter), false));
					} else {
						events.add(new OpenTimeEvent(LocalTime.parse(resturant.getClose(), formatter), false));
					}

				}
			}

			Collections.sort(events);

			int maxOpen = 0;
			int currentOpen = 0;
			LocalTime startPeak = LocalTime.MIN;
			LocalTime endPeak = LocalTime.MIN;

			for (int i = 0; i < events.size(); i++) {
				OpenTimeEvent event = events.get(i);

				if (event.isOpen()) {
					currentOpen++;
					if (currentOpen > maxOpen) {
						maxOpen = currentOpen;
						startPeak = event.getTime();
						// The end time will be the next event's time
						endPeak = (i < events.size() - 1) ? events.get(i + 1).getTime() : LocalTime.MAX;
					}
				} else {
					currentOpen--;
				}
			}
			GetPeakTimeOfDealsOutput output = setGetPeakTimeOfDeals(startPeak, endPeak);

			return gson.toJson(output);

		} catch (Exception e) {
			System.out.println("Error" + e);
			Map<String, String> error = new HashMap<>();
			error.put("error", e.toString());
			return gson.toJson(error);
		}

	}

	private GetPeakTimeOfDealsOutput setGetPeakTimeOfDeals(LocalTime peakStartTime, LocalTime peakEndTime) {
		DateTimeFormatter outputTimeFormatter = DateTimeFormatter.ofPattern("hh:mma");
		GetPeakTimeOfDealsOutput output = new GetPeakTimeOfDealsOutput();
		output.setPeakTimeStart(peakStartTime.format(outputTimeFormatter).toString());
		output.setPeakTimeEnd(peakEndTime.format(outputTimeFormatter).toString());

		return output;
	}

}
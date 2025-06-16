package com.ctjioman.eatClub.Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GetPeakTimeOfDealsOutput {
    String peakTimeStart;
    String peakTimeEnd;

    public GetPeakTimeOfDealsOutput setGetPeakTimeOfDeals(LocalTime peakStartTime, LocalTime peakEndTime) {
		DateTimeFormatter outputTimeFormatter = DateTimeFormatter.ofPattern("hh:mma");
		this.peakTimeStart = peakStartTime.format(outputTimeFormatter).toString();
		this.peakTimeEnd = peakEndTime.format(outputTimeFormatter).toString();

		return this;
	}
}

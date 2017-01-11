package com.egineering.recyclerViewPOC.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

	public static String getDayOfWeek(String dateAsString){
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat dayOfTheWeekFormat = new SimpleDateFormat("EEE");
		return("(" + dayOfTheWeekFormat.format(date) + ")");
	}

}

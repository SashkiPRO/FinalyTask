package ua.nure.botsula.st4.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Contains methods and constants for dates manipulations
 * 
 * @author A.Botsula
 * 
 */
public class DateUtils {

	public static final long MILLISECONDS_IN_YEAR = 1000 * 60 * 60 * 24 * 365L;
	public static final long MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24L;
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 
	 * @param strDate
	 *            string value of date
	 * @param dateFormat
	 *            date format
	 * @return date from given string
	 */
	public static Date getDateFromString(String strDate, String dateFormat) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = new Date(sdf.parse(strDate).getTime());
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * Returns quantity of days between two dates
	 * 
	 * @param dateFrom
	 *            start date
	 * @param dateTo
	 *            final date
	 */
	public static long fullDaysBetweenDates(Date dateFrom, Date dateTo) {
		return (dateTo.getTime() - dateFrom.getTime()) / MILLISECONDS_IN_DAY;
	}

	/**
	 * Returns the date after one year
	 * */
	public static Date afterOneYearDate(Date setCurrentDate) {
		Date date = new Date(setCurrentDate.getTime() + MILLISECONDS_IN_YEAR);
		return date;
	}

	/**
	 * Returns Current Date
	 * */
	public static Date getCurrentDate() {
		return new Date(Calendar.getInstance().getTimeInMillis());
	}
}
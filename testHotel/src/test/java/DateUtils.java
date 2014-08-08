package test.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getCurrentDateString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();		
		return dateFormat.format(date);
	}
	
	public static String getCurrentDateWithTimeString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();		
		return dateFormat.format(date);
	}
	
	public static String getPastDateString() {
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -1);    
	        return dateFormat.format(cal.getTime());
	}
	
	public static String getFutureDateString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);    
        return dateFormat.format(cal.getTime());
	}
	
	private DateUtils() {};
}

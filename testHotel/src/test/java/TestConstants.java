package test.java;

public final class TestConstants {
	//Common
	public static final String SERVICE_PORT = "9090";
	public static final String SERVICE_URL = "localhost";	
	public static final Integer ZERO = 0;
	
	//Error codes
	public static final Integer ERROR_400 = 400;	
	public static final Integer ERROR_404 = 404;
	
	// Check availability service specific constants
	public static final Integer TOTAL_ROOMS = 500;
	public static final Integer MIN_PRICE = 50;
	public static final Integer MAX_PRICE = 200;
	public static final String ROOMS_AVAILABLE_STRING= "rooms_available";
	public static final String PRICE_STRING= "price";
	public static final String DATE_STRING= "date";
	
	// book Room service specific constants
	public static final String TOTAL_PRICE_STRING= "totalPrice";

	
	// best practice is to move error code/messages to separate class. I leave it here for better readability 
	public static final String WRONG_DATE_FORMAT_MESSAGE = "<b>400.</b> Invalid or missing date '%s'. Valid date format is: yyyy-mm-dd, e.g. 2013-04-20.";
		 
	private TestConstants() {};
}

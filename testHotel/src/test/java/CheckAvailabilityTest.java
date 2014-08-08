package test.java;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static org.testng.Assert.assertTrue;
import static test.java.TestConstants.ERROR_400;
import static test.java.TestConstants.ERROR_404;
import static test.java.TestConstants.MAX_PRICE;
import static test.java.TestConstants.MIN_PRICE;
import static test.java.TestConstants.PRICE_STRING;
import static test.java.TestConstants.ROOMS_AVAILABLE_STRING;
import static test.java.TestConstants.SERVICE_PORT;
import static test.java.TestConstants.TOTAL_ROOMS;
import static test.java.TestConstants.WRONG_DATE_FORMAT_MESSAGE;
import static test.java.TestConstants.ZERO;

import test.java.DateUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;

/*
 * Test sute for checkAvailability web service
 * 
 * date 08.08.2014
 * author Boris Wainberg 
 */
public class CheckAvailabilityTest {
	/*
	 * to make project structure simpler I put this object here, however it
	 * would be more correctly to create separate package for that.
	 */
	class CheckAvailabilityRequest {
		String date;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public CheckAvailabilityRequest(String date) {
			this.setDate(date);
		}
	}

	@BeforeClass
	public void setUp() {
		RestAssured.port = Integer.parseInt(SERVICE_PORT);
	}

	@Test(timeOut = 10000)
	public void testCheckRoomsNumberForToday() {
		CheckAvailabilityRequest checkAvl = new CheckAvailabilityRequest(
				DateUtils.getCurrentDateString());

		assertTrue(
				(get("/checkAvailability/" + checkAvl.getDate())
						.path(ROOMS_AVAILABLE_STRING)) instanceof Integer,
				String.format("Check number of available rooms is integer"));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(ROOMS_AVAILABLE_STRING)) < TOTAL_ROOMS, String.format(
				"Check number of rooms is less than %s", TOTAL_ROOMS));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(ROOMS_AVAILABLE_STRING)) >= ZERO, String.format(
				"Check number of rooms is more or equal to %s", ZERO));
	}

	@Test(timeOut = 10000)
	public void testCheckRoomsNumberForTomorrow() {
		CheckAvailabilityRequest checkAvl = new CheckAvailabilityRequest(
				DateUtils.getFutureDateString());

		assertTrue(
				(get("/checkAvailability/" + checkAvl.getDate())
						.path(ROOMS_AVAILABLE_STRING)) instanceof Integer,
				String.format("Check number of available rooms is integer"));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(ROOMS_AVAILABLE_STRING)) < TOTAL_ROOMS, String.format(
				"Check number of rooms is less than %s", TOTAL_ROOMS));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(ROOMS_AVAILABLE_STRING)) >= ZERO, String.format(
				"Check number of rooms is more or equal to %s", ZERO));
	}

	@Test(timeOut = 10000)
	public void testCheckRoomsNumberForYesterday() {
		CheckAvailabilityRequest checkAvl = new CheckAvailabilityRequest(
				DateUtils.getPastDateString());
		/*
		 * Assumption: number of available rooms is always 0 for date in past.
		 * Also it's possible to expect error code here.
		 */
		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(ROOMS_AVAILABLE_STRING)) == ZERO, String.format(
				"Check number of rooms is equal to %s for date in the past",
				ZERO));
	}

	@Test(timeOut = 10000)
	public void testCheckPriceForToday() {
		CheckAvailabilityRequest checkAvl = new CheckAvailabilityRequest(
				DateUtils.getCurrentDateString());

		assertTrue(
				(get("/checkAvailability/" + checkAvl.getDate())
						.path(PRICE_STRING)) instanceof Integer,
				String.format("Check if price is integer"));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(PRICE_STRING)) < MAX_PRICE, String.format(
				"Check number of rooms is less than %s", TOTAL_ROOMS));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(PRICE_STRING)) >= MIN_PRICE, String.format(
				"Check number of rooms is more or equal to %s", ZERO));
	}

	@Test(timeOut = 10000)
	public void testCheckPriceForYesterday() {
		CheckAvailabilityRequest checkAvl = new CheckAvailabilityRequest(
				DateUtils.getPastDateString());

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(PRICE_STRING)) < MAX_PRICE, String.format(
				"Check number of rooms is less than %s", TOTAL_ROOMS));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(PRICE_STRING)) >= MIN_PRICE, String.format(
				"Check number of rooms is more or equal to %s", ZERO));
	}

	@Test(timeOut = 10000)
	public void testCheckPriceForTomorrow() {
		CheckAvailabilityRequest checkAvl = new CheckAvailabilityRequest(
				DateUtils.getFutureDateString());

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(PRICE_STRING)) < MAX_PRICE, String.format(
				"Check number of rooms is less than %s", TOTAL_ROOMS));

		assertTrue(((Integer) get("/checkAvailability/" + checkAvl.getDate())
				.path(PRICE_STRING)) >= MIN_PRICE, String.format(
				"Check number of rooms is more or equal to %s", ZERO));
	}

	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormat() {
		checkErrorMessageForInvalidDate("1231122");
	}

	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormatWrongMonth() {
		checkErrorMessageForInvalidDate("2232-13-12");
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormatWrongMonth2() {
		checkErrorMessageForInvalidDate("2232-0-12");
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormatWrongDay1() {
		checkErrorMessageForInvalidDate("2232-1-32");
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormatWrongDay2() {
		checkErrorMessageForInvalidDate("2232-1-00");
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormatInvalidChars() {
		checkErrorMessageForInvalidDate("erqwer");
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormatEmptyDate() {
		checkErrorMessageForInvalidDate("");
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnDateWithTime() {
		checkErrorMessageForInvalidDate(DateUtils.getCurrentDateWithTimeString());
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidDateFormatNullDate() {
		checkErrorMessageForInvalidDate(null);
	}
	
	@Test(timeOut = 10000)
	public void testCheckErrorMessageOnInvalidURL() {
		expect().statusCode(ERROR_404).when().get("/checkAvailability_ddd/"+DateUtils.getCurrentDateString());
	}

	private void checkErrorMessageForInvalidDate(String wrongDate) {
		expect().statusCode(ERROR_400).when().get("/checkAvailability/1231122");
		String json = get("/checkAvailability/" + wrongDate).asString();
		assertTrue(json.contains(String.format(WRONG_DATE_FORMAT_MESSAGE,
				wrongDate)));
	}
}

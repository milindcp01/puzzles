import static com.jayway.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static test.java.TestConstants.ERROR_400;
import static test.java.TestConstants.ERROR_404;
import static test.java.TestConstants.MAX_PRICE;
import static test.java.TestConstants.MIN_PRICE;
import static test.java.TestConstants.PRICE_STRING;
import static test.java.TestConstants.TOTAL_PRICE_STRING;
import static test.java.TestConstants.ROOMS_AVAILABLE_STRING;
import static test.java.TestConstants.SERVICE_PORT;
import static test.java.TestConstants.TOTAL_ROOMS;
import static test.java.TestConstants.WRONG_DATE_FORMAT_MESSAGE;
import static test.java.TestConstants.ZERO;
import test.java.DateUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.matcher.*;
import com.jayway.restassured.response.*;

import static org.hamcrest.Matchers.*;

/*
 * Test sute for bookRoom web service
 * 
 * date 08.08.2014
 * author Boris Wainberg 
 */
public class BookRoomTest {
	/*
	 * to make project structure simpler I put this object here, however it
	 * would be more correctly to create separate package for that.
	 */
	class BookRoomRequest {
		String bookDate;

		public String getBookDate() {
			return bookDate;
		}

		public void setBookDate(String bookDate) {
			this.bookDate = bookDate;
		}

		public BookRoomRequest(String date) {
			this.setBookDate(date);
		}
	}

	@BeforeClass
	public void setUp() {
		RestAssured.port = Integer.parseInt(SERVICE_PORT);
	}

	@Test(timeOut = 10000)
	public void testCheckCheckInDate() {
		String date = DateUtils.getCurrentDateString();
		assertEquals(
				given().request()
						.contentType(ContentType.JSON)
						.body("{\"numOfDays\":\"1\" ,\"checkInDate\":\"" + date
								+ "\"}").when().post("/bookRoom")
						.path("checkInDate").toString(), date,
				"Check checkInDate in response is equals to checkInDate in request");
	}

	@Test(timeOut = 10000)
	public void testCheckCheckOutDate() {
		String date = DateUtils.getCurrentDateString();
		assertEquals(
				given().request()
						.contentType(ContentType.JSON)
						.body("{\"numOfDays\":\"1\" ,\"checkInDate\":\"" + date
								+ "\"}").when().post("/bookRoom")
						.path("checkOutDate").toString(),
				DateUtils.getFutureDateString(),
				"Check checkOutDate in response is correct");
	}

	@Test(timeOut = 10000)
	public void testCheckTotalPrice() {
		String date = DateUtils.getCurrentDateString();
		assertEquals(
				given().request()
						.contentType(ContentType.JSON)
						.body("{\"numOfDays\":\"1\" ,\"checkInDate\":\"" + date
								+ "\"}").when().post("/bookRoom")
						.path(TOTAL_PRICE_STRING).toString(),
				((Integer) get("/checkAvailability/" + date).path(PRICE_STRING)),
				"Check total price is equal to price from Check Availability request");
	}

}

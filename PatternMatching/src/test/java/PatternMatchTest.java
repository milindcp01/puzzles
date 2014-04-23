package patternmatching;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import patternmatching.PatternMatching;

/**
 * Unit tests for PatternMatching class.
 */
public class PatternMatchTest {

	@Test
	public void checkContainsStringWordMatched() {
		String str1 = "I am good with programming";
		String str2 = "programming";
		assertTrue(PatternMatching.containsString(str1, str2));
	}

	@Test
	public void checkContainsStringWordNotMatched() {
		String str1 = "I am good with programming";
		String str2 = "test";
		assertFalse(PatternMatching.containsString(str1, str2));
	}

	@Test
	public void checkContainsStringEmptyParameters() {
		String str1 = "";
		String str2 = "";
		assertTrue(PatternMatching.containsString(str1, str2));
	}

	@Test
	public void checkContainsStringFirstParameterIsEmpty() {
		String str1 = "";
		String str2 = "test";
		assertFalse(PatternMatching.containsString(str1, str2));
	}

	@Test
	public void checkContainsStringSecondParameterIsEmpty() {
		String str1 = "I am good with programming";
		String str2 = "";
		assertTrue(PatternMatching.containsString(str1, str2));
	}

	@Test
	public void checkContainsStringEqualParameters() {
		String str1 = "I am good with programming";
		String str2 = "I am good with programming";
		assertTrue(PatternMatching.containsString(str1, str2));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void checkContainsStringFirstParameterNotInitialized() {
		String str1 = null;
		String str2 = "I am good with programming";
		PatternMatching.containsString(str1, str2);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void checkContainsStringSecondParameterNotInitialized() {
		String str1 = "I am good with programming";
		String str2 = null;
		PatternMatching.containsString(str1, str2);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void checkContainsStringBothParametersNotInitialized() {
		String str1 = null;
		String str2 = null;
		PatternMatching.containsString(str1, str2);
	}
}

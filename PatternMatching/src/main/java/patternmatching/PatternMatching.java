package patternmatching;

public class PatternMatching 
{
	public static boolean containsString(String sentence, String word) {		
		return sentence.toLowerCase().contains(word.toLowerCase());
	}
}

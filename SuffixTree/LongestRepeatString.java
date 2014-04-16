import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestRepeatString {

	public static void main(String[] args) {
		String s = "adadada";
		List<String> suffixArray = new ArrayList<String>();

		for (int i = 0; i < s.length(); i++) {
			suffixArray.add(s.substring(i, s.length()));
		}
		Collections.sort(suffixArray);
		String repeat = "";
		int longest = 0;

		for (int i = 1; i < suffixArray.size(); i++) {
			String str1 = suffixArray.get(i - 1);
			String str2 = suffixArray.get(i);
			int matchLength = 0;
			for (; matchLength < Math.min(str1.length(), str2.length()); matchLength++) {
				if (str1.charAt(matchLength) != str2.charAt(matchLength))
					break;
			}
			System.out.print("str1: " + str1);
			System.out.println(" str2: " + str2 + " matchedLength: "
					+ matchLength);

			if (matchLength > longest) {
				longest = matchLength;
				repeat = str1.substring(0, matchLength);
			}
		}
		System.out.println("Answer:" + repeat);
	}

}

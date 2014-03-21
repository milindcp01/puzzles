package codility;

import java.util.Stack;

public class NestedString {
	public static void main(String[] args) {
		String str1 = "";
		String str2 = "()";
		String str3 = "(()())";
		String str4 = ")))))(((((";
		String str5 = "(())";
		String str6 = "((((((((";

		System.out.println(solution(str1));
		System.out.println(solution(str2));
		System.out.println(solution(str3));
		System.out.println(solution(str4));
		System.out.println(solution(str5));
		System.out.println(solution(str6));

	}

	public static int solution(String str) {
		char[] arr = str.toCharArray();
		Stack<String> stack = new Stack<String>();
		int counter = 0;
		if (arr.length < 2)
			return 0;
		for (int i = 0; i < str.length(); i++) {
			switch (String.valueOf(arr[i])) {
			case "(":
				stack.push("(");
				counter++;
				break;
			case ")":
				if (!stack.isEmpty() && stack.pop().equals("(")) {
					counter--;
				} else
					return 0;
				break;
			default:
				break;
			}
		}
		return (counter == 0) ? 1 : 0;
	}
}

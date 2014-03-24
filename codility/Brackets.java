import java.util.LinkedList;

public class Brackets {
	public static void main(String[] args) {
		String str1 = "{{}";
		System.out.println(validateString(str1));
	}

	static LinkedList<String> stack = new LinkedList<String>();

	public static int validateString(String str) {

		int[] counter = { 0, 0, 0 };
		for (int i = 0; i < str.length(); i++) {
			char symbol = str.charAt(i);
			System.out.println(symbol + " stack size = " + stack.size());

			if (str.length() < 2)
				return 0;
			switch (Character.toString(symbol)) {
			case "[":
				stack.push(Character.toString(symbol));
				counter[0]++;
				break;
			case "{":
				stack.push(Character.toString(symbol));
				counter[1]++;
				break;
			case "(":
				stack.push(Character.toString(symbol));
				counter[2]++;
				break;
			case "]":
				if (stack.size() == 0 || !stack.pop().equals("["))
					return 0;
				counter[0]--;
				break;
			case "}":
				if (stack.size() == 0 || !stack.pop().equals("{"))
					return 0;
				counter[1]--;
				break;
			case ")":
				if (stack.size() == 0 || !stack.pop().equals("("))
					return 0;
				counter[2]--;
				break;

			default:
				return 0;
			}
		}

		if (counter[0] != 0 || counter[1] != 0 || counter[2] != 0) {
			return 0;
		} else {
			System.out
					.println(counter[0] + " " + counter[1] + " " + counter[2]);
			return 1;
		}
	}

	public int getSize() {
		return stack.size();
	}

	public String pop() {
		return (String) stack.removeFirst();
	}

	public void push(String c) {
		stack.addFirst(c);
	}

	public String top() {
		if (stack.size() > 0) {
			return (String) stack.getFirst();
		} else
			return null;
	}
}

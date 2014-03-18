import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.*;

public class Expression {
	// main here for quick function test proposal. Will be moved to separate
	// test package.
	public static void main(String[] args) {
		String exp1 = "))((";
		System.out.println(isValidExpression(exp1));
		String exp2 = "()(())";
		System.out.println(isValidExpression(exp2));
		String test = "(23+3)*5+(14+2)";

		generateTokens("(23+3)*5+(14+2)");
	}

	public static boolean isValidExpression(String expString) {
		return isValidBrackets(expString);
	}

	private enum Operation {
		PLUS("+") {
			@Override
			BigDecimal apply(BigDecimal x, BigDecimal y) {
				return x.add(y);
			}
		},
		SUBSTRACT("-") {
			@Override
			BigDecimal apply(BigDecimal x, BigDecimal y) {
				return x.subtract(y);
			}
		},
		MULTIPLE("*") {
			@Override
			BigDecimal apply(BigDecimal x, BigDecimal y) {
				return x.multiply(y);
			}
		},
		DIVIDE("/") {
			@Override
			BigDecimal apply(BigDecimal x, BigDecimal y) {
				return x.divide(y);
			}
		};
		private final String symbol;

		private Operation(String symbol) {
			this.symbol = symbol;
		}

		abstract BigDecimal apply(BigDecimal x, BigDecimal y);
	}

	/*
	 * function add brackets around each number
	 *
	 * @param expression
	 */
	public static String addBrackets(String input) {
		return input.replaceAll("([0-9]+)", "($1)");
	}

	/*
	 * function checks expression on nested brackets correction.
	 *
	 * @param expression
	 */
	private static boolean isValidBrackets(String exp) {
		char[] elements = exp.toCharArray();
		int bracketCount = 0;
		int counter = 0;
		for (char c : elements) {
			counter++;
			if (Character.toString(c).equals("(")) {
				bracketCount++;
			}
			if (Character.toString(c).equals(")")) {
				if (bracketCount == 0) {
					return false;
				}
				bracketCount--;
			}
		}
		return (counter == elements.length && bracketCount != 0) ? false : true;
	}

	static class Token {
		public Stack<String> operands = new Stack<String>();
		public Stack<Operation> operators = new Stack<Operation>();
		public Stack<Token> tokens = new Stack<Token>();
		StringBuilder expression = new StringBuilder();

		public void addNestedToken(Token nestedToken) {
			this.tokens.push(nestedToken);

		}

		@Override
		public String toString() {
			return expression.toString();

		}
	}



	private static void generateTokens(String inputStr) {
		Stack<String> operands = new Stack<String>();
		Stack<Character> operators = new Stack<Character>();
		Stack<Token> tokens = new Stack<Token>();

		char[] chars = inputStr.toCharArray();
		boolean openToken = false;
		Token t = null;
		for (char c : chars) {
			if (Character.toString(c).equals("(") && !openToken) {
				t = new Token();
				openToken = true;
			} else if (Character.toString(c).equals("(") && openToken) {
				// case of nested brackets
				tokens.firstElement().addNestedToken(t);
			}
			if (!Character.toString(c).equals(")")
					&& !Character.toString(c).equals("(")) {
				t.expression.append(Character.toString(c));
			}
			if (Character.toString(c).equals(")") && openToken) {
				tokens.push(t);
				openToken = false;
				System.out.println("Token: " + t.toString());
			}
		}
	}

}

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

public class Expression {
	// main here for quick function test proposal. Will be moved to separate test package.
	public static void main(String[] args) {
		String exp1 = "))((";
		System.out.println(isValidExpression(exp1));
		String exp2 = "()(())";
		System.out.println(isValidExpression(exp2));		
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
		}, MULTIPLE("*") {
			@Override
			BigDecimal apply(BigDecimal x, BigDecimal y) {
				return x.multiply(y);
			}
		}, DIVIDE("/") {
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
	 * function checks expression on nested brackets correction
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
	
}

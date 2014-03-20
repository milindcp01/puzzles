import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.regex.*;

public class Expression {
	// main here for quick function test proposal. Will be moved to separate
	// test package.
	public static void main(String[] args) {

		String test = "( 2 + 2 ) + 2 + 2";
		try {
			System.out.println(eval(test));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Stack<Operation> operators;
	private static Stack<Integer> operands;

	public static boolean isValidExpression(String expString) {
		return isValidBrackets(expString);
	}

	public enum Operation {
		OPEN_BRACKET("(", -1) {
			@Override
			@Deprecated
			<T extends Number> T apply(T x, T y) {
				return null;
			}
		},

		CLOSE_BRACKET(")", -1) {
			@Override
			@Deprecated
			<T extends Number> T apply(T x, T y) {
				return null;
			}
		},

		PLUS("+", 1) {
			@Override
			<T extends Number> T apply(T x, T y) {
				return (T) new Integer(x.intValue() + y.intValue());
			}
		},
		SUBSTRACT("-", 1) {
			@Override
			<T extends Number> T apply(T x, T y) {
				return (T) new Integer(x.intValue() - y.intValue());
			}
		},
		MULTIPLE("*", 2) {
			@Override
			<T extends Number> T apply(T x, T y) {
				return (T) new Integer(x.intValue() * y.intValue());
			}
		},
		DIVIDE("/", 2) {
			@Override
			<T extends Number> T apply(T x, T y) {
				return (T) new Double(x.doubleValue() / y.doubleValue());
			}
		};

		private final String symbol;
		private final int rank;

		public int getPriority() {
			return this.rank;
		}

		// Factory
		public static Operation getOperation(String symbol) {
			switch (symbol) {
			case "+":
				System.out.println("+ detected");
				return PLUS;
			case "-":
				System.out.println("- detected");
				return SUBSTRACT;
			case "*":
				System.out.println("* detected");
				return MULTIPLE;
			case "/":
				System.out.println("/ detected");
				return DIVIDE;
			case "(":
				System.out.println("( detected");
				return OPEN_BRACKET;
			case ")":
				System.out.println(") detected");
				return CLOSE_BRACKET;
			default:
				throw new UnsupportedOperationException("Opeation " + symbol
						+ " is not supported.");
			}
		}

		public static boolean isOperation(Character op) {
			for (Operation o : Operation.values()) {
				if (o.toString().equals(Character.toString(op))) {
					System.out.println("operation!");
					return true;
				}
			}
			return false;
		}

		public boolean isClosingBracket() {
			if (this.symbol.toString().equals(
					Operation.CLOSE_BRACKET.toString())) {
				System.out.println("it's closing br");
				return true;
			}

			return false;
		}

		public boolean isOpenBracket() {
			if (this.symbol.toString()
					.equals(Operation.OPEN_BRACKET.toString())) {
				return true;
			}
			return false;
		}

		private Operation(String symbol, int rank) {
			this.symbol = symbol;
			this.rank = rank;
		}

		abstract <T extends Number> T apply(T x, T y);

		@Override
		public String toString() {
			return this.symbol;
		}
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

	private static int eval(String inputStr) throws Exception {
		operands = new Stack<Integer>();
		operators = new Stack<Operation>();
		String tokens[] = inputStr.split("\\s+");

		for (String j : tokens) {
			System.out.println(">> " + j);
			char op = j.charAt(0);
			if (Operation.isOperation(op)) {
				processOp(Operation.getOperation(Character.toString(op)));

			} else if (Character.isDigit(op)) {
				operands.push(Integer.parseInt(j));
			} else {
				throw new Exception("Unexpected Character Encountered: " + op);
			}
		}
		while (operators.size() != 0) {
			processCalc(operators.pop());
			}


		if (operands.size() == 1) {
			return operands.pop();
		} else {
			throw new Exception(
					"No operands or too many operands in operator stack");
		}
	}

	private static void processOp(Operation o) throws Exception {
		System.out.println("process " + o.symbol);
		if (operators.empty() && !o.isClosingBracket()) {
			operators.push(o);
		} else {
			if (o.isClosingBracket()) {
				if (operators.empty()) {
					throw new Exception(
							"Unmatched parenthesis at beginning of expression");
				}
				while (!operators.empty() && !operators.peek().isOpenBracket()) {
					processCalc(operators.pop());
				}
				try {
					operators.pop();
				} catch (Exception e) {
					throw new Exception(
							"Lacking matching beginning parenthesis");
				}
			} else if (o.isOpenBracket()) {
				operators.push(o);
			} else if (o.rank > operators.peek().rank) {
				operators.push(o);
			} else {
				System.out.println("HERE");
				// pop all stacked operators with equal or higher precedence
				// than op
				System.out.println("o.rank = " + o.rank);
				while (!operators.empty() && o.rank <= (operators.peek().rank)) {
					// use the popped off operator and process it
					System.out.println("process both for " + operators.peek());
					processCalc(operators.pop());
				}
				operators.push(o);
			}

		}

	}

	private static void processCalc(Operation o) throws Exception {
		try {
			// pop off the top two operands on the operandStack
			System.out.println("stack size before: " + operands.size());
			int rhs = operands.pop();
			int lhs = operands.pop();
			System.out.println("stack size after: " + operands.size());
			int result = 0;

			// process the top two operands
			switch (o) {
			case PLUS:
				result = Operation.PLUS.apply(lhs, rhs);
				break;
			case SUBSTRACT:
				result = Operation.SUBSTRACT.apply(lhs, rhs);
				break;
			case MULTIPLE:
				result = Operation.MULTIPLE.apply(lhs, rhs);
				break;
			case DIVIDE:
				result = Operation.DIVIDE.apply(lhs, rhs);
				break;
			}
			// place the result back on the operandStack
			System.out.println("res=" + result);
			operands.push(result);

		} catch (EmptyStackException e) {
			throw new Exception("Not enough operands");
		}
	}
}

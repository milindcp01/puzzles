package codility;

/* Calcaulates average of first N numbers of fibonacci sequence.
*
*/
import java.math.*;

public class Fibonacci {

	public static void main(String[] args) {
		fibonacciAverage(5);
		fibonacciAverage(1);
		fibonacciAverage(12);
	}

	// 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89

	public static BigDecimal fibonacciAverage(int number) {
		BigDecimal rounds = BigDecimal.valueOf(number);
		BigDecimal fib1 = BigDecimal.valueOf(0L);
		BigDecimal fib2 = BigDecimal.valueOf(1L);
		BigDecimal fib = BigDecimal.valueOf(fib1.longValue());
		BigDecimal sum = BigDecimal.valueOf(0L);

		for (int i = 1; i <= number; i++) {
			fib = fib1.add(fib2);
			fib2 = fib1;
			fib1 = fib;
			sum = sum.add(fib);
		}
		System.out.println(sum.divide(rounds, MathContext.DECIMAL32));
		return sum.divide(rounds, MathContext.DECIMAL32);
	}
}

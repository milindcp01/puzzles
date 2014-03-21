package codility;

import java.math.BigInteger;

public class PermMissingElem {

	// program finds missing elements in array [1..N]
	// https://codility.com/c/intro/demoHENHJ8-AHV
	// score 100/100

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 3, 4, 5, 7 };
		int[] b = { 1, 3 };
		int[] c = { 2, 3, 1, 5 };
		int[] d = { 2 };
		solution(d);

	}

	public static int solution(int[] a) {
		int res = 0;
		long sum =0L;
		long sum2 = 0L;
		if (a.length == 0)
			return 1;
		for (int i = 0; i <= a.length + 1; i++) {
			sum += i;
		}
		for (int i = 0; i < a.length; i++) {
			sum2 += a[i];
		}
		return (int) (sum - sum2);
	}
}

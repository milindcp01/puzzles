package codility;

import java.util.Arrays;

/* solution for demo task ProductofThree
* score 100/100. complexity (O(N*log(N))
/* keep in mind negative number multiple negative produce positive :)


public class ProductOfThree {
	
	public static void main(String[] args) {
		// some tests here
		int[] a = { -3, 1, 2, -2, 5, 6 };
		int[] b = { 2, 1, 100 };
		
		System.out.println(solution(b));
	}

	public static int solution(int[] A) {
		Arrays.sort(A);
		int right = A[A.length - 1] * A[A.length - 2] * A[A.length - 3];
		int left = A[A.length - 1] * A[0] * A[1];
		return left > right ? left : right;
	}
}

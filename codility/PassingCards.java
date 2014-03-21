package codility;

public class PassingCards {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 0, 1, 0, 1, 1 };
		int[] b = { 0, 1 };
		solution(a);
		solution(b);

	}

	public static int solution(int[] a) {
		long res = 0;
		long zeros = 0;
		long ones = 0;

		long sum = 0;
		if (a[0] == 0) {
			for (int i = 0; i < a.length; i++) {
				if (a[i] == 0)
					zeros++;
				if (a[i] == 1)
					ones++;
				sum += a[i];
				if (sum != sum - a[i])
					if (a[0] == 0) {
						res += zeros;
					} else {
						res += ones;
					}
				if (res > 1_000_000_000)
					return -1;
				System.out.println(sum);
			}
			System.out.println("res: " + res);
		}
		System.out.println(res);
		return (int) res;
	}

}

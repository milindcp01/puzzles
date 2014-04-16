public class FindRange {
	/*
	 * Given a sorted array with duplicates and a number, find the range in the
	 * form of (startIndex, endIndex) of that number. For example,
	 * 
	 * find_range({0 2 3 3 3 10 10}, 3) should return (2,4).find_range({0 2 3 3
	 * 3 10 10}, 6) should return (-1,-1).The array and the number of duplicates
	 * can be large.
	 */

	/*
	 * O(n) solution
	 */
	public static void findRange1(int[] seq, int number) {
		int less = -1;
		int median = (seq.length - 1) / 2;
		int more = -1;
		int counter = 0;

		for (int i = 0, j = seq.length - 1; i < median && j >= median; i++, j--) {
			counter++;
			if (seq[i] == number)
				less = i;
			if (seq[j] == number)
				more = j;
		}

		System.out.println(less + " " + more);
		System.out.println("iterations: " + counter);
	}

	/*
	 * O(log(n)) solution.
	 */
	public static void findRange2(int[] seq, int number) {
		int leftBorder = binarySearch(seq, number, true);
		int rightBorder = binarySearch(seq, number, false);
		if (rightBorder < leftBorder) {
			System.out.println("number not found in array");
		} else {
		System.out.print(binarySearch(seq, number, true) + ";"
				+ binarySearch(seq, number, false));
		}
	}

	/*
	 * finds first occuarance using binary search assume all numbers are
	 * positive. complexity: O(log(n))
	 */
	private static int binarySearch(int[] seq, int number, boolean direction) {
		int low = 0;
		int high = seq.length - 1;

		if (low > high || seq[low] > number || seq[high] < number)
			return -1; // not sorted or out of range for given number
		while (low <= high) {
			int mid = low + ((high - low) / 2);
			if (direction) {
				if (seq[mid] >= number) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {				
				if (seq[mid] > number) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
		}
		return (direction) ? high + 1 : low - 1;
	}
}

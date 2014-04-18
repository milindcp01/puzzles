package sort;

/*
 * 0. We consider an array with size <= 1 sorted .
 * 1. The array that needs to be sorted is A = { 5, 2, 1, 12, 2, 10, 4, 13, 5} . At this point step = 1 .
 * 2. At the first iteration array A is divided into blocks of size step = 1 . The resulting blocks (sub-arrays) are {5}, {2}, {1}, {12}, {2}, {10}, {4}, {13}, {5} .
 * 3. step *= 2, thus step is now 2 . At this point we have a collection of sorted sub-arrays (because their size is = 1) . We will group the sub-arrays one-by-one and we will start merging them . After the merge, the resulting sub-arrays are: {2, 5}, {1,12}, {2,10}, {4, 13} and {5} . {5} remains unpaired as the array size is an odd number . We will take care of this block later .
 * 4. step *= 2, thus step is now 4 . Now we have a collection of 4 blocks of size two and one block of size one . We will start to merge again the adjacent blocks, so the sub-arrays collection becomes: {1, 2, 5, 12}, {2, 4, 10, 13} and {5} .
 * 5. step *= 2, thus step is now 8 . Now we have a collection of 2 blocks with size 4 and one block with size 1 . We will merge the adjacent blocks so the sub-arrays collection becomes {1, 2, 2, 4, 5, 10, 12, 13} and {5} .
 * 6. We now have two blocks one of size 8 and one of size 1 . We will merge those blocks and obtain the resulting sorted array: {1, 2, 2, 4, 5, 5, 10, 12, 13} .
 * 
 */
public class BottomUpMergeSort {
	public static void main(String[] args) {
		int[] array = { 3, 1, 12, 5, 3, 8, 2, 5, 4 };
		printArray(array);
		mergeSort(array);
		printArray(array);
	}

	public static void mergeSort(int[] array) {
		if (array.length < 2)
			return;
		// size of block. increaed with each iteration.
		int step = 1;
		int startL, startR; // indexies of left and right sub-arrays.
		while (step < array.length) {
			startL = 0;
			startR = step;
			while (startR + step <= array.length) {
				mergeArrays(array, startL, startL + step, startR, startR + step);
				System.out.printf("startL=%d, stopL=%d, startR=%d, stopR=%d\n",
						startL, startL + step, startR, startR + step);
				startL = startR + step;
				startR = startL + step;
			}
			System.out.printf("- - - with step = %d\n", step);
			if (startR < array.length) {
				mergeArrays(array, startL, startL + step, startR, array.length);
				System.out.printf(
						"* startL=%d, stopL=%d, startR=%d, stopR=%d\n", startL,
						startL + step, startR, array.length);
			}
			step *= 2;
		}

	}

	/*
	 * merges already sorted blocks
	 */
	public static void mergeArrays(int[] array, int startL, int stopL,
			int startR, int stopR) {
		int[] right = new int[stopR - startR + 1];
		int[] left = new int[stopL - startL + 1];

		for (int i = 0, k = startR; i < (right.length - 1); ++i, ++k) {
			right[i] = array[k];
		}
		for (int i = 0, k = startL; i < (left.length - 1); ++i, ++k) {
			left[i] = array[k];
		}
		right[right.length - 1] = Integer.MAX_VALUE;
		left[left.length - 1] = Integer.MAX_VALUE;



		for (int k = startL, n = 0, m = 0; k < stopR; k++) {
			if (left[m] < right[n]) {
				array[k] = left[m];
				m++;
			} else {
				array[k] = right[n];
				n++;
			}
		}

		System.out.println("Left array:");
		printArray(left);
		System.out.println("Right array:");
		printArray(right);
	}

	private static void printArray(int[] array) {
		for (int i : array) {
			System.out.printf("%d ", i);
		}
		System.out.printf("\n");
	}

}

package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Recursive Merge sort algoruthm 
 * time complexity: O(n*long(n)) in worst case
 * space complexity: O(n) 
 * 
 */

public class RecursiveMergeSort {
	static int it = 1;
	public static void main(String[] args) {
		int size = 10000000;
		int[] list = new int[size];
		for (int i = 0; i < size; i++) {
			list[i] = list.length - i;
		}
		long timeBefore = System.nanoTime();
		// System.out.println("before: " + Arrays.toString(list));
		mergeSort(list);
		long timeAfter = System.nanoTime();
		double seconds = (double) (timeAfter - timeBefore) / 1000000000.0;

		// System.out.println("after:  " + Arrays.toString(list));
		System.out.println("elements: " + list.length + "\ntime elapsed: "
				+ seconds + " sec.");
	}

	public static int[] mergeSort(int[] array) {
		if (array.length <= 1)
			return array;
		int[] result = array;
		int middle = array.length / 2;
		int[] left = Arrays.copyOfRange(array, 0, middle);
		int[] right = Arrays.copyOfRange(array, middle, array.length);
		// System.out.println("-----------------");
		// System.out.print("left: ");
		// printArray(left);
		// System.out.print("right: ");
		// printArray(right);
		// System.out.println("iteration: " + it++);
		// System.out.println("-----------------");

		mergeSort(left);
		mergeSort(right);

		merge(left, right, result);

		return result;
	}

	public static void merge(int[] left, int[] right, int[] result) {
		int iLeft = 0;
		// Next element to consider in the second array
		int iRight = 0;

		// Next open position in the result
		int j = 0;
		// As long as neither iLeft nor iRight is past the end, move the
		// smaller element into the result.
		while (iLeft < left.length && iRight < right.length) {
			if (left[iLeft] < right[iRight]) {
				result[j] = left[iLeft];
				// System.out.println(left[iLeft] + "<--LR--->" +
				// right[iRight]);
				iLeft++;
			} else {
				result[j] = right[iRight];
				// System.out.println(left[iLeft] + "<--RL-->" + right[iRight]);
				iRight++;
			}
			j++;
		}
		// copy what's left
		System.arraycopy(left, iLeft, result, j, left.length - iLeft);
		System.arraycopy(right, iRight, result, j, right.length - iRight);
	}

	public static void printArray(int[] array) {
		System.out.print("[");
		for (int i : array) {
			System.out.print(i + ", ");
		}
		System.out.println("]");
	}

}

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.jws.soap.SOAPBinding;


public class Dominator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {};
		System.out.println(solution(arr1));

	}
	public static int solution(int[] arr) {
		
		int res = -1;
		int[] back=Arrays.copyOf(arr, arr.length);
		Arrays.sort(arr);
		if(arr.length ==0) return -1;
		int count=0;
		int candidate =arr[arr.length/2];
		for(int i = 0; i< arr.length; i++) {
			System.out.println(arr[i]);
			if(arr[i] == candidate) {
				count++;
			//	System.out.println(count);
			}
			if(count > arr.length/2) res = candidate;
		}
		
		return Arrays.binarySearch(back, res);
	}

}

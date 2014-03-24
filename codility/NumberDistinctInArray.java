import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberDistinctInArray {
	
	public static void genericTets(Set<Object> set1) {
		Integer i = new Integer(10);
		set1.add(i);
		String s = "d";
		set1.add(s);
		System.out.println(set1.contains(i));
		
	}
	public static void main(String[] args) {
		int[] a = { -5, -3, -1, 0, 3, 6 };
		int[] b = { -1, -1};
		solution(a);
		solution(b);
		genericTets(new HashSet());
		
	}

	public static int solution(int[] A) {
		int distCount = 0;
		int[] distinct = new int[A.length];

		if (A.length == 1)
			return 1;
		for (int i = 0; i < A.length; i++) {
			distinct[i] = Math.abs(A[i]);
		}
		Arrays.sort(distinct);
		
		for(int i = 1; i<A.length;i+=2) {
			System.out.print(distinct[i]+" ");
			if(A[i] != A[i-1]) {
				
				 if(i==1 && A.length>3) distCount++;
				 else {
					 distCount+=2;
				 }
			} else {
				distCount++;;
			}
		}
		System.out.println();
		System.out.println(distCount);
		return distCount;
	}
}

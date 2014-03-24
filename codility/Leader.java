import java.util.*;
public class Leader {
	public static void main(String[] args) {
		int[] a = {2,2,0};
		int[] b = {4,2,2};
		int[] c = {4, 2, 2, 3, 2, 4, 2, 2, 6, 4};
		System.out.println("res="+solution(a));
		System.out.println("res="+solution(b));
		System.out.println("res="+solution(c));
	}
	public static int solution(int[] A) {
		  int n = A.length;
	        int[] L = new int[n + 1];
	        L[0] = -1;
	        for (int i = 0; i < n; i++) {
	            L[i + 1] = A[i];
	        }
	        Arrays.sort(L);
	        int count = 0;
	        int pos = (n+2 ) / 2;
	        int candidate = L[pos];
	        System.out.println("cand "+candidate);
	        for (int i = 1; i <= n; i++) {
	        	
	            if (L[i] == candidate) {
	            	
	            	count = count + 1;
	            	System.out.println("count: "+count);
	            }
	                
	        }
	        if (count >= pos)
	            return candidate;
	        return (-1);
	}

}

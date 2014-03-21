package codility;

/* codility task (https://codility.com/train)
 *
 *A small frog wants to get to the other side of a river. 
 *The frog is currently located at position 0, and wants to get to position X. Leaves fall from a tree onto the surface of the river.
 *You are given
 */

public class FrogAndRiver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 4, 3, 2, 2, 5, 5 };
		int[] b = { 1 };
		int[] c = { 1, 100, 1000, 3 };
		int[] d = { 0, 100, 0, };
	}

	public static int solution(int X, int[] A) {
		int[] path = new int[X];
		int pathElements = 0;
		int t = 0;
		while (pathElements < X && t < A.length) {
			int newLeaf = A[t] - 1;
			if (newLeaf < X) {
				path[newLeaf]++;
				if (path[newLeaf] == 1) {
					pathElements++;
				}
			}
			t++;
		}
		if (pathElements < X) {
			return -1;
		}
		return t - 1;
	}
}

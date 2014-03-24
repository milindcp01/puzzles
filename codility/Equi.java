import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.stream.IIOByteBuffer;

public class Equi {
	public static void main(String[] args) {
		int[] a = { 2, 1, 2, 0, 1 };
		int[] b = { 1, 2 };

		System.out.println(solution(a));
		System.out.println(solution(b));

	}

	public static int solution(int[] array) {
		Set<Integer> valuesSet = differentValuesIn(array);

		int i = -1;
		while (!valuesSet.isEmpty()) {
			valuesSet.remove(array[++i]);
		}
		return i;
	}

	private static Set<Integer> differentValuesIn(int[] array) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i : array) {
		
			set.add(i);
		}
		System.out.println(set);
		return set;
	}

}

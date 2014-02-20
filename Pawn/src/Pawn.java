import java.util.Random;

// WARNING! current solution has major defect (optimal ways may be ignored)

public class Pawn {
	public static int[][] playGround = generateMatrix(50, 50);

	public static int[][] generateMatrix(int m, int n) {
		int[][] playGround = new int[m][n];
		Random randInt = new Random();
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				playGround[x][y] = randInt.nextInt(4);
				System.out.print(playGround[x][y] + " ");
			}
			System.out.println();
		}
		return playGround;
	}

	public static int solvePawn() {
		int res = 0;

		for (int i = playGround.length - 2; i >= 0; i--) {
			for (int j = playGround[i].length - 2; j >= 0; j--) {
				if (i == j) {
					
					playGround[i][j] += Math.min(playGround[i + 1][j + 1]
							+ playGround[i + 1][j], playGround[i + 1][j + 1]
							+ playGround[i][j + 1]);					
				}
			}
		}
		return playGround[0][0];

	}

	public static void main(String[] args) {		
		System.out.println("done. result = "+solvePawn());
	}
}

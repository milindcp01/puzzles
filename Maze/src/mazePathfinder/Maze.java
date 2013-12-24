//change it
package mazePathfinder;

import java.util.ArrayList;
import java.util.List;

class Cell {
	boolean isVisited;
	boolean isWall;
	private Cell cellUp;
	private Cell cellDown;
	private Cell cellLeft;
	private Cell cellRight;
	private boolean isEnd;
	private int x, y;

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}

	public Cell getCellUp() {
		return cellUp;
	}

	public void setCellUp(Cell cellUp) {
		this.cellUp = cellUp;
	}

	public Cell getCellDown() {
		return cellDown;
	}

	public void setCellDown(Cell cellDown) {
		this.cellDown = cellDown;
	}

	public Cell getCellLeft() {
		return cellLeft;
	}

	public void setCellLeft(Cell cellLeft) {
		this.cellLeft = cellLeft;
	}

	public Cell getCellRight() {
		return cellRight;
	}

	public void setCellRight(Cell cellRight) {
		this.cellRight = cellRight;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}

class InitCells {
	public static Cell[][] CellMap = null;
	public static List<Cell> cellList = new ArrayList<Cell>();

	private static Cell getCellByXY(int x, int y) {
		for (Cell c : cellList) {
			if (c.getX() == x && c.getY() == y) {
				return c;
			}
		}
		return null;
	}

	public static void createMap(int[][] array, int n, int m) {
		int rows = array.length;
		int cc = 0;
		int h;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				Cell c = new Cell();
				int val = array[i][j];

				c.setX(i);
				c.setY(j);
				System.out.print(val);
				c.isVisited = false;

				if (val == 0) {
					c.isWall = true;
					c.setCellDown(null);
					c.setCellLeft(null);
					c.setCellRight(null);
					c.setCellUp(null);

					if (i == 0 && j == 0) {
						c.setEnd(true);
					}

				} else {
					c.isWall = false;
					c.isVisited = false;

				}
				cellList.add(c);

			}
			System.out.println("");
		}

		// here we set Neighbor of each cell
		// for border cells we set null as pointer to its neighbor
		// TODO: it looks a bit awkward but leave as is until redesign.

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("x=" + i + " y=" + j);
				Cell c = getCellByXY(i, j);
				if (c.isWall)
					continue;

				if (c.getX() == 0) {
					c.setCellUp(null);
				} else if (!getCellByXY(c.getX() - 1, j).isWall) {
					c.setCellUp(getCellByXY(c.getX() - 1, j));
				}

				if (c.getX() == m - 1) {
					c.setCellDown(null);
				} else if (!getCellByXY(c.getX() + 1, j).isWall) {
					c.setCellDown(getCellByXY(c.getX() + 1, j));
				}

				if (c.getY() == 0) {
					c.setCellLeft(null);
				} else if (!getCellByXY(i, c.getY() - 1).isWall) {
					c.setCellLeft(getCellByXY(i, c.getY() - 1));
				}

				if (c.getY() == n - 1) {
					c.setCellRight(null);
				} else if (!getCellByXY(i, c.getY() + 1).isWall) {
					c.setCellRight(getCellByXY(i, c.getY() + 1));
				}
			}
		}
		System.out.println("stop " + cellList.size());

	}
}

public class Maze {
	public static void main(String[] args) {
		int n = 13; // x axis
		int m = 9; // y axis
		int[][] arr = new int[][] { { 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 },
				{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
				{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1 },
				{ 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
				{ 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		InitCells.createMap(arr, n, m);
		System.out.println("done");
	}

}

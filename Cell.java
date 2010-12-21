public class Cell {

	private boolean northWall;
	private boolean eastWall;
	private int x;
	private int y;
	private int gold;

	public Cell(boolean northWall, boolean eastWall, int gold, int x, int y) {
		this.northWall = northWall;
		this.eastWall = eastWall;
		this.gold = gold;
		this.x = x;
		this.y = y;
	}

	/* returns true iff there's a wall in the north
	 */
	public boolean hasNorthWall() {
		return northWall;
	}

	/* returns true iff there's a wall in the east
	 */
	public boolean hasEastWall() {
		return eastWall;
	}

	/* returns the current amount of gold >= 0 on this cell
	 */
	public int getGold() {
		return gold;
	}

	/* returns the x coordinate of the cell 0<=x, from west
	 * to east
	 */
	public int getX() {
		return x;
	}

	/* returns the y coordinate of the cell 0<=y, from north
	 * to south
	 */
	public int getY() {
		return y;
	}
}

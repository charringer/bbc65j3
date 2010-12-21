public class Cell {

	private boolean northWall;
	private boolean eastWall;
	private int gold;

	public Cell(boolean northWall, boolean eastWall, int gold) {
		this.northWall = northWall;
		this.eastWall = eastWall;
		this.gold = gold;
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
}

public class Cell {

	private boolean northWall;
	private boolean eastWall;

	public Cell(boolean northWall, boolean eastWall) {
		this.northWall = northWall;
		this.eastWall = eastWall;
	}

	public boolean hasNorthWall() {
		return northWall;
	}

	public boolean hasEastWall() {
		return eastWall;
	}
}

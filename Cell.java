import java.util.ArrayList;
import java.util.List;

public class Cell {

	private Labyrinth labyrinth;
	private boolean northWall;
	private boolean eastWall;
	private int x;
	private int y;
	private int gold;
	private List<Player> players = new ArrayList<Player>();

	public Cell(Labyrinth labyrinth, boolean northWall, boolean eastWall, int gold, int x, int y) {
		this.labyrinth = labyrinth;
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
	
	public synchronized void addPlayer(Player player) {
		players.add(player);
	}
	
	public synchronized void removePlayer(Player player) {
		players.remove(player);
	}

	/* just return the associated Labyrinth
	 */
	public Labyrinth getLabyrinth() {
		return labyrinth;
	}
	
	/* return a list of reachable cells from this cell, respecting walls
	 * only iff respectWalls is true
	 */
	public List<Cell> adjacentCells(boolean respectWalls) {
		List<Cell> cells = new ArrayList<Cell>();
		
		if (!respectWalls || !northWall) {
			if (y + 1 < labyrinth.getHeight()) {
				cells.add(labyrinth.getCell(x, y+1));
			}
		}
		
		if (!respectWalls || !eastWall) {
			if (x + 1 < labyrinth.getWidth()) {
				cells.add(labyrinth.getCell(x+1, y));
			}
		}
		
		if (y - 1 >= 0) {
			Cell newCell = labyrinth.getCell(x, y-1);
			if (!respectWalls || !newCell.hasNorthWall()) {
				cells.add(newCell);
			}
		}
		
		if (x - 1 >= 0) {
			Cell newCell = labyrinth.getCell(x-1, y);
			if (!respectWalls || !newCell.hasEastWall()) {
				cells.add(newCell);
			}
		}
		
		return cells;
	}

	/* an exit is exactly a wall at the north or eastern end, that
	 * has at least one free wall; return whether this is an exit
	 */
	public boolean isExit() {
		return (x+1 == labyrinth.getWidth() && !eastWall)
			|| (y+1 == labyrinth.getHeight() && !northWall);
	}

	/* withdraws the amount of gold >= 0 of this cell and returns the
	 * amount
	 */ 
	public synchronized int seizeGold() {
		int gold = this.gold;
		this.gold = 0;
		return gold;
	}
}

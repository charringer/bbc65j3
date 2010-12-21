import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

	private int height;
	private int width;
	private Cell[][] cell;
	private List<Player> players = new ArrayList<Player>();
	private EndState endState = null;
	private Object finishNotifier = new Object();

	/* format of map: see test cases; minimum dim: 1x1
	 */
	public Labyrinth(String[] map, int[][] treasure) {
		height = (map.length - 1) / 2;
		width = (map[1].length() - 1) / 2;

		cell = new Cell[height][];
		for (int y = 0; y < height; y++) {
			Cell[] cellLine = new Cell[width];
			cell[y] = cellLine;
			for (int x = 0; x < width; x++) {
				boolean north = (map[2*height - 2*(y+1)].charAt(2*x + 1) == '-');
				boolean east = (map[2*height - 2*(y+1) + 1].charAt(2*x + 2) == '|');
				int gold = treasure[height - (y+1)][x];
				Cell curCell = new Cell(this, north, east, gold, x, y);
				cellLine[x] = curCell;
			}
		}
	}

	/* returns height >= 0 */
	public int getHeight() {
		return height;
	}

	/* returns width >= 0 */
	public int getWidth() {
		return width;
	}

	/* returns the cell at coordinates (x,y); x>=0,y>=0,x<width,y<height
	 * the x axis points north, the y axis east
	 */
	public Cell getCell(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			throw new IllegalArgumentException("invalid coordinates");
		return cell[y][x];
	}
	
	/* adds a player to the player list */
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	/* returns the player list */
	public List<Player> getPlayers() {
		return players;
	}
	
	/* starts all players in the player list */
	public void start() {
		endState = null;
		for (Player player : players) {
			player.start();
		}
	}
	
	/* stops all players in the player list and notifies the finishNotifier */
	private void stop() {
		for (Player player : players) {
			player.stopPlayer();
		}
		synchronized (finishNotifier) {
			finishNotifier.notifyAll();
		}
	}
	
	/* ends the game if a hunter reached an exit */ 
	public void huntersWin() {
		endState = EndState.HUNTERS_WIN;
		stop();
	}
	
	/* returns true iff there is a hunter in the player list that is alive */
	public synchronized boolean hasAliveHunter() {
		for (Player player : players) {
			if (player instanceof TreasureHunter) {
				if (!((TreasureHunter) player).isDead()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/* ends the game if all hunters are dead */
	public synchronized void hunterGotKilled() {
		if (!hasAliveHunter()) {
			endState = EndState.GHOSTS_WIN;
			stop();
		}
	}

	/* ends the game if a player reached the maximum step count */
	public void playerReachesStepCount() {
		endState = EndState.STEP_COUNT_REACHED;
		stop();
	}
	
	/* returns as soon as the game has ended */
	public EndState waitForEnd() {
		while (endState == null) {
			try {
				synchronized (finishNotifier) {
					finishNotifier.wait(1000);
				}
			} catch (InterruptedException ex) {}
		}
		return endState;
	}
}

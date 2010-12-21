import java.util.List;
import java.util.Random;

public class TreasureHunter extends Player {

	private Random rand;
	private int gold;
	private boolean alive;

	public TreasureHunter(Cell currentCell, long sleep, int maxSteps) {
		super(currentCell, sleep, maxSteps);
		rand = new Random();
		this.alive = true;
	}

	protected synchronized void step() {
		if (currentCell.isExit()) {
			currentCell.getLabyrinth().huntersWin();
		} else {
			List<Cell> adjacentCells = currentCell.adjacentCells(true);
			int decisionIndex = rand.nextInt(adjacentCells.size());
			Cell nextCell = adjacentCells.get(decisionIndex);
			moveTo(nextCell);
		}
	}
	
	private void moveTo(Cell nextCell) {
		currentCell.removePlayer(this);
		currentCell = nextCell;
		nextCell.addPlayer(this);
		
		gold += currentCell.seizeGold();
	}

	/* return the amount of gold collected so far
	 */
	public int getGold() {
		return gold;
	}

	/* return the live status of the hunter
	 */
	public boolean isDead() {
		return !alive;
	}
	
	public void kill() {
		alive = false;
		stopPlayer();
	}
}

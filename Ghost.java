import java.util.Random;
import java.util.List;

public class Ghost extends Player {
	private Random rand;
	
	public Ghost(Cell currentCell, long sleep, int maxSteps) {
		super(currentCell, sleep, maxSteps);
		this.rand = new Random();
	}

	public void step() {
		// Ghosts can walk through walls 
		List<Cell> ac = this.currentCell.adjacentCells(false);
		Cell nextCell = ac.get(rand.nextInt(ac.size()));
		currentCell.removePlayer(this);
		nextCell.addPlayer(this);
		currentCell = nextCell;
		currentCell.addPlayer(this);

	}
}

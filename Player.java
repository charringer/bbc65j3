public abstract class Player extends Thread {
	private Cell currentCell;
	private long sleepTime;

	public Player(Cell startCell, long sleepTime) {
		currentCell = startCell;
		this.sleepTime = sleepTime;
	}

	public Cell getCell() {
		return currentCell;
	} 

	/*public void run() {
		try {
			sleep(sleepTime);	
		}
	}*/
}

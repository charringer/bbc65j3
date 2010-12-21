public abstract class Player extends Thread {
	private Cell currentCell;
	private long sleepTime;
	private boolean dead;

	/* saves startCell and sleepTime 
	 *  startCell is place where the Player starts
	 *  sleepTime time the player sleeps between each move
	 */
	public Player(Cell startCell, long sleepTime) {
		currentCell = startCell;
		this.sleepTime = sleepTime;
		this.dead = false;
	}

	/* calles step() every sleepTime milliseconds */
	public void run() {
		while (true) {
			try {
				sleep(sleepTime);	
				step();
			} catch (InterruptedException e) {
				if (this.dead)
					return;
			}
		}
	}

	/* stops the current player
	 * by interrupting the thread */
	public void kill() {
		this.dead = true;
		this.interrupt();
	}

	/* should move the player */
	protected abstract void step();
}

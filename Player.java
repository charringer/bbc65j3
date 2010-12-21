public abstract class Player extends Thread {
	protected Cell currentCell;
	private long sleepTime;
	private boolean dead;
	private int stepCountdown;

	/* saves startCell and sleepTime 
	 *  startCell is place where the Player starts
	 *  sleepTime time the player sleeps between each move
	 */
	public Player(Cell startCell, long sleepTime, int maxSteps) {
		currentCell = startCell;
		this.sleepTime = sleepTime;
		this.dead = false;
		startCell.getLabyrinth().addPlayer(this);
		startCell.addPlayer(this);
		this.stepCountdown = maxSteps;
	}

	/* calles step() every sleepTime milliseconds */
	public void run() {
		while (true) {
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				if (this.dead)
					return;
			}	
			step();
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

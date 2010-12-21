public abstract class Player extends Thread {
	protected Cell currentCell;
	private long sleepTime;
	private boolean running;
	private int stepCountdown;

	/* saves startCell and sleepTime 
	 *  startCell is place where the Player starts
	 *  sleepTime time the player sleeps between each move
	 */
	public Player(Cell startCell, long sleepTime, int maxSteps) {
		currentCell = startCell;
		this.sleepTime = sleepTime;
		this.running = false;
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
				if (this.running)
					return;
			}	
			step();
			stepCountdown--;
			if(stepCountdown == 0)
				currentCell.getLabyrinth().playerReachesStepCount();
		}
	}

	/* stops the current player
	 * by interrupting the thread */
	public void stopPlayer() {
		this.running = true;
		this.interrupt();
	}

	/* should move the player */
	protected abstract void step();
	
	@Override
	public String toString() {
		return getClass()	.toString();
	}
}

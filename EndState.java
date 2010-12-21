/* describes how the game ended */
public enum EndState {
	/* one player reached the maximum step count */
	STEP_COUNT_REACHED, 
	/* one hunter reached an exit */
	HUNTERS_WIN,
	/* the ghosts killed all hunters */
	GHOSTS_WIN
}

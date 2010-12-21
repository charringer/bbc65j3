public class Labyrinth {

	private int height;
	private int width;

	/* format of map: see test cases; minimum dim: 1x1
	 */
	public Labyrinth(String[] map) {
		height = map.length / 2;
		width = map[1].length() / 2;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}

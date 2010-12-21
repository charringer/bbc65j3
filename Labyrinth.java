public class Labyrinth {

	private int height;
	private int width;
	private Cell[][] cell;

	/* format of map: see test cases; minimum dim: 1x1
	 */
	public Labyrinth(String[] map, int[][] treasure) {
		height = map.length / 2;
		width = map[1].length() / 2;

		cell = new Cell[height][];
		for (int y = 0; y < height; y++) {
			Cell[] cellLine = new Cell[width];
			cell[y] = cellLine;
			for (int x = 0; x < width; x++) {
				boolean north = (map[2*height - 2*(y+1)].charAt(2*x) == '-');
				boolean east = (map[2*height - 2*(y+1) + 1].charAt(2*x + 1) == '|');
				int gold = treasure[height - (y+1)][x];
				Cell curCell = new Cell(this, north, east, gold, x, y);
				cellLine[x] = curCell;
			}
		}
	}

	public int getHeight() {
		return height;
	}

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
}

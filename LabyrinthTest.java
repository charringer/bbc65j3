import java.util.Arrays;

public class LabyrinthTest extends Assert {

	private Labyrinth lab;
	private Labyrinth exitlessLab;

	@Before
	public void setUp() {
		String[] map = new String[]{
			"o-o-o",
			"|    ",
			"o o-o",
			"|   |",
			"o-o o",
			"|   |",
			"o-o-o"
		};
		int[][] treasure = new int[][] {
			new int[]{50, 5},
			new int[]{ 0, 0},
			new int[]{ 0,10}
		};
		lab = new Labyrinth(map, treasure);

		String[] exitlessMap = new String[]{
			"o-o-o",
			"|   |",
			"o-o-o",
			"|   |",
			"o o o",
			"| | |",
			"o-o-o"
		};       
		exitlessLab = new Labyrinth(exitlessMap, treasure);
	}

	@Tst
	public void getHeightWidth_works() {
		assertEquals(3, lab.getHeight());
		assertEquals(2, lab.getWidth());
	}

	@Tst
	public void getCell_worksAndKnowsWalls() {
		Cell swCell = lab.getCell(0,0);
		assertTrue(swCell != null);
		assertEquals(true, swCell.hasNorthWall());
		assertEquals(false, swCell.hasEastWall());

		// even more tests
		assertEquals(true, lab.getCell(1,1).hasNorthWall());
		assertEquals(true, lab.getCell(1,1).hasEastWall());
		assertEquals(true,  lab.getCell(0,2).hasNorthWall());
		assertEquals(false, lab.getCell(0,2).hasEastWall());
	}

	@Tst
	public void getCell_worksAndKnowsTreasure() {
		assertEquals(0, lab.getCell(0,0).getGold());
		assertEquals(0, lab.getCell(1,1).getGold());
		assertEquals(50, lab.getCell(0,2).getGold());
		assertEquals(10, lab.getCell(1,0).getGold());
	}

	@Tst
	public void getCell_worksAndKnowsCoordinates() {
		assertEquals(0, lab.getCell(0,0).getX());
		assertEquals(1, lab.getCell(1,0).getX());
		assertEquals(0, lab.getCell(0,1).getX());
		assertEquals(1, lab.getCell(1,1).getX());
		assertEquals(0, lab.getCell(0,2).getX());
		assertEquals(1, lab.getCell(1,2).getX());

		assertEquals(0, lab.getCell(0,0).getY());
		assertEquals(0, lab.getCell(1,0).getY());
		assertEquals(1, lab.getCell(0,1).getY());
		assertEquals(1, lab.getCell(1,1).getY());
		assertEquals(2, lab.getCell(0,2).getY());
		assertEquals(2, lab.getCell(1,2).getY());
	}
	
	@Tst
	public void adjacentCells_works() {
		assertEquals(2, lab.getCell(0,0).adjacentCells(false).size());
		assertEquals(3, lab.getCell(1,1).adjacentCells(false).size());

		assertEquals(1, lab.getCell(0,0).adjacentCells(true).size());
		assertEquals(2, lab.getCell(1,1).adjacentCells(true).size());

		assertEquals(Arrays.asList(lab.getCell(1,0)),
				lab.getCell(0,0).adjacentCells(true));
		assertEquals(Arrays.asList(lab.getCell(1,0), lab.getCell(0,1)),
				lab.getCell(1,1).adjacentCells(true));
	}

	@Tst
	public void huntersWinThroughExit() {
		new TreasureHunter("lucky hunter", lab.getCell(0,0), 2, 200);
		lab.start();
		assertEquals(EndState.HUNTERS_WIN, lab.waitForEnd());
	}

	@Tst
	public void huntersCollectAllTreasure() {
		TreasureHunter th1 = new TreasureHunter("hunter 1", lab.getCell(0,0), 2, 100);
		TreasureHunter th2 = new TreasureHunter("hunter 2", lab.getCell(0,1), 5, 100);
		lab.start();
		assertEquals(EndState.HUNTERS_WIN, lab.waitForEnd());

		assertEquals(65, th1.getGold() + th2.getGold());
	}

	@Tst
	public void huntersReachesMaxSteps() {
		new TreasureHunter("desperate hunter", exitlessLab.getCell(0,0), 2, 20);
		exitlessLab.start();
		assertEquals(EndState.STEP_COUNT_REACHED, exitlessLab.waitForEnd());
	}

	@Tst
	public void allHuntersDie() {
		TreasureHunter th1 = new TreasureHunter("Bonny", exitlessLab.getCell(0,0), 50, 50);
		TreasureHunter th2 = new TreasureHunter("Clyde", exitlessLab.getCell(1,2), 50, 50);
		new Ghost(exitlessLab.getCell(0,2), 1, 2000);
		exitlessLab.start();
		assertEquals(EndState.GHOSTS_WIN, exitlessLab.waitForEnd());

		assertTrue(th1.isDead());
		assertTrue(th2.isDead());
	}
}

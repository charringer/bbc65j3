public class LabyrinthTest extends Assert {

	private Labyrinth lab;

	@Before
	public void setUp() {
		String[] map = new String[]{
			"-o-o",
			"    ",
			" o-o",
			"   |",
			"-o o",
			"   |"
		};
		lab = new Labyrinth(map);
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
}

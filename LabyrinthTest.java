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
	public void getHeightWidth_work() {
		assertEquals(3, lab.getHeight());
		assertEquals(2, lab.getWidth());
	}
}


public class Test {

	public static void main(String[] args) {
		System.out.println("== Phase I: deterministic test cases");
		TestRunner.main(LabyrinthTest.class);

		System.out.println("");
		System.out.println("== Phase II: big real-life labyrinth in action");
		String[] map = new String[]{
"o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o",
"|             |                         |",
"o o-o-o-o-o-o o-o-o o o-o-o o-o-o-o o-o o",
"| |               | | |   | |   | | |   |",
"o o o-o o-o-o-o o-o o-o o o o-o o o o-o o",
"| | | |   |   | |       | |   | |     | |",
"o o o o o-o o o o o-o-o o o-o-o o o-o o o",
"| |   | |   | | | |     |       |     | |",
"o o o-o o o-o o-o o-o-o o o o-o o o-o-o o",
"| |     | |           | | | | | | |     |",
"o o-o-o-o o-o o o-o-o-o o-o o o o o o-o o",
"|         |   | |         | |   | | |   |",
"o o-o o-o-o o-o o o-o-o-o o-o-o o o o-o o",
"| | | | |   |   |       |     | | |   | |",
"o o o-o o o o-o o o-o-o o-o-o o o o-o o o",
"| |     |       | | |       | | |   | | |",
"o o-o-o o o o-o-o o o-o-o-o o o o-o-o o o",
"|     | | | |     |     |   | |         |",
"o o-o-o o o-o o-o-o o-o o-o-o o-o-o-o-o o",
"| |                 |                   |",
"o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o",
		};
		int[][] treasure = new int[][] {
			new int[]{50, 5},
			new int[]{ 0, 0},
			new int[]{ 0,10}
		};
		
		Labyrinth lab = new Labyrinth(map, treasure);
		for (int i = 0; i < 10; i++) { 
			new TreasureHunter("Hunter "+i, lab.getCell(0,0), 10, 100);
		}
		for (int i = 0; i < 5; i++) {
			new Ghost(lab.getCell(0,2), 100, 100);
		}
		lab.start();
		System.out.println(lab.waitForEnd());
		
		for (Player player : lab.getPlayers()) {
			if (player instanceof TreasureHunter) {
				TreasureHunter hunter = (TreasureHunter) player;
				System.out.println(hunter.getMaidenName()+" collected "+hunter.getGold()
					+" Gold"+(hunter.isDead()?" and died":""));
			}
		}
	}

}

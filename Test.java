import java.util.Random;

public class Test {

	public static void main(String[] args) {
		System.out.println("== Phase I: deterministic test cases");
		TestRunner.main(LabyrinthTest.class);

		System.out.println("");
		System.out.println("== Phase II: big real-life labyrinth in action");
		String[] map = new String[]{
"o-o-o-o-o-o-o-o o-o-o-o-o-o-o o-o-o-o-o-o",
"|             |                         |",
"o o-o-o-o-o-o o-o-o o o-o-o o-o-o-o o-o o",
"| |    C l a u d i|o| |   | |   | | |   |",
"o o o-o o-o-o-o o-o o-o o o o-o o o o-o o",
"| | | |   |   | |       | |   | |     | |",
"o o o o o-o o o o o-o-o o o-o-o o o-o o o",
"| |   | |  G| | | |     |       |     | |",
"o-o o-o o o-o o-o o-o-o o o o-o o o-o-o o",
"|       | |a          | | | | | | |     |",
"o o-o-o-o o-o o o-o-o-o o-o o o o o o-o o",
"|         |b  | |      H  | |   | | |    ",
"o o-o o-o-o o-o o o-o-o-o o-o-o o o o-o-o",
"| | | | |  r|   |       |a    | | |   |  ",
"o o o-o o o o-o o o-o-o o-o-o o o o-o o o",
"| |     |  i    | | |      n| | |   | | |",
"o o-o-o o o o-o-o o o-o-o-o o o o-o-o o o",
"|     | | |e|     |     |   |s|         |",
"o o-o-o o o-o o-o-o o-o o-o-o o-o-o-o-o o",
"| |        l        |                   |",
"o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o",
		};
		int[][] treasure = new int[][] {
new int[]{44, 0, 8,70, 0,95, 0,21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
new int[]{ 0, 0,28, 0, 0, 0, 0, 0, 0, 0, 0,43,92, 0, 0,18, 8, 0, 0, 0},
new int[]{ 0, 0, 0, 0, 0, 0, 0, 0,40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,77,28, 0, 0, 0, 0, 0, 0, 0, 0},
new int[]{ 0,86, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,38, 0, 0, 0, 0, 0},
new int[]{ 0, 0, 0,27, 0, 0,66,84, 0, 0,92, 0,48, 0,17, 0, 0, 0, 0, 0},
new int[]{46, 0, 0,23, 0, 6, 0, 0,59, 0, 0,42, 0, 0, 0,11,52,11, 0, 0},
new int[]{65, 0,95, 0, 0, 5,92, 0, 0,65,84, 0,91, 0, 0, 0, 0, 0, 0, 0},
new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0,98,50, 0, 0, 0, 0, 0, 0, 0, 0, 0},
new int[]{ 0,95, 0,68, 0, 0, 0,77, 0, 0, 0,65, 0, 0, 0, 0, 0,99, 0, 0},
		};
		
		Random rand = new Random();
		Labyrinth lab = new Labyrinth(map, treasure);
		for (int i = 0; i < 15; i++) { 
			int x = rand.nextInt(10);
			int y = rand.nextInt(5);
			new TreasureHunter("Hunter "+(i+1), lab.getCell(x,y), 3+rand.nextInt(5), 500);
		}
		for (int i = 0; i < 5; i++) {
			int x = rand.nextInt(20);
			int y = rand.nextInt(10);
			new Ghost(lab.getCell(x,y), 30+rand.nextInt(10), 300);
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

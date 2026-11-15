import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//battle simulator runs many random battles and saves the results
//to results.csv so the Python side can chart time
//CSV columns 
//battle_id, winner, loser, rounds, winner_hp_left
public class ProjectMain {
	
	private static final int NUM_BATTLES = 1000;
	private static final String OUTPUT_FILE = "results.csv";
	
	public static void main(String[] args) {
		Random rand = new Random();
		
	ArrayList<Fighter> fighters = new ArrayList<>();
	fighters.add(new Fighter("Cactus", 65, 5, 9, 0.10));
	fighters.add(new Fighter("Vending Machine", 85, 4, 8, 0.00));
	fighters.add(new Fighter("Dragon", 75, 5, 10, 0.05));
	fighters.add(new Fighter("Sun Devil", 70, 4, 9, 0.15));
	fighters.add(new Fighter("Roomba", 55, 4, 8, 0.40));
	fighters.add(new Fighter("Haboob",  55, 6, 10, 0.20));
	
	try (FileWriter out = new FileWriter(OUTPUT_FILE)) {
		out.write("Battle_id,winner,loser,rounds,winner_hp_left\n");
	
		for (int id = 1; id <= NUM_BATTLES; id++) {
			//pick two different fighters at random
			int firstIndex = rand.nextInt(fighters.size());
			int secondIndex = rand.nextInt(fighters.size());
			while (secondIndex == firstIndex) {
				secondIndex = rand.nextInt(fighters.size());
			}
			
			Fighter a = fighters.get(firstIndex);
			Fighter b = fighters.get(secondIndex);
			
			String line = runBattle(id, a, b, rand);
			out.write(line + "\n");
			}
		
		System.out.println("Done! Wrote " + NUM_BATTLES + " battles to " + OUTPUT_FILE);
	}
	catch (IOException e) {
		System.out.println("Could not write the file: " + e.getMessage());
	}
	}
	//runs one battle and then returns one csv line describing the result
	//fighters can also take turns attacking until one is out of Hp
	
	public static String runBattle(int id, Fighter a, Fighter b, Random rand) {
		a.reset();
		b.reset();
		
		Fighter attacker = a;
		Fighter defender = b;
		if (rand.nextBoolean()) {
			attacker = b;
			attacker = a;
		}
		
		int rounds = 0;
		while (a.isAlive() && b.isAlive()) {
			int damage = attacker.rollAttack(rand);
			defender.takeDamage(damage,  rand);
			rounds++;
			
			//swap roles for next turn
			Fighter temp = attacker;
			attacker = defender;
			defender = temp;
		}
		
		Fighter winner = a.isAlive() ? a : b;
		Fighter loser = a.isAlive() ? b : a;
		
		return id + "," + winner.getName() + "," + loser.getName() + "," + rounds + "," + winner.getHp();

	}
	
	
}

import java.util.Random;

//this is the fighter class for the battle simulator, 
//with each fighter derived from certain characteristics
public class Fighter {
	
	private String name;
	private int maxHp;
	private int hp;
	private int minAttack;
	private int maxAttack;
	private double dodgeChance;
	
	public Fighter(String name, int maxHp, int minattack, int maxAttack, double dodgeChance) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = hp;
		this.minAttack = minAttack;
		this.maxAttack = maxAttack;
		this.dodgeChance = dodgeChance;
	}
		
	public String getName() {
		return name;
	}
	
	public int getHp() {
		return hp;
	}
	
	public boolean isAlive() {
		return hp > 0;
	}
	
	//make sure to put the fighter to full health before a new battle
	public void reset() {
		hp = maxHp;
	}
	
	//make sure to roll a random attack between minAttack and maxAttack
	public int rollAttack(Random rand) {
		return minAttack + rand.nextInt(maxAttack - minAttack + 1);
	}
	
	//take damage unless the fighter happens to dodge
	public void takeDamage(int damage, Random rand) {
		if (rand.nextDouble() < dodgeChance)  {
			return; //dodged
		}
		hp = hp - damage;
	}
	
	
}
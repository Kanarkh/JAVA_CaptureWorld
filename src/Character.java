 
public class Character {
	String name;
	Land currentLandNumber;
	private int attack;
	protected int life;

	public Character() {
		
	}
	public Character(int attack,int life) {
		this.attack = attack;
		this.life = life;
	}
	public String nameShowMe() {
		return this.name;
	}

	public synchronized void changeLife(int num) {
		this.life += num;
	}

	public synchronized void setLife(int num) {
		this.life = num;
	}

	public synchronized int showLifeData() {
		return this.life;
	}

	public synchronized void changeAttack(int num) {
		this.attack += num;
	}

	public synchronized void setAttack(int num) {
		this.attack = num;
	}

	public int showAttackData() {
		return this.attack;
	}
}

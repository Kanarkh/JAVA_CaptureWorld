
public class Building {
	int durability;
	int requiredWorkload;
	King buildingOwner;
	Land currentLand;
	

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public void changeDurability(int variation) {
		this.durability += variation;
	}

}

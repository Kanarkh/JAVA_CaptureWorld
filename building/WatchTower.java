
public class WatchTower extends Building implements Runnable {
	int towerAttack;
	int towerRange;
	int reloadTime; // ms

	public WatchTower(King owner, Land currentLand) {
		this.buildingOwner = owner;
		this.currentLand = currentLand;
		this.setDurability(10);
		this.requiredWorkload = 50;
		this.towerAttack = 1;
		this.towerRange = 1;
		this.reloadTime = 3;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 * this.reloadTime);
				
				if(this.durability<=0)
					break;
				
				attack();

			} catch (Exception e) {
				break;
			}
		}
		if(this.durability<=0)
			System.out.println("WatchTower ¹Ú»ì³²");
	}

	public void attack() {
		Block tempBlock = this.buildingOwner.nationalMap.findBlock(this.currentLand.landNumber);
		Block targetBlock;
		
		targetBlock=tempBlock;
		if (targetBlock!= null)
			if (targetBlock.land.landOwner != null)
				if (targetBlock.land.landOwner == this.buildingOwner) {
					//¾Æ±º¶¥
					if(targetBlock.land.offensivePlayer.size()!=0)
						targetBlock.land.offensivePlayer.get(0).changeLife(this.towerAttack*-1);
					
				} else {
					//Àû±º¶¥
					if(targetBlock.land.defensivePlayer.size()!=0)
						targetBlock.land.defensivePlayer.get(0).changeLife(this.towerAttack*-1);
				}
		
		targetBlock=tempBlock.preBlock0;
		if (targetBlock!= null)
			if (targetBlock.land.landOwner != null)
				if (targetBlock.land.landOwner == this.buildingOwner) {
					//¾Æ±º¶¥
					if(targetBlock.land.offensivePlayer.size()!=0)
						targetBlock.land.offensivePlayer.get(0).changeLife(this.towerAttack*-1);
					
				} else {
					//Àû±º¶¥
					if(targetBlock.land.defensivePlayer.size()!=0)
						targetBlock.land.defensivePlayer.get(0).changeLife(this.towerAttack*-1);
				}
		
		targetBlock=tempBlock.preBlock1;
		if (targetBlock!= null)
			if (targetBlock.land.landOwner != null)
				if (targetBlock.land.landOwner == this.buildingOwner) {
					//¾Æ±º¶¥
					if(targetBlock.land.offensivePlayer.size()!=0)
						targetBlock.land.offensivePlayer.get(0).changeLife(this.towerAttack*-1);
					
				} else {
					//Àû±º¶¥
					if(targetBlock.land.defensivePlayer.size()!=0)
						targetBlock.land.defensivePlayer.get(0).changeLife(this.towerAttack*-1);
				}
		
		targetBlock=tempBlock.nextBlock0;
		if (targetBlock!= null)
			if (targetBlock.land.landOwner != null)
				if (targetBlock.land.landOwner == this.buildingOwner) {
					//¾Æ±º¶¥
					if(targetBlock.land.offensivePlayer.size()!=0)
						targetBlock.land.offensivePlayer.get(0).changeLife(this.towerAttack*-1);
					
				} else {
					//Àû±º¶¥
					if(targetBlock.land.defensivePlayer.size()!=0)
						targetBlock.land.defensivePlayer.get(0).changeLife(this.towerAttack*-1);
				}
		
		targetBlock=tempBlock.nextBlock1;
		if (targetBlock!= null)
			if (targetBlock.land.landOwner != null)
				if (targetBlock.land.landOwner == this.buildingOwner) {
					//¾Æ±º¶¥
					if(targetBlock.land.offensivePlayer.size()!=0)
						targetBlock.land.offensivePlayer.get(0).changeLife(this.towerAttack*-1);
					
				} else {
					//Àû±º¶¥
					if(targetBlock.land.defensivePlayer.size()!=0)
						targetBlock.land.defensivePlayer.get(0).changeLife(this.towerAttack*-1);
				}
	}
}

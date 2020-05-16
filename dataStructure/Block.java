
public class Block {
	int BlockNum;
	Block preBlock0, preBlock1;
	Block nextBlock0, nextBlock1;
	int distanceOursCapital;
	int distanceEnemyCapital;
	Land land;
	
	public Block() {

	}

	public Block(int blockNum,Land land) {
		this.BlockNum = blockNum;
		this.land = land;
	}

	public void setPreBlock(int blockNum, Block block) {
		if (blockNum == 0)
			this.preBlock0 = block;
		else if (blockNum == 1)
			this.preBlock1 = block;
		else
			System.out.println("잘못된 이전블럭 주소");
	}

	public void setNextBlock(int blockNum, Block block) {
		if (blockNum == 0)
			this.nextBlock0 = block;
		else if (blockNum == 1)
			this.nextBlock1 = block;
		else
			System.out.println("잘못된 다음블럭 주소");
	}

	public void setBlockNum(int blockNum) {
		this.BlockNum = blockNum;
	}

	public int getBlockNum() {
		return this.BlockNum;
	}
}

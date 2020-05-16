import java.util.LinkedList;

public class GraphMap {
	// ������ġ, ��ũ�� �ʿ�.
	Block startBlock;
	private int startPoint;
	private Block tempMap[][];
	int mapSize;
	private Land land[];
	LinkedList<Land> listLandHeld;

	public GraphMap(int startPoint, Land[] land, int mapSize) {
		this.startPoint = startPoint;
		this.mapSize = mapSize;
		this.land = land;
		// �ӽ� �� ����
		this.tempMap = new Block[mapSize][mapSize];
		// �� �ȿ� ��� ���� 1���� ~ ����
		tempMapCreate();
		GraphMapCreate();

	}

	public Block findBlock(int id) {
		Block tempBlock = null;

		a: for (int j = 0; j < this.mapSize; j++) {
			for (int i = 0; i < this.mapSize; i++) {
				if (tempMap[i][j].BlockNum == id) {
					tempBlock = tempMap[i][j];
					break a;
				}
			}
		}
		return tempBlock;
	}

	private void tempMapCreate() {
		int i, j;
		int tempNum = 0;
		int tempDistance;
		int enemyDistanceMax = (mapSize - 1) * 2;
		for (j = 0; j < this.mapSize; j++) {
			tempDistance = j;
			for (i = 0; i < this.mapSize; i++) {
				this.tempMap[i][j] = new Block(tempNum, land[tempNum]);
				this.tempMap[i][j].distanceOursCapital = tempDistance;
				this.tempMap[i][j].distanceEnemyCapital = enemyDistanceMax - tempDistance;
				tempNum++;
			}
		}
	}

	private void GraphMapCreate() {
		int i, j;
		if (startPoint == 0) {
			this.startBlock = tempMap[0][0];
			for (j = 0; j < this.mapSize; j++) {
				for (i = 0; i < this.mapSize; i++) {
					// ������� ����
					
					if (i - 1 >= 0) { // ���η� ���� ��� ����
						tempMap[i][j].setPreBlock(1, tempMap[i - 1][j]);
					} else
						tempMap[i][j].setPreBlock(1, null);

					if (j - 1 >= 0) { // ���η� ���� ��� ����
						tempMap[i][j].setPreBlock(0, tempMap[i][j - 1]);
					} else
						tempMap[i][j].setPreBlock(0, null);

					// ���ĳ�� ����
					if (i + 1 < mapSize) {
						tempMap[i][j].setNextBlock(0, tempMap[i + 1][j]); // ���η� ���� ��� ����
					} else
						tempMap[i][j].setNextBlock(0, null);

					if (j + 1 < mapSize) {
						tempMap[i][j].setNextBlock(1, tempMap[i][j + 1]); // ���η� ���� ��� ����
					} else
						tempMap[i][j].setNextBlock(1, null);
				}
			}
		} else if (startPoint == mapSize - 1) {
			System.out.println("�̱���_graphMap");
		} else if (startPoint == (mapSize * mapSize) - (mapSize)) {
			System.out.println("�̱���_graphMap");
		} else if (startPoint == (mapSize * mapSize) - 1) {

			this.startBlock = tempMap[mapSize - 1][mapSize - 1];
//			for (j = mapSize - 1; j >= 0; j--) {
//				for (i = mapSize - 1; i >= 0; i--) {
			for (j = 0; j < this.mapSize; j++) {
				for (i = 0; i < this.mapSize; i++) {
					// ������� ����
					if (i + 1 < mapSize) {
						tempMap[i][j].setPreBlock(0, tempMap[i + 1][j]); // ���η� ���� ��� ����
					} else
						tempMap[i][j].setPreBlock(0, null);

					if (j + 1 < mapSize) {
						tempMap[i][j].setPreBlock(1, tempMap[i][j + 1]); // ���η� ���� ��� ����
					} else
						tempMap[i][j].setPreBlock(1, null);

					// ���ĳ�� ����
					if (i - 1 >= 0) { // ���η� ���� ��� ����
						tempMap[i][j].setNextBlock(1, tempMap[i - 1][j]);
					} else
						tempMap[i][j].setNextBlock(1, null);

					if (j - 1 >= 0) { // ���η� ���� ��� ����
						tempMap[i][j].setNextBlock(0, tempMap[i][j - 1]);
					} else
						tempMap[i][j].setNextBlock(0, null);
				}
			}

		}
	}
}

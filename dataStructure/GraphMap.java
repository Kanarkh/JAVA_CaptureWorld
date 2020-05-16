import java.util.LinkedList;

public class GraphMap {
	// 시작위치, 맵크기 필요.
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
		// 임시 맵 생성
		this.tempMap = new Block[mapSize][mapSize];
		// 맵 안에 블록 생성 1부터 ~ 까지
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
					// 이전노드 연결
					
					if (i - 1 >= 0) { // 가로로 이전 노드 연결
						tempMap[i][j].setPreBlock(1, tempMap[i - 1][j]);
					} else
						tempMap[i][j].setPreBlock(1, null);

					if (j - 1 >= 0) { // 세로로 이전 노드 연결
						tempMap[i][j].setPreBlock(0, tempMap[i][j - 1]);
					} else
						tempMap[i][j].setPreBlock(0, null);

					// 이후노드 연결
					if (i + 1 < mapSize) {
						tempMap[i][j].setNextBlock(0, tempMap[i + 1][j]); // 가로로 이후 노드 연결
					} else
						tempMap[i][j].setNextBlock(0, null);

					if (j + 1 < mapSize) {
						tempMap[i][j].setNextBlock(1, tempMap[i][j + 1]); // 세로로 이후 노드 연결
					} else
						tempMap[i][j].setNextBlock(1, null);
				}
			}
		} else if (startPoint == mapSize - 1) {
			System.out.println("미구현_graphMap");
		} else if (startPoint == (mapSize * mapSize) - (mapSize)) {
			System.out.println("미구현_graphMap");
		} else if (startPoint == (mapSize * mapSize) - 1) {

			this.startBlock = tempMap[mapSize - 1][mapSize - 1];
//			for (j = mapSize - 1; j >= 0; j--) {
//				for (i = mapSize - 1; i >= 0; i--) {
			for (j = 0; j < this.mapSize; j++) {
				for (i = 0; i < this.mapSize; i++) {
					// 이전노드 연결
					if (i + 1 < mapSize) {
						tempMap[i][j].setPreBlock(0, tempMap[i + 1][j]); // 가로로 이전 노드 연결
					} else
						tempMap[i][j].setPreBlock(0, null);

					if (j + 1 < mapSize) {
						tempMap[i][j].setPreBlock(1, tempMap[i][j + 1]); // 세로로 이전 노드 연결
					} else
						tempMap[i][j].setPreBlock(1, null);

					// 이후노드 연결
					if (i - 1 >= 0) { // 가로로 이후 노드 연결
						tempMap[i][j].setNextBlock(1, tempMap[i - 1][j]);
					} else
						tempMap[i][j].setNextBlock(1, null);

					if (j - 1 >= 0) { // 세로로 이후 노드 연결
						tempMap[i][j].setNextBlock(0, tempMap[i][j - 1]);
					} else
						tempMap[i][j].setNextBlock(0, null);
				}
			}

		}
	}
}

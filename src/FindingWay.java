import java.util.ArrayList;

public class FindingWay implements Runnable {
	// 왕, 맵 그래프, 시작위치, 탐색완료, 탐색대기
	King owner;
	GraphMap gMap;
	Land startLand;
	Block startBlock;
	//Frame frame;
	ArrayList<Block> completedList = new ArrayList<Block>();
	ArrayList<Block> waitingList = new ArrayList<Block>();

	public FindingWay(King owner, Land startLand) {
		this.owner = owner;
		this.gMap = this.owner.nationalMap;
		this.startLand = startLand;

	}

	@Override
	public void run() {
		boolean searching = true;
		int i;
		Block pointBlock;

		startBlock = this.gMap.findBlock(startLand.landNumber);
		this.waitingList.add(startBlock);

		while (searching) {
			if (Thread.interrupted()) { // 쓰레드 종료시키기
				break;
			}
			/// 시작 ///
//			System.out.println("findBlock");
			if (waitingList.size() != 0) {
				pointBlock = waitingList.get(0);

				if (pointBlock.preBlock0 != null)
					if (pointBlock.preBlock0.land.landOwner == this.owner) {
						if (completedList.contains(pointBlock.preBlock0) == false)
							if (waitingList.contains(pointBlock.preBlock0) == false)
								waitingList.add(pointBlock.preBlock0);
					}
				if (pointBlock.preBlock1 != null)
					if (pointBlock.preBlock1.land.landOwner == this.owner) {
						if (completedList.contains(pointBlock.preBlock1) == false)
							if (waitingList.contains(pointBlock.preBlock1) == false)
								waitingList.add(pointBlock.preBlock1);
					}
				if (pointBlock.nextBlock0 != null)
					if (pointBlock.nextBlock0.land.landOwner == this.owner) {
						if (completedList.contains(pointBlock.nextBlock0) == false)
							if (waitingList.contains(pointBlock.nextBlock0) == false)
								waitingList.add(pointBlock.nextBlock0);
					}
				if (pointBlock.nextBlock1 != null)
					if (pointBlock.nextBlock1.land.landOwner == this.owner) {
						if (completedList.contains(pointBlock.nextBlock1) == false)
							if (waitingList.contains(pointBlock.nextBlock1) == false)
								waitingList.add(pointBlock.nextBlock1);
					}

				completedList.add(pointBlock);
				waitingList.remove(pointBlock);
			} else {
				// 탐색이 끝났다.
				if (completedList.contains(this.gMap.startBlock)) {
					// 수도와 연결된다.
					for (i = 0; i < completedList.size(); i++) {
						// 리스트에 포함된 땅들도 수도와 연결된다.
						completedList.get(i).land.ReadWriteSupplyRoute("W", true);
						supplyGui(completedList.get(i).land.landNumber,true);
						//this.frame.labelLand[completedList.get(i).land.landNumber].setText("보급로 연결됨");
					}
				} else {
					// 수도와 연결되지 않는다.
					for (i = 0; i < completedList.size(); i++) {
						// 리스트에 포함된 땅들도 수도와 연결되지 않는다.
						completedList.get(i).land.ReadWriteSupplyRoute("W", false);
						supplyGui(completedList.get(i).land.landNumber,false);
						//this.frame.labelLand[completedList.get(i).land.landNumber].setText("보급로 끊어짐");
					}
				}
				searching = false; // 종료
				break;
			}
		} // end while(searching)
	}

	private void supplyGui(int landNum,boolean state) {
		this.owner.frameBlock[landNum].setSupply(state);
	}
}

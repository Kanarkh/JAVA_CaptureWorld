import java.util.ArrayList;

public class FindingWay implements Runnable {
	// ��, �� �׷���, ������ġ, Ž���Ϸ�, Ž�����
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
			if (Thread.interrupted()) { // ������ �����Ű��
				break;
			}
			/// ���� ///
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
				// Ž���� ������.
				if (completedList.contains(this.gMap.startBlock)) {
					// ������ ����ȴ�.
					for (i = 0; i < completedList.size(); i++) {
						// ����Ʈ�� ���Ե� ���鵵 ������ ����ȴ�.
						completedList.get(i).land.ReadWriteSupplyRoute("W", true);
						supplyGui(completedList.get(i).land.landNumber,true);
						//this.frame.labelLand[completedList.get(i).land.landNumber].setText("���޷� �����");
					}
				} else {
					// ������ ������� �ʴ´�.
					for (i = 0; i < completedList.size(); i++) {
						// ����Ʈ�� ���Ե� ���鵵 ������ ������� �ʴ´�.
						completedList.get(i).land.ReadWriteSupplyRoute("W", false);
						supplyGui(completedList.get(i).land.landNumber,false);
						//this.frame.labelLand[completedList.get(i).land.landNumber].setText("���޷� ������");
					}
				}
				searching = false; // ����
				break;
			}
		} // end while(searching)
	}

	private void supplyGui(int landNum,boolean state) {
		this.owner.frameBlock[landNum].setSupply(state);
	}
}

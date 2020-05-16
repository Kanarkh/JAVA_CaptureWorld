
/*
 * ���α׷� �̸� : ���� �����Ա�
 * ���α׷� ���� : GAME
 * ���α׷� ���� :
 * 1. �� ����, �� ��� �ʱ�ȭ
 * 2. ����(�÷��̾�, AI) ����
 * 3. ���� �ձ�-�� ��ġ  
 * 4. <���� ����>
 * 5. ���� �ൿ ���� ( 1.�ǹ� ���׷��̵�  2.���ֻ���  3.������ 4.���������ϱ�) (v)
 * 	5.1.1 �ձ��� �� �����Ͽ� ��ȭ�ȴ�.  (v)
 * 	5.2.1 ������ �ش� ������ ����Ѵ�.
 *  5.2.2 ������ �� �ϴ� �̵��ӵ���ŭ ĭ�� �̵��Ѵ�.
 *  5.2.3 ������ �ش� ������ �����Ѵ�.
 *  5.2.3.1 ���� ���� �ִٸ� ������ �����Ѵ�.
 *  5.2.3.2 ���� ���� ���ٸ� �ش������� �����Ѵ�.
 * 
 */

import java.awt.Color;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Scanner;

public class PlayGame_0 {

	// ANSI CODE ������ ǥ��
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BACK = "\u001B[47m"; // ������ ���
	public static final String ANSI_BOLD = "\u001B[47m"; // ����

	// play() ����
	int mapSize = 5;
	Scanner sc = new Scanner(System.in);
	Land[] land = new Land[mapSize * mapSize];

	King player;
	King aiPlayer;
//	King player = new King("", land[0], Color.red);
//	King player = new MerchantBornKing("", land[0], Color.red);
//	King player = new ThiefBornKing("", land[0], Color.red);
//	King player = new KnightBornKing("", land[0], Color.red);
//	King aiPlayer = new King("AI_1", land[24], Color.ORANGE);

	Soldier tempSoldier;

	ThreadGroup tg1 = new ThreadGroup("Group1");
	boolean employment = false;

	// gui
	GameFrame_1 gameFrame;
	FrameBlock[] fb;
	Ch te;
	// file
	LinkedList<String> readList = new LinkedList<String>();
	LinkedList<Integer> seekPoint = new LinkedList<Integer>();

	public void play() {

		if (te.origin.contentEquals("Knight"))
			player = new KnightBornKing("", land[0], Color.red);
		else if (te.origin.contentEquals("Merchant"))
			player = new MerchantBornKing("", land[0], Color.red);
		else if (te.origin.contentEquals("Thief"))
			player = new ThiefBornKing("", land[0], Color.red);

		int temp = (int) (Math.random() * 3);

		if (temp == 0)
			aiPlayer = new KnightBornKing("AI_1", land[0], Color.orange);
		else if (temp == 1)
			aiPlayer = new MerchantBornKing("AI_1", land[0], Color.orange);
		else if (temp == 2)
			aiPlayer = new ThiefBornKing("AI_1", land[0], Color.orange);

		
		// �ݺ��� ����
		int i;
		boolean gaming = true;
		// Ŭ���� ����
		String playerName;

		// game ���� ����
		int screenNum = 0;

//		System.out.print("�÷��̸� �Է� : ");
//		playerName = sc.next();
//		playerName = te.loginId;
//		System.out.println("����� �̸��� \"" + ANSI_BOLD + ANSI_BACK + ANSI_RED + playerName + ANSI_RESET + "\"�Դϴ�");

		// �� ����, �� ��� �ʱ�ȭ
		for (i = 0; i < land.length; i++) {
			land[i] = new Land(i, 1);
			fb[i].setLandGold(1);
			fb[i].setLandTopography(land[i].topography);
		}
										////////////////////////////////////////////////////////////////////////////////////��//////////////////////////
		player.changeHoldingGold(499);
		aiPlayer.changeHoldingGold(3);

//		Frame frame0 = new Frame(player, aiPlayer, null, null, land);
//		frame0.setThreadGroup(tg1);
//		frame0.SetFramBlock(this.fb);

//		for (i = 0; i < land.length; i++) {
//			land[i].frame = frame0;
//		}

		// ���� �ʱ�ȭ
		land[0].landOwner = player;
		land[24].landOwner = aiPlayer;
		player.name = te.name;
		player.currentLandNumber = land[0];
		land[0].supplyRoute = true;
		player.currentLandNumber.defensivePlayer.add(player);
		aiPlayer.currentLandNumber = land[24];
		land[24].supplyRoute = true;
		aiPlayer.currentLandNumber.defensivePlayer.add(aiPlayer);
		aiPlayer.aiOption = true;

		player.SetFramBlock(fb);
		aiPlayer.SetFramBlock(fb);

		player.setGameFrame(gameFrame);
		aiPlayer.setGameFrame(gameFrame);
		this.gameFrame.setPlayerGold(player.holdingGold);
		player.setNationlMap(player.currentLandNumber.landNumber, land, mapSize);
		aiPlayer.setNationlMap(aiPlayer.currentLandNumber.landNumber, land, mapSize);

		gameFrame.initGameGui(player, aiPlayer, land, tg1);

		this.fb[0].setLandColor(player.color);
		this.fb[0].setSupply(land[0].supplyRoute);
		this.fb[24].setLandColor(aiPlayer.color);
		this.fb[0].setSupply(land[24].supplyRoute);

		player.enemyKing=aiPlayer;
		aiPlayer.enemyKing =player;
		
		// ���� thread
		TaxGold taxGold = new TaxGold(player, aiPlayer, land, this.gameFrame);
		Thread taxThread = new Thread(tg1, taxGold);
//		taxThread.setDaemon(true);
		taxThread.start();

		Ai ai0 = new Ai(aiPlayer,player, land, 5);
		ai0.setThreadGroup(tg1);
		Thread ai0Thread = new Thread(tg1, ai0);
//		ai0Thread.setDaemon(true);
		ai0Thread.start();

		NativeCharacter nativeCharacter = new NativeCharacter(5, 7, land);
		nativeCharacter.SetFramBlock(fb);
		Thread nativeCharThread = new Thread(tg1, nativeCharacter);
//		nativeCharThread.setDaemon(true);
		nativeCharThread.start();

		while (gaming) {

			if (screenNum == 12) {
				gaming = false;
			} else if (screenNum == 11) {
				tempSoldier = player.createSoldier(1);
				tempSoldier.setLand(land);
				tempSoldier.targetLandNumber = 4;
				Thread tempThread = new Thread(tempSoldier);
				tempThread.start();
				System.out.println("����");
				tempSoldier = null;
			} else if (screenNum == 1) {
				if (ai0.targetNumList.size() != 0) {
					for (i = 0; i < ai0.targetNumList.size(); i++)
						System.out.println(ai0.targetNumList.get(i).BlockNum);
				} else {
					System.out.println("AI�� ��ǥ�� �����ϴ�.");
				}
			} else if (screenNum == 2) {
				System.out.println("�÷��̾� ü��:" + player.showLifeData());
				System.out.println("ai�÷��̾� ü��:" + aiPlayer.showLifeData());
			} else if (screenNum == 3) {
				System.out.println(tg1.activeCount());
				tg1.interrupt();
				System.out.println(tg1.activeCount());
			}

			if (player.showLifeData() <= 0) {
				System.out.println(aiPlayer.name + "�� �¸��߽��ϴ�.");
				this.gameFrame.setPlayerStat(player);
				this.gameFrame.setPlayerStat(aiPlayer);
				recordResult(false);
				tg1.interrupt();
				te.i=4;
				break;
			} else if (aiPlayer.showLifeData() <= 0) {
				System.out.println(player.name + "�� �¸��߽��ϴ�.");
				tg1.interrupt();
				this.gameFrame.setPlayerStat(player);
				this.gameFrame.setPlayerStat(aiPlayer);
				recordResult(true);
				
				te.i=3;
				break;
			}

		}

		//System.out.println("������ ����Ǿ����ϴ�."); // ة �� �� ܲ ��
		this.gameFrame.dispose();

		sc.close();
		tg1.interrupt();
		System.out.println(tg1.activeCount());
	}

	public void recordResult(boolean victory) {

		if (victory == true) {
			try {

				RandomAccessFile readFile = new RandomAccessFile("dataTest.txt", "rw");

				while (readFile.getFilePointer() != readFile.length()) {
					seekPoint.add((int) readFile.getFilePointer());
					readList.add(readFile.readLine());

				}

				for (int i = 0; i < readList.size(); i++) {
					int idx = readList.get(i).indexOf("@");
					String id = readList.get(i).substring(0, idx);

					if (id.equals(te.loginId)) {
						System.out.println("ã�⼺��");
						int widx = readList.get(i).indexOf("!");
						int lidx = readList.get(i).indexOf("%");

						String win = readList.get(i).substring(widx + 1, lidx);
						String lose = readList.get(i).substring(lidx + 1);

						win = String.valueOf((Integer.parseInt(win) + (int) 1));

						readFile.seek((long) seekPoint.get(i) + widx + 1);
						readFile.writeBytes(String.valueOf(win) + "%" + String.valueOf(lose));

						readFile.close();
						break;
					}

				}

			} catch (IOException e) {

			}
		} else {
			// �й�
			try {

				RandomAccessFile readFile = new RandomAccessFile("dataTest.txt", "rw");

				while (readFile.getFilePointer() != readFile.length()) {
					seekPoint.add((int) readFile.getFilePointer());
					readList.add(readFile.readLine());

				}

				for (int i = 0; i < readList.size(); i++) {
					int idx = readList.get(i).indexOf("@");
					String id = readList.get(i).substring(0, idx);

					if (id.equals(te.loginId)) {
						System.out.println("ã�⼺��");
						int widx = readList.get(i).indexOf("!");
						int lidx = readList.get(i).indexOf("%");

						String win = readList.get(i).substring(widx + 1, lidx);
						String lose = readList.get(i).substring(lidx + 1);

						lose = String.valueOf((Integer.parseInt(lose) + (int) 1));

						readFile.seek((long) seekPoint.get(i) + widx + 1);
						readFile.writeBytes(String.valueOf(win) + "%" + String.valueOf(lose));

						readFile.close();
						break;
					}

				}

			} catch (IOException e) {

			}
		}
	}

	public void SetFramBlock(FrameBlock[] frameBlock, Ch te) {
		this.te = te;
		this.gameFrame = new GameFrame_1(frameBlock, te);
		this.fb = frameBlock;

		for (int i = 0; i < fb.length; i++) {
			fb[i].setLandNum(i);
			fb[i].setLandOwner("���ξ���");
			fb[i].setLandGold(1);
			fb[i].setLandTopography("");
			fb[i].setSupply(false);
			fb[i].setBuilding(false, "����Ʈ");
			fb[i].setDefensive(0);
			fb[i].setOffensive(0);
			fb[i].setLandColor(Color.white);
		}
	}

}// end PlayGame_0

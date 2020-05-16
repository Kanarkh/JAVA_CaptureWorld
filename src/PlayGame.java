

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

/*
import java.util.ArrayList;
import java.util.Scanner;

public class PlayGame {
	//ANSI CODE ������ ǥ��
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BACK = "\u001B[47m"; //������ ���
	public static final String ANSI_BOLD = "\u001B[47m"; //����
	
	Scanner sc = new Scanner(System.in);
	Land[] land = new Land[9];
	King player = new King("", land[0]);
	King aiPlayer = new King("AI_1", land[8]);
	ArrayList<Soldier> soldierList = new ArrayList<Soldier>();
	Soldier tempSoldier;
	BombingSoldier tempBombingSoldier;
	SpySoldier tempSpySoldier;

	boolean employment = false;

	public void play() {

		// �ݺ��� ����
		int i;
		boolean gaming = true;

		// Ŭ���� ����
		String playerName;

		// game ���� ����
		int screenNum = 0;
		boolean screenNext = true;

		int temp = 0;

		System.out.print("�÷��̸� �Է� : ");
//		playerName = sc.next();
		playerName = "���û���";
		System.out.println("����� �̸��� \""+ANSI_BOLD+ANSI_BACK+ANSI_RED + playerName+ANSI_RESET + "\"�Դϴ�");

		// �� ����, �� ��� �ʱ�ȭ

		for (i = 0; i < 9; i++) {
			if (i == 0 || i == 1 || i == 3 || i == 5 || i == 7 || i == 8)
				land[i] = new Land(i, 1);
			else if (i == 2 || i == 6)
				land[i] = new Land(i, 2);
			else if (i == 4)
				land[i] = new Land(i, 3);
		} //

		// ���� �ʱ�ȭ
		land[0].landOwner = player;
		land[8].landOwner = aiPlayer;
		player.currentLandNumber = land[0];
		player.name = playerName;
		aiPlayer.currentLandNumber = land[8];

		// ���� ����
		player.holdingGold = 40;// �׽�Ʈ ���
		aiPlayer.holdingGold = 4;
		// ��ȣ���� -> �ش� ��ȣ �̺�Ʈ ó�� (��ȯ)
		// ����ó���� �ȵȴ�. �ٸ� ��ȣ�� ������ ���������.
		while (gaming) {

			if (screenNext == true) {
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNext = false;
			}

			if (screenNum == 1) { // ����
				displayScreen(screenNum);
				screenNum = sc.nextInt();

				screenNum += 10;

			}

			if (screenNum == 10) { // ����ȭ��
				screenNext = true;
				screenNum = 0;

			} else if (screenNum >= 11 && screenNum <= 15) { // ���ֻ����ϱ�
				createSoldier(screenNum);

				if (employment == true && soldierList.size() > 0) {
					System.out.print("�ش� ���縦 ��������� �����ðڽ��ϱ�?");

					temp = sc.nextInt();
					while (temp > 8 || temp <= 0) {
						System.out.println("�ش� ��ġ���� ��ġ�Ҽ� �����ϴ�. �ٽ� �Է����ּ���");
						temp = sc.nextInt();
					}
					employment = false;
					player.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), temp);
				}
				screenNum = 1; // ���� ¡�� �ݺ�

			}

			if (screenNum == 2) { // ���� �Ʒ�
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNum += 20;
			}
			if (screenNum == 20) { // ����ȭ��

				screenNext = true;
				screenNum = 0;

			} else if (screenNum == 21) { // ���� �Ʒ��ϱ�
				kingLevelUp();
				screenNext = true;
				screenNum = 0;
			}

			if (screenNum == 3) { // �ձ� ����
				displayScreen(screenNum);

				screenNum = sc.nextInt();
				screenNum += 30;
			}
			if (screenNum == 30) { // ����ȭ��

				screenNext = true;
				screenNum = 0;

			} else if (screenNum == 31) { // �ձ����� _ �ϰ�� ���
				///////////////////
				KingdomUpgrade();
				///////////////////
				screenNext = true;
				screenNum = 0;
			}
			if (screenNum == 4) { // ������ ǥ��
				displayScreen(screenNum);
				screenNext = true;
				screenNum = 0;
			}

			if (screenNum == 5) { // ������
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNum += 50;
			}
			if (screenNum == 50) { // ����ȭ��
				screenNext = true;
				screenNum = 0;
			} else if (screenNum == 51) { // �������ϱ�
				aiPlay(aiPlayer);
				turnEnd();
				gaming = whoWon();
				screenNext = true;
				screenNum = 0;
			}

			if (screenNum == 6) { // ��������
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNum += 60;
			}
			if (screenNum == 60) { // ����ȭ��
				screenNext = true;
				screenNum = 0;
			} else if (screenNum == 61) { // �����մϴ�
				gaming = false;
			}

		}
		System.out.println("������ ����Ǿ����ϴ�."); // ة �� �� ܲ ��

		sc.close();
	}

	// ���� ����� ��ġ
	public void createSoldier(int screenNum) {
		System.out.println("���� �����");
		if (screenNum == 11) {
			if (player.holdingGold >= 2) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"���� �����մϴ�"+ANSI_RESET);

		} else if (screenNum == 12) {
			if (player.holdingGold >= 3) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"���� �����մϴ�"+ANSI_RESET);

		} else if (screenNum == 13) {
			if (player.holdingGold >= 4) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"���� �����մϴ�"+ANSI_RESET);

		} else if (screenNum == 14) {
			if (player.holdingGold >= 10) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"���� �����մϴ�"+ANSI_RESET);
		} else if (screenNum == 15) {
			if (player.holdingGold >= 10) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"���� �����մϴ�"+ANSI_RESET);
		}
	}

	// �ձ� ����
	public void KingdomUpgrade() {
		System.out.println("�ձ�����");
		if (player.holdingGold >= 10)
			player.kingdomUpgrade();
		else
			System.out.println(ANSI_GREEN+"���� �����մϴ�"+ANSI_RESET);
	}

	// ���� �Ʒ�
	public void kingLevelUp() {
		System.out.println("�����Ʒ�");
		if (player.holdingGold >= 5)
			player.kingLevelUpgrade();
		else
			System.out.println(ANSI_GREEN+"���� �����մϴ�"+ANSI_RESET);
	}

	// �� ����
	public void turnEnd() {

		// ������� ����� �ൿ
		for (int i = 0; i < soldierList.size(); i++) {
//			if (soldierList.get(i).name.equals("��ź��")) {
//				tempBombingSoldier =(BombingSoldier)soldierList.get(i);
//				tempBombingSoldier.suicideBombing();
//			} else if (soldierList.get(i).name.equals("���ۿ�")) {
//				tempSpySoldier =(SpySoldier) soldierList.get(i);
//				tempSpySoldier.PlotRevolt();
//			} else
			soldierList.get(i).action(land);
			// ���⼭ ������ �����ϸ� �ε����� �ٲ�� �Ǽ� ���δ�.
		}
		for (int i = soldierList.size() - 1; i >= 0; i--) {
			if (soldierList.get(i).life <= 0)
				soldierList.remove(i);

		}

		// ��� �й�
		turnGold();
	}

	public void turnGold() {
		for (int i = 0; i < 9; i++) {
			if (land[i].landOwner != null) {
				land[i].landOwner.holdingGold += land[i].turnGold;
			}
		}
	}

	public void aiPlay(King ai) {
		if (aiPlayer.holdingGold > 2) { // if�� �������..
			if (land[7].landOwner != aiPlayer) {
				if (aiPlayer.holdingGold >= 2) {
					soldierList.add(aiPlayer.createSoldier(1));
					aiPlayer.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), 7);
				}
			}
			if (land[4].landOwner != aiPlayer) {
				if (aiPlayer.holdingGold >= 2) {
					soldierList.add(aiPlayer.createSoldier(1));
					aiPlayer.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), 4);
				}
			}
			if (land[1].landOwner != aiPlayer) {
				if (aiPlayer.holdingGold >= 2) {
					soldierList.add(aiPlayer.createSoldier(1));
					aiPlayer.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), 3);
				}
			}
			if (land[5].landOwner != aiPlayer) {
				if (aiPlayer.holdingGold >= 2) {
					soldierList.add(aiPlayer.createSoldier(1));
					aiPlayer.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), 5);
				}
			}
			if (land[2].landOwner != aiPlayer) {
				if (aiPlayer.holdingGold >= 2) {
					soldierList.add(aiPlayer.createSoldier(1));
					aiPlayer.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), 6);
				}
			}
			if (player.life <= (aiPlayer.holdingGold / 2) * 2) {
				while (aiPlayer.holdingGold >= 2) {
					soldierList.add(aiPlayer.createSoldier(1));
					aiPlayer.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), 0);
				}
			}

		}
	}

	public boolean whoWon() {
		boolean gaming = true;
		if (land[0].landOwner != player && land[8].landOwner != aiPlayer) {
			System.out.println("���º�!");
			gaming = false;
		} else if (land[0].landOwner != player) {
			System.out.println(player.name + "���� �׾����ϴ�.");
			System.out.println(aiPlayer.name + "���� �¸��Դϴ�.");
			gaming = false;
		} else if (land[8].landOwner != aiPlayer) {
			System.out.println(aiPlayer.name + "���� �׾����ϴ�.");
			System.out.println(player.name + "���� �¸��Դϴ�.");
			gaming = false;
		}
		return gaming;
	}

	public void displayScreen(int num) {
		if (num == 0) {
			System.out.println("<�ൿ����>");
			System.out.println("(1.����¡�� / 2.�����Ʒ� / 3.�ձ����� / 4.������ / 5.������ / 6.����) ");
		} else if (num == 1) {
			System.out.println("<����¡��>");
			System.out.println("1.����(Attack:2, Life:4) -2G");
			System.out.println("2.���к�(Attack:0, Life:6) -3G");
			System.out.println("3.�⺴(Attack:3, Life:3) -4G");
			System.out.println("4.��ź��(Attack:1, Life:1) -10G #��ų : ��ǥ ���� ��� ���翡�� �ڽ��� �ִ� Life ���ݸ��� ���ظ� �ش� ");
			System.out.println("5.���ۿ�(Attack:0, Life:1) -10G #��ų : 30%�� Ȯ���� ��ǥ ���� ���縦 �ڽ��� �ձ��� ���Խ�Ų�� ");
			System.out.println("[�ݺ����� :" + player.holdingGold + "]");
			System.out.println("0.����ȭ��  1.���� ����  2.���к� ����  3.�⺴ ����  4.��ź�� ����  5.���ۿ� ����");
		} else if (num == 2) {
			System.out.println("<�����Ʒ�>");
			System.out.println("�ձ� ���ݷ� :" + player.attack + ", �ձ� ü�� : " + player.life);
			System.out.println("���� �Ʒ� : ������ ���ݷ� +1, ü��+3");
			System.out.println("�ݺ����� :" + player.holdingGold + ", �ʿ��� : 5");
			System.out.println("0.����ȭ�� 1.���׷��̵� �ϱ�");
		} else if (num == 3) {
			System.out.println("<�ձ�����>");
			System.out.println("�ձ������� : �ϰ�  +1");
			System.out.println("�ݺ����� :" + player.holdingGold + ", �ʿ��� : 10");
			System.out.println("�ձ� �ϰ�� :" + player.currentLandNumber.turnGold);
			System.out.println("0.����ȭ�� 1.���׷��̵� �ϱ�");
		} else if (num == 4) {
			System.out.println("<������ Ȯ��>");
			displayLand();
		} else if (num == 5) {
			System.out.println("<������>");
			System.out.println("���� �ѱ�ðڽ��ϱ�?");
			System.out.println("0.����ȭ�� 1.��");
		} else if (num == 6) {
			System.out.println("<��������>");
			System.out.println("��������� ��� ����� ������� �ʽ��ϴ�.");
			System.out.println("0.����ȭ�� 1.�����մϴ�.");
		}
	}

	public void displayLand() {
		for (int i = 0; i < 9; i++) {
			System.out.print(", ����ȣ :");
			System.out.print(land[i].landNumber);
			System.out.print("������ :");
			if (land[i].landOwner == null)
				System.out.print("���� ����");
			else {
				if(land[i].landOwner==player)
				System.out.print(ANSI_BOLD+ANSI_BACK+ANSI_RED+land[i].landOwner.name+ANSI_RESET);
				else
					System.out.print(ANSI_BOLD+ANSI_BACK+ANSI_BLUE+land[i].landOwner.name+ANSI_RESET);
			}
			System.out.print("   ");
			System.out.print(", �ϰ�� :");
			System.out.print(land[i].turnGold);
			System.out.print(", ���� ���ݷ� :");
			if (land[i].defensiveSoldier == null)
				System.out.print("��ġ�� ���� ����");
			else
				System.out.print(land[i].defensiveSoldier.attack);

			System.out.print(", ���� ü�� :");
			if (land[i].defensiveSoldier == null)
				System.out.println("��ġ�� ���� ����");
			else
				System.out.println(land[i].defensiveSoldier.life);
		}
	}

}
*/
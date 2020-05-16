

/*
 * 프로그램 이름 : 전략 땅따먹기
 * 프로그램 목적 : GAME
 * 프로그램 순서 :
 * 1. 땅 생성, 땅 골드 초기화
 * 2. 유저(플레이어, AI) 생성
 * 3. 유저 왕국-땅 배치  
 * 4. <게임 시작>
 * 5. 유저 행동 선택 ( 1.건물 업그레이드  2.유닛생성  3.턴종료 4.게임종료하기) (v)
 * 	5.1.1 왕국이 이 다음턴에 강화된다.  (v)
 * 	5.2.1 유닛이 해당 지역을 출발한다.
 *  5.2.2 유닛이 한 턴당 이동속도만큼 칸을 이동한다.
 *  5.2.3 유닛이 해당 지역에 도착한다.
 *  5.2.3.1 만약 적이 있다면 전투를 실행한다.
 *  5.2.3.2 만약 적이 없다면 해당지역을 차지한다.
 * 
 */

/*
import java.util.ArrayList;
import java.util.Scanner;

public class PlayGame {
	//ANSI CODE 색정보 표기
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BACK = "\u001B[47m"; //바탕색 흰색
	public static final String ANSI_BOLD = "\u001B[47m"; //굵게
	
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

		// 반복문 변수
		int i;
		boolean gaming = true;

		// 클레스 변수
		String playerName;

		// game 진행 변수
		int screenNum = 0;
		boolean screenNext = true;

		int temp = 0;

		System.out.print("플레이명 입력 : ");
//		playerName = sc.next();
		playerName = "낮올빼미";
		System.out.println("당신의 이름은 \""+ANSI_BOLD+ANSI_BACK+ANSI_RED + playerName+ANSI_RESET + "\"입니다");

		// 땅 생성, 땅 골드 초기화

		for (i = 0; i < 9; i++) {
			if (i == 0 || i == 1 || i == 3 || i == 5 || i == 7 || i == 8)
				land[i] = new Land(i, 1);
			else if (i == 2 || i == 6)
				land[i] = new Land(i, 2);
			else if (i == 4)
				land[i] = new Land(i, 3);
		} //

		// 국왕 초기화
		land[0].landOwner = player;
		land[8].landOwner = aiPlayer;
		player.currentLandNumber = land[0];
		player.name = playerName;
		aiPlayer.currentLandNumber = land[8];

		// 게임 시작
		player.holdingGold = 40;// 테스트 골드
		aiPlayer.holdingGold = 4;
		// 번호선택 -> 해당 번호 이벤트 처리 (순환)
		// 예외처리가 안된다. 다른 번호를 누르면 멈춰버린다.
		while (gaming) {

			if (screenNext == true) {
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNext = false;
			}

			if (screenNum == 1) { // 유닛
				displayScreen(screenNum);
				screenNum = sc.nextInt();

				screenNum += 10;

			}

			if (screenNum == 10) { // 이전화면
				screenNext = true;
				screenNum = 0;

			} else if (screenNum >= 11 && screenNum <= 15) { // 유닛생산하기
				createSoldier(screenNum);

				if (employment == true && soldierList.size() > 0) {
					System.out.print("해당 병사를 어느땅으로 보내시겠습니까?");

					temp = sc.nextInt();
					while (temp > 8 || temp <= 0) {
						System.out.println("해당 위치에는 배치할수 없습니다. 다시 입력해주세요");
						temp = sc.nextInt();
					}
					employment = false;
					player.TransferCommandSoldier(soldierList.get(soldierList.size() - 1), temp);
				}
				screenNum = 1; // 병사 징집 반복

			}

			if (screenNum == 2) { // 국왕 훈련
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNum += 20;
			}
			if (screenNum == 20) { // 이전화면

				screenNext = true;
				screenNum = 0;

			} else if (screenNum == 21) { // 국왕 훈련하기
				kingLevelUp();
				screenNext = true;
				screenNum = 0;
			}

			if (screenNum == 3) { // 왕국 발전
				displayScreen(screenNum);

				screenNum = sc.nextInt();
				screenNum += 30;
			}
			if (screenNum == 30) { // 이전화면

				screenNext = true;
				screenNum = 0;

			} else if (screenNum == 31) { // 왕국발전 _ 턴골드 상승
				///////////////////
				KingdomUpgrade();
				///////////////////
				screenNext = true;
				screenNum = 0;
			}
			if (screenNum == 4) { // 맵정보 표시
				displayScreen(screenNum);
				screenNext = true;
				screenNum = 0;
			}

			if (screenNum == 5) { // 턴종료
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNum += 50;
			}
			if (screenNum == 50) { // 이전화면
				screenNext = true;
				screenNum = 0;
			} else if (screenNum == 51) { // 턴종료하기
				aiPlay(aiPlayer);
				turnEnd();
				gaming = whoWon();
				screenNext = true;
				screenNum = 0;
			}

			if (screenNum == 6) { // 게임종료
				displayScreen(screenNum);
				screenNum = sc.nextInt();
				screenNum += 60;
			}
			if (screenNum == 60) { // 이전화면
				screenNext = true;
				screenNum = 0;
			} else if (screenNum == 61) { // 종료합니다
				gaming = false;
			}

		}
		System.out.println("게임이 종료되었습니다."); // 馬 爆 防 兵 諜

		sc.close();
	}

	// 병사 만들고 배치
	public void createSoldier(int screenNum) {
		System.out.println("병사 만들기");
		if (screenNum == 11) {
			if (player.holdingGold >= 2) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"금이 부족합니다"+ANSI_RESET);

		} else if (screenNum == 12) {
			if (player.holdingGold >= 3) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"금이 부족합니다"+ANSI_RESET);

		} else if (screenNum == 13) {
			if (player.holdingGold >= 4) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"금이 부족합니다"+ANSI_RESET);

		} else if (screenNum == 14) {
			if (player.holdingGold >= 10) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"금이 부족합니다"+ANSI_RESET);
		} else if (screenNum == 15) {
			if (player.holdingGold >= 10) {
				employment = true;
				soldierList.add(player.createSoldier(screenNum - 10));
			} else
				System.out.println(ANSI_GREEN+"금이 부족합니다"+ANSI_RESET);
		}
	}

	// 왕국 발전
	public void KingdomUpgrade() {
		System.out.println("왕국발전");
		if (player.holdingGold >= 10)
			player.kingdomUpgrade();
		else
			System.out.println(ANSI_GREEN+"금이 부족합니다"+ANSI_RESET);
	}

	// 국왕 훈련
	public void kingLevelUp() {
		System.out.println("국왕훈련");
		if (player.holdingGold >= 5)
			player.kingLevelUpgrade();
		else
			System.out.println(ANSI_GREEN+"금이 부족합니다"+ANSI_RESET);
	}

	// 턴 종료
	public void turnEnd() {

		// 턴종료시 병사들 행동
		for (int i = 0; i < soldierList.size(); i++) {
//			if (soldierList.get(i).name.equals("폭탄병")) {
//				tempBombingSoldier =(BombingSoldier)soldierList.get(i);
//				tempBombingSoldier.suicideBombing();
//			} else if (soldierList.get(i).name.equals("공작원")) {
//				tempSpySoldier =(SpySoldier) soldierList.get(i);
//				tempSpySoldier.PlotRevolt();
//			} else
			soldierList.get(i).action(land);
			// 여기서 병력을 삭제하면 인덱스가 바뀌게 되서 꼬인다.
		}
		for (int i = soldierList.size() - 1; i >= 0; i--) {
			if (soldierList.get(i).life <= 0)
				soldierList.remove(i);

		}

		// 골드 분배
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
		if (aiPlayer.holdingGold > 2) { // if를 써야할지..
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
			System.out.println("무승부!");
			gaming = false;
		} else if (land[0].landOwner != player) {
			System.out.println(player.name + "왕이 죽었습니다.");
			System.out.println(aiPlayer.name + "왕의 승리입니다.");
			gaming = false;
		} else if (land[8].landOwner != aiPlayer) {
			System.out.println(aiPlayer.name + "왕이 죽었습니다.");
			System.out.println(player.name + "왕의 승리입니다.");
			gaming = false;
		}
		return gaming;
	}

	public void displayScreen(int num) {
		if (num == 0) {
			System.out.println("<행동선택>");
			System.out.println("(1.병사징집 / 2.국왕훈련 / 3.왕국발전 / 4.맵정보 / 5.턴종료 / 6.종료) ");
		} else if (num == 1) {
			System.out.println("<병사징집>");
			System.out.println("1.보병(Attack:2, Life:4) -2G");
			System.out.println("2.방패병(Attack:0, Life:6) -3G");
			System.out.println("3.기병(Attack:3, Life:3) -4G");
			System.out.println("4.폭탄병(Attack:1, Life:1) -10G #스킬 : 목표 땅의 모든 병사에게 자신의 최대 Life 절반만금 피해를 준다 ");
			System.out.println("5.공작원(Attack:0, Life:1) -10G #스킬 : 30%의 확률로 목표 땅의 병사를 자신의 왕국에 편입시킨다 ");
			System.out.println("[금보유량 :" + player.holdingGold + "]");
			System.out.println("0.이전화면  1.보병 생산  2.방패병 생산  3.기병 생산  4.폭탄병 생산  5.공작원 생산");
		} else if (num == 2) {
			System.out.println("<국왕훈련>");
			System.out.println("왕국 공격력 :" + player.attack + ", 왕국 체력 : " + player.life);
			System.out.println("국왕 훈련 : 국왕의 공격력 +1, 체력+3");
			System.out.println("금보유량 :" + player.holdingGold + ", 필요골드 : 5");
			System.out.println("0.이전화면 1.업그레이드 하기");
		} else if (num == 3) {
			System.out.println("<왕국발전>");
			System.out.println("왕국발전시 : 턴골  +1");
			System.out.println("금보유량 :" + player.holdingGold + ", 필요골드 : 10");
			System.out.println("왕국 턴골드 :" + player.currentLandNumber.turnGold);
			System.out.println("0.이전화면 1.업그레이드 하기");
		} else if (num == 4) {
			System.out.println("<맵정보 확인>");
			displayLand();
		} else if (num == 5) {
			System.out.println("<턴종료>");
			System.out.println("턴을 넘기시겠습니까?");
			System.out.println("0.이전화면 1.네");
		} else if (num == 6) {
			System.out.println("<게임종료>");
			System.out.println("게임종료시 모든 기록은 저장되지 않습니다.");
			System.out.println("0.이전화면 1.종료합니다.");
		}
	}

	public void displayLand() {
		for (int i = 0; i < 9; i++) {
			System.out.print(", 땅번호 :");
			System.out.print(land[i].landNumber);
			System.out.print("소유주 :");
			if (land[i].landOwner == null)
				System.out.print("주인 없음");
			else {
				if(land[i].landOwner==player)
				System.out.print(ANSI_BOLD+ANSI_BACK+ANSI_RED+land[i].landOwner.name+ANSI_RESET);
				else
					System.out.print(ANSI_BOLD+ANSI_BACK+ANSI_BLUE+land[i].landOwner.name+ANSI_RESET);
			}
			System.out.print("   ");
			System.out.print(", 턴골드 :");
			System.out.print(land[i].turnGold);
			System.out.print(", 병사 공격력 :");
			if (land[i].defensiveSoldier == null)
				System.out.print("배치된 병력 없음");
			else
				System.out.print(land[i].defensiveSoldier.attack);

			System.out.print(", 병사 체력 :");
			if (land[i].defensiveSoldier == null)
				System.out.println("배치된 병력 없음");
			else
				System.out.println(land[i].defensiveSoldier.life);
		}
	}

}
*/
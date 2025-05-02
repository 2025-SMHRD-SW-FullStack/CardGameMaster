package model;

import model.dao.CardDAO;
import model.dto.CardDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.GameController;

public class Player {
	// 필드
	public int hp = 100; // 플레이어 체력
	public boolean stunned; // 스턴상태여부
	public boolean healBlocked; // 힐차단 상태여부
	public List<CardDTO> deck = new ArrayList<>(); // 댁
	public List<CardDTO> hand = new ArrayList<>(); // 플레이어 핸드

	// getCardsByPlayerId 외부 메서드를 사용하기위해 객체생성
	CardDAO carddao = new CardDAO();

	// 생성자
	public Player(int hp, boolean stunned, boolean healBlocked, List<CardDTO> deck, List<CardDTO> hand) {
		this.hp = hp;
		this.stunned = stunned;
		this.healBlocked = healBlocked;
		this.deck = deck;
		this.hand = hand;
	}

	public Player() {

	}

	// 메서드
	public void atStartGame() {
		List<CardDTO> allCards = carddao.getCardsByPlayerId(); // DB에서 모든 카드를 가져오는 메서드

		// 카드셔플
		Collections.shuffle(allCards);
		// 셔플한 카드 덱 추가
		deck.addAll(allCards);

		// 부족한 수만큼 랜덤으로 중복 채우기
		Random rand = new Random();
		while (deck.size() < 30) {
			CardDTO randomCard = allCards.get(rand.nextInt(allCards.size()));
			deck.add(randomCard); // 복사본이 아니라 같은 인스턴스 넣는 걸 주의
		}

		// 나 객체의 hand에 덱의 카드 3장을 뽑기 (즉, 3장은 덱에서 제거돼야 함)

		for (int i = 0; i < 3; i++) {
			CardDTO carddto = deck.remove(0);
			hand.add(carddto);
		}

	}

	// 카드 드로우
	public void drawCard() {
		if (!deck.isEmpty()) {
			hand.add(deck.remove(0));
		}
	}

	public void displayAllCards() {
		String[] lines = new String[7];
		for (int i = 0; i < lines.length; i++)
			lines[i] = "";

		for (int i = 0; i < hand.size(); i++) {
			CardDTO c = hand.get(i);
			String name = c.getName();
			String type = c.getType();
			int value = c.getValue();

			boolean isAtk = type.equals("ATK");
			lines[0] += "┌────────────┐ ";
			lines[1] += String.format("│ %-7s│ ", name);
			lines[2] += "│            │ ";
			lines[3] += String.format("│   %-9s│ ", isAtk ? "DAMAGE" : "HEAL");
			lines[4] += String.format("│   Power %3d│ ", value);
			lines[5] += String.format("│   (%d) %-5s│ ", i + 1, type);
			lines[6] += "└────────────┘ ";
		}

		for (String line : lines) {
			System.out.println(line);
		}
	}

	public void displayCard(String name, String type, int value) {
		boolean isAtk = type.equals("ATK");

		System.out.println("┌────────────┐");
		System.out.printf("│ %-7s│\n", name);
		System.out.println("│            │");
		System.out.printf("│   %-8s│\n", isAtk ? "DAMAGE" : "HEAL");
		System.out.printf("│   Power %2d │\n", value);
		System.out.println("│            │");
		System.out.printf("│    %-6s │\n", type);
		System.out.println("└────────────┘");
	}

	public void displayCard2(String name, String type, int value) {
		int innerWidth = 12; // ┌───┐ 테두리 안쪽 길이
		System.out.println("┌────────────┐");
		printLine(name, innerWidth);
		System.out.println("│            │");
		printLine(type.equals("ATK") ? "DAMAGE" : "HEAL", innerWidth);
		printLine("Power " + value, innerWidth);
		System.out.println("│            │");
		printLine(type, innerWidth);
		System.out.println("└────────────┘");
	}

	// 한글/영문 모두 출력 폭 계산하고 줄바꿈
	private void printLine(String content, int innerWidth) {
		int currentWidth = 0;
		StringBuilder line = new StringBuilder();

		for (char c : content.toCharArray()) {
			int charWidth = getDisplayWidth(String.valueOf(c));
			if (currentWidth + charWidth > innerWidth) {
				System.out.printf("│%s│\n", padRight(line.toString(), innerWidth));
				line.setLength(0);
				currentWidth = 0;
			}
			line.append(c);
			currentWidth += charWidth;
		}

		System.out.printf("│%s│\n", padRight(line.toString(), innerWidth));
	}

	// 출력 폭 계산: 한글은 2칸, 영문/숫자/기호는 1칸
	private int getDisplayWidth(String str) {
		int width = 0;
		for (char c : str.toCharArray()) {
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_SYLLABLES
					|| Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_JAMO) {
				width += 2;
			} else {
				width += 1;
			}
		}
		return width;
	}

	private String padRight(String str, int totalWidth) {
		int displayWidth = getDisplayWidth(str);
		int padding = totalWidth - displayWidth;
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < padding; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	// 사용한 카드 능력을 쓰고 사용한 카드는 버리기
	public void playCard(Player self, Player opponent) {
		Scanner sc = new Scanner(System.in);

		// 1. 카드 4장 모두 출력
		displayAllCards();

		// 10초짜리 타이머 시작
		GameController.getInstance().startTurnTimer(10);

		if (hand.isEmpty()) {
			System.out.println("핸드에 카드가 없습니다.");
			return;
		}

		// 2. 유저가 카드 선택
		int choice = 0;
		do {
			System.out.println();
			try {
				System.out.print("사용할 카드 선택 (1~" + hand.size() + ")");
				choice = sc.nextInt() - 1;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요!");
			}

			if (GameController.getInstance().getTimeOver()) {
				System.out.println("이미 턴이 넘어갔습니다. 입력한 내용은 무시됩니다.");
				return;
			}

			if (choice < 0 || choice >= hand.size())
				System.out.println("손에 존재하지 않는 카드입니다. 다시 선택해주세요!");
		} while (choice < 0 || choice >= hand.size());

		// 타이머 종료 전에 끝났으므로 타이머도 정지
		GameController.getInstance().stopTurnTimer();

		// 3. 선택한 카드만 다시 강조 출력
		CardDTO selected = hand.remove(choice);
		System.out.println("\n[선택한 카드]");
		displayCard2(selected.getName(), selected.getType(), selected.getValue());

		useCard(selected, self, opponent);
	}

	public void botCard(Player self, Player opponent) {
		if (hand.isEmpty())
			return;

		int choice = 0;
		CardDTO cdto = hand.remove(choice);

		try {
			System.out.println("\n[봇이 선택한 카드]");
			Thread.sleep(2000);

			displayCard2(cdto.getName(), cdto.getType(), cdto.getValue());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		useCard(cdto, self, opponent);
	}

	public void useCard(CardDTO cdto, Player p1, Player p2) {
		Card card = null;
		switch (cdto.getType().toUpperCase()) {
		case "ATK":
			card = new AttackCard(cdto.getName(), cdto.getValue());
			break;
		case "HEAL":
			card = new HealCard(cdto.getName(), cdto.getValue());
			break;
		case "HOLE":
			card = new StunTrapCard(cdto.getName(), cdto.getValue(), cdto.getInfo());
			break;
		case "HOLE_H":
			card = new HealBlockCard(cdto.getName(), cdto.getValue());
			break;
		}

		if (card != null)
			card.use(p1, p2);
	}

	// 체력이 0이상인지 확인
	public boolean isAlive() {

		return hp > 0;
	}

}
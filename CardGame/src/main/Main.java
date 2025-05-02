package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import javazoom.jl.player.MP3Player;
import model.Player;
import view.GameView;

public class Main {

	public static void main(String[] args) {
		GameView view = new GameView();
		Scanner sc = new Scanner(System.in);
		Player play = new Player();
		Player bot = new Player();
		MP3Player mp3 = new MP3Player();
		// 프로젝트 안에 music 파일 관리 시 주소를 통한 음악 재생
		mp3.play("./유희왕 - 열정적인 듀얼리스트들.mp3");
		view.title();
		while (true) {
			try {
				
				System.out.println("\n\t** zi존 카드마스터 **\n");
                System.out.println("\n┌──────────────────────────────────┐");
                System.out.println("\t[1] 로그인 [2] 회원가입 ");
                System.out.print("\tchoice : ");
                int choice = sc.nextInt();
                System.out.println("└──────────────────────────────────┘\n");
				
				if (choice == 1) {
					// 로그인 후 게임 시작
					if (view.login())
						break;
					;
				} else if (choice == 2) {
					// 회원가입
					view.join();
				} else {
					System.out.println("\n┌────────────────────────┐");
                    System.out.println("[1], [2]중 선택해주세요.");
                    System.out.println("└────────────────────────┘\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 가능합니다!");
			}

		}

		play.atStartGame();
		bot.atStartGame();
		if (!play.deck.isEmpty())
			view.gamelogic(play, bot);
		else
			System.out.println("덱이없어서 게임이 종료됩니다 게임은 무승부로 처리됩니다.");
		
		sc.close();

	}

}

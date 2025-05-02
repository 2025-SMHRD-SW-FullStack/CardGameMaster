package view;

import java.util.Scanner;

import controller.GameController;
import javazoom.jl.player.MP3Player;
import model.Player;
import model.dao.PlayerDAO;
import model.dto.PlayerDTO;

public class GameView { // 사용자 콘솔 입출력 담당
	MP3Player mp3 = new MP3Player();
	PlayerDTO dto = new PlayerDTO();
    PlayerDAO dao = new PlayerDAO();
    public double getWinRate(int win ,int lose) {

        int totalGames = win + lose; // 총 게임 수
        if (totalGames == 0)
            return 0.0; // 게임을 한 번도 하지 않았다면 0%로 리턴
        return ((double) win / totalGames) * 100; // 승리 횟수 / 총 게임 수
    }
    
    public void join() {

        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n┌──────────────────────────────────┐");
        System.out.print("\t 아이디 : ");
        String user_id = sc.next();
        System.out.print("\t 비밀번호 : ");
        String user_pw = sc.next();
        System.out.println("└──────────────────────────────────┘\n");

        dto.setId(user_id);
        dto.setPw(user_pw);
        
        int result = dao.join(dto);
        if (result > 0) 
        	System.out.println("회원가입완료");
        
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n┌──────────────────────────────────┐");
        System.out.print("\t아이디 : ");
        String user_id = sc.next();
        System.out.print("\t비밀번호 : ");
        String user_pw = sc.next();
        System.out.println("└──────────────────────────────────┘\n");

        dto = dao.login(user_id, user_pw);
        
        if (dto != null) {
        	System.out.println("\t ** 입 장 ** \t" + "\n\n" +  dto.getId() + " 마스터님 환영합니다!\n\n");
            System.out.println("WIN : " + dto.getWin()+" "+"\tLOSE : "+dto.getLose() + "\t승률 : " + getWinRate(dto.getWin(),dto.getLose()));
            return true;
        } else {
        	System.out.println("\t** 입 장 실 패 **\t\n로그인에 실패했습니다.");
            System.out.println("아이디와 비밀번로를 확인 해주세요!");
            return false;
        }
        
    }
    public void title() {
    	System.out.println("\r\n"
    	+ "\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@;~.,@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@:, .,,.......@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@~.  ..    .......,@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@-   ,             ........@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@-                      .......@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@:                        .......,@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@.                        .......,@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@    ;,                   ........@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@    -:                    .......@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@    .,~                   .......@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@;  :..,                   ......,@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@*  ~  ,                   .......@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@*   . ~                   .......@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@*   ;:;.                  .......,@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@.  -;;.                  .......-@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@.  .;:                   ........@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@.   ..                   ........@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@,                         ....... @@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@=                         .......-@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@                         ........@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@                          .......@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@                          .......@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@~                 ::      .......@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@*            ,;;.;;;~     .......@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@*           .;;;~;;;;     .......@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@*           -;;;;;;;;     ....... @@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@=           -;;;;;;;;     .......-@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@.           ;;;;;;;;     .......,@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@.           -;;;;;;~     ........@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@.            :;;;;;-      .......@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@~            .;;;;;.      .......-@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@              :;;:       .......,@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@              .;;        ........@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@                ,        ........@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@.                         .......@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@!                         .......@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@!                         .......@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@!                         .......@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@*                         .......-@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@#.                        .......,@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@.                        ..;~....@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@.                         .;;-...@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@.                         ~;;-...;@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@=                         .:~,...,@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@                         ...-~...@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@                         :,.~....@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@                         .~.~....@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@~                         -.~...,@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@!                          :-....@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@!                         ..,....@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@!                         .......@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@$                          .,-,:@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@.                     .!===#@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@.             .---:#@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@=         ~*==$@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@*,,,-=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
    	+ "");
    	}

    public void gamelogic(Player play, Player bot) {
        GameController cont = new GameController();
        while (true) {
        	if (play.stunned) {
        		System.out.println("내가 기절 상태입니다.");
        		cont.setUserTurn(false);
        		play.stunned =  false;
        	} else if (bot.stunned) {
        		System.out.println("적이 기절 상태입니다.");
        		cont.setUserTurn(true);
        		bot.stunned =  false;
        	}
        	
            if (cont.isUserTurn()) {
            	System.out.println("\n\n──────── 나의 턴 ────────  \n\n");
                play.drawCard(); // draw
                // 카드사용
                play.playCard(play, bot);
                mp3.play("./draw-sword.mp3");
                System.out.println("나의 HP : " + play.hp + "\nbot HP : " + bot.hp);
            } else {
                //상대 턴 행동들
            	System.out.println("\n\n──────── bot 턴 ────────\n\n");
                bot.drawCard();// draw
                bot.botCard(bot, play);
                mp3.play("./draw-sword.mp3");
                System.out.println("나의 HP : " + play.hp + "\nbot HP : " + bot.hp);
            }
            
            if (play.isAlive() && bot.isAlive()) {
                cont.switchTurn(); // 유저와 컴퓨터의 턴을 바꾸는 메서드
            } else {
                //게임 종료 조건 처리
                if(cont.checkGameOver(play, bot)) {
                    dao.setScore(dto.getWin()+1, dto.getLose(), dto.getId());
                } else {
                    dao.setScore(dto.getWin(), dto.getLose()+1, dto.getId());
                }
                break;
            }
            
            
        }
    }
}




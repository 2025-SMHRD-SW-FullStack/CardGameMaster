package controller;

import model.Player;

//흐름제어, 게임 로직 중재
public class GameController {
	
	Player user = new Player();
	Player bot = new Player();
	private boolean isUserTurn = true;
	private boolean timeOver;
	private static GameController instance;
	private Thread turnTimer;
	
	public GameController() {
		instance = this;  // 생성될 때 자기자신을 저장
	}

	public static GameController getInstance() {
		return instance;
	}
	
	//유저와 컴퓨터의 턴을 바꾸는 메서드
	public void switchTurn() {
		isUserTurn = !isUserTurn;
	}
	
	
	//게임 종료 조건 처리
	/// if(!isAlive) controller.checkGameOver
	public boolean checkGameOver(Player user, Player bot) {
		if(user.hp > 0) {
			 System.out.println("\n\n ⎛⎝(•‿•)⎠⎞⎛⎝(•‿•)⎠⎞  승    리  ⎛⎝(•‿•)⎠⎞⎛⎝(•‿•)⎠⎞\n\n");
			return true;
		}
		else{
			System.out.println("\n┌────────────────────────┐");
            System.out.println("\tL O S E");
            System.out.println("└────────────────────────┘\n");
			return false;
		}

	}
	
	public void startTurnTimer(int seconds) {
		stopTurnTimer();
	    timeOver = false;
	    turnTimer = new Thread(() -> {
	        try {
	        	System.out.println();
	        	for (int i = seconds; i > 0; i--) {
	        		System.out.print("\r남은 시간: " + i + "초 ");
	                Thread.sleep(1000);
	            }
	            //Thread.sleep(seconds * 1000);
	            timeOver = true; // 시간 초과 → 턴 종료
	            System.out.println("\n시간 초과로 턴이 넘어갑니다. 아무 키나 눌러주세요.");
	        } catch (InterruptedException e) {
	            //System.out.println("[알림] 타이머가 취소되었습니다.");
	        }
	    });
	    turnTimer.start();
	}
	
    public void stopTurnTimer() {
        if (turnTimer != null && turnTimer.isAlive()) {
            turnTimer.interrupt();
        }
    }

	public boolean isUserTurn() {
		return isUserTurn;
	}


	public void setUserTurn(boolean isUserTurn) {
		this.isUserTurn = isUserTurn;
	}

	public boolean getTimeOver() {
		return timeOver;
	}

	public void setTimeOver(boolean timeOver) {
		this.timeOver = timeOver;
	}
	
	
}

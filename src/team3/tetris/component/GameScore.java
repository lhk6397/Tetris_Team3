package team3.tetris.component;

import team3.tetris.control.Difficulty;

public class GameScore {
	
	Difficulty difficulty;
	private int level;
    private int score;
    private int addition; // 더해지는 점수
	private String mode;

    public GameScore(Difficulty difficulty) 
    {
    	this.difficulty = difficulty;
    	this.level = 1;
        this.score = 0; 
        this.addition = 10;
    }

    // 점수 반환
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
	
	// addition 값 조정 by speed
	public void setAddition() {
		addition += difficulty.getSpeed() / 100;
	}
	
	// 점수 증가
    public void addScore(){
        score += addition;
    }
    
    // 한 번에 여러 줄 삭제
    public void lineClear(int lineCount) {
    	score += 100*lineCount*lineCount;
    }
    
    // 레벨 업
    public void levelUp() {
    	level++;
    	difficulty.setSpeed();
    	setAddition();
    }

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}

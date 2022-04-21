package team3.tetris.component;

import team3.tetris.control.Difficulty;

public class GameScore {
	
	private final int unitScore = 10;
	
	Difficulty difficulty;
	private int level;
    private int score;
    private int addition; // 더해지는 점수
	private String mode;
    private int tempAddition;

    public GameScore(Difficulty difficulty) 
    {
    	this.difficulty = difficulty;
    	this.level = 1;
        this.score = 0; 
        this.addition = unitScore;
    }

    // 점수 반환
	public int getScore() {
		return score;
	}
	
	// level 반환
	public int getLevel() {
		return level;
	}
	
	public int getAddition() {
		return addition;
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
    
    /* for FeverTime Item in ItemMode */
    public void setFeverAddtion() {
    	addition *= 2;
    }
    
    public void setAdditionDefault() {
    	addition /= 2;
    }

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}

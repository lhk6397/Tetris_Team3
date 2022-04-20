package team3.tetris.component;

import team3.tetris.control.Difficulty;

public class GameScore {
	
	Difficulty difficulty;
    private int score;
    private int addition; // 더해지는 점수

    public GameScore(Difficulty difficulty) 
    {
    	this.difficulty = difficulty;
        this.score = 0; 
        this.addition = 10;
    }

    // 점수 반환
	public int getScore() {
		return score;
	}
	
	// addition 값 조정 by speed
	public void setAddition() {
		addition += difficulty.getSpeed() / 100;
	}
	
	// 점수 증가
    public void addScore(){
        score += addition;
    }
    
    // 줄 삭제 시 점수 계산
    public void lineClear() {
    	score += 100;
    }
    
//    public void multiLine(int line_num) {
//    	score += 10*line_num*line_num;
//    } // 콤보 점수 - 한 번에 여러 해을 맞췄을 때
    
    

}

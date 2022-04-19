package team3.tetris.component;

import team3.tetris.control.Difficulty;

public class GameScore {
	
	Difficulty difficulty;
    private int score; 
    private int speed; // 난이도에 따른 속도
    private int addition; // 추가 점수

    public GameScore() 
    {
    	difficulty = new Difficulty();
        this.score = 0; 
        this.addition = 5;
        this.speed = difficulty.getSpeed();
    }

//    public GameScore(int score, int addition) { // 난이도
//        this.score = score;
//        this.addition = addition;
//        this.speed = 0;
//    }

    public void increaseScore(){
        score += addition;
    } // 게임이 진행될 때 자동으로 추가되는 점수
    
    public void setPlus(int speed){ 
    	addition += speed; 
    } // 속도가 올라감에 따라 plus를 증가하는 함수
    
    public void line() {
    	score += 10;
    } // 한 행을 맞췄을 때
    
    public void multiLine(int line_num) {
    	score += 10*line_num*line_num;
    } // 콤보 점수 - 한 번에 여러 해을 맞췄을 때
    
    public int getScore() {
        return score;
    } // 최종 점수 반환

}

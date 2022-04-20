package team3.tetris.control;

public class Difficulty {
	
	private static int Interval = 1000;
	
	private static int difficulty;  // 0 -> easy , 1 -> normal, 2-> hard
	private static double speed;
	private static int probability;

	public Difficulty() {
		this.difficulty = 1;
		this.speed = Interval;
		this.probability = 70;
	}
	
	public Difficulty(int difficultyType) {
		this.difficulty = difficultyType;
		this.speed = Interval;
		setProbability();
	}
	
	public static void setDifficulty(int num){		
		difficulty = num;
	}

	
	// 일정 수 줄 삭제 -> speed 증가(난이도에 따라) = setSpeed()
	public static void setSpeed(){
		switch(difficulty){
		case 0:
			speed *= 0.8;
			break;
		case 1:
			speed *= 0.6;
			break;
		case 2:
			speed *= 0.4;	
			break;
		}
	}

	public static void setProbability(){
		switch(difficulty){
		case 0:
			probability = 72;
			break;
		case 1:
			probability = 70;
			break;
		case 2:
			probability = 68;	
			break;
		}

	}
	
	public static int getDifficulty() {
		return difficulty;
	}
	
	public static String getStringDifficulty() {
		switch(difficulty){
		case 0:
			return "Easy";
		case 1:
			return "Normal";
		case 2:
			return "Hard";	
		}
		return "Normal";
	}

	public static double getSpeed(){
		return speed;
	}

	public static int getProbability(){	
		return probability;
	}
}

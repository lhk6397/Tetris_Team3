package team3.tetris.control;

public class Control {
	
	public static int difficulty = 2;  // 0 -> easy , 1 -> normal, 2-> hard
	public static int speed = 1000;
	public static int probability = 70;
	
	public static void setDifficulty(int num){		
		difficulty = num;
	}
	
	public static void setSpeed(){
		switch(difficulty){
		case 0:
			speed = 800;
		case 1:
			speed = 1000;
		case 2:
			speed = 1200;	
		}
	}
	
	public static void setProbability(){
		switch(difficulty){
		case 0:
			probability = 72;
		case 1:
			probability = 70;
		case 2:
			probability = 68;	
		}
	
	}
	
	public static String getDifficulty() {
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
	
	public static int getSpeed(){
		return speed;
	}
		
	public static int getProbability(){	
		return probability;
	}
	
}

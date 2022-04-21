package team3.tetris.control;

public class GameSize {

	private static int gameSizeType;	// 0 -> default , 1 -> big, 2-> full
	private static int gameSize; 		// 10 -> default , 15 -> big, 20-> full
	private static int fontSize;		// 20 -> default , 25 -> big, 30-> full
	private static int frameSize;		// 5 -> default , 8 -> big, 1 -> full

	public GameSize() {
		GameSize.gameSizeType = 0;
		GameSize.gameSize = 10;
		GameSize.fontSize = 20;
		GameSize.frameSize = 5;	

	}

	public GameSize(int gameSizeType) {
		GameSize.gameSizeType = gameSizeType;
		setGameSize();
		setFontSize();
		setFrameSize();
	}


	public static void setGameSizeType(int gameSizeType) {
		GameSize.gameSizeType = gameSizeType;
	}

	public static void setGameSize() {
		switch(gameSizeType){
		case 0:
			gameSize = 10;
			break;
		case 1:
			gameSize = 15;
			break;
		case 2:
			gameSize = 20;
			break;
		}
	}
	
	public static void setFontSize() {
		switch(gameSizeType){
		case 0:
			fontSize = 20;
			break;
		case 1:
			fontSize = 25;
			break;
		case 2:
			fontSize = 30;
			break;
		}
	} 
	
	public static void setFrameSize() {
		switch(gameSizeType){
		case 0:
			frameSize = 5;
			break;
		case 1:
			frameSize = 8;
			break;
		case 2:
			frameSize = 11;
			break;
		}
	}

	public static int getGameSizeType() {
		return gameSizeType;
	}

	public static int getGameSize() {
		return gameSize;
	}
	
	public static int getFontSize() {
		return fontSize;
	} 
	
	public static int getFrameSize() {
		return frameSize;
	}

} 
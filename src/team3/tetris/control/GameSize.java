package team3.tetris.control;

public class GameSize {
	
	private static int gameSizeType;	// 0 -> default , 1 -> big, 2-> full
	private static int gameSize; 		// 10 -> default , 15 -> big, 20-> full		// 20 -> default , 25 -> big, 30-> full
	private static int frameSize;		// 5 -> default , 8 -> big, 1 -> full

	public GameSize() {
		GameSize.gameSizeType = 0;
		GameSize.gameSize = 10;
		GameSize.frameSize = 5;	
		
	}
	
	public GameSize(int gameSizeType) {
		GameSize.gameSizeType = gameSizeType;
		setGameSize();
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
			gameSize = 13;
			break;
		case 2:
			gameSize = 16;
			break;
		}
	}
	
	public static void setFrameSize() {
		switch(gameSizeType){
		case 0:
			frameSize = 5;
			break;
		case 1:
			frameSize = 7;
			break;
		case 2:
			frameSize = 9;
			break;
		}
	}
	
	public static int getGameSizeType() {
		return gameSizeType;
	}
	
	public static int getGameSize() {
		return gameSize;
	}

	
	public static int getFrameSize() {
		return frameSize;
	}
	
}
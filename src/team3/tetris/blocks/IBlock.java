package team3.tetris.blocks;

import java.awt.Color;

public class IBlock extends Block {
	private static final int ID = 1;
	public IBlock() {
		shape = new int[][] { 
			{1, 1, 1, 1}
		};
		color = Color.CYAN;
	}

}

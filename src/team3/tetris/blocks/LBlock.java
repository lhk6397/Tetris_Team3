package team3.tetris.blocks;

import java.awt.Color;

public class LBlock extends Block {
	private static final int ID = 3;
	public LBlock() {
		shape = new int[][] { 
			{1, 1, 1},
			{1, 0, 0}
		};
		color = Color.ORANGE;
	}
}

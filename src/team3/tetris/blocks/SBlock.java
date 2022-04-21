package team3.tetris.blocks;

import java.awt.Color;

public class SBlock extends Block {
	private static final int ID = 5;
	public SBlock() {
		shape = new int[][] { 
			{0, 1, 1},
			{1, 1, 0}
		};
		color = Color.GREEN;
	}
}

package team3.tetris.blocks;

import java.awt.Color;

public class TBlock extends Block {
	private static final int ID = 6;
	public TBlock() {
		shape = new int[][] { 
			{0, 1, 0},
			{1, 1, 1}
		};
		color = Color.MAGENTA;
	}
}

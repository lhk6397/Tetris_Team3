package team3.tetris.blocks;

import java.awt.Color;

public class ZBlock extends Block {
	private static final int ID = 7;
	public ZBlock() {
		shape = new int[][] { 
			{1, 1, 0},
			{0, 1, 1}
		};
		color = Color.RED;
	}
}

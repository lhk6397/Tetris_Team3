package team3.tetris.blocks;

import java.awt.Color;

public class ZBlock extends Block {
	private static final int ID = 7;
	public ZBlock() {
		shape = new int[][] { 
			{7, 7, 0},
			{0, 7, 7}
		};
		color = Color.RED;
		id = 7;
	}
}

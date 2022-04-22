package team3.tetris.blocks;

import java.awt.Color;

public class JBlock extends Block {
	private static final int ID = 2;
	public JBlock() {
		shape = new int[][] { 
				{1, 1, 1},
				{0, 0, 1}
		};
		color = Color.BLUE;
	}
}

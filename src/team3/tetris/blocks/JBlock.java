package team3.tetris.blocks;

import java.awt.Color;

public class JBlock extends Block {
	private static final int ID = 2;
	public JBlock() {
		shape = new int[][] { 
				{2, 2, 2},
				{0, 0, 2}
		};
		color = Color.BLUE;
		id = 2;
	}
}

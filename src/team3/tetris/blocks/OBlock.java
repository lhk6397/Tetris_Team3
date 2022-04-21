package team3.tetris.blocks;

import java.awt.Color;

public class OBlock extends Block {
	private static final int ID = 4;
	public OBlock() {
		shape = new int[][] { 
			{4, 4},
			{4, 4}
		};
		color = Color.YELLOW;
		id = 4;
	}
}

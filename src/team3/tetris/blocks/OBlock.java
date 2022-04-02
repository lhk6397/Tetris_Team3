package team3.tetris.blocks;

import java.awt.Color;

public class OBlock extends Block {

	public OBlock() {
		shape = new int[][] { 
			{1, 1, 0, 0}, 
			{1, 1, 0, 0}
		};
		color = Color.YELLOW;
	}
}

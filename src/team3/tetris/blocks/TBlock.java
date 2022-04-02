package team3.tetris.blocks;

import java.awt.Color;

public class TBlock extends Block {
	
	public TBlock() {
		shape = new int[][] { 
			{0, 1, 0, 0},
			{1, 1, 1, 0}
		};
		color = Color.MAGENTA;
	}
}

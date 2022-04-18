package team3.tetris.blocks;

import java.awt.Color;

public abstract class Block {
		
	protected int[][] shape;
	protected Color color;
	
	public Block() {
		shape = new int[][]{ 
				{1, 1}, 
				{1, 1}
		};
		color = Color.YELLOW;
	}
	public void setShape(int[][] shape) { this.shape = shape; }
	
	public int[][] getBlockShape(){
		return shape;
	}
	
	public int getShape(int x, int y) {
		return shape[y][x];
	}
	
	public Color getColor() {
		return color;
	}
	
	public void rotate() { // 블럭 90도 회전
		int n = shape.length;
		int m = shape[0].length;
		int[][] rotate = new int[m][n];
		
		for(int i =0; i<rotate.length; ++i) {
			for(int j =0; j< rotate[i].length; ++j) {
				rotate[i][j] = shape[n-1-j][i];
			}
		}
		
		shape = rotate.clone();
	}
	
	public int height() {
		return shape.length;
	}
	
	public int width() {
		if(shape.length > 0)
			return shape[0].length;
		return 0;
	}
}

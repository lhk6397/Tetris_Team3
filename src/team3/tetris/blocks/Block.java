package team3.tetris.blocks;

import java.awt.Color;

public abstract class Block {
		
	protected int[][] shape;
	protected Color color;
	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Block() {
		shape = new int[][]{ 
				{1, 1}, 
				{1, 1}
		};
		color = Color.YELLOW;
	}
	public void setShape(int[][] shape) { 
		this.shape = shape;
	}
	
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
		int w = shape[0].length;
		int h = shape.length;
	
		int[][] rotate = new int[w][h];
		
		for(int i =0; i<rotate.length; ++i) {
			for(int j =0; j< rotate[i].length; ++j) {
				rotate[i][j] = shape[h-1-j][i];
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
	
	public Block clone() {
		Block temp = new OBlock();
		temp.shape = this.getBlockShape();
		temp.color = this.getColor();
		
		return temp;
	}
}

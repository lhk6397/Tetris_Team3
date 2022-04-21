package team3.tetris.blocks;

import java.awt.*;

public class WeightBlock extends Block {
    public WeightBlock() {
        shape = new int[][] {
                {0,10,10,0},
                {10,10,10,10},
        };
        color = Color.WHITE;
    }

    @Override
    public void rotate() {}
}

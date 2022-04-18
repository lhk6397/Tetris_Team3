package team3.tetris.blocks;

import java.awt.*;

public class WeighBlock extends Block {
    public WeighBlock() {
        shape = new int[][] {
                {0,1,1,0},
                {1,1,1,1},
        };
        color = Color.WHITE;
    }

    @Override
    public void rotate() {}
}

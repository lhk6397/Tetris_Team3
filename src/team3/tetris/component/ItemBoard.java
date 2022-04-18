package team3.tetris.component;

import team3.tetris.blocks.*;

import java.util.Random;

public class ItemBoard extends Board {
    public int count = 0;
    public ItemBoard() {
        super();
    }


    @Override
    protected Block getRandomBlock() {
        count ++;
        if (count % 10 == 0) {
            Random rnd = new Random(System.currentTimeMillis()*11);
            int num = rnd.nextInt(2);
            switch (num) {
                case 0:
                    return createItemLineClear(super.getRandomBlock());
                case 1: //다른 아이템 구상
                case 2:
                    return createWeightBlock();
            }
        } return super.getRandomBlock();
    }

    //LineClear block 생성 함수
    private Block createItemLineClear(Block block) {
        int n = block.width();
        int m = block.height();
        Random rnd = new Random(System.currentTimeMillis()*11);
        int num = rnd.nextInt(4);
        int cnt = 0;
        int[][] LCblock = new int[n][m];

        for (int i=0; i<n;i++) {
            for (int j=0;j<m;j++) {
                if (block.getShape(i,j) == 0) {
                    LCblock[i][j] = 0;
                    continue;
                } cnt ++;
                if (cnt == num) {
                    LCblock[i][j] = 2;
                    continue;
                } else LCblock[i][j] = 1;
            }
        }
        block.setShape(LCblock);
        return block;
    }

    //무게추 블럭 생성함수
    private Block createWeightBlock() {
        WeighBlock wbk = new WeighBlock();
        return wbk;
    }
}

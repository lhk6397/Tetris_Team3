package team3.tetris.component;

import team3.tetris.blocks.*;

import java.util.Random;

public class ItemBoard extends Board {
    public int count = 0;
    public boolean isFieldClear = false;
    
    public ItemBoard() {
        super();
    }


    @Override
    protected Block getRandomBlock(int num, int blocks) {
        count++;
        if (count % 3 == 0) {
            Random rnd = new Random(System.currentTimeMillis()*11);
            int number = rnd.nextInt(2);
            switch (number) {
                case 0:
                    return createItemLineClear(super.getRandomBlock(11, probability));
                case 1: //다른 아이템 구상
                case 2:
                    return createWeightBlock();
                case 3:
                	return createItemFieldClear(super.getRandomBlock(11, probability));
            }
        } return super.getRandomBlock(11, probability);
    }

    @Override
    protected boolean checkLineFull(int lineNum) {
		for(int i = 0; i < WIDTH; ++i) {
			if(inactiveBlock[lineNum][i] <= 0) {
				isFieldClear = false;
				return false;
			}
			
			int element = inactiveBlock[lineNum][i];
			if(element >= 2) {
				switch(element) {
				case 2:
					// lineClear
					break;
				case 3:
					isFieldClear = true;
					break;
				default:
					break;
				}
						
			}
		}
		return true;
	}
    
    public void fieldClear() {
    	// int elementCount = 0;
    	for(int j = HEIGHT -1; j > 0; --j) {
    		for(int i = 0; i < WIDTH; ++i) {
    			if(inactiveBlock[j][i] >= 1) {
    				// elementCount++;
    		    	gameScore.addScore();
    			}
    		}
    	}
    	isFieldClear = false;
    	updateScore();
    	reset();
    	drawBoard();
    }
    // 해결과졔: 2111113111와 같이 아이템이 중첩되어 있는 라인의 경우 어떻게?
    @Override
 	protected void lineClear() {
 		int combo = 0; // 붙어서 삭제 되는 line 수
 		for(int j = HEIGHT - 1; j >0; --j) {
 			if(!checkLineFull(j)) {
 				continue;
 			}
 			
 			while(checkLineFull(j)) {
 				if(isFieldClear) {
 					fieldClear();
 					return;
 				}
 				combo++; // 붙어서 삭제되는 줄의 수
 				
 				// inactiveBlock[][] 한 칸씩 내려오기
 				for(int rows = j - 1; rows >= 0; --rows) {
 					for(int cols = 0; cols < WIDTH; ++cols) {
 						inactiveBlock[rows+1][cols] = inactiveBlock[rows][cols];
 					}
 				}
 				inactiveBlock[0] = new int[WIDTH];
 				
 				// board[][] 한 칸씩 내려오기
 				for(int rows = j - 1; rows >= 0; --rows) {
 					for(int cols = 0; cols < WIDTH; ++cols) {
 						board[rows+1][cols] = board[rows][cols];
 					}
 				}
 				board[0] = new int[WIDTH];
 			}
 			gameScore.lineClear(combo);

 			while(combo > 0) {
 				deletedLineCount++;
 				combo--;
 				if(deletedLineCount % TARGET_COUNT == 0) {
 					levelUp();
 				}
 			}
 			updateScore();
 		}
 		drawBoard();
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
                }
                cnt ++;
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
    
  //FieldClear block 생성 함수
    private Block createItemFieldClear(Block block) {
        int n = block.width();
        int m = block.height();
        Random rnd = new Random(System.currentTimeMillis()*11);
        int num = rnd.nextInt(4);
        int cnt = 0;
        int[][] FCblock = new int[n][m];

        for (int i=0; i<n;i++) {
            for (int j=0;j<m;j++) {
                if (block.getShape(i,j) == 0) {
                    FCblock[i][j] = 0;
                    continue;
                } 
                cnt ++;
                if (cnt == num) {
                    FCblock[i][j] = 3;
                    continue;
                } else FCblock[i][j] = 1;
            }
        }
        block.setShape(FCblock);
        return block;
    }
    
    
}

package team3.tetris.component;

import team3.tetris.blocks.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ItemBoard extends Board {
    private JTextPane modePane;

    private final int bonus = 1000;

    public int count = 0;
    public boolean isFeverTime = false;
    public boolean isFieldClear = false;
    private boolean isBonusScore = false;

    public ItemBoard() {
        super();
        super.isNormal = false;
    }

    // Line Clear Item
    private void clearCurrentLine(int lineNum) {
    	// inactiveBlock[][] 한 칸씩 내려오기
		for(int rows = lineNum - 1; rows >= 0; --rows) {
			for(int cols = 0; cols < WIDTH; ++cols) {
				inactiveBlock[rows+1][cols] = inactiveBlock[rows][cols];
			}
		}
		inactiveBlock[0] = new int[WIDTH];

		// board[][] 한 칸씩 내려오기
		for(int rows = lineNum - 1; rows >= 0; --rows) {
			for(int cols = 0; cols < WIDTH; ++cols) {
				board[rows+1][cols] = board[rows][cols];
			}
		}
		board[0] = new int[WIDTH];

		gameScore.lineClear(1);
		updateScore();

    }

    // Field Clear Item
    private void fieldClear() {
    	int elementCount = 0;
    	for(int j = HEIGHT -1; j > 0; --j) {
    		for(int i = 0; i < WIDTH; ++i) {
    			if(inactiveBlock[j][i] >= 1) {
    				elementCount++;
    			}
    		}
    	}

    	for(int i = 0 ; i < elementCount / WIDTH; ++i) {
    		gameScore.addScore();
    	}
    	isFieldClear = false;
    	updateScore();
    	reset();
    	drawBoard();
    }

    // FeverTime Item
    public void setFeverTime() {
    	gameScore.setFeverAddtion();
    	Timer FeverTimer = new Timer(10000, new ActionListener() { // initInterval 마다 actionPerformed
			@Override
			public void actionPerformed(ActionEvent e) {
				gameScore.setAdditionDefault();
			}
		});
    	isFeverTime = false;
    	FeverTimer.setRepeats(false); // Only Execute once
    	FeverTimer.start();
    }
    // BonusScore Item
    private void addBonusScore() {
    	gameScore.addBonusScore(bonus);
    	isBonusScore = false;
    }

    //무게추 블럭 생성함수
    private Block createWeightBlock() {
        WeightBlock wbk = new WeightBlock();
        return wbk;
    }

    //WeightBlock에 대한 구현
    private void eraseByWeightBlock() {
        int w = curr.width();
        int h = curr.height();

        for (int i=0 ; i<w;i++){
            inactiveBlock[y+h][x+i] = 0;
            board[y+h][x+i]=0;
        }
    }

    //Weight 동작에 대한 hardDrop 구현
    private void hardDropByWeightBlock() {
    	int w = curr.width();
        int st = y;
		int lineCount = 0;
		eraseCurr();
		for(; y < HEIGHT; ++y) {
			lineCount++;

			if(checkFloor()) {
				for(int j = st; j < HEIGHT; ++j) {
					for(int i = 0; i < w; ++i) {
						inactiveBlock[j][x+i] = 0;
						board[j][x+i] = 0;
					}
				}
				placeBlock();
				inactivateBlock();
				while(lineCount > 0) {
					gameScore.addScore();
					lineCount--;
				}
				updateScore();
				return;
			}
		}
		// hardDrop 후 방향키 입력 시 분신술? drawBoard()

	}

    //WeightBlock 함수
    private void moveDownWeightBlock() {
        if (!checkFloor()) {
            eraseCurr();
            updateScore();
            if (!checkOtherBlockUnder()) {
                eraseByWeightBlock();
            }
            y++;
            gameScore.addScore(); // score 증가
            placeBlock();
        } else {
            // inactiveBlock[][]에 블럭 모양 반영
            inactivateBlock();
            lineClearCheck();
            spawnBlock();
        }
    }


    // Item Block 생성 함수
    private Block createItemBlock(int itemType, Block block) {
    	int n = block.width();
        int m = block.height();
        Random rnd = new Random(System.currentTimeMillis()*11);
        int num = rnd.nextInt(4);
        int cnt = 0;
        int[][] itemBlock = new int[n][m];

        for (int i=0; i<n;i++) {
            for (int j=0;j<m;j++) {
                if (block.getShape(i,j) == 0) {
                	itemBlock[i][j] = 0;
                    continue;
                }
                cnt ++;
                if (cnt == num) {
                	itemBlock[i][j] = itemType;
                    continue;
                } else itemBlock[i][j] = 1;
            }
        }
        block.setShape(itemBlock);
        return block;
    }

    @Override
    protected Block getRandomBlock(int num, int blocks) {
        count++;
        if (count % 3 == 0) {
            Random rnd = new Random(System.currentTimeMillis()*num);
            int number = rnd.nextInt(5)+9;
            switch (number) {
                case 10:
                	return createWeightBlock();
                case 11:
				case 12:
				case 13:
				case 14:
					return createItemBlock(number, super.getRandomBlock(num, probability));
			}
        } return super.getRandomBlock(11, probability);
    }

    @Override
    protected boolean checkLineFull(int lineNum) {
		for(int i = 0; i < WIDTH; ++i) {
			if(inactiveBlock[lineNum][i] <= 0) {
				isFieldClear = false;
				isFeverTime = false;
				isBonusScore = false;
				return false;
			}

			int element = inactiveBlock[lineNum][i];
			if(element >= 11) {
				switch(element) {
				case 11:
					clearCurrentLine(lineNum);
					break;
				case 12:
					isFieldClear = true;
					break;
				case 13:
					isFeverTime = true;
					break;
				case 14:
					isBonusScore = true;
				default:
					break;
				}

			}
		}
		return true;
	}

    @Override
 	protected void lineClearCheck() {
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

 				if(isFeverTime) {
 					setFeverTime();
 				}

 				if(isBonusScore) {
 					addBonusScore();
 				}

 				combo++; // 붙어서 삭제되는 줄의 수

 				clearCurrentLine(j);

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
 	}

    @Override
    protected void moveDown() {
    	String itemName = curr.getClass().getSimpleName();
    	if(itemName.equals("WeightBlock")) moveDownWeightBlock();
    	else{
    		//아래로 이동
    		if(checkBottom()) {
    			eraseCurr();
    			y++;
    			gameScore.addScore(); // score 증가
    			updateScore();
    			placeBlock();
    		}
    		//고정
    		else {
    			// inactiveBlock[][]에 블럭 모양 반영
    			inactivateBlock();
    			lineClearCheck();
    			spawnBlock();
    		}
    	}
    }
    @Override
    protected void hardDrop() {
    	 String itemName = curr.getClass().getSimpleName();
         if(itemName.equals("WeightBlock")) hardDropByWeightBlock();
         else {
	    	 int lineCount = 0;
	    	 eraseCurr();
	    	 for(; y < HEIGHT; ++y) {
	 			lineCount++;
	 			if(!checkBottom()) {
	 				placeBlock();
	 				inactivateBlock();
	 				while(lineCount > 0) {
	 					gameScore.addScore();
	 					lineCount--;
	 				}
	 				lineClearCheck();
	 				updateScore();
	 				return;
	 			}
			}
		}
	}

}

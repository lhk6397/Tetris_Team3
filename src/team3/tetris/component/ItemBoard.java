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

    public int count = 0;
    public boolean isFeverTime = false;
    public boolean isFieldClear = false;
    
    public ItemBoard() {
        super();
        super.isNormal = false;
    }


    @Override
    protected Block getRandomBlock(int num, int probability) {
        count++;
        if (count % 3 == 0) {
            Random rnd = new Random(System.currentTimeMillis()*num);
            int number = rnd.nextInt(4);
            switch (number) {
                case 0:
                    return createItemLineClear(super.getRandomBlock(num, probability));
                case 1:
                	return createItemFieldClear(super.getRandomBlock(num, probability));
                case 2:
                    return createWeightBlock();
                case 3:
                	return createItemFeverTime(super.getRandomBlock(num, probability));
                	
            }
        } return super.getRandomBlock(num, probability);
    }

    @Override
    protected boolean checkLineFull(int lineNum) {
		for(int i = 0; i < WIDTH; ++i) {
			if(inactiveBlock[lineNum][i] <= 0) {
				isFieldClear = false;
				isFeverTime = false;
				return false;
			}
			
			int element = inactiveBlock[lineNum][i];
			if(element >= 2) {
				switch(element) {
				case 2:
					clearCurrentLine(lineNum);
					break;
				case 3:
					isFieldClear = true;
					break;
				case 4:
					isFeverTime = true;
				default:
					break;
				}
						
			}
		}
		return true;
	}
  
    public void clearCurrentLine(int lineNum) {
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
    
    public void fieldClear() {
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
    
    public void setFeverTime() {
    	gameScore.setFeverAddtion();
    	Timer FeverTimer = new Timer(10000, new ActionListener() { // initInterval 마다 actionPerformed
			@Override
			public void actionPerformed(ActionEvent e) {
				gameScore.setAdditionDefault();
			}
		});
    	FeverTimer.setRepeats(false); // Only Execute once
    	FeverTimer.start();
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
            curr = next;
            next = getRandomBlock(1, probability);
            x = 3;
            y = 0;
            placeBlock();
            gameOverCheck();
        }
    }

    //Weight 동작에 대한 구현
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
                curr = next;
                next = getRandomBlock(1,probability);
                x = 3;
                y = 0;
                placeBlock();
                gameOverCheck();
            }
        }
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
    
    private Block createItemFeverTime(Block block) {
    	int n = block.width();
        int m = block.height();
        Random rnd = new Random(System.currentTimeMillis()*11);
        int num = rnd.nextInt(4);
        int cnt = 0;
        int[][] FTblock = new int[n][m];

        for (int i=0; i<n;i++) {
            for (int j=0;j<m;j++) {
                if (block.getShape(i,j) == 0) {
                	FTblock[i][j] = 0;
                    continue;
                } 
                cnt ++;
                if (cnt == num) {
                	FTblock[i][j] = 4;
                    continue;
                } else FTblock[i][j] = 1;
            }
        }
        block.setShape(FTblock);
        return block;
    }
    
    @Override
    public void drawBoard() {
		StyleConstants.setForeground(styleSet, curr.getColor());
		StringBuilder sb = new StringBuilder(); // 문자열 추가나 변경등의 작업이 많을 경우에는 StringBuilder를, 문자열 변경 작업이 거의 없는 경우에는 그냥 String을 사용하는 것이 유리
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR); // 윗쪽 벽
		sb.append("\n");
		for(int i=0; i < board.length; i++) {
			sb.append(BORDER_CHAR); // 왼쪽 벽
			for(int j=0; j < board[i].length; j++) { // 블럭에 해당되는 부분 draw
				if(board[i][j] == 1) {
					sb.append("■");
				} else if(board[i][j] == 2){
					sb.append("L");
				} else if(board[i][j] == 3) {
					sb.append("C");
				} else if(board[i][j] == 4) {
					sb.append("F");
				} else {
					sb.append("  ");
				} // 아이템에 대한 L표시가 이루어져야함 근데 표시 오류가 있음
			}
			sb.append(BORDER_CHAR); // 오른쪽 벽
			sb.append("\n");
		}
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR); // 아랫쪽 벽
		pane.setText(sb.toString());
		StyledDocument doc = pane.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		pane.setStyledDocument(doc);
		drawPreviewBoard();
	}
	
    @Override
	public void drawPreviewBoard() {
		StyleConstants.setForeground(styleSet, next.getColor());
		StringBuilder sb2 = new StringBuilder(); 
		
		sb2.append("\n");
		for(int i=0; i < previewBoard.length; i++) {
			for(int j=0; j < previewBoard[i].length; j++) { // 블럭에 해당되는 부분 draw
				if(previewBoard[i][j] == 1) {
					sb2.append("■");
				} else if(previewBoard[i][j] == 2){
					sb2.append("L");
				} else if(previewBoard[i][j] == 3) {
					sb2.append("C");
				} else if(previewBoard[i][j] == 4) {
					sb2.append("F");
				} else {
					sb2.append("  ");
				}
			}
			sb2.append("\n");
		}

		previewPane.setText(sb2.toString());
		StyledDocument doc = previewPane.getStyledDocument();							
		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		previewPane.setStyledDocument(doc);
	}
    
}

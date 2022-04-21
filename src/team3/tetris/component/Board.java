package team3.tetris.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import team3.tetris.blocks.Block;
import team3.tetris.blocks.IBlock;
import team3.tetris.blocks.JBlock;
import team3.tetris.blocks.LBlock;
import team3.tetris.blocks.OBlock;
import team3.tetris.blocks.SBlock;
import team3.tetris.blocks.TBlock;
import team3.tetris.blocks.ZBlock;
import team3.tetris.control.Difficulty;

public class Board extends JFrame {

	private static final long serialVersionUID = 2434035659171694595L; // version
	
	public static final int HEIGHT = 20;
	public static final int WIDTH = 10;
	public static final int PREVIEWHEIGHT = 5;
	public static final int PREVIEWWIDTH = 5;
	public static final int TARGET_COUNT = 10;
	public static final char BORDER_CHAR = '○';

	public Difficulty difficulty;
	protected GameScore gameScore; 
	protected int deletedLineCount = 0;
	protected int[][] board;
	protected int[][] inactiveBlock; // 굳어진 블럭들에 대한 2차원 배열
	protected int probability;
	protected double initInterval; 
	protected boolean isNormal;
	protected boolean isPaused;
	
	private JTextPane pane;
	private JTextPane previewPane;
	private JTextPane scorePane;
	private JTextPane statusBar;
	private JTextPane levelPane;
	private JTextPane difficultyBar;
	private JTextPane background;
	private int[][] previewBoard;
	private KeyListener playerKeyListener;
	private SimpleAttributeSet styleSet;
	private Timer timer;
	private Block curr;
	private Block next;
	private String status;
	
	int x = 3; //Default Position.
	int y = 0;
	
	public Board() {
		super("Team 3 Tetris");
		this.isNormal = true;
		this.isPaused = false;
		applyDifficulty(); // 난이도 설정 불러오기 

		// Initialize board for the game.
		gameScore = new GameScore(difficulty);
		board = new int[HEIGHT][WIDTH];
		inactiveBlock = new int[HEIGHT][WIDTH];
		previewBoard = new int[PREVIEWHEIGHT][PREVIEWWIDTH];
		playerKeyListener = new PlayerKeyListener();
		addKeyListener(playerKeyListener);
		setFocusable(true);
		requestFocus(); // 컴포넌트가 이벤트를 받을 수 있게 함. (키 이벤트 독점)
		setDisplayAndLayout();

	}

	//timer 실행 및 placeBlock 실행
	protected void run() {
		timer = new Timer((int) (initInterval), new ActionListener() { // initInterval 마다 actionPerformed
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isPaused) {
					moveDown();
					drawBoard();
				}
			}
		});

		// Create block and draw.
		curr = getRandomBlock(11, probability);
		next = getRandomBlock(1, probability);
		placePreBlock();
		drawBoard();
		drawPreviewBoard();
		timer.start();
	}

	//생성자에 있던 DisplaySetting 옮겼습니다.
	public void setDisplayAndLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X 버튼 눌렀을 때 닫히도록 설정

		//Board display setting.
		setSize(445,669);
		setLocationRelativeTo(null);
		CompoundBorder border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));

		pane = new JTextPane();
		pane.setEditable(false);
		pane.setBackground(Color.BLACK);
		pane.setBorder(border);
		pane.setBounds(0, 0, 260, 630);
		this.getContentPane().add(pane);

		//PreviewBoard
		previewPane = new JTextPane();
		previewPane.setEditable(false);
		previewPane.setBackground(Color.BLACK);
		previewPane.setBorder(border);
		previewPane.setBounds(280, 0, 130, 130);
		this.getContentPane().add(previewPane);

		//ScoreBoard
		scorePane = new JTextPane();
		scorePane.setEditable(false);
		scorePane.setBorder(border);
		TitledBorder border2 = BorderFactory.createTitledBorder("SCORE");
		scorePane.setBounds(280, 150, 130, 50);
		scorePane.setBorder(border2);
		scorePane.setText("Score : "+ gameScore.getScore());
		this.getContentPane().add(scorePane);

		//LevelBoard
		levelPane = new JTextPane();
		levelPane.setEditable(false);
		levelPane.setBorder(border);
		TitledBorder border3 = BorderFactory.createTitledBorder("LEVEL");
		levelPane.setBounds(280, 410, 130, 50);
		levelPane.setBorder(border3);
		levelPane.setText("Level : "+ gameScore.getLevel());
		this.getContentPane().add(levelPane);
		
		//difficultyBar
		difficultyBar = new JTextPane();
		difficultyBar.setEditable(false);
		TitledBorder border4 = BorderFactory.createTitledBorder("DIFFICULTY");
		difficultyBar.setBounds(280, 480, 130, 50);	
		difficultyBar.setBorder(border4); 
		difficultyBar.setText(difficulty.getStringDifficulty());		
		this.getContentPane().add(difficultyBar);

		//statusBar
		statusBar = new JTextPane();
		statusBar.setEditable(false);
		TitledBorder border5 = BorderFactory.createTitledBorder("STATUS");
		statusBar.setBounds(280, 550, 130, 50);
		statusBar.setBorder(border5);
		statusBar.setText("Playing!");
		this.getContentPane().add(statusBar);


		//Background
		background = new JTextPane();
		background.setBackground(Color.WHITE);
		this.getContentPane().add(background);


		//Document default style.
		styleSet = new SimpleAttributeSet();
		StyleConstants.setFontSize(styleSet, 20);
		StyleConstants.setFontFamily(styleSet, "Dialog");
		StyleConstants.setBold(styleSet, true);
		StyleConstants.setForeground(styleSet, Color.WHITE);
		StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);
	}

	protected Block getRandomBlock(int num, int probability) {
		Random rnd = new Random(System.currentTimeMillis()*num); // Generate Random Number. // num : block과 previewBlock 구분을 위해 소수 곱하기
		int block = rnd.nextInt(probability);

		if(block < 10) return new OBlock();
		else if (block <20) return new JBlock();
		else if (block <30) return new LBlock();
		else if (block <40) return new ZBlock();
		else if (block <50) return new SBlock();
		else if (block <60) return new TBlock();
		else return new IBlock();
	}
	
	private void applyDifficulty() {
		difficulty = new Difficulty(2);			// 처음 설정창과 연결하기 () 부분 난이도 설정 
		initInterval = difficulty.getSpeed();
		probability = difficulty.getProbability();
	}

	public void levelUp() {
		gameScore.levelUp();
		initInterval = difficulty.getSpeed();
		timer.setDelay((int)initInterval);
		levelPane.setText("Level : " + gameScore.getLevel());
		statusBar.setText("LEVEL UP!!");
	}
	
	private void pause() {
		/*
		 * 1. P와 ESC를 제외한 나머지의 키 입력을 제한
		 * 
		 */
        isPaused = !isPaused;

        if (isPaused) {
        	status = "Pause!";
        } else {
        	status = "Playing!";
        }
        drawBoard();
        statusBar.setText(status);
    }
	
	private void escape() {
		timer.stop();
		Scoreboard sb = null;
		try {
			sb = new Scoreboard(gameScore, Board.this);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		sb.setVisible(true);
	}
	
	private void placePreBlock() {
		StyledDocument doc = pane.getStyledDocument();
		SimpleAttributeSet styles = new SimpleAttributeSet();
//		StyleConstants.setForeground(styles, next.getColor());
				
		for(int j=0; j<next.height(); j++) {
			int rows = y+j == 0 ? 0 : y+j-1; // y+j가 0이면 rows = 0 아니면 rows = y+j-1
			int offset = rows * (WIDTH+3) + x + 1;
			doc.setCharacterAttributes(offset, next.width(), styles, true);
			for(int i=0; i<next.width(); i++) { // 블럭 배열을 previewboard 배열에 대입
				previewBoard[j][i] = next.getShape(i, j);
			}
		}
		
	}
	
	private void placeBlock() { // 현재 블럭의 shape을 board 배열에 대입
		StyledDocument doc = pane.getStyledDocument();
		SimpleAttributeSet styles = new SimpleAttributeSet();
//		StyleConstants.setForeground(styles, curr.getColor());
		
		for(int j=0; j<curr.height(); ++j) {
			int rows = y+j == 0 ? 0 : y+j-1; // y+j가 0이면 rows = 0 아니면 rows = y+j-1
			int offset = rows * (WIDTH+3) + x + 1;
			doc.setCharacterAttributes(offset, curr.width(), styles, true);
			for(int i=0; i<curr.width(); ++i) { // 블럭 배열을 board 배열에 대입
				if(inactiveBlock[y+j][x+i] >= 1) {
					continue;
				}
				board[y+j][x+i] = curr.getShape(i, j);
			}			
		}
		
		eraseNext();
		placePreBlock();
	}
	
	private void inactivateBlock() {
		for(int j = 0; j < curr.height(); ++j) {
			for(int i = 0; i < curr.width(); ++i) {
				if(inactiveBlock[y+j][x+i] >= 1) {
					continue;
				}
				inactiveBlock[y+j][x+i] = curr.getShape(i, j);
			}
		}
	}
	
	public void updateScore(){
        scorePane.setText("Score : "+ gameScore.getScore());
    }
	
	private void eraseCurr() { // Erase current block.
		for(int i=x; i<x+curr.width(); i++) {
			for(int j=y; j<y+curr.height(); j++) {
				if(inactiveBlock[j][i] == 1) continue;
				board[j][i] = 0;
			}
		}  		 
	}
	
	private void eraseNext() {
		for(int i = 0; i < PREVIEWWIDTH; i++) {
			for(int j=0; j < PREVIEWHEIGHT; j++) {
				previewBoard[j][i] = 0; 
			}
		}
	}
	
	// 줄 삭제
	protected void lineClear() {
		int combo = 0; // 붙어서 삭제 되는 line 수
		for(int j = HEIGHT - 1; j >0; --j) {
			if(!checkLineFull(j)) {
				continue;
			}
			
			while(checkLineFull(j)) {
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
	
	protected boolean checkLineFull(int lineNum) {
		for(int i = 0; i < WIDTH; ++i) {
			if(inactiveBlock[lineNum][i] <= 0) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean checkBottom() {
		int w = curr.width();
		int h = curr.height();
		//블럭이 땅에 닿았는지 검사
		if (y + h >= HEIGHT) {
			return false;
		}
		
		int[][] currShape = curr.getBlockShape();
		
		//블럭의 각 요소가 다른블럭과 닿았는지 체크
		for(int i = 0; i < w; ++i) {
            for(int j = h-1; j >= 0; --j) {
                if(currShape[j][i] > 0) {
                    if(inactiveBlock[y+j+1][x+i] > 0) {
                    	return false;
                    }
                }
            }
        }
		return true;
	}
	
	protected boolean checkRight() {
		int w = curr.width();
		int h = curr.height();
		//블럭이 땅에 닿았는지 검사
		if (x + w >= WIDTH) {
			return false;
		}
		
		int[][] currShape = curr.getBlockShape();
		
		//블럭의 각 요소가 다른블럭과 닿았는지 체크
		for(int j = 0; j < h; ++j) {
            for(int i = w-1; i >= 0; --i) {
                if(currShape[j][i] > 0) {
                    if(inactiveBlock[y+j][x+i+1] > 0) {
                    	return false;
                    }
                }
            }
        }
		return true;
	}
	
	protected boolean checkLeft() {
		int w = curr.width();
		int h = curr.height();
		//블럭이 땅에 닿았는지 검사
		if (x <= 0) {
			return false;
		}
		
		int[][] currShape = curr.getBlockShape();
		
		//블럭의 각 요소가 다른블럭과 닿았는지 체크
		for(int j = 0; j < h; ++j) {
            for(int i = 0; i < w; ++i) {
                if(currShape[j][i] > 0) {
                    if(inactiveBlock[y+j][x+i-1] > 0) {
                    	return false;
                    }
                }
            }
        }
		return true;
	}
	
	// git issues 해결중
	public boolean rotateCheck() {
		Block temp = curr.clone();
		temp.rotate();
		int w = temp.width();
		int h = temp.height();
		
		int[][] shape = temp.getBlockShape();
		
		if(y + h>= HEIGHT) {
			--y;
		}
		
		if(x + w >= WIDTH) {
			--x;
		}
		
		
		for(int j = h-1; j >= 0; --j) {
			for(int i = 0; i < w; ++i) {
				if(inactiveBlock[y+j][x+i] > 0) {
					return false;
				}
			}
		}
		return true;
		
	}

	private void hardDrop() {
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
				updateScore();
				return;
			}
		}
		// hardDrop 후 방향키 입력 시 분신술? drawBoard()
		
	}
	
	protected void rotate() {
		if(rotateCheck()) {
			eraseCurr();
			curr.rotate();
			placeBlock();
		} 
	}
	
	protected void moveDown() {
		if(checkBottom()) {
			eraseCurr();
			y++;
			gameScore.addScore(); // score 증가
			updateScore(); 
			placeBlock();
		}
		else {
			// inactiveBlock[][]에 블럭 모양 반영
			inactivateBlock();
			lineClear();
			curr = next;
			next = getRandomBlock(1,probability);
			x = 3;
			y = 0;
			placeBlock();
			gameOverCheck();
		}
	} 
	
	protected void moveRight() {
		if(checkRight()) {
			eraseCurr();
			x++;
			placeBlock();
		}else {
			return;
		}
	}

	protected void moveLeft() {
		if(checkLeft()) {
			eraseCurr();
			x--;
			placeBlock();
		} else {
			return;
		}
	}
	
	public void gameOverCheck() {
		for(int k = 0; k < WIDTH; ++k) {
			if(inactiveBlock[0][k] == 1) {
				timer.stop();
				Scoreboard sb;
				try {
					sb = new Scoreboard(gameScore, Board.this);
					sb.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
	}
	
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
				} else {
					sb.append("   ");
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
				} else {
					sb2.append("   ");
				}
			}
			sb2.append("\n");
		}

		previewPane.setText(sb2.toString());
		StyledDocument doc = previewPane.getStyledDocument();							
		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		previewPane.setStyledDocument(doc);
	}
	
	public void reset() {
		this.board = new int[HEIGHT][WIDTH];
		this.previewBoard = new int [PREVIEWHEIGHT][PREVIEWWIDTH];
		this.inactiveBlock = new int[HEIGHT][WIDTH];
	}

	public class PlayerKeyListener implements KeyListener { // key 입력
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				moveDown();
				drawBoard();
				break;
			case KeyEvent.VK_RIGHT:
				moveRight();
				drawBoard();
				break;
			case KeyEvent.VK_LEFT:
				moveLeft();
				drawBoard();
				break;
			case KeyEvent.VK_UP:
				rotate();
				break;
			case KeyEvent.VK_SPACE:
				hardDrop();
				break;
			case KeyEvent.VK_P:
				pause();
				break;
			case KeyEvent.VK_ESCAPE:
				escape();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

	}
}


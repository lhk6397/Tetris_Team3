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
import team3.tetris.control.Control;

public class Board extends JFrame {

	private static final long serialVersionUID = 2434035659171694595L; // version
	
	public static final int HEIGHT = 20;
	public static final int WIDTH = 10;
	public static final int PREVIEWHEIGHT = 4;
	public static final int PREVIEWWIDTH = 4;
	public static final char BORDER_CHAR = '▧';
	
	private GameScore gameScore; 
	private boolean isPaused = false;
	private JTextPane pane;
	private JTextPane previewPane;
	private JTextPane scorePane;
	private JTextPane statusBar;
	private JTextPane difficultyBar;
	private JTextPane background;
	private int[][] board;
	private int[][] inactiveBlock; // 굳어진 블럭들에 대한 2차원 배열
	private int[][] previewBoard;
	private KeyListener playerKeyListener;
	private SimpleAttributeSet styleSet;
	private Timer timer;
	private Block curr;
	private Block next;
	private String status;
	
	int x = 3; //Default Position.
	int y = 0;
	
	public int probability = 70;     // I 블럭 관련, easy : 72, normal : 70, hard 68
	public int initInterval = 1000;  // 테트리스 속도 관련  easy : 800, normal : 1000, hard : 1200 
	
	public Board() {
		super("Team 3 Tetris");
		//Set timer for block drops.
		applyDifficulty();								// 난이도 설정 불러오기 

		// Initialize board for the game.
		gameScore = new GameScore();
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
		timer = new Timer(initInterval, new ActionListener() { // initInterval 마다 actionPerformed
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
		scorePane.setBounds(280, 130, 130, 50);
		scorePane.setBorder(border2);
		scorePane.setText("Score : "+ gameScore.getScore());
		this.getContentPane().add(scorePane);

		//statusBar
		statusBar = new JTextPane();
		statusBar.setEditable(false);
		TitledBorder border3 = BorderFactory.createTitledBorder("Status");
		statusBar.setBounds(280, 570, 130, 50);
		statusBar.setBorder(border3);
		this.getContentPane().add(statusBar);

		//Background
		background = new JTextPane();
		background.setBackground(Color.WHITE);
		this.getContentPane().add(background);

		//difficultyBar
		difficultyBar = new JTextPane();
		difficultyBar.setEditable(false);
		TitledBorder border4 = BorderFactory.createTitledBorder("difficulty");
		difficultyBar.setBounds(280, 520, 130, 50);	
		// difficultyBar.setBounds(250, 520, 130, 50);
		difficultyBar.setBorder(border4); 
		difficultyBar.setText(Control.getDifficulty());		
		this.getContentPane().add(difficultyBar);

		//Document default style.
		styleSet = new SimpleAttributeSet();
		StyleConstants.setFontSize(styleSet, 20);
		StyleConstants.setFontFamily(styleSet, "DIALOG");
		StyleConstants.setBold(styleSet, true);
		//StyleConstants.setForeground(styleSet, Color.WHITE);
		StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);
	}

	protected Block getRandomBlock(int num, int blocks) {
		Random rnd = new Random(System.currentTimeMillis()*num); // Generate Random Number. // num : block과 previewBlock 구분을 위해 소수 곱하기
		int block = rnd.nextInt(blocks);

		if(block < 10) return new OBlock();
		else if (block <20) return new JBlock();
		else if (block <30) return new LBlock();
		else if (block <40) return new ZBlock();
		else if (block <50) return new SBlock();
		else if (block <60) return new TBlock();
		else return new IBlock();
	}
	
	private void applyDifficulty() {
		Control.setDifficulty(2);				// 처음 설정창과 연결하기 () 부분 난이도 설정 
		Control.setSpeed();
		Control.setProbability();
		initInterval = Control.getSpeed();
		probability = Control.getProbability();
	}
	
	private void pause() {

        isPaused = !isPaused;

        if (isPaused) {
        	status = "Pause!";
        } else {
        	status = "Resume!";
        }
        drawBoard();
        statusBar.setText(status);
    }
	
	private void escape() {
		timer.stop();
		Scoreboard sb = null;
		try {
			sb = new Scoreboard(Board.this);
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
		
		gameOverCheck();
		
		for(int j=0; j<curr.height(); ++j) {
			int rows = y+j == 0 ? 0 : y+j-1; // y+j가 0이면 rows = 0 아니면 rows = y+j-1
			int offset = rows * (WIDTH+3) + x + 1;
			doc.setCharacterAttributes(offset, curr.width(), styles, true);
			for(int i=0; i<curr.width(); ++i) { // 블럭 배열을 board 배열에 대입
				if(board[y+j][x+i] == 1) {
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
				if(inactiveBlock[y+j][x+i] == 1) {
					continue;
				}
				inactiveBlock[y+j][x+i] = curr.getShape(i, j);
			}
		}
	}
	
	public void updateScore(){
        String total = Integer.toString(gameScore.getScore());
        scorePane.setText(total);
    }
	
	private void eraseCurr() { // Erase current block.
		for(int i=x; i<x+curr.width(); i++) {
			for(int j=y; j<y+curr.height(); j++) {
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

	//4.18 - 김세한 - 블럭이동이 가능한지 검사하는 함수
	//4.19 - 고지완 - checkBottom으로 수정 및 오류 개선
	protected boolean checkBottom() {
		int w = curr.width();
		int h = curr.height();
		//블럭이 땅에 닿았는지 검사
		if (y + h >= HEIGHT) return false;
		
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
		if (x + curr.width() >= WIDTH) return false;
		
		int[][] currShape = curr.getBlockShape();
		
		//블럭의 각 요소가 다른블럭과 닿았는지 체크
		for(int j = 0; j < h; ++j) {
            for(int i = w-1; i >= w; --i) {
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
		if (x <= 0) return false;
		
		int[][] currShape = curr.getBlockShape();
		
		//블럭의 각 요소가 다른블럭과 닿았는지 체크
		for(int j = 0; j < j; ++j) {
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
	
	//git issues 해결중
	public void rotateOK() {
		curr.rotate();
		if (x + curr.width() > WIDTH) {
			x = x - curr.width(); return;
		}
	}

	protected void moveDown() {
		if(checkBottom()) {
			eraseCurr();
			y++;
			gameScore.increaseScore(); // score 증가
			updateScore(); 
			placeBlock();
		}
		else {
			// constantBlock에 블럭 모양 반영
			inactivateBlock();
			curr = next;
			next = getRandomBlock(1,probability);
			x = 3;
			y = 0;
			placeBlock();
		}
	} 
	
	protected void moveRight() {
		if(checkRight()) {
			eraseCurr();
			x++;
			placeBlock();
		}
	}

	protected void moveLeft() {
		if(checkLeft()) {
			eraseCurr();
			x--;
			placeBlock();
		}
	}

	public void gameOverCheck() {
		for(int k = 0; k<WIDTH; ++k) {
			if(board[0][k] == 1) {
				timer.stop();
				Scoreboard sb;
				try {
					sb = new Scoreboard(Board.this);
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
				eraseCurr();
				curr.rotate();
				//rotateOK();
				drawBoard();
				break;
			case KeyEvent.VK_SPACE:
				//harDrop();
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


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

public class ItemModeBoard extends JFrame {

	private static final long serialVersionUID = 2434035659171694595L; // version
	
	public static final int HEIGHT = 20;
	public static final int WIDTH = 10;
	public static final int PREVIEWHEIGHT = 4;
	public static final int PREVIEWWIDTH = 4;
	public static final char BORDER_CHAR = 'X';
	
	private int score = 100;  // 점수칸 매꾸는 용도
	private boolean isPaused = false;
	private JTextPane pane;
	private JTextPane previewPane;
	private JTextPane scorePane;
	private JTextPane statusBar;
	private JTextPane background;
	private int[][] ItemModeBoard;
	private int[][] previewItemModeBoard;
	private KeyListener playerKeyListener;
	private SimpleAttributeSet styleSet;
	private Timer timer;
	private Block curr;
	private Block next;
	String status;
	int x = 3; //Default Position.
	int y = 0;
	
	private static final int initInterval = 1000;
	
	public ItemModeBoard() {
		super("Team 3 Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X 버튼 눌렀을 때 닫히도록 설정
		
		//ItemModeBoard display setting.
		setSize(415,669);
		setLocationRelativeTo(null);
		CompoundBorder border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		
		pane = new JTextPane();
		pane.setEditable(false);
		pane.setBackground(Color.BLACK);
		pane.setBorder(border);
		pane.setBounds(0, 0, 230, 630);
		this.getContentPane().add(pane);
		
		//PreviewItemModeBoard 
		previewPane = new JTextPane();
		previewPane.setEditable(false); 
		previewPane.setBackground(Color.BLACK);
		previewPane.setBorder(border); 
		previewPane.setBounds(250, 0, 130, 130);
		this.getContentPane().add(previewPane);
		
		//ScoreItemModeBoard
		scorePane = new JTextPane();
		scorePane.setEditable(false);
		scorePane.setBorder(border);
		TitledBorder border2 = BorderFactory.createTitledBorder("SCORE");
		scorePane.setBounds(250, 130, 130, 50);	
		scorePane.setBorder(border2);
		scorePane.setText("Score : "+ score);
		this.getContentPane().add(scorePane);
		
		//statusBar
		statusBar = new JTextPane();
		statusBar.setEditable(false);
		TitledBorder border3 = BorderFactory.createTitledBorder("Status");
		statusBar.setBounds(250, 570, 130, 50);	
		statusBar.setBorder(border3); 
		this.getContentPane().add(statusBar);
		
		//Background
		background = new JTextPane();
		background.setBackground(Color.WHITE);		
		this.getContentPane().add(background);
		 
		//Document default style.
		styleSet = new SimpleAttributeSet();
		StyleConstants.setFontSize(styleSet, 20);
		StyleConstants.setFontFamily(styleSet, "MONOSPACED");
		StyleConstants.setBold(styleSet, true);
		StyleConstants.setForeground(styleSet, Color.WHITE);
		StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);
		
		//Set timer for block drops.
		timer = new Timer(initInterval, new ActionListener() { // initInterval 마다 actionPerformed 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isPaused) {
					moveDown();
					drawItemModeBoard();
				}
			}
		});
		
		// Initialize ItemModeBoard for the game.
		ItemModeBoard = new int[HEIGHT][WIDTH];
		previewItemModeBoard = new int[PREVIEWHEIGHT][PREVIEWWIDTH];
		playerKeyListener = new PlayerKeyListener();
		addKeyListener(playerKeyListener);
		setFocusable(true);
		requestFocus(); // 컴포넌트가 이벤트를 받을 수 있게 함. (키 이벤트 독점)
	
		// Create block and draw.
		curr = getRandomFirstBlock();
		next = getRandomBlock();
		placeBlock();
		placePreBlock();
		drawItemModeBoard();
		drawPreviewItemModeBoard();
		timer.start();
	}

	private Block getRandomFirstBlock() {
		
		Random rnd = new Random(System.currentTimeMillis()*11); // Generate Random Number.
		int block = rnd.nextInt(6);
		switch(block) {
		case 0:
			return new IBlock();
		case 1:
			return new JBlock();
		case 2:
			return new LBlock();
		case 3:
			return new ZBlock();
		case 4:
			return new SBlock();
		case 5:
			return new TBlock();
		case 6:
			return new OBlock();			
		}
		return new LBlock();
		
	}
	
	private Block getRandomBlock() {
		
		Random rnd = new Random(System.currentTimeMillis()); // Generate Random Number.
		int block = rnd.nextInt(7);
		switch(block) {
		case 0:
			return new IBlock();
		case 1:
			return new JBlock();
		case 2:
			return new LBlock();
		case 3:
			return new ZBlock();
		case 4:
			return new SBlock();
		case 5:
			return new TBlock();
		case 6:
			return new OBlock();			
		}
		return new LBlock();
		
	}
	
	private void pause() {

        isPaused = !isPaused;

        if (isPaused) {
        	status = "Pause!";
        } else {
        	status = "Resume!";
        }
        drawItemModeBoard();
        statusBar.setText(status);
    }
	
	private void escape() {
		timer.stop();
		Scoreboard sb = null;
		try {
			sb = new Scoreboard();
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
			for(int i=0; i<next.width(); i++) { // 블럭 배열을 previewItemModeBoard 배열에 대입
				previewItemModeBoard[j][i] = next.getShape(i, j);
			}
		}
		
	}
	
	private void placeBlock() {
		StyledDocument doc = pane.getStyledDocument();
		SimpleAttributeSet styles = new SimpleAttributeSet();
//		StyleConstants.setForeground(styles, curr.getColor());
		
		gameOverCheck();
		
		for(int j=0; j<curr.height(); j++) {
			int rows = y+j == 0 ? 0 : y+j-1; // y+j가 0이면 rows = 0 아니면 rows = y+j-1
			int offset = rows * (WIDTH+3) + x + 1;
			doc.setCharacterAttributes(offset, curr.width(), styles, true);
			for(int i=0; i<curr.width(); i++) { // 블럭 배열을 ItemModeBoard 배열에 대입
				ItemModeBoard[y+j][x+i] = curr.getShape(i, j);
			}			
		}
		
		eraseNext();
		placePreBlock();
	}
	
	private void eraseCurr() { // Erase current block.
		for(int i=x; i<x+curr.width(); i++) {
			for(int j=y; j<y+curr.height(); j++) {
				ItemModeBoard[j][i] = 0;
			}
		}  		 
	}
	
	private void eraseNext() {
		for(int i = 0; i < PREVIEWWIDTH; i++) {
			for(int j=0; j < PREVIEWHEIGHT; j++) {
				previewItemModeBoard[j][i] = 0; 
			}
		}
	}

	protected void moveDown() {
		eraseCurr();
		if(y < HEIGHT - curr.height()) y++;
		else {
			placeBlock();
			curr = next;
			next = getRandomBlock();
			x = 3;
			y = 0;
		} 
		placeBlock();
	} 
	
	protected void moveRight() {
		eraseCurr();
		if(x < WIDTH - curr.width()) x++;
		placeBlock();
	}

	protected void moveLeft() {
		eraseCurr();
		if(x > 0) {
			x--;
		}
		placeBlock();
	}

	public void gameOverCheck() {
		for(int k = 0; k<WIDTH; ++k) {
			if(ItemModeBoard[0][k] == 1) {
				timer.stop();
				Scoreboard sb;
				try {
					sb = new Scoreboard();
					sb.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void drawItemModeBoard() {
		StyleConstants.setForeground(styleSet, curr.getColor());
		StringBuilder sb = new StringBuilder(); // 문자열 추가나 변경등의 작업이 많을 경우에는 StringBuilder를, 문자열 변경 작업이 거의 없는 경우에는 그냥 String을 사용하는 것이 유리
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR); // 윗쪽 벽
		sb.append("\n");
		for(int i=0; i < ItemModeBoard.length; i++) {
			sb.append(BORDER_CHAR); // 왼쪽 벽
			for(int j=0; j < ItemModeBoard[i].length; j++) { // 블럭에 해당되는 부분 draw
				if(ItemModeBoard[i][j] == 1) {
					sb.append("O");
				} else {
					sb.append(" ");
				}
			}
			sb.append(BORDER_CHAR); // 오른쪽 벽
			sb.append("\n");
		}
		for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR); // 아랫쪽 벽
		pane.setText(sb.toString());
		StyledDocument doc = pane.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
		pane.setStyledDocument(doc);
		drawPreviewItemModeBoard();
	}
	
	public void drawPreviewItemModeBoard() {
		StyleConstants.setForeground(styleSet, next.getColor());
		StringBuilder sb2 = new StringBuilder(); 
		
		sb2.append("\n");
		for(int i=0; i < previewItemModeBoard.length; i++) {
			for(int j=0; j < previewItemModeBoard[i].length; j++) { // 블럭에 해당되는 부분 draw
				if(previewItemModeBoard[i][j] == 1) {
					sb2.append("O");
				} else {
					sb2.append(" ");
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
		this.ItemModeBoard = new int[HEIGHT][WIDTH];
		this.previewItemModeBoard = new int [PREVIEWHEIGHT][PREVIEWWIDTH];
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
				drawItemModeBoard();
				break;
			case KeyEvent.VK_RIGHT:
				moveRight();
				drawItemModeBoard();
				break;
			case KeyEvent.VK_LEFT:
				moveLeft();
				drawItemModeBoard();
				break;
			case KeyEvent.VK_UP:
				eraseCurr();
				curr.rotate();
				drawItemModeBoard();
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

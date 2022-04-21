package team3.tetris.component;

import team3.tetris.record.RecordDTO;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;


public class Scoreboard extends JFrame{
	private boolean isSubmitted;
	Container con;
	JLabel label;
	ScoreTable scoretable;
	InputPanel inputPanel;
	ExitPanel exitPanel;
	
	GameScore gameScore;
	Board parent;
	String mode;
	String difficulty;
	
	public Scoreboard(GameScore gameScore, Board parent) throws IOException {
		super("ScoreBoard");
		this.isSubmitted = false;
		this.gameScore = gameScore;
		this.parent = parent;
		this.mode = parent.isNormal ? "normal" : "item";
		this.difficulty = parent.difficulty.getStringDifficulty();
		
		setSize(515, 500);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowFocusListener(new myWindowFocusListener());
		
		CompoundBorder border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		
		con = getContentPane();
		
		label = new JLabel("Game Over!");
		label.setFont(new Font("SansSerif", Font.BOLD, 30));
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		
		scoretable = new ScoreTable(mode, difficulty);
		inputPanel = new InputPanel(this);
		exitPanel = new ExitPanel(this.parent, this);
		
		scoretable.setBorder(border);
		setLayout(null);
        
		label.setBounds(0, 0, 500, 100);
		scoretable.setBounds(0, 100, 500, 300);
		inputPanel.setBounds(0, 400, 500, 100);
		exitPanel.setBounds(0, 400, 500, 100);
		
        con.add(label);
        con.add(scoretable);
        con.add(inputPanel);
	}

	public void checkSubmission(boolean isSubmitted, String nameT) throws IOException {
		this.isSubmitted = isSubmitted;
		if(isSubmitted) {
			con.remove(inputPanel);
			con.add(exitPanel);
			RecordDTO newRecord = new RecordDTO(mode, difficulty, nameT, gameScore.getScore(), null );
			newRecord.setTime();
			scoretable.updateScoreTable(newRecord);
			setVisible(false);
			setVisible(true);
		} else {
			if(parent != null) {
				parent.dispose();
			}
			dispose();
		}
	}
	
	public class myWindowFocusListener implements WindowFocusListener {

		@Override
		public void windowGainedFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			if(isSubmitted) {
				exitPanel.mainMenu.requestFocus();
				exitPanel.curBtn = exitPanel.mainMenu;
			} else {
				inputPanel.nameT.requestFocus();
				inputPanel.curBtn = inputPanel.okBtn;
			}
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}



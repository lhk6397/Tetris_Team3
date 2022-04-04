package team3.tetris.component;

import team3.tetris.record.RecordDTO;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;

public class Scoreboard extends JFrame{
	Container con;
	JLabel label;
	ScoreTable scoreTable;
	InputPanel inputPanel;
	ExitPanel exitPanel;
	
	Board parent;
	String mode = "NORMAL";
	String level = "EASY";
    
	public void checkSubmission(boolean isSubmitted, RecordDTO record) throws IOException {
		if(isSubmitted) {
			con.remove(inputPanel);
			con.add(exitPanel);
			setVisible(false);
			setVisible(true);
			setNewScoreboard(scoreTable, record);

		} else {
			if(parent != null) {
				parent.dispose();
			}
			dispose();
		}
	}
	
	public Scoreboard(Board... parent) throws IOException {
		super("ScoreBoard");
		this.parent = parent.length > 0 ? parent[0] : null;
		setSize(515, 500);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
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
		
		scoreTable = new ScoreTable(mode, level);
		inputPanel = new InputPanel(this);
		exitPanel = new ExitPanel(this.parent, this);
		
		scoreTable.setBorder(border);
		setLayout(null);
        
		label.setBounds(0, 0, 500, 100);
		scoreTable.setBounds(0, 100, 500, 300);
		inputPanel.setBounds(0, 400, 500, 100);
		exitPanel.setBounds(0, 400, 500, 100);
		
        con.add(label);
        con.add(scoreTable);
        con.add(inputPanel);
	}

	//newScoreboard를 화면에 표시
	public void setNewScoreboard(ScoreTable scoreTable, RecordDTO record) throws IOException {
		scoreTable.updateScoreTable(record);
	}

}



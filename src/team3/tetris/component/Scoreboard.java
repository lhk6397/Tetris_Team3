package team3.tetris.component;

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

/*
 * 클래스: Scoreboard
 * 기능: 게임 종료 시 띄우는 Frame
 */

public class Scoreboard extends JFrame{
	private boolean isSubmitted = false;
	Container con;
	JLabel label;
	ScoreTable scoretable;
	InputPanel inputPanel;
	ExitPanel exitPanel;
	
	Board parent;
    
	public void checkSubmission(boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
		if(isSubmitted) {
			con.remove(inputPanel);
			con.add(exitPanel);
			setVisible(false);
			setVisible(true);
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
		
		scoretable = new ScoreTable();
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



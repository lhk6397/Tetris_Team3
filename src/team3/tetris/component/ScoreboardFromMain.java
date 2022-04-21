package team3.tetris.component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;


public class ScoreboardFromMain extends JFrame implements ActionListener{
	private boolean isSubmitted = false;
	Container con;
	JLabel label;
	JButton backBtn;
	JButton curBtn;
	ScoreTable scoretable;
	
	GameScore gameScore;
	MainMenu parent;
	String mode = "normal";
	String level = "easy";

	public ScoreboardFromMain(MainMenu parent) throws IOException {
		super("ScoreBoard");
		this.parent = parent;
		setSize(515, 500);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowFocusListener(new myWindowFocusListener());
		
		CompoundBorder border = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 10),
				BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		
		con = getContentPane();
		con.setBackground(Color.BLACK);
		
		label = new JLabel("SCORE BOARD");
		label.setFont(new Font("SansSerif", Font.BOLD, 30));
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		
		scoretable = new ScoreTable(mode, level);
		scoretable.setBorder(border);
		setLayout(null);
        
		backBtn = new JButton("Back to MainMenu");
		backBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		backBtn.setBackground(Color.BLACK);
		backBtn.setHorizontalAlignment(JLabel.CENTER);
		backBtn.setForeground(Color.WHITE);
		backBtn.addFocusListener(new MyFocusListener());
		backBtn.addActionListener(this);
		backBtn.addKeyListener(new PlayerKeyListener());
		
		label.setBounds(0, 0, 500, 100);
		scoretable.setBounds(0, 100, 500, 300);
		backBtn.setBounds(125, 425, 250, 25);
		
        con.add(label);
        con.add(scoretable);
        con.add(backBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			this.dispose();
			parent.setVisible(true);
		}
	}
	
	public class PlayerKeyListener implements KeyListener { // key 입력
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
			        curBtn.doClick(); 
					break;
				default:
					break;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {}

	}
	
	public class MyFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			curBtn = (JButton)e.getComponent();
			curBtn.setBackground(Color.WHITE);
			curBtn.setForeground(Color.BLACK);
		}

		@Override
		public void focusLost(FocusEvent e) {
			curBtn.setBackground(Color.BLACK);
			curBtn.setForeground(Color.WHITE);
		}	
	}
	
	public class myWindowFocusListener implements WindowFocusListener {

		@Override
		public void windowGainedFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			backBtn.requestFocus();
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}



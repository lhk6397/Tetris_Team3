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
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;

import team3.tetris.component.InputPanel.MyFocusListener;
import team3.tetris.component.InputPanel.PlayerKeyListener;


public class ScoreboardFromMain extends JFrame implements ActionListener{
	private boolean isSubmitted = false;
	
	CompoundBorder border;
	Container con;
	JLabel label;
	JButton easy;
	JButton normal;
	JButton hard;
	JButton normalMode;
	JButton itemMode;
	JButton backBtn;
	JButton curBtn;
	ScoreTable scoretable;
	
	GameScore gameScore;
	MainMenu parent;
	String mode="normal";
	String difficulty="Easy";

	public ScoreboardFromMain(MainMenu parent) throws IOException {
		super("ScoreBoard");
		this.parent = parent;
		setSize(515, 500);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowFocusListener(new myWindowFocusListener());
		
		border = BorderFactory.createCompoundBorder(
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
		
		Box difficultyBox = Box.createHorizontalBox();
		Box modeBox = Box.createHorizontalBox();
		
		easy = new JButton("Easy");
		basicButtonSetting(easy);
		
		normal = new JButton("Normal");
		basicButtonSetting(normal);

		
		hard = new JButton("Hard");
		basicButtonSetting(hard);
		
		difficultyBox.add(easy);
		difficultyBox.add(normal);
		difficultyBox.add(hard);

		normalMode = new JButton("Normal Mode");
		basicButtonSetting(normalMode);

		itemMode = new JButton("Item Mode");
		basicButtonSetting(itemMode);
		
		modeBox.add(normalMode);
		modeBox.add(itemMode);
		
		scoretable = new ScoreTable(mode, difficulty);
		scoretable.setBorder(border);
		setLayout(null);
        
		backBtn = new JButton("Back to MainMenu");
		backBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		basicButtonSetting(backBtn);
		
		label.setBounds(0, 0, 500, 100);
		difficultyBox.setBounds(30, 70, 200, 30);
		modeBox.setBounds(270, 70, 200, 30);
		scoretable.setBounds(0, 100, 500, 300);
		backBtn.setBounds(125, 425, 250, 25);
		
        con.add(label);
        con.add(modeBox);
        con.add(difficultyBox);
        con.add(scoretable);
        con.add(backBtn);
	}
	public void basicButtonSetting(JButton btn) {
		btn.setBackground(Color.BLACK);
		btn.setForeground(Color.WHITE);
		btn.setHorizontalAlignment(JLabel.CENTER);
		btn.addKeyListener(new PlayerKeyListener());
		btn.addFocusListener(new MyFocusListener());
		btn.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			this.dispose();
			parent.setVisible(true);
		} else if(e.getSource() == easy) {
			this.difficulty = "Easy";
		} else if(e.getSource() == normal) {
			this.difficulty = "Normal";
		} else if(e.getSource() == hard) {
			this.difficulty = "Hard";
		} else if(e.getSource() == normalMode) {
			this.mode = "normal";
		} else if(e.getSource() == itemMode) {
			this.mode = "item";
		}
		
		try {
			con.remove(scoretable);
			scoretable = new ScoreTable(mode, difficulty);
			scoretable.setBorder(border);
			setLayout(null);
			con.add(scoretable);
			setVisible(false);
			setVisible(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public class PlayerKeyListener implements KeyListener { // key 입력
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_DOWN,  KeyEvent.VK_RIGHT:
					if(curBtn == itemMode) {
						backBtn.requestFocus();
					}
					curBtn.transferFocus();
					break;
				case KeyEvent.VK_UP, KeyEvent.VK_LEFT:
					curBtn.transferFocusBackward();

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
			easy.requestFocus();
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}



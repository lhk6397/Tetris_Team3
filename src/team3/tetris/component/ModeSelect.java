package team3.tetris.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * 클래스: ModeSelect
 * 기능: Start시 모드 선택 창을 띄움
 */

public class ModeSelect extends JFrame implements ActionListener {
	private KeyListener playerKeyListener;
	JPanel pan;
	JLabel label;
	JButton easy;
	JButton normal;
	JButton hard;
	JButton ItemMode;
	JButton scoreBoardReset;
	JButton colorBlind;
	JButton settingsReset;
	JButton exit;
	JButton curBtn;
    
	
	
	public ModeSelect() {
		super("ModeSelect");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		addWindowFocusListener(new myWindowFocusListener());
		playerKeyListener = new PlayerKeyListener();
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(null);
		
		label = new JLabel("ModeSelect");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 20));

		// easy button
		easy = new JButton("EASY");
		easy.addActionListener(this);
		easy.addFocusListener(new MyFocusListener());
		easy.addKeyListener(playerKeyListener);
		easy.setBackground(Color.BLACK);
		easy.setForeground(Color.WHITE);
		
		// normal button
		normal = new JButton("NORMAL");
		normal.addActionListener(this);
		normal.addFocusListener(new MyFocusListener());
		normal.addKeyListener(playerKeyListener);
		normal.setBackground(Color.BLACK);
		normal.setForeground(Color.WHITE);
		
		// hard button
		hard = new JButton("HARD");
		hard.addActionListener(this);
		hard.addFocusListener(new MyFocusListener());
		hard.addKeyListener(playerKeyListener);
		hard.setBackground(Color.BLACK);
		hard.setForeground(Color.WHITE);
		
		// ItemMode button
		ItemMode = new JButton("ItemMode");
		ItemMode.addActionListener(this);
		ItemMode.addFocusListener(new MyFocusListener());
		ItemMode.addKeyListener(playerKeyListener);
		ItemMode.setBackground(Color.BLACK);
		ItemMode.setForeground(Color.WHITE);
		
		// exit button
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		label.setBounds(33, 0, 270, 30);
		easy.setBounds(33, 30, 90, 30);
		normal.setBounds(123, 30, 90, 30);
		hard.setBounds(213, 30, 90, 30);
		ItemMode.setBounds(33, 70, 270, 30);
		exit.setBounds(33, 230, 270, 30);
		
		pan.add(label);
		pan.add(easy);
		pan.add(normal);
		pan.add(hard);
		pan.add(ItemMode);
		pan.add(exit);
		
		add(pan);
		
		easy.requestFocus();
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == easy) {
			new Board().setVisible(true);
		}
		else if(e.getSource() == normal) {
			new Board().setVisible(true);
		}
		else if(e.getSource() == hard) {
			new Board().setVisible(true);
		}
		else if(e.getSource() == ItemMode) {
			System.exit(0);
		}
		else if(e.getSource() == exit) {
			System.exit(0);
		}
	}
	
	public class PlayerKeyListener implements KeyListener { // key 입력
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
				case KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT:
					curBtn.transferFocus();
					break;
				case KeyEvent.VK_UP, KeyEvent.VK_LEFT:
					curBtn.transferFocusBackward();
					break;
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
package team3.tetris.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Settings extends JFrame implements ActionListener {
	private KeyListener playerKeyListener;
	JPanel pan;
	JLabel label;
	JButton small;
	JButton medium;
	JButton large;
	JButton keySettings;
	JButton scoreBoardReset;
	JButton colorBlind;
	JButton settingsReset;
	JButton exit;
	JButton curBtn;
    
	public Settings() {
		super("Settings");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		addWindowFocusListener(new myWindowFocusListener());
		playerKeyListener = new PlayerKeyListener();
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(null);
		
		label = new JLabel("Settings");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 20));

		// small button
		small = new JButton("SMALL");
		small.addActionListener(this);
		small.addFocusListener(new MyFocusListener());
		small.addKeyListener(playerKeyListener);
		small.setBackground(Color.BLACK);
		small.setForeground(Color.WHITE);
		
		// medium button
		medium = new JButton("MEDIUM");
		medium.addActionListener(this);
		medium.addFocusListener(new MyFocusListener());
		medium.addKeyListener(playerKeyListener);
		medium.setBackground(Color.BLACK);
		medium.setForeground(Color.WHITE);
		
		// large button
		large = new JButton("LARGE");
		large.addActionListener(this);
		large.addFocusListener(new MyFocusListener());
		large.addKeyListener(playerKeyListener);
		large.setBackground(Color.BLACK);
		large.setForeground(Color.WHITE);
		
		// keySettings button
		keySettings = new JButton("KeySettings (키설정)");
		keySettings.addActionListener(this);
		keySettings.addFocusListener(new MyFocusListener());
		keySettings.addKeyListener(playerKeyListener);
		keySettings.setBackground(Color.BLACK);
		keySettings.setForeground(Color.WHITE);
		
		// scoreBoardReset button
		scoreBoardReset = new JButton("ScoreBoardReset (점수판 초기화)");
		scoreBoardReset.addActionListener(this);
		scoreBoardReset.addFocusListener(new MyFocusListener());
		scoreBoardReset.addKeyListener(playerKeyListener);
		scoreBoardReset.setBackground(Color.BLACK);
		scoreBoardReset.setForeground(Color.WHITE);
		
		// colorBlind button
		colorBlind = new JButton("ClorBlind (색맹)");
		colorBlind.addActionListener(this);
		colorBlind.addFocusListener(new MyFocusListener());
		colorBlind.addKeyListener(playerKeyListener);
		colorBlind.setBackground(Color.BLACK);
		colorBlind.setForeground(Color.WHITE);
		
		// settingsReset button
		settingsReset = new JButton("SettingsReset (설정 초기화)");
		settingsReset.addActionListener(this);
		settingsReset.addFocusListener(new MyFocusListener());
		settingsReset.addKeyListener(playerKeyListener);
		settingsReset.setBackground(Color.BLACK);
		settingsReset.setForeground(Color.WHITE);
		
		// exit button
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		label.setBounds(33, 0, 270, 30);
		small.setBounds(33, 30, 90, 30);
		medium.setBounds(123, 30, 90, 30);
		large.setBounds(213, 30, 90, 30);
		keySettings.setBounds(33, 70, 270, 30);
		scoreBoardReset.setBounds(33, 110, 270, 30);
		colorBlind.setBounds(33, 150, 270, 30);
		settingsReset.setBounds(33, 190, 270, 30);
		exit.setBounds(33, 230, 270, 30);
		
		pan.add(label);
		pan.add(small);
		pan.add(medium);
		pan.add(large);
		pan.add(keySettings);
		pan.add(scoreBoardReset);
		pan.add(colorBlind);
		pan.add(settingsReset);
		pan.add(exit);
		
		add(pan);
		
		small.requestFocus();
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == small) {
			System.exit(0);
		}
		else if(e.getSource() == medium) {
			System.exit(0);
		}
		else if(e.getSource() == large) {
			System.exit(0);
		}
		else if(e.getSource() == keySettings) {
			System.exit(0);
		}
		else if(e.getSource() == scoreBoardReset) {
			System.exit(0);
		}
		else if(e.getSource() == colorBlind) {
			System.exit(0);
		}
		else if(e.getSource() == settingsReset) {
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
			small.requestFocus();
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}

package team3.tetris.component;

import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
	private KeyListener playerKeyListener;
	JLabel label;
	JButton start;
	JButton settings;
	JButton scoreBoard;
	JButton exit;
	JButton curBtn;
    private CustomButton startButton;
    private CustomButton settingButton; 
    private CustomButton scoreButton; 
    private CustomButton exitButton;
    private CustomButton normalModeButton; 
    private CustomButton itemModeButton;
    private CustomButton settingBoard_ExitButton;
    private CustomButton scoreBoard_ExitButton; 
    private JPanel pan;                        
    private JPanel titlePanel;
    private JPanel buttonPannel1; 
    private JPanel buttonPannel2; 
    private JPanel buttonPannel3;
    private JPanel buttonPannel4;  
    private JPanel settingBoard;
    private JPanel modeBoard;           
    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;

    private int[] preferredResolution;  // frame resolution - frame top border

    public MainMenu() {
        setSize(350, 300);
        pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(new GridLayout(0,1));

		addWindowFocusListener(new myWindowFocusListener());
		playerKeyListener = new PlayerKeyListener();
		
		label = new JLabel("Tetris");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		// start button
		start = new JButton("START");
		start.addActionListener(this);
		start.addFocusListener(new MyFocusListener());
		start.addKeyListener(playerKeyListener);
		start.setBackground(Color.BLACK);
		start.setForeground(Color.WHITE);
				
		// settings button
		settings = new JButton("SETTINGS");
		settings.addActionListener(this);
		settings.addFocusListener(new MyFocusListener());
		settings.addKeyListener(playerKeyListener);
		settings.setBackground(Color.BLACK);
		settings.setForeground(Color.WHITE);

		// scoreBoard button
		scoreBoard = new JButton("SCOREBOARD");
		scoreBoard.addActionListener(this);
		scoreBoard.addFocusListener(new MyFocusListener());
		scoreBoard.addKeyListener(playerKeyListener);
		scoreBoard.setBackground(Color.BLACK);
		scoreBoard.setForeground(Color.WHITE);
				
		// exit button
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.addFocusListener(new MyFocusListener());
		exit.addKeyListener(playerKeyListener);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		label.setBounds(33, 0, 270, 30);
		start.setBounds(33, 30, 270, 30);
		settings.setBounds(33, 70, 270, 30);
		scoreBoard.setBounds(33, 110, 270, 30);
		exit.setBounds(33, 150, 270, 30);
		
		pan.add(label);
		pan.add(start);
		pan.add(settings);
		pan.add(scoreBoard);
		pan.add(exit);
		
		add(pan);
		
		start.requestFocus();
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			ModeSelect selector = new ModeSelect();
			selector.setVisible(true);
			dispose();
		}
		else if(e.getSource() == settings) {
			// 세팅 인터페이스 띄우기
			new Settings().setVisible(true);

			dispose();
		}
		else if(e.getSource() == scoreBoard) {
			try {
				new ScoreboardFromMain(this).setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
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
			start.requestFocus();
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
